package com.example.brandstore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class sp_manager {

    private static final String SHARED_PREF_NAME = "user_details";
    private static final String uid = "keyuid";
    private static final String username = "keybuyername";
    private static final String user_mobile = "keybuyermobile";
    private static final String user_address = "keybuyeremail";
    private static final String user_pin = "keybuyerdob";
    //private static final String KEY_BUYERID = "keybuyerid";

    private static sp_manager mInstance;
    private static Context mCtx;




//thsi 2
    static SharedPreferences getSharedPreferences(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    public static void setuserdetails(Context ctx, String user){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(SHARED_PREF_NAME,user);
        editor.commit();
    }
    public static String getUser2(Context ctx){
        return getSharedPreferences(ctx).getString(SHARED_PREF_NAME,"");
    }


    //this2




    private sp_manager(Context context)
    {
        mCtx = context;
    }


    public static synchronized sp_manager getInstance(Context context)
    {
        if (mInstance == null)
        {
            mInstance = new sp_manager(context);
        }

        return mInstance;
    }


    public void userlogin(User buyer)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(uid, buyer.getUid());
        editor.putString(username, buyer.getUsernname());
        editor.putString(user_mobile, buyer.getMobile());
        editor.putString(user_address, buyer.getAddress());

        editor.apply();
    }



    public boolean isLoggedIn()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(username,null) != null;
    }

    public User getUser()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(uid,-1),
                sharedPreferences.getString(username,null),
                sharedPreferences.getString(user_mobile,null),
                sharedPreferences.getString(user_address,null),
                sharedPreferences.getString(user_pin,null)
        );
    }


    public void logout()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        mCtx.startActivity(new Intent(mCtx, verify_phno.class));
    }

    public void clearuser(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
        ctx.startActivity(new Intent(ctx, verify_phno.class));
    }



}
