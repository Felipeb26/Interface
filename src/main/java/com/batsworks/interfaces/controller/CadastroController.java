package com.batsworks.interfaces.controller;

import com.batsworks.interfaces.HelloController;
import com.batsworks.interfaces.database.CustomRepository;
import com.batsworks.interfaces.model.UsuariosEntity;
import com.batsworks.interfaces.navigation.Change;
import com.batsworks.interfaces.navigation.Screens;
import com.batsworks.interfaces.utils.DefaultController;
import com.batsworks.interfaces.utils.RegexPattern;
import com.batsworks.interfaces.utils.Validate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Long.parseLong;

@Slf4j
public class CadastroController extends DefaultController implements Initializable {

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
    Button btnSair;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository = new CustomRepository<>(UsuariosEntity.class, UsuariosEntity::rowMapper);
        var errors = List.of(errorNome, errorEmail, errorEndereco, errorIdade, errorSenha, errorSenhaConfirm);
        errors.forEach(it -> it.setVisible(false));
    }

    @FXML
    protected void onEntrar(ActionEvent event) {
        var nome = inputNome.getText();
        var email = inputEmail.getText();
        var endereco = inputEndereco.getText();
        var idade = inputIdade.getText();
        var senha = inputSenha.getText();
        var senhaConfirm = inputSenhaConfirm.getText();

        var allOk = validateManyField(nome, email, endereco, idade, senha, senhaConfirm);
        if (!allOk) return;

        repository.save(UsuariosEntity.builder()
                .nome(nome)
                .email(email)
                .endereco(endereco)
                .adm(nome.contains("felipe"))
                .idade(parseLong(idade))
                .senha(parseLong(senha))
                .build());
        Change.screen(Screens.MAIN, HelloController.class.getName(), event);

    }


    private boolean validateManyField(String nome, String email, String endereco, String idade, String senha, String senhaConfirm) {

        var emailIsOK = Validate.textField(RegexPattern.EMAIL, email, errorEmail);
        var senhaIsOK = Validate.textField(RegexPattern.SENHA, senha, errorSenha);
        var senhaConfirmIsOK = Validate.textField(RegexPattern.SENHA, senha, senhaConfirm, errorSenha);
        var idadeIsOK = Validate.textField(RegexPattern.IDADE, idade, errorIdade);
        var nomeIsOK = Validate.textField(RegexPattern.NOME, nome, errorNome);
        var enderecoIsOK = Validate.textField(RegexPattern.NOME, endereco, errorEndereco);

        return List.of(nomeIsOK, enderecoIsOK, emailIsOK, idadeIsOK, senhaIsOK, senhaConfirmIsOK).contains(Boolean.FALSE);
    }

    @Override
    public void loadValues(Object... args) {
        if (args.length == 0) return;
        if (args[0].toString().equals("hide-sair")) {
            btnSair.setVisible(false);
        }
        super.loadValues(args);
    }
}
