package coop.bancocredicoop.omnited.entity;

import com.fasterxml.jackson.databind.JsonNode;

public class BotWhatsApp {
    
    private Integer idBot;
    private JsonNode botWhatsAppPayload;

    public BotWhatsApp() {
    }

    public BotWhatsApp(Integer idBot, JsonNode botWhatsAppPayload) {
        this.idBot = idBot;
        this.botWhatsAppPayload = botWhatsAppPayload;
    }

    public Integer getIdBot() {
        return idBot;
    }

    public void setIdBot(Integer idBot) {
        this.idBot = idBot;
    }

    public JsonNode getBotWhatsAppPayload() {
        return botWhatsAppPayload;
    }

    public void setBotWhatsAppPayload(JsonNode botWhatsAppPayload) {
        this.botWhatsAppPayload = botWhatsAppPayload;
    }
}
