
import java.util.*;

public class Menu {
	
	Scanner input = new Scanner(System.in);
	
	//gives user the option to chose a sumbol
	public char symbolChoice() {
		
		//loops while true to allow the menu to loop until a valid choice is made
		while(true) {
			
			//options
			System.out.println("Select: [X] or [O]");
			String choice = input.nextLine().toUpperCase(); 
			
			//if the user selects X or O
			if(choice.equals("X") || choice.equals("O")) {
				
				//return the choice at the appropriate index
				return choice.charAt(0);
			}
			
			//if the user enters invalid entry
			else {
				
				System.out.println("Invalid Input:Please enter X or O");
			}
			
		}
		
	}
	
	//function that gives user the option to chose the type of game they want to play
	public int gameTypeSelect() {
		
		//loops while true
		while(true) {
			
			//options
			System.out.println("Select:\n[1]2 player\n[2]Against Computer");
			
			//if the scanner has an integer as next input
			if(input.hasNextInt()) {
				
				//takes in user optiom
				int option = input.nextInt();
				
				//creates space
				input.nextLine();
				
				//if option = 1 or 2
				if(option == 1 || option == 2){
					
					//return the choice
					return option;
					
				}
				
				//if user enters invalid option
				else {
					
					System.out.println("Invalid Input:Please Enter 1 for 2 player OR 2 for against Computer");
				}
						
			}
			//if the next input is not an integer
			else {
				
				System.out.println("Invalid Input: Please Enter number 1 or number 2 ");
			}
			
		}
		
	}
	
	//function that gives user the option to play first
	public boolean playFirst() {
		
		//loops while true
		while(true) {
			
			System.out.println("Do You want to play First:[Y] or [N]");
			String choice = input.nextLine().toUpperCase();
			
			//if the choice is Y
			if(choice.equals("Y")){
				
				
				return true;
				
			}
			//if the choice is N
			else if(choice.equals("N")) {
				
				return false;
			}
			//else invalid choice
			else {
				
				System.out.println("Invalid Input:Please enter Y or N");
			}
				
		}
	}
	
	//function that give use option to play again
	public boolean playAgain() {
		
		
		while(true) {
			
			System.out.println("Do You want to play Again:[Y] or [N]");
			String choice = input.nextLine().toUpperCase();
			
			//if user select y
			if(choice.equals("Y")){
				
				return true;
				
			}
			//if user selects N
			else if(choice.equals("N")) {
				
				return false;
			}
			//if user enters invalid choice
			else {
				
				System.out.println("Invalid Input:Please enter Y or N");
			}
			
		}
	}
	

}
