package mainPackage.Model;

import java.time.LocalDate;

import javafx.beans.property.*;
/**
 * Created by Vladislav Russinovich on 07.10.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class Deal {
    private ObjectProperty<LocalDate> dateOfDeal;
    private IntegerProperty objectNumber;
    private ObjectProperty<Person> customer;
    private DoubleProperty cost;
    private DoubleProperty tax;
    private DoubleProperty insurance;

    public Deal(LocalDate dateOfDeal, int objectNumber, Person customer, double cost, double tax, double insurance)
    {
        this.dateOfDeal = (dateOfDeal!=null) ? new SimpleObjectProperty<>(dateOfDeal) : new SimpleObjectProperty<>(LocalDate.now());
        this.objectNumber = new SimpleIntegerProperty(objectNumber);
        this.customer = (customer!=null) ? new SimpleObjectProperty<>(customer) : new SimpleObjectProperty<>(new Person());
        this.cost = new SimpleDoubleProperty(cost);
        this.tax = new SimpleDoubleProperty(tax);
        this.insurance = new SimpleDoubleProperty(insurance);
    }

    public Deal(){
        this(null, 0, null, 0,0,0);
    }

    public LocalDate getDateOfDeal() {
        return dateOfDeal.get();
    }

    public ObjectProperty<LocalDate> dateOfDealProperty() {
        return dateOfDeal;
    }

    public void setDateOfDeal(LocalDate dateOfDeal) {
        this.dateOfDeal.set(dateOfDeal);
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

    public Person getCustomer() {
        return customer.get();
    }

    public ObjectProperty<Person> customerProperty() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer.set(customer);
    }

    public double getCost() {
        return cost.get();
    }

    public DoubleProperty costProperty() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost.set(cost);
    }

    public double getTax() {
        return tax.get();
    }

    public DoubleProperty taxProperty() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax.set(tax);
    }

    public double getInsurance() {
        return insurance.get();
    }

    public DoubleProperty insuranceProperty() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance.set(insurance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deal deal = (Deal) o;

        if (getDateOfDeal() != null ? !getDateOfDeal().equals(deal.getDateOfDeal()) : deal.getDateOfDeal() != null)
            return false;
        if (getObjectNumber()!=deal.getObjectNumber()) return false;
        if (getCustomer() != null ? !getCustomer().equals(deal.getCustomer()) : deal.getCustomer() != null)
            return false;
        if (getCost()!=deal.getCost()) return false;
        if (getTax()!=deal.getTax()) return false;
        return (getInsurance() == deal.getInsurance());

    }

    @Override
    public int hashCode() {
        int result = getDateOfDeal() != null ? getDateOfDeal().hashCode() : 0;
        result = 31 * result + new Integer(getObjectNumber()).hashCode();
        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
        result = 31 * result + new Double(getCost()).hashCode();
        result = 31 * result + new Double(getTax()).hashCode();
        result = 31 * result + new Double(getInsurance()).hashCode();
        return result;
    }
}
