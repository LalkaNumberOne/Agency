package space.vladoff.model;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import space.vladoff.model.enums.*;

import java.time.LocalDate;

/**
 * Created by Vladislav Russinovich on 08.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class House extends RealEstate {
    private ObjectProperty<ElectricityProperty> electricity;
    private ObjectProperty<GarageProperty> garage;
    private ObjectProperty<Inventory> inventory;

    public House(LocalDate dateOfRecord, Person owner, int roomCount, Adress adress,
                 MaterialType objectMaterial, int floor, double area,
                 BalconyProperty balconyProperty, RoomType roomType,
                 ElectricityProperty electricity, GarageProperty garage, Inventory inventory) {

        super(dateOfRecord, owner, roomCount, adress, objectMaterial, floor, area, balconyProperty, roomType);
        this.electricity = new SimpleObjectProperty<>(electricity);
        this.garage = new SimpleObjectProperty<>(garage);
        this.inventory = new SimpleObjectProperty<>(inventory);
    }

    public House(ElectricityProperty electricity, GarageProperty garage, Inventory inventory) {
        this.electricity = new SimpleObjectProperty<>(electricity);
        this.garage = new SimpleObjectProperty<>(garage);
        this.inventory = new SimpleObjectProperty<>(inventory);
    }

    public House() {
        this.electricity = new SimpleObjectProperty<>(ElectricityProperty.no);
        this.garage = new SimpleObjectProperty<>(GarageProperty.noGarage);
        this.inventory = new SimpleObjectProperty<>(Inventory.noInv);
    }

    public ElectricityProperty getElectricity() {
        return electricity.get();
    }

    public void setElectricity(ElectricityProperty electricity) {
        this.electricity.setValue(electricity);
    }

    public GarageProperty getGarage() {
        return garage.get();
    }

    public void setGarage(GarageProperty garage) {
        this.garage.setValue(garage);
    }

    public Inventory getInventory() {
        return inventory.get();
    }

    public void setInventory(Inventory inventory) {
        this.inventory.setValue(inventory);
    }

    public ObjectProperty<Inventory> inventoryProperty() {
        return inventory;
    }

    public ObjectProperty<GarageProperty> garageProperty() {
        return garage;
    }

    public ObjectProperty<ElectricityProperty> electricityProperty() {
        return electricity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        House house = (House) o;

        if (getElectricity() != house.getElectricity()) return false;
        if (getGarage() != house.getGarage()) return false;
        return getInventory() == house.getInventory();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getElectricity() != null ? getElectricity().hashCode() : 0);
        result = 31 * result + (getGarage() != null ? getGarage().hashCode() : 0);
        result = 31 * result + (getInventory() != null ? getInventory().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Дача";
    }
}
