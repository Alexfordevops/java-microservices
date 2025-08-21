package boxsystem.product_service.rabbitMQ.consumers;

import boxsystem.product_service.model.UserReadModel;
import boxsystem.product_service.rabbitMQ.events.UserEvent;
import boxsystem.product_service.repository.UserReadModelRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    @Autowired
    private UserReadModelRepository repo;

    @RabbitListener(queues = "${rabbitmq.user.queue}")
    public void handleUserEvent(UserEvent event) {
        System.out.println("📥 Evento recebido: " + event.getType());

        switch (event.getType()) {
            case "user.created" -> repo.save(
                    new UserReadModel(
                            event.getData().getId(),
                            event.getData().getUsername(),
                            event.getData().getName(),
                            event.getData().getRole(),
                            event.getData().getCreationDate()
                    )
            );

            case "user.updated" -> repo.save(
                    new UserReadModel(
                            event.getData().getId(),
                            event.getData().getUsername(),
                            event.getData().getName(),
                            event.getData().getRole(),
                            event.getData().getCreationDate()
                    )
            );

            case "user.deleted" -> repo.deleteById(event.getData().getId());
        }
    }
}
