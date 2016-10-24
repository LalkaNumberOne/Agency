package mainPackage.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Created by Vladislav Russinovich on 07.10.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class Requisite {
    private StringProperty INN; //12 цифр
    private StringProperty KPP; //8-9 цифр
    private StringProperty bankName;
    private StringProperty BIK; //9 цифр
    private StringProperty account; //20 цифр

    public Requisite(String INN, String KPP, String bankName, String BIK, String account) {
        this.INN = new SimpleStringProperty(INN);
        this.KPP = new SimpleStringProperty(KPP);
        this.bankName = new SimpleStringProperty(bankName);
        this.BIK = new SimpleStringProperty(BIK);
        this.account = new SimpleStringProperty(account);
    }

    public Requisite() {
        this(null, null, null, null, null);
    }

    public String getINN() {
        return INN.get();
    }

    public StringProperty INNProperty() {
        return INN;
    }

    public void setINN(String INN) {
        this.INN.set(INN);
    }

    public String getKPP() {
        return KPP.get();
    }

    public StringProperty KPPProperty() {
        return KPP;
    }

    public void setKPP(String KPP) {
        this.KPP.set(KPP);
    }

    public String getBankName() {
        return bankName.get();
    }

    public StringProperty bankNameProperty() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName.set(bankName);
    }

    public String getBIK() {
        return BIK.get();
    }

    public StringProperty BIKProperty() {
        return BIK;
    }

    public void setBIK(String BIK) {
        this.BIK.set(BIK);
    }

    public String getAccount() {
        return account.get();
    }

    public StringProperty accountProperty() {
        return account;
    }

    public void setAccount(String account) {
        this.account.set(account);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Requisite requisite = (Requisite) o;

        if (INN != null ? !INN.equals(requisite.INN) : requisite.INN != null) return false;
        if (KPP != null ? !KPP.equals(requisite.KPP) : requisite.KPP != null) return false;
        if (bankName != null ? !bankName.equals(requisite.bankName) : requisite.bankName != null) return false;
        if (BIK != null ? !BIK.equals(requisite.BIK) : requisite.BIK != null) return false;
        return account != null ? account.equals(requisite.account) : requisite.account == null;

    }

    @Override
    public int hashCode() {
        int result = INN != null ? INN.hashCode() : 0;
        result = 31 * result + (KPP != null ? KPP.hashCode() : 0);
        result = 31 * result + (bankName != null ? bankName.hashCode() : 0);
        result = 31 * result + (BIK != null ? BIK.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        return result;
    }
}

