package space.vladoff.model.enums;

/**
 * Created by Vladislav Russinovich on 17.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public enum Inventory {
    full {
        public String toString() {
            return "Полный комплект";
        }
    },
    partial {
        public String toString() {
            return "Частично";
        }
    },
    noInv {
        public String toString() {
            return "Нет";
        }
    }
}
