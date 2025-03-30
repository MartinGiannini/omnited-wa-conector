package coop.bancocredicoop.omnited.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.service.rabbit.RabbitSenderService;
import org.springframework.stereotype.Component;

@Component
public class MessageToRabbit {

    private final ObjectMapper objectMapper;
    private final RabbitSenderService rabbitSenderService;

    public MessageToRabbit(
            ObjectMapper objectMapper,
            RabbitSenderService rabbitSenderService) {
        this.objectMapper = objectMapper;
        this.rabbitSenderService = rabbitSenderService;
    }

}