package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.config.MessageOut;
import coop.bancocredicoop.omnited.exposition.GrupoDatosDTO;
import coop.bancocredicoop.omnited.exposition.GrupoEstadoDTO;
import coop.bancocredicoop.omnited.exposition.GrupoHabilidadDTO;
import coop.bancocredicoop.omnited.message.models.LoginSectores;
import coop.bancocredicoop.omnited.service.db.GrupoEstadoService;
import coop.bancocredicoop.omnited.service.db.GrupoHabilidadService;
import coop.bancocredicoop.omnited.service.rabbit.MessageHandler;
import coop.bancocredicoop.omnited.service.rabbit.RabbitSenderService;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioLoginGruposHandler implements MessageHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RabbitSenderService rabbitSenderService;
    private final GrupoEstadoService grupoEstadoService;
    private final GrupoHabilidadService grupoHabilidadService;

    public UsuarioLoginGruposHandler(GrupoEstadoService grupoEstadoService, GrupoHabilidadService grupoHabilidadService, RabbitSenderService rabbitSenderService) {
        this.grupoEstadoService = grupoEstadoService;
        this.grupoHabilidadService = grupoHabilidadService;
        this.rabbitSenderService = rabbitSenderService;
    }

    @Override
    public void handle(String jsonPayload, String idMensaje) throws Exception {
        // Mapear el nodo al objeto UsuarioDatos
        LoginSectores loginSectores = objectMapper.readValue(jsonPayload, LoginSectores.class);

        Set<Long> sectores = loginSectores.getSectoresDatos().getSectores().stream()
                .map(LoginSectores.SectorInput::getId)
                .collect(Collectors.toSet());

        Set<GrupoEstadoDTO> grupoEstados = grupoEstadoService.getGrupoEstadosBySectores(sectores);
        Set<GrupoHabilidadDTO> grupoHabilidades = grupoHabilidadService.getGrupoHabilidadesBySectores(sectores);

        // Crear el objeto contenedor
        GrupoDatosDTO grupoDatos = new GrupoDatosDTO(grupoEstados, grupoHabilidades);

        // Serializar el objeto a JSON
        String grupoDatosJson = objectMapper.writeValueAsString(grupoDatos);

        // Enviar el mensaje serializado a RabbitMQ
        processMessage("usuariologingruposDB", grupoDatosJson, idMensaje);
    }

    /**
     * Método para enviar el mensaje al RabbitMQ.
     *
     * @param usuarioJson Usuario serializado como JSON
     */
    private void processMessage(String type, String usuarioJson, String idMensaje) {

        try {
            MessageOut.MensajeJSON message = MessageOut.MensajeJSON.newBuilder()
                    .setId(idMensaje) // Reemplaza con un generador de ID único
                    .setType(type)
                    .setJsonPayload(usuarioJson)
                    //.setRecipients(usuarioNombre)
                    .build();

            // Usar el servicio RabbitSenderService para enviar el mensaje
            rabbitSenderService.sendMessage(message);

        } catch (Exception e) {
            System.out.println("Error en el processMessage" + e);
        }
    }
}
