package space.vladoff.model;

/**
 * Created by Vladislav Russinovich on 09.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
//TODO: Implement visitor for PlanningType in TableView
public class PlanningTypeVisitor implements Visitor {
    @Override
    public void visit(Flat patient) {

    }

    @Override
    public void visit(House patient) {

    }

    @Override
    public void visit(Cottage patient) {

    }
}
