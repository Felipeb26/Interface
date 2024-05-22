package com.batsworks.interfaces.utils;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class TableConsume<T> {


    public List<TableColumn<T, String>> findGroup(Class c) {
        List<TableColumn<T, String>> list = new ArrayList<>();
        var fields = c.getDeclaredFields();
        for (var field : fields) {
            TableColumn<T, String> column = new TableColumn<>(field.getName().toUpperCase());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            list.add(column);
        }
        return list;
    }
}
