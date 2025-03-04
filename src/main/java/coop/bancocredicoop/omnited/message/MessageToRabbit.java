package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.service.rabbit.RabbitSenderService;
import org.springframework.stereotype.Component;

@Component
public class MessageToRabbit {

    private final ObjectMapper objectMapper;
    private final RabbitSenderService rabbitSenderService;

    public MessageToRabbit(
            ObjectMapper objectMapper,
            RabbitSenderService rabbitSenderService) {
        this.objectMapper = objectMapper;
        this.rabbitSenderService = rabbitSenderService;
    }

    public void processMessage(String idMensaje, String mensajeType, String mensajeJson) {

        try {
            MensajeJSON message = MensajeJSON.newBuilder()
                    .setIdMensaje(idMensaje)
                    .setMensajeType(mensajeType)
                    .setMensajeJson(mensajeJson)
                    .build();

            // Usar el servicio RabbitSenderService para enviar el mensaje
            rabbitSenderService.sendMessage(message);

        } catch (Exception e) {
            System.out.println("Error en el processMessage" + e);
        }
    }
    
    public void processMessageMulticast(String idMensaje, String mensajeType, String mensajeJson, Long idSector) {
        
        try {
            MensajeJSON message = MensajeJSON.newBuilder()
                    .setIdMensaje(idMensaje)
                    .setMensajeType(mensajeType)
                    .setMensajeJson(mensajeJson)
                    .setIdSector(Math.toIntExact(idSector))
                    .build();

            // Usar el servicio RabbitSenderService para enviar el mensaje
            rabbitSenderService.sendMessage(message);

        } catch (Exception e) {
            System.out.println("Error en el processMessage" + e);
        }
    }
    
    public void processMessageUnicast(String idMensaje, String mensajeType, String mensajeJson, Long idUsuario) {
        
        try {
            MensajeJSON message = MensajeJSON.newBuilder()
                    .setIdMensaje(idMensaje)
                    .setMensajeType(mensajeType)
                    .setMensajeJson(mensajeJson)
                    .setIdUsuario(Math.toIntExact(idUsuario))
                    .build();

            // Usar el servicio RabbitSenderService para enviar el mensaje
            rabbitSenderService.sendMessage(message);

        } catch (Exception e) {
            System.out.println("Error en el processMessage" + e);
        }
    }
}