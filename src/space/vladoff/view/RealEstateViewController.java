package space.vladoff.view;
/**
 * Created by Vladislav Russinovich on 09.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */

import javafx.beans.property.SimpleObjectProperty;
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
import space.vladoff.model.enums.*;
import space.vladoff.util.LabList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class RealEstateViewController {
    @FXML
    private TableView<RealEstate> realEstateTable;
    @FXML
    private TableColumn<RealEstate, LocalDate> dateOfRecord;
    @FXML
    private TableColumn<RealEstate, Number> objectNumber;
    @FXML
    private TableColumn<RealEstate, Adress> objectAdress;
    @FXML
    private TableColumn<RealEstate, Person> owner;
    @FXML
    private TableColumn<RealEstate, Number> floor;
    @FXML
    private TableColumn<RealEstate, Number> roomCount;
    @FXML
    private TableColumn<RealEstate, Number> area;
    @FXML
    private TableColumn<RealEstate, BalconyProperty> balcony;
    @FXML
    private TableColumn<RealEstate, RoomType> isIsolate;
    @FXML
    private TableColumn<RealEstate, PlanningType> planningType;
    @FXML
    private TableColumn<RealEstate, MaterialType> materialType;
    @FXML
    private TableColumn<RealEstate, Inventory> inventory;
    @FXML
    private TableColumn<RealEstate, GarageProperty> garage;
    @FXML
    private TableColumn<RealEstate, Pool> pool;
    @FXML
    private TableColumn<RealEstate, ElectricityProperty> electricity;

    private Stage viewStage;

    private AgencyBrowseViewController controller;

    private ObservableList<RealEstate> realEstateObservableList;
    private List<RealEstate> realEstateArrayList;

    @FXML
    private void initialize() {
        dateOfRecord.setCellValueFactory(CellData -> CellData.getValue().dateOfRecordProperty());
        objectNumber.setCellValueFactory(CellData -> CellData.getValue().objectNumberProperty());
        objectAdress.setCellValueFactory(CellData -> CellData.getValue().adressProperty());
        owner.setCellValueFactory(CellData -> CellData.getValue().ownerProperty());
        floor.setCellValueFactory(CellData -> CellData.getValue().floorProperty());
        roomCount.setCellValueFactory(CellData -> CellData.getValue().roomCountProperty());
        area.setCellValueFactory(CellData -> CellData.getValue().areaProperty());
        balcony.setCellValueFactory(CellData -> CellData.getValue().balconyProperty());
        isIsolate.setCellValueFactory(CellData -> CellData.getValue().roomTypeProperty());
        materialType.setCellValueFactory(CellData -> CellData.getValue().objectMaterialProperty());

        pool.setCellValueFactory(CellData -> (CellData.getValue() instanceof Cottage) ?
                ((Cottage) CellData.getValue()).poolProperty() : new SimpleObjectProperty<>(Pool.no));

        electricity.setCellValueFactory(CellData -> (CellData.getValue() instanceof House) ?
                ((House) CellData.getValue()).electricityProperty() : new SimpleObjectProperty<>(ElectricityProperty.yes));

        garage.setCellValueFactory(CellData -> (CellData.getValue() instanceof House) ?
                ((CellData.getValue() instanceof Cottage) ?
                        ((Cottage) CellData.getValue()).garageProperty() : new SimpleObjectProperty<>(GarageProperty.noGarage))
                : new SimpleObjectProperty<>(GarageProperty.noGarage));

        inventory.setCellValueFactory(CellData -> (CellData.getValue() instanceof House) ?
                ((House) CellData.getValue()).inventoryProperty() : new SimpleObjectProperty<>(Inventory.noInv));


        planningType.setCellValueFactory(CellData -> (CellData.getValue() instanceof Flat) ?
                ((Flat) CellData.getValue()).planProperty() : new SimpleObjectProperty<>(PlanningType.unknown));
    }

    public void setDialogStage(Stage viewStage) {
        this.viewStage = viewStage;
    }

    public void setMainController(AgencyBrowseViewController controller) {
        this.controller = controller;
        realEstateArrayList = controller.getRealEstateData();
        realEstateObservableList = FXCollections.observableArrayList();
        for (int i = 0; i < realEstateArrayList.size(); i++) {
            realEstateObservableList.add(realEstateArrayList.get(i));
        }
        realEstateTable.setItems(realEstateObservableList);
    }

    @FXML
    private void handleDeleteEstates() {
        int selectedIndex = realEstateTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            realEstateArrayList.remove(realEstateTable.getItems().get(selectedIndex));
            controller.setRealEstateData(realEstateArrayList);
            realEstateTable.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(viewStage);
            alert.setTitle("Ничего не выбрано!");
            alert.setHeaderText("Объект недвижимости не выбран!");
            alert.setContentText("Пожалуйста, выберите объект недвижимости в таблице.");

            alert.showAndWait();
        }
    }

    public boolean showRealEstateEditDialog(Flat flat) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("view/FlatEditView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование квартиры");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(viewStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            RealEstateEditViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setRealEstate(flat);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showRealEstateEditDialog(House house) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("view/HouseEditView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование дачи");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(viewStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            RealEstateEditViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setRealEstate(house);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showRealEstateEditDialog(Cottage cottage) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("view/CottageEditView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование коттеджа");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(viewStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            RealEstateEditViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setRealEstate(cottage);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleClose() {
        viewStage.close();
    }

    @FXML
    private void handleNewRealEstate() {
        boolean okClicked = false;
        RealEstate realEstate = null;

        List<String> choices = new ArrayList<>();
        choices.add("Квартира");
        choices.add("Коттедж");
        choices.add("Дача");

        ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Создание нового объекта");
        dialog.setHeaderText("Создание нового объекта");
        dialog.setContentText("Выберите объект для создание");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            switch (result.get()) {
                case "Квартира":
                    realEstate = new Flat();
                    okClicked = showRealEstateEditDialog((Flat) realEstate);
                    break;
                case "Коттедж":
                    realEstate = new Cottage();
                    okClicked = showRealEstateEditDialog((Cottage) realEstate);
                    break;
                case "Дача":
                    realEstate = new House();
                    okClicked = showRealEstateEditDialog((House) realEstate);
                    break;
                default:
                    return;
            }
        }
        if (okClicked) {
            realEstateObservableList.add(realEstate);
            realEstateArrayList.add(realEstate);
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit...
     */
    @FXML
    private void handleEditRealEstate() {
        RealEstate realEstate = realEstateTable.getSelectionModel().getSelectedItem();
        if (realEstate != null) {
            realEstateArrayList.remove(realEstate);
            if (realEstate instanceof Flat)
                showRealEstateEditDialog((Flat) realEstate);
            if (realEstate instanceof House)
                showRealEstateEditDialog((House) realEstate);
            if (realEstate instanceof Cottage)
                showRealEstateEditDialog((Cottage) realEstate);
            realEstateArrayList.add(realEstate);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(viewStage);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Объект недвижимости не выбран");
            alert.setContentText("Пожалуйста, выберите объект недвижимости.");

            alert.showAndWait();
        }
    }
}
