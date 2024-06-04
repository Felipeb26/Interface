package com.batsworks.interfaces;

import com.batsworks.interfaces.database.CustomRepository;
import com.batsworks.interfaces.model.UsuariosEntity;
import com.batsworks.interfaces.navigation.Change;
import com.batsworks.interfaces.navigation.Screens;
import com.batsworks.interfaces.notification.Alert;
import com.batsworks.interfaces.notification.NotificationType;
import com.batsworks.interfaces.utils.DefaultController;
import com.batsworks.interfaces.utils.RegexPattern;
import com.batsworks.interfaces.utils.Validate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
public class HelloController extends DefaultController implements Initializable {

    CustomRepository<UsuariosEntity> repository;

    @FXML
    ImageView imageLogo;
    @FXML
    TextField senhaField;
    @FXML
    TextField emailField;
    @FXML
    Label errorEmail;
    @FXML
    Label errorSenha;
    @FXML
    Button cadastrarBtn;
    @FXML
    Button entrarBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository = new CustomRepository<>(UsuariosEntity.class, UsuariosEntity::rowMapper);
        errorEmail.setVisible(false);
        errorSenha.setVisible(false);

        emailField.setText("adm@gmail.com");
        senhaField.setText("2626");
        entrarBtn.setDefaultButton(true);
    }

    @FXML
    protected void onEntrar(ActionEvent event) throws Exception {
        var email = emailField.getText();
        var senha = senhaField.getText();

        if (validManyFields(email, senha)) return;
        var entity = repository.custom("select * from usuarios where email like '".concat(email + "%'") + " and senha=%s".formatted(senha));
        if (entity == null) {
            Alert.show("USER NOT FOUND", "please check your credentials", NotificationType.ERROR);
            return;
        }
        if (Boolean.TRUE.equals(entity.getAdm())) {
            Change.screen(Screens.ADM_VIEW, HelloController.class.getName(), event, entity);
        }
    }


    @FXML
    protected void onCadastrar(ActionEvent event) {
        try {
            Change.screen(Screens.CADASTRO_USUARIO, HelloController.class.getName(), event);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @FXML
    protected void onAbout(MouseEvent event) {
        Change.screen(Screens.ABOUT, HelloController.class.getName(), event);
    }

    private boolean validManyFields(String email, String senha) {
        var emailIsOK = Validate.textField(RegexPattern.EMAIL, email, errorEmail);
        var senhaIsOK = Validate.textField(RegexPattern.SENHA, senha, errorSenha);
        return List.of(emailIsOK, senhaIsOK).contains(Boolean.FALSE);
    }

    @Override
    public void loadValues(Object... args) {
        super.loadValues(args);
        if (args.length == 0) return;

        emailField.setText(args[0].toString());
        senhaField.setText(args[1].toString());
    }
}