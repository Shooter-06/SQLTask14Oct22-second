package com.example.sqltask14oct22;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.material.badge.BadgeDrawable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME ="Inventory.db";
    public static final int DB_VERSION =1;
    public static final String TABLE_NAME ="product";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "BRAND";
    public static final String COL_4 = "PRC";
    public static final String COL_5 = "REV";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table product (id integer primary key autoincrement, name vachar" +
                ", brand varchar,prc number, rev vachar )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists product");
        onCreate(db);
    }


    public boolean addProducts(String name, String brand, String price, String reviews){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, brand);
        contentValues.put(COL_4, price);
        contentValues.put(COL_5, reviews);

        db.insert("product", null, contentValues);
        db.close();
        return true;
    }

    public boolean updateProducts(String id, String name, String brand, String price, String reviews){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, brand);
        contentValues.put(COL_4, price);
        contentValues.put(COL_5, reviews);

        db.update(TABLE_NAME, contentValues, "ID = ? ", new String[]{id});
        return true;
    }

    public Integer deleteProducts(String id){
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ? ", new String[]{id});
    }

    public Cursor viewProducts(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res=  db.rawQuery(" select * from  product ", null);
        return res;
    }
}
