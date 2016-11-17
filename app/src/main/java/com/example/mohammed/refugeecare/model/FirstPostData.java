package com.example.mohammed.refugeecare.model;

/**
 * Created by Ahmed Khattab 2 on 10/16/2016.
 */
public class FirstPostData
{
    private String userImg;


    public FirstPostData()
    {
        userImg = "http://downloadicons.net/sites/default/files/user-icon-2722.png";
    }

    public FirstPostData(int id )
    {

    }
    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }


}
