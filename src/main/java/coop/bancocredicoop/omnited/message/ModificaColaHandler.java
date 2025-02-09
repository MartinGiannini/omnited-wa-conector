package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.config.MessageOut;
import coop.bancocredicoop.omnited.message.models.ColaDatos;
import coop.bancocredicoop.omnited.message.models.RetornoCambiosRealizados;
import coop.bancocredicoop.omnited.service.db.ColaService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitSenderService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class ModificaColaHandler implements RabbitMessageHandler {

    private final ColaService colaService;
    private final RabbitSenderService rabbitSenderService;

    public ModificaColaHandler(ColaService colaService, RabbitSenderService rabbitSenderService) {
        this.colaService = colaService;
        this.rabbitSenderService = rabbitSenderService;
    }

    @Override
    public void handle(String jsonPayload, String idMensaje) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            ColaDatos cola = objectMapper.readValue(jsonPayload, ColaDatos.class);

            colaService.actualizarCola(cola.getIngresoDatos().getCola());

            // Crear una instancia de RetornoIngreso
            RetornoCambiosRealizados cambios = new RetornoCambiosRealizados(
                    "Operación Exitosa",
                    "La cola fue actualiza correctamente.",
                    "ok"
            );

            String retorno = objectMapper.writeValueAsString(cambios);
            processMessage("cambiosRealizadosDB", retorno, idMensaje);
        } catch (JsonProcessingException e) {
            System.err.println("Error procesando propiedades de cola: " + e.getMessage());
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
