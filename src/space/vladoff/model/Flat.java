package space.vladoff.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import space.vladoff.model.enums.BalconyProperty;
import space.vladoff.model.enums.MaterialType;
import space.vladoff.model.enums.PlanningType;
import space.vladoff.model.enums.RoomType;
import space.vladoff.util.FormVisitor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

/**
 * Created by Vladislav Russinovich on 08.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class Flat extends RealEstate {
    private transient ObjectProperty<PlanningType> plan;


    public Flat(LocalDate dateOfRecord, int objectNumber, Person owner, int roomCount, Adress adress, MaterialType objectMaterial, int floor, double area, BalconyProperty balconyProperty, RoomType roomType, PlanningType plan) {
        super(dateOfRecord, owner, roomCount, adress, objectMaterial, floor, area, balconyProperty, roomType);
        this.plan = new SimpleObjectProperty<>(plan);
    }

    public Flat(PlanningType plan) {
        this.plan = new SimpleObjectProperty<>(plan);
    }

    public Flat() {
        this.plan = new SimpleObjectProperty<>(PlanningType.unknown);
    }

    public void accept(FormVisitor v) {
        v.visit(this);
    }

    public PlanningType getPlan() {
        return plan.get();
    }

    public ObjectProperty<PlanningType> planProperty() {
        return plan;
    }

    public void setPlan(PlanningType plan) {
        this.plan.set(plan);
    }

    @Override
    public String toString() {
        return "Квартира";
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();

        oos.writeInt(getPlan().ordinal());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();

        this.plan = new SimpleObjectProperty<>(PlanningType.values()[ois.readInt()]);
    }
}
