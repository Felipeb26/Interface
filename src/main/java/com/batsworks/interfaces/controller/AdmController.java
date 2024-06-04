package com.batsworks.interfaces.controller;

import com.batsworks.interfaces.CadastroView;
import com.batsworks.interfaces.HelloController;
import com.batsworks.interfaces.database.CustomRepository;
import com.batsworks.interfaces.model.ProdutosEntity;
import com.batsworks.interfaces.model.UsuariosEntity;
import com.batsworks.interfaces.navigation.Change;
import com.batsworks.interfaces.navigation.Screens;
import com.batsworks.interfaces.utils.Currency;
import com.batsworks.interfaces.utils.DefaultController;
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
import javafx.scene.control.Button;
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

public class AdmController extends DefaultController implements Initializable {

    private static UsuariosEntity usuariosEntity;
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
    @FXML
    Button btnShowUser;
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
            return searchModel.getAdm().toString().contains(keyword)
                    || searchModel.getNome().toLowerCase(Locale.ROOT).contains(keyword)
                    || searchModel.getEmail().toLowerCase(Locale.ROOT).contains(keyword);

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
            return searchModel.getDescricao().equals(keyword)
                    || (searchModel.getNome().toLowerCase(Locale.ROOT).contains(keyword)
                    || (searchModel.getValor().toLowerCase(Locale.ROOT).contains(keyword)));
        })));

        SortedList<ProdutosEntity> entitySortedList = new SortedList<>(filteredList);
        entitySortedList.comparatorProperty().bind(tableProdutos.comparatorProperty());
        tableProdutos.setItems(entitySortedList);
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
        FXMLLoader loader = new FXMLLoader(Change.resource(Screens.CADASTRO_USUARIO, CadastroView.class.getName()));
        Parent root = loader.load();
        Stage register = new Stage();
        if (loader.getController() instanceof DefaultController controller) {
            controller.loadValues("hide-sair");
        }
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

    @FXML
    protected void onAddProduto() throws Exception {
        Parent root = FXMLLoader.load(Change.resource(Screens.CADASTRO_PRODUTO, CadastroView.class.getName()));
        Stage register = new Stage();
        register.setScene(new Scene(root));
        register.show();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                productObservableList.removeAll(productObservableList);
                productObservableList.addAll(produtosRepository.findAll());
                productObservableList.forEach(it -> it.setValor(Currency.formatToBrazilianCurrency(new BigDecimal(it.getValor()))));
                executor.shutdown();
            });
        };
        scheduler.schedule(task, 3, TimeUnit.SECONDS);
        scheduler.shutdown();
    }

    @FXML
    protected void updateUser(ActionEvent event) throws Exception {
        Change.screen(Screens.ATUALIZAR_USUARIO, HelloController.class.getName(), event, usuariosEntity);

    }

    @Override
    public void loadValues(Object... args) {
        super.loadValues(args);
        if (args[0] instanceof UsuariosEntity entity) {
            usuariosEntity = entity;
        }
    }
}
