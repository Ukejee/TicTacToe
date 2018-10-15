package com.example.ukeje.tictactoe;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Author: Ukeje Emeka
    Email: ukejee3@gmail.com */


public class GameBoardActivity extends AppCompatActivity {

    Game mainGame = new Game(this);
    List<Integer> btnIdArray = Arrays.asList(R.id._tile_0, R.id._tile_1, R.id._tile_2, R.id._tile_3, R.id._tile_4,
            R.id._tile_5, R.id._tile_6, R.id._tile_7, R.id._tile_8);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);
        setupButtons();
        mainGame.setupGame();


    }

    private void setupButtons(){
        for (int buttonId : btnIdArray){
            Button button = findViewById(buttonId);
            button.setTextSize(70);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onButtonClick(v);
                }
            });
        }
    }

    public void onButtonClick(View view) {

        mainGame.processPlayerTurn(btnIdArray.indexOf(view.getId()) + 1, view);

        if(mainGame.hasGameEnded()){
            openDialog();
        }

    }


        public void openDialog () {

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            // Set Custom Title
            TextView title = new TextView(this);
            // Title Properties
            title.setText(R.string.game_over);
            title.setPadding(10, 10, 10, 10);   // Set Position
            title.setGravity(Gravity.CENTER);
            title.setTextColor(Color.BLACK);
            title.setTextSize(20);
            alertDialog.setCustomTitle(title);

            // Set Message
            TextView msg = new TextView(this);
            // Message Properties
            msg.setText(R.string.play_again_text);
            msg.setGravity(Gravity.CENTER_HORIZONTAL);
            msg.setTextColor(Color.BLACK);
            alertDialog.setView(msg);

            // Set Button
            // you can more buttons
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Perform Action on Button
                    mainGame.processNewGame();
                }
            });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Perform Action on Button
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                    //finish();
                }
            });

            new Dialog(getApplicationContext());
            alertDialog.show();

            // Set Properties for OK Button
            final Button okBT = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
            LinearLayout.LayoutParams neutralBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
            neutralBtnLP.gravity = Gravity.FILL_HORIZONTAL;
            okBT.setPadding(50, 10, 10, 10);   // Set Position
            okBT.setTextColor(Color.BLUE);
            okBT.setLayoutParams(neutralBtnLP);

            final Button cancelBT = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
            LinearLayout.LayoutParams negBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
            negBtnLP.gravity = Gravity.FILL_HORIZONTAL;
            cancelBT.setTextColor(Color.RED);
            cancelBT.setLayoutParams(negBtnLP);
        }
    }



