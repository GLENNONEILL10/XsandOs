
import java.util.*;

public class Board {
	
	private char[][] board;
	private String [][] numberGrid;
	
	public Board(){
		
		board = new char[3][3];
		
		for(int i = 0; i < 3;i++) {
			
			for(int j = 0; j < 3;j++) {
				
				board[i][j] = ' ';
			}
		}
		
		numberGrid = new String[3][3];
		int num = 1;
		
		for(int i = 0; i < 3; i++){
			
			for(int j = 0; j< 3;j++){
				
				numberGrid[i][j] = String.valueOf(num++);
				
			}
			
		}
		
	}
	
	public void printBoard() {
		
		for(int i = 0; i < 3;i++){
			
			for(int j = 0;j < 3;j++) {
				
				System.out.print(board[i][j]);
				
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
	
	public void removeNumber(int number){
		
		if (number < 1 || number > 9) return;
		
		int row = (number - 1) / 3;
		
		int col = (number - 1) % 3;
		
		numberGrid[row][col] = " ";
		
		
	}
	
	public boolean isAvailable(int num){
		
		if (num < 1 || num > 9) return false;
		
		int row = (num - 1) / 3;
        int col = (num - 1) % 3;
        
        return !numberGrid[row][col].equals(" ");
		
		
	}
	
	public boolean placeMove(int row,int col, char symbol) {
		
		
		if(row >= 0 && row < 3 && col >= 0 && col < 3){
			
			if(board[row][col] == ' '){
				
				board[row][col] = symbol;
				
				return true;
			}
			else {
				
				return false;
			}
			
		}
		else {
			
			return false;
		}
		
	}
	
	public boolean isFull() {
			
			for(int i = 0; i < 3; i++){
				
				for(int j = 0; j < 3; j++) {
					
					if(board[i][j] == ' '){
						
						return false;
						
					}	
					
				}
			}
			
			return true;
		
	}
	
	public boolean checkWin(char symbol) {
				
		for(int i = 0;i < 3;i++){
			
			if(board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol){
				
				return true;
				
			}
			
		}
		for(int j = 0; j<3;j++){
			
			if(board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol){
				
				return true;
				
			}
			
		}
		
		if(board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol){
			
			return true;
			
		}
		
		if(board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol){
			
			return true;
			
		}
		
		return false;
		
		
	}
	
	public boolean isValidMove(int row,int col) {
		
		if(row >= 0 && row < 3 && col >= 0 && col < 3){
			
			if(board[row][col] == ' '){
				
				
				return true;
			}
			
		}
			
		return false;
			
						
	}
	
	public List<Integer> getAvailableNumbers() {
		
	    List<Integer> moves = new ArrayList<>();
	    
	    for (int n = 1; n <= 9; n++) {
	    	
	        int r = (n - 1) / 3;
	        int c = (n - 1) % 3;
	        
	        if (board[r][c] == ' ') {   // SPACE check
	        	
	            moves.add(n);
	        }
	    }
	    return moves;
	}
	
	public boolean placeByNumber(int number, char symbol){
		
		
		int row = (number - 1)/3;
		int col = (number - 1)%3;
		
		if(!isValidMove(row,col)){
			
			 System.out.println("Square is taken");
			 
		     return false;
		}
		
		placeMove(row, col, symbol); 
	    removeNumber(number);        
	    
	    return true;
	
		
		
	}
	
	public char getCell(int r, int c) { 
		
		return board[r][c]; 
		
	}
	
}
