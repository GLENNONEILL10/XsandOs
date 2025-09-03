
import java.util.*;

public class Game {
	
	private Menu menu;
	private Board board;
	private AI ai;
	private char currentPlayerSymbol;
	private char aiPlayer;
	
	Scanner input = new Scanner(System.in);
	
	//constructor that creates menu object
	public Game() {
		
		menu = new Menu();
	}
	
	//function for the game play
	public void play() {
		
		
		boolean gameMenu = false;
		boolean gameOver = false;
		

		//while gameMenu is false
		while(!gameMenu) {
			
			
			boolean keepPlaying;
			
			//stores the user game type choice
			int option = menu.gameTypeSelect();
			
			//if the user selects 2 player
			if(option == 1){
				
				
				do {
					
						//creates new board object
						Board board = new Board();
						gameOver = false;
						
						//stores the chosen symbol
						currentPlayerSymbol = menu.symbolChoice();
						
						//while gameOver is false
						while(!gameOver){
							
							int numberChoice ;
							
							//prints the number grid
							board.printNumberGrid();
							
							//space
							System.out.println();
							
							//prints the game board
							board.printBoard();
							
							//allows user the choose 1 - 9
							numberChoice = input.nextInt();
							
							//if invalid choice
							 if (numberChoice < 1 || numberChoice > 9) {
								 
							        System.out.println("Out of range. Enter 1-9.");
							        continue;
							    }
							
								//if the choice is available
							if(board.isAvailable(numberChoice)) {
								
								//removes the number from the grid
								board.removeNumber(numberChoice);
								
						//	== if number choice from 1 - 9 is valid it places the move onto board ==
								switch(numberChoice){
								
									//if user selects 1
									case 1:
										
									    //if move is valid
										if(board.isValidMove(0, 0)) {
											
											//place the move
											board.placeMove(0, 0, currentPlayerSymbol);
										}
										//if move isnt valid
										else {
											
											System.out.println("Square is taken");
										}
									
										break;
										
									case 2:
										
										if(board.isValidMove(0, 1)){
											
											board.placeMove(0, 1, currentPlayerSymbol);
											
										}
										else {
											
											System.out.println("Square is taken");
										}
										
										
										break;
										
									case 3:
										
										if(board.isValidMove(0, 2)) {
									
											board.placeMove(0, 2, currentPlayerSymbol);
											
										}
										
										else {
											
											System.out.println("Square is taken");
										}
										break;
										
									case 4:
										
										if(board.isValidMove(1, 0)) {
										
											board.placeMove(1,0,currentPlayerSymbol);
											
										}
										
										else {
											
											System.out.println("Square is taken");
										}
										break;
										
									case 5:
										
										if(board.isValidMove(1, 1)) {
										
											board.placeMove(1, 1, currentPlayerSymbol);
										}
										
										else {
											
											System.out.println("Square is taken");
										}
										break;
										
									case 6:
										
										if(board.isValidMove(1, 2)){
											
											board.placeMove(1, 2, currentPlayerSymbol);
										}
										else {
											
											System.out.println("Square is taken");
										}
										break;
										
									case 7:
										
										if(board.isValidMove(2, 0)) {
										
											board.placeMove(2, 0, currentPlayerSymbol);
										}
										else {
											
											System.out.println("Square is taken");
										}
										break;
										
									case 8:
										
										if(board.isValidMove(2, 1)) {
										
											board.placeMove(2, 1, currentPlayerSymbol);
										}
										else {
											
											System.out.println("Square is taken");
										}
										break;
										
									case 9:
										
										if(board.isValidMove(2, 2)) {
										
											board.placeMove(2, 2, currentPlayerSymbol);
										}
										else {
											System.out.println("Square is taken");
										}
										break;
										
									//if user selects invalid choice	
									default:
										
										System.out.println("Error out of range 1-9");
										
								}
									
							}
							else {
								//error here
								System.out.println("Please enter a number in the grid");
								
							}
							
							//checks if the current player has won is true
							if(board.checkWin(currentPlayerSymbol)){
								
								//prints the board
								board.printBoard();
								
								System.out.println(currentPlayerSymbol +"- Congratulations You Have Won");
								
								//ends the game
								gameOver = true;
								
								break;
								
							}
							
							//checks if board is full is true
							if(board.isFull()){
								
								//prints board
								board.printBoard();
								
								System.out.println("Draw");
								
								//ends the game
								gameOver = true;
								
								break;
								
							}
							
							//swaps the current player
							if(currentPlayerSymbol == 'X'){
								
								currentPlayerSymbol = 'O';
								
							}
							else if(currentPlayerSymbol == 'O'){
								
								currentPlayerSymbol = 'X';
								
							}
											
						}
						
						//gives player the choice to play again
						keepPlaying = menu.playAgain();
						
					//loops while keep playing is true
				} while(keepPlaying);
				
			}
			//if the user chooses to play against ai
			else if(option == 2) {
				
				boolean playFirst = false;
				
				
				do {
				
					Board board = new Board();
					AI ai = new AI(board);
					gameOver = false;
					
					//current player is set to the symbol 
					currentPlayerSymbol = menu.symbolChoice();
					
					//asks user if they want to play first
					playFirst = menu.playFirst();
					
					//sets the ai player to the opposite of the real player
					if(currentPlayerSymbol == 'X') {
						
						aiPlayer = 'O';
					}
					else if(currentPlayerSymbol == 'O') {
						
						aiPlayer = 'X';
					}
					
					
					//while game over is false
					while(!gameOver) {
						
						//print number grid and game board
						board.printNumberGrid();
						
						System.out.println();
						
						board.printBoard();
						
						//if user choses to play first
						if(playFirst == true) {
							
						
							int numberChoice ;
							
							
							numberChoice = input.nextInt();
							
							 if (numberChoice < 1 || numberChoice > 9) {
								 
							        System.out.println("Out of range. Enter 1-9.");
							        continue;
							    }
							 
							 //if number available is false
							 if(!board.isAvailable(numberChoice)) {
								 
								 System.out.println("Please enter a number in the grid");
								 
								    continue;
									
											
								}
							 
							//if the square chosen is false
							 if (!board.placeByNumber(numberChoice, currentPlayerSymbol)) {
								 	
								 	System.out.println("Square is taken");
								 
								    continue; // retry if invalid move
								}
										
							//checks if real player has won	
							if(board.checkWin(currentPlayerSymbol)){
								
								board.printBoard();
								
								System.out.println(currentPlayerSymbol +"- Congratulations You Have Won");
								
								gameOver = true;
								
								break;
								
							}
							//checks if the board is full
							if(board.isFull()){
								
								board.printBoard();
								
								System.out.println("Draw");
								
								gameOver = true;
								
								break;
								
							}
								
							 //stores the move from the move list
							 int aiMove = ai.getMove();
							 
							 //if there is no more moves in the list that means draw
							 if(aiMove == -1){
								 
								 board.printBoard();
								 
								 System.out.println("Draw");
								 
								 gameOver = true;
								 break;
								 
							 }
							 //places the ai move based on the numbers left in the grid
							board.placeByNumber(aiMove, aiPlayer);
							 
							 //checks if the ai has won
							 if (board.checkWin(aiPlayer)) {
								 
					                board.printBoard();
					                
					                System.out.println("Computer (" + aiPlayer + ") wins!");
					                
					                gameOver = true;
					                break;
					            }
							 
							//checks if board is full	
							if (board.isFull()) {
								
								board.printBoard();
								
								System.out.println("Draw");
								
								gameOver = true;
								break;
							}
					            
					           

						}
					
						//if ai goes first
						else {
							
							int aiMove = ai.getMove();
							 
							 if(aiMove == -1){
								 
								 board.printBoard();
								 
								 System.out.println("Draw");
								 
								 gameOver = true;
								 break;
								 
							 }
							 
							  board.placeByNumber(aiMove, aiPlayer);
							 
											 
							 if (board.checkWin(aiPlayer)) {
								 
					                board.printBoard();
					                
					                System.out.println("Computer (" + aiPlayer + ") wins!");
					                
					                gameOver = true;
					                break;
					            }
					            if (board.isFull()) {
					            	
					                board.printBoard();
					                
					                System.out.println("Draw");
					                
					                gameOver = true;
					                break;
					            }
					            
					            board.printNumberGrid();
					            System.out.println();
					            board.printBoard();
					            
					            int numberChoice ;
								
								
								numberChoice = input.nextInt();
								
								 if (numberChoice < 1 || numberChoice > 9) {
									 
								        System.out.println("Out of range. Enter 1-9.");
								        continue;
								    }
								 
								
								 
								 if (!board.isAvailable(numberChoice)) {
					                    System.out.println("Please enter a number in the grid");
					                    continue; // retry human
					                }

					                if (!board.placeByNumber(numberChoice, currentPlayerSymbol)) {
					                    System.out.println("Square is taken");
					                    continue; // retry human
					                }
										
								
								 
								 if(board.checkWin(currentPlayerSymbol)){
										
										board.printBoard();
										
										System.out.println(currentPlayerSymbol +"- Congratulations You Have Won");
										
										gameOver = true;
										
										break;
										
									}
									
									if(board.isFull()){
										
										board.printBoard();
										
										System.out.println("Draw");
										
										gameOver = true;
										
										break;
										
									}		 
							}
						 					
						
					}
					
					
					keepPlaying = menu.playAgain();
					
				}while(keepPlaying);
				
				
			}
			
			//asks player if they want to exit the game or go back to main menu
			int choice;
			System.out.println("[1]Do You want to quit completely\n[2]go back to main menu");
			
			choice = input.nextInt();
			
			//if the user choses to exit
			if(choice == 1){
				
				System.exit(1);
				
				gameMenu = true;
				
				
			}

			//if they go back to main menu
			else if(choice == 2) {
				
				
				gameMenu = false;
				
			}
			else {
				
				System.out.println("Please enter 1 or 2");
			}
			
			
			
		}
		
				
	}

}
