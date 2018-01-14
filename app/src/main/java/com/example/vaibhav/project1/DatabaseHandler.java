package com.example.vaibhav.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LoginUser";
    private static final String TABLE_NAME = "user";
    private static final String NAME = "Name";
    private static final String USERNAME = "Username";
    private static final String PASS = "Password";
    private static final String ACTIVE = "Active";
    private static final String TOTAL_QUIZ = "TotalQuiz";
    private static final String TOTAL_SCORE = "TotalScore";
    private static final String AVG_SCORE = "AverageScore";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Category table create query
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME +"("
                +NAME + " TEXT, "
                +USERNAME + " TEXT PRIMARY KEY, "
                +PASS + " TEXT, "
                +ACTIVE +" TEXT, "
                +TOTAL_QUIZ +" INTEGER, "
                +TOTAL_SCORE +" INTEGER, "
                +AVG_SCORE +" INTEGER)";
        Log.d("Table creating : ",CREATE_ITEM_TABLE);
        db.execSQL(CREATE_ITEM_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
    public int register(String nm,String unm,String pwd)
    {
        Log.d("REGISTERING ",unm);
        SQLiteDatabase db = this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+" WHERE "+USERNAME+"='"+unm+"'";
        Cursor c=db.rawQuery(query,null);
        try {
            if (c.moveToFirst()) {
                c.close();
                db.close();
                return 0;
            }
        }catch(SQLException e) {
            return 2;
        }
        ContentValues values = new ContentValues();
        values.put(NAME, nm);
        values.put(USERNAME,unm);
        values.put(PASS, pwd);
        values.put(ACTIVE, "N");
        values.put(TOTAL_QUIZ, "0");
        values.put(TOTAL_SCORE, "0");
        values.put(AVG_SCORE, "0");
        db.insert(TABLE_NAME,null,values);
        db.close();
        Log.d("REGISTERED SUCCESSFULLY",unm);
        return 1;
    }
    public boolean verify(String unm, String p) {
        Log.d("VERIFYING ",unm);
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+USERNAME+"='" + unm + "' AND "+PASS+"='" + p + "'";
        Cursor c = db.rawQuery(query, null);
        try {
            if (c.moveToFirst()) {
                ContentValues values = new ContentValues();
                Log.d("Name : ", c.getString(0));
                Log.d("Username : ",c.getString(1));
                Log.d("Password : ", c.getString(2));
                Log.d("Active : ", c.getString(3));
                Log.d("TotalQuiz : ", c.getString(4));
                Log.d("Total score : ", c.getString(5));
                Log.d("avg score : ",c.getString(6));
                values.put(NAME, c.getString(0));
                values.put(USERNAME,c.getString(1));
                values.put(PASS, c.getString(2));
                values.put(ACTIVE, "Y");
                values.put(TOTAL_QUIZ, c.getString(4));
                values.put(TOTAL_SCORE, c.getString(5));
                values.put(AVG_SCORE, c.getString(6));
                db.update(TABLE_NAME,values," Username = ?",new String[]{unm});
                c.close();
                db.close();
                Log.d("VERIFIED ",unm);
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
    public void signOut(String unm) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + USERNAME + "='" + unm + "'";
        Cursor c = db.rawQuery(query, null);
        try {
            if (c.moveToFirst()) {
                ContentValues values = new ContentValues();
                values.put(NAME, c.getString(0));
                values.put(USERNAME, c.getString(1));
                values.put(PASS, c.getString(2));
                values.put(ACTIVE, "N");
                values.put(TOTAL_QUIZ, c.getString(4));
                values.put(TOTAL_SCORE, c.getString(5));
                values.put(AVG_SCORE, c.getString(6));
                db.update(TABLE_NAME, values, " Username = ?", new String[]{unm});
                c.close();
                db.close();
            }
        } catch (SQLException e) {
            return;
        }
    }
    public String activeUser()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+ACTIVE+"='Y'";
        Cursor c = db.rawQuery(query, null);
        try {
            if (c.moveToFirst()) {
                String name=c.getString(1);
                Log.d("Active user : ",c.getString(1));
                c.close();
                db.close();
                return name;
            }
            else
                return "";
        } catch (SQLException e) {
            return "";
        }
    }
    public void update(String unm,int score)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "+USERNAME+"='" + unm + "'";
        Cursor c = db.rawQuery(query, null);
        try {
            if (c.moveToFirst()) {
                int z=c.getInt(4)+1;
                ContentValues values = new ContentValues();
                values.put(NAME, c.getString(0));
                values.put(USERNAME,c.getString(1));
                values.put(PASS, c.getString(2));
                values.put(ACTIVE, c.getString(3));
                values.put(TOTAL_QUIZ, z);
                values.put(TOTAL_SCORE, c.getInt(5)+score);
                values.put(AVG_SCORE, (c.getInt(5)+score)/z);
                db.update(TABLE_NAME,values," Username = ?",new String[]{unm});
                c.close();
                db.close();
                Log.d("UPDATED : ",unm);
                return;
            }
        } catch (SQLException e) {
            return;
        }
    }
}

