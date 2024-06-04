package com.batsworks.interfaces;

import com.batsworks.interfaces.navigation.Screens;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Screens.MAIN.fxml()));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BatsWorks Market");
        stage.setScene(scene);
        stage.show();
        log.info("APPLICATION STARTED");
    }

    public static void main(String[] args) {
        launch();
    }
}