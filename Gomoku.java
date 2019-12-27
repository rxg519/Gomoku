/**
 * This class creates the game Gomoku. It extends the JavaFX abstract class Application to provide JavaFX functionality.
 * 
 * @author Raghav Goyal
 */

//Import the Application package from the JavaFX library
import javafx.application.Application;

//Import the Scene package from the JavaFX library
import javafx.scene.Scene;

//Import the Button package from the JavaFX library
import javafx.scene.control.Button;

//Import the Stage package from the JavaFX library
import javafx.stage.Stage;

//Import the Color package from the JavaFX library
import javafx.scene.paint.Color;

//Import all layout's packages from the JavaFX library
import javafx.scene.layout.*;

//Import the Insets package from JavaFX library
import javafx.geometry.Insets;

//Import all of event's packages from the JavaFX library.
import javafx.event.*;



public class Gomoku extends Application{
  
  //Create a 2D array that stores Button objects.
  private Button[][] buttonArray = new Button[19][19];
  
  /**Create a 2D array that stores int values. A -1 means that there is no stone there. A 0 means that the stone is white. 
    * A 1 means that the stone is black.
    */
  private int[][] intButtonArray = new int[19][19];
  
  /** The endGame boolean field represents the situation in which a player can proudly declare in a menacing manner and in a deep, grounded voice: "I am inevitable..."
    * If this value is false it means that our brave and honorable players have yet to defeat one another in the coliseum...
    * If this value is true it means that a victor can be pronounced.
    */
  private boolean endGame = false;
  
  //Create a new GridPane object
  private GridPane pane = new GridPane();
  
  /**Counter to keep track of the players. Set to 1 because black starts.
    * Throughout this program a "0" is used to represent white stones and a "1" to represent black stones.
    */
  private int currentPlayer = 1;
  
  /**
   * The start method creates the scene, sets up the stage and provides the buttons with an EventHandler object.
   * 
   * @param primaryStage creates the main stage on which the board is created.
   */
  public void start(Stage primaryStage){
      
    //Method that creates the
    createBoard();
    
    //Create a new scene on which the pane is created.
    Scene scene = new Scene(pane);
    
    //Provide the stage a scene.
    primaryStage.setScene(scene);
    
    //Set the title of the stage to be "Gomoku"
    primaryStage.setTitle("Gomoku");
    
    //Show the stage.
    primaryStage.show();

  }
  
  public void setPlayer(int player){
    
    currentPlayer = player;
    
  }
  
  public int getPlayer(){
   
    return currentPlayer;
    
  }
  
  
  /**
   * The createBoard method makes the board out of a 2D array of buttons.
   */
  
