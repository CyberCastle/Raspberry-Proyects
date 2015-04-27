package cl.cc.service.logic;

import cl.cc.service.endpoint.MessageDispatcher;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author CyberCastle
 */
@Component
public class SendMessageTask {

    @Autowired
    MessageDispatcher messageDispatcher;

    public void run() {
        messageDispatcher.sendMessage("Demo", "Probando Spring Boot + Quartz + Webjars", "Hora: " + new Date());
    }
    
}
