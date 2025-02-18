package coop.bancocredicoop.omnited.service.rabbit;

import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.message.MessageToRabbit;
import coop.bancocredicoop.omnited.message.ModificaGrupoEstadosHandler;
import coop.bancocredicoop.omnited.message.ModificaGrupoHabilidadesHandler;
import coop.bancocredicoop.omnited.message.ModificaColaHandler;
import coop.bancocredicoop.omnited.message.ModificaUsuarioEstadosHandler;
import coop.bancocredicoop.omnited.message.ModificaUsuarioHabilidadesHandler;
import coop.bancocredicoop.omnited.message.UsuarioLoginGruposHandler;
import coop.bancocredicoop.omnited.message.UsuarioLoginSectoresHandler;
import coop.bancocredicoop.omnited.message.UsuarioLoginHandler;
import coop.bancocredicoop.omnited.message.ModificaUsuarioPermisosOperacionHandler;
import coop.bancocredicoop.omnited.message.ModificaUsuarioPermisosSupervisionHandler;
import coop.bancocredicoop.omnited.service.db.ColaService;
import coop.bancocredicoop.omnited.service.db.EstrategiaService;
import coop.bancocredicoop.omnited.service.db.GrupoEstadoService;
import coop.bancocredicoop.omnited.service.db.GrupoHabilidadService;
import coop.bancocredicoop.omnited.service.db.PermisoService;
import coop.bancocredicoop.omnited.service.db.SectorService;
import coop.bancocredicoop.omnited.service.db.UsuarioService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitListenerService {

    private final Map<String, RabbitMessageHandler> handlers = new HashMap<>();

    /**
     * Servicios que se registran en la carga de la aplicación.
     *
     * @param usuarioService
     * @param sectorService
     * @param grupoEstadoService
     * @param grupoHabilidadService
     * @param permisoService
     * @param estrategiaService
     * @param colaService
     * @param messageToRabbit
     */
    public RabbitListenerService(
            UsuarioService usuarioService,
            SectorService sectorService,
            GrupoEstadoService grupoEstadoService,
            GrupoHabilidadService grupoHabilidadService,
            PermisoService permisoService,
            EstrategiaService estrategiaService,
            ColaService colaService,
            MessageToRabbit messageToRabbit
    ) {
        // Registrar handlers para cada tipo de mensaje
        handlers.put("usuariologinWS", new UsuarioLoginHandler(usuarioService, permisoService, messageToRabbit));
        handlers.put("usuariologinsectoresWS", new UsuarioLoginSectoresHandler(sectorService, messageToRabbit));
        handlers.put("usuariologingruposWS", new UsuarioLoginGruposHandler(grupoEstadoService, grupoHabilidadService, estrategiaService, messageToRabbit));
        handlers.put("usuarioHabilidadesWS", new ModificaUsuarioHabilidadesHandler(usuarioService, messageToRabbit));
        handlers.put("usuarioEstadosWS", new ModificaUsuarioEstadosHandler(usuarioService, messageToRabbit));
        handlers.put("permisosOperacionWS", new ModificaUsuarioPermisosOperacionHandler(usuarioService, messageToRabbit));
        handlers.put("permisosSupervisionWS", new ModificaUsuarioPermisosSupervisionHandler(usuarioService, messageToRabbit));
        handlers.put("modificaColaWS", new ModificaColaHandler(colaService, messageToRabbit));
        handlers.put("grupoHabilidadesWS", new ModificaGrupoHabilidadesHandler(grupoHabilidadService, messageToRabbit));
        handlers.put("grupoEstadosWS", new ModificaGrupoEstadosHandler(grupoEstadoService, messageToRabbit));
    }

    /**
     * Colas registradas en Rabbit. Esta aplicación solo escuchará mensajes
     * provinientes de esas colas.
     *
     * @param message
     */
    @RabbitListener(queues = {
        "#{@environment.getProperty('spring.rabbitmq.colaWS')}",
        "#{@environment.getProperty('spring.rabbitmq.colaCR')}"
    })

    /**
     * Método que buscará manejar los mensajes de acuerdo al TYPE.
     *
     */
    public void receiveMessage(MensajeJSON message) {

        try {
            String idMensaje = message.getIdMensaje();
            String mensajeType = message.getMensajeType();
            String mensajeJson = message.getMensajeJson();
            
            // Identificar y procesar el mensaje según su tipo
            RabbitMessageHandler handler = handlers.get(mensajeType);

            if (handler != null) {
                handler.handle(idMensaje, mensajeJson);
            } else {
                System.err.println("No handler found for type: " + mensajeType);
            }
        } catch (Exception e) {
            System.err.println("Error handling message: " + e.getMessage());
        }
    }
}
