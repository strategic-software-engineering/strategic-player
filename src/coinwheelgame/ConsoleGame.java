package coinwheelgame;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A console interface for a user to play the game
 *
 * @author Jon Bowen, James Niedfeldt, Evan Ballinger, Doug McLaughlin, Levi Portenier
 * @version 0.0.1
 */
public class ConsoleGame {
    private int PLAY_GAME = 1;
    private int QUIT = 2;

    private Wheel wheel;

    public ConsoleGame() {
        printWelcome();

        int option = getPlayerOption();

        if (option == PLAY_GAME) {
            System.out.println("Please enter desired number of slots:");
            System.out.print("> ");
            Scanner consoleIn = new Scanner(System.in);
            try {
                play(consoleIn.nextInt());
            } catch (InputMismatchException e) {
                System.out.println("***ERROR: Please enter an integer");
            }
        } else {
            System.out.println("Thank you for playing!");
            System.out.println("Exiting the game...");
        }
    }

    /**
     * Plays one game with a wheel of numSlots slots
     *
     * @param numSlots The number of slots to play the game with
     */
    public void play(int numSlots) {
        this.wheel = new Wheel(numSlots);
        System.out.println(this.wheel.wheelInfoAsString() + System.lineSeparator());

        /*for (int i = 0; i < 5; i++) {
            this.wheel.spin();
            System.out.println(this.wheel.wheelInfoAsString() + System.lineSeparator());
        }*/

    }

    /**
     * Print a welcome to the player
     */
    public void printWelcome() {
        System.out.println("*********************************");
        System.out.println("*** Welcome to the Wheel Game ***");
        System.out.println("*********************************");
    }

    /**
     * Print game options to proceed
     */
    public void printOptions() {
        System.out.println("Game options:");
        System.out.println("\t1. Play game");
        System.out.println("\t2. Quit");
    }

    /**
     * Get a game option from the user
     *
     * @return User selected option
     */
    public int getPlayerOption() {
        int option = -1;
        while (option != PLAY_GAME && option != QUIT) {
            printOptions();
            System.out.println("Please enter a displayed option number:");
            System.out.print("> ");
            Scanner consoleIn = new Scanner(System.in);
            try {
                option = consoleIn.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("***ERROR: Please enter an integer for a displayed option");
            }
        }
        return option;
    }
}

