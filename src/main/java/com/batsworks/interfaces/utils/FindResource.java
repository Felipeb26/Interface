package com.batsworks.interfaces.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class FindResource {

    private FindResource() {
        throw new IllegalStateException("Utility class");
    }

    public static URL resource(String name, String className) throws Exception {
        className = className.substring(0, className.lastIndexOf("."));
        className = className.replace(".", "/");
        File file = new File("src/main/resources/%s/%s".formatted(className, name));
        return file.toURI().toURL();
    }

    public static void changeScreen(String resource, String className, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(FindResource.resource(resource, className));
            Stage register = (Stage) ((Node) event.getSource()).getScene().getWindow();
            register.setScene(new Scene(root));
            register.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
