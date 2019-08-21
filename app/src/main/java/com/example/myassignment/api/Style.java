package com.example.myassignment.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Style {


    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("identifier")
    @Expose
    private String identifier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
