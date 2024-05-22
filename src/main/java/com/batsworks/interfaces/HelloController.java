package com.batsworks.interfaces;

import com.batsworks.interfaces.database.CustomRepository;
import com.batsworks.interfaces.model.UsuariosEntity;
import com.batsworks.interfaces.utils.FindResource;
import com.batsworks.interfaces.utils.RegexPattern;
import com.batsworks.interfaces.utils.Validate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class HelloController implements Initializable {

    private static final Logger log = Logger.getLogger(HelloController.class.getName());
    CustomRepository<UsuariosEntity> repository;

    @FXML
    TextField senhaField;
    @FXML
    TextField emailField;
    @FXML
    Label errorEmail;
    @FXML
    Label errorSenha;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository = new CustomRepository<>(UsuariosEntity.class, UsuariosEntity::rowMapper);
        errorEmail.setVisible(false);
        errorSenha.setVisible(false);
    }

    @FXML
    protected void onEntrar(ActionEvent event) {
        var email = emailField.getText();
        var senha = senhaField.getText();

        if (validManyFields(email, senha)) return;
        var entity = repository.custom("select * from usuarios where email like '".concat(email + "%'") + " and senha=%s".formatted(senha));
        if(Boolean.TRUE.equals(entity.getAdm())){
            FindResource.changeScreen("adm-view.fxml", HelloController.class.getName(), event);
        }
    }


    @FXML
    protected void onCadastrar(ActionEvent event) {
        try {
            FindResource.changeScreen("cadastro-view.fxml", HelloController.class.getName(), event);
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }

    private boolean validManyFields(String email, String senha) {
        var emailIsOK = Validate.textField(RegexPattern.EMAIL, email, errorEmail);
        var senhaIsOK = Validate.textField(RegexPattern.SENHA, senha, errorSenha);
        return List.of(emailIsOK, senhaIsOK).contains(Boolean.FALSE);
    }

}