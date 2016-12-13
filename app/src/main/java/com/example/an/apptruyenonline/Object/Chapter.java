package com.example.an.apptruyenonline.Object;

import java.io.Serializable;

/**
 * Created by An on 12/10/2016.
 */

public class Chapter implements Serializable {
    int IdChapter, IdPart;
    String ContentChapter, Images;
    int Pos, Active;

    public Chapter(int idChapter, int idPart, String contentChapter, String images, int pos, int active) {
        IdChapter = idChapter;
        IdPart = idPart;
        ContentChapter = contentChapter;
        Images = images;
        Pos = pos;
        Active = active;
    }

    public int getIdChapter() {
        return IdChapter;
    }

    public void setIdChapter(int idChapter) {
        IdChapter = idChapter;
    }

    public int getIdPart() {
        return IdPart;
    }

    public void setIdPart(int idPart) {
        IdPart = idPart;
    }

    public String getContentChapter() {
        return ContentChapter;
    }

    public void setContentChapter(String contentChapter) {
        ContentChapter = contentChapter;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
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
}
