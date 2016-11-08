package space.vladoff.model;

import java.time.LocalDate;

/**
 * Created by Vladislav Russinovich on 08.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class CommercialRealEstate extends RealEstate {
    public CommercialRealEstate(LocalDate dateOfRecord, int objectNumber, Person owner, RealEstateType objectType, int roomCount, Adress adress, MaterialType objectMaterial, int floor, double area, boolean balcony, boolean isIsolate, PlanningType plan) {
        super(dateOfRecord, objectNumber, owner, objectType, roomCount, adress, objectMaterial, floor, area, balcony, isIsolate, plan);
    }

    public CommercialRealEstate() {
    }
}
