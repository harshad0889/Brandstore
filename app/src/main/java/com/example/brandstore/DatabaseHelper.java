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
    public static final String TABLE_PRODUCT = "product_table";
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


    //sale details
    public static final String TABLE_SALE = "sale_table";
    public static final String COL_sid = "sid";
    public static final String COL_stitle = "sale_title";
    public static final String COL_sdesc = "sale_desc";
    public static final String COL_siv = "siv";

    //category details
    public static final String TABLE_CATEGORY = "cate_table";
    public static final String COL_catid = "ctid";
    public static final String COL_ctname = "cat_name";
    public static final String COL_ctiv = "ctiv";


    //cart details
    public static final String TABLE_CART = "cart_table";
    public static final String COL_cartid = "cart_id";
    public static final String COL_prodid = "pid";
    public static final String COL_uid = "_id";
    public static final String COL_qty = "qty";

    public static final String COL_cart_psize = "cart_psize";




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
           // + COL_pofprice + " INTEGER,"
            + COL_psale + " TEXT,"
           // + COL_poff + " INTEGER,"
            + COL_iv + " BLOB " + ")";


    //sale table
    private String CREATE_SALE_TABLE = "CREATE TABLE " + TABLE_SALE + "("
            + COL_sid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_stitle + " TEXT,"
            + COL_sdesc + " TEXT,"
            + COL_siv + " BLOB " + ")";

    //category table
    private String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
            + COL_catid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_ctname + " TEXT,"
            + COL_ctiv + " BLOB " + ")";


    //cart table
    private String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + "("
            + COL_cartid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_prodid + " INTEGER,"
            + COL_uid + " INTEGER,"
            + COL_qty + " INTEGER,"
            + COL_cart_psize + " TEXT,"
            + " FOREIGN KEY ("+COL_prodid+") REFERENCES "+TABLE_PRODUCT+" ("+COL_pid+"),"
            + " FOREIGN KEY ("+COL_uid+") REFERENCES "+TABLE_NAME+" ("+COL_1+"));";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_SALE_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_CART_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);






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


    //INSERT cart DATA******************** add to cart**********

    public boolean insert_to_cart(String spid, String uid, String qty, String size) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_prodid, spid);
        contentValues.put(COL_uid, uid);
        contentValues.put(COL_qty, qty);
        contentValues.put(COL_cart_psize, size);

        long result = db.insert(TABLE_CART, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    //INSERT CAR DATA

    public boolean insert_prod_data(String cname, String c_cat, String cdesc, String
            csize, String cact_price,  String csale,
                                 byte[] newentryimg) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_pname, cname);
        contentValues.put(COL_pcat, c_cat);
        contentValues.put(COL_pdesc, cdesc);
        contentValues.put(COL_psize, csize);
        contentValues.put(COL_paprice, cact_price);
       // contentValues.put(COL_pofprice, cof_price);
        contentValues.put(COL_psale, csale);
       // contentValues.put(COL_poff, coff);
        contentValues.put(COL_iv, newentryimg);

        long result = db.insert(TABLE_PRODUCT, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }



    //**************update product data*************************


    public int updateproduct(String prodid,String cname, String c_cat, String cdesc, String
            csize, String cact_price,  String csale,
                             byte[] newentryimg) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_pname, cname);
        contentValues.put(COL_pcat, c_cat);
        contentValues.put(COL_pdesc, cdesc);
        contentValues.put(COL_psize, csize);
        contentValues.put(COL_paprice, cact_price);
        //contentValues.put(COL_pofprice, cof_price);
        contentValues.put(COL_psale, csale);
        //contentValues.put(COL_poff, coff);
        contentValues.put(COL_iv, newentryimg);
        int i = db.update(TABLE_PRODUCT, contentValues, COL_pid + " = " + prodid, null);
        return i;

    }



    //******************prod image************

    public byte[] prodImage(String _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] col=new String[]{COL_iv};  // your column which data u want to retrive if id is same
        Cursor c=db.query(TABLE_PRODUCT, col, COL_pid+"="+_id,null, null, null, null);
        if (c.moveToFirst()) {
            byte[] blob = c.getBlob(c.getColumnIndex(COL_iv));
            c.close();
            return blob;
        }
        c.close();
        return null;
    }


    //***************INSERT SALE DATA*********************
    public boolean insert_sale_data(String stitle, String sdesc, byte[] siv) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_stitle, stitle);
        contentValues.put(COL_sdesc, sdesc);
        contentValues.put(COL_siv, siv);

        long result = db.insert(TABLE_SALE, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }




    //***************INSERT CATEGORY DATA*********************
    public boolean add_cate(String ctname, byte[] ctiv) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ctname, ctname);

        contentValues.put(COL_ctiv, ctiv);

        long result = db.insert(TABLE_CATEGORY, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    //*****************PRODUCT LIST*******************
    public Cursor prodlist() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCT  , null);

        return cursor;
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
