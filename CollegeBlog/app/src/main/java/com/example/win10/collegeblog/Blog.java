package com.example.win10.collegeblog;

/**
 * Created by win10 on 3/20/2018.
 */

public class Blog
{
    private String Description,ImageUri,Title;

    public Blog()
    {

    }
    public Blog(String description, String imageUri, String title) {
        Description = description;
        ImageUri = imageUri;
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
