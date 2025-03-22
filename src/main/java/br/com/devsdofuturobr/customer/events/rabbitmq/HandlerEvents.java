package br.com.devsdofuturobr.customer.events.rabbitmq;

import br.com.devsdofuturobr.customer.events.custom.CustomerCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class HandlerEvents {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeCustomerName;

    @Value("${rabbitmq.routing.key}")
    private String routingCustomerKey;

    private final RabbitTemplate rabbitTemplate;

    @Async
    @EventListener
    public void handlerCustomerCreatedEvent(CustomerCreatedEvent event) {
        rabbitTemplate.convertAndSend(exchangeCustomerName, routingCustomerKey, event);
    }

    @Async
    @EventListener
    public void handlerCustomerCreatedEvent2(CustomerCreatedEvent event) {
        log.info("Customer Created - Email: {}", event.customer().getEmail());
    }
}
