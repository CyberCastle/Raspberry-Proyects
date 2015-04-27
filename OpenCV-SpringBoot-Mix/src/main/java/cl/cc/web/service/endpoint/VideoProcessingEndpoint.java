package cl.cc.web.service.endpoint;

import cl.cc.web.service.logic.VideoProcessingTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author CyberCastle
 */
@Controller
@RequestMapping("/video")
public class VideoProcessingEndpoint {

    @Autowired
    private VideoProcessingTask videoProcessingTask;

    @RequestMapping(value = "/processing/start", method = RequestMethod.GET)
    public ResponseEntity startProcessing() {
        this.videoProcessingTask.startTask();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/processing/stop", method = RequestMethod.GET)
    public ResponseEntity stopProcessing() {
        this.videoProcessingTask.stopTask();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
