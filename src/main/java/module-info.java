module com.batsworks.interfaces {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.slf4j;
    requires static lombok;
    requires java.desktop;
    requires java.management;
    requires org.controlsfx.controls;


    opens com.batsworks.interfaces.model to javafx.base;
    exports com.batsworks.interfaces.model;

    opens com.batsworks.interfaces to javafx.fxml;
    exports com.batsworks.interfaces;

    opens com.batsworks.interfaces.utils to javafx.fxml;
    exports com.batsworks.interfaces.utils;

    opens com.batsworks.interfaces.controller to javafx.fxml;
    exports com.batsworks.interfaces.controller ;
}