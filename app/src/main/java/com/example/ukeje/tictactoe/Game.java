package com.example.ukeje.tictactoe;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;


/* Author: Ukeje Emeka
    Email: ukejee3@gmail.com */

public class Game {
    Player p1;
    Player p2;
    Board board;
    boolean isPlayer1Turn = true;
    boolean newGame = false;
    boolean isGameOver = false;
    private GameBoardActivity gameBoard;
    Context context;

    public Game(Context context){
        this.context=context;
    }

    public void clearButtonText(){
        for(int i = 0; i<gameBoard.btnIdArray.size();i++) {
            addButtonText(" ",((GameBoardActivity)context).findViewById(gameBoard.btnIdArray.get(i)));
        }
    }

    public void clearButtonColor(){
        for(int i = 0; i<gameBoard.btnIdArray.size(); i++){
            Button buttons = (Button) ((GameBoardActivity)context).findViewById(gameBoard.btnIdArray.get(i));
            buttons.setTextColor(Color.BLACK);
        }
    }

    public void clearText(){
        TextView txtView = (TextView) ((GameBoardActivity)context).findViewById(R.id.textView);
        txtView.setText(" ");
    }

    public void addText(String word){
        TextView txtView = (TextView) ((GameBoardActivity)context).findViewById(R.id.textView);
        txtView.append(word);
        txtView.append("\n");
        txtView.setMovementMethod(new ScrollingMovementMethod());
    }

    public void addButtonText(String sign, View view){
        Button buttons = (Button) ((GameBoardActivity)context).findViewById(view.getId());
        buttons.setText(sign);
    }

    public void changeButtonColor(int i){
        Button buttons = (Button) ((GameBoardActivity)context).findViewById(gameBoard.btnIdArray.get(i));
        buttons.setTextColor(Color.RED);
    }

    public void setScoreBoard(int p1Point, int p2Point){
        TextView p1Score = ((GameBoardActivity)context).findViewById(R.id.p1_score);
        TextView p2Score = ((GameBoardActivity)context).findViewById(R.id.p2_score);;

        p1Score.setText("" + p1Point);
        p2Score.setText("" + p2Point);
    }

    public void setupGame(){

        //welcome message
        System.out.println("about to set up the game");
        //create board
        board = new Board();
        board.initializeBoard();

        //create board
        gameBoard = new GameBoardActivity();



        //create players
        p1 = new Player("Player 1", "X",0);
        p2 = new Player("Player 2", "O",0);


       displayWelcomeMessage();
    }

    public Player getCurrentPlayer() {
        //setting player one to play first
        return (isPlayer1Turn)? p1: p2;
    }

    public void displayWelcomeMessage() {
        //welcome message
        addText("===============================================");
        addText("                  TicTacToe                    ");
        addText("===============================================");
        //ui.println("   ");
        addText("Player 1 is X");
        addText("Player 2 is O");
        //ui.println(" ");
        addText(getCurrentPlayer().name + " will start first");
        //ui.displayText();
    }



    public void processPlayerTurn(int selectedSquare,View view) {
        System.out.println("this is the number " +selectedSquare );

        gameBoard = new GameBoardActivity();
        //loop until game has been won or game over
        //get the current player
        if(board.hasGameEnded()) return;
        System.out.println("this is here");
        try {
            if(board.checkSlotValidity(selectedSquare)) {
                //validate board position
                board.fillSlot(selectedSquare, getCurrentPlayer().signature);
                addButtonText(getCurrentPlayer().signature,view);
               // gameBoard.setButtonText(btnId, getCurrentPlayer().signature);
                Player winningPlayer = board.checkWinnerV2(p1, p2);

                if(board.hasGameEnded()) {
                    System.out.println("game has ended");
                    //perform winner check

                    if(winningPlayer == null) {
                        addText("Game ended in a draw");
                        isGameOver = false;
                    }else {
                        System.out.println("Send message to server for winner");
                        board.getWinningArray(p1, p2);

                        changeButtonColor(board.winningArray.get(0));
                        changeButtonColor(board.winningArray.get(1));
                        changeButtonColor(board.winningArray.get(2));
                       addText(winningPlayer.name + " is the winner");
                       setScoreBoard(p1.score,p2.score);
                       isGameOver = false;
                       //gameBoard.openDialog();
                    }


                }else {
                    // change the current player
                    isPlayer1Turn = !isPlayer1Turn;
                    addText(getCurrentPlayer().name + " your turn" );

                    if(isPlayer1Turn){
                        TextView p2Name = ((GameBoardActivity)(context)).findViewById(R.id.p2_name);
                        TextView p2Sign = ((GameBoardActivity)(context)).findViewById(R.id.p2_sign);
                        TextView p1Name = ((GameBoardActivity)(context)).findViewById(R.id.p1_name);
                        TextView p1Sign = ((GameBoardActivity)(context)).findViewById(R.id.p1_sign);

                        p2Name.setTextColor(ContextCompat.getColor(context, R.color.displayTextColorDark));
                        p2Sign.setTextColor(ContextCompat.getColor(context, R.color.displayTextColorDark));
                        p1Name.setTextColor(ContextCompat.getColor(context, R.color.displayTextColor));
                        p1Sign.setTextColor(ContextCompat.getColor(context, R.color.displayTextColor));

                    }
                    else{
                        TextView p2Name = ((GameBoardActivity)(context)).findViewById(R.id.p2_name);
                        TextView p2Sign = ((GameBoardActivity)(context)).findViewById(R.id.p2_sign);
                        TextView p1Name = ((GameBoardActivity)(context)).findViewById(R.id.p1_name);
                        TextView p1Sign = ((GameBoardActivity)(context)).findViewById(R.id.p1_sign);

                        p2Name.setTextColor(ContextCompat.getColor(context, R.color.displayTextColor));
                        p2Sign.setTextColor(ContextCompat.getColor(context, R.color.displayTextColor));
                        p1Name.setTextColor(ContextCompat.getColor(context, R.color.displayTextColorDark));
                        p1Sign.setTextColor(ContextCompat.getColor(context, R.color.displayTextColorDark));
                    }
                }

            }


        }catch(InvalidSlotException e) {
            addText(e.getMessage());
        }

    }

    public boolean hasGameEnded(){
        if(board.hasGameEnded()){
            isGameOver = true;
        }
        return isGameOver;
    }

    public void processNewGame() {
        this.newGame = true;
        //ui.clearScreen();
        //ui.clearButton();
        clearText();
        clearButtonText();
        displayWelcomeMessage();
        setUpNewGame();
        clearButtonColor();

    }

    public void setUpNewGame() {
        //reintialize values
        board = new Board();
        board.initializeBoard();
        this.isGameOver = false;

    }

}
