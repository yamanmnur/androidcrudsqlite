package com.example.yamanmnur.belajarsqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected  Cursor cursor;

    DataHelper dbcenter;

    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Button btn = (Button) findViewById(R.id.button2);

         btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(MainActivity.this, BuatBiodata.class);
                startActivity(inte);
            }
        });

        ma = this;

        dbcenter = new DataHelper(this);

         RefreshList();
        Log.d("hai","hai");
    }


    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM biodata",null);
        daftar = new String[cursor.getCount()];


        //cursor.moveToNext();
        int cc=0;
        while(cursor.moveToNext()){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
            cc++;
        }
        //cursor.moveToFirst();
        //cursor.moveToPosition(0);
        //Log.d(TAG, "RefreshList: ");
       //
        //daftar[0] = cursor.getString(1).toString();
        //Toast.makeText(ma, "", Toast.LENGTH_SHORT).show();

        //for (int cc=0; cc < cursor.getCount(); cc++){
            //cursor.moveToPosition(cc);

        //}

        ListView01 = (ListView) findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,daftar
                ));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {

                final String selection = daftar[arg2];
                final CharSequence[] dialogItem = {"Lihat Biodata","Update Biodata","Hapus Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("pilihan");

                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent i = new Intent(getApplicationContext(), LihatBiodata.class);
                                i.putExtra("nama",selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateBiodata.class);
                                in.putExtra("nama",selection);
                                startActivity(in);
                                break;
                            case 2 :
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from biodata where nama = '"+selection+"'");
                                RefreshList();
                                break;

                        }
                    }
                });
                builder.create().show();
            }
        });
       Button btn5 = (Button) findViewById(R.id.button5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lgn = new Intent(getApplicationContext(), LoginUserActivity.class);
                startActivity(lgn);
            }
        });
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
