package space.vladoff.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import space.vladoff.model.enums.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

/**
 * Created by Vladislav Russinovich on 08.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class Cottage extends RealEstate {

    private transient ObjectProperty<GarageProperty> garage;
    private transient ObjectProperty<Pool> pool;

    public Cottage(LocalDate dateOfRecord, Person owner, int roomCount, Adress adress,
                   MaterialType objectMaterial, int floor, double area,
                   BalconyProperty balconyProperty, RoomType roomType,
                   GarageProperty garage, Pool pool) {

        super(dateOfRecord, owner, roomCount, adress, objectMaterial, floor, area, balconyProperty, roomType);
        this.garage = new SimpleObjectProperty<>(garage);
        this.pool = new SimpleObjectProperty<>(pool);
    }

    public Cottage(GarageProperty garage, Pool pool) {
        this.garage = new SimpleObjectProperty<>(garage);
        this.pool = new SimpleObjectProperty<>(pool);
    }

    public Cottage() {
        this.garage = new SimpleObjectProperty<>(GarageProperty.noGarage);
        this.pool = new SimpleObjectProperty<>(Pool.no);
    }

    public GarageProperty getGarage() {
        return garage.get();
    }

    public void setGarage(GarageProperty garage) {
        this.garage.setValue(garage);
    }

    public Pool getPool() {
        return pool.get();
    }

    public void setPool(Pool pool) {
        this.pool.setValue(pool);
    }

    public ObjectProperty<GarageProperty> garageProperty() {
        return this.garage;
    }

    public ObjectProperty<Pool> poolProperty() {
        return this.pool;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Cottage cottage = (Cottage) o;

        if (getGarage() != cottage.getGarage()) return false;
        return getPool() == cottage.getPool();

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getGarage() != null ? getGarage().hashCode() : 0);
        result = 31 * result + (getPool() != null ? getPool().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Коттедж";
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();

        oos.writeInt(getGarage().ordinal());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();

        this.garage = new SimpleObjectProperty<>(GarageProperty.values()[ois.readInt()]);
        this.pool = new SimpleObjectProperty<>(Pool.values()[ois.readInt()]);
    }
}
