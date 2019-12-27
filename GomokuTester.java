/**
 * Test all types in Project 5
 * @author Raghav Goyal
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;

public class GomokuTester{
  
  Gomoku gomoku = new Gomoku();
  
  
  @Test
  public void testRules(){
    int[][] intButtonArray = new int[19][19];
    for(int row = 0; row < intButtonArray.length; row++){
      
      for(int col = 0; col < intButtonArray[row].length; col++){
        
        intButtonArray[row][col] = -1;
        
      }
      
    }
    
    //Testing up and right (Test First)
    
    intButtonArray[1][5] = 1;
    intButtonArray[2][5] = 1;
    intButtonArray[3][6] = 1;
    intButtonArray[3][7] = 1;
    
    //If there is no obstruction and there are 4 open ends then the three three rule applies
    assertEquals(true, gomoku.rules(intButtonArray, 3, 5, 3));
    
    intButtonArray[4][5] = 0;
    
    //If there is an obstruction and there are fewer than 4 open ends then the three three rule does not apply
    assertEquals(false, gomoku.rules(intButtonArray, 3, 5, 3));
    
    for(int row = 0; row < intButtonArray.length; row++){
      
      for(int col = 0; col < intButtonArray[row].length; col++){
        
        intButtonArray[row][col] = -1;
        
      }
      
    }
    
    gomoku.setPlayer(1);
    intButtonArray[1][5] = 1;
    intButtonArray[2][5] = 1;
    intButtonArray[4][5] = 1;
    intButtonArray[5][5] = 1;
    
    /** Testing whether having two groups of three in one column is a valid move. 
      * Since the move does not result in 4 open positions it can be made. 
      * Hence the three-three rule is false in this case.
      */
    assertEquals(false, gomoku.rules(intButtonArray, 3, 5, 3));
    
    //Testing down and topright (Test Middle)
    
    gomoku.setPlayer(0);
    intButtonArray[12][5] = 0;
    intButtonArray[11][5] = 0;
    intButtonArray[9][6] = 0;
    intButtonArray[8][7] = 0;
    
    assertEquals(true, gomoku.rules(intButtonArray, 10, 5, 3));
    for(int row = 0; row < intButtonArray.length; row++){
      
      for(int col = 0; col < intButtonArray[row].length; col++){
        
        intButtonArray[row][col] = -1;
        
      }
      
    }
    
    //Testing Topleft and bottomleft (Testing Last)
    gomoku.setPlayer(1);
    intButtonArray[9][8] = 1;
    intButtonArray[10][9] = 1;
    intButtonArray[12][9] = 1;
    intButtonArray[13][8] = 1;
    assertTrue(gomoku.rules(intButtonArray, 11, 10, 3));
    
    for(int row = 0; row < intButtonArray.length; row++){
      
      for(int col = 0; col < intButtonArray[row].length; col++){
        
        intButtonArray[row][col] = -1;
        
      }
      
    }
    
