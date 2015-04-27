package cl.cc.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author CyberCastle
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(final ServletContext context) throws ServletException {

        // Read application Settings
        final AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.scan("cl.cc");
        context.addListener(new ContextLoaderListener(root));

        // Web enviroment settings
        final ServletRegistration.Dynamic servlet = context.addServlet("GenericServlet", new DispatcherServlet(new GenericWebApplicationContext()));
        servlet.setAsyncSupported(true);  // Important!!!, for enable asynchronous operations
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");
    }

}
