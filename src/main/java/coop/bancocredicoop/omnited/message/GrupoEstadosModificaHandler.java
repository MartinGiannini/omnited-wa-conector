package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.exposition.GrupoEstadoDTO;
import coop.bancocredicoop.omnited.message.models.GrupoEstadosDatos;
import coop.bancocredicoop.omnited.message.models.RetornoMensajeRealizado;
import coop.bancocredicoop.omnited.service.db.GrupoEstadoService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

/**
 *
 * @author mgiannini
 */
public class GrupoEstadosModificaHandler implements RabbitMessageHandler {

    private final GrupoEstadoService grupoEstadoService;
    private final MessageToRabbit messageToRabbit;

    public GrupoEstadosModificaHandler(
            GrupoEstadoService grupoEstadoService,
            MessageToRabbit messageToRabbit) {
        this.grupoEstadoService = grupoEstadoService;
        this.messageToRabbit = messageToRabbit;
    }

    // Crear una instancia de RetornoIngreso
    RetornoMensajeRealizado cambios = new RetornoMensajeRealizado(
            "Operación Exitosa",
            "El grupo fue actualizado correctamente.",
            "ok"
    );

    @Override
    public void handle(String idMensaje, String mensajeJson) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            GrupoEstadosDatos grupoEstadosDatos = objectMapper.readValue(mensajeJson, GrupoEstadosDatos.class);
            String retornoCambios;
            String type;

            if (grupoEstadosDatos.getIngresoDatos().getGrupoDatos().getIdGrupoEstado() == 1100011) {
                GrupoEstadoDTO grupoGuardado = grupoEstadoService.guardarGrupoEstado(grupoEstadosDatos.getIngresoDatos().getIdSector(), grupoEstadosDatos.getIngresoDatos().getGrupoDatos());
                retornoCambios = objectMapper.writeValueAsString(grupoGuardado);
                type = "grupoEstadosAgregaDB";
            } else {
                grupoEstadoService.actualizarGrupoEstado(grupoEstadosDatos.getIngresoDatos().getGrupoDatos());
                retornoCambios = objectMapper.writeValueAsString(grupoEstadosDatos.getIngresoDatos().getGrupoDatos());
                type = "grupoEstadosModificaDB";
            }
            
            messageToRabbit.processMessageMulticast(idMensaje, type, retornoCambios, grupoEstadosDatos.getIngresoDatos().getIdSector());

            String retorno = objectMapper.writeValueAsString(cambios);
            messageToRabbit.processMessage(idMensaje, "cambiosRealizadosDB", retorno);

        } catch (JsonProcessingException e) {
            System.err.println("Error actualizando el grupo de habilidades: " + e.getMessage());
            throw e;
        }
    }
}
