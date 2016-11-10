package space.vladoff.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import space.vladoff.model.Adress;
import space.vladoff.model.Flat;
import space.vladoff.model.Person;
import space.vladoff.model.RealEstate;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Vladislav Russinovich on 09.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
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
    private TableColumn<RealEstate, String> balcony;
    @FXML
    private TableColumn<RealEstate, String> isIsolate;
    @FXML
    private TableColumn<RealEstate, String> PlanningType;

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
        area.setCellValueFactory(CellData -> CellData.getValue().roomCountProperty());
        balcony.setCellValueFactory(CellData -> new SimpleStringProperty((CellData.getValue().isBalcony()) ? "Есть" : "Нет"));
        isIsolate.setCellValueFactory(CellData -> new SimpleStringProperty((CellData.getValue().isolate()) ? "Изолированный" : "Смежный"));
        PlanningType.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RealEstate, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RealEstate, String> param) {
                if (param.getValue() instanceof Flat) {
                    Flat flat = (Flat) param.getValue();
                    return new SimpleStringProperty(flat.getPlan().toString());
                } else return new SimpleStringProperty("Нет");
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

    @FXML
    private void handleEditEstates() {

    }

    @FXML
    private void handleClose() {
        viewStage.close();
    }
}
