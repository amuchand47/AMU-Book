package com.herokuapp.amulibraray.amulibrary.Model;

public class Song {
    private String image;
    private String title;

    public  Song(){}
    public Song(String image, String title) {
        this.image = image;
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
