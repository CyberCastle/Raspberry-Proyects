package cl.cc.web.service.endpoint;

import cl.cc.web.service.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author CyberCastle
 */
@Component
public class MessageDispatcher implements ApplicationListener<BrokerAvailabilityEvent> {

    private final MessageSendingOperations<String> messagingTemplate;

    @Autowired
    public MessageDispatcher(final MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(String type, String title, String text) {
        Message message = new Message(type, title, text);
        this.messagingTemplate.convertAndSend("/realtime", message);
    }

    @Override
    public void onApplicationEvent(BrokerAvailabilityEvent event) {
        // Nothing
    }
    
}
