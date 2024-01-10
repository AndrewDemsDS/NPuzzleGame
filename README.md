NPuzzleGame
===========

Description
-----------

The NPuzzleGame is a Java-based implementation of the classic sliding puzzle game. The project consists of two main parts: `NPuzzleLib.java`, which provides essential functions and methods for managing and solving the puzzle, and `NPuzzleClient.java`, the main program for user interaction.

Features
--------

*   Interactive puzzle game with customizable size.
*   Essential puzzle functions like initialization, shuffling, and display.
*   User-friendly command-line interface for gameplay.

How to Compile and Run
----------------------

1.  **Compile the Program:**
    *   `javac -cp path/to/stdlib.jar NPuzzleClient.java NPuzzleLib.java`
2.  **Run the Application:**
    
    *   Linux/macOS: `java -cp .:/path/to/stdlib.jar NPuzzleClient N d`
    *   Windows: `java -cp .;/path/to/stdlib.jar NPuzzleClient N d`
    
    Replace `N` with the puzzle size and `d` with the difficulty level.

How to Play
-----------

*   Start the game using the command-line instructions above.
*   Follow the on-screen instructions to move the puzzle tiles.
*   The objective is to arrange the tiles in numerical order.

Requirements
------------

*   Java Runtime Environment (JRE)
*   Standard libraries as per the provided `stdlib.jar`

Author
------

Developed by Andreas Demosthenous for a university project. Written on 14/10/2023 and last updated on 29/10/2023.