//Testing the 4-4 rule
    
    gomoku.setPlayer(1);
    intButtonArray[9][5] = 1;
    intButtonArray[10][5] = 1;
    intButtonArray[11][5] = 1;
    intButtonArray[12][6] = 1;
    intButtonArray[12][7] = 1;
    intButtonArray[12][8] = 1;
    
    //When there are no obstructions and there are 4 open positions on either end of the 2 groups of 4 then the rule applies
    assertTrue(gomoku.rules(intButtonArray, 12, 5, 4));
    
    intButtonArray[13][5] = 1;
    
    //Even when there are obstructions the 4-4 rule applies because a 5 can be made on either of the other sides.
    assertEquals(true, gomoku.rules(intButtonArray, 12, 5, 4));
    
    
    
  }
  
  @Test
  public void testGameWon(){
    
    int[][] intButtonArray = new int[19][19];
    for(int row = 0; row < intButtonArray.length; row++){
      
      for(int col = 0; col < intButtonArray[row].length; col++){
        
        intButtonArray[row][col] = -1;
        
      }
      
    }
    gomoku.setPlayer(0);
    
    //Testing First with the Up direction
    
    intButtonArray[0][0] = 0;
    intButtonArray[1][0] = 0;
    intButtonArray[2][0] = 0;
    intButtonArray[3][0] = 0;
    assertTrue(gomoku.gameWon(intButtonArray, 4, 0));
    
    for(int row = 0; row < intButtonArray.length; row++){
      
      for(int col = 0; col < intButtonArray[row].length; col++){
        
        intButtonArray[row][col] = -1;
        
      }
      
    }
    gomoku.setPlayer(1);
    //Testing Middle with the Down position
    intButtonArray[1][0] = 1;
    intButtonArray[2][0] = 1;
    intButtonArray[3][0] = 1;
    intButtonArray[4][0] = 1;
    assertTrue(gomoku.gameWon(intButtonArray, 0, 0));
    
    for(int row = 0; row < intButtonArray.length; row++){
      
      for(int col = 0; col < intButtonArray[row].length; col++){
        
        intButtonArray[row][col] = -1;
        
      }
      
    }
    gomoku.setPlayer(0);
    //Testing Last with the Topleft position
    intButtonArray[0][0] = 0;
    intButtonArray[1][1] = 0;
    intButtonArray[2][2] = 0;
    intButtonArray[3][3] = 0;
    assertTrue(gomoku.gameWon(intButtonArray, 4, 4));
    

  }
  
  @Test
  public void testOverLine(){
    
    int[][] intButtonArray = new int[19][19];
    
    /**
     * TESTING THE 4 MAIN DIRECTIONS
     */
    for(int row = 0; row < intButtonArray.length; row++){
      
      for(int col = 0; col < intButtonArray[row].length; col++){
        
        intButtonArray[row][col] = -1;
        
      }
      
    }
    
    gomoku.setPlayer(0);
    intButtonArray[1][0] = 0;
    intButtonArray[2][0] = 0;
    intButtonArray[3][0] = 0;
    intButtonArray[4][0] = 0;
    intButtonArray[5][0] = 0;
    intButtonArray[6][0] = 0;
    intButtonArray[7][0] = 0;
    intButtonArray[8][0] = 0;
    
    //Testing First in one of the 4 main directions
    assertTrue(gomoku.overLine(intButtonArray, 0, 0));
    
    for(int row = 0; row < intButtonArray.length; row++){
      
      for(int col = 0; col < intButtonArray[row].length; col++){
        
        intButtonArray[row][col] = -1;
        
      }
      
    }
    
    gomoku.setPlayer(1);
    intButtonArray[0][0] = 1;
    intButtonArray[1][0] = 1;
    intButtonArray[2][0] = 1;
    intButtonArray[3][0] = 1;
    intButtonArray[5][0] = 1;
    intButtonArray[6][0] = 1;
    intButtonArray[7][0] = 1;
    intButtonArray[8][0] = 1;
    //Testing Middle in one of the 4 main directions
    assertTrue(gomoku.overLine(intButtonArray, 4, 0));
    
     for(int row = 0; row < intButtonArray.length; row++){
      
      for(int col = 0; col < intButtonArray[row].length; col++){
        
        intButtonArray[row][col] = -1;
        
      }
      
    }
    
    gomoku.setPlayer(1);

    intButtonArray[1][0] = 1;
    intButtonArray[2][0] = 1;
    intButtonArray[3][0] = 1;
    intButtonArray[4][0] = 1;
    intButtonArray[5][0] = 1;
    intButtonArray[6][0] = 1;
    intButtonArray[7][0] = 1;
    intButtonArray[8][0] = 1;
    
    //Testing Last in one of the 4 main directions
    assertTrue(gomoku.overLine(intButtonArray, 9, 0));
    
    
    
    
    /**
     * TESTING THE 4 DIAGONAL DIRECTIONS
     */
    for(int row = 0; row < intButtonArray.length; row++){
      
      for(int col = 0; col < intButtonArray[row].length; col++){
        
        intButtonArray[row][col] = -1;
        
      }
      
    }
    
    gomoku.setPlayer(0);
    intButtonArray[1][1] = 0;
    intButtonArray[2][2] = 0;
    intButtonArray[3][3] = 0;
    intButtonArray[4][4] = 0;
    intButtonArray[5][5] = 0;
    intButtonArray[6][6] = 0;
    intButtonArray[7][7] = 0;
    intButtonArray[8][8] = 0;
    
    //Testing First in one of the 4 diagonal directions
    assertTrue(gomoku.overLine(intButtonArray, 0, 0));
    
    for(int row = 0; row < intButtonArray.length; row++){
      
      for(int col = 0; col < intButtonArray[row].length; col++){
        
        intButtonArray[row][col] = -1;
        
      }
      
    }
    
    gomoku.setPlayer(1);
    intButtonArray[0][0] = 1;
    intButtonArray[1][1] = 1;
    intButtonArray[2][2] = 1;
    intButtonArray[3][3] = 1;
    intButtonArray[5][5] = 1;
    intButtonArray[6][6] = 1;
    intButtonArray[7][7] = 1;
    intButtonArray[8][8] = 1;
    //Testing Middle in one of the 4 diagonal directions
    assertTrue(gomoku.overLine(intButtonArray, 4, 4));
    
     for(int row = 0; row < intButtonArray.length; row++){
      
      for(int col = 0; col < intButtonArray[row].length; col++){
        
        intButtonArray[row][col] = -1;
        
      }
      
    }
    
    gomoku.setPlayer(1);

    intButtonArray[1][1] = 1;
    intButtonArray[2][2] = 1;
    intButtonArray[3][3] = 1;
    intButtonArray[4][4] = 1;
    intButtonArray[5][5] = 1;
    intButtonArray[6][6] = 1;
    intButtonArray[7][7] = 1;
    intButtonArray[8][8] = 1;
    
    //Testing Last in one of the 4 diagonal directions
    assertTrue(gomoku.overLine(intButtonArray, 9, 9));

    
  }

}