package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.config.MessageOut;
import coop.bancocredicoop.omnited.message.models.GrupoEstadosDatos;
import coop.bancocredicoop.omnited.message.models.RetornoCambiosRealizados;
import coop.bancocredicoop.omnited.service.db.GrupoEstadoService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitSenderService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

/**
 *
 * @author mgiannini
 */
public class ModificaGrupoEstadosHandler implements RabbitMessageHandler {
    private final GrupoEstadoService grupoEstadoService;
    private final RabbitSenderService rabbitSenderService;

    public ModificaGrupoEstadosHandler(GrupoEstadoService grupoEstadoService, RabbitSenderService rabbitSenderService) {
        this.grupoEstadoService = grupoEstadoService;
        this.rabbitSenderService = rabbitSenderService;
    }
    
    @Override
    public void handle(String jsonPayload, String idMensaje) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            GrupoEstadosDatos input = objectMapper.readValue(jsonPayload, GrupoEstadosDatos.class);
            
            if (input.getIngresoDatos().getSeleccionados().getIdGrupoEstado() == 1100011) {
                grupoEstadoService.guardarGrupoEstado(input.getIngresoDatos().getIdSector(), input.getIngresoDatos().getSeleccionados());
            } else {
                grupoEstadoService.actualizarGrupoEstado(input.getIngresoDatos().getSeleccionados());
            }

            // Crear una instancia de RetornoIngreso
            RetornoCambiosRealizados cambios = new RetornoCambiosRealizados(
                    "Operación Exitosa",
                    "El grupo fue actualizado correctamente.",
                    "ok"
            );

            String retorno = objectMapper.writeValueAsString(cambios);
            processMessage("cambiosRealizadosDB", retorno, idMensaje);
            
        } catch (JsonProcessingException e) {
            System.err.println("Error actualizando el grupo de habilidades: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Método para enviar el mensaje al RabbitMQ.
     *
     * @param usuarioJson Usuario serializado como JSON
     */
    private void processMessage(String type, String usuarioJson, String idMensaje) {

        try {
            MessageOut.MensajeJSON message = MessageOut.MensajeJSON.newBuilder()
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
