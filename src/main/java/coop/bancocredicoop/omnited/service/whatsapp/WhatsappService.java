package coop.bancocredicoop.omnited.service.whatsapp;

import coop.bancocredicoop.omnited.service.redis.RedisService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WhatsappService {

    @Value("${whatsapp.phone-number-id}")
    private String phoneNumberId;

    @Value("${whatsapp.access-token}")
    private String accessToken;

    private final RestTemplate restTemplate = new RestTemplate();
    private final RedisService redisService;

    public WhatsappService(
            RedisService redisService
    ) {
        this.redisService = redisService;
    }

    public void sendTextMessage(String to, String messageText) {
        String url = "https://graph.facebook.com/v22.0/" + phoneNumberId + "/messages";

        // 1) Construir el payload como Map
        Map<String, Object> text = new HashMap<>();
        text.put("body", messageText);

        Map<String, Object> payload = new HashMap<>();
        payload.put("messaging_product", "whatsapp");
        payload.put("to", to);
        payload.put("type", "text");
        payload.put("text", text);

        // 2) Cabeceras HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        // 3) Enviar la petición; Spring convierte el Map a JSON automáticamente
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("WhatsApp API response: " + response.getBody());
    }

}
