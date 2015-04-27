package cl.cc.image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author CyberCastle
 */
public class FaceDetector {

    private final CascadeClassifier faceClassifier;
    private Rect[] faceRegions;

    public FaceDetector() {
        String faceClassifierPath = "/usr/local/share/OpenCV/haarcascades/haarcascade_frontalface_alt.xml";
        this.faceClassifier = new CascadeClassifier(faceClassifierPath);
    }

    public void detect(Mat image) {
        MatOfRect faces = new MatOfRect();

        // More info: http://stackoverflow.com/a/20805153
        this.faceClassifier.detectMultiScale(
                image,
                faces,
                1.05,
                5,
                0, // Parameter not used.
                new Size(100, 100),
                new Size(600, 600)
        );

        this.faceRegions = faces.toArray();
    }

    public Rect[] getFaceRegions() {
        return this.faceRegions;
    }

    public Mat getROI(int facePos, Mat image) {
        return image;
    }

    public Mat createMask(int facePos, Mat image) {
        Rect faceRegion = this.faceRegions[facePos];
        Point center = new Point(faceRegion.x + faceRegion.width * 0.5, faceRegion.y + faceRegion.height * 0.5);
        int radius = (int) Math.round((faceRegion.width + faceRegion.height) * 0.35);

        // Recortando el rostro, despreciando el resto de la imagen.
        Point x1 = new Point((center.x - radius + 20), (center.y - radius));
        Point x2 = new Point((center.x + radius - 20), (center.y + radius));
        Core.rectangle(image, x1, x2, new Scalar(255, 255, 0, 255), 2, 8, 0);
        return image;
    }
}
