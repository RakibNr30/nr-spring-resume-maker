package com.resume.constant;

public enum Proficiency {
    BASIC(1, "Basic"), INTERMEDIATE(2, "Intermediate"), ADVANCED(3, "Advanced");

    private final String label;

    private final int value;

    Proficiency(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }

    public static String getLabelByValue(int value) {
        for (Proficiency proficiency : Proficiency.values()) {
            if (proficiency.value == value) {
                return proficiency.label;
            }
        }

        return "";
    }
}
