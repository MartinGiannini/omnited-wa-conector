package coop.bancocredicoop.omnited.service.db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisPublisherService {

    private final String CHANNEL_NAME = "actualizaciones";
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisPublisherService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publicarActualizacion(String tipo, Long idSector, Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Agregamos el idSector al JSON
            ObjectNode messageNode = objectMapper.createObjectNode();
            messageNode.put("idSector", idSector);
            messageNode.putPOJO("data", data);

            String mensaje = tipo + ";" + objectMapper.writeValueAsString(messageNode);
            redisTemplate.convertAndSend(CHANNEL_NAME, mensaje);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}