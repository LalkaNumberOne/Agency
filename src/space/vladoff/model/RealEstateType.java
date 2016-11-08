package space.vladoff.model;

/**
 * Created by Vladislav Russinovich on 08.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
enum RealEstateType {
    apartment {
        public String toString(){
            return "Apartment";
        }
    },
    house {
        public String toString(){
            return "House";
        }
    },
    cottage {
        public String toString(){
            return "Cottage";
        }
    },
    unknown {
        public String toString() {return "Unknown";}
    }
}
