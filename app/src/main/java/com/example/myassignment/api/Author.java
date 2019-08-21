package com.example.myassignment.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Author {
    @SerializedName("iconsets_count")
    @Expose
    private Integer iconsetsCount;
    @SerializedName("is_designer")
    @Expose
    private Boolean isDesigner;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;

    public Integer getIconsetsCount() {
        return iconsetsCount;
    }

    public void setIconsetsCount(Integer iconsetsCount) {
        this.iconsetsCount = iconsetsCount;
    }

    public Boolean getDesigner() {
        return isDesigner;
    }

    public void setDesigner(Boolean designer) {
        isDesigner = designer;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
