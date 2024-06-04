package com.batsworks.interfaces.controller;

import com.batsworks.interfaces.database.CustomRepository;
import com.batsworks.interfaces.model.UsuariosEntity;
import com.batsworks.interfaces.navigation.Screens;
import com.batsworks.interfaces.utils.DefaultController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
public class UserController extends DefaultController implements Initializable {

    CustomRepository<UsuariosEntity> repository;
    @FXML
    Label errorNome;
    @FXML
    Label errorEmail;
    @FXML
    Label errorEndereco;
    @FXML
    Label errorIdade;
    @FXML
    Label errorSenha;
    @FXML
    Label errorSenhaConfirm;
    @FXML
    TextField inputNome;
    @FXML
    TextField inputEmail;
    @FXML
    TextField inputEndereco;
    @FXML
    TextField inputIdade;
    @FXML
    TextField inputSenha;
    @FXML
    TextField inputSenhaConfirm;
    @FXML
    CheckBox isAdmin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        repository = new CustomRepository<>(UsuariosEntity.class, UsuariosEntity::rowMapper);
        var errors = List.of(errorNome, errorEmail, errorEndereco, errorIdade, errorSenha, errorSenhaConfirm);
        errors.forEach(it -> it.setVisible(false));
    }


    public void onSairUpdate(ActionEvent event) {
        super.onSair(event, Screens.ADM_VIEW);
    }


    @Override
    public void loadValues(Object... args) {
        if (args.length == 0) return;
        super.loadValues(args);

        if (args[0] instanceof UsuariosEntity usuario) {
            inputNome.setText(usuario.getNome());
            inputEmail.setText(usuario.getEmail());
            inputEndereco.setText(usuario.getEndereco());
            inputIdade.setText(String.valueOf(usuario.getIdade()));
            inputSenha.setText(String.valueOf(usuario.getSenha()));
            inputSenhaConfirm.setText(String.valueOf(usuario.getSenha()));
            isAdmin.setSelected(usuario.getAdm());
        }
    }
}
