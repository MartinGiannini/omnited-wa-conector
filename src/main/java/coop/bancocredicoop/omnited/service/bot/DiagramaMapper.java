package coop.bancocredicoop.omnited.service.bot;

import java.net.URI;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import coop.bancocredicoop.omnited.service.redis.RedisService;
import coop.bancocredicoop.omnited.service.whatsapp.WhatsappService;

/**
 * Aquí se implementa la lógica para: - Encontrar nodo inicial - Iterar según
 * tipo de nodo (out, in, inVar, svc, cond, drv) - Guardar posición en Redis -
 * Validar entradas, invocar servicios, condicionales, etc.
 */
@Service
public class DiagramaMapper {

    private static final Logger LOGGER = Logger.getLogger(DiagramaMapper.class.getName());

    private final RedisService redisService;
    private final WhatsappService whatsappService;

    public DiagramaMapper(
            RedisService redisService,
            WhatsappService whatsappService) {
        this.redisService = redisService;
        this.whatsappService = whatsappService;
    }

    /**
     * Punto de entrada para procesar un mensaje de un usuario.
     *
     * @param botLimpio JsonNode con {"nodes":[…], "edges":[…]}
     * @param from Número de teléfono del usuario (p. ej. "5491169329545")
     * @param textoUsuario Texto que acaba de escribir el usuario
     */
    public void procesarMensaje(JsonNode botLimpio, String from, String textoUsuario) {
        // 1) Verificar si existe un estado previo en Redis -> nodoActualId
        String posicionKey = "posicion:" + from;
        String nodoActualId = redisService.get(posicionKey);

        if (nodoActualId == null) {
            // No existe estado previo, buscar nodo inicial
            nodoActualId = encontrarNodoInicial(botLimpio);
            LOGGER.log(Level.INFO, "[" + from + "] Nodo inicial: " + nodoActualId);
        }

        // 2) Obtener JsonNode del nodo actual
        JsonNode nodoActual = encontrarNodoPorId(botLimpio, nodoActualId);
        if (nodoActual == null) {
            LOGGER.log(Level.SEVERE, "[" + from + "] No se encontró el nodo con id=" + nodoActualId);
            return;
        }

        String tipo = nodoActual.get("type").asText();
        switch (tipo) {
            case "out":
                handleNodoOut(botLimpio, nodoActual, from);
                break;
            case "in":
                handleNodoIn(botLimpio, nodoActual, from, textoUsuario);
                break;
            case "inVar":
                handleNodoInVar(botLimpio, nodoActual, from, textoUsuario);
                break;
            case "svc":
                handleNodoSvc(botLimpio, nodoActual, from);
                break;
            case "cond":
                handleNodoCond(botLimpio, nodoActual, from);
                break;
            case "drv":
                handleNodoDrv(nodoActual, from);
                break;
            default:
                LOGGER.log(Level.WARNING, "[" + from + "] Nodo de tipo desconocido: " + tipo);
        }
    }

    // --------------------
    // 2.1 Nodo OUT
    // --------------------
    private void handleNodoOut(JsonNode botLimpio, JsonNode nodoOut, String from) {
        LOGGER.log(Level.INFO, "");
        LOGGER.log(Level.INFO, "[" + from + "] Nodo OUT");
        LOGGER.log(Level.INFO, "");

        String texto = nodoOut.get("data").get("text").asText();
        LOGGER.log(Level.INFO, "[" + from + "] Nodo OUT: enviando texto -> " + texto);
        // 1) Enviar mensaje
        whatsappService.sendTextMessage(from, texto);

        // 2) Determinar siguiente nodo y guardarlo en Redis
        String siguienteId = obtenerTarget(botLimpio, nodoOut);
        if (siguienteId != null) {
            LOGGER.log(Level.INFO, "[" + from + "] Nodo OUT: siguiente nodo -> " + siguienteId);
            redisService.set("posicion:" + from, siguienteId, 60 * 30); // TTL 30 min

            // Llamamos recursivamente para “caer” en el siguiente nodo inmediatamente:
            procesarMensaje(botLimpio, from, "");
        } else {
            LOGGER.log(Level.WARNING, "[" + from + "] Nodo OUT: no se encontró target.");
        }
    }

