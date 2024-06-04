package com.batsworks.interfaces;

import com.batsworks.interfaces.navigation.Change;
import com.batsworks.interfaces.navigation.Screens;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AboutView extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(CadastroView.class.getResource("about-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("About!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void sair(ActionEvent event) {
        Change.screen(Screens.MAIN, HelloController.class.getName(), event);
    }

}
