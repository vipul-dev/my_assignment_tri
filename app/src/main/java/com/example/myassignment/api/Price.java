package com.example.myassignment.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Price {

    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("license")
    @Expose
    private License license;
    @SerializedName("currency")
    @Expose
    private String currency;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
