
import java.util.*;

public class Board {

	//2d character arrays
	private char[][] board;
	private String [][] numberGrid;
	
	public Board(){
		
		//board = a new 2d array 3*3 length 9
		board = new char[3][3];
		
		//initialises the board to empty
		for(int i = 0; i < 3;i++) {
			
			for(int j = 0; j < 3;j++) {
				
				board[i][j] = ' ';
			}
		}
		//grid = a new 2d array 3*3 length 9 
		numberGrid = new String[3][3];
		int num = 1;
		
		//initialised the number grid from 1 - 9
		for(int i = 0; i < 3; i++){
			
			for(int j = 0; j< 3;j++){
				
				numberGrid[i][j] = String.valueOf(num++);
				
			}
			
		}
		
	}
	//function that prints the board to console
	public void printBoard() {
		
		//nested loop to handle 2d array
		for(int i = 0; i < 3;i++){
			
			for(int j = 0;j < 3;j++) {
				
				//prints the board 
				System.out.print(board[i][j]);
				
				//if j internal iterator is less than 2
				if(j < 2) {
					
					//print line to form the board
					System.out.print("  | ");
					
				}
				
			}
			//space
			System.out.println();

			//if i the external interator is less than 2
			if(i < 2){
				
				//prints this dashed line to form board
				System.out.println("------------");
				
			}
			
		}
		
	}
	
	//Function that prints the number grid
	public void printNumberGrid() {
		
		
		for(int i = 0; i < 3;i++){
			
			for(int j = 0;j < 3;j++) {
				
				System.out.print(numberGrid[i][j]);
				
				if(j < 2) {
					
					System.out.print("  | ");
					
				}
				
			}
			
			System.out.println();
			
			if(i < 2){
				
				System.out.println("------------");
				
			}
			
		}

	}
	//removes the number chosen from the number grid
	public void removeNumber(int number){
		
		//if number is less than 1 or greater than 9
		if (number < 1 || number > 9) return;
		
		//row = number entered - 1 divided by 3 so 1 = 1-1 = 0 / 3 =  row index 0
		int row = (number - 1) / 3;
		
		//col = number entered - 1 modulo 3 so 5 = 5-1 = 4 % 3 = column index 1
		int col = (number - 1) % 3;
		
		//sets the number entered to blank
		numberGrid[row][col] = " ";
		
		
	}
	
	//function that checks if chosen number is available
	public boolean isAvailable(int num){
		
		if (num < 1 || num > 9) return false;
		
		int row = (num - 1) / 3;
        int col = (num - 1) % 3;
        
		//returns true or false if the cell is empty or not
        return !numberGrid[row][col].equals(" ");
		
		
	}
	
	//function that places move
	public boolean placeMove(int row,int col, char symbol) {
		
		//if row is greater than 0 and less than 3 , the same as col
		if(row >= 0 && row < 3 && col >= 0 && col < 3){
			
			//if the row and col entered is empty
			if(board[row][col] == ' '){
				
				//replace the index with the symbol chosen by the current user
				board[row][col] = symbol;
				
				return true;
			}
			//if the board row and column is not empty
			else {
				
				return false;
			}
			
		}
		//if invalid row or column
		else {
			
			return false;
		}
		
	}
	
	//function that checks if the board is full
	public boolean isFull() {
			
			for(int i = 0; i < 3; i++){
				
				for(int j = 0; j < 3; j++) {
					
					//if somewhere on the board is empty
					if(board[i][j] == ' '){
						
						return false;
						
					}	
					
				}
			}
			
			//if the function exits the loop that means no empty cells where found, which means its full
			return true;
		
	}
	
	//function that checks if move is a win
	public boolean checkWin(char symbol) {
		
		//loops through rows
		for(int i = 0;i < 3;i++){
			
			//if index 0.0 is equal to symbol and index 0.1 is equal to symbol and index 0.2 is = to symbol
			if(board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol){
				
				//return true as this is a win
				return true;
				
			}
			
		}

		//loops through columns
		for(int j = 0; j<3;j++){
			
			if(board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol){
				
				return true;
				
			}
			
		}

		//==== if the diagonals are = to symbols ====
		if(board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol){
			
			return true;
			
		}
		
		if(board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol){
			
			return true;
			
		}
		
		//if the flow gets to this point then win was not found
		return false;
		
		
	}
	//function that checks if move is valid
	public boolean isValidMove(int row,int col) {
		
		if(row >= 0 && row < 3 && col >= 0 && col < 3){
			
			if(board[row][col] == ' '){
				
				
				return true;
			}
			
		}
			
		return false;
			
						
	}
	
	//function that gets the available moves
	public List<Integer> getAvailableNumbers() {
		
		//creates array list
	    List<Integer> moves = new ArrayList<>();
	    
		//loops while n is less than or equal to 9
	    for (int n = 1; n <= 9; n++) {
	    	
			//gets the actual index of number
	        int r = (n - 1) / 3;
	        int c = (n - 1) % 3;
	        
			//if the index is empty
	        if (board[r][c] == ' ') {   // SPACE check
	        	
				//add the number selected to the array list
	            moves.add(n);
	        }
	    }

		//returns the array list
	    return moves;
	}
	
	//function for ai class that places symbol by number
	public boolean placeByNumber(int number, char symbol){
		
		//gets the actual index
		int row = (number - 1)/3;
		int col = (number - 1)%3;
		
		//if the move is not valid
		if(!isValidMove(row,col)){
			
			//move cant be made
			 System.out.println("Square is taken");
			 
		     return false;
		}
		
		//if the move is valid it places the move and removes the number from the number grid
		placeMove(row, col, symbol); 
	    removeNumber(number);        
	    
	    return true;
		
	}
		
	public char getCell(int r, int c) { 
		
		return board[r][c]; 
		
	}
	
}
