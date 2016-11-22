package space.vladoff;
/**
 * Created by Vladislav Russinovich on 20.10.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */

import java.io.IOException;

import space.vladoff.model.*;

import java.util.ArrayList;

import javafx.scene.layout.AnchorPane;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import space.vladoff.model.enums.BalconyProperty;
import space.vladoff.model.enums.MaterialType;
import space.vladoff.model.enums.PlanningType;
import space.vladoff.util.LabList;
import space.vladoff.view.AgencyBrowseViewController;

public class MainController extends Application {

    private ObservableList<RealEstateAgency> agencyData = FXCollections.observableArrayList();
    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Агентство недвижимости");

        initRootLayout();
    }

    /**
     * Инициализирует корневой макет.
     */
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("view/AgencyBrowseView.fxml"));
            rootLayout = loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            AgencyBrowseViewController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
            // Даём контроллеру доступ к главному приложению.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @return gives us primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public MainController() {
        LabList<RealEstate> simpleList = new LabList<>();
        //simplelist.add(new House(null, new Person("Иван", "Иванов", "Иванович"), 5, new Adress("Новосибирск", "Ленина", "1", "12"), MaterialType.monolit,2,103.2, BalconyProperty.loggi,RoomType.));
        //simplelist.add(new House())
        simpleList.add(new Flat(PlanningType.improvedPlanning));
        simpleList.add(new Flat(PlanningType.khrushevPlanning));
        agencyData.add(new RealEstateAgency("Котлетка"));
        agencyData.add(new RealEstateAgency("Мир ковров"));
        agencyData.add(new RealEstateAgency("Сбербанк"));
        agencyData.add(new RealEstateAgency("Шляпа"));
        RealEstateAgency real = new RealEstateAgency("Левобережное", "32564342443",
                new Requisite("7728168971", "775001001", "Альфа-банк", "4434682008452", "543234880085043852"),
                2500.46, simpleList, null, 120);
        agencyData.add(real);
    }

    /**
     * Возвращает данные в виде наблюдаемого списка адресатов.
     *
     * @return gives us observable list of Real Estate Agency
     */
    public ObservableList<RealEstateAgency> getAgencyData() {
        return agencyData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}