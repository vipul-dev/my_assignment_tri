package com.example.myassignment.api;


import androidx.databinding.ObservableArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ApiResponse  {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("icons")
    @Expose
    private ObservableArrayList<DataResponseOfIcon> iconsSetData = null;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


    public ObservableArrayList<DataResponseOfIcon> getIconsSetData() {
        return iconsSetData;
    }

    public void setIconsSetData(ObservableArrayList<DataResponseOfIcon> iconsSetData) {
        this.iconsSetData = iconsSetData;
    }
}
