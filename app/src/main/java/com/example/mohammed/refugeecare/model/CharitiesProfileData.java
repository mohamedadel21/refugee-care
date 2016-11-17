package com.example.mohammed.refugeecare.model;

/**
 * Created by Ahmed Khattab 2 on 10/20/2016.
 */
public class CharitiesProfileData {
    private String charityName;
    private String charityTypeOfGive;
    private String charityNeeds;
    private String charityAddress;
    private String[] charityPhones;
    private String charityNotes;


    public CharitiesProfileData(String charityName,
            String charityTypeOfGive,
            String charityNeeds,
            String charityAddress,
            String[] charityPhones,
            String charityNotes)
    {
       this.charityName = charityName;
       this.charityTypeOfGive = charityTypeOfGive;
       this.charityNeeds = charityNeeds;
       this.charityAddress = charityAddress;
       this.charityPhones = charityPhones;
       this.charityNotes = charityNotes;


    }

    public String getCharityName() {
        return charityName;
    }

    public void setCharityName(String charityName) {
        this.charityName = charityName;
    }

    public String getCharityTypeOfGive() {
        return charityTypeOfGive;
    }

    public void setCharityTypeOfGive(String charityTypeOfGive) {
        this.charityTypeOfGive = charityTypeOfGive;
    }

    public String getCharityNeeds() {
        return charityNeeds;
    }

    public void setCharityNeeds(String charityNeeds) {
        this.charityNeeds = charityNeeds;
    }

    public String getCharityAddress() {
        return charityAddress;
    }

    public void setCharityAddress(String charityAddress) {
        this.charityAddress = charityAddress;
    }

    public String[] getCharityPhones() {
        return charityPhones;
    }

    public void setCharityPhones(String[] charityPhones) {
        this.charityPhones = charityPhones;
    }

    public String getCharityNotes() {
        return charityNotes;
    }

    public void setCharityNotes(String charityNotes) {
        this.charityNotes = charityNotes;
    }
}
