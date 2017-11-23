package com.stepan.dashboard;

import com.stepan.dashboard.configuration.DashboardConfiguration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Lazy
@SpringBootApplication
@Import(DashboardConfiguration.class)
public class DashboardServiceRunner extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent root;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(DashboardServiceRunner.class);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard.fxml"));
        loader.setControllerFactory(springContext::getBean);
        root = loader.load();
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // initialise the UI
        stage.setTitle("Twitter Dashboard");
        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/dashboard.css").toString());

        DashboardController dashboardController = new DashboardController();


        stage.setTitle("My app");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(DashboardServiceRunner.class, args);
    }
}
