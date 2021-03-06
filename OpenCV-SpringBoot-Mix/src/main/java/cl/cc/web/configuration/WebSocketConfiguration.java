package cl.cc.web.configuration;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 *
 * @author CyberCastle
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/realtime.fallback").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(final ChannelRegistration registration) {
    }

    @Override
    public void configureClientOutboundChannel(final ChannelRegistration registration) {
    }

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry) {
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration wstr) {
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> list) {
        return true;
    }
}
