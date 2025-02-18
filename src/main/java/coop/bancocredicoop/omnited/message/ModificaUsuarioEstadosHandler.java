package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.message.models.RetornoMensajeRealizado;
import coop.bancocredicoop.omnited.message.models.UsuarioDatos;
import coop.bancocredicoop.omnited.service.db.UsuarioService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class ModificaUsuarioEstadosHandler implements RabbitMessageHandler {

    private final UsuarioService usuarioService;
    private final MessageToRabbit messageToRabbit;

    public ModificaUsuarioEstadosHandler(
            UsuarioService usuarioService,
            MessageToRabbit messageToRabbit
    ) {
        this.usuarioService = usuarioService;
        this.messageToRabbit = messageToRabbit;
    }

    // Crear una instancia de RetornoIngreso
    RetornoMensajeRealizado mensajeRealizado = new RetornoMensajeRealizado(
            "Operación Exitosa",
            "Los estados fueron actualizados correctamente.",
            "ok"
    );

    @Override
    public void handle(String idMensaje, String mensajeJson) throws Exception {
        try {
            // Deserializar el JSON recibido
            ObjectMapper objectMapper = new ObjectMapper();
            UsuarioDatos usuarioDatos = objectMapper.readValue(mensajeJson, UsuarioDatos.class);

            // Llamar al servicio para guardar los estados
            usuarioService.actualizarUsuarioEstado(usuarioDatos.getIngresoDatos().getUsuario());

            String retornoCambios = objectMapper.writeValueAsString(usuarioDatos.getIngresoDatos().getUsuario());
            messageToRabbit.processMessageDestino(idMensaje, "usuarioEstadosDB", retornoCambios, usuarioDatos.getIngresoDatos().getIdSector());

            String retorno = objectMapper.writeValueAsString(mensajeRealizado);
            messageToRabbit.processMessage(idMensaje, "cambiosRealizadosDB", retorno);

        } catch (JsonProcessingException e) {
            System.err.println("Error procesando estados del usuario: " + e.getMessage());
            throw e;
        }
    }
}