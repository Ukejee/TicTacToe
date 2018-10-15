package com.example.ukeje.tictactoe;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/* Author: Ukeje Emeka
    Email: ukejee3@gmail.com */

public class Board {

    String board [] = new String[9];
    boolean gameWon = false;
    ArrayList<Integer> winningArray = new ArrayList<>();

    /**
     * game has ended either when all the tiles have been filled or a user has won
     * @return
     */
    public boolean hasGameEnded() {

        //someone has won
        if(gameWon) {
            return true;
        }

        //check if all the tiles are filled
        boolean doesBoardContainEmptySpace = false;
        for(int i = 0 ; i < board.length; i++) {
            String value = board[i];
            if(value.trim().equalsIgnoreCase("")) {
                doesBoardContainEmptySpace = true;
                break;
            }
        }
        //if there is no empty space
        if(!doesBoardContainEmptySpace) {
            return true;
        }
        return false;
    }


    public void initializeBoard() {

        for(int i = 0; i < 9; i++) {

            board[i] =  "";

        }
        // ui.initializeUI();
    }

    public Player checkWinnerV2(Player p1, Player p2) {

        String[] winningCombinations = {"012", "345", "678", "036", "147", "258", "048", "246"};
        Player playerWhoWon = null;

        for(String combination : winningCombinations) {
            //if a combination has the same value in the array a winner has been established

            int x = Integer.valueOf(combination.substring(0,1));
            int y = Integer.valueOf(combination.substring(1,2));
            int z = Integer.valueOf(combination.substring(2,3));

            //if it is not empty
            if(!board[x].trim().equals("")) {
                //if the value is equal to the other 2 fields in the winning combo
                if(board[x].trim().equalsIgnoreCase(board[y].trim()) &&
                        board[x].trim().equalsIgnoreCase(board[z].trim())){

                    playerWhoWon = (p1.signature.equalsIgnoreCase(board[x].trim())) ? p1 : p2;
                    //break out of loop cause we have found a winner
                    System.out.println(playerWhoWon.name + " is the winner!!!!!");
                    playerWhoWon.score++;
                    gameWon = true;
                    break;
                }
            }
        }

        return playerWhoWon;
    }

    public ArrayList<Integer> getWinningArray(Player p1, Player p2) {

        String[] winningCombinations = {"012", "345", "678", "036", "147", "258", "048", "246"};
        Player playerWhoWon = null;
        int first = 0;


        for(String combination : winningCombinations) {
            //if a combination has the same value in the array a winner has been established

            int x = Integer.valueOf(combination.substring(0,1));
            int y = Integer.valueOf(combination.substring(1,2));
            int z = Integer.valueOf(combination.substring(2,3));

            //if it is not empty
            if(!board[x].trim().equals("")) {
                //if the value is equal to the other 2 fields in the winning combo
                if(board[x].trim().equalsIgnoreCase(board[y].trim()) &&
                        board[x].trim().equalsIgnoreCase(board[z].trim())){

                    playerWhoWon = (p1.signature.equalsIgnoreCase(board[x].trim())) ? p1 : p2;
                    winningArray.add(x);
                    winningArray.add(y);
                    winningArray.add(z);
                }
            }
        }

        return winningArray;
    }

    public boolean checkSlotValidity(int slotNo) throws InvalidSlotException {
        //if it is in the range of 1 - board size and it has not been filled
        if(slotNo >= 1 && slotNo <= board.length) {

            //if that slot has not been filled it is now valid
            if(board[slotNo - 1].trim().equals("")) {
                return true;
            }else {
                throw new InvalidSlotException("Slot has already been filled, please choose another");
            }
        }else {
            throw new InvalidSlotException("Invalid Slot no : " + slotNo + " slot number must be between 1 - 9");
        }

        //return false;
    }


    public void fillSlot(int slotNo, String symbol) throws ArrayIndexOutOfBoundsException {

        board[slotNo - 1] = symbol;

    }


}
