package space.vladoff.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import space.vladoff.model.*;
import space.vladoff.model.enums.BalconyProperty;
import space.vladoff.model.enums.MaterialType;
import space.vladoff.model.enums.RoomType;

/**
 * Created by Vladislav Russinovich on 17.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class RealEstateEditViewController {
    @FXML
    protected DatePicker dateOfRecord;
    @FXML
    protected TextField roomCount;
    @FXML
    protected ComboBox<MaterialType> materialType;
    @FXML
    protected TextField floor;
    @FXML
    protected TextField area;
    @FXML
    protected TextField objectField;

    @FXML
    protected ComboBox<BalconyProperty> balcony;
    @FXML
    protected ComboBox<RoomType> roomType;

    @FXML
    protected TextField firstName;
    @FXML
    protected TextField lastName;
    @FXML
    protected TextField middleName;


    @FXML
    protected TextField city;
    @FXML
    protected TextField street;
    @FXML
    protected TextField buildNo;
    @FXML
    protected TextField apartNo;


    protected Stage dialogStage;
    protected RealEstate realEstate;
    protected boolean okClicked = false;

    @FXML
    protected void initialize() {
        materialType.getItems().setAll(MaterialType.values());
        roomType.getItems().setAll(RoomType.values());
        balcony.getItems().setAll(BalconyProperty.values());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;

        dateOfRecord.setValue(realEstate.getDateOfRecord());
        roomCount.setText(Integer.toString(realEstate.getRoomCount()));
        materialType.setValue(realEstate.getObjectMaterial());
        floor.setText(Integer.toString(realEstate.getFloor()));
        area.setText(Double.toString(realEstate.getArea()));
        balcony.setValue(realEstate.getBalcony());
        roomType.setValue(realEstate.getRoomType());

        objectField.setText(Integer.toString(realEstate.getObjectNumber()));

        firstName.setText(realEstate.getOwner().getFirstName());
        lastName.setText(realEstate.getOwner().getLastName());
        middleName.setText(realEstate.getOwner().getMiddleName());

        city.setText(realEstate.getAdress().getCity());
        street.setText(realEstate.getAdress().getStreet());
        buildNo.setText(realEstate.getAdress().getBuildNo());
        apartNo.setText(realEstate.getAdress().getApartNo());
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            realEstate.setDateOfRecord(dateOfRecord.getValue());
            realEstate.setFloor(Integer.parseInt(floor.getText()));
            realEstate.setObjectMaterial(materialType.getValue());
            realEstate.setRoomCount(Integer.parseInt(roomCount.getText()));
            realEstate.setArea(Double.parseDouble(area.getText()));
            realEstate.setBalcony(balcony.getValue());
            realEstate.setRoomType(roomType.getValue());
            realEstate.setObjectNumber(Integer.parseInt(objectField.getText()));

            realEstate.setOwner(new Person(firstName.getText(), lastName.getText(), middleName.getText()));

            realEstate.setAdress(new Adress(city.getText(), street.getText(), buildNo.getText(), apartNo.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    protected boolean isInputValid() {
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
