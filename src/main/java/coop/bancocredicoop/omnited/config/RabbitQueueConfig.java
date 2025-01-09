package coop.bancocredicoop.omnited.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQueueConfig {

    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${spring.rabbitmq.colaSalida}")
    private String colaSalida;

    @Value("${spring.rabbitmq.colaWS}")
    private String colaEntranteWS;

    @Value("${spring.rabbitmq.colaCR}")
    private String colaEntranteCR;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue colaSalida() {
        return new Queue(colaSalida, true); // Cola de salida (duradera)
    }

    @Bean
    public Queue colaEntranteWS() {
        return new Queue(colaEntranteWS, true); // Cola de entrada DB (duradera)
    }

    @Bean
    public Queue colaEntranteCR() {
        return new Queue(colaEntranteCR, true); // Cola de entrada CR (duradera)
    }

    /**
     * Binding de la cola de salida al exchange con la clave de enrutamiento.
     *
     * @param colaSalida
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingColaSalida(Queue colaSalida, DirectExchange exchange) {
        return BindingBuilder.bind(colaSalida).to(exchange).with(routingKey + ".db");
    }

    /**
     * Binding de la cola de entrada DB al exchange con una clave de
     * enrutamiento específica.
     *
     * @param colaEntranteWS
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingColaEntranteWS(Queue colaEntranteWS, DirectExchange exchange) {
        return BindingBuilder.bind(colaEntranteWS).to(exchange).with(routingKey + ".ws");
    }

    /**
     * Binding de la cola de entrada CR al exchange con una clave de
     * enrutamiento específica.
     *
     * @param colaEntranteCR
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingColaEntranteCR(Queue colaEntranteCR, DirectExchange exchange) {
        return BindingBuilder.bind(colaEntranteCR).to(exchange).with(routingKey + ".cr");
    }
}
