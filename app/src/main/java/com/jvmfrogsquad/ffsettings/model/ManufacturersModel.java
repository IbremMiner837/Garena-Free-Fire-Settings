package com.jvmfrogsquad.ffsettings.model;

import androidx.annotation.Keep;

@Keep
public class ManufacturersModel {
    String name, model;
    Boolean showInProductionApp, isAvailable;

    public ManufacturersModel() {};

    public ManufacturersModel(String name, String model, Boolean showInProductionApp, Boolean isAvailable) {
        this.name = name;
        this.model = model;
        this.showInProductionApp = showInProductionApp;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getShowInProductionApp() {
        return showInProductionApp;
    }

    public void setShowInProductionApp(Boolean showInProductionApp) {
        this.showInProductionApp = showInProductionApp;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
