package space.vladoff.model.enums;

/**
 * Created by Vladislav Russinovich on 17.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public enum RoomType {
    isolate {
        public String toString() {
            return "Изолированный";
        }
    },
    adjacent {
        public String toString() {
            return "Смежный";
        }
    },
    other {
        public String toString() {
            return "Другой";
        }
    }
}
