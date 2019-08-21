package com.example.myassignment.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponseOfIcon {


    @SerializedName("raster_sizes")
    @Expose
    private List<RasterSize> rasterSizes = null;


    public List<RasterSize> getRasterSizes() {
        return rasterSizes;
    }

    public void setRasterSizes(List<RasterSize> rasterSizes) {
        this.rasterSizes = rasterSizes;
    }
}
