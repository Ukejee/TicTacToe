package com.example.ukeje.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

/* Author: Ukeje Emeka
    Email: ukejee3@gmail.com */


public class Player {

    String name;
    String signature;
    int score;
    int userSlot;

    public Player(String userName, String userSignature, int userScore ) {

        name = userName;
        signature = userSignature;
        score = userScore;
    }

    public int takeTurn(Board board) {

        //loop until user gives us a valid input
        Scanner in = new Scanner(System.in);
        int userSlot = 0;
        System.out.println(name + " Your turn");

        boolean inputNotValid = true;
        while(inputNotValid) {
            try {
                System.out.println(name + " enter slot number: ");
                userSlot = in.nextInt();
                //in.nextLine();
                if(board.checkSlotValidity(userSlot)) {
                    break;
                }

            }catch(InvalidSlotException e) {
                System.out.println(e.getMessage());
            }catch(InputMismatchException e) {
                System.out.println("Characters are not allowed !!! "
                        + "Please enter a valid number between 1 - 9");
                in.nextLine();

            }catch(Exception e) {

                System.out.println("Please enter a valid number between 1 - 9");
            }

        }

        return userSlot;
    }
}
