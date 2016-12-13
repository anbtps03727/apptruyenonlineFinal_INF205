package com.example.an.apptruyenonline.Object;

import java.io.Serializable;

/**
 * Created by An on 12/10/2016.
 */

public class Part implements Serializable {
    int IdPart, IdStory, Pos, Active;
    String NamePart, Images;

    public Part(int idPart, int idStory, String namePart, String images, int pos, int active) {
        IdPart = idPart;
        IdStory = idStory;
        NamePart = namePart;
        Images = images;
        Pos = pos;
        Active = active;
    }

    public int getIdPart() {
        return IdPart;
    }

    public void setIdPart(int idPart) {
        IdPart = idPart;
    }

    public int getIdStory() {
        return IdStory;
    }

    public void setIdStory(int idStory) {
        IdStory = idStory;
    }

    public int getPos() {
        return Pos;
    }

    public void setPos(int pos) {
        Pos = pos;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public String getNamePart() {
        return NamePart;
    }

    public void setNamePart(String namePart) {
        NamePart = namePart;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }
}