  public void createBoard(){
    
    for(int row = 0; row < buttonArray.length; row++){
      
      
      for(int col = 0; col < buttonArray[row].length; col++){
        
        Button button = new Button();
        button.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(2), new Insets(1))));
        button.setMinSize(45,45);
        buttonArray[row][col] = button;
        buttonArray[row][col].setOnAction(new gameMove());
        pane.add(button, col, row);
        intButtonArray[row][col] = -1;
        
      }
    }
    
  }

  /**
   * findButtonOnBoard finds the row and column of the button provided and returns an array of 2 elements. 
   * The first element is the row number and the second is the column number.
   * 
   * @param b the button to find
   * @return an int array with the coordinates of the button.
   */
  
  public int[] findButtonOnBoard(Button b){
    
    for(int row = 0; row < buttonArray.length; row++){
      
      for(int col = 0; col < buttonArray[row].length; col++){
        
        if(buttonArray[row][col].equals(b)){
          
          return new int[] {row, col};
          
        }
        
      }
      
    }
    return new int[] {-1, -1};
  }
  
  /**
   * nextPlayer is a method that switches to the next player.
   * 
   * @param player stores the player number.
   * @return the player who's turn is next. A 0 represents the player playing white and a 1 repesents the player playing black.
   */
  
  public int nextPlayer(int player){
    
    return (player + 1) % 2;
    
  }
  
  /**
   * consecStonesData is a method that loops through the stones in a specified direction.
   * @param currentState stores the current state of the 2D array button.
   * @param row the row where the button is stored.
   * @param col the col where the button is stored.
   * @param direction the direction you want to check for stones. 
   * @return a 3 element int array that stores the row of the position immediately after the last valid stone, 
   * the column of the position immediately after the last valid stone, and the number of consecutive stones 
   * found.
   */
  
  public int[] consecStonesData(int[][] currentState, int row, int col, String direction) {
    
    //if statement for each direction. Bespoke code will be created for each direction specified.
    
    //while a boolean reachedEnd is not true and the next row/col is within the boundary and the next stone is of the same color as the current stone
    
    int[] consecStonesDataArray = new int[3];
    
    boolean reachedEnd = false;
    
    int newRowToCheck = row;
    
    int newColToCheck = col;
    
    int numStonesConsec = 1;
    
    int firstStoneColor = currentPlayer;
    
    if(direction.equals("left")){
      
      if(col == 0 || currentState[row][col-1] != firstStoneColor){
        
        reachedEnd = true;
        
        
      }
      
      newColToCheck-=1;
      
      while(!reachedEnd && newColToCheck >= 0 && currentState[row][newColToCheck] == firstStoneColor){
        
        numStonesConsec++;
        newColToCheck--;
        
        
      }
    }
    
    if(direction.equals("up")){
      
      
      if(row == 0 || currentState[row-1][col] != firstStoneColor){
        
        reachedEnd = true;
        
      }
      newRowToCheck-=1;
      
      while(!reachedEnd && newRowToCheck >= 0 && currentState[newRowToCheck][col] == firstStoneColor){
        
        numStonesConsec++;
        newRowToCheck--;
        
      }
    }
    
    if(direction.equals("right")){
      
      if(col == 18 || currentState[row][col+1] != firstStoneColor){
        
        reachedEnd = true;
        
      }
      
      newColToCheck+=1;
      
      while(newColToCheck  <= 18 && currentState[row][newColToCheck] == firstStoneColor){
        
        numStonesConsec++;
        newColToCheck++;
        
      }
    }
    
    if(direction.equals("down")){
      
      if(row == 18 || currentState[row+1][col] != firstStoneColor){
        
        reachedEnd = true;
        
      }
      
      newRowToCheck+=1;
      
      while(newRowToCheck <= 18 && currentState[newRowToCheck][col] == firstStoneColor){
        
        numStonesConsec++;
        newRowToCheck++;
        
      }
    }
    
    if(direction.equals("topleft")){
      
      if(col == 0 || row == 0 || currentState[row-1][col-1] != firstStoneColor){
        
        reachedEnd = true;
        
      }

      newColToCheck-=1;
      newRowToCheck-=1;
      
      while(newRowToCheck >= 0 && newColToCheck >= 0 && currentState[newRowToCheck][newColToCheck] == firstStoneColor){
        
        numStonesConsec++;
        newColToCheck--;
        newRowToCheck--;
        
      }
    }
    
    if(direction.equals("bottomleft")){
      
      if(col == 0 || row == 18 || currentState[row+1][col-1] != firstStoneColor){
        
        reachedEnd = true;
        
      }
      
      newColToCheck-=1;
      newRowToCheck+=1;
      
      while(!reachedEnd && newRowToCheck <= 18 && newColToCheck >= 0 && currentState[newRowToCheck][newColToCheck] == firstStoneColor){
        
        numStonesConsec++;
        newColToCheck--;
        newRowToCheck++;
        
      }
    }
    
    if(direction.equals("topright")){
      
      if(row == 0 || col == 18 || currentState[row-1][col+1] != firstStoneColor){
        
        reachedEnd = true;
        
      }

      newColToCheck+=1;
      newRowToCheck-=1;
      
      while(!reachedEnd && newRowToCheck >= 0 && newColToCheck <= 18 && currentState[newRowToCheck][newColToCheck] == firstStoneColor){
        
        numStonesConsec++;
        newColToCheck++;
        newRowToCheck--;
        
      }
      
    }
    
    if(direction.equals("bottomright")){
      
      if(col == 18 || row == 18 || currentState[row+1][col+1] != firstStoneColor){
        
        reachedEnd = true;
        
      }
      
      newColToCheck+=1;
      newRowToCheck+=1;
      
      while(!reachedEnd && newRowToCheck <= 18 && newColToCheck <= 18 && currentState[newRowToCheck][newColToCheck] == firstStoneColor){
        
        numStonesConsec++;
        newColToCheck++;
        newRowToCheck++;
        
      }
    }
    
    consecStonesDataArray[0] = newRowToCheck;
    consecStonesDataArray[1] = newColToCheck;
    consecStonesDataArray[2] = numStonesConsec;
    return consecStonesDataArray;
    
    
    
  }
  
  
  /**
   * numberInLine checks the number of stones that are in a consecutive row.
   * @param currentState stores the current state of the 2D array button.
   * @param row the row where the button is stored.
   * @param col the col where the button is stored.
   * @param direction the direction you want to check for stones. 
   */
  
  public int numberInLine(int[][] currentState, int row, int col, String direction){
    
    return consecStonesData(currentState, row, col, direction)[2];
    
  }
  
  /**
   * isOpen is a method that checks if the next position after a series of consecutive stones of the same color in a specified direction is empty.
   * @param currentState stores the current state of the 2D array button.
   * @param row the row where the button is stored.
   * @param col the col where the button is stored.
   * @param direction the direction you want to check for stones. 
   * @return true if next position in consecutive sequence is open.
   */
  
  public boolean isOpen(int[][] currentState, int row, int col, String direction){
    
    int[] rowColData = consecStonesData(currentState, row, col, direction);
    
    int rowToCheck = rowColData[0];

    
    int colToCheck = rowColData[1];

    
    if(rowToCheck == -1 || colToCheck == -1 || rowToCheck == 19 || colToCheck == 19){
      
      return false;
      
    }
    
    if(currentState[rowToCheck][colToCheck] == -1){
      
      
      return true;
      
    } else {
      
      
      return false;
      
    }
    
  }
  
  /**
   * pieceOnSquare checks if there is a piece placed on the square or not.
   * @param row The row to check
   * @param col The column to check
   * @return true if there is a stone on the square and false otherwise. 
   */
  public boolean pieceOnSquare(int row, int col){
    

    
    if(buttonArray[row][col].getBackground().getFills().size() == 2){
      
      return true;

    } else {
      
      return false;
      
    }
    
  }
  
  /**
   * Creates an 2D int array that stores the number of stones in all 8 directions from the current position.
   * @param currentState stores the current state of the 2D int array representing buttons.
   * @param row the row where the button is stored.
   * @param col the col where the button is stored.
   * @param direction the direction you want to check for stones. 
   * @return an 2D int array with 8 rows and 3 columns containing the consecStonesDataArray for each direction in the following order:
   * {up, topright, right, bottomright, down, bottomleft, left, topleft}
   */
  
  public int[][] stonesInAllDirections(int[][] currentState, int row, int col){
    
    int[][] stonesDataArray = new int[8][3];
    
    stonesDataArray[0] = consecStonesData(currentState, row, col, "up");
    stonesDataArray[1] = consecStonesData(currentState, row, col, "topright");
    stonesDataArray[2] = consecStonesData(currentState, row, col, "right");
    stonesDataArray[3] = consecStonesData(currentState, row, col, "bottomright");
    stonesDataArray[4] = consecStonesData(currentState, row, col, "down");
    stonesDataArray[5] = consecStonesData(currentState, row, col, "bottomleft");
    stonesDataArray[6] = consecStonesData(currentState, row, col, "left");
    stonesDataArray[7] = consecStonesData(currentState, row, col, "topleft");
    
    return stonesDataArray;
    
  }
  
  
  public boolean overLine(int[][] currentState, int row, int col){
    
    int[][] numStonesArray = stonesInAllDirections(currentState, row, col);
    
    for(int index = 0; index < numStonesArray.length; index++){
      
      if((numStonesArray[index][2] + numStonesArray[(index+4) % 8][2] - 1) > 5){
        
        return true;
        
      }
      
    }
    
    return false;
  }
  
  /**
   * gameWon is a method to check if there are 5 stones of the same color in a row. If so that player is the winner
   * @param currentState takes the current version of the board
   * @param row is the row of the button clicked on
   * @param col is the column of the button clicked on
   * @return true if game won false if game must continue.
   */
  
  public boolean gameWon(int[][] currentState, int row, int col){
    
    int[][] numStonesArray = stonesInAllDirections(currentState, row, col);
    
    for(int index = 0; index < numStonesArray.length; index++){
      
      if(numStonesArray[index][2] == 5){
        
        return true;
        
      }
    }
    
    return false;
    
  }
  
  public boolean rules(int[][] currentState, int row, int col, int ruleType){
    
    boolean[] found = new boolean[8];
    int[][] stonesData = stonesInAllDirections(currentState, row, col);
    String direction = "";
    String oppositeDirection = "";
    int numTrue = 0;
    
    for(int index = 0; index < stonesData.length; index++){
      
      if(index == 0){
        
        direction = "up";
        oppositeDirection = "down";
        
      } else if (index == 1){
        
        direction = "topright";
        oppositeDirection = "bottomleft";
        
      } else if(index == 2){
        
        direction = "right";
        oppositeDirection = "left";
        
      } else if (index == 3){
        
        direction = "bottomright";
        oppositeDirection = "topleft";
        
      } else if(index == 4){
        
        direction = "down";
        oppositeDirection = "up";
        
      } else if (index == 5){
        
        direction = "bottomleft";
        oppositeDirection = "topright";
        
      } else if(index == 6){
        
        direction = "left";
        oppositeDirection = "right";
        
      } else if (index == 7){
        
        direction = "topleft";
        oppositeDirection = "bottomright";
        
      }
      
      if(stonesData[index][2] == ruleType){
        
        if (ruleType == 3){
          
          if(isOpen(currentState, row, col, direction) && stonesData[(index+4) % 8][2] == 1 && isOpen(currentState, row, col, oppositeDirection)){
            
            found[index] = true;
            
          }
          
        } else if (ruleType == 4){
          
          found[index] = true;
          
        }
        
      }
    }
    for(int index = 0; index < found.length; index++){
      
      if(found[index] == true){
        
        numTrue = numTrue + 1;
        
      }
    }
    
    if(numTrue >= 2){
      
      return true;
      
      
    } else {
      
      return false;
      
      
    }
  }
  
  /**
   * fourfourRule is a method that checks for the 4-4 rule and then returns true if the 4-4 rule applies. 
   * @param currentState the current state of the board
   * @param row the row of the button clicked
   * @param col the col of the button clicked
   * @return false if the user can place a piece at the current position.
   */
  
  public boolean fourfourRule(int[][] currentState, int row, int col){
    
    return rules(currentState, row, col, 4);
    
  }
  /**
   * threethreeRule is a method that checks for the 3-3 rule and then returns true if the 3-3 rule applies. 
   * @param currentState the current state of the board
   * @param row the row of the button clicked
   * @param col the col of the button clicked
   * @return false if the user can place a piece at the current position.
   */
  
  public boolean threethreeRule(int[][] currentState, int row, int col){
    
    return rules(currentState, row, col, 3);
    
  }
  
  public static void main (String[] args){
    
    
    Application.launch(args);
    
  }
  
  private class gameMove implements EventHandler<ActionEvent> {
    
    /** React to a button click:  add an appropriate stone
      * @param e  information about the button click event that occurred
      */
    public void handle(ActionEvent e) {
      
      Button b = (Button) e.getSource(); 
      int[] buttonCoordinates = findButtonOnBoard(b);
      if(!endGame && currentPlayer == 0 && !pieceOnSquare(buttonCoordinates[0],buttonCoordinates[1]) && !threethreeRule(intButtonArray, buttonCoordinates[0], buttonCoordinates[1]) && !fourfourRule(intButtonArray, buttonCoordinates[0], buttonCoordinates[1])){
        
        b.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(2), new Insets(1)),new BackgroundFill(Color.WHITE, new CornerRadii(100, true), null)));
        intButtonArray[buttonCoordinates[0]][buttonCoordinates[1]] = 0;
        
        if(overLine(intButtonArray, buttonCoordinates[0], buttonCoordinates[1])){
          
          System.out.println("There are more than 5 consecutive stones so the game continues...");
          
        }
        
        if(!endGame && gameWon(intButtonArray, buttonCoordinates[0], buttonCoordinates[1]) && !overLine(intButtonArray, buttonCoordinates[0], buttonCoordinates[1])){
          
          System.out.println("Congratulations! White has won the game!");
          endGame = true;
          
        }
        
        currentPlayer = nextPlayer(currentPlayer);
        
      } else if(!endGame && currentPlayer == 1 && !pieceOnSquare(buttonCoordinates[0],buttonCoordinates[1]) && !threethreeRule(intButtonArray, buttonCoordinates[0], buttonCoordinates[1]) && !fourfourRule(intButtonArray, buttonCoordinates[0], buttonCoordinates[1])){
        
        b.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(2), new Insets(1)),new BackgroundFill(Color.BLACK, new CornerRadii(100,true), null)));
        intButtonArray[buttonCoordinates[0]][buttonCoordinates[1]] = 1;
        
        if(overLine(intButtonArray, buttonCoordinates[0], buttonCoordinates[1])){
          
          System.out.println("There are more than 5 consecutive stones so the game continues...");
          
        }
        
        if(!endGame && gameWon(intButtonArray, buttonCoordinates[0], buttonCoordinates[1]) && !overLine(intButtonArray, buttonCoordinates[0], buttonCoordinates[1])){
          
          System.out.println("Congratulations! Black has won the game!");
          endGame = true;
          
        }
        
        currentPlayer = nextPlayer(currentPlayer);
        
      } else if (threethreeRule(intButtonArray, buttonCoordinates[0], buttonCoordinates[1])){
        
        System.out.println("You are not allowed to place a piece there because of the three-three rule.");
        
      } else if (fourfourRule(intButtonArray, buttonCoordinates[0], buttonCoordinates[1])){
        
        System.out.println("You are not allowed to place a piece there because of the four-four rule.");
        
      }
    }
    
  }
  
}