package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.exposition.SectorDTO;
import coop.bancocredicoop.omnited.message.models.SectorDatos;
import coop.bancocredicoop.omnited.service.db.SectorService;
import java.util.Set;
import java.util.stream.Collectors;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class UsuarioLoginSectoresHandler implements RabbitMessageHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SectorService sectorService;
    private final MessageToRabbit messageToRabbit;

    public UsuarioLoginSectoresHandler(
            SectorService sectorService,
            MessageToRabbit messageToRabbit
    ) {
        this.sectorService = sectorService;
        this.messageToRabbit = messageToRabbit;
    }

    @Override
    public void handle(String idMensaje, String mensajeJson) throws Exception {

        // Mapear el nodo al objeto UsuarioDatos
        SectorDatos sectorDatos = objectMapper.readValue(mensajeJson, SectorDatos.class);

        Set<Long> idSectores = sectorDatos.getIngresoDatos().getSectores().stream()
                .map(SectorDTO::getIdSector) // Extrae solo los idSector
                .collect(Collectors.toSet());

        // Buscar el usuario en la base de datos
        Set<SectorDTO> retorno = sectorService.getSectoresByUsuarioSectores(idSectores);

        // Serializar el objeto Usuario a JSON
        String sectorJson = objectMapper.writeValueAsString(retorno);

        // Enviar el mensaje serializado a RabbitMQ
        messageToRabbit.processMessage(idMensaje, "usuariologinsectoresDB", sectorJson);
    }
}