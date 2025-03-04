package coop.bancocredicoop.omnited.message;

import java.util.Set;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.entity.Usuario;
import coop.bancocredicoop.omnited.exposition.SectorDTO;
import coop.bancocredicoop.omnited.message.models.RetornoMensajeRealizado;
import coop.bancocredicoop.omnited.message.models.UsuarioDatos;
import coop.bancocredicoop.omnited.service.db.SectorService;
import coop.bancocredicoop.omnited.service.db.UsuarioSectorService;
import coop.bancocredicoop.omnited.service.db.UsuarioService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class UsuarioModificaHandler implements RabbitMessageHandler {

    private final UsuarioService usuarioService;
    private final UsuarioSectorService usuarioSectorService;
    private final SectorService sectorService;
    private final MessageToRabbit messageToRabbit;
    private static Set<Long> idSectores;

    public UsuarioModificaHandler(
            UsuarioService usuarioService,
            UsuarioSectorService usuarioSectorService,
            SectorService sectorService,
            MessageToRabbit messageToRabbit) {
        this.usuarioService = usuarioService;
        this.usuarioSectorService = usuarioSectorService;
        this.sectorService = sectorService;
        this.messageToRabbit = messageToRabbit;
    }

    RetornoMensajeRealizado mensajeRealizado = new RetornoMensajeRealizado(
            "Operación Exitosa",
            "Las habilidades fueron actualizadas correctamente.",
            "ok"
    );

    @Override
    public void handle(String idMensaje, String mensajeJson) throws Exception {
        try {
            // Deserializar el JSON recibido
            ObjectMapper objectMapper = new ObjectMapper();
            UsuarioDatos usuarioDatos = objectMapper.readValue(mensajeJson, UsuarioDatos.class);
            Usuario usuarioGuardado;
            String type;

            idSectores = usuarioDatos.getIngresoDatos().getUsuario().getUsuarioSector().stream()
                    .map(SectorDTO::getIdSector)
                    .collect(Collectors.toSet());

            if (usuarioDatos.getIngresoDatos().getUsuario().getIdUsuario() != null) {
                // Si el idUsuario es diferente a null, se pide una actualizacion
                usuarioService.usuarioActualizar(usuarioDatos.getIngresoDatos().getUsuario());
                type = "usuarioActualizarDB";
            } else {
                // Si el idUsuario es null se entiende que se ingresa un nuevo usuario.
                usuarioGuardado =  usuarioService.usuarioAgregar(usuarioDatos.getIngresoDatos().getUsuario());
                type = "usuarioAgregarDB";
                usuarioDatos.getIngresoDatos().getUsuario().setIdUsuario(usuarioGuardado.getIdUsuario());
            }

            if (usuarioDatos.getIngresoDatos().getUsuario().getUsuarioPerfil().getIdPerfil() == 2) {
                usuarioService.actualizaUsuarioPermisoSupervision(usuarioDatos.getIngresoDatos().getUsuario());
                usuarioService.actualizaUsuarioPermisoOperacion(usuarioDatos.getIngresoDatos().getUsuario());
            } else {
                usuarioService.actualizaUsuarioPermisoOperacion(usuarioDatos.getIngresoDatos().getUsuario());
            }

            usuarioSectorService.guardarUsuarioConSectores(usuarioDatos.getIngresoDatos().getUsuario().getIdUsuario(), usuarioDatos.getIngresoDatos().getSectores());

            String usuarioJson = objectMapper.writeValueAsString(usuarioDatos.getIngresoDatos().getUsuario());
            
            idSectores.forEach(idSector -> {
                messageToRabbit.processMessageMulticast(idMensaje, type, usuarioJson, idSector);
            });
            
            String mensaje = objectMapper.writeValueAsString(mensajeRealizado);
            messageToRabbit.processMessage(idMensaje, "cambiosRealizadosDB", mensaje);

        } catch (JsonProcessingException e) {
            System.err.println("Error procesando habilidades de usuario: " + e.getMessage());
            throw e;
        }
    }
}
