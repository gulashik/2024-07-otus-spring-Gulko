package org.gulash;

import org.gulash.service.TestRunnerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;

@ConfigurationPropertiesScan
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        var testRunnerService = context.getBean(TestRunnerService.class);

        testRunnerService.run();

    }
}