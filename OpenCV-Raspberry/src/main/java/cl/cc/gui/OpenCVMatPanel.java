package cl.cc.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.JPanel;
import org.opencv.core.Mat;

/**
 *
 * @author CyberCastle
 */
public class OpenCVMatPanel extends JPanel {

    private static final long serialVersionUID = 773239711080280884L;

    private BufferedImage image;
    private String stringToDraw;

    public void drawMat(Mat matrix) {
        this.image = this.matToBufferedImage(matrix);
    }

    /**
     * Converts/writes a Mat into a BufferedImage.
     *
     * @param matrix Mat of type CV_8UC3 or CV_8UC1
     * @return BufferedImage of type TYPE_3BYTE_BGR or TYPE_BYTE_GRAY
     * @see
     * http://cell0907.blogspot.com.es/2013/12/from-mat-to-bufferedimage.html
     */
    private BufferedImage matToBufferedImage(Mat matrix) {
        int width = matrix.width(), height = matrix.height(), channels = matrix.channels();
        byte[] sourcePixels = new byte[width * height * channels];
        matrix.get(0, 0, sourcePixels);

        // Image type selection
        int imageType = BufferedImage.TYPE_3BYTE_BGR;
        if (channels == 1) {
            imageType = BufferedImage.TYPE_BYTE_GRAY;
        }

        // Create new image and get reference to backing data 
        BufferedImage _image = new BufferedImage(width, height, imageType);
        final byte[] targetPixels = ((DataBufferByte) _image.getRaster().getDataBuffer()).getData();
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
        return _image;
    }

    public void drawString(String stringToDraw) {
        this.stringToDraw = stringToDraw;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.image != null) {
            g.drawImage(this.image, 10, 10, this.image.getWidth(), this.image.getHeight(), null);
            return;
        }

       
        
        if (this.stringToDraw != null) {
            g.drawString(this.stringToDraw, 10, 10);
        }
    }
}
