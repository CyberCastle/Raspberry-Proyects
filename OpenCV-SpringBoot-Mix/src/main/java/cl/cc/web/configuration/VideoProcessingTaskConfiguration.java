package cl.cc.web.configuration;

import cl.cc.web.service.logic.VideoProcessingTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author CyberCastle
 */
@Configuration
public class VideoProcessingTaskConfiguration {

    @Bean
    public VideoProcessingTask videoProcessingTask() {
        return new VideoProcessingTask();
    }

}
