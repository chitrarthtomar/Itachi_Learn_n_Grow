package com.example.hp15p077tx.courses.Models;

/**
 * Created by HP 15 P077 TX on 09-04-2017.
 * This class will contain data type.
 */

public class ListItems {
    private String title;
    private int imageResId;
    private String Size;
    private boolean Clicked = false;

    public boolean isClicked() {
        return Clicked;
    }

    public void setClicked(boolean clicked) {
        Clicked = clicked;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = "Chapters:" + size;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }


}
