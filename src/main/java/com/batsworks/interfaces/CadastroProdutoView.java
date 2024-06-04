package com.batsworks.interfaces;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastroProdutoView extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(CadastroView.class.getResource("cadastro-produto-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("About!");
        stage.setScene(scene);
        stage.show();
    }
}
