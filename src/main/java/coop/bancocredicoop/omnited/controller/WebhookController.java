package coop.bancocredicoop.omnited.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private static final String VERIFY_TOKEN = "2ufoyrqCCPbMXxMoOpVRnZZenQq_6SrEaKCf7VRWtrRKVQV3i";

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
        // Lógica para procesar el mensaje entrante
        System.out.println("Mensaje entrante: " + payload);
        // Aquí podrías extraer la información del mensaje y responder o guardar a DB
        return ResponseEntity.ok().build();
    }
}
