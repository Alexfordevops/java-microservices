package boxsystem.auth_service.rabbitmq;

import boxsystem.auth_service.payload.UserEvent;
import boxsystem.auth_service.payload.UserPayload;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange exchange;

    public UserEventPublisher(RabbitTemplate rabbitTemplate, TopicExchange userExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = userExchange;
    }

    public void publishUserCreated(Long id, String username, String name, String role, LocalDateTime creationDate) {
        UserEvent event = new UserEvent(UUID.randomUUID().toString(),
                "user.created",
                "1",
                Instant.now(),
                new UserPayload(id, username, name, role, creationDate));
        send(event);
    }

    public void publishUserUpdated(Long id, String username, String name, String role, LocalDateTime creationDate) {
        UserEvent event = new UserEvent(UUID.randomUUID().toString(),
                "user.updated",
                "1",
                Instant.now(),
                new UserPayload(id, username, name, role, creationDate));
        send(event);
    }

    public void publishUserDeleted(Long id) {
        UserEvent event = new UserEvent(UUID.randomUUID().toString(),
                "user.deleted",
                "1",
                Instant.now(),
                new UserPayload(id, null, null, null, null));
        send(event);
    }

    private void send(UserEvent event) {
        rabbitTemplate.convertAndSend(
                exchange.getName(),
                event.getType(),
                event,
                message -> {
                    message.getMessageProperties().setContentType(MediaType.APPLICATION_JSON_VALUE);
                    message.getMessageProperties().setHeader("x-event-id", event.getEventId());
                    message.getMessageProperties().setHeader("x-event-type", event.getType());
                    message.getMessageProperties().setHeader("x-event-version", event.getVersion());
                    message.getMessageProperties().setHeader("x-occurred-at", event.getOccurredAt().toString());
                    return message;
                });
    }
}
