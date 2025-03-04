package coop.bancocredicoop.omnited.message;

import java.util.Set;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.exposition.EstrategiaDTO;
import coop.bancocredicoop.omnited.exposition.GrupoDatosDTO;
import coop.bancocredicoop.omnited.exposition.GrupoEstadoDTO;
import coop.bancocredicoop.omnited.exposition.GrupoHabilidadDTO;
import coop.bancocredicoop.omnited.exposition.PermisosPorCategoriaDTO;
import coop.bancocredicoop.omnited.exposition.SectorDTO;
import coop.bancocredicoop.omnited.exposition.SessionDTO;
import coop.bancocredicoop.omnited.exposition.UsuarioDTO;
import coop.bancocredicoop.omnited.message.models.UsuarioDatos;
import coop.bancocredicoop.omnited.service.db.EstrategiaService;
import coop.bancocredicoop.omnited.service.db.GrupoEstadoService;
import coop.bancocredicoop.omnited.service.db.GrupoHabilidadService;
import coop.bancocredicoop.omnited.service.db.PermisoService;
import coop.bancocredicoop.omnited.service.db.SectorService;
import coop.bancocredicoop.omnited.service.db.UsuarioService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class UsuarioLoginHandler implements RabbitMessageHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UsuarioService usuarioService;
    private final SectorService sectorService;
    private final PermisoService permisoService;
    private final GrupoEstadoService grupoEstadoService;
    private final GrupoHabilidadService grupoHabilidadService;
    private final EstrategiaService estrategiaService;
    private final MessageToRabbit messageToRabbit;

    // Variables locales
    private static Long idUsuario;
    private static Long idPerfil;
    private static Set<Long> idSectores;
    
    public UsuarioLoginHandler(
            UsuarioService usuarioService,
            SectorService sectorService,
            PermisoService permisoService,
            GrupoEstadoService grupoEstadoService,
            GrupoHabilidadService grupoHabilidadService,
            EstrategiaService estrategiaService,
            MessageToRabbit messageToRabbit
    ) {
        this.usuarioService = usuarioService;
        this.sectorService = sectorService;
        this.permisoService = permisoService;
        this.grupoEstadoService = grupoEstadoService;
        this.grupoHabilidadService = grupoHabilidadService;
        this.estrategiaService = estrategiaService;
        this.messageToRabbit = messageToRabbit;
    }

    @Override
    public void handle(String idMensaje, String mensajeJson) throws Exception {

        // Mapeo el JSON entrante en la clase UsuarioDatos
        UsuarioDatos input = objectMapper.readValue(mensajeJson, UsuarioDatos.class);

        // Buscar el usuario en la base de datos
        UsuarioDTO usuario = usuarioService.getUsuarioByUsuario(input.getIngresoDatos().getUsuario());
        idUsuario = usuario.getIdUsuario();
        idPerfil = usuario.getUsuarioPerfil().getIdPerfil();
        idSectores = usuario.getUsuarioSector().stream()
                    .map(SectorDTO::getIdSector)
                    .collect(Collectors.toSet());
        
        String usuarioJson = objectMapper.writeValueAsString(usuario);
        messageToRabbit.processMessage(idMensaje, "usuariologinDB", usuarioJson);
        
        // Si el perfil es Administrador o Supervisor se envia los datos del sistema acorde a los sectores del usuario
        if (idPerfil == 1 || idPerfil == 2) {

            Set<SectorDTO> sectores = sectorService.getSectoresByUsuarioSectores(idSectores);
            String sectorJson = objectMapper.writeValueAsString(sectores);

            PermisosPorCategoriaDTO permisos = permisoService.getPermisosPorCategoria();
            String permisosJson = objectMapper.writeValueAsString(permisos);

            Set<GrupoEstadoDTO> grupoEstados = grupoEstadoService.getGrupoEstadosBySectores(idSectores);
            Set<GrupoHabilidadDTO> grupoHabilidades = grupoHabilidadService.getGrupoHabilidadesBySectores(idSectores);

            GrupoDatosDTO grupoDatos = new GrupoDatosDTO(grupoEstados, grupoHabilidades);
            String grupoDatosJson = objectMapper.writeValueAsString(grupoDatos);

            Set<EstrategiaDTO> estrategias = estrategiaService.getAllEstrategias();
            String estrategiasJson = objectMapper.writeValueAsString(estrategias);

            // Enviar los mensajes serializados a RabbitMQ
            messageToRabbit.processMessage(idMensaje, "usuariologinsectoresDB", sectorJson);
            messageToRabbit.processMessage(idMensaje, "usuariologinpermisosDB", permisosJson);
            messageToRabbit.processMessage(idMensaje, "usuariologingruposDB", grupoDatosJson);
            messageToRabbit.processMessage(idMensaje, "usuariologinestrategiasDB", estrategiasJson);
        }
        
        // Envio datos para el guardado de la sesion WebSocket en omnited-ws-server
        SessionDTO session = new SessionDTO(idUsuario, idPerfil, idSectores);
        String sessionJson = objectMapper.writeValueAsString(session);
        
        // Enviar el mensaje serializado a RabbitMQ
        messageToRabbit.processMessage(idMensaje, "usuariosessionDB", sessionJson);
    }
}
