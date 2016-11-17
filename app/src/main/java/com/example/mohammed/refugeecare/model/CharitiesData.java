package com.example.mohammed.refugeecare.model;

import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/19/2016.
 */
public class CharitiesData {

    private String charityProfilePic;
    private String charityProfileName;
    private String charityAddress;
    private String charityPostContent;



    public CharitiesData() {
    }


    public CharitiesData(String charityProfilePic, String charityProfileName, String charityAddress, String charityPostContent) {
        this.charityProfilePic = charityProfilePic;
        this.charityProfileName = charityProfileName;
        this.charityAddress = charityAddress;
        this.charityPostContent = charityPostContent;
    }

    /******************************************************************************************************************************/

    public ArrayList<CharitiesData> getData() {

        ArrayList<String> allPics = getAllPics();
        ArrayList<String> allNames = getAllNames();
        ArrayList<String> allAddress = getAllAdress();
        ArrayList<String> allContent = getAllContent();

        ArrayList<CharitiesData> data = new ArrayList<>();
        for(int i = 0 ; i < getAllAdress().size() ; i++)
        {
                CharitiesData obj = new CharitiesData(
                        allPics.get(i),
                        allNames.get(i),
                        allAddress.get(i),
                        allContent.get(i)
                );
            data.add(obj);
        }

        return data;

    }

    public ArrayList<String> getAllAdress(){
        ArrayList<String> mAllAdress = new ArrayList<>();
        for(int i = 0  ;i<50 ;i++ )
        {
            mAllAdress.add("address "+(i+1));

        }
        return mAllAdress;
    }
    public ArrayList<CharitiesProfileData> getAllData()
    {

        ArrayList<CharitiesProfileData> alldata = new ArrayList<>();
        String[] phones ;
        CharitiesProfileData data;

        phones= new String[]{"27964443"};
        data = new CharitiesProfileData(
                "1-مركزتسجيل السوريين التابع للمفوضيه العليا للاجئين",
                "الحماية والمناصرة",
                "صور جواز السفر صورتين شخصية الحضور شخصي",
                "الزمالك-خلف فندق الماريوت-كنيسة كل القديسين"
                ,phones
                ,"التسجيل اولا");
        alldata.add(data);


        phones= new String[]{"27964441", "27961771"};
        data = new CharitiesProfileData(
                "كاريتاس",
                "دعم صحي",
                "كرت  التسجيل بالمفوضية",
"غاردن سيتي-خلف فندق الفورسيزون-جانب السفارة الاندونيسية"
                ,phones
                ,"1-الخدمات الاجتماعية: مشفى السنابل منشية الصدر بالمترو\n" +
                "2-الخدمات الصحية:مشفى القبطية رمسيس\n" +
                "\n"
        );
        alldata.add(data);


        phones= new String[]{"27964441"};
        data = new CharitiesProfileData(
                "ملجا مصر",
                "عيادات الامومة ورعاية الطفل",
                "1-الزمالك خلف فندق ماريوت كنيسة كل القديسين"
                ,"2-6اكتوبر الحي العاشر المحور المركزي كنيسة السيدة العذراء\n"

                ,phones
                ,"للنساء  وحالات الولادة والأطفال الخامسة ومادون");
        alldata.add(data);



        phones= new String[]{"16868"};
        data = new CharitiesProfileData(
            "بنك ناصر الاجتماعي "            ,
            "اعانة مادية",
            "صور جواز السفر وعقد الايجار",

        "بكل فروع البنك\n" +
        "1-6اكتوبر  المنطقة الصناعية الرابعة تجمع البنوك\n" +
        "2- حدائق الزيتون شارع العزيز بالله\n" +
        "3- الجيزة\n",
                phones
                ,"التسجيل كل شهر من 15 حتى 20 الشهر\n" +
                "يقبل التسجيل كلا حسب المنطقة المقيم فيها لذا يجب الاتصال اولا\n");
        alldata.add(data);



        return alldata;


    }
    public ArrayList<String> getAllNames(){
        ArrayList<String> mAllNames = new ArrayList<>();
        mAllNames.add("مركزتسجيل السوريين التابع للمفوضيه العليا للاجئين");
        mAllNames.add("المفوضية العليا لشؤون اللاجئين   الادارة");
        mAllNames.add("كاريتاس");
        mAllNames.add("ملجا مصر");
        mAllNames.add("الهيئة الكاثوليكيةCRS");

        mAllNames.add("منظمة أميرا");
        mAllNames.add("مركز تضامن سوريا");
        mAllNames.add(" اطباء بلا حدود MSF");
        mAllNames.add("مركز الاغاثة السوري سوريا الغد");
        mAllNames.add("مركزالاغاثة الانساني-نقابة اطباء القاهرة");

        mAllNames.add("الاغاثة الاسلامية عبر العالم IRWW");
        mAllNames.add("رابطة اللاجئين السوريين");
        mAllNames.add("بيت العيلة");
        mAllNames.add("-مركزالاغاثة المصرية لمساعدة السوريين");
        mAllNames.add("وزارة الاوقاف المصرية");

        mAllNames.add("مركز بايونيرز الطبي");
        mAllNames.add("بنك فيصل الاسلامي");
        mAllNames.add(" المنظمة المصرية لعلماء المسلمين");
        mAllNames.add("الجمعية الدوليةللتواصل العربي الافريقي");


        mAllNames.add(" مشفى مصطفى محمود");
        mAllNames.add(" جمعية مصطفى محمود");
        mAllNames.add("جمعية ابن خلدون");
        mAllNames.add("بنك ناصر الاجتماعي");

        return mAllNames;
    }

    public ArrayList<String> getAllPics() {

        ArrayList<String> mAllPics = new ArrayList<>();
        for(int i = 0  ;i<50 ;i++ )
        {
            mAllPics.add("https://static-files.jobzella.com/uploads/employers/333331/1461152662_logo_333331_.png");
        }
        return mAllPics;
    }
    /******************************************************************************************************************************/
    public String getCharityProfilePic() {
        return charityProfilePic;
    }

    public void setCharityProfilePic(String charityProfilePic) {
        this.charityProfilePic = charityProfilePic;
    }

    public String getProfileNameCharity() {
        return charityProfileName;
    }

    public void setProfileNameCharity(String profileNameCharity) {
        this.charityProfileName = profileNameCharity;
    }

    public String getCharityAddress() {
        return charityAddress;
    }

    public void setCharityAddress(String picProfileAddress) {
        this.charityAddress = picProfileAddress;
    }

    public String getCharityPostContent() {
        return charityPostContent;
    }

    public void setCharityPostContent(String profilePostContentCharity) {
        this.charityPostContent = profilePostContentCharity;
    }

    public ArrayList<String> getAllContent() {
        ArrayList<String> allContent = new ArrayList<>();
        allContent.add("الحماية والمناصرة");
        allContent.add("دعم صحي");
        allContent.add("عيادات الامومة ورعاية الطفل");
        allContent.add("منح تعليمية");
        allContent.add("");

        allContent.add("");
        allContent.add("");
        allContent.add("");
        allContent.add("");
        allContent.add("");

        allContent.add("");
        allContent.add("");
        allContent.add("");
        allContent.add("");
        allContent.add("");

        allContent.add("");
        allContent.add("");
        allContent.add("");
        allContent.add("");


        allContent.add("");
        allContent.add("");
        allContent.add("");
        allContent.add("");

        return allContent;
    }
}
