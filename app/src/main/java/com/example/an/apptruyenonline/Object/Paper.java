package com.example.an.apptruyenonline.Object;

import java.io.Serializable;

/**
 * Created by An on 12/10/2016.
 */

public class Paper implements Serializable {
    int IdPaper , IdChapter;
    String Paper;

    public Paper(int idPaper, int idChapter, String paper) {
        IdPaper = idPaper;
        IdChapter = idChapter;
        Paper = paper;
    }

    public int getIdPaper() {
        return IdPaper;
    }

    public void setIdPaper(int idPaper) {
        IdPaper = idPaper;
    }

    public int getIdChapter() {
        return IdChapter;
    }

    public void setIdChapter(int idChapter) {
        IdChapter = idChapter;
    }

    public String getPaper() {
        return Paper;
    }

    public void setPaper(String paper) {
        Paper = paper;
    }
}
