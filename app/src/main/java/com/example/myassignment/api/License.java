package com.example.myassignment.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class License {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("license_id")
    @Expose
    private Integer licenseId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Integer licenseId) {
        this.licenseId = licenseId;
    }
}
