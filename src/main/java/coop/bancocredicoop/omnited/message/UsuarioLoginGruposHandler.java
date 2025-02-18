package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.exposition.EstrategiaDTO;
import coop.bancocredicoop.omnited.exposition.GrupoDatosDTO;
import coop.bancocredicoop.omnited.exposition.GrupoEstadoDTO;
import coop.bancocredicoop.omnited.exposition.GrupoHabilidadDTO;
import coop.bancocredicoop.omnited.exposition.SectorDTO;
import coop.bancocredicoop.omnited.message.models.SectorDatos;
import coop.bancocredicoop.omnited.service.db.EstrategiaService;
import coop.bancocredicoop.omnited.service.db.GrupoEstadoService;
import coop.bancocredicoop.omnited.service.db.GrupoHabilidadService;
import java.util.Set;
import java.util.stream.Collectors;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class UsuarioLoginGruposHandler implements RabbitMessageHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final GrupoEstadoService grupoEstadoService;
    private final GrupoHabilidadService grupoHabilidadService;
    private final EstrategiaService estrategiaService;
    private final MessageToRabbit messageToRabbit;

    public UsuarioLoginGruposHandler(
            GrupoEstadoService grupoEstadoService,
            GrupoHabilidadService grupoHabilidadService,
            EstrategiaService estrategiaService,
            MessageToRabbit messageToRabbit
    ) {
        this.grupoEstadoService = grupoEstadoService;
        this.grupoHabilidadService = grupoHabilidadService;
        this.estrategiaService = estrategiaService;
        this.messageToRabbit = messageToRabbit;
    }

    @Override
    public void handle(String idMensaje, String mensajeJson) throws Exception {
        // Mapear el nodo al objeto UsuarioDatos
        SectorDatos sectorDatos = objectMapper.readValue(mensajeJson, SectorDatos.class);

        Set<Long> idSectores = sectorDatos.getIngresoDatos().getSectores().stream()
                .map(SectorDTO::getIdSector) // Extrae solo los idSector
                .collect(Collectors.toSet());
        
        Set<GrupoEstadoDTO> grupoEstados = grupoEstadoService.getGrupoEstadosBySectores(idSectores);
        Set<GrupoHabilidadDTO> grupoHabilidades = grupoHabilidadService.getGrupoHabilidadesBySectores(idSectores);
        Set<EstrategiaDTO> estrategias = estrategiaService.getAllEstrategias();

        // Crear el objeto contenedor
        GrupoDatosDTO grupoDatos = new GrupoDatosDTO(grupoEstados, grupoHabilidades);

        // Serializar los objetos a JSON
        String grupoDatosJson = objectMapper.writeValueAsString(grupoDatos);
        String estrategiasJson = objectMapper.writeValueAsString(estrategias);

        // Enviar el mensaje serializado a RabbitMQ
        messageToRabbit.processMessage(idMensaje, "usuariologingruposDB", grupoDatosJson);
        messageToRabbit.processMessage(idMensaje, "usuariologinestrategiasDB", estrategiasJson);
    }
}