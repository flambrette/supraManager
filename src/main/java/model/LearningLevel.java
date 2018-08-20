package model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LearningLevel {
    N("N"),
    M("M"),
    B("B");

    private String value;

    LearningLevel(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    public static LearningLevel fromString(String text) {
        for (LearningLevel ll : LearningLevel.values()) {
            if (ll.value.equalsIgnoreCase(text)) {
                return ll;
            }
        }
        throw new IllegalArgumentException("No constant with value " + text + " found");
    }

    @Override
    public String toString() {
        return value;
    }
}
