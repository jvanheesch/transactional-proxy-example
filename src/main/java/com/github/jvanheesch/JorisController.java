package com.github.jvanheesch;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@RestController
public class JorisController {
    private final JmsTemplate jmsTemplate;

    public JorisController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping
    public void send() {
        jmsTemplate.send("opdrachtenMailbox.queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage msg = session.createTextMessage("event sent from transactional proxy app");
//                msg.setStringProperty("type", type);
//                msg.setStringProperty("operation", operation);
//                msg.setStringProperty("identifier", identifier);
                return msg;
            }
        });
    }
}
