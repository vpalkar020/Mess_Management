package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="PHNO";
    public static final String COL_4="JOINING";
    public static final String COL_5="COMPLITION";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,PHNO TEXT,JOINING TEXT,COMPLITION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    this.onCreate(db);
    }
    public boolean insertData(String name,String phno,String joining,String complition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, phno);
        contentValues.put(COL_4, joining);
        contentValues.put(COL_5, complition);
        long result=db.insert(TABLE_NAME, null, contentValues);
        if(result==-1) {
            return false;
        }
        else {
            return true;
        }
        }
     public Cursor viewAllData(){
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
         return  res;
     }
    public Integer del_data(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID= ?",new String[] {id} );

    }
    public Cursor getMobile(String Id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.query(TABLE_NAME,new String[] {"PHNO"},"ID LIKE ?",new String[] {Id},null,null,null);
        return  res;
    }
}
