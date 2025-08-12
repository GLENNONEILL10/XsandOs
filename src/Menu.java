ni .gitignore -ItemType File
import java.util.*;

public class Menu {
	
	Scanner input = new Scanner(System.in);
	
	public char symbolChoice() {
		
		while(true) {
			
			System.out.println("Select: [X] or [O]");
			String choice = input.nextLine().toUpperCase(); 
			
			if(choice.equals("X") || choice.equals("O")) {
				
				return choice.charAt(0);
			}
			
			else {
				
				System.out.println("Invalid Input:Please enter X or O");
			}
			
		}
		
	}
	
	public int gameTypeSelect() {
		
		while(true) {
			
			System.out.println("Select:\n[1]2 player\n[2]Against Computer");
			
			
			if(input.hasNextInt()) {
				
				int option = input.nextInt();
				
				input.nextLine();
				
				if(option == 1 || option == 2){
					
					return option;
					
				}
				
				else {
					
					System.out.println("Invalid Input:Please Enter 1 for 2 player OR 2 for against Computer");
				}
						
			}
			else {
				
				System.out.println("Invalid Input: Please Enter number 1 or number 2 ");
			}
			
		}
		
	}
	
	public boolean playFirst() {
		
		while(true) {
			
			System.out.println("Do You want to play First:[Y] or [N]");
			String choice = input.nextLine().toUpperCase();
			
			if(choice.equals("Y")){
				
				return true;
				
			}
			else if(choice.equals("N")) {
				
				return false;
			}
			
			else {
				
				System.out.println("Invalid Input:Please enter Y or N");
			}
				
		}
	}
	
	public boolean playAgain() {
		
		
		while(true) {
			
			System.out.println("Do You want to play Again:[Y] or [N]");
			String choice = input.nextLine().toUpperCase();
			
			if(choice.equals("Y")){
				
				return true;
				
			}
			else if(choice.equals("N")) {
				
				return false;
			}
			
			else {
				
				System.out.println("Invalid Input:Please enter Y or N");
			}
			
		}
	}
	

}
