package com.people.ui.bean;

/**
 * 1.
 * 2.梁萌萌
 * 3.2017/1/5
 */
public class RecyclerBean {
    private int image;
    private String title;

    public RecyclerBean(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
