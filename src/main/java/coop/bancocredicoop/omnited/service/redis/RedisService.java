package coop.bancocredicoop.omnited.service.redis;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;
    private static final Logger LOGGER = Logger.getLogger(RedisService.class.getName());

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Guarda un valor simple (string) en Redis sin TTL.
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        LOGGER.info("Redis SET key=" + key + " value=" + value);
    }

    /**
     * Guarda un valor con TTL (en segundos).
     */
    public void set(String key, String value, long ttlSeconds) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(ttlSeconds));
        LOGGER.info("Redis SET key=" + key + " value=" + value + " TTL=" + ttlSeconds + "s");
    }

    /**
     * Obtiene un valor. Retorna null si no existe.
     */
    public String get(String key) {
        String val = redisTemplate.opsForValue().get(key);
        LOGGER.fine("Redis GET key=" + key + " -> " + val);
        return val;
    }

    /**
     * Obtiene un valor y si no existe retorna defaultValue.
     */
    public String getOrDefault(String key, String defaultValue) {
        String val = redisTemplate.opsForValue().get(key);
        if (val == null) {
            return defaultValue;
        }
        return val;
    }

    /**
     * Borra la clave indicada.
     */
    public void delete(String key) {
        redisTemplate.delete(key);
        LOGGER.info("Redis DEL key=" + key);
    }

    /**
     * Retorna un conjunto de llaves que coincidan con el patrón. Ej:
     * keys("var:1234:*")
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * Retorna todas las variables guardadas para un usuario "from": Busca
     * llaves con prefijo "var:<from>:" y crea un Map<nombreVar, valor>.
     */
    public Map<String, String> getAllVars(String from) {
        String pattern = "var:" + from + ":*";
        Set<String> allKeys = redisTemplate.keys(pattern);
        Map<String, String> result = new HashMap<>();
        if (allKeys != null) {
            for (String key : allKeys) {
                String val = redisTemplate.opsForValue().get(key);
                // key tiene formato "var:<from>:<nombreVar>"
                String nombreVar = key.substring(("var:" + from + ":").length());
                result.put(nombreVar, val);
            }
        }
        LOGGER.info("Redis getAllVars for from=" + from + " -> " + result);
        return result;
    }
}
