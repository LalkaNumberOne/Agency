package space.vladoff.view;
/**
 * Created by Vladislav Russinovich on 09.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import space.vladoff.MainController;
import space.vladoff.model.*;

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
    private TableColumn<RealEstate, Boolean> balcony;
    @FXML
    private TableColumn<RealEstate, Boolean> isIsolate;
    @FXML
    private TableColumn<RealEstate, PlanningType> PlanningType;

    private Stage viewStage;

    private AgencyBrowseViewController controller;

    private ObservableList<RealEstate> realEstateObservableList;
    private ArrayList<RealEstate> realEstateArrayList;

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
        isIsolate.setCellValueFactory(CellData -> CellData.getValue().IsolateProperty());
        PlanningType.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RealEstate, space.vladoff.model.PlanningType>, ObservableValue<space.vladoff.model.PlanningType>>() {
            @Override
            public ObservableValue<PlanningType> call(TableColumn.CellDataFeatures<RealEstate, PlanningType> param) {
                if (param.getValue() instanceof Flat) {
                    Flat flat = (Flat) param.getValue();
                    return flat.planProperty();
                }
                return new SimpleObjectProperty<space.vladoff.model.PlanningType>(space.vladoff.model.PlanningType.unknown);
            }
        });
    }

    public void setDialogStage(Stage viewStage) {
        this.viewStage = viewStage;
    }

    public void setMainController(AgencyBrowseViewController controller) {
        this.controller = controller;
        realEstateArrayList = controller.getRealEstateData();
        realEstateObservableList = FXCollections.observableArrayList(realEstateArrayList);
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
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

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
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(viewStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            FlatEditViewController controller = loader.getController();
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
        //TODO: Implement House edit dialog method and form
        return false;
    }

    public boolean showRealEstateEditDialog(Cottage cottage) {
        //TODO: Implement Cottage edit dialog method and form
        return false;
    }

    public boolean showRealEstateEditDialog(RealEstate realestate) {
        //Dummy method
        return false;
    }

    @FXML
    private void handleClose() {
        viewStage.close();
    }

    @FXML
    private void handleNewRealEstate() {
        boolean okClicked = false;
        RealEstate realEstate = new RealEstate();

        List<String> choices = new ArrayList<>();
        choices.add("Квартира");
        choices.add("Коттедж");
        choices.add("Дом");

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
                case "Дом":
                    realEstate = new House();
                    okClicked = showRealEstateEditDialog((House) realEstate);
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
     * Открывает диалоговое окно для изменения выбранного адресата.
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
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
