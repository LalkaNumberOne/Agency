package space.vladoff.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import space.vladoff.MainController;
import space.vladoff.model.Deal;
import space.vladoff.model.RealEstate;
import space.vladoff.model.RealEstateAgency;
import javafx.scene.control.Label;
import space.vladoff.util.LabList;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

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
    @FXML
    ComboBox<RealEstateAgency> agencys;

    ObservableList<RealEstate> realEstateObservableList;


    public AgencyBrowseViewController() {
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

    @FXML
    private void handleRealEstates() {
        if (agencys.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Внимание!");
            alert.setHeaderText("Не выбрано агентство.");
            alert.setContentText("Выберите агентство, пожалуйста.");

            alert.showAndWait();
        } else showRealEstateView();
    }

    @FXML
    private void handleDeals() {
        if (agencys.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Внимание!");
            alert.setHeaderText("Не выбрано агентство.");
            alert.setContentText("Выберите агентство, пожалуйста.");

            alert.showAndWait();
        } else showDealsView();
    }

    private void showDealsView() {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("view/DealsView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Список сделок");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            DealsViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainController(this);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showRealEstateView() {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("view/RealEstateView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Список объектов недвижимости");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            RealEstateViewController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainController(this);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LabList<RealEstate> getRealEstateData() {
        return agencys.getSelectionModel().getSelectedItem().getEstates();
    }

    public void setRealEstateData(LabList<RealEstate> estate) {
        agencys.getSelectionModel().getSelectedItem().setEstates(estate);
        agencys.getSelectionModel().getSelectedItem().setHouseCount(estate.size());
    }

    public void setDealsData(LabList<Deal> deals) {
        agencys.getSelectionModel().getSelectedItem().setDeals(deals);
        agencys.getSelectionModel().getSelectedItem().setDealCount(deals.size());
    }

    public LabList<Deal> getDealsData() {
        return agencys.getSelectionModel().getSelectedItem().getDeals();
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
            realEstateObservableList = FXCollections.observableArrayList();
            for (int i = 0; i < agency.getEstates().size(); i++) {
                realEstateObservableList.add(agency.getEstates().get(i));
            }
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


