package space.vladoff.util;

import space.vladoff.model.Cottage;
import space.vladoff.model.Flat;
import space.vladoff.model.House;

/**
 * Created by Vladislav Russinovich on 13.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public class FormVisitor implements Visitor {
    public Flat visit(Flat p) {
        return p;
    }

    public Cottage visit(Cottage p) {
        return p;
    }

    public House visit(House p) {
        return p;
    }
}
