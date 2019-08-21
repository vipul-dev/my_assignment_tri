package com.example.myassignment.model;

public class HomeModel {
    private String image;
    private String imageName;
    private String imageLength;
    private boolean isPaid;

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String  getImage() {
        return image;
    }

    public void setImage(String  image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageLength() {
        return imageLength;
    }

    public void setImageLength(String imageLength) {
        this.imageLength = imageLength;
    }
}
