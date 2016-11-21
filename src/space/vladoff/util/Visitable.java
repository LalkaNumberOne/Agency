package space.vladoff.util;

/**
 * Created by Vladislav Russinovich on 13.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public interface Visitable {
    public void accept(Visitor v);
}
