package space.vladoff.model;

/**
 * Created by Vladislav Russinovich on 08.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public enum MaterialType {
    brick {
        public String toString(){
            return "Brick";
        }
    },
    pane {
        public String toString(){
            return "Pane";
        }
    },
    unknown {
        public String toString() {return "Unknown";}
    }
}
