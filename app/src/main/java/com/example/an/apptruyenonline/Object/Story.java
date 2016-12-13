package com.example.an.apptruyenonline.Object;

import java.io.Serializable;

/**
 * Created by An on 12/9/2016.
 */

public class Story implements Serializable {
    int IdStory;
    String NameStory, TypeStory, Images, Description, ReleaseDate, Pos, Active;

    public Story(int idStory, String nameStory, String typeStory, String images, String description, String releaseDate, String pos, String active) {
        IdStory = idStory;
        NameStory = nameStory;
        TypeStory = typeStory;
        Images = images;
        Description = description;
        ReleaseDate = releaseDate;
        Pos = pos;
        Active = active;
    }

    public int getIdStory() {
        return IdStory;
    }

    public void setIdStory(int idStory) {
        IdStory = idStory;
    }

    public String getNameStory() {
        return NameStory;
    }

    public void setNameStory(String nameStory) {
        NameStory = nameStory;
    }

    public String getTypeStory() {
        return TypeStory;
    }

    public void setTypeStory(String typeStory) {
        TypeStory = typeStory;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getPos() {
        return Pos;
    }

    public void setPos(String pos) {
        Pos = pos;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }
}
