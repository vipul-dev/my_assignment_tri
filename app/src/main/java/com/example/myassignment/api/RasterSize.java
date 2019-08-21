package com.example.myassignment.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RasterSize {

    @SerializedName("formats")
    @Expose
    public List<FormatData> formats = null;

    public List<FormatData> getFormats() {
        return formats;
    }

    public void setFormats(List<FormatData> formats) {
        this.formats = formats;
    }


}
