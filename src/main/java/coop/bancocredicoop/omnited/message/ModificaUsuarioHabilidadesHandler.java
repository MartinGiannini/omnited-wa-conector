package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.message.models.RetornoMensajeRealizado;
import coop.bancocredicoop.omnited.message.models.UsuarioDatos;
import coop.bancocredicoop.omnited.service.db.UsuarioService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class ModificaUsuarioHabilidadesHandler implements RabbitMessageHandler {

    private final UsuarioService usuarioService;
    private final MessageToRabbit messageToRabbit;

    public ModificaUsuarioHabilidadesHandler(
            UsuarioService usuarioService,
            MessageToRabbit messageToRabbit) {
        this.usuarioService = usuarioService;
        this.messageToRabbit = messageToRabbit;
    }

    RetornoMensajeRealizado mensajeRealizado = new RetornoMensajeRealizado(
            "Operación Exitosa",
            "Las habilidades fueron actualizadas correctamente.",
            "ok"
    );

    @Override
    public void handle(String idMensaje, String mensajeJson) throws Exception {
        try {
            // Deserializar el JSON recibido
            ObjectMapper objectMapper = new ObjectMapper();
            UsuarioDatos usuarioDatos = objectMapper.readValue(mensajeJson, UsuarioDatos.class);

            // Llamar al servicio para guardar las habilidades
            usuarioService.actualizaUsuarioHabilidad(usuarioDatos.getIngresoDatos().getUsuario());

            String retornoCambios = objectMapper.writeValueAsString(usuarioDatos.getIngresoDatos().getUsuario());
            messageToRabbit.processMessageDestino(idMensaje, "usuarioHabilidadesDB", retornoCambios, usuarioDatos.getIngresoDatos().getIdSector());

            String mensaje = objectMapper.writeValueAsString(mensajeRealizado);
            messageToRabbit.processMessage(idMensaje, "cambiosRealizadosDB", mensaje);

        } catch (JsonProcessingException e) {
            System.err.println("Error procesando habilidades de usuario: " + e.getMessage());
            throw e;
        }
    }
}