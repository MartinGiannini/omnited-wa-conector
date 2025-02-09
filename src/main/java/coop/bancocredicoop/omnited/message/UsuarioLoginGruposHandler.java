package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.config.MessageOut;
import coop.bancocredicoop.omnited.exposition.EstrategiaDTO;
import coop.bancocredicoop.omnited.exposition.GrupoDatosDTO;
import coop.bancocredicoop.omnited.exposition.GrupoEstadoDTO;
import coop.bancocredicoop.omnited.exposition.GrupoHabilidadDTO;
import coop.bancocredicoop.omnited.message.models.SectorDatos;
import coop.bancocredicoop.omnited.service.db.EstrategiaService;
import coop.bancocredicoop.omnited.service.db.GrupoEstadoService;
import coop.bancocredicoop.omnited.service.db.GrupoHabilidadService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitSenderService;
import java.util.Set;
import java.util.stream.Collectors;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class UsuarioLoginGruposHandler implements RabbitMessageHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RabbitSenderService rabbitSenderService;
    private final GrupoEstadoService grupoEstadoService;
    private final GrupoHabilidadService grupoHabilidadService;
    private final EstrategiaService estrategiaService;

    public UsuarioLoginGruposHandler(
            GrupoEstadoService grupoEstadoService,
            GrupoHabilidadService grupoHabilidadService,
            EstrategiaService estrategiaService,
            RabbitSenderService rabbitSenderService
    ) {
        this.rabbitSenderService = rabbitSenderService;
        this.grupoEstadoService = grupoEstadoService;
        this.grupoHabilidadService = grupoHabilidadService;
        this.estrategiaService = estrategiaService;
    }

    @Override
    public void handle(String jsonPayload, String idMensaje) throws Exception {
        // Mapear el nodo al objeto UsuarioDatos
        SectorDatos loginSectores = objectMapper.readValue(jsonPayload, SectorDatos.class);
                
        Set<Long> sectores = loginSectores.getSectoresDatos().getSectores().stream()
                .map(SectorDatos.SectorInput::getIdSector)
                .collect(Collectors.toSet());
        
        Set<GrupoEstadoDTO> grupoEstados = grupoEstadoService.getGrupoEstadosBySectores(sectores);
        Set<GrupoHabilidadDTO> grupoHabilidades = grupoHabilidadService.getGrupoHabilidadesBySectores(sectores);
        Set<EstrategiaDTO> estrategias = estrategiaService.getAllEstrategias();

        // Crear el objeto contenedor
        GrupoDatosDTO grupoDatos = new GrupoDatosDTO(grupoEstados, grupoHabilidades);

        // Serializar los objetos a JSON
        String grupoDatosJson = objectMapper.writeValueAsString(grupoDatos);
        String estrategiasJson = objectMapper.writeValueAsString(estrategias);

        // Enviar el mensaje serializado a RabbitMQ
        processMessage("usuariologingruposDB", grupoDatosJson, idMensaje);
        processMessage("estrategiasDB", estrategiasJson, idMensaje);
    }

    /**
     * Método para enviar el mensaje al RabbitMQ.
     *
     * @param usuarioJson Usuario serializado como JSON
     */
    private void processMessage(String type, String usuarioJson, String idMensaje) {

        try {
            MessageOut.MensajeJSON message = MessageOut.MensajeJSON.newBuilder()
                    .setId(idMensaje) // Reemplaza con un generador de ID único
                    .setType(type)
                    .setJsonPayload(usuarioJson)
                    //.setRecipients(usuarioNombre)
                    .build();

            // Usar el servicio RabbitSenderService para enviar el mensaje
            rabbitSenderService.sendMessage(message);

        } catch (Exception e) {
            System.out.println("Error en el processMessage" + e);
        }
    }
}
