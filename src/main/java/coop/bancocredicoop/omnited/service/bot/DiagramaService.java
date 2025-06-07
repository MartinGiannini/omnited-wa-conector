package coop.bancocredicoop.omnited.service.bot;

import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.logging.Level;

@Service
public class DiagramaService {

    private static final Logger LOGGER = Logger.getLogger(DiagramaService.class.getName());
    private JsonNode currentDiagram;

    public void saveDiagram(JsonNode diagram) {
        this.currentDiagram = diagram;
        LOGGER.log(Level.INFO, "Diagrama guardado en memoria.");

        // Llamamos a la nueva función para “imprimir” el mapeo lógico
        loguearMapping(diagram);
    }

    public JsonNode getDiagram() {
        return this.currentDiagram;
    }

    /**
     * Recorre todos los nodos y edges para imprimir (en logs) 
     * quién conecta con quién (incluyendo sourceHandle).
     */
    private void loguearMapping(JsonNode botLimpio) {
        if (botLimpio == null 
            || !botLimpio.has("nodes") 
            || !botLimpio.has("edges")) {
            LOGGER.log(Level.WARNING, "El diagrama está vacío o no tiene nodes/edges.");
            return;
        }

        JsonNode nodes = botLimpio.get("nodes");
        JsonNode edges = botLimpio.get("edges");

        // Primero, crear un lookup rápido: para cada nodo, imprimimos su tipo
        // y luego buscamos todos los edges que tengan source == id de ese nodo.
        for (JsonNode nodo : nodes) {
            String idNodo = nodo.get("id").asText();
            String tipoNodo = nodo.get("type").asText();

            // Construimos la línea principal del nodo
            StringBuilder sb = new StringBuilder();
            sb.append("Nodo ").append(idNodo)
              .append(" (type=").append(tipoNodo).append("):\n");

            // Recorremos todos los edges para encontrar los que tienen este id como "source"
            boolean tieneSalida = false;
            for (JsonNode edge : edges) {
                String source = edge.get("source").asText();
                if (idNodo.equals(source)) {
                    tieneSalida = true;

                    // ¿Tiene sourceHandle?
                    String handle = "(sin handle)";
                    if (edge.has("sourceHandle") && !edge.get("sourceHandle").isNull()) {
                        handle = "sourceHandle=" + edge.get("sourceHandle").asText();
                    }

                    // ¿Tiene target?
                    String target = "(null)";
                    if (edge.has("target") && !edge.get("target").isNull()) {
                        target = edge.get("target").asText();
                    }

                    sb.append("    └─ ")
                      .append(handle)
                      .append(" → ")
                      .append(target)
                      .append("\n");
                }
            }

            if (!tieneSalida) {
                sb.append("    (sin destinos – fin de flujo o nodo drv)\n");
            }

            // Finalmente, imprimimos el bloque para este nodo
            LOGGER.log(Level.INFO, sb.toString());
        }
    }
}
