package UI;

import java.util.Scanner;

import Classes.BlackJack;
import Exceptions.CutCardException;

public class BlackJackUI {

    private static BlackJack bj = new BlackJack(2);
    private static Scanner sc = new Scanner(System.in);
    private static Boolean quit = false;
    
    public static void main(String[] args) {
        System.out.println("Welcome to BlackJack Preparing your game");
        while (!quit) {
            placeBet();
            try {
                bj.dealHands();
            } catch (CutCardException e) {
                bj.setLastHandTrue();
            }
            String command = sc.nextLine();
            findCommand(command);
        }
        System.out.println("Thanks for playing");
    } 

    private static void placeBet() {
         int bet = 0;
         while (bet == 0) {
            System.out.println("Please enter your bet!");
            bet = Integer.parseInt(sc.nextLine());
         }
         bj.placeBet(bet);
    }

    private static void findCommand(String s) {

    }

    private static void reshuffle() {

    }

}