    // --------------------
    // 2.2 Nodo IN (Opciones o Texto)
    // --------------------
    private void handleNodoIn(JsonNode botLimpio, JsonNode nodoIn, String from, String textoUsuario) {

        LOGGER.log(Level.INFO, "");
        LOGGER.log(Level.INFO, "[" + from + "] Nodo IN");
        LOGGER.log(Level.INFO, "");

        JsonNode data = nodoIn.get("data");
        String modo = data.get("mode").asText();
        String estadoInKey = "inEsperando:" + from;

        if ("Opciones".equalsIgnoreCase(modo)) {
            // 2.2.1 Modo “Opciones”
            boolean esperando = Boolean.parseBoolean(redisService.getOrDefault(estadoInKey, "false"));
            if (!esperando) {
                // Mostrar menú de opciones
                StringBuilder menu = new StringBuilder("Selecciona una opción:\n");
                for (JsonNode opt : data.get("optionsList")) {
                    int id = opt.get("id").asInt();
                    String txt = opt.get("text").asText();
                    menu.append(id).append(". ").append(txt).append("\n");
                }
                LOGGER.log(Level.INFO, "[" + from + "] Nodo IN (Opciones): enviando menú ->\n" + menu);
                whatsappService.sendTextMessage(from, menu.toString());
                // Marco en Redis que estoy esperando respuesta
                redisService.set(estadoInKey, "true", 60 * 30);
                return; // No avanzo posición hasta recibir respuesta
            } else {
                // Ya envié el menú, ahora proceso textoUsuario
                int opcionElegida;
                try {
                    opcionElegida = Integer.parseInt(textoUsuario.trim());
                } catch (NumberFormatException e) {
                    opcionElegida = -1;
                }

                // Verificar si la opción existe
                boolean valida = false;
                for (JsonNode opt : data.get("optionsList")) {
                    if (opt.get("id").asInt() == opcionElegida) {
                        valida = true;
                        break;
                    }
                }

                // Contador de intentos en Redis
                String intentosKey = "intentos:" + from;
                int intentosUsados = Integer.parseInt(redisService.getOrDefault(intentosKey, "0"));
                intentosUsados++;
                redisService.set(intentosKey, String.valueOf(intentosUsados), 60 * 30);

                if (!valida && intentosUsados < data.get("numeroIntentos").asInt()) {
                    // Reintentos posibles
                    LOGGER.log(Level.WARNING, "[" + from + "] IN (Opciones): opción inválida. Reintentos = " + intentosUsados);
                    whatsappService.sendTextMessage(from, "Opción inválida. Intenta de nuevo.");
                    return;
                } else if (!valida) {
                    // Se agotaron intentos
                    LOGGER.log(Level.SEVERE, "[" + from + "] IN (Opciones): intentos agotados.");
                    String nodoError = buscarEdgePorHandle(botLimpio, nodoIn, "error");
                    if (nodoError != null) {
                        redisService.set("posicion:" + from, nodoError, 60 * 30);
                        // Continúo procesamiento recursivamente
                        procesarMensaje(botLimpio, from, "");
                    } else {
                        whatsappService.sendTextMessage(from, "Lo siento, no entendí tu opción. Intenta más tarde.");
                    }
                    // Limpio flags
                    redisService.delete(estadoInKey);
                    redisService.delete(intentosKey);
                    return;
                } else {
                    // Opción válida
                    // 1) Obtener textoVar (por ej. el nombre de variable asociado)
                    String textoVar = null;
                    for (JsonNode opt : data.get("optionsList")) {
                        if (opt.get("id").asInt() == opcionElegida) {
                            textoVar = opt.get("text").asText();
                            break;
                        }
                    }

                    // 2) Guardar variable en Redis: por ejemplo: var:<from>:<textoVar> = textoUsuario
                    if (textoVar != null) {
                        redisService.set("var:" + from + ":" + textoVar, textoUsuario.trim(), 60 * 30);
                        LOGGER.log(Level.INFO, "[" + from + "] IN (Opciones): variable guardada -> "
                                + textoVar + " = '" + textoUsuario.trim() + "'");
                    }

                    // 3) Buscar el nodo INVAR cuyo data.selectedVar == textoVar
                    String nodoInVarId = buscarNodoInVar(botLimpio, textoVar);
                    LOGGER.log(Level.INFO, "[" + from + "] IN (Opciones): avanzando a nodo inVar " + nodoInVarId);

                    // 4) Limpio flags
                    redisService.delete(estadoInKey);
                    redisService.delete(intentosKey);

                    // 5) Guardo nueva posición y llamo recursivamente
                    if (nodoInVarId != null) {
                        redisService.set("posicion:" + from, nodoInVarId, 60 * 30);
                        procesarMensaje(botLimpio, from, "");
                    } else {
                        LOGGER.log(Level.SEVERE, "[" + from + "] IN (Opciones): no se encontró nodo inVar para variable=" + textoVar);
                    }
                    return;
                }
            }

        } else {
            // 2.2.2 Modo “Texto”
            LOGGER.log(Level.INFO, "[" + from + "] Nodo IN (Texto): recibiendo texto -> '" + textoUsuario + "'");
            // Guardar texto libre (aquí lo guardamos en "textoLibre", pero podrías mapearlo a una variable en particular)
            redisService.set("var:" + from + ":textoLibre", textoUsuario.trim(), 60 * 30);

            // Avanzar al siguiente nodo (handle="ok")
            String siguienteId = buscarEdgePorHandle(botLimpio, nodoIn, "ok");
            if (siguienteId != null) {
                LOGGER.log(Level.INFO, "[" + from + "] IN (Texto): siguiente nodo -> " + siguienteId);
                redisService.set("posicion:" + from, siguienteId, 60 * 30);
                procesarMensaje(botLimpio, from, "");
            } else {
                LOGGER.log(Level.WARNING, "[" + from + "] IN (Texto): no se encontró target handle=ok");
            }
            return;
        }
    }

