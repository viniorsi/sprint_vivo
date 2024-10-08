package com.vivo.territory.Service.Email;

import com.vivo.territory.Domain.Email.EmailMessage;
import com.vivo.territory.Infra.RabbiMq.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishEmail(EmailMessage emailMessage) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "email.send", emailMessage);
    }

}
