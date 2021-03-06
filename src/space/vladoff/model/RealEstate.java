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

import java.time.LocalDate;

public class RealEstate {
    private ObjectProperty<LocalDate> dateOfRecord;
    private IntegerProperty objectNumber;
    private ObjectProperty<Person> owner;
    private IntegerProperty roomCount;
    private ObjectProperty<Adress> adress;
    private ObjectProperty<MaterialType> objectMaterial;
    private IntegerProperty floor;
    private DoubleProperty area;
    private ObjectProperty<BalconyProperty> balcony;
    private ObjectProperty<RoomType> roomType;

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
}
