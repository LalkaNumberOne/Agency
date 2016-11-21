package space.vladoff.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import space.vladoff.model.Deal;
import space.vladoff.model.Person;

/**
 * Created by Vladislav Russinovich on 18.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class DealsEditViewController {

    @FXML
    private TextField cost;
    @FXML
    private TextField tax;
    @FXML
    private TextField objectnumber;
    @FXML
    private TextField insurance;
    @FXML
    private DatePicker dateOfDeal;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField middleName;

    private Stage dialogStage;
    private Deal deal;
    private boolean okClicked = false;

    @FXML
    protected void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
        dateOfDeal.setValue(deal.getDateOfDeal());
        objectnumber.setText(Integer.toString(deal.getObjectNumber()));
        cost.setText(Double.toString(deal.getCost()));
        tax.setText(Double.toString(deal.getTax()));
        insurance.setText(Double.toString(deal.getInsurance()));

        firstName.setText(deal.getCustomer().getFirstName());
        lastName.setText(deal.getCustomer().getLastName());
        middleName.setText(deal.getCustomer().getMiddleName());
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            deal.setDateOfDeal(dateOfDeal.getValue());
            deal.setObjectNumber(Integer.parseInt(objectnumber.getText()));
            deal.setCost(Double.parseDouble(cost.getText()));
            deal.setInsurance(Double.parseDouble(insurance.getText()));
            deal.setTax(Double.parseDouble(tax.getText()));

            deal.setCustomer(new Person(firstName.getText(), lastName.getText(), middleName.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        StringBuilder sb = new StringBuilder();

        if (dateOfDeal.getValue() == null)
            sb.append("Неверная дата!\n");
        if (objectnumber.getText() == null || objectnumber.getText().length() == 0)
            sb.append("Неверный номер объекта!\n");
        else {
            try {
                Integer.parseInt(objectnumber.getText());
            } catch (NumberFormatException e) {
                sb.append("Номер объекта должен быть задан числом!\n");
            }
        }
        if (tax.getText() == null || tax.getText().length() == 0)
            sb.append("Неверный налог!\n");
        else {
            try {
                Double.parseDouble(tax.getText());
            } catch (NumberFormatException e) {
                sb.append("Налог должен быть задан числом!\n");
            }
        }
        if (insurance.getText() == null || insurance.getText().length() == 0)
            sb.append("Неверное значение страховки\n");
        else {
            try {
                Double.parseDouble(insurance.getText());
            } catch (NumberFormatException e) {
                sb.append("Страховка должна быть задана числом\n");
            }
        }
        if (cost.getText() == null || cost.getText().length() == 0)
            sb.append("Неверное значение цены\n");
        else {
            try {
                Double.parseDouble(cost.getText());
            } catch (NumberFormatException e) {
                sb.append("Цена должна быть задана числом\n");
            }
        }
        if (firstName.getText() == null || firstName.getText().length() == 0)
            sb.append("Имя не задано!\n");
        if (lastName.getText() == null || lastName.getText().length() == 0)
            sb.append("Фамилия не задана!\n");
        if (middleName.getText() == null || middleName.getText().length() == 0)
            sb.append("Отчество не задано!\n");

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
