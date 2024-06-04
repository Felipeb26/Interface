package com.batsworks.interfaces.utils;

import com.batsworks.interfaces.HelloController;
import com.batsworks.interfaces.navigation.Change;
import com.batsworks.interfaces.navigation.Screens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public abstract class DefaultController {

    private Screens screens = Screens.MAIN;

    public void loadValues(Object... args) {
        if (args.length == 0) return;
        log.info("ARGUMENTS {}", Arrays.toString(args));
    }

    @FXML
    public void onSair(ActionEvent event) {
        Change.screen(screens, HelloController.class.getName(), event);
    }

    public void onSair(ActionEvent event, Screens screen) {
        screens = screen;
        onSair(event);
    }

}
