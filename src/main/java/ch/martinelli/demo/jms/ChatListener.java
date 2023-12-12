package ch.martinelli.demo.jms;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ChatListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatListener.class);

    @JmsListener(destination = "${chat.topic}",
            selector = "user <> '${chat.user}'",
            containerFactory = "artemisConnectionFactory",
            subscription = "chat")
    public void onMessage(Message message) throws JMSException {
        if (message instanceof TextMessage textMessage) {
            String sender = message.getStringProperty("user");
            LOGGER.info("Message from {}: {}", sender, textMessage.getText());
        }
    }
}
