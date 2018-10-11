package com.example.ukeje.tictactoe;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/* Author: Ukeje Emeka
    Email: ukejee3@gmail.com */


public class GameBoardActivity extends AppCompatActivity {

    Game mainGame = new Game(this);
    ArrayList<Integer> list = new ArrayList<>(10);

    int[] btnIdArray = {R.id.button1, R.id.button2, R.id.button3, R.id.button6, R.id.button5,
            R.id.button4, R.id.button7, R.id.button8, R.id.button9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        mainGame.setupGame();

    }

    public ArrayList<Integer> onButtonClick(View view) {
        list.add(R.id.button1);
        list.add(R.id.button2);
        list.add(R.id.button3);
        list.add(R.id.button6);
        list.add(R.id.button5);
        list.add(R.id.button4);
        list.add(R.id.button7);
        list.add(R.id.button8);
        list.add(R.id.button9);
        mainGame.processPlayerTurn(list.indexOf(view.getId()) + 1, view);
        mainGame.gameOver();
        if(mainGame.isGameOver == true){
            openDialog();
        }
        return list;

    }


        public void openDialog () {

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            // Set Custom Title
            TextView title = new TextView(this);
            // Title Properties
            title.setText("Game Over");
            title.setPadding(10, 10, 10, 10);   // Set Position
            title.setGravity(Gravity.CENTER);
            title.setTextColor(Color.BLACK);
            title.setTextSize(20);
            alertDialog.setCustomTitle(title);

            // Set Message
            TextView msg = new TextView(this);
            // Message Properties
            msg.setText("Do you wish to play again?");
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
                    moveTaskToBack(true);
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



