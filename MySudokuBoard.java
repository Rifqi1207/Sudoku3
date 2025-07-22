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
   private boolean checkRows() {
      for(int row = 0; row < SIZE; row++) {
         boolean[] seen = new boolean[10];
         for (int col = 0; col < SIZE; col++) {
            char c = myBoard[row][col];
            if (c >= '1' && c <= '9') {
               int num = c - '0';
               if (seen[num]) return false;
               seen[num] = true;
            }
         }
      }
      return true;
   }
   
   // Checks each column for duplicate digits (ignores '.')
   private boolean checkCols() {
      for(int col = 0; col < SIZE; col++) {
         boolean[] seen = new boolean[10];
         for (int row = 0; row <SIZE; row++) {
            char c = myBoard[row][col];
            if (c >= '1' && c <= '9') {
               int num = c - '0';
               if (seen[num]) return false;
               seen[num] =true;
            }
         }
      }
      return true;
   }
   
   // Checks each 3x3 grid for duplicate digits (ignores '.')
   private boolean checkMiniSquares() {
      for(int boxRow = 0; boxRow < 3; boxRow++) {
         for(int boxCol =0; boxCol < 3; boxCol++) {
            boolean[] seen = new boolean[10];
            for(int i = 0; i< 3; i++) {
               for (int j = 0; j < 3; j++) {
                  char c = myBoard[boxRow * 3 + i][boxCol * 3 + j];
                  if (c >= '1' && c <= '9') {
                     int num = c - '0';
                     if (seen[num]) return false;
                     seen[num] = true;
                  }
               }
            }
         }
      }
      return true;
   }
   
   // Main isValid: all constraints 
   public boolean isValid() {
      return checkValidData() && checkRows() && checkCols() && checkMiniSquares();
   }
   
   // isSolved: valid AND every digit 1-9 appears exactly 9 times, no '.'
   public boolean isSolved() {
      if(!isValid()) return false;
      int[] count = new int[10];
      for (int row = 0; row < SIZE; row++) {
         for (int col = 0; col < SIZE; col++) {
            char c = myBoard[row][col];
            if (c == '.') return false;
            int num = c - '0';
            if (num < 1 || num > 9) return false;
            count[num]++;
         }
      }
      for (int i = 1; i <= 9; i++) {
         if (count[i] != 9) return false;
      }
      return true;
   }
   
}