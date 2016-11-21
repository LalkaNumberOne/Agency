package space.vladoff.model.enums;

/**
 * Created by Vladislav Russinovich on 17.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public enum ElectricityProperty {
    yes {
        public String toString() {
            return "Есть";
        }
    },
    no {
        public String toString() {
            return "Нет";
        }
    }
}
