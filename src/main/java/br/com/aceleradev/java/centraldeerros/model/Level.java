package br.com.aceleradev.java.centraldeerros.model;

public enum Level {
    ERROR("error"), WARNING("warning"), INFO("info");

    private String level;

    Level(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public static Level find(String value) {
        for (Level level : Level.values()) {
            if(value.equalsIgnoreCase((level.level)))
                return level;
        }
        return null;
    }
}
