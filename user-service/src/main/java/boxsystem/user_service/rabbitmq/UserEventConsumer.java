package boxsystem.user_service.rabbitmq;

import boxsystem.user_service.models.UserDBReadModel;
import boxsystem.user_service.rabbitmq.payload.UserEvent;
import boxsystem.user_service.repository.UserDBReadModelRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    @Autowired
    private UserDBReadModelRepository repo;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void handleUserEvent(UserEvent event) {
        System.out.println("📥 Evento recebido: " + event.getType());

        switch (event.getType()) {
            case "user.created" -> repo.save(
                    new UserDBReadModel(
                            event.getData().getId(),
                            event.getData().getUsername(),
                            event.getData().getName(),
                            event.getData().getRole(),
                            event.getData().getCreationDate()
                    )
            );

            case "user.updated" -> repo.save(
                    new UserDBReadModel(
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
