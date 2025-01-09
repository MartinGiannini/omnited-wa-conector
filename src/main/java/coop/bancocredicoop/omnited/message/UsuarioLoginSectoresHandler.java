package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.exposition.SectorDTO;
import coop.bancocredicoop.omnited.message.models.LoginSectores;
import coop.bancocredicoop.omnited.service.db.SectorService;
import coop.bancocredicoop.omnited.service.rabbit.MessageHandler;
import coop.bancocredicoop.omnited.service.rabbit.RabbitSenderService;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioLoginSectoresHandler implements MessageHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SectorService sectorService;
    private final RabbitSenderService rabbitSenderService;

    public UsuarioLoginSectoresHandler(SectorService sectorService, RabbitSenderService rabbitSenderService) {
        this.sectorService = sectorService;
        this.rabbitSenderService = rabbitSenderService;
    }

    @Override
    public void handle(String jsonPayload, String idMensaje) throws Exception {
        
        // Mapear el nodo al objeto UsuarioDatos
        LoginSectores loginSectores = objectMapper.readValue(jsonPayload, LoginSectores.class);

        Set<Long> sectores = loginSectores.getSectoresDatos().getSectores().stream()
                    .map(LoginSectores.SectorInput::getId)
                    .collect(Collectors.toSet());
        
        // Buscar el usuario en la base de datos
        Set<SectorDTO> retorno = sectorService.getSectoresByUsuarioSectores(sectores);
        
        // Serializar el objeto Usuario a JSON
        String sectorJson = objectMapper.writeValueAsString(retorno);

        // Enviar el mensaje serializado a RabbitMQ
        processMessage("usuariologinsectoresDB", sectorJson, idMensaje);
    }

    /**
     * Método para enviar el mensaje al RabbitMQ.
     *
     * @param usuarioJson Usuario serializado como JSON
     */
    private void processMessage(String type, String usuarioJson, String idMensaje) {

        System.out.println("*******************************************");
        System.out.println("Se envia el mensaje");
        System.out.println("*******************************************");
        
        
        try {
            MensajeJSON message = MensajeJSON.newBuilder()
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
