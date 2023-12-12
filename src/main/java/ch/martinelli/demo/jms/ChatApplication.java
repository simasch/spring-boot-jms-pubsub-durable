package ch.martinelli.demo.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

@SpringBootApplication
@EnableJms
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args).close();
    }

    @Value("client-${chat.user}")
    private String clientId;
    @Value("${spring.jms.pub-sub-domain}")
    private boolean pubSubDomain;

    @Bean
    public JmsListenerContainerFactory<?> artemisConnectionFactory(CachingConnectionFactory connectionFactory,
                                                  DefaultJmsListenerContainerFactoryConfigurer configurer) {
        // When using durable subscription the ClientId must be set for reconnect
        // Note that client IDs need to be unique among all active Connections of the underlying JMS provider
        connectionFactory.setClientId(clientId);

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(pubSubDomain);
        factory.setSubscriptionDurable(true);

        return factory;
    }
}
