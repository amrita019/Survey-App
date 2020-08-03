package com.amrita.walkover_test.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static SQLiteDatabase db;

    private static final String DB_NAME = "walkover.db";
    private static final int DB_VERSION = 1;
    private String DB_PATH;


    //Columns for user table
    private static final String USER_TABLE_NAME = "usertable";
    public static final String COLUMN_NAME = "username";
    public static final String COLUMN_USERNAME = "userusername";
    public static final String COLUMN_EMAIL = "useremail";
    public static final String COLUMN_PHONE = "userphone";
    public static final String COLUMN_PASSWORD = "userpassword";

    //Columns for Survey table
    private static final String SURVEY_TABLE_NAME = "surveytable";
    public static final String SURVEY_COLUMN_NAME = "name";
    public static final String SURVEY_COLUMN_ADDRESS = "address";
    public static final String SURVEY_COLUMN_AGE = "age";
    public static final String SURVEY_COLUMN_GENDER = "gender";
    public static final String SURVEY_COLUMN_LAST_EDUCATION = "lasteducation";
    public static final String SURVEY_COLUMN_WANT_TO_BE = "wanttobe";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_PATH = "data/data/" + context.getPackageName() + "/databases/";
        db = this.getWritableDatabase();

        try{
            db.execSQL(createUserTable());
            db.execSQL(createSurveyTable());
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void openDatabase() throws SQLException{
        String path = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SURVEY_TABLE_NAME);

    }

    private String createUserTable() {
        return "CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME + "(" +
                "_id" + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_PASSWORD + " TEXT) ";
    }

    private String createSurveyTable() {
        return "CREATE TABLE IF NOT EXISTS " + SURVEY_TABLE_NAME + "(" +
                "_id" + " INTEGER PRIMARY KEY, " +
                SURVEY_COLUMN_NAME + " TEXT, " +
                SURVEY_COLUMN_ADDRESS + " TEXT, " +
                SURVEY_COLUMN_AGE + " INTEGER, " +
                SURVEY_COLUMN_GENDER + " TEXT, " +
                SURVEY_COLUMN_LAST_EDUCATION+ " TEXT, " +
                SURVEY_COLUMN_WANT_TO_BE + " TEXT) ";
    }

    public long insertInUserTable(String name, String username, String email, String phone,
                                  String password) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PHONE, phone);
        contentValues.put(COLUMN_PASSWORD, password);

        return db.insert(USER_TABLE_NAME, null, contentValues);
    }

    public long insertInSurveyTable(String name, String address, int age, String gender,
                                    String lasteducation, String wanttobe) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(SURVEY_COLUMN_NAME, name);
        contentValues.put(SURVEY_COLUMN_ADDRESS, address);
        contentValues.put(SURVEY_COLUMN_AGE, age);
        contentValues.put(SURVEY_COLUMN_GENDER, gender);
        contentValues.put(SURVEY_COLUMN_LAST_EDUCATION, lasteducation);
        contentValues.put(SURVEY_COLUMN_WANT_TO_BE, wanttobe);

        return db.insert(SURVEY_TABLE_NAME, null, contentValues);
    }

    public boolean checkUserExists(String username){
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " +
                        COLUMN_USERNAME + " =?", new String[]{username});

        if(cursor.getCount() >= 1){
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean checkUserLogin(String username, String password){
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " +
                COLUMN_USERNAME + " =?", new String[]{username});

        if(cursor.getCount() >= 1){
            cursor.moveToFirst();
            if(password.equals(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))){
                cursor.close();
                return true;
            } else {
                cursor.close();
                return false;
            }
        } else {
            cursor.close();
            return false;
        }
    }

    public Cursor getAllSurvey() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + SURVEY_TABLE_NAME, null);
    }

    public Cursor getUserDetails(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " +
                COLUMN_USERNAME + " =?", new String[]{username});
    }

}
