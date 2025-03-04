package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.entity.GrupoHabilidad;
import coop.bancocredicoop.omnited.exposition.GrupoHabilidadDTO;
import coop.bancocredicoop.omnited.message.models.GrupoHabilidadesDatos;
import coop.bancocredicoop.omnited.message.models.RetornoMensajeRealizado;
import coop.bancocredicoop.omnited.service.db.GrupoHabilidadService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class GrupoHabilidadesModificaHandler implements RabbitMessageHandler {

    private final GrupoHabilidadService grupoHabilidadService;
    private final MessageToRabbit messageToRabbit;

    public GrupoHabilidadesModificaHandler(
            GrupoHabilidadService grupoHabilidadService,
            MessageToRabbit messageToRabbit
    ) {
        this.grupoHabilidadService = grupoHabilidadService;
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
            GrupoHabilidadesDatos grupoHabilidadesDatos = objectMapper.readValue(mensajeJson, GrupoHabilidadesDatos.class);
            String retornoCambios;
            String type;

            if (grupoHabilidadesDatos.getIngresoDatos().getGrupoDatos().getIdGrupoHabilidad() == 1100011) {
                GrupoHabilidadDTO grupoGuardado = grupoHabilidadService.guardarGrupoHabilidad(grupoHabilidadesDatos.getIngresoDatos().getIdSector(), grupoHabilidadesDatos.getIngresoDatos().getGrupoDatos());
                retornoCambios = objectMapper.writeValueAsString(grupoGuardado);
                type = "grupoHabilidadesAgregaDB";
            } else {
                grupoHabilidadService.actualizarGrupoHabilidad(grupoHabilidadesDatos.getIngresoDatos().getGrupoDatos());
                retornoCambios = objectMapper.writeValueAsString(grupoHabilidadesDatos.getIngresoDatos().getGrupoDatos());
                type = "grupoHabilidadesModificaDB";
            }

            messageToRabbit.processMessageMulticast(idMensaje, type, retornoCambios, grupoHabilidadesDatos.getIngresoDatos().getIdSector());

            String retorno = objectMapper.writeValueAsString(cambios);
            messageToRabbit.processMessage(idMensaje, "cambiosRealizadosDB", retorno);

        } catch (JsonProcessingException e) {
            System.err.println("Error actualizando el grupo de habilidades: " + e.getMessage());
            throw e;
        }
    }
}
