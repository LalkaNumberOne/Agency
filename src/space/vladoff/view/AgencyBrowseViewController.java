package space.vladoff.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import space.vladoff.MainController;
import space.vladoff.model.RealEstateAgency;
import javafx.scene.control.Label;

/**
 * Created by Vladislav Russinovich on 20.10.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */

public class AgencyBrowseViewController {
    private ObservableList<RealEstateAgency> list;
    private MainController mainApp;

    /*
    Объявление управляющих элементов контроллера
     */
    @FXML
    Label agencyNameLabel;
    @FXML
    Label billSumLabel;
    @FXML
    Label licenseNumberLabel;
    @FXML
    Label dealCountLabel;
    @FXML
    Label houseCountLabel;
    @FXML
    Label rateOfReturnLabel;
    @FXML
    Label INNLabel;
    @FXML
    Label KPPLabel;
    @FXML
    Label bankNameLabel;
    @FXML
    Label BIKLabel;
    @FXML
    Label accountLabel;
    //ObservableList<RealEstateAgency> spisok;
    @FXML
    ComboBox<RealEstateAgency> agencys;


    public AgencyBrowseViewController()
    {
    }

    @FXML
    private void initialize() {
        //Показываем информацию о текущем агентстве(null)
        showAgencyDetails(null);
        //Слушаем действия при взаимодействии с ComboBox
        agencys.setOnAction(event -> showAgencyDetails(agencys.getSelectionModel().getSelectedItem()));
    }

    public void setMainApp(MainController mainApp) {
        //Получаем ссылку на главный контроллер(вызывается из главного контроллера)
        this.mainApp = mainApp;
        list = mainApp.getAgencyData();
        agencys.setItems(list);
    }


    //Метод вывода информации об агентстве на форму
    private void showAgencyDetails(RealEstateAgency agency) {
        if (agency != null) {
            agencyNameLabel.setText(agency.getAgencyName());
            billSumLabel.setText(Double.toString(agency.getBillSum()));
            licenseNumberLabel.setText(agency.getLicenseNumber());
            dealCountLabel.setText(Integer.toString(agency.getDealCount()));
            houseCountLabel.setText(Integer.toString(agency.getHouseCount()));
            rateOfReturnLabel.setText(Integer.toString(agency.getRateOfReturn()));
            INNLabel.setText(agency.getRequisite().getINN());
            KPPLabel.setText(agency.getRequisite().getKPP());
            bankNameLabel.setText(agency.getRequisite().getBankName());
            BIKLabel.setText(agency.getRequisite().getBIK());
            accountLabel.setText(agency.getRequisite().getAccount());
        } else {
            agencyNameLabel.setText("");
            billSumLabel.setText("");
            licenseNumberLabel.setText("");
            dealCountLabel.setText("");
            houseCountLabel.setText("");
            rateOfReturnLabel.setText("");
            INNLabel.setText("");
            KPPLabel.setText("");
            bankNameLabel.setText("");
            BIKLabel.setText("");
            accountLabel.setText("");
        }
    }
}


