package space.vladoff.model;
/**
 * Created by Vladislav Russinovich on 07.10.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
import javafx.beans.property.*;
import space.vladoff.model.enums.BalconyProperty;
import space.vladoff.model.enums.MaterialType;
import space.vladoff.model.enums.RoomType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public abstract class RealEstate implements Serializable {
    private transient ObjectProperty<LocalDate> dateOfRecord;
    private transient IntegerProperty objectNumber;
    private transient ObjectProperty<Person> owner;
    private transient IntegerProperty roomCount;
    private transient ObjectProperty<Adress> adress;
    private transient ObjectProperty<MaterialType> objectMaterial;
    private transient IntegerProperty floor;
    private transient DoubleProperty area;
    private transient ObjectProperty<BalconyProperty> balcony;
    private transient ObjectProperty<RoomType> roomType;

    public RealEstate(LocalDate dateOfRecord, Person owner, int roomCount, Adress adress,
                      MaterialType objectMaterial, int floor, double area, BalconyProperty balconyProperty, RoomType roomType) {
        this.dateOfRecord = new SimpleObjectProperty<>((dateOfRecord!=null) ? dateOfRecord : LocalDate.now());
        this.owner = new SimpleObjectProperty<>((owner!=null) ? owner : new Person());
        this.roomCount = new SimpleIntegerProperty(roomCount);
        this.adress = new SimpleObjectProperty<>((adress!=null)? adress: new Adress());
        this.objectMaterial = new SimpleObjectProperty<>(objectMaterial);
        this.floor = new SimpleIntegerProperty(floor);
        this.area = new SimpleDoubleProperty(area);
        this.balcony = new SimpleObjectProperty<>(balconyProperty);
        this.roomType = new SimpleObjectProperty<>(roomType);
        this.objectNumber = new SimpleIntegerProperty(0);
    }

    public RealEstate()
    {
        this(null, null, 0, null, MaterialType.unknown, 0, 0, BalconyProperty.noBalcony, RoomType.other);
    }

    /**
     * Getters and Setters
     */

    /**
     * @return gives date of record in LocalDate type
     */
    public LocalDate getDateOfRecord() {
        return dateOfRecord.get();
    }

    public ObjectProperty<LocalDate> dateOfRecordProperty() {
        return dateOfRecord;
    }

    public void setDateOfRecord(LocalDate dateOfRecord) {
        this.dateOfRecord.set(dateOfRecord);
    }

    public int getObjectNumber() {
        return objectNumber.get();
    }

    public IntegerProperty objectNumberProperty() {
        return objectNumber;
    }

    public void setObjectNumber(int objectNumber) {
        this.objectNumber.set(objectNumber);
    }

    public Person getOwner() {
        return owner.get();
    }

    public ObjectProperty<Person> ownerProperty() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner.set(owner);
    }

    public int getRoomCount() {
        return roomCount.get();
    }

    public IntegerProperty roomCountProperty() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount.set(roomCount);
    }

    public Adress getAdress() {
        return adress.get();
    }

    public ObjectProperty<Adress> adressProperty() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress.set(adress);
    }

    public MaterialType getObjectMaterial() {
        return objectMaterial.get();
    }

    public ObjectProperty<MaterialType> objectMaterialProperty() {
        return objectMaterial;
    }

    public void setObjectMaterial(MaterialType objectMaterial) {
        this.objectMaterial.set(objectMaterial);
    }

    public int getFloor() {
        return floor.get();
    }

    public IntegerProperty floorProperty() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor.set(floor);
    }

    public double getArea() {
        return area.get();
    }

    public DoubleProperty areaProperty() {
        return area;
    }

    public void setArea(double area) {
        this.area.set(area);
    }

    public BalconyProperty getBalcony() {
        return balcony.get();
    }

    public ObjectProperty<BalconyProperty> balconyProperty() {
        return balcony;
    }

    public void setBalcony(BalconyProperty balcony) {
        this.balcony.set(balcony);
    }

    public RoomType getRoomType() {
        return roomType.get();
    }

    public ObjectProperty<RoomType> roomTypeProperty() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType.set(roomType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RealEstate that = (RealEstate) o;

        if (getDateOfRecord() != null ? !getDateOfRecord().equals(that.getDateOfRecord()) : that.getDateOfRecord() != null)
            return false;
        if (getObjectNumber()!=that.getObjectNumber()) return false;
        if (getOwner() != null ? !getOwner().equals(that.getOwner()) : that.getOwner() != null) return false;
        if (getRoomCount()!=that.getRoomCount()) return false;
        if (getAdress() != null ? !getAdress().equals(that.getAdress()) : that.getAdress() != null) return false;
        if (getObjectMaterial() != that.getObjectMaterial()) return false;
        if (getBalcony() != that.getBalcony()) return false;
        if (getRoomType() != that.getRoomType()) return false;
        if (getFloor()!=that.getFloor()) return false;
        return  (getArea()==that.getArea());

    }

    @Override
    public int hashCode() {
        int result = getDateOfRecord() != null ? getDateOfRecord().hashCode() : 0;
        result = 31 * result + new Integer(getObjectNumber()).hashCode();
        result = 31 * result + (getOwner() != null ? getOwner().hashCode() : 0);
        result = 31 * result + new Integer(getRoomCount()).hashCode();
        result = 31 * result + (getAdress() != null ? getAdress().hashCode() : 0);
        result = 31 * result + (getObjectMaterial() != null ? getObjectMaterial().hashCode() : 0);
        result = 31 * result + new Integer(getFloor()).hashCode();
        result = 31 * result + new Double(getArea()).hashCode();
        result = 31 * result + (getBalcony() != null ? getBalcony().hashCode() : 0);
        result = 31 * result + (getRoomType() != null ? getRoomType().hashCode() : 0);
        return result;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();

        oos.writeObject(getDateOfRecord());
        oos.writeObject(getOwner());
        oos.writeInt(getRoomCount());
        oos.writeObject(getAdress());
        oos.writeInt(getObjectMaterial().ordinal());
        oos.writeInt(getFloor());
        oos.writeDouble(getArea());
        oos.writeInt(getBalcony().ordinal());
        oos.writeInt(getRoomType().ordinal());
        oos.writeInt(getObjectNumber());
    }

    private void readObject(ObjectInputStream ios) throws IOException, ClassNotFoundException {
        ios.defaultReadObject();
        this.dateOfRecord = new SimpleObjectProperty<>((LocalDate) ios.readObject());
        this.owner = new SimpleObjectProperty<>((Person) ios.readObject());
        this.roomCount = new SimpleIntegerProperty(ios.readInt());
        this.adress = new SimpleObjectProperty<>((Adress) ios.readObject());
        this.objectMaterial = new SimpleObjectProperty<>(MaterialType.values()[ios.readInt()]);
        this.floor = new SimpleIntegerProperty(ios.readInt());
        this.area = new SimpleDoubleProperty(ios.readDouble());
        this.balcony = new SimpleObjectProperty<>(BalconyProperty.values()[ios.readInt()]);
        this.roomType = new SimpleObjectProperty<>(RoomType.values()[ios.readInt()]);
        this.objectNumber = new SimpleIntegerProperty(ios.readInt());
    }
}
