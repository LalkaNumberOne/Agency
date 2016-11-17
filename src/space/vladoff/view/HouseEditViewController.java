package space.vladoff.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import space.vladoff.model.*;
import space.vladoff.model.enums.ElectricityProperty;
import space.vladoff.model.enums.GarageProperty;
import space.vladoff.model.enums.Inventory;

/**
 * Created by Vladislav Russinovich on 17.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class HouseEditViewController extends RealEstateEditViewController {
    @FXML
    private ComboBox<Inventory> inventory;
    @FXML
    private ComboBox<GarageProperty> garage;
    @FXML
    private ComboBox<ElectricityProperty> electricity;

    private House house;

    @FXML
    protected void initialize() {
        super.initialize();
        inventory.getItems().setAll(Inventory.values());
        garage.getItems().setAll(GarageProperty.values());
        electricity.getItems().setAll(ElectricityProperty.values());
    }

    @Override
    public void setRealEstate(RealEstate realEstate) {
        super.setRealEstate(realEstate);
        this.house = (House) realEstate;
        this.electricity.setValue(house.getElectricity());
        this.garage.setValue(house.getGarage());
        this.inventory.setValue(house.getInventory());
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
            house.setGarage(garage.getValue());
            house.setElectricity(electricity.getValue());
            house.setInventory(inventory.getValue());
            realEstate.setObjectNumber(Integer.parseInt(objectField.getText()));

            realEstate.setOwner(new Person(firstName.getText(), lastName.getText(), middleName.getText()));

            realEstate.setAdress(new Adress(city.getText(), street.getText(), buildNo.getText(), apartNo.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }
}
