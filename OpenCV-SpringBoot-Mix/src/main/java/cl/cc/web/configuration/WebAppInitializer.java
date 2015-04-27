package cl.cc.web.configuration;

import java.lang.reflect.Field;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.opencv.core.Core;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author CyberCastle
 */
@SuppressWarnings("UseSpecificCatch")
public class WebAppInitializer implements WebApplicationInitializer {

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

    @Override
    public void onStartup(final ServletContext context) throws ServletException {

        // Read application Settings
        final AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.scan("cl.cc.web");
        context.addListener(new ContextLoaderListener(root));

        // Web enviroment settings
        final ServletRegistration.Dynamic servlet = context.addServlet("GenericServlet", new DispatcherServlet(new GenericWebApplicationContext()));
        servlet.setAsyncSupported(true);  // Important!!!, for enable asynchronous operations
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");
    }

    // Método para agregar en tiempo de ejecución una librería nativa.
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
