package space.vladoff.model;

/**
 * Created by Vladislav Russinovich on 08.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public interface Visitor {
    void visit(Flat patient);

    void visit(House patient);

    void visit(Cottage patient);
}