    // --------------------
    // 2.3 Nodo INVAR
    // --------------------
    private void handleNodoInVar(JsonNode botLimpio, JsonNode nodoInVar, String from, String textoUsuario) {
        LOGGER.log(Level.INFO, "");
        LOGGER.log(Level.INFO, "[" + from + "] Nodo INVAR");
        LOGGER.log(Level.INFO, "");

        String selectedVar = nodoInVar.get("data").get("selectedVar").asText();
        System.out.println("El selectedVar es: " + selectedVar);

        String textoPrompt = nodoInVar.get("data").get("text").asText();
        System.out.println("El textoPrompt es: " + textoPrompt);

        int maxIntentos = nodoInVar.get("data").get("numeroIntentos").asInt();
        System.out.println("El maxIntentos es: " + maxIntentos);

        String estadoVarKey = "inVarEsperando:" + from + ":" + selectedVar;
        System.out.println("El estadoVarKey es: " + estadoVarKey);

        boolean esperando = Boolean.parseBoolean(redisService.getOrDefault(estadoVarKey, "false"));
        System.out.println("El esperando es: " + esperando);

        String intentosVarKey = "intentosVar:" + from + ":" + selectedVar;
        System.out.println("El intentosVarKey es: " + intentosVarKey);

        int intentosUsados = Integer.parseInt(redisService.getOrDefault(intentosVarKey, "0"));
        System.out.println("El intentosUsados es: " + intentosUsados);

        if (!esperando) {
            // 1) Primera vez: enviar prompt
            LOGGER.log(Level.INFO, "[" + from + "] Nodo INVAR: enviando prompt -> " + textoPrompt);
            whatsappService.sendTextMessage(from, textoPrompt);
            redisService.set(estadoVarKey, "true", 60 * 30);
            return;
        }

        intentosUsados++;
        redisService.set(intentosVarKey, String.valueOf(intentosUsados), 60 * 30);

        // 3) Entrada válida
        redisService.set("var:" + from + ":" + selectedVar, textoUsuario.trim(), 60 * 30);
        LOGGER.log(Level.INFO, "[" + from + "] INVAR: variable " + selectedVar + " guardada = '" + textoUsuario.trim() + "'");

        // 4) Avanzar al siguiente nodo
        String siguienteId = obtenerTarget(botLimpio, nodoInVar);
        LOGGER.log(Level.INFO, "[" + from + "] INVAR: siguiente nodo -> " + siguienteId);

        // 5) Limpiar flags
        redisService.delete(estadoVarKey);
        redisService.delete(intentosVarKey);

        // 6) Guardar posición y continuar recursivamente
        if (siguienteId != null) {
            redisService.set("posicion:" + from, siguienteId, 60 * 30);
            procesarMensaje(botLimpio, from, "");
        } else {
            LOGGER.log(Level.WARNING, "[" + from + "] INVAR: no se encontró target para nodoInVar id=" + nodoInVar.get("id").asText());
        }
        return;

    }

