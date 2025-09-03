
import java.util.*;

public class AI {
	
	private Board board;
	
	private Random rand = new Random();
	
	//constuctor
	public AI(Board board) {
		
		//sets the board so its consistant with ai moves
		this.board = board;
	}
	
	//function that gets the ai move
	public int getMove(){
		
		//assigns the available grid numbers to a list
		List<Integer> gets = board.getAvailableNumbers();
		
		//if the list is empty it means error
		if(gets.isEmpty()){
			
			return -1;
		
		}
		
		//returns a random number from the list
		return gets.get(rand.nextInt(gets.size()));
		
		
	}

}
