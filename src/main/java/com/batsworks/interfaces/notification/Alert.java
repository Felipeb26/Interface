package com.batsworks.interfaces.notification;

import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;
import javafx.util.Duration;


public class Alert {

    Alert() {
        throw new IllegalStateException("Utility class");
    }

    public static void show(String title, String message, NotificationType type) {
        var notification = Notifications.create()
                .hideAfter(Duration.seconds(10))
                .title(title)
                .text(message)
                .position(Pos.BOTTOM_RIGHT);

        switch (type) {
            case INFORMATION -> notification.showInformation();
            case CONFRIM -> notification.showConfirm();
            case ERROR -> notification.showError();
            case WARN -> notification.showWarning();
            default -> notification.show();
        }

    }

}