    // --------------------
    // 2.4 Nodo SVC
    // --------------------
    private void handleNodoSvc(JsonNode botLimpio, JsonNode nodoSvc, String from) {
        LOGGER.log(Level.INFO, "");
        LOGGER.log(Level.INFO, "[" + from + "] Nodo Servicio");
        LOGGER.log(Level.INFO, "");

        String url = nodoSvc.get("data").get("url").asText();
        String method = nodoSvc.get("data").get("method").asText();
        LOGGER.log(Level.INFO, "[" + from + "] Nodo SVC: invocando " + method + " a " + url);

        boolean resultado = invocarServicio(url, method, from);
        LOGGER.log(Level.INFO, "[" + from + "] Nodo SVC: resultado servicio -> " + resultado);

        // Guardar resultado en Redis para el siguiente nodo COND
        redisService.set("bool:" + from, String.valueOf(resultado), 60 * 30);

        // Avanzar al siguiente nodo (se asume que el siguiente es un nodo COND)
        String condNodeId = obtenerTarget(botLimpio, nodoSvc);
        if (condNodeId != null) {
            redisService.set("posicion:" + from, condNodeId, 60 * 30);
            procesarMensaje(botLimpio, from, "");
        } else {
            LOGGER.log(Level.WARNING, "[" + from + "] Nodo SVC: no se encontró target para nodoSvc id=" + nodoSvc.get("id").asText());
        }
    }

    // --------------------
    // 2.5 Nodo COND
    // --------------------
    private void handleNodoCond(JsonNode botLimpio, JsonNode nodoCond, String from) {
        LOGGER.log(Level.INFO, "");
        LOGGER.log(Level.INFO, "[" + from + "] Nodo Condicion");
        LOGGER.log(Level.INFO, "");

        boolean valor = Boolean.parseBoolean(redisService.getOrDefault("bool:" + from, "false"));
        LOGGER.log(Level.INFO, "[" + from + "] Nodo COND: valor booleano recibido -> " + valor);

        String siguienteId = buscarEdgePorHandle(botLimpio, nodoCond, valor ? "true" : "false");
        if (siguienteId != null) {
            LOGGER.log(Level.INFO, "[" + from + "] Nodo COND: avanzando a -> " + siguienteId);
            redisService.set("posicion:" + from, siguienteId, 60 * 30);
            procesarMensaje(botLimpio, from, "");
        } else {
            LOGGER.log(Level.WARNING, "[" + from + "] Nodo COND: no se encontró target para handle=" + (valor ? "true" : "false"));
        }
    }

    // --------------------
    // 2.6 Nodo DRV
    // --------------------
    private void handleNodoDrv(JsonNode nodoDrv, String from) {
        LOGGER.log(Level.INFO, "");
        LOGGER.log(Level.INFO, "[" + from + "] Nodo Derivacion");
        LOGGER.log(Level.INFO, "");

        int primary = nodoDrv.get("data").get("primary").asInt();
        int secondary = nodoDrv.get("data").get("secondary").asInt();
        LOGGER.log(Level.INFO, "[" + from + "] Nodo DRV: derivar a agente -> primary=" + primary + ", secondary=" + secondary);

        // Lógica para notificar al sistema de agentes
        derivarAAgente(from, primary, secondary);
    }

    // --------------------
    // Métodos auxiliares
    // --------------------
    /**
     * Encuentra el nodo inicial: aquel cuyo "id" no aparece como target en
     * ningún edge.
     */
    private String encontrarNodoInicial(JsonNode botLimpio) {
        Set<String> todosNodos = new HashSet<>();
        Set<String> targets = new HashSet<>();

        for (JsonNode nodo : botLimpio.get("nodes")) {
            todosNodos.add(nodo.get("id").asText());
        }
        for (JsonNode edge : botLimpio.get("edges")) {
            JsonNode target = edge.get("target");
            if (target != null && !target.isNull()) {
                targets.add(target.asText());
            }
        }
        // Nodo inicial = todosNodos \ targets
        for (String idNodo : todosNodos) {
            if (!targets.contains(idNodo)) {
                return idNodo;
            }
        }
        return null;
    }

