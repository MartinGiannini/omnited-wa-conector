package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.message.models.ColaDatos;
import coop.bancocredicoop.omnited.message.models.RetornoMensajeRealizado;
import coop.bancocredicoop.omnited.service.db.ColaService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class ModificaColaHandler implements RabbitMessageHandler {

    private final ColaService colaService;
    private final MessageToRabbit messageToRabbit;

    public ModificaColaHandler(
            ColaService colaService,
            MessageToRabbit messageToRabbit
    ) {
        this.colaService = colaService;
        this.messageToRabbit = messageToRabbit;
    }

    // Crear una instancia de RetornoIngreso
    RetornoMensajeRealizado cambios = new RetornoMensajeRealizado(
            "Operación Exitosa",
            "La cola fue actualiza correctamente.",
            "ok"
    );

    @Override
    public void handle(String idMensaje, String mensajeJson) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            ColaDatos colaDatos = objectMapper.readValue(mensajeJson, ColaDatos.class);

            colaService.actualizarCola(colaDatos.getIngresoDatos().getCola());
            
            String retornoCambios = objectMapper.writeValueAsString(colaDatos.getIngresoDatos().getCola());
            messageToRabbit.processMessageDestino(idMensaje, "sectorColaDB", retornoCambios, colaDatos.getIngresoDatos().getIdSector());

            String retorno = objectMapper.writeValueAsString(cambios);
            messageToRabbit.processMessage(idMensaje, "cambiosRealizadosDB", retorno);

        } catch (JsonProcessingException e) {
            System.err.println("Error procesando propiedades de cola: " + e.getMessage());
            throw e;
        }
    }
}
