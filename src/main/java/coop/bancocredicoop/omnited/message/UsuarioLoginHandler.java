package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.exposition.UsuarioDTO;
import coop.bancocredicoop.omnited.message.models.UsuarioDatos;
import coop.bancocredicoop.omnited.service.db.UsuarioService;
import coop.bancocredicoop.omnited.service.rabbit.MessageHandler;
import coop.bancocredicoop.omnited.service.rabbit.RabbitSenderService;

public class UsuarioLoginHandler implements MessageHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UsuarioService usuarioService;
    private final RabbitSenderService rabbitSenderService;

    public UsuarioLoginHandler(UsuarioService usuarioService, RabbitSenderService rabbitSenderService) {
        this.usuarioService = usuarioService;
        this.rabbitSenderService = rabbitSenderService;
    }

    @Override
    public void handle(String jsonPayload, String idMensaje) throws Exception {
        
        System.out.println("");
        System.out.println("*******************************************");
        System.out.println("El id mensaje es: "+idMensaje);
        System.out.println("*******************************************");
        System.out.println("");
        
        // Parsear el JSON raíz
        JsonNode rootNode = objectMapper.readTree(jsonPayload);
        // Acceder al nodo "UsuarioDatos"
        JsonNode loginUsuarioNode = rootNode.get("UsuarioDatos");
        // Mapear el nodo al objeto UsuarioDatos
        UsuarioDatos loginUsuario = objectMapper.treeToValue(loginUsuarioNode, UsuarioDatos.class);
                
        // Buscar el usuario en la base de datos
        UsuarioDTO usuario = usuarioService.getUsuarioByUsuario(loginUsuario.getUsuario());
        
        // Serializar el objeto Usuario a JSON
        String usuarioJson = objectMapper.writeValueAsString(usuario);
        
        // Enviar el mensaje serializado a RabbitMQ
        processMessage("usuariologinDB", usuarioJson, idMensaje);
    }

    /**
     * Método para enviar el mensaje al RabbitMQ.
     *
     * @param usuarioJson Usuario serializado como JSON
     */
    private void processMessage(String type, String usuarioJson, String idMensaje) {
        
        try {
            MensajeJSON message = MensajeJSON.newBuilder()
                    .setId(idMensaje)
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
