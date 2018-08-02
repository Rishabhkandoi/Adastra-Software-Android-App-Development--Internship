package com.example.hp.chhabras;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by hp on 14-06-2018.
 */

public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE=0;

    private static final String PREFER_NAME = "BniJaipur";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_USER_ID = "LOGGED_ID";
    public static final String KEY_USER_PHONE = "LOGGED_PHONE";
    public static final String KEY_USER_NAME = "LOGGED_NAME";
    public static final String KEY_USER_COMPANY = "lOGGED_COMPANY";
    public static final String KEY_USER_GIVE = "lOGGED_GIVE";
    public static final String KEY_USER_ASK = "lOGGED_ASK";
    public static final String KEY_USER_TOTAL_GIVE = "lOGGED_TOTAL_GIVE";
    public static final String KEY_USER_TOTAL_ASK = "lOGGED_TOTAL_ASK";
    //public static final String KEY_USER_IMAGE ="lOGGED_IMAGE";
    public static final String KEY_USER_EMAIL ="lOGGED_EMAIL";
    //public static final String KEY_USER_FIELD ="lOGGED_FIELD";
    public static final String KEY_USER_DESIGNATION ="lOGGED_DESIGNATION";

    public SessionManager(Context context){
        this._context=context;
        pref= _context.getSharedPreferences(PREFER_NAME,PRIVATE_MODE);
        editor=pref.edit();
    }

    public void createUserLoginSession(String id, String mobile, String name, String company, String give, String ask, String total_give, String total_ask, String email, String designation)  {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_USER_ID, id);
        editor.putString(KEY_USER_PHONE, mobile);
        editor.putString(KEY_USER_NAME,name);
        editor.putString(KEY_USER_COMPANY,company);
        editor.putString(KEY_USER_GIVE,give);
        editor.putString(KEY_USER_ASK,ask);
        editor.putString(KEY_USER_TOTAL_GIVE,total_give);
        editor.putString(KEY_USER_TOTAL_ASK,total_ask);
        //editor.putString(KEY_USER_IMAGE,image);
        editor.putString(KEY_USER_EMAIL,email);
        //editor.putString(KEY_USER_FIELD,field);
        editor.putString(KEY_USER_DESIGNATION,designation);
        editor.commit();
    }

    public HashMap<String,String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USER_ID,pref.getString(KEY_USER_ID,null));
        user.put(KEY_USER_PHONE,pref.getString(KEY_USER_PHONE,null));
        user.put(KEY_USER_NAME,pref.getString(KEY_USER_NAME,null));
        user.put(KEY_USER_COMPANY,pref.getString(KEY_USER_COMPANY,null));
        user.put(KEY_USER_GIVE,pref.getString(KEY_USER_GIVE,null));
        user.put(KEY_USER_ASK,pref.getString(KEY_USER_ASK,null));
        user.put(KEY_USER_TOTAL_GIVE,pref.getString(KEY_USER_TOTAL_GIVE,null));
        user.put(KEY_USER_TOTAL_ASK,pref.getString(KEY_USER_TOTAL_ASK,null));
        //user.put(KEY_USER_IMAGE,pref.getString(KEY_USER_IMAGE,null));
        user.put(KEY_USER_EMAIL,pref.getString(KEY_USER_EMAIL,null));
        //user.put(KEY_USER_FIELD,pref.getString(KEY_USER_FIELD,null));
        user.put(KEY_USER_DESIGNATION,pref.getString(KEY_USER_DESIGNATION,null));

        return user;
    }

    /*** Clear session details   * */
    public void logoutUser(){
        // Clearing all user data from Shared Preferences
        editor.remove(KEY_USER_ID);
        editor.remove(KEY_USER_EMAIL);
        editor.remove(KEY_USER_PHONE);
        editor.remove(KEY_USER_NAME);
        editor.remove(IS_USER_LOGIN);
        editor.commit();
    }

    public boolean isUserLoggedIn()
    {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}
