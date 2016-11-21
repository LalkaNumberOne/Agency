package space.vladoff.model;

import java.util.ArrayList;
import javafx.beans.property.*;

/**
 * Created by Vladislav Russinovich on 07.10.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class RealEstateAgency {
    private StringProperty agencyName;
    private StringProperty licenseNumber;
    private ObjectProperty<Requisite> requisite;
    private DoubleProperty billSum;
    private IntegerProperty houseCount;
    private ArrayList<RealEstate> estates;
    private IntegerProperty dealCount;
    private ArrayList<Deal> deals;
    private IntegerProperty rateOfReturn;

    public RealEstateAgency(String agencyName, String licenseNumber, Requisite requisite, double billSum, ArrayList<RealEstate> estates, ArrayList<Deal> deals, int rateOfReturn) {
        this.agencyName = new SimpleStringProperty(agencyName);
        this.licenseNumber = new SimpleStringProperty(licenseNumber);
        this.requisite = new SimpleObjectProperty<>((requisite!=null) ? requisite : new Requisite());
        this.billSum = new SimpleDoubleProperty(billSum);
        this.estates = (estates!=null)? estates: new ArrayList<>(0);
        try {
            this.houseCount = new SimpleIntegerProperty(estates.size());
        } catch (NullPointerException e) { this.houseCount = new SimpleIntegerProperty(0); }
        this.deals = (deals!=null)? deals: new ArrayList<>(0);
        try {
            this.dealCount = new SimpleIntegerProperty(deals.size());
        } catch (NullPointerException e) { this.dealCount = new SimpleIntegerProperty(0); }
        this.rateOfReturn = new SimpleIntegerProperty(rateOfReturn);
    }

    public RealEstateAgency()
    {
        this(null,null,null,0,null,null,0);
    }
    public RealEstateAgency(String agencyName)
    {
        this(agencyName,null,null,0,null,null,0);
    }

    public String getAgencyName() {
        return agencyName.get();
    }

    public StringProperty agencyNameProperty() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName.set(agencyName);
    }

    public String getLicenseNumber() {
        return licenseNumber.get();
    }

    public StringProperty licenseNumberProperty() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber.set(licenseNumber);
    }

    public Requisite getRequisite() {
        return requisite.get();
    }

    public ObjectProperty<Requisite> requisiteProperty() {
        return requisite;
    }

    public void setRequisite(Requisite requisite) {
        this.requisite.set(requisite);
    }

    public double getBillSum() {
        return billSum.get();
    }

    public DoubleProperty billSumProperty() {
        return billSum;
    }

    public void setBillSum(double billSum) {
        this.billSum.set(billSum);
    }

    public int getHouseCount() {
        return houseCount.get();
    }

    public IntegerProperty houseCountProperty() {
        return houseCount;
    }

    public void setHouseCount(int houseCount) {
        this.houseCount.set(houseCount);
    }

    public ArrayList<RealEstate> getEstates() {
        return estates;
    }

    public void setEstates(ArrayList<RealEstate> estates) {
        this.estates = estates;
    }

    public int getDealCount() {
        return dealCount.get();
    }

    public IntegerProperty dealCountProperty() {
        return dealCount;
    }

    public void setDealCount(int dealCount) {
        this.dealCount.set(dealCount);
    }

    public ArrayList<Deal> getDeals() {
        return deals;
    }

    public void setDeals(ArrayList<Deal> deals) {
        this.deals = deals;
    }

    public int getRateOfReturn() {
        return rateOfReturn.get();
    }

    public IntegerProperty rateOfReturnProperty() {
        return rateOfReturn;
    }

    public void setRateOfReturn(int rateOfReturn) {
        this.rateOfReturn.set(rateOfReturn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RealEstateAgency that = (RealEstateAgency) o;

        if (getAgencyName() != null ? !getAgencyName().equals(that.getAgencyName()) : that.getAgencyName() != null)
            return false;
        if (getLicenseNumber() != null ? !getLicenseNumber().equals(that.getLicenseNumber()) : that.getLicenseNumber() != null)
            return false;
        if (!getRequisite().equals(that.getRequisite())) return false;
        if (getBillSum() != that.getBillSum()) return false;
        if (getHouseCount()!= that.getHouseCount()) return false;
        if (!getEstates().equals(that.getEstates())) return false;
        if (getDealCount()!=that.getDealCount()) return false;
        if (!getDeals().equals(that.getDeals())) return false;
        return getRateOfReturn()==that.getRateOfReturn();

    }

    @Override
    public int hashCode() {
        int result = getAgencyName() != null ? getAgencyName().hashCode() : 0;
        result = 31 * result + (getLicenseNumber() != null ? getLicenseNumber().hashCode() : 0);
        result = 31 * result + getRequisite().hashCode();
        result = 31 * result + new Double(getBillSum()).hashCode();
        result = 31 * result + new Integer(getHouseCount()).hashCode();
        result = 31 * result + getEstates().hashCode();
        result = 31 * result + new Integer(getDealCount()).hashCode();
        result = 31 * result + getDeals().hashCode();
        result = 31 * result + new Integer(getRateOfReturn()).hashCode();
        return result;
    }


    @Override
    public String toString() {
        return getAgencyName();
    }
}
