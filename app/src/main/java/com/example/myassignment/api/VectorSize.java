package com.example.myassignment.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class VectorSize {
    @SerializedName("formats")
    @Expose
    private List<Format_> formats = null;
    @SerializedName("target_sizes")
    @Expose
    private List<List<Integer>> targetSizes = null;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("size_height")
    @Expose
    private Integer sizeHeight;
    @SerializedName("size_width")
    @Expose
    private Integer sizeWidth;

    public List<Format_> getFormats() {
        return formats;
    }

    public void setFormats(List<Format_> formats) {
        this.formats = formats;
    }

    public List<List<Integer>> getTargetSizes() {
        return targetSizes;
    }

    public void setTargetSizes(List<List<Integer>> targetSizes) {
        this.targetSizes = targetSizes;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(Integer sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public Integer getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(Integer sizeWidth) {
        this.sizeWidth = sizeWidth;
    }
}
