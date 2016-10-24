package mainPackage.Model; /**
 * Created by Vladislav Russinovich on 07.10.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;

enum RealEstateType {
    apartment {
        public String toString(){
            return "Apartment";
        }
    },
    house {
        public String toString(){
            return "House";
        }
    },
    cottage {
        public String toString(){
            return "Cottage";
        }
    },
    unknown {
        public String toString() {return "Unknown";}
    }
}

enum MaterialType {
    brick {
        public String toString(){
            return "Brick";
        }
    },
    pane {
        public String toString(){
            return "Pane";
        }
    },
    unknown {
        public String toString() {return "Unknown";}
    }
}

enum PlanningType {
    khrushevPlanning {
        public String toString() {
            return "Khrushev Planning";
        }
    },

    fullPlanning {
        public String toString(){
            return "Full Planning";
        }
    },

    improvedPlanning {
        public String toString(){
            return "Improved Planning";
        }
    },
    unknown {
        public String toString() {return "Unknown";}
    }

}

public class RealEstate {
    private ObjectProperty<LocalDate> dateOfRecord;
    private IntegerProperty objectNumber;
    private ObjectProperty<Person> owner;
    private RealEstateType objectType;
    private IntegerProperty roomCount;
    private ObjectProperty<Adress> adress;
    private MaterialType objectMaterial;
    private IntegerProperty floor;
    private DoubleProperty area;
    private boolean balcony;
    private boolean isIsolate;
    private PlanningType plan;

    public RealEstate(LocalDate dateOfRecord, int objectNumber, Person owner,
                      RealEstateType objectType, int roomCount, Adress adress,
                      MaterialType objectMaterial, int floor, double area,
                      boolean balcony, boolean isIsolate, PlanningType plan) {
        this.dateOfRecord = new SimpleObjectProperty<>((dateOfRecord!=null) ? dateOfRecord : LocalDate.now());
        this.objectNumber = new SimpleIntegerProperty(objectNumber);
        this.owner = new SimpleObjectProperty<>((owner!=null) ? owner : new Person());
        this.objectType = objectType;
        this.roomCount = new SimpleIntegerProperty(roomCount);
        this.adress = new SimpleObjectProperty<>((adress!=null)? adress: new Adress());
        this.objectMaterial = objectMaterial;
        this.floor = new SimpleIntegerProperty(floor);
        this.area = new SimpleDoubleProperty(area);
        this.balcony = balcony;
        this.isIsolate = isIsolate;
        this.plan = plan;
    }

    public RealEstate()
    {
        this(null,0,null,RealEstateType.unknown,0,null,MaterialType.unknown,0,0,false,false,PlanningType.unknown);
    }

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

    public RealEstateType getObjectType() {
        return objectType;
    }

    public void setObjectType(RealEstateType objectType) {
        this.objectType = objectType;
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
        return objectMaterial;
    }

    public void setObjectMaterial(MaterialType objectMaterial) {
        this.objectMaterial = objectMaterial;
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

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public boolean isolate() {
        return isIsolate;
    }

    public void setIsolate(boolean isolate) {
        isIsolate = isolate;
    }

    public PlanningType getPlan() {
        return plan;
    }

    public void setPlan(PlanningType plan) {
        this.plan = plan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RealEstate that = (RealEstate) o;

        if (isBalcony() != that.isBalcony()) return false;
        if (isIsolate != that.isIsolate) return false;
        if (getDateOfRecord() != null ? !getDateOfRecord().equals(that.getDateOfRecord()) : that.getDateOfRecord() != null)
            return false;
        if (getObjectNumber()!=that.getObjectNumber()) return false;
        if (getOwner() != null ? !getOwner().equals(that.getOwner()) : that.getOwner() != null) return false;
        if (getObjectType() != that.getObjectType()) return false;
        if (getRoomCount()!=that.getRoomCount()) return false;
        if (getAdress() != null ? !getAdress().equals(that.getAdress()) : that.getAdress() != null) return false;
        if (getObjectMaterial() != that.getObjectMaterial()) return false;
        if (getFloor()!=that.getFloor()) return false;
        if (getArea()!=that.getArea()) return false;
        return getPlan() == that.getPlan();

    }

    @Override
    public int hashCode() {
        int result = getDateOfRecord() != null ? getDateOfRecord().hashCode() : 0;
        result = 31 * result + new Integer(getObjectNumber()).hashCode();
        result = 31 * result + (getOwner() != null ? getOwner().hashCode() : 0);
        result = 31 * result + (getObjectType() != null ? getObjectType().hashCode() : 0);
        result = 31 * result + new Integer(getRoomCount()).hashCode();
        result = 31 * result + (getAdress() != null ? getAdress().hashCode() : 0);
        result = 31 * result + (getObjectMaterial() != null ? getObjectMaterial().hashCode() : 0);
        result = 31 * result + new Integer(getFloor()).hashCode();
        result = 31 * result + new Double(getArea()).hashCode();
        result = 31 * result + (isBalcony() ? 1 : 0);
        result = 31 * result + (isIsolate ? 1 : 0);
        result = 31 * result + (getPlan() != null ? getPlan().hashCode() : 0);
        return result;
    }
}
