package com.example.yamanmnur.belajarsqllite;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyAdapter extends SQLiteOpenHelper {

    // ------properti atau variable-----
    public static final String DATABASE_NAME = "ujang";
    public static final int DATABASE_VERSION = 1;
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String TABLE_NAME = "user";

    private Context context;
    // ------properti-------------------

    public MyAdapter(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
        Message.message(context,"Contructor di panggil");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name VARCHAR(40), password VARCHAR(24))";
        try {
            db.execSQL(CREATE_TABLE);
            Message.message(context,"data berhasil di load");
        }catch (SQLException e){
            Message.message(context,"gagal");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS user";
        try{
            db.execSQL(DROP_TABLE);
            onCreate(db);
            Message.message(context,"baso");
        }catch (SQLException e){
            Message.message(context,"gagal");
        }
    }
}
