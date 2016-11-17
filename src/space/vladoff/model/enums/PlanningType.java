package space.vladoff.model.enums;

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
            return "Полногабаритная";
        }
    },
    studioPlaninng {
        public String toString() {
            return "Студия";
        }
    },
    lowfamilyPlanning {
        public String toString() {
            return "Малосемейка";
        }
    },
    newPlanning {
        public String toString() {
            return "Новая";
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
