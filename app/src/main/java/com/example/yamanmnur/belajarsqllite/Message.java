package com.example.yamanmnur.belajarsqllite;
import android.content.Context;
import android.widget.Toast;

public class Message {
    public static void message(Context context, String message){
        Toast.makeText(context, "berhasil", Toast.LENGTH_SHORT).show();
    }
}
