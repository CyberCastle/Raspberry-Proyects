package cl.cc.image;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author CyberCastle
 */
public class ImageProcessor {

    private Mat imageProcessed;

    public ImageProcessor(Mat image) {
        this.imageProcessed = new Mat();
        image.copyTo(this.imageProcessed);
    }

    public ImageProcessor toGray() {
        Imgproc.cvtColor(imageProcessed, imageProcessed, Imgproc.COLOR_BGR2GRAY);
        return this;
    }

    public ImageProcessor equalize() {
        Imgproc.equalizeHist(imageProcessed, imageProcessed);
        return this;
    }

    public ImageProcessor resize(double factor) {
        Mat smallImage = new Mat();
        double width = this.imageProcessed.width() * factor;
        double height = this.imageProcessed.height() * factor;
        Imgproc.resize(this.imageProcessed, smallImage, new Size(width, height), 0, 0, Imgproc.INTER_LANCZOS4);
        this.imageProcessed = smallImage;
        return this;
    }

    public Mat result() {
        return this.imageProcessed;
    }
}
