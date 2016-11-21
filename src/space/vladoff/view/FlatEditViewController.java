package space.vladoff.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import space.vladoff.model.*;
import space.vladoff.model.enums.PlanningType;

/**
 * Created by Vladislav Russinovich on 11.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class FlatEditViewController extends RealEstateEditViewController {

    @FXML
    private ComboBox<PlanningType> plan;

    private Flat flat;

    @FXML
    protected void initialize() {
        super.initialize();
        plan.getItems().setAll(PlanningType.values());
    }

    @Override
    public void setRealEstate(RealEstate realEstate) {
        super.setRealEstate(realEstate);
        this.flat = (Flat) realEstate;
        plan.setValue(flat.getPlan());
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
            flat.setPlan(plan.getValue());
            realEstate.setObjectNumber(Integer.parseInt(objectField.getText()));

            realEstate.setOwner(new Person(firstName.getText(), lastName.getText(), middleName.getText()));

            realEstate.setAdress(new Adress(city.getText(), street.getText(), buildNo.getText(), apartNo.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }
}
