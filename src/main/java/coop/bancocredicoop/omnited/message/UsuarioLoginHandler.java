package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.exposition.PermisosPorCategoriaDTO;
import coop.bancocredicoop.omnited.exposition.UsuarioDTO;
import coop.bancocredicoop.omnited.message.models.UsuarioDatos;
import coop.bancocredicoop.omnited.service.db.PermisoService;
import coop.bancocredicoop.omnited.service.db.UsuarioService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class UsuarioLoginHandler implements RabbitMessageHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UsuarioService usuarioService;
    private final PermisoService permisoService;
    private final MessageToRabbit messageToRabbit;

    public UsuarioLoginHandler(
            UsuarioService usuarioService, 
            PermisoService permisoService,
            MessageToRabbit messageToRabbit) {
        this.usuarioService = usuarioService;
        this.permisoService = permisoService;
        this.messageToRabbit = messageToRabbit;
    }

    @Override
    public void handle(String idMensaje, String mensajeJson) throws Exception {
        
        // Mapeo el JSON entrante en la clase UsuarioDatos
        UsuarioDatos input = objectMapper.readValue(mensajeJson, UsuarioDatos.class);
                
        // Buscar el usuario en la base de datos
        UsuarioDTO usuario = usuarioService.getUsuarioByUsuario(input.getIngresoDatos().getUsuario());
        
        // Buscar los permisos en la base de datos
        PermisosPorCategoriaDTO permisos = permisoService.getPermisosPorCategoria();
        
        // Serializar los objetos Usuario a JSON
        String usuarioJson = objectMapper.writeValueAsString(usuario);
        String permisosJson = objectMapper.writeValueAsString(permisos);
        
        // Enviar los mensajes serializado a RabbitMQ
        messageToRabbit.processMessage(idMensaje, "usuariologinDB", usuarioJson);
        messageToRabbit.processMessage(idMensaje, "usuariologinpermisosDB", permisosJson);
    }
}