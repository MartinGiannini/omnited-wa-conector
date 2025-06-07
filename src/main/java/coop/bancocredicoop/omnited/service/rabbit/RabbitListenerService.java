package coop.bancocredicoop.omnited.service.rabbit;

import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.message.BotWhatsAppHandler;
import coop.bancocredicoop.omnited.message.MessageToRabbit;
import coop.bancocredicoop.omnited.service.bot.DiagramaService;
import coop.bancocredicoop.omnited.utils.JsonCleaningService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitListenerService {

    private final Map<String, RabbitMessageHandler> handlers = new HashMap<>();
    
    /**
     * Servicios que se registran en la carga de la aplicación.
     *
     * @param botDefinition
     * @param messageToRabbit
     */
    public RabbitListenerService(
            JsonCleaningService jsonCleaningService,
            MessageToRabbit messageToRabbit,
            DiagramaService diagramaService
    ) {
        handlers.put("botWhatsAppDB", new BotWhatsAppHandler(jsonCleaningService, diagramaService));
    }

    /**
     * Colas registradas en Rabbit. Esta aplicación solo escuchará mensajes
     * provinientes de esas colas.
     *
     * @param message
     */
    @RabbitListener(queues = {
        "#{@environment.getProperty('spring.rabbitmq.colaDB_WA')}"
    })

    /**
     * Método que buscará manejar los mensajes de acuerdo al TYPE.
     *
     */
    public void receiveMessage(MensajeJSON message) {

        try {
            
            String idMensaje = message.getIdMensaje();
            String mensajeType = message.getMensajeType();
            String mensajeJson = message.getMensajeJson();
            long fechaEnvio = message.getFechaEnvio();
            
            // Identificar y procesar el mensaje según su tipo
            RabbitMessageHandler handler = handlers.get(mensajeType);

            if (handler != null) {
                handler.handle(idMensaje, mensajeJson, fechaEnvio);
            } else {
                System.err.println("No handler found for type: " + mensajeType);
            }
        } catch (Exception e) {
            System.err.println("Error handling message: " + e.getMessage());
        }
    }
}
