// Muhammad Rifqi Akbari
// CS 143
// HW Core Topics: Implements a Sudoku board in Java with methods load, validate, and check
// if the board is completely and correctly solved according to Sudoku rules.


/*
   This is started code for Crystal's Sudoku #2.
   The code is not pretty, but it works.
*/

import java.util.*;
import java.io.*;

public class MySudokuBoard {
   public final int SIZE = 9;
   protected char[][] myBoard;
   
   public MySudokuBoard(String theFile) {
      myBoard = new char[SIZE][SIZE];
      try {
         Scanner file = new Scanner(new File(theFile));
         for(int row = 0; row < SIZE; row++) {
            String theLine = file.nextLine();
            for(int col = 0; col < theLine.length(); col++) {
               myBoard[row][col] = theLine.charAt(col);
            }
         }
      } catch(Exception e) {
         System.out.println("Something went wrong :(");
         e.printStackTrace();
      }
   }
   
   public String toString() {
      String result = "My Board:\n\n";
      for(int row = 0; row < SIZE; row++) {
         for(int col = 0; col < SIZE; col++) {
            result += (myBoard[row][col]);
         }
         result += ("\n");
      }
      return result;
   }
   // Checks if all cell data is valid )either '.' or '1' - '9')
   // Pre:myBoard is a filled 2D char array of size SIZE x SIZE.
   // Post:Returns true if all cells are valid ('.' or '1'-'9'), else returns false.
   private boolean checkValidData() {
      for(int row = 0; row < SIZE; row++) {
         for(int col = 0; col < SIZE; col++) {
            char c = myBoard[row][col];
            if(!(c == '.' || (c >= '1' && c <= '9'))) {
               return false;
            }
         }
      }
      return true;
   }
   // Checks each row for duplicate digits (ignore '.')
   // Pre:myBoard is a filled 2D char array of size SIZE x SIZE, each cell is either '.' or a digit '1'-'9'.
   // Post: Returns true if no row contains duplicate digits '1'-'9' (ignoring dots), otherwise returns false.
   private boolean checkRows() {
      for(int row = 0; row < SIZE; row++) {
         Set<Character> seen = new HashSet<>();
         for(int col = 0; col < SIZE; col++) {
            char c = myBoard[row][col];
            if (c >= '1' && c <= '9') {
               if (seen.contains(c)) return false;
               seen.add(c);
            }
         }
      }
      return true;
   }
   
   // Checks each column for duplicate digits (ignores '.')
   // Pre: myBoard is a filled 2D char array of size SIZE x SIZE, with each cell as '.' or a digit '1'-'9'.
   // Post: Returns true if no column contains duplicate digits '1'-'9' (ignoring dots), otherwise returns false.
   private boolean checkCols() {
      for(int col = 0; col < SIZE; col++) {
         Set<Character> seen = new HashSet<>();
         for(int row = 0; row < SIZE; row++) {
            char c = myBoard[row][col];
            if (c >= '1' && c <= '9') {
               if (seen.contains(c)) return false;
               seen.add(c);
            }
         }
      }
      return true;
   }
   
   // Checks each 3x3 grid for duplicate digits (ignores '.')
   // Pre: myBoard is a filled 9x9 char array, each cell is '.' or a digit '1'-'9'.
   // Post: Returns true if every 3x3 grid has no duplicate digits '1'-'9' (ignoring dots), otherwise returns false.
   private boolean checkMiniSquares() {
      for(int boxRow = 0; boxRow < 3; boxRow++) {
         for( int boxCol = 0; boxCol < 3; boxCol++) {
            Set<Character> seen = new HashSet<>();
            for (int i = 0; i < 3; i++) {
               for (int j = 0; j < 3; j++) {
                  char c = myBoard[boxRow * 3 + i][boxCol * 3 + j];
                  if (c >= '1' && c <= '9') {
                     if (seen.contains(c)) return false;
                     seen.add(c);
                  }
               }
            }
         }
      }
      return true;
   }
   
   // Main isValid: all constraints 
   // Pre:myBoard is a 9x9 char array with each cell as '.' or '1'-'9', and all helper check methods are defined.
   // Post:Returns true if the board is a valid Sudoku state under all constraints; otherwise returns false.
   public boolean isValid() {
      return checkValidData() && checkRows() && checkCols() && checkMiniSquares();
   }
   
   // isSolved: valid AND every digit 1-9 appears exactly 9 times, no '.'
   // Pre: myBoard is a filled 9x9 char array with each cell as '.' or '1'-'9', and isValid() is implemented.
   // Post:Returns true if the board is a fully solved Sudoku (all rules satisfied, no dots, each digit '1'-'9' appears 9 times); otherwise returns false.
   public boolean isSolved() {
      for(int row = 0; row < SIZE; row++) {
         Map<Character, Integer> count = new HashMap<>();
         for(int col = 0; col < SIZE; col++) {
            char c = myBoard[row][col];
            if(c < '1' || c > '9') return false;
            count.put(c, count.getOrDefault(c,0) + 1);
            if (count.get(c) > 1) return false;
         }
         for(char d = '1'; d <= '9'; d++) {
            if(count.getOrDefault(d,0) !=1) return false;
         }
      }
      return true;
   }
}