package space.vladoff.model.enums;

/**
 * Created by Vladislav Russinovich on 08.11.2016.
 * NSTU, Faculty of Automation and Computer Engineering, AVT-512
 * Licensed under WTFPL
 */
public enum MaterialType {
    brick {
        public String toString(){
            return "Кирпич";
        }
    },
    pane {
        public String toString(){
            return "Панель";
        }
    },
    monolit {
        public String toString() {
            return "Монолитный";
        }
    },
    unknown {
        public String toString() {
            return "Другой";
        }
    }
}
