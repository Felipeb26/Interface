package com.batsworks.interfaces.navigation;

import lombok.Getter;

@Getter
public enum Screens {

    MAIN("hello-view.fxml"),
    ABOUT("about-view.fxml"),
    ADM_VIEW("adm-view.fxml"),
    USER_VIEW("user-view.fxml"),
    CADASTRO_PRODUTO("cadastro-produto-view.fxml"),
    CADASTRO_USUARIO("cadastro-view.fxml"),
    ATUALIZAR_USUARIO("user-update-view.fxml");

    private final String fxml;

    Screens(String fxml) {
        this.fxml = fxml;
    }

    public String fxml() {
        return this.fxml;
    }
}
