package com.example.user.smartvillage.Controller;

public class AppConfig {
    private static final String ROOT_URL = "http://192.168.1.19/smartVillage/";
    public static final String URL_REGISTER = ROOT_URL + "frontend/web/index.php?r=API%2Fauth%2Fsignup";
    public static final String URL_LOGIN= ROOT_URL + "frontend/web/index.php?r=API%2Fauth%2Flogin";
    public static final String URL_GET_DATA_PEMBANGUNAN= ROOT_URL + "frontend/web/index.php?r=API%2Fpembangunan";
    public static final String URL_PICTURE = "http://192.168.1.19/smartvillage/backend/";
}
