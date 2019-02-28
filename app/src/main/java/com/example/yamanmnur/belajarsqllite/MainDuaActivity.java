package com.example.yamanmnur.belajarsqllite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainDuaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dua);
        MyAdapter my = new MyAdapter(this);
        SQLiteDatabase db = my.getWritableDatabase();




    }
    public void tambahData(View v){
        TextView b = (TextView) findViewById(R.id.namaText);

        EditText Nama = (EditText) findViewById(R.id.Nama);
        EditText Password = (EditText) findViewById(R.id.Password);

        MyAdapter my = new MyAdapter(this);
        SQLiteDatabase db = my.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(my.NAME,Nama.getText().toString());
        contentValues.put(my.PASSWORD,Nama.getText().toString());

        //proses insert data
        long id = db.insert(my.TABLE_NAME,null,contentValues);
        if(id <= 0){
            Message.message(this,"data berhasil di load");
        }else{
            Toast.makeText(this, "berhasil dimasukn", Toast.LENGTH_SHORT).show();
        }

        String[] columns = {my.ID,my.NAME,my.PASSWORD};

        Cursor cursor = db.query(my.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer = new StringBuffer();
        String name = null;

        while(cursor.moveToNext()){
            int cid = cursor.getInt(cursor.getColumnIndex(my.ID));
            name = cursor.getString(cursor.getColumnIndex(my.NAME));
            buffer.append("nama : "+name+"\n");
        }
        b.setText(buffer.toString());

    }
    public void hapusSemua(View view){
        MyAdapter my = new MyAdapter(this);

        SQLiteDatabase db = my.getWritableDatabase();
        //proses pengahapusan data
        db.delete(my.TABLE_NAME,null,null);
    }
    public void pindah(View view){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}
