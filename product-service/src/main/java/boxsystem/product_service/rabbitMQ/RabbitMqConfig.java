package boxsystem.product_service.rabbitMQ;

import jdk.jfr.Enabled;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfig {

    @Value("${rabbitmq.user.exchange}")
    private String userExchangeName;

    @Value("${rabbitmq.user.queue}")
    private String userQueueName;

    @Value("${rabbitmq.user.routing-key}")
    private String routingKey;

    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange(userExchangeName);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(userQueueName, true);
    }

    @Bean
    public Binding binding(Queue userQueue, TopicExchange userExchange) {
        return BindingBuilder
                .bind(userQueue)
                .to(userExchange)
                .with(routingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter jacksonConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jacksonConverter);
        return template;
    }
}
