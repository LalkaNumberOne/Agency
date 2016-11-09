package space.vladoff.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import space.vladoff.model.Flat;
import space.vladoff.model.Person;
import space.vladoff.model.RealEstate;

import java.time.LocalDate;

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

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage viewStage) {
        this.viewStage = viewStage;
    }

    public void buildTable(ObservableList<RealEstate> realEstates) {
        realEstateTable.setItems(realEstates);
        dateOfRecord.setCellValueFactory(CellData -> CellData.getValue().dateOfRecordProperty());
        objectNumber.setCellValueFactory(CellData -> CellData.getValue().objectNumberProperty());
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

}
