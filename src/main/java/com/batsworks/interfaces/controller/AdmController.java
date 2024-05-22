package com.batsworks.interfaces.controller;

import com.batsworks.interfaces.CadastroView;
import com.batsworks.interfaces.HelloController;
import com.batsworks.interfaces.database.CustomRepository;
import com.batsworks.interfaces.model.ProdutosEntity;
import com.batsworks.interfaces.model.UsuariosEntity;
import com.batsworks.interfaces.utils.Currency;
import com.batsworks.interfaces.utils.FindResource;
import com.batsworks.interfaces.utils.TableConsume;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AdmController implements Initializable {

    CustomRepository<UsuariosEntity> repository;
    CustomRepository<ProdutosEntity> produtosRepository;
    @FXML
    TableView<UsuariosEntity> tablePessoas;
    @FXML
    TableView<ProdutosEntity> tableProdutos;
    @FXML
    TextField pessoaFilter;
    @FXML
    TextField produtoFilter;
    ObservableList<UsuariosEntity> entityObservableList = FXCollections.observableArrayList();
    ObservableList<ProdutosEntity> productObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository = new CustomRepository<>(UsuariosEntity.class, UsuariosEntity::rowMapper);
        produtosRepository = new CustomRepository<>(ProdutosEntity.class, ProdutosEntity::rowMapper);

        entityObservableList.addAll(repository.findAll());
        tablePessoas.getColumns().addAll(new TableConsume<UsuariosEntity>().findGroup(UsuariosEntity.class));
        tablePessoas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tablePessoas.getItems().addAll(entityObservableList);

        productObservableList.addAll(produtosRepository.findAll());

        productObservableList.forEach(it -> it.setValor(Currency.formatToBrazilianCurrency(new BigDecimal(it.getValor()))));
        tableProdutos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableProdutos.getColumns().addAll(new TableConsume<ProdutosEntity>().findGroup(ProdutosEntity.class));
        tableProdutos.getItems().addAll(productObservableList);
        filterUserData();
        filterProductData();
    }

    private void filterUserData() {
        FilteredList<UsuariosEntity> filteredList = new FilteredList<>(entityObservableList, b -> true);
        pessoaFilter.textProperty().addListener(((observableValue, oldValue, newValue) -> filteredList.setPredicate(searchModel -> {
            if (newValue == null || newValue.isEmpty() || newValue.isBlank()) return true;

            String keyword = newValue.toLowerCase(Locale.ROOT);
            if (searchModel.getAdm().toString().contains(keyword)) {
                return true;
            } else if (searchModel.getNome().toLowerCase(Locale.ROOT).contains(keyword)) {
                return true;
            } else if (searchModel.getEmail().toLowerCase(Locale.ROOT).contains(keyword)) {
                return true;
            } else return false;
        })));

        SortedList<UsuariosEntity> entitySortedList = new SortedList<>(filteredList);
        entitySortedList.comparatorProperty().bind(tablePessoas.comparatorProperty());
        tablePessoas.setItems(entitySortedList);
    }

    private void filterProductData() {
        FilteredList<ProdutosEntity> filteredList = new FilteredList<>(productObservableList, b -> true);
        produtoFilter.textProperty().addListener(((observableValue, oldValue, newValue) -> filteredList.setPredicate(searchModel -> {
            if (newValue == null || newValue.isEmpty() || newValue.isBlank()) return true;

            String keyword = newValue.toLowerCase(Locale.ROOT);
            if (searchModel.getDescricao().equals(keyword)) {
                return true;
            } else if (searchModel.getNome().toLowerCase(Locale.ROOT).contains(keyword)) {
                return true;
            } else if (searchModel.getValor().toLowerCase(Locale.ROOT).contains(keyword)) {
                return true;
            } else return false;
        })));

        SortedList<ProdutosEntity> entitySortedList = new SortedList<>(filteredList);
        entitySortedList.comparatorProperty().bind(tableProdutos.comparatorProperty());
        tableProdutos.setItems(entitySortedList);
    }

    @FXML
    protected void onSair(ActionEvent event) {
        try {
            FindResource.changeScreen("hello-view.fxml", HelloController.class.getName(), event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onRemove(ActionEvent event) {
        var selectionModel = tablePessoas.getSelectionModel();
        var selectionModelP = tableProdutos.getSelectionModel();

        if (selectionModel != null) {
            var selectedIndex = selectionModel.getSelectedIndex();
            if (selectedIndex >= 0) {
                entityObservableList.remove(selectedIndex);
            }
        }

        if (selectionModelP != null) {
            var selectedIndex = selectionModelP.getSelectedIndex();
            if (selectedIndex >= 0) {
                productObservableList.remove(selectedIndex);
            }
        }
    }

    @FXML
    protected void onAdd() throws Exception {
        Parent root = FXMLLoader.load(FindResource.resource("cadastro-view.fxml", CadastroView.class.getName()));
        Stage register = new Stage();
        register.setScene(new Scene(root));
        register.show();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                entityObservableList.removeAll(entityObservableList);
                entityObservableList.addAll(repository.findAll());
                executor.shutdown();
            });
        };
        scheduler.schedule(task, 5, TimeUnit.SECONDS);
        scheduler.shutdown();
    }

}
