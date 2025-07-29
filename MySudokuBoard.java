// Muhammad Rifqi Akbari
// CS 143
// HW Core Topics: Implements a Sudoku board in Java with methods load, validate, and check
// if the board is completely and correctly solved according to Sudoku rules.

/*
   This is starter code for Crystal's Sudoku #3.
   The code is not pretty, but it works.
*/

import java.util.*;
import java.io.*;

public class MySudokuBoard {
   public final int SIZE = 9;
   protected char[][] myBoard;
   
   // Pre: The input file theFile exists and contains a valid Sudoku board with SIZE rows and columns.
   // Post: he myBoard 2D array is filled with characters read from the file, representing the Sudoku board.
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
   
   // Pre: The myBoard array has been initialized and contains a valid Sudoku board.
   // Post: Returns true if the board is valid and completely solved (each number appears exactly 9 times); otherwise, returns false.
   public boolean isSolved() {
      if(!isValid())
         return false;
         
      Map<Character,Integer> map = new HashMap<>();
      for(char[] row : myBoard) {
         for(char cell : row) {
            if(map.containsKey(cell))
               map.put(cell, map.get(cell) + 1);
            else 
               map.put(cell, 1);
         }
      }
      // info on Collections: https://docs.oracle.com/javase/8/docs/api/?java/util/Collections.html
      return map.keySet().size() == 9 && Collections.frequency(map.values(),9) == 9;
   }
   
   // Pre: The myBoard array has been initialized and contains a partially or fully filled Sudoku board.
   // Post: Returns true if the board has only valid characters and no duplicate values in any row or column; otherwise, returns false.
   public boolean isValid() {
      // checks for bad data
      for(char[] row : myBoard) 
         for(char cell : row)
            if(cell != '.' && (cell < '1' || cell > '9'))
               return false;
      
      // checks for row/col violations
      for(int r = 0; r < myBoard.length; r++) {
         Set<Character> trackingRow = new HashSet<>();
         Set<Character> trackingCol = new HashSet<>();
         for(int c = 0; c < myBoard[r].length; c++) {
            // check for row violation
            if(trackingRow.contains(myBoard[r][c]))
               return false;
            else if(myBoard[r][c] != '.')
               trackingRow.add(myBoard[r][c]);
               
            // check for col violation
            if(trackingCol.contains(myBoard[c][r]))
               return false;
            else if(myBoard[c][r] != '.')
               trackingCol.add(myBoard[c][r]);
         } 
      }
      
      // check for mini-squares
      for(int square = 1; square <= 9; square++) {   
         char[][] mini = miniSquare(square);
         Set<Character> trackingMini = new HashSet<>();
         for(int r = 0; r < 3; r++)
            for(int c = 0; c < 3; c++)
               // check for mini violation
               if(trackingMini.contains(mini[r][c]))
                  return false;
               else if(mini[r][c] != '.')
                  trackingMini.add(mini[r][c]);
      }
      
      // if there weren't any violations above...
      return true;
   }
  // Pre: The myBoard array is initialized as a 9x9 grid and spot is an integer from 1 to 9.
  // Post: Returns a 3x3 character array containing the elements of the specified 3x3 mini-square from the Sudoku board.
  private char[][] miniSquare(int spot) {
      char[][] mini = new char[3][3];
      for(int r = 0; r < 3; r++) {
         for(int c = 0; c < 3; c++) {
            // whoa - wild! This took me a solid hour to figure out.
            // This translates between the "spot" in the 9x9 Sudoku board
            // and a new mini square of 3x3
            mini[r][c] = myBoard[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }
      }
      return mini;
   }
   // Pre: The myBoard array is initialized and contains a partially filled, valid Sudoku board.
   // Post: Returns true and fills myBoard with a completed solution if one exists; otherwise, returns false and leaves myBoard unchanged or partially solved.
   public boolean solve() {
      if(!isValid()) {
         return false;
      } 
      if(isSolved()) {
         return true;
      }
      
      for(int row = 0; row < SIZE; row++) {
         for(int col = 0; col < SIZE; col++) {
            if(myBoard[row][col] == '.') {
               for(char num = '1'; num <= '9'; num++) {
                  myBoard[row][col] = num;
                  if(isValid() && solve()) {
                     return true;
                  }
                  myBoard[row][col] = '.';
               }
               return false;
            }
         }
      }
      return true;
   }
   
   // Pre: The myBoard array has been initialized and contains the current state of the Sudoku board.
   // Post: Returns a string that visually represents the entire Sudoku board, formatted with rows and columns.
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

}