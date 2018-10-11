package com.example.ukeje.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/* Author: Ukeje Emeka
    Email: ukejee3@gmail.com */


public class StartPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_start_page);
        setContentView(R.layout.board);
    }

    public void onClickStart(View view){
        Intent intent = new Intent(this, GameBoardActivity.class);
        startActivity(intent);
    }

    public void onClickExit(View view){
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);


    }
}
