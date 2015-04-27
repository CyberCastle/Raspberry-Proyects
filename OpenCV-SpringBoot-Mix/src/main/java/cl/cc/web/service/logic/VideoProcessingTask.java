package cl.cc.web.service.logic;

import cl.cc.image.FaceDetector;
import cl.cc.image.ImageProcessor;
import cl.cc.web.service.endpoint.MessageDispatcher;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author CyberCastle
 */
@Component
public class VideoProcessingTask implements InitializingBean, DisposableBean {

    @Autowired
    private MessageDispatcher messageDispatcher;

    private Boolean looped;

    // Parámetros para procesar imagenes
    private FaceDetector face;
    private Mat cameraImage;
    private VideoCapture camera;

    public void startTask() {

        if (this.camera.isOpened()) {
            System.out.println("Beging Video Capture...");
            while (this.looped) {
                this.camera.read(this.cameraImage);
                if (!this.cameraImage.empty()) {

                    // Image filters
                    ImageProcessor processor = new ImageProcessor(this.cameraImage);
                    Mat i = processor.resize(0.5).toGray().equalize().result();

                    this.face.detect(i);
                    Integer facesDetected = face.getFaceRegions().length;
                    System.out.println("Faces detected: " + facesDetected);

                    this.messageDispatcher.sendMessage("Demo",
                            "Probando Spring Boot + Quartz + Webjars",
                            "Faces detected: " + facesDetected);

                    i.release();
                } else {
                    System.out.println("Ending Video Capture...");
                    break;
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing Camera ...");
        this.face = new FaceDetector();
        this.cameraImage = new Mat(); // Mat from video stream 
        this.camera = new VideoCapture(0);

        System.out.println("Waiting for 2 seconds...");
        Thread.sleep(2000);

        this.looped = true;
    }

    public void stopTask() {
        this.looped = false;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Closing Camera ...");
        this.looped = false;
        this.cameraImage.release();
        this.camera.release();
    }

}
