
import java.util.*;

public class AI {
	
	private Board board;
	
	private Random rand = new Random();
	
	
	public AI(Board board) {
		
		this.board = board;
	}
	
	public int getMove(){
		
		List<Integer> gets = board.getAvailableNumbers();
		
		if(gets.isEmpty()){
			
			return -1;
		
		}
		
		return gets.get(rand.nextInt(gets.size()));
		
		
	}

}
