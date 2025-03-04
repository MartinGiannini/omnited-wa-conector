package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.entity.Cola;
import coop.bancocredicoop.omnited.message.models.ColaDatos;
import coop.bancocredicoop.omnited.message.models.RetornoMensajeRealizado;
import coop.bancocredicoop.omnited.service.db.ColaHabilidadService;
import coop.bancocredicoop.omnited.service.db.ColaService;
import coop.bancocredicoop.omnited.service.rabbit.RabbitMessageHandler;

public class ColaModificaHandler implements RabbitMessageHandler {

    private final ColaService colaService;
    private final ColaHabilidadService colaHabilidadService;
    private final MessageToRabbit messageToRabbit;

    public ColaModificaHandler(
            ColaService colaService,
            ColaHabilidadService colaHabilidadService,
            MessageToRabbit messageToRabbit
    ) {
        this.colaService = colaService;
        this.colaHabilidadService = colaHabilidadService;
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
            String type;
            
            if (colaDatos.getIngresoDatos().getCola().getIdCola() != null) {
                colaService.colaActualizar(colaDatos.getIngresoDatos().getCola());
                type = "colaActualizarDB";
            } else {
                Cola cola = colaService.colaAgregar(colaDatos.getIngresoDatos().getIdSector(), colaDatos.getIngresoDatos().getCola());
                colaHabilidadService.asociarHabilidadesACola(cola, colaDatos.getIngresoDatos().getCola().getColaHabilidad());
                colaDatos.getIngresoDatos().getCola().setIdCola(cola.getIdCola());
                type = "colaAgregarDB";
            }
            
            colaDatos.getIngresoDatos().getCola().setIdSector(colaDatos.getIngresoDatos().getIdSector());
            String colaJson = objectMapper.writeValueAsString(colaDatos.getIngresoDatos().getCola());
            messageToRabbit.processMessageMulticast(idMensaje, type, colaJson, colaDatos.getIngresoDatos().getIdSector());
            
            String retorno = objectMapper.writeValueAsString(cambios);
            messageToRabbit.processMessage(idMensaje, "cambiosRealizadosDB", retorno);

        } catch (JsonProcessingException e) {
            System.err.println("Error procesando propiedades de cola: " + e.getMessage());
            throw e;
        }
    }
}
