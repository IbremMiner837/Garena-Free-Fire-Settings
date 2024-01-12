package com.jvmfrogsquad.ffsettings.model;

public class NoNameModel {
    private String type, name, description, stringValue;
    private int intValue, intMinValue, intMaxValue, intStepSize;
    private float floatValue, floatMinValue, floatMaxValue, floatStepSize;
    private boolean booleanValue;

    public NoNameModel(String type, String name, String description, String stringValue, int intValue, int intMinValue, int intMaxValue, int intStepSize, float floatValue, float floatMinValue, float floatMaxValue, float floatStepSize, boolean booleanValue) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.stringValue = stringValue;
        this.intValue = intValue;
        this.intMinValue = intMinValue;
        this.intMaxValue = intMaxValue;
        this.intStepSize = intStepSize;
        this.floatValue = floatValue;
        this.floatMinValue = floatMinValue;
        this.floatMaxValue = floatMaxValue;
        this.floatStepSize = floatStepSize;
        this.booleanValue = booleanValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public int getIntMinValue() {
        return intMinValue;
    }

    public void setIntMinValue(int intMinValue) {
        this.intMinValue = intMinValue;
    }

    public int getIntMaxValue() {
        return intMaxValue;
    }

    public void setIntMaxValue(int intMaxValue) {
        this.intMaxValue = intMaxValue;
    }

    public int getIntStepSize() {
        return intStepSize;
    }

    public void setIntStepSize(int intStepSize) {
        this.intStepSize = intStepSize;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    public float getFloatMinValue() {
        return floatMinValue;
    }

    public void setFloatMinValue(float floatMinValue) {
        this.floatMinValue = floatMinValue;
    }

    public float getFloatMaxValue() {
        return floatMaxValue;
    }

    public void setFloatMaxValue(float floatMaxValue) {
        this.floatMaxValue = floatMaxValue;
    }

    public float getFloatStepSize() {
        return floatStepSize;
    }

    public void setFloatStepSize(float floatStepSize) {
        this.floatStepSize = floatStepSize;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }
}
