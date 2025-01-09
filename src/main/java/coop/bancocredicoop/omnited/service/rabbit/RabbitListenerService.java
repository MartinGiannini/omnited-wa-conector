package coop.bancocredicoop.omnited.service.rabbit;

import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.message.UsuarioLoginGruposHandler;
import coop.bancocredicoop.omnited.message.UsuarioLoginSectoresHandler;
import coop.bancocredicoop.omnited.message.UsuarioLoginHandler;
import coop.bancocredicoop.omnited.service.db.GrupoEstadoService;
import coop.bancocredicoop.omnited.service.db.GrupoHabilidadService;
import coop.bancocredicoop.omnited.service.db.SectorService;
import coop.bancocredicoop.omnited.service.db.UsuarioService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitListenerService {

    //private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<String, MessageHandler> handlers = new HashMap<>();
    

    /**
     * Servicios que se registran en la carga de la aplicación.
     * 
     * @param usuarioService
     * @param sectorService
     * @param grupoEstadoService
     * @param grupoHabilidadService
     * @param rabbitSenderService 
     */
    public RabbitListenerService(
            UsuarioService usuarioService,
            SectorService sectorService,
            GrupoEstadoService grupoEstadoService,
            GrupoHabilidadService grupoHabilidadService,
            RabbitSenderService rabbitSenderService
    ) {
        // Registrar handlers para cada tipo de mensaje
        handlers.put("usuariologinWS", new UsuarioLoginHandler(usuarioService, rabbitSenderService));
        handlers.put("usuariologinsectoresWS", new UsuarioLoginSectoresHandler(sectorService, rabbitSenderService));
        handlers.put("usuariologingruposWS", new UsuarioLoginGruposHandler(grupoEstadoService, grupoHabilidadService, rabbitSenderService));
        
        
        // Agrega más handlers según sea necesario
    }

    /**
     * Colas registradas en Rabbit. Esta aplicación solo escuchará mensajes provinientes de esas colas.
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
            String idMensaje = message.getId();
            String type = message.getType();
            String jsonPayload = message.getJsonPayload();
            
            System.out.println("");
            System.out.println("*****************************************************");
            System.out.println("el ID que llega es: "+idMensaje+" el type es: "+type);
            System.out.println("*****************************************************");
            System.out.println("");
            
            // Identificar y procesar el mensaje según su tipo
            MessageHandler handler = handlers.get(type);
            
            if (handler != null) {
                handler.handle(jsonPayload, idMensaje);
            } else {
                System.err.println("No handler found for type: " + type);
            }
        } catch (Exception e) {
            System.err.println("Error handling message: " + e.getMessage());
        }
    }
}