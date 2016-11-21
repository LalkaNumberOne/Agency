package space.vladoff.model.enums;

/**
 * Created by Vladislav Russinovich on 17.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public enum BalconyProperty {
    balcony {
        public String toString() {
            return "Балкон";
        }
    },
    loggi {
        public String toString() {
            return "Лоджия";
        }
    },
    noBalcony {
        public String toString() {
            return "Нет";
        }
    }
}
