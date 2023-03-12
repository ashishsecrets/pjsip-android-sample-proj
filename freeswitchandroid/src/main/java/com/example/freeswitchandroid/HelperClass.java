package com.example.freeswitchandroid;

public class HelperClass {

    public static class AuthHelper{

        public static String authToken = "";

        public static String getAuthToken() {
            return authToken;
        }

        public static void setAuthToken(String authToken) {
            AuthHelper.authToken = authToken;
        }



    }


}
