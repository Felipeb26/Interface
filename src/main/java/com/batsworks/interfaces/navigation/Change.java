package com.batsworks.interfaces.navigation;

import com.batsworks.interfaces.utils.DefaultController;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;

@Slf4j
public class Change {

    private Change() {
        throw new IllegalStateException("Utility class");
    }

    public static URL resource(Screens screen, String className) throws Exception {
        className = className.substring(0, className.lastIndexOf("."));
        className = className.replace(".", "/");
        File file = new File("src/main/resources/%s/%s".formatted(className, screen.fxml()));
        return file.toURI().toURL();
    }


    public static void screen(Screens screen, String className, Event event, Object... args) throws Exception {
        FXMLLoader loader = new FXMLLoader(Change.resource(screen, className));
        Parent root = loader.load();

        var controllerLoader = loader.getController();
        if (controllerLoader instanceof DefaultController controller) {
            controller.loadValues(args);
            Stage register = (Stage) ((Node) event.getSource()).getScene().getWindow();
            register.setScene(new Scene(root));
            register.show();
        } else throw new RuntimeException("Controller are not the same");
    }

    public static void screen(Screens screen, String className, Event event) {
        try {
            Parent root = FXMLLoader.load(Change.resource(screen, className));
            Stage register = (Stage) ((Node) event.getSource()).getScene().getWindow();
            register.setScene(new Scene(root));
            register.show();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
