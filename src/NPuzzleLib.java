import java.util.Scanner;
/**
 * NPuzzleLib class used by the client has many methods, for initializing the puzzle, shuffling the puzzle and displaying it
 */
public class NPuzzleLib {
    public static int[][] board; // Global array for the puzzle board
    public static int emptyRow; // Global variable to keep track of the empty row position
    public static int emptyCol; // Global variable to keep track of the empty column position
    static Scanner stdin = new Scanner(System.in);

    /**
     * Method to get a user command (input from the user)
     * returns a string
     */
    public static String getUserCommand() {
        return stdin.next();
    }

    /**
     * Method Initialize the puzzle with numbers from 1 to N^2-1
     * and an empty cell in the bottom-right corner used for interactive mode
     */
    public static void initializePuzzle(int N) {
        board = new int[N][N];
        int tile = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                board[row][col] = tile;
                tile++;
            }
        }
        board[N - 1][N - 1] = 0; // The empty cell is represented by 0
        emptyRow = N - 1; // Initialize the empty cell's row position
        emptyCol = N - 1; // Initialize the empty cell's column position
    }

    /**
     * Method Initialize the puzzle with numbers from 1 to N^2-1
     * and an empty cell in the bottom-right corner used for automatic mode
     */
    public static void initializePuzzleK(int N) {
        board = new int[N][N];
        int tile = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                board[row][col] = tile;
                tile++;
            }
        }
        board[N - 1][N - 1] = 0; // The empty cell is represented by 0
        emptyRow = N - 1; // Initialize the empty cell's row position
        emptyCol = N - 1; // Initialize the empty cell's column position

    }

    /**
     * Method to shuffle the puzzle within the specified difficulty range [kmin, kmax]
     * Shuffle the puzzle with k random valid moves using Math.random
     */
    public static void shufflePuzzle(int k) {
        int N = board.length; // Get the puzzle board's size (assuming it's an N x N puzzle).

        for (int i = 0; i < k; i++) {
            //Initialize the table with non-valid move(not 0 ,1 ,2 ,3)
            int[] validMoves = {-1, -1, -1, -1}; // Array to store valid move directions (0: Left, 1: Right, 2: Up, 3: Down).
            int validMoveCount = 0; // Keep track of the number of valid moves.

            // Check and store valid moves
            if (isValidMove(emptyRow, emptyCol - 1, N)) {
                validMoves[validMoveCount] = 0; // Add Left (0) to the valid moves array.
                validMoveCount++;
            }
            if (isValidMove(emptyRow, emptyCol + 1, N)) {
                validMoves[validMoveCount] = 1; // Add Right (1) to the valid moves array.
                validMoveCount++;
            }
            if (isValidMove(emptyRow - 1, emptyCol, N)) {
                validMoves[validMoveCount] = 2; // Add Up (2) to the valid moves array.
                validMoveCount++;
            }
            if (isValidMove(emptyRow + 1, emptyCol, N)) {
                validMoves[validMoveCount] = 3; // Add Down (3) to the valid moves array.
                validMoveCount++;
            }

            // Select a random valid move
            if (validMoveCount > 0) {
                int randomMove = validMoves[(int) (validMoveCount * Math.random())]; // Choose a random move from the valid moves array.
                switch (randomMove) {
                    case 0:
                        makeMoveLeft(); // Execute the move to the left.
                        break;
                    case 1:
                        makeMoveRight(); // Execute the move to the right.
                        break;
                    case 2:
                        makeMoveUp(); // Execute the move upwards.
                        break;
                    case 3:
                        makeMoveDown(); // Execute the move downwards.
                        break;
                }
            }
        }
    }
    /**
     * Method check if the current board is in a solved state
     * returns true or false if the puzzle is solved
     */
    public static boolean isSolution() {
        int N = board.length;
        int tile = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (row == N - 1 && col == N - 1) {
                    continue; // Skip the last cell, which should be empty
                }
                if (board[row][col] != tile) {
                    return false; // If any tile is not in the correct position, it's not a solution
                }
                tile++;
            }
        }
        return true; // The puzzle is in a solved state
    }

    /**
     * Method play used for the interactive game logic and gets as a parameter the d(display mode)
     */
    public static void play(int d) {
        int moveCount = 1;
        exit:
        while (true) {
            if (moveCount == 1)
                System.out.print("Enter the " + moveCount + "st move:");
            else if (moveCount == 2)
                System.out.print("Enter the " + moveCount + "nd move:");
            else if (moveCount == 3)
                System.out.print("Enter the " + moveCount + "rd move:");
            else
                System.out.print("Enter the " + moveCount + "th move:");
            String move = getUserCommand();
            System.out.println("*******************************");
            boolean moveIsValid = true;
            switch (move) {
                case "e":
                    break exit; // Exit the game
                case "l":
                    makeMoveLeft();
                    break;
                case "r":
                    makeMoveRight();
                    break;
                case "u":
                    makeMoveUp();
                    break;
                case "d":
                    makeMoveDown();
                    break;
                default:
                    System.out.println("Invalid move. Use 'u/d/l/r' or 'e'.");
                    moveIsValid = false;
                    break;
            }
            if(moveIsValid)
                moveCount++;
            if (d == 1)
                displayPuzzle();
            else if (d == 2)
                displayPuzzleGraph();
            if (isSolution()) {
                System.out.println("Congratulations, puzzle solved!!");
                break;
            }
        }
    }

    /**
     *  Method to move the empty cell left if it's a valid move
     */
    public static void makeMoveLeft() {
        if (isValidMove(emptyRow, emptyCol - 1, board.length)) {
            swapTiles(emptyRow, emptyCol, emptyRow, emptyCol - 1);
            emptyCol--; // Update the empty cell's position
        }
    }

    /**
     *  Method to move the empty cell right if it's a valid move
     */

    public static void makeMoveRight() {
        if (isValidMove(emptyRow, emptyCol + 1, board.length)) {
            swapTiles(emptyRow, emptyCol, emptyRow, emptyCol + 1);
            emptyCol++; // Update the empty cell's position
        }
    }

    /**
     * Move the empty cell up if it's a valid move
     */
    public static void makeMoveUp() {
        if (isValidMove(emptyRow - 1, emptyCol, board.length)) {
            swapTiles(emptyRow, emptyCol, emptyRow - 1, emptyCol);
            emptyRow--; // Update the empty cell's position
        }
    }

    /**
     * Move the empty cell down if it's a valid move
     */
    public static void makeMoveDown() {
        if (isValidMove(emptyRow + 1, emptyCol, board.length)) {
            swapTiles(emptyRow, emptyCol, emptyRow + 1, emptyCol);
            emptyRow++; // Update the empty cell's position
        }
    }

    /**
     * Method to swap two tiles in the puzzle board
     */
    private static void swapTiles(int row1, int col1, int row2, int col2) {
        int temp = board[row1][col1];
        board[row1][col1] = board[row2][col2];
        board[row2][col2] = temp;
    }

    /**
     * Method to solve the puzzle, in automatic mode, for different k values and calculate statistics.
     */
    public static void solvePuzzle(int moveLimit, int kmin, int kmax, int q) {
        for (int j = kmin; j <= kmax; j++) {
            int successCount = 0;
            int totalMoves = 0;

            // Repeat the simulation for 'q' times with the current shuffle difficulty 'j'
            for (int p = 0; p <= q; p++) {
                initializePuzzleK(board.length); // Reset the puzzle to the initial state
                shufflePuzzle(j); // Shuffle the puzzle with 'j' random moves
                int moves = 0;

                // Attempt to solve the shuffled puzzle within the move limit
                while (moves < moveLimit) {
                    char randomMove = getRandomValidMove();
                    switch (randomMove) {
                        case 'u':
                            makeMoveUp();
                            break;
                        case 'd':
                            makeMoveDown();
                            break;
                        case 'l':
                            makeMoveLeft();
                            break;
                        case 'r':
                            makeMoveRight();
                            break;
                    }
                    moves++;

                    // Check if the puzzle is solved
                    if (isSolution()) {
                        successCount++;
                        break;
                    }
                }

                if (isSolution())
                    totalMoves += moves;
            }

            // Calculate and display the average moves and success rate for the current shuffle difficulty 'j'
            double mo = (double) totalMoves / successCount;
            double successRate = (double) successCount * 100 / (q + 1);
            System.out.printf("For k = %d, average moves  %.2f, and success rate = %.2f%%.\n", j, mo, successRate);
        }
    }

    /**
     * Method used for automatic play that returns a random move and returns it as a char
     */
    public static char getRandomValidMove() {
        char[] moves = { 'u', 'd', 'l', 'r' };
        int randomIndex = (int) (4 * Math.random());
        return moves[randomIndex];
    }

    /**
     * Check if a move is valid
     * gets as parameters the position the intended move location (row, col) and the size of the table N
     * returns true or false if the move is valid
     */
    public static boolean isValidMove(int row, int col, int N) {
        return (row >= 0 && row < N) && (col >= 0 && col < N);
    }

    /**
     * Method used to display the puzzle for text-based mode
     */
    public static void displayPuzzle() {
        int N = board.length; // Assuming N x N puzzle

        for (int[] ints : board) {
            // Print the top border for each row
            for (int col = 0; col < N; col++) {
                System.out.print("+-----");
            }
            System.out.println("+"); // End the top borderline

            // Print the puzzle values and vertical borders
            for (int col = 0; col < N; col++) {
                int cellValue = ints[col];
                if (cellValue == 0) {
                    // If the cell contains zero (empty cell), display an empty cell
                    System.out.print("|     ");
                } else {
                    // Format and display the cell value with a fixed width of 3 characters
                    System.out.printf("|%3d  ", cellValue);
                }
            }
            System.out.println("|"); // End the row of values
        }

        // Print the bottom border for the last row
        for (int col = 0; col < N; col++) {
            System.out.print("+-----");
        }
        System.out.println("+"); // End the bottom borderline

        System.out.println("*******************************");
    }


    /**
     *  Method used to display the puzzle for gui mode using stddraw from stdlib
     */
     public static void displayPuzzleGraph() {
        int N = board.length;

        // Set the canvas size

        // Set the x and y scales based on the puzzle size
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);

        // Iterate through the puzzle state
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int cellValue = board[row][col];

                // Calculate the coordinates of the top-left corner of the cell
                double x = col + 0.5; // Adjust for the center of the cell
                double y = N - row - 0.5; // Adjust for the center of the cell and invert the y-coordinate

                // Set the pen color and draw a filled rectangle for the cell
                StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE); // Set the cell color
                StdDraw.filledRectangle(x, y, 0.5, 0.5);

                // Draw the cell border
                StdDraw.setPenRadius(0.02); // Set the pen radius for the border
                StdDraw.setPenColor(StdDraw.GRAY); // Set the border color
                StdDraw.rectangle(x, y, 0.5, 0.5);

                if (cellValue != 0) {
                    // If the cell is not empty (0), display the cell value
                    StdDraw.setPenRadius(0.04); // Set the pen radius for the text
                    StdDraw.setPenColor(StdDraw.BLACK); // Set the text color
                    StdDraw.text(x, y, Integer.toString(cellValue));
                }
            }
        }
    }
}
