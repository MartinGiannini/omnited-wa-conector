package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.entity.BotWhatsApp;
import coop.bancocredicoop.omnited.service.bot.DiagramaService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;
import coop.bancocredicoop.omnited.utils.JsonCleaningService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BotWhatsAppHandler implements RabbitMessageHandler {

    private static final Logger LOGGER = Logger.getLogger(BotWhatsAppHandler.class.getName());
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JsonCleaningService jsonCleaningService;
    private final DiagramaService diagramaService;

    public BotWhatsAppHandler(
            JsonCleaningService jsonCleaningService,
            DiagramaService diagramaService
    ) {
        this.jsonCleaningService = jsonCleaningService;
        this.diagramaService = diagramaService;
    }

    @Override
    public void handle(String idMensaje, String mensajeJson, long fechaEnvio) throws Exception {

        try {
            // 1) Parseo el bot.
            BotWhatsApp botWhatsApp = objectMapper.readValue(mensajeJson, BotWhatsApp.class);
            JsonNode botLimpio = jsonCleaningService.clean(botWhatsApp.getBotWhatsAppPayload());
            
            System.out.println("El ID del bot es: "+botWhatsApp.getIdBot());
            System.out.println("El bot limpio es: "+botLimpio);
            
            
            LOGGER.log(Level.INFO, "Se recibió diagrama para botId=" + botWhatsApp.getIdBot());
            LOGGER.log(Level.FINE, "Diagrama limpio: " + botLimpio.toString());

            // 3) Guardar diagrama en memoria
            diagramaService.saveDiagram(botLimpio);

            LOGGER.log(Level.INFO, "Diagrama almacenado exitosamente en memoria.");

            /**
             * 
             * Aca debería ir el código para mapear el bot que llega en cleaned
             * 
             */

        } catch (Exception e) {
            System.out.println("El error es: " + e);
        }
    }
}
