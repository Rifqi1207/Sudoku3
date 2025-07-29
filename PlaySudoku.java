// Muhammad Rifqi Akbari
// CS 143
// HW#1 Sudoku

// This program will test the Sudokuboard class by creating a board from a file and displaying it on the screen.

public class PlaySudoku {
   // Pre: "data1.sdk" file exist in the same folder as this code,
   // formatted with 9 rows of 9 characters each
   // Post: Prints the loaded Sudoku board to the console
   
   public static void main(String[] args) {
      SudokuBoard board = new SudokuBoard("data1.sdk");
      System.out.println(board);
   }
}

/* 
Paste the output from JGrasp here.

  ----jGRASP exec: java PlaySudoku
 2 . . | 1 . 5 | . . 3 
 . 5 4 | . . . | 7 1 . 
 . 1 . | 2 . 3 | . 8 . 
 ------+------+------
 6 . 2 | 8 . 7 | 3 . 4 
 . . . | . . . | . . . 
 1 . 5 | 3 . 9 | 8 . 6 
 ------+------+------
 . 2 . | 7 . 1 | . 6 . 
 . 8 1 | . . . | 2 4 . 
 7 . . | 4 . 2 | . . 1 
 
 
  ----jGRASP: Operation complete.
 

*/