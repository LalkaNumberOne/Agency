package space.vladoff.model;

/**
 * Created by Vladislav Russinovich on 08.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class Cottage extends RealEstate {

    public void accept(Visitor v) {
        v.visit(this);
    }
}
