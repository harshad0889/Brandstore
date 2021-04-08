package com.example.brandstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "BRAND.db";
    public static final String TABLE_NAME = "registrationtable";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "username";
    public static final String COL_3 = "phone";
    public static final String COL_4 = "password";
    public static final String COL_5 = "address";

    //product details
    public static final String TABLE_PRODUCT = "cartable";
    public static final String COL_pid = "pid";
    public static final String COL_pname = "pname";
    public static final String COL_pcat = "pcategory";
    public static final String COL_pdesc = "pdesc";
    public static final String COL_psize = "psize";
    public static final String COL_paprice = "act_price";
    public static final String COL_pofprice = "of_price";
    public static final String COL_psale = "psale";
    public static final String COL_poff = "p_off";
    public static final String COL_iv = "iv";
   // public static final String COL_cuid = "_id";


//user registration table
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_2 + " TEXT,"
            + COL_3 + " TEXT,"
            + COL_4 + " TEXT " + ")";

// add product == PRODUCT TABLE
    private String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
            + COL_pid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_pname + " TEXT,"
            + COL_pcat + " TEXT,"
            + COL_pdesc + " TEXT,"
            + COL_psize + " TEXT,"
            + COL_paprice + " INTEGER,"
            + COL_pofprice + " INTEGER,"
            + COL_psale + " TEXT,"
            + COL_poff + " INTEGER,"
            + COL_iv + " BLOB " + ")";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);





    }

    //INSERT USER DATA

    public boolean insertdata(String username, String phone, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, phone);
        contentValues.put(COL_4, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }


    //INSERT CAR DATA

    public boolean insertcardata(String cname, String c_cat, String cdesc, String
            csize, String cact_price, String cof_price, String csale, String coff,
                                 byte[] newentryimg) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_pname, cname);
        contentValues.put(COL_pcat, c_cat);
        contentValues.put(COL_pdesc, cdesc);
        contentValues.put(COL_psize, csize);
        contentValues.put(COL_paprice, cact_price);
        contentValues.put(COL_pofprice, cof_price);
        contentValues.put(COL_psale, csale);
        contentValues.put(COL_poff, coff);
        contentValues.put(COL_iv, newentryimg);

        long result = db.insert(TABLE_PRODUCT, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

public UserModel Authenticate(UserModel userModel) {

    // array of columns to fetch
    String[] columns = {
            COL_1,
            COL_2,
            COL_3,
            COL_4
    };
    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.query(TABLE_NAME, //Table to query
            columns,                    //columns to return
            null,                  //columns for the WHERE clause
            null,              //The values for the WHERE clause
            null,                       //group the rows
            null,                       //filter by row groups
            null);                      //The sort order
    if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
        do {

            UserModel user1 = new UserModel(cursor.getString(cursor.getColumnIndex(this.COL_1)),
                    cursor.getString(cursor.getColumnIndex(this.COL_2)),
                    cursor.getString(cursor.getColumnIndex(this.COL_3)),
                    cursor.getString(cursor.getColumnIndex(this.COL_4)));

            if (userModel.password.equalsIgnoreCase(user1.password) && userModel.username.equals(user1.username)) {
                return user1;

            }
        } while (cursor.moveToNext());

    }
    cursor.close();
    return null;
}


    public Cursor singleUser(String _id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL_1+" = '"+_id+"'",null);

        return  cursor;
    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


}
