package space.vladoff.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import space.vladoff.model.*;
import space.vladoff.model.enums.GarageProperty;
import space.vladoff.model.enums.PlanningType;
import space.vladoff.model.enums.Pool;

/**
 * Created by Vladislav Russinovich on 17.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class CottageEditViewController extends RealEstateEditViewController {
    @FXML
    private ComboBox<Pool> pool;
    @FXML
    private ComboBox<GarageProperty> garage;

    private Cottage cottage;

    @FXML
    protected void initialize() {
        super.initialize();
        pool.getItems().setAll(Pool.values());
        garage.getItems().setAll(GarageProperty.values());
    }

    @Override
    public void setRealEstate(RealEstate realEstate) {
        super.setRealEstate(realEstate);
        this.cottage = (Cottage) realEstate;
        pool.setValue(cottage.getPool());
        garage.setValue(cottage.getGarage());
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
            cottage.setGarage(garage.getValue());
            cottage.setPool(pool.getValue());
            realEstate.setObjectNumber(Integer.parseInt(objectField.getText()));

            realEstate.setOwner(new Person(firstName.getText(), lastName.getText(), middleName.getText()));

            realEstate.setAdress(new Adress(city.getText(), street.getText(), buildNo.getText(), apartNo.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

}
