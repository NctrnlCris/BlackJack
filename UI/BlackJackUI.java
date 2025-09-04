package UI;

import java.util.Scanner;

import Classes.BlackJack;
import Exceptions.BustException;
import Exceptions.CutCardException;

public class BlackJackUI {

    private static BlackJack bj = new BlackJack(2);
    private static Scanner sc = new Scanner(System.in);
    private static Boolean quit = false;
    private static String command = "";
    private static Boolean completed = false;
    
    public static void main(String[] args) {
        System.out.println("Welcome to BlackJack Preparing your game");
        while (!quit) {
            playBlackJack();
        }
        System.out.println("Thanks for playing");
    } 

    private static void playBlackJack() {
            try {
                placeBet();
                dealGame();
            } catch (CutCardException e) {
                bj.setLastHandTrue();
                reshuffle();
            }
            try { 
                while (!completed) {
                    command = sc.nextLine();
                    findCommand(command);
                } 
            } catch (CutCardException e) {
                bj.setLastHandTrue();
                reshuffle();
            }
            bj.clearTable();
            // Handle game after player action is done

    }

    private static void placeBet() {
         int bet = 0;
         while (bet == 0) {
            System.out.println("Please enter your bet!");
            bet = Integer.parseInt(sc.nextLine());
         }
         bj.placeBet(bet);
    }

    private static void findCommand(String s) throws CutCardException {
        switch (s) {
            case "h":
                hit();
                break;
            
            case "s":
                stand();
                break;

            case "d":
                doubleDown();
                break;

            // case "q":
            //     quit = true;
            //     break;
        }
    }

    private static void dealGame() throws CutCardException {
        bj.dealHands();
        System.out.println("Player total is: " + bj.getPlayerTotal());
        System.out.println("Dealer total is: " + bj.getDealerTotal());
    }

    private static void reshuffle() {

    }

    private static void hit() throws CutCardException {
        try {
            bj.hitPlayer();
            System.out.println("Player total is: " + bj.getPlayerTotal());
        } catch (BustException e) {
            System.out.println("Player total is: " + bj.getPlayerTotal());
            System.out.println("Too many!");
            completed = true;
        }
        System.out.println("Player total is: " + bj.getPlayerTotal());
    }

    private static void stand() {

    }

    private static void doubleDown() {

    }

}
