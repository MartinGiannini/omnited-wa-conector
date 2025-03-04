package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.message.models.RetornoMensajeRealizado;
import coop.bancocredicoop.omnited.message.models.UsuarioDatos;
import coop.bancocredicoop.omnited.service.db.UsuarioService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class UsuarioPermisosOperacionModificaHandler implements RabbitMessageHandler {

    private final UsuarioService usuarioService;
    private final MessageToRabbit messageToRabbit;

    public UsuarioPermisosOperacionModificaHandler(
            UsuarioService usuarioService,
            MessageToRabbit messageToRabbit
    ) {
        this.usuarioService = usuarioService;
        this.messageToRabbit = messageToRabbit;
    }

    // Crear una instancia de RetornoIngreso
    RetornoMensajeRealizado cambios = new RetornoMensajeRealizado(
            "Operación Exitosa",
            "Los permisos fueron actualizados correctamente.",
            "ok"
    );

    @Override
    public void handle(String idMensaje, String mensajeJson) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            UsuarioDatos usuarioDatos = objectMapper.readValue(mensajeJson, UsuarioDatos.class);

            // Llamar al servicio para guardar los permisos
            usuarioService.actualizaUsuarioPermisoOperacion(usuarioDatos.getIngresoDatos().getUsuario());
            
            String retornoCambios = objectMapper.writeValueAsString(usuarioDatos.getIngresoDatos().getUsuario());
            messageToRabbit.processMessageMulticast(idMensaje, "usuarioPermisosOperacionSectorDB", retornoCambios, usuarioDatos.getIngresoDatos().getIdSector());
            messageToRabbit.processMessageMulticast(idMensaje, "usuarioPermisosOperacionUsuarioDB", retornoCambios, usuarioDatos.getIngresoDatos().getUsuario().getIdUsuario());
            
            String retorno = objectMapper.writeValueAsString(cambios);
            messageToRabbit.processMessage(idMensaje, "cambiosRealizadosDB", retorno);

        } catch (JsonProcessingException e) {
            System.err.println("Error procesando permisos de operación del usuario: " + e.getMessage());
            throw e;
        }
    }
}
