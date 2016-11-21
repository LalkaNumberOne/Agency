package space.vladoff.model.enums;

/**
 * Created by Vladislav Russinovich on 17.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */

/*
ЗДЕСЬ ПРОДАЮТСЯ ГАРАЖИ!!!!
ЗДЕСЬ ПРОДАЮТСЯ ГАРАЖИ!!!!
 */
public enum GarageProperty {
    garage {
        public String toString() {
            return "1 гараж";
        }
    },
    twoGarages {
        public String toString() {
            return "2 гаража";
        }
    },
    noGarage {
        public String toString() {
            return "Нет";
        }
    }
}

//ПРОДАМ ГАРАЖ!
