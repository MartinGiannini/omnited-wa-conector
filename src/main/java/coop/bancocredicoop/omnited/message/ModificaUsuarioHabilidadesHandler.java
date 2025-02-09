package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.config.MessageOut;
import coop.bancocredicoop.omnited.message.models.RetornoCambiosRealizados;
import coop.bancocredicoop.omnited.message.models.UsuarioDatos;
import coop.bancocredicoop.omnited.service.db.UsuarioService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitSenderService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class ModificaUsuarioHabilidadesHandler implements RabbitMessageHandler {

    private final UsuarioService usuarioService;
    private final RabbitSenderService rabbitSenderService;

    public ModificaUsuarioHabilidadesHandler(UsuarioService usuarioService, RabbitSenderService rabbitSenderService) {
        this.usuarioService = usuarioService;
        this.rabbitSenderService = rabbitSenderService;
    }

    @Override
    public void handle(String jsonPayload, String idMensaje) throws Exception {
        try {
            // Deserializar el JSON recibido
            ObjectMapper objectMapper = new ObjectMapper();
            UsuarioDatos usuarioDatos = objectMapper.readValue(jsonPayload, UsuarioDatos.class);
            
            // Llamar al servicio para guardar las habilidades
            usuarioService.actualizaUsuarioHabilidad(usuarioDatos.getIngresoDatos().getUsuario());

            // Crear una instancia de RetornoIngreso
            RetornoCambiosRealizados cambios = new RetornoCambiosRealizados(
                    "Operación Exitosa",
                    "Las habilidades fueron actualizadas correctamente.",
                    "ok"
            );
            
            String retorno = objectMapper.writeValueAsString(cambios);

            processMessage("cambiosRealizadosDB", retorno, idMensaje);

        } catch (JsonProcessingException e) {
            System.err.println("Error procesando habilidades de usuario: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Método para enviar el mensaje al RabbitMQ.
     *
     * @param usuarioJson Usuario serializado como JSON
     */
    private void processMessage(String type, String usuarioJson, String idMensaje) {

        try {
            MessageOut.MensajeJSON message = MessageOut.MensajeJSON.newBuilder()
                    .setId(idMensaje)
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
