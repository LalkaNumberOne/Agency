package space.vladoff.model;

/**
 * Created by Vladislav Russinovich on 08.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public enum PlanningType {
    khrushevPlanning {
        public String toString() {
            return "Khrushev Planning";
        }
    },

    fullPlanning {
        public String toString(){
            return "Full Planning";
        }
    },

    improvedPlanning {
        public String toString(){
            return "Improved Planning";
        }
    },
    unknown {
        public String toString() {return "Unknown";}
    }

}
