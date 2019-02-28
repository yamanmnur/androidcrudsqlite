package com.example.yamanmnur.belajarsqllite;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper{

    private static  final String DATABASE_NAME = "biodatadi.db";
    private static final int DATABASE_VERSION = 1;
 /*   private static final String TABLE_USER_NAME = "user";
    private static final String ID = "id_user";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";*/

     public DataHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
     }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table biodata(no integer primary key," +
                "nama text null, tgl text null, jk text null, alamat text null);" ;
        String tb_user = "create table user(id_user integer primary key autoincrement," +
                "nama text null, email text null,password text null );" ;

         Log.d("data","onCreate: "+ sql);
        db.execSQL(sql);

         sql = "INSERT INTO biodata (no, nama, tgl, jk, alamat)" +
                "values('1001','fathur','1994-02-03','laki laki','jakarta');";

        db.execSQL(tb_user);
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
/*
    public long register(String email,String password){
        SQLiteDatabase dbb = dataHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(dataHelper.EMAIL, email );
        contentValues.put(dataHelper.PASSWORD, password);
        long id = dbb.insert(dataHelper.TABLE_USER_NAME, null, contentValues);
        return id;
    }*/
}
