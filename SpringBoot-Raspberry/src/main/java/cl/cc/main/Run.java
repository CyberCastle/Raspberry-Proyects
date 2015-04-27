package cl.cc.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author CyberCastle
 *
 * Set up Spring boot and launch the application
 */
@ComponentScan
@EnableAutoConfiguration
public class Run {

    public static void main(String... args) {
        SpringApplication.run(Run.class, args);
    }
}
