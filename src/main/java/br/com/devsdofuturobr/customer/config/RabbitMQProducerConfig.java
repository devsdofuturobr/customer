package br.com.devsdofuturobr.customer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQProducerConfig {

    // Environment variables for queue, exchange, and routing key
    @Value("${rabbitmq.queue.name}")
    private String queueCustomerName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeCustomerName;

    @Value("${rabbitmq.routing.key}")
    private String routingCustomerKey;

    @Bean
    public Queue queue() {
        // Creating the RabbitMQ Queue dynamically from env variables
        return new Queue(queueCustomerName, true); // Durable queue
    }

    @Bean
    public DirectExchange exchange() {
        // Creating a Direct Exchange dynamically from env variables
        return new DirectExchange(exchangeCustomerName);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        // Binding the queue with the exchange using a routing key dynamically
        return BindingBuilder.bind(queue).to(exchange).with(routingCustomerKey);
    }
}