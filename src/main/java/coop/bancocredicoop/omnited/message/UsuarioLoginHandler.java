package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.exposition.PermisosPorCategoriaDTO;
import coop.bancocredicoop.omnited.exposition.UsuarioDTO;
import coop.bancocredicoop.omnited.message.models.UsuarioDatos;
import coop.bancocredicoop.omnited.service.db.PermisoService;
import coop.bancocredicoop.omnited.service.db.UsuarioService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitSenderService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class UsuarioLoginHandler implements RabbitMessageHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UsuarioService usuarioService;
    private final PermisoService permisoService;
    private final RabbitSenderService rabbitSenderService;

    public UsuarioLoginHandler(UsuarioService usuarioService, PermisoService permisoService, RabbitSenderService rabbitSenderService) {
        this.usuarioService = usuarioService;
        this.rabbitSenderService = rabbitSenderService;
        this.permisoService = permisoService;
    }

    @Override
    public void handle(String jsonPayload, String idMensaje) throws Exception {
        
        // Mapeo el JSON entrante en la clase UsuarioDatos
        UsuarioDatos input = objectMapper.readValue(jsonPayload, UsuarioDatos.class);
                
        // Buscar el usuario en la base de datos
        UsuarioDTO usuario = usuarioService.getUsuarioByUsuario(input.getIngresoDatos().getUsuario());
        
        // Buscar los permisos en la base de datos
        PermisosPorCategoriaDTO permisos = permisoService.getPermisosPorCategoria();
        
        // Serializar los objetos Usuario a JSON
        String usuarioJson = objectMapper.writeValueAsString(usuario);
        String permisosJson = objectMapper.writeValueAsString(permisos);
        
        // Enviar los mensajes serializado a RabbitMQ
        processMessage("usuariologinDB", usuarioJson, idMensaje);
        processMessage("usuariologinpermisosDB", permisosJson, idMensaje);
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
