package coop.bancocredicoop.omnited.service.rabbit;

public interface RabbitMessageHandler {
    void handle(String jsonPayload, String id) throws Exception;
}