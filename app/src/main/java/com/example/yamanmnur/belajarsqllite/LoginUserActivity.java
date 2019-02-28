package com.example.yamanmnur.belajarsqllite;

  import android.database.Cursor;
  import android.database.sqlite.SQLiteDatabase;
  import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
 import android.widget.Toast;

public class LoginUserActivity extends AppCompatActivity {
    EditText Email, Password;
    DataHelper dbhelper;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);

        dbhelper = new DataHelper(this);
        Button register = (Button) findViewById(R.id.register);
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getReadableDatabase();
                cursor = db.rawQuery("SELECT * FROM user WHERE email = '" +
                        Email.getText().toString()+"'",null);
                cursor.moveToFirst();

                if (cursor.getCount()>0){
                    Toast.makeText(LoginUserActivity.this, "Data ada", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginUserActivity.this, "data tidak ada", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                db.execSQL("insert into user(nama,email,password) values('anma','nama@gmail.com','password')");
                Toast.makeText(getApplicationContext(),"berhasil",Toast.LENGTH_LONG).show();

            }
        });


    }
}
