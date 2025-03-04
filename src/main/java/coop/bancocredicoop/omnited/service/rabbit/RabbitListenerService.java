package coop.bancocredicoop.omnited.service.rabbit;

import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.message.MessageToRabbit;
import coop.bancocredicoop.omnited.message.GrupoEstadosModificaHandler;
import coop.bancocredicoop.omnited.message.GrupoHabilidadesModificaHandler;
import coop.bancocredicoop.omnited.message.ColaModificaHandler;
import coop.bancocredicoop.omnited.message.UsuarioEstadosModificaHandler;
import coop.bancocredicoop.omnited.message.UsuarioHabilidadesModificaHandler;
import coop.bancocredicoop.omnited.message.UsuarioLoginHandler;
import coop.bancocredicoop.omnited.message.UsuarioModificaHandler;
import coop.bancocredicoop.omnited.message.UsuarioPermisosOperacionModificaHandler;
import coop.bancocredicoop.omnited.message.UsuarioPermisosSupervisionModificaHandler;
import coop.bancocredicoop.omnited.service.db.ColaHabilidadService;
import coop.bancocredicoop.omnited.service.db.ColaService;
import coop.bancocredicoop.omnited.service.db.EstrategiaService;
import coop.bancocredicoop.omnited.service.db.GrupoEstadoService;
import coop.bancocredicoop.omnited.service.db.GrupoHabilidadService;
import coop.bancocredicoop.omnited.service.db.PermisoService;
import coop.bancocredicoop.omnited.service.db.SectorService;
import coop.bancocredicoop.omnited.service.db.UsuarioSectorService;
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
     * @param usuarioSectorService
     * @param sectorService
     * @param grupoEstadoService
     * @param grupoHabilidadService
     * @param permisoService
     * @param estrategiaService
     * @param colaService
     * @param colaHabilidadService
     * @param messageToRabbit
     */
    public RabbitListenerService(
            UsuarioService usuarioService,
            UsuarioSectorService usuarioSectorService,
            SectorService sectorService,
            GrupoEstadoService grupoEstadoService,
            GrupoHabilidadService grupoHabilidadService,
            PermisoService permisoService,
            EstrategiaService estrategiaService,
            ColaService colaService,
            ColaHabilidadService colaHabilidadService,
            MessageToRabbit messageToRabbit
    ) {
        // Registrar handlers para cada tipo de mensaje
        handlers.put("usuariologinWS", new UsuarioLoginHandler(usuarioService, sectorService, permisoService, grupoEstadoService, grupoHabilidadService, estrategiaService, messageToRabbit));
        handlers.put("usuarioadminWS", new UsuarioModificaHandler(usuarioService, usuarioSectorService, sectorService, messageToRabbit));
        handlers.put("colaadminWS", new ColaModificaHandler(colaService, colaHabilidadService, messageToRabbit));
        handlers.put("usuarioHabilidadesWS", new UsuarioHabilidadesModificaHandler(usuarioService, messageToRabbit));
        handlers.put("usuarioEstadosWS", new UsuarioEstadosModificaHandler(usuarioService, messageToRabbit));
        handlers.put("usuarioPermisosOperacionWS", new UsuarioPermisosOperacionModificaHandler(usuarioService, messageToRabbit));
        handlers.put("usuarioPermisosSupervisionWS", new UsuarioPermisosSupervisionModificaHandler(usuarioService, messageToRabbit));
        handlers.put("grupoHabilidadesWS", new GrupoHabilidadesModificaHandler(grupoHabilidadService, messageToRabbit));
        handlers.put("grupoEstadosWS", new GrupoEstadosModificaHandler(grupoEstadoService, messageToRabbit));
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
