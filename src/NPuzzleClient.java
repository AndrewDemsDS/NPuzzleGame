/**
 * Author: Andreas Demosthenous
 * Written: 14/10/2023
 * Last updated: 29/10/2023
 * <p>
 * Compilation: javac -cp path/to/stdlib.jar NPuzzleClient.java NPuzzleLib.java
 * Execution(linux/macOS): java -cp .:/path/to/stdlib.jar NPuzzleClient N d
 * Execution(Windows): java -cp .;/path/to/stdlib.jar NPuzzleClient N d
 * <p>
 * The program spits into the main program and the lib:
 * NPuzzleLib is the underlying Java class that provides the essential functions and methods for managing and solving the N-Puzzle game,
 * encompassing tasks such as initializing the puzzle, shuffling, checking for completion, and executing moves.
 * NPuzzleClient is the central program that serves as a user-friendly interface for N-Puzzle interactions.
 * It empowers users to make choices between two distinct modes: interactive play, where they can set the puzzle's level of challenge, or automatic play,
 * which permits them to specify various parameters for solving the puzzle across different difficulty levels.
 * This program also handles command-line arguments, enabling users to define the puzzle's size and preferred display mode.
 */
public class NPuzzleClient {
    public static void main(String[] args) {
        if(args.length == 0 || args.length == 1) {
            System.out.println("No arguments for the size of the puzzle or the display mode");
            return;
        }
        int N = Integer.parseInt(args[0]); // Get puzzle size from command line arguments
        int d = Integer.parseInt(args[1]); // Get display mode from command line arguments
        if (N <= 1 || (d !=0 && d != 1 && d !=2)){
            System.out.println("Wrong commandline arguments. Give N and d ");
            return;
        }
        // Display a menu for user to choose an option
        System.out.print("""
                +-------------------------+
                |1. Interactive play      |
                |2. Automatic play        |
                |3. Exit                  |
                +-------------------------+
                """);
        System.out.print("Choose Option: ");
        int mode = Integer.parseInt(NPuzzleLib.getUserCommand()); // Read user's choice
        while (mode < 1 || mode > 3) {
            System.out.println("Wrong mode given choose 1, 2 or 3: ");
            mode = Integer.parseInt(NPuzzleLib.getUserCommand()); // Read user's choice
        }
        if (mode == 3)
            return; // Exit the program
        else if (mode == 1) {
            System.out.println("*******************************");
            System.out.print("Give level of difficulty: ");
            int difficulty = Integer.parseInt(NPuzzleLib.getUserCommand()); // Read user's chosen difficulty
            System.out.println("*******************************");
            NPuzzleLib.initializePuzzle(N); // Initialize the puzzle
            NPuzzleLib.shufflePuzzle(difficulty); // Shuffle the puzzle
            if(d==1) // Switch between different display modes
                NPuzzleLib.displayPuzzle(); // Display the puzzle
            else if (d == 2){
                StdDraw.setCanvasSize(600, 600); // Adjust the size as needed
                NPuzzleLib.displayPuzzleGraph();}
            NPuzzleLib.play(d); // Start interactive play
        }
        else {
            System.out.print("Dwse kmin: ");
            int kmin = Integer.parseInt(NPuzzleLib.getUserCommand()); // Read kmin value
            System.out.print("Dwse kmax: ");
            int kmax = Integer.parseInt(NPuzzleLib.getUserCommand()); // Read kmax value
            System.out.print("Dwse p: ");
            int q = Integer.parseInt(NPuzzleLib.getUserCommand()); // Read p value
            System.out.print("Dwse q: ");
            int p = Integer.parseInt(NPuzzleLib.getUserCommand()); // Read q value
            NPuzzleLib.initializePuzzleK(N); // Initialize the puzzle with a shuffled configuration
            NPuzzleLib.solvePuzzle(p, kmin, kmax, q); // Solve the puzzle for the specified parameters
        }
      if (d == 2 && mode != 2){

          StdDraw.setPenColor(StdDraw.BLACK);
          if(NPuzzleLib.isSolution()){
              StdDraw.pause(1000);
              StdDraw.clear();
              StdDraw.text((double) N/2, (double)N/2, "Congratulations, puzzle solved!!" );
              StdDraw.pause(3000);
              StdDraw.clear();
          }
          StdDraw.clear();
          StdDraw.text((double) N/2, (double)N/2, "Closing Puzzle in 3 Seconds..." );
          StdDraw.pause(3000);
          System.exit(0);}
    }
}
