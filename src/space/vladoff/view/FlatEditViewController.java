package space.vladoff.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import space.vladoff.model.*;

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
    private boolean okClicked = false;

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
        isIsolate.setSelected(!flat.isIsolate());
        plan.setValue(flat.getPlan());
        objectField.setText(Integer.toString(flat.getObjectNumber()));

        firstName.setText(flat.getOwner().getFirstName());
        lastName.setText(flat.getOwner().getLastName());
        middleName.setText(flat.getOwner().getMiddleName());

        city.setText(flat.getAdress().getCity());
        street.setText(flat.getAdress().getStreet());
        buildNo.setText(flat.getAdress().getBuildNo());
        apartNo.setText(flat.getAdress().getApartNo());
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            flat.setDateOfRecord(dateOfRecord.getValue());
            flat.setFloor(Integer.parseInt(floor.getText()));
            flat.setObjectMaterial(materialType.getValue());
            flat.setRoomCount(Integer.parseInt(roomCount.getText()));
            flat.setArea(Double.parseDouble(area.getText()));
            flat.setBalcony(balcony.isSelected());
            flat.setIsIsolate(!isIsolate.isSelected());
            flat.setPlan(plan.getValue());
            flat.setObjectNumber(Integer.parseInt(objectField.getText()));

            flat.setOwner(new Person(firstName.getText(), lastName.getText(), middleName.getText()));

            flat.setAdress(new Adress(city.getText(), street.getText(), buildNo.getText(), apartNo.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        StringBuilder sb = new StringBuilder();

        if (dateOfRecord.getValue() == null)
            sb.append("Неверная дата!\n");
        if (floor.getText() == null || floor.getText().length() == 0)
            sb.append("Неверное количество этажей!\n");
        else {
            try {
                Integer.parseInt(floor.getText());
            } catch (NumberFormatException e) {
                sb.append("Количество этажей должно быть указано числом!\n");
            }
        }
        if (roomCount.getText() == null || floor.getText().length() == 0)
            sb.append("Неверное количество комнат!\n");
        else {
            try {
                Integer.parseInt(roomCount.getText());
            } catch (NumberFormatException e) {
                sb.append("Количество комнат должно быть указано числом!\n");
            }
        }
        if (area.getText() == null || area.getText().length() == 0)
            sb.append("Неверное значение площади!\n");
        else {
            try {
                Double.parseDouble(area.getText());
            } catch (NumberFormatException e) {
                sb.append("Площадь должна быть задана числом!\n");
            }
        }
        if (objectField.getText() == null || objectField.getText().length() == 0)
            sb.append("Неверный номер объекта!");
        else {
            try {
                Integer.parseInt(objectField.getText());
            } catch (NumberFormatException e) {
                sb.append("Номер объекта должен быть задан числом!");
            }
        }

        if (firstName.getText() == null || firstName.getText().length() == 0)
            sb.append("Имя не задано!\n");
        if (lastName.getText() == null || lastName.getText().length() == 0)
            sb.append("Фамилия не задана!\n");
        if (middleName.getText() == null || middleName.getText().length() == 0)
            sb.append("Отчество не задано!\n");

        if (city.getText() == null || city.getText().length() == 0)
            sb.append("Город не задан!\n");
        if (street.getText() == null || street.getText().length() == 0)
            sb.append("Улица не задана!\n");
        if (buildNo.getText() == null || buildNo.getText().length() == 0)
            sb.append("Номер здания не задан!\n");
        if (apartNo.getText() == null || apartNo.getText().length() == 0)
            sb.append("Номер квартиры не задан!\n");

        if (sb.toString().length() == 0) return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Неверно заполненные поля!");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(sb.toString());

            alert.showAndWait();

            return false;
        }

    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

}
