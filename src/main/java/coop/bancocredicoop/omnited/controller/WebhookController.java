package coop.bancocredicoop.omnited.controller;

import com.fasterxml.jackson.databind.JsonNode;
import coop.bancocredicoop.omnited.service.bot.DiagramaMapper;
import coop.bancocredicoop.omnited.service.bot.DiagramaService;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import coop.bancocredicoop.omnited.service.whatsapp.WhatsappService;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.logging.Logger;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private static final Logger LOGGER = Logger.getLogger(WebhookController.class.getName());
    private static final String VERIFY_TOKEN = "2ufoyrqCCPbMXxMoOpVRnZZenQq_6SrEaKCf7VRWtrRKVQV3i";
    private final WhatsappService whatsappService;
    private final DiagramaService diagramaService;
    private final DiagramaMapper diagramaMapper;

    // Inyección por constructor (recomendado)
    @Autowired
    public WebhookController(
            WhatsappService whatsappService,
            DiagramaService diagramaService,
            DiagramaMapper diagramaMapper
    ) {
        this.whatsappService = whatsappService;
        this.diagramaService = diagramaService;
        this.diagramaMapper = diagramaMapper;
    }

    @GetMapping
    public ResponseEntity<String> verifyWebhook(
            @RequestParam(name = "hub.mode") String mode,
            @RequestParam(name = "hub.verify_token") String token,
            @RequestParam(name = "hub.challenge") String challenge) {

        System.out.println("hub.mode: " + mode);
        System.out.println("hub.verify_token: " + token);
        System.out.println("hub.challenge: " + challenge);

        if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(token)) {
            return ResponseEntity.ok()
                    .header("Content-Type", "text/plain")
                    .body(challenge);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .header("Content-Type", "text/plain")
                .body("Verification token mismatch");
    }

    @PostMapping
    public ResponseEntity<Void> receiveMessage(@RequestBody Map<String, Object> payload) {

        try {
            // 1) Extraer "from" y "body" del mensaje entrante
            List<Map<String, Object>> entries = (List<Map<String, Object>>) payload.get("entry");
            Map<String, Object> change = ((List<Map<String, Object>>) entries.get(0).get("changes")).get(0);
            Map<String, Object> value = (Map<String, Object>) change.get("value");
            List<Map<String, Object>> messages = (List<Map<String, Object>>) value.get("messages");

            if (messages != null && !messages.isEmpty()) {
                Map<String, Object> message = messages.get(0);
                String from = (String) message.get("from");
                Map<String, Object> textObj = (Map<String, Object>) message.get("text");
                String body = (String) textObj.get("body");

                LOGGER.log(Level.INFO, "Mensaje recibido de " + from + ": " + body);

                // 2) Obtener diagrama actual
                JsonNode diagrama = diagramaService.getDiagram();
                if (diagrama == null) {
                    LOGGER.log(Level.WARNING, "No hay diagrama cargado en memoria. Ignorando mensaje.");
                    // Quizás quieras enviar un texto de error:
                    whatsappService.sendTextMessage(from, "El bot aún no está configurado. Por favor, inténtalo más tarde.");
                    return ResponseEntity.ok().build();
                }

                // 3) Llamar al mapeador para procesar el mensaje
                diagramaMapper.procesarMensaje(diagrama, from, body);
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error en WebhookController: " + e.getMessage(), e);
        }
        /*
        try {
            List<Map<String, Object>> entries = (List<Map<String, Object>>) payload.get("entry");
            Map<String, Object> change = ((List<Map<String, Object>>) entries.get(0).get("changes")).get(0);
            Map<String, Object> value  = (Map<String, Object>) change.get("value");
            List<Map<String, Object>> messages = (List<Map<String, Object>>) value.get("messages");

            if (messages != null && !messages.isEmpty()) {
                Map<String, Object> message = messages.get(0);
                
                String from = (String) message.get("from");  // número del remitente (ej: "54911XXXXXXX")
                Map<String, Object> textObj = (Map<String, Object>) message.get("text");
                String body = (String) textObj.get("body");  // contenido enviado

                System.out.println("Mensaje recibido de " + from + ": " + body);

                // 2) Formular una respuesta
                String reply = "A la grande le pusiste \"" + body + "\".";
                
                // 3) Enviar la respuesta
                whatsappService.sendTextMessage(from, reply);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         */

        return ResponseEntity.ok().build();
    }
}
