package com.example.brandstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    Cursor cursor;

    public static final String DATABASE_NAME = "BRAND.db";
    public static final String TABLE_NAME = "registrationtable";
    public static final String COL_1 = "_id";
    public static final String COL_2 = "username";
    public static final String COL_3 = "phone";
    public static final String COL_4 = "password";
    public static final String COL_5 = "address";
    public static final String COL_6 = "pin";

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
    public static final String COL_sprodname = "pname";
    public static final String COL_sdate = "start_date";
    public static final String COL_edate = "end_date";
    public static final String COL_siv = "siv";

    public static final String COL_dtime = "sale_date";

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
    public static final String COL_oid = "order_id";

    public static final String COL_cart_psize = "cart_psize";
    public static final String COL_cart_status = "cart_status";


    //whislist details
    public static final String TABLE_WISHLIST = "wish_table";
    public static final String COL_wish_id = "wish_id";
    public static final String COL_product_id = "pid";
    public static final String COL_wuid = "_id";
    public static final String COL_wqty = "qty";
    public static final String COL_wsize = "size";

   // public static final String COL_cart_psize = "cart_psize";


    //order details
    public static final String TABLE_ORDER = "order_table";
    public static final String COL_ordid = "order_id";
    public static final String COL_ouid = "_id";
    public static final String COL_cart_id = "cart_id";
    public static final String COL_total = "total";
    public static final String COL_delviery_amt = "delivery_amt";
    public static final String COL_quantity = "quantity";
    public static final String COL_ostatus = "status";
    public static final String COL_pmode = "pay_mode";
    public static final String COL_odate = "odate";
  //  public static final String COL_opname = "pname";
  //  public static final String COL_oprice = "oprice";
  // public static final String COL_opqty = "odate";


    //table car review

    public static final String TABLE_REVIEW = "REVIEW";
    public static final String COL_rid = "_id";
    public static final String COL_pro_id = "pid";
    public static final String COL_user = "user";
    public static final String COL_review = "review";
    public static final String COL_rating = "rating";


    //table stocks

    public static final String TABLE_STOCKS = "STOCKS";
    public static final String stock_id = "s_id";
    public static final String p_category = "pcat";
    public static final String stock = "stock";
    public static final String s_date = "s_date";







