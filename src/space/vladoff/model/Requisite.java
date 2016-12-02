package space.vladoff.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;

/**
 * Created by Vladislav Russinovich on 07.10.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class Requisite implements Serializable {
    private transient StringProperty INN; //12 цифр
    private transient StringProperty KPP; //8-9 цифр
    private transient StringProperty bankName;
    private transient StringProperty BIK; //9 цифр
    private transient StringProperty account; //20 цифр

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

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(getINN());
        oos.writeObject(getKPP());
        oos.writeObject(getBankName());
        oos.writeObject(getBIK());
        oos.writeObject(getAccount());
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        INN = new SimpleStringProperty((String) ois.readObject());
        KPP = new SimpleStringProperty((String) ois.readObject());
        bankName = new SimpleStringProperty((String) ois.readObject());
        BIK = new SimpleStringProperty((String) ois.readObject());
        account = new SimpleStringProperty((String) ois.readObject());
    }
}