    /**
     * Retorna el JsonNode (nodo) cuyo "id" coincida con el provisto.
     */
    private JsonNode encontrarNodoPorId(JsonNode botLimpio, String id) {
        for (JsonNode nodo : botLimpio.get("nodes")) {
            if (nodo.get("id").asText().equals(id)) {
                return nodo;
            }
        }
        return null;
    }

    /**
     * Para un nodo dado, retorna el campo "target" del primer edge que tenga
     * source == id del nodo y (opcionalmente) no importe sourceHandle.
     */
    private String obtenerTarget(JsonNode botLimpio, JsonNode nodo) {
        String idNodo = nodo.get("id").asText();
        for (JsonNode edge : botLimpio.get("edges")) {
            if (edge.get("source").asText().equals(idNodo)) {
                // Ignoramos sourceHandle y devolvemos el target
                JsonNode t = edge.get("target");
                if (t != null && !t.isNull()) {
                    return t.asText();
                }
            }
        }
        return null;
    }

    /**
     * Para un nodo dado, busca el edge con sourceHandle == handle, y retorna
     * target.
     */
    private String buscarEdgePorHandle(JsonNode botLimpio, JsonNode nodo, String handle) {
        String idNodo = nodo.get("id").asText();
        for (JsonNode edge : botLimpio.get("edges")) {
            JsonNode sHandle = edge.get("sourceHandle");
            if (edge.get("source").asText().equals(idNodo)
                    && sHandle != null
                    && !sHandle.isNull()
                    && sHandle.asText().equalsIgnoreCase(handle)) {
                return edge.get("target").asText();
            }
        }
        return null;
    }

    /**
     * Busca en todos los nodos al tipo "inVar" cuyo data.selectedVar ==
     * selectedVar, y retorna ese id.
     */
    private String buscarNodoInVar(JsonNode botLimpio, String selectedVar) {
        for (JsonNode nodo : botLimpio.get("nodes")) {
            if ("inVar".equalsIgnoreCase(nodo.get("type").asText())) {
                String var = nodo.get("data").get("selectedVar").asText();
                if (selectedVar.equalsIgnoreCase(var)) {
                    return nodo.get("id").asText();
                }
            }
        }
        return null;
    }

    /**
     * Valida el formato según dtype. Si dtype == "Integer", chequea solo
     * dígitos. Si dtype == "String", acepta cualquier texto no vacío (puedes
     * ajustar reglas).
     */
    private boolean validarFormato(String texto, String dtype) {
        if ("Integer".equalsIgnoreCase(dtype)) {
            return texto.matches("\\d+");
        } else if ("String".equalsIgnoreCase(dtype)) {
            return texto != null && !texto.trim().isEmpty();
        }
        // Otros dtypes (p.ej. fechas) podrías añadir aquí
        return false;
    }

    /**
     * Invoca un servicio externo REST (GET o POST) devolviendo booleano. Aquí
     * usamos RestTemplate para simplicidad.
     */
    private boolean invocarServicio(String url, String method, String from) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Boolean resp = null;
            if ("GET".equalsIgnoreCase(method)) {
                resp = restTemplate.getForObject(new URI(url), Boolean.class);
            } else if ("POST".equalsIgnoreCase(method)) {
                // Si necesitas enviar variables, podrías recuperar:
                // Map<String,String> vars = redisService.getAllVars(from);
                // y armar un body con ellas. Aquí simplificamos enviando sin body.
                resp = restTemplate.postForObject(new URI(url), null, Boolean.class);
            } else {
                LOGGER.log(Level.WARNING, "Método HTTP no soportado: " + method + ". Se asume false.");
                return false;
            }
            return resp != null && resp;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error invocando servicio " + method + " " + url + ": " + e.getMessage(), e);
            return false;
        }
    }

    /**
     * Lógica para derivar al usuario a agente humano. Por ahora solo
     * registramos en log, pero aquí llamarías a tu sistema de agentes.
     */
    private void derivarAAgente(String from, int primary, int secondary) {
        // Ejemplo muy simple:
        LOGGER.log(Level.INFO, "[" + from + "] Derivando a agente human@" + primary
                + " (fallback: " + secondary + ")");
        // TODO: notificar a tu sistema de agentes humanos (colocar en Redis/DB/cola, etc.)
    }
}
