import java.util.Random;
import java.util.Scanner;

public class MUGWUMP {
    // Global variables to be used throughout the program
    private static final int gridSize = 10;
    private static final int numMugwumps = 4;
    private static final int maxTries = 10;
    private static int[][] mugwumps = new int[numMugwumps][2]; // create an array to store the mugwumps
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("THE OBJECT OF THIS GAME IS TO FIND FOUR MUGWUMPS");
		System.out.println("HIDDEN ON A 10 BY 10 GRID. HOMEBASE IS POSITION 0,0");
		System.out.println("ANY GUESS YOU MAKE MUST BE TWO NUMBERS WITH EACH");
		System.out.println("NUMBER BETWEEN 0 AND 9; INCLUSIVE. FIRST NUMBER");
		System.out.println("IS DISTANCE TO RIGHT OF HOMEBASE AND SECOND NUMBER");
		System.out.println("IS DISTANCE ABOVE HOMEBASE.");
		System.out.println();
        System.out.println("YOU GET 10 TRIES. AFTER EACH TRY, I WILL TELL");
        System.out.println("YOU HOW FAR YOU RRE FROM EACH MUGWUMP. ");
        System.out.println();

		do {
            playGame();
            System.out.println("THAT WAS FUN! LET'S PLAY AGAIN....");
            System.out.println("FOUR MORE MUGWUMPS ARE NOW IN HIDING.");
        } while (true);
    }

    private static void playGame() {
        placeMugwumps();
        int tries = 0;

        while (tries < maxTries) {
            tries++;
            System.out.println();
            System.out.println("TURN NO. " + tries + " WHAT IS YOUR GUESS?");
            int xCoordinate = scanner.nextInt();
            int yCoordinate = scanner.nextInt();

            boolean foundAll = true; // Initialize to true at the start of each turn

            for (int counter = 0; counter < numMugwumps; counter++) {
                if (mugwumps[counter][0] == -1) continue; // Skip already found Mugwumps
                if (mugwumps[counter][0] == xCoordinate && mugwumps[counter][1] == yCoordinate) {
                    mugwumps[counter][0] = -1; // Mark Mugwump as found
                    System.out.println("YOU HAVE FOUND MUGWUMP " + (counter + 1));
                } else {
                    double distance = Math.sqrt(Math.pow(mugwumps[counter][0] - xCoordinate, 2) + Math.pow(mugwumps[counter][1] - yCoordinate, 2));
                    System.out.printf("YOU ARE %.1f UNITS FROM MUGWUMP %d%n", distance, counter + 1);
                    foundAll = false; // Set to false if any Mugwump is not found
                }
            }

            if (foundAll) {
                System.out.println();
                System.out.println("YOU GOT THEM ALL IN " + tries + " TURNS!");
                return; // End the game if all Mugwumps are found
            }
        }

        System.out.println();
        System.out.println("SORRY, THAT'S 10 TRIES. HERE IS WHERE THEY'RE HIDING:");
        for (int counter = 0; counter < numMugwumps; counter++) {
            if (mugwumps[counter][0] != -1) {
                System.out.printf("MUGWUMP " + (counter + 1) + " IS AT (" + mugwumps[counter][0] + "," + mugwumps[counter][1] + "); ");
            }
        }
    }

    private static void placeMugwumps() {
        for (int counter = 0; counter < numMugwumps; counter++) {
            mugwumps[counter][0] = random.nextInt(gridSize);
            mugwumps[counter][1] = random.nextInt(gridSize);
        }
    }
}