//user registration table
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_2 + " TEXT,"
            + COL_3 + " TEXT,"
            + COL_4 + " TEXT, "
        + COL_5 + " TEXT,"
        + COL_6 + " TEXT" + ")";

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
            + COL_sprodname + " TEXT,"
            + COL_sdate + " TEXT,"
            + COL_edate + " TEXT,"
            + COL_dtime + " date default CURRENT_DATE, "
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
            //+ COL_oid + " INTEGER,"
            + COL_qty + " INTEGER,"
            + COL_cart_psize + " TEXT,"
            + COL_cart_status + " TEXT,"
            + COL_oid + " INTEGER,"
            + " FOREIGN KEY ("+COL_oid+") REFERENCES "+TABLE_ORDER+" ("+COL_ordid+"),"
            + " FOREIGN KEY ("+COL_prodid+") REFERENCES "+TABLE_PRODUCT+" ("+COL_pid+"),"
            + " FOREIGN KEY ("+COL_uid+") REFERENCES "+TABLE_NAME+" ("+COL_1+"));";


    //wishlist table
    private String CREATE_WISHLIST_TABLE = "CREATE TABLE " + TABLE_WISHLIST + "("
            + COL_wish_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_product_id + " INTEGER,"
            + COL_wuid + " INTEGER,"
            + COL_wqty + " INTEGER,"
            + COL_wsize + " TEXT,"
            + " FOREIGN KEY ("+COL_product_id+") REFERENCES "+TABLE_PRODUCT+" ("+COL_pid+"),"
            + " FOREIGN KEY ("+COL_wuid+") REFERENCES "+TABLE_NAME+" ("+COL_1+"));";



    //order table
    private String CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_ORDER + "("
            + COL_ordid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_ouid + " INTEGER,"
            + COL_total + " INTEGER,"
           // + COL_opname + " TEXT,"
           // + COL_oprice + " TEXT,"
          //  + COL_opqty + " INTEGER,"
            + COL_delviery_amt + " TEXT,"
            + COL_quantity + " TEXT,"
            + COL_ostatus + " TEXT,"
            + COL_pmode + " TEXT,"
            + COL_odate + " date default CURRENT_DATE,"
            + " FOREIGN KEY ("+COL_ouid+") REFERENCES "+TABLE_NAME+" ("+COL_1+"))";



    //create table  review

    private String CREATE_REVIEW = "CREATE TABLE " + TABLE_REVIEW + "("
            + COL_rid + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_pro_id + " TEXT,"
            + COL_user + " TEXT,"
            + COL_rating + " TEXT,"
            + COL_review + " TEXT" + ")";


    //user stocksd table
    private String CREATE_STOCKS_TABLE = "CREATE TABLE " + TABLE_STOCKS + "("
            + stock_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + p_category + " TEXT,"
            + stock + " TEXT,"
            + s_date + " date default CURRENT_DATE "+ ")";

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
        db.execSQL(CREATE_ORDER_TABLE);
        db.execSQL(CREATE_WISHLIST_TABLE);
        db.execSQL(CREATE_REVIEW);
        db.execSQL(CREATE_STOCKS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISHLIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STOCKS);






    }

    //INSERT USER DATA

    public boolean insertdata(String username, String phone,String address,String pin, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, phone);
        contentValues.put(COL_4, password);
        contentValues.put(COL_5, address);
        contentValues.put(COL_6, pin);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }


    //INSERT cart DATA******************** add to cart**********

    public boolean insert_to_cart(String spid, String uid, String qtyv, String size) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_prodid, spid);
        contentValues.put(COL_uid, uid);
        contentValues.put(COL_qty, qtyv);
        contentValues.put(COL_cart_psize, size);
        contentValues.put(COL_cart_status, "ORDERED");

        long result = db.insert(TABLE_CART, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }


    //INSERT WISHLIST DATA******************** add to cart**********

    public boolean insert_to_wishlist(String spid, String uid,String qty,String size) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_product_id, spid);
        contentValues.put(COL_wuid, uid);
        contentValues.put(COL_wqty, qty);
        contentValues.put(COL_wsize, size);


        long result = db.insert(TABLE_WISHLIST, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    //INSERT PRODUCT DATA

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



    //INSERT ORDER DATA

    public Long insert_order_data(String uid,String total_amt,String delivery_charge,String quantity,String order_status,String p_mode) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //contentValues.put(COL_ordid, cname);
        contentValues.put(COL_ouid, uid);
        contentValues.put(COL_total, total_amt);
        contentValues.put(COL_delviery_amt, delivery_charge);
        contentValues.put(COL_quantity, quantity);
        contentValues.put(COL_ostatus, order_status);
        contentValues.put(COL_pmode, p_mode);
        //contentValues.put(COL_odate, newentryimg);

        long result = db.insert(TABLE_ORDER, null, contentValues);
        if (result == -1)
            return result;
        else

            return result;

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


    //*************update cart************

    //public int update_cart(String )
    public int update_cart(String order_id, String cart_id) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_oid, order_id);
        //contentValues.put(COL_pcat, c_cat);
        int i = db.update(TABLE_CART, contentValues, COL_cart_id + " = " + cart_id, null);
        return  i;

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
    public boolean insert_sale_data(String stitle, String sdesc,String prod_name,String sdate, String edate, byte[] siv) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_stitle, stitle);
        contentValues.put(COL_sdesc, sdesc);
        contentValues.put(COL_sprodname, prod_name);
        contentValues.put(COL_sdate, sdate);
        contentValues.put(COL_edate, edate);
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

    public Cursor getData2(String sql){
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery(sql, null);
    }

    public int remove_item(String spid,String uid) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_uid, uid);
        contentValues.put(COL_prodid, spid);
        int i = db.delete(TABLE_CART, COL_uid + " = " + uid + " AND " + COL_prodid + "=" + spid,null );
        return i;
    }


    public int remove_item2(String spid,String uid) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_wuid, uid);
        contentValues.put(COL_product_id, spid);
        int i = db.delete(TABLE_WISHLIST, COL_wuid + " = " + uid + " AND " + COL_product_id + "=" + spid,null );
        return i;
    }


    public int update_user_data(String s_name, String s_phn, String s_address, String s_pin,String uid) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, s_name);
        contentValues.put(COL_3, s_phn);
        contentValues.put(COL_5, s_address);
        contentValues.put(COL_6, s_pin);
        int i = db.update(TABLE_NAME, contentValues, COL_1 + " = " + uid, null);
        return  i;


    }
    //insert  review data
    public boolean p_review(String pro_id, String username, String review,String rating) {




            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_pro_id, pro_id);
            contentValues.put(COL_user, username);
            contentValues.put(COL_review, review);
            contentValues.put(COL_rating, rating);




            long result = db.insert(TABLE_REVIEW, null, contentValues);
            if (result == -1)
                return false;
            else
                return true;


    }


    //****************review list***********
    public Cursor review_list(String _id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REVIEW + " WHERE " + COL_pro_id + " = '" + _id + "'", null);

        return cursor;
    }

    public boolean insert_stocks(String ps_cat, String stock_qty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(p_category, ps_cat);
        contentValues.put(stock, stock_qty);





        long result = db.insert(TABLE_STOCKS, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;


    }
//stocks list
    public Cursor stocklist(String sdate) {

        SQLiteDatabase db = getReadableDatabase();

        Cursor curso = db.rawQuery("SELECT rowid _id, * FROM " + TABLE_STOCKS + " WHERE " + s_date + " = '" + sdate + "'" , null);

        return curso;
    }

    public int update_status(String scart_id,String order_id,String status)
    { SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_cart_status, status);


        int i = db.update(TABLE_CART, contentValues, COL_cartid + " = " + scart_id, null);
        return  i;

    }
}
