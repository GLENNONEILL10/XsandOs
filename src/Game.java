
import java.util.*;

public class Game {
	
	private Menu menu;
	private Board board;
	private AI ai;
	private char currentPlayerSymbol;
	private char aiPlayer;
	
	Scanner input = new Scanner(System.in);
	
	public Game() {
		
		menu = new Menu();
	}
	
	public void play() {
		
		
		boolean gameMenu = false;
		boolean gameOver = false;
		

		while(!gameMenu) {
			
			
			boolean keepPlaying;
			
			int option = menu.gameTypeSelect();
			
		
			if(option == 1){
				
				
				do {
					
						Board board = new Board();
						gameOver = false;
						
						currentPlayerSymbol = menu.symbolChoice();
						
						while(!gameOver){
							
							int numberChoice ;
							
							board.printNumberGrid();
							
							System.out.println();
							
							board.printBoard();
							
							numberChoice = input.nextInt();
							
							 if (numberChoice < 1 || numberChoice > 9) {
								 
							        System.out.println("Out of range. Enter 1-9.");
							        continue;
							    }
							
							if(board.isAvailable(numberChoice)) {
								
								board.removeNumber(numberChoice);
								
								switch(numberChoice){
								
									case 1:
										
										if(board.isValidMove(0, 0)) {
											
											board.placeMove(0, 0, currentPlayerSymbol);
										}
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
										
									default:
										
										System.out.println("Error out of range 1-9");
										
								}
									
							}
							else {
								//error here
								System.out.println("Please enter a number in the grid");
								
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
							
							if(currentPlayerSymbol == 'X'){
								
								currentPlayerSymbol = 'O';
								
							}
							else if(currentPlayerSymbol == 'O'){
								
								currentPlayerSymbol = 'X';
								
							}
											
						}
					
						keepPlaying = menu.playAgain();
						
					
				} while(keepPlaying);
				
			}
			else if(option == 2) {
				
				boolean playFirst = false;
				
				
				do {
				
					Board board = new Board();
					AI ai = new AI(board);
					gameOver = false;
					
					currentPlayerSymbol = menu.symbolChoice();
					
					playFirst = menu.playFirst();
					
					
					if(currentPlayerSymbol == 'X') {
						
						aiPlayer = 'O';
					}
					else if(currentPlayerSymbol == 'O') {
						
						aiPlayer = 'X';
					}
					
					
					
					while(!gameOver) {
						

						board.printNumberGrid();
						
						System.out.println();
						
						board.printBoard();
									
						if(playFirst == true) {
							
						
							int numberChoice ;
							
							
							numberChoice = input.nextInt();
							
							 if (numberChoice < 1 || numberChoice > 9) {
								 
							        System.out.println("Out of range. Enter 1-9.");
							        continue;
							    }
							 
							 
							 if(!board.isAvailable(numberChoice)) {
								 
								 System.out.println("Please enter a number in the grid");
								 
								    continue;
									
											
								}
							 
							 if (!board.placeByNumber(numberChoice, currentPlayerSymbol)) {
								 	
								 	System.out.println("Square is taken");
								 
								    continue; // retry if invalid move
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
					            
					           

						}
					
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
			
			int choice;
			System.out.println("[1]Do You want to quit completely\n[2]go back to main menu");
			
			choice = input.nextInt();
			
			if(choice == 1){
				
				System.exit(1);
				
				gameMenu = true;
				
				
			}
			else if(choice == 2) {
				
				
				gameMenu = false;
				
			}
			else {
				
				System.out.println("Please enter 1 or 2");
			}
			
			
			
		}
		
				
	}

}
