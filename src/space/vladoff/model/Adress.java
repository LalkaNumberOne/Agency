package space.vladoff.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * Created by Vladislav Russinovich on 07.10.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class Adress {
    private StringProperty city;
    private StringProperty street;
    private StringProperty buildNo;
    private StringProperty apartNo;

    public Adress(String city, String street, String buildNo, String apartNo) {
        this.city = new SimpleStringProperty(city);
        this.street = new SimpleStringProperty(street);
        this.buildNo = new SimpleStringProperty (buildNo);
        this.apartNo = new SimpleStringProperty(apartNo);
    }

    public Adress()
    {
        this(null,null,null,null);
    }

    public StringProperty getCity() {

        return city;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getBuildNo() {
        return buildNo.get();
    }

    public StringProperty buildNoProperty() {
        return buildNo;
    }

    public void setBuildNo(String buildNo) {
        this.buildNo.set(buildNo);
    }

    public String getApartNo() {
        return apartNo.get();
    }

    public StringProperty apartNoProperty() {
        return apartNo;
    }

    public void setApartNo(String apartNo) {
        this.apartNo.set(apartNo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Adress adress = (Adress) o;

        if (getCity() != null ? !getCity().equals(adress.getCity()) : adress.getCity() != null) return false;
        if (getStreet() != null ? !getStreet().equals(adress.getStreet()) : adress.getStreet() != null) return false;
        if (getBuildNo() != null ? !getBuildNo().equals(adress.getBuildNo()) : adress.getBuildNo() != null)
            return false;
        return getApartNo() != null ? getApartNo().equals(adress.getApartNo()) : adress.getApartNo() == null;

    }

    @Override
    public int hashCode() {
        int result = getCity() != null ? getCity().hashCode() : 0;
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getBuildNo() != null ? getBuildNo().hashCode() : 0);
        result = 31 * result + (getApartNo() != null ? getApartNo().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCity());
        sb.append("\n");
        sb.append(getStreet());
        sb.append("\n");
        sb.append(getBuildNo());
        sb.append(", ");
        sb.append(getApartNo());
        return sb.toString();
    }
}
