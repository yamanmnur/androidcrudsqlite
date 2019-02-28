package com.example.yamanmnur.belajarsqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserAdapter {
    UserDataHelper userDataHelper;
    public UserAdapter(Context context){
        userDataHelper = new UserDataHelper(context);
    }

    public long register(String email,String password){
        SQLiteDatabase dbb = userDataHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(userDataHelper.EMAIL, email);
        contentValues.put(userDataHelper.PASSWORD, password);
        long id = dbb.insert(userDataHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    static class UserDataHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "newDatabase.db";
        private static final String TABLE_NAME = "user";
        private static final int DATABASE_Version = 1;
        private static final String UID = "id";
        private static final String EMAIL = "email";
        private static final String PASSWORD = "password";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"(" +
                UID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                EMAIL+" VARCHAR(100)," +
                PASSWORD+" VARCHAR(100));";
        private Context context;

        public UserDataHelper(Context context){
            super(context, DATABASE_NAME,null, DATABASE_Version);

            this.context = context;
        }
        public void onCreate(SQLiteDatabase db){
            try{
                db.execSQL(CREATE_TABLE);
            }catch (Exception e){

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
