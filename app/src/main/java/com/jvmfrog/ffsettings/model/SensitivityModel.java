package com.jvmfrog.ffsettings.model;

import androidx.annotation.Keep;

@Keep
public class SensitivityModel {
    public String deviceName, manufacturer, settingsSourceUrl;
    public int dpi, fireButton, review, collimator, x2Scope, x4Scope, sniperScope, freeReview;

    public SensitivityModel(String deviceName, String manufacturer, String settingsSourceUrl, int dpi, int fireButton, int review, int collimator, int x2Scope, int x4Scope, int sniperScope, int freeReview) {
        this.deviceName = deviceName;
        this.manufacturer = manufacturer;
        this.settingsSourceUrl = settingsSourceUrl;
        this.dpi = dpi;
        this.fireButton = fireButton;
        this.review = review;
        this.collimator = collimator;
        this.x2Scope = x2Scope;
        this.x4Scope = x4Scope;
        this.sniperScope = sniperScope;
        this.freeReview = freeReview;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSettingsSourceUrl() {
        return settingsSourceUrl;
    }

    public void setSettingsSourceUrl(String settingsSourceUrl) {
        this.settingsSourceUrl = settingsSourceUrl;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public int getFireButton() {
        return fireButton;
    }

    public void setFireButton(int fireButton) {
        this.fireButton = fireButton;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getCollimator() {
        return collimator;
    }

    public void setCollimator(int collimator) {
        this.collimator = collimator;
    }

    public int getX2Scope() {
        return x2Scope;
    }

    public void setX2Scope(int x2Scope) {
        this.x2Scope = x2Scope;
    }

    public int getX4Scope() {
        return x4Scope;
    }

    public void setX4Scope(int x4Scope) {
        this.x4Scope = x4Scope;
    }

    public int getSniperScope() {
        return sniperScope;
    }

    public void setSniperScope(int sniperScope) {
        this.sniperScope = sniperScope;
    }

    public int getFreeReview() {
        return freeReview;
    }

    public void setFreeReview(int freeReview) {
        this.freeReview = freeReview;
    }
}
