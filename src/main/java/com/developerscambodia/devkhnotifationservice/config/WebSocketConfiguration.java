package com.developerscambodia.devkhnotifationservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        // path for notification
        // registry.addEndpoint("/notification").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/notification")
                .setAllowedOrigins("http://localhost:3000" , "http://localhost:3000/");

    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");

    }
}
