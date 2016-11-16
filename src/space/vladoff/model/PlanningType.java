package space.vladoff.model;

/**
 * Created by Vladislav Russinovich on 08.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public enum PlanningType {
    khrushevPlanning {
        public String toString() {
            return "Хрущевка";
        }
    },

    fullPlanning {
        public String toString(){
            return "Полноценная";
        }
    },

    improvedPlanning {
        public String toString(){
            return "Улучшенная";
        }
    },
    unknown {
        public String toString() {
            return "Другая";
        }
    }

}
