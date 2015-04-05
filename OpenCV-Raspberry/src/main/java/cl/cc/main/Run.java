package cl.cc.main;

import cl.cc.gui.OpenCVMatPanel;
import cl.cc.image.FaceDetector;
import cl.cc.image.ImageProcessor;
import java.lang.reflect.Field;
import java.util.Scanner;
import javax.swing.JFrame;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author CyberCastle
 */
@SuppressWarnings("UseSpecificCatch")
public class Run {

    static {
        try {
            // Agregamos la librería nativa de OpenCV al Path
            addLibraryPath("/usr/local/share/OpenCV/java");
            // Y la cargamos
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static void main(String... arg) throws Exception {

        FaceDetector face = new FaceDetector();

//        // Frame setting
//        JFrame frame = new JFrame("CyberCastle Says: ");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
//
//        // Image viewer setting
//        OpenCVMatPanel cvPanel = new OpenCVMatPanel();
//        frame.setContentPane(cvPanel);
//        frame.setVisible(true);

        // Read the video stream 
        Mat cameraImage = new Mat();
        VideoCapture camera = new VideoCapture(0);
        if (camera.isOpened()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Type \\q for exit....");
            String readString = scanner.nextLine();
            while (readString != null) {
                camera.read(cameraImage);
                if (!cameraImage.empty()) {

                    // Image filters
                    ImageProcessor processor = new ImageProcessor(cameraImage);
                    Mat i = processor.resize(0.5).toGray().equalize().result();

                    // Apply the classifier to the captured image 
                    face.detect(i);
                    System.out.println("Faces detected: " + face.getFaceRegions().length);

                    // Display the image
//                    frame.setSize(i.width() + 40, i.height() + 60);
//                    if (face.getFaceRegions().length > 0) {
//                        cvPanel.drawMat(face.createMask(0, i));
//                    } else {
//                        //cvPanel.drawString("No hay Imagen....");
//                        cvPanel.drawMat(processor.result());
//                    }
//
//                    // Repaint the viewer
//                    cvPanel.repaint();
                } else {
                    // Not image, exiting...
                    break;
                }

                // Routine for exit.....
                if (readString.equals("\\q")) {
                    readString = null;
                    System.out.println("Exiting ... ");
                } else if (scanner.hasNextLine()) {
                    readString = scanner.nextLine();
                }
            }
        }
    }

    // Método para agregar runtime una librería nativa.
    // Pieza de código obtenida desde aquí: http://fahdshariff.blogspot.com/2011/08/changing-java-library-path-at-runtime.html
    private static void addLibraryPath(String libraryPath) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        String newLibraryPath = libraryPath + ":" + System.getProperty("java.library.path");
        System.setProperty("java.library.path", newLibraryPath);

        //Set sys_paths to null
        final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
        sysPathsField.setAccessible(true);
        sysPathsField.set(null, null);
    }
}
