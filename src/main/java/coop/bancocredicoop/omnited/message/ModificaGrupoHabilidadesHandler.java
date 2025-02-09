package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.config.MessageOut;
import coop.bancocredicoop.omnited.message.models.GrupoHabilidadesDatos;
import coop.bancocredicoop.omnited.message.models.RetornoCambiosRealizados;
import coop.bancocredicoop.omnited.service.db.GrupoHabilidadService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitSenderService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class ModificaGrupoHabilidadesHandler implements RabbitMessageHandler {

    private final GrupoHabilidadService grupoHabilidadService;
    private final RabbitSenderService rabbitSenderService;

    public ModificaGrupoHabilidadesHandler(GrupoHabilidadService grupoHabilidadService, RabbitSenderService rabbitSenderService) {
        this.grupoHabilidadService = grupoHabilidadService;
        this.rabbitSenderService = rabbitSenderService;
    }

    @Override
    public void handle(String jsonPayload, String idMensaje) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            GrupoHabilidadesDatos input = objectMapper.readValue(jsonPayload, GrupoHabilidadesDatos.class);
            
            if (input.getIngresoDatos().getSeleccionados().getIdGrupoHabilidad() == 1100011) {
                grupoHabilidadService.guardarGrupoHabilidad(input.getIngresoDatos().getIdSector(), input.getIngresoDatos().getSeleccionados());
            } else {
                grupoHabilidadService.actualizarGrupoHabilidad(input.getIngresoDatos().getSeleccionados());
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
