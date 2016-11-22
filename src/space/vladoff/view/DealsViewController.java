package space.vladoff.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import space.vladoff.MainController;
import space.vladoff.model.*;
import space.vladoff.util.LabList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Vladislav Russinovich on 18.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class DealsViewController {
    @FXML
    private TableView<Deal> dealTableView;
    @FXML
    private TableColumn<Deal, LocalDate> dateOfRecord;
    @FXML
    private TableColumn<Deal, Number> objectNumber;
    @FXML
    private TableColumn<Deal, Person> customer;
    @FXML
    private TableColumn<Deal, Number> tax;
    @FXML
    private TableColumn<Deal, Number> insurance;
    @FXML
    private TableColumn<Deal, Number> cost;

    private Stage viewStage;

    private AgencyBrowseViewController controller;

    private ObservableList<Deal> dealObservableList;
    private LabList<Deal> dealArrayList;

    @FXML
    private void initialize() {
        dateOfRecord.setCellValueFactory(CellData -> CellData.getValue().dateOfDealProperty());
        objectNumber.setCellValueFactory(CellData -> CellData.getValue().objectNumberProperty());
        customer.setCellValueFactory(CellData -> CellData.getValue().customerProperty());
        tax.setCellValueFactory(CellData -> CellData.getValue().taxProperty());
        insurance.setCellValueFactory(CellData -> CellData.getValue().insuranceProperty());
        cost.setCellValueFactory(CellData -> CellData.getValue().costProperty());
    }

    public void setDialogStage(Stage viewStage) {
        this.viewStage = viewStage;
    }

    public void setMainController(AgencyBrowseViewController controller) {
        this.controller = controller;
        dealArrayList = controller.getDealsData();
        dealObservableList = FXCollections.observableArrayList();
        for (int i = 0; i < dealArrayList.size(); i++) {
            dealObservableList.add(dealArrayList.get(i));
        }
        dealTableView.setItems(dealObservableList);
    }

    @FXML
    private void handleDeleteDeals() {
        int selectedIndex = dealTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            dealArrayList.remove(dealTableView.getItems().get(selectedIndex));
            controller.setDealsData(dealArrayList);
            dealTableView.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(viewStage);
            alert.setTitle("Ничего не выбрано!");
            alert.setHeaderText("Сделака не выбрана!");
            alert.setContentText("Пожалуйста, выберите сделку в таблице.");

            alert.showAndWait();
        }
    }

    public boolean showDealEditDialog(Deal deal) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("view/DealsEditView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование сделки");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(viewStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            DealsEditViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDeal(deal);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleNewDeal() {
        boolean okClicked = false;
        Deal deal = new Deal();
        okClicked = showDealEditDialog(deal);

        if (okClicked) {
            dealObservableList.add(deal);
            dealArrayList.add(deal);
        }
    }

    @FXML
    private void handleEditDeal() {
        Deal deal = dealTableView.getSelectionModel().getSelectedItem();
        if (deal != null) {
            dealArrayList.remove(deal);
            showDealEditDialog(deal);
            dealArrayList.add(deal);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(viewStage);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Сделка не выбрана");
            alert.setContentText("Пожалуйста, выберите сделку");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleClose() {
        viewStage.close();
    }
}
