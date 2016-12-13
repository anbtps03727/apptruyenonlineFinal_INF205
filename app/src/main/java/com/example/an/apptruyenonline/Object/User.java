package com.example.an.apptruyenonline.Object;

import java.io.Serializable;

/**
 * Created by Lovemotnguoi on 8/28/2016.
 */
public class User implements Serializable {

    public int UserId;
    public String Username;
    public String Password;
    public String Fullname;
    public String Phone;
    public String FaceBookbId;
    public String Avatar;
    public int Pos;
    public int Active;


    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public User(int userId, String username, String password, String fullname, String phone, String faceBookbId, String avatar, int pos, int active) {
        UserId = userId;
        Username = username;
        Password = password;
        Fullname = fullname;
        Phone = phone;
        FaceBookbId = faceBookbId;
        Avatar = avatar;
        Pos = pos;
        Active = active;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFaceBookbId() {
        return FaceBookbId;
    }

    public void setFaceBookbId(String faceBookbId) {
        FaceBookbId = faceBookbId;
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
