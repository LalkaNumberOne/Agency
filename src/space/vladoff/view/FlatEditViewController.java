package space.vladoff.view;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import space.vladoff.model.Flat;
import space.vladoff.model.MaterialType;
import space.vladoff.model.PlanningType;

/**
 * Created by Vladislav Russinovich on 11.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class FlatEditViewController {
    @FXML
    private DatePicker dateOfRecord;
    @FXML
    private TextField roomCount;
    @FXML
    private ComboBox<MaterialType> materialType;
    @FXML
    private TextField floor;
    @FXML
    private TextField area;
    @FXML
    private CheckBox balcony;
    @FXML
    private CheckBox isIsolate;
    @FXML
    private ComboBox<PlanningType> plan;
    @FXML
    private TextField objectField;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField middleName;


    @FXML
    private TextField city;
    @FXML
    private TextField street;
    @FXML
    private TextField buildNo;
    @FXML
    private TextField apartNo;


    private Stage dialogStage;
    private Flat flat;

    @FXML
    private void initialize() {
        materialType.getItems().setAll(MaterialType.values());
        plan.getItems().setAll(PlanningType.values());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setRealEstate(Flat flat) {
        this.flat = flat;

        dateOfRecord.setValue(flat.getDateOfRecord());
        roomCount.setText(Integer.toString(flat.getRoomCount()));
        materialType.setValue(flat.getObjectMaterial());
        floor.setText(Integer.toString(flat.getFloor()));
        area.setText(Double.toString(flat.getArea()));
        balcony.setSelected(flat.isBalcony());
        isIsolate.setSelected(!flat.isolate());
        plan.setValue(flat.getPlan());
        objectField.setText(Integer.toString(flat.getObjectNumber()));

        firstName.setText(flat.getOwner().getFirstName());
        lastName.setText(flat.getOwner().getLastName());
        middleName.setText(flat.getOwner().getMiddleName());

        city.setText(flat.getAdress().getCity());
        street.setText(flat.getAdress().getCity());
        buildNo.setText(flat.getAdress().getBuildNo());
        apartNo.setText(flat.getAdress().getApartNo());
    }

}
