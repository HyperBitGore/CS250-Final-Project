import java.awt.Color;
import java.util.ArrayList;

enum State{
	DEFENDING, SEARCHING, EXECUTING
}




public class AI {
	private State state;
	private Gameboard gb; // copy of gameboard
	private ArrayList<Piece> plan; //plan of where to place pieces
	
	
	public AI(Gameboard g) {
		state = State.DEFENDING;
		g = gb;
		plan = new ArrayList<>();
	}
	
	public void grabGameboard(Gameboard g) {
		gb = g;
	}
	
	public int turn() {
		int ret = 0;
		switch(state) {
		case DEFENDING:
			ret = defending();
			break;
		case SEARCHING:
			
			break;
		case EXECUTING:
			//follow plan from searching turn, and check if player is getting close to connect four
			
			break;
		}
		
		return ret;
		
	}
	
	public void executing() {
		
	}
	
	public void searching() {
		int col = gb.getLastColumn();
		int row = gb.getLastRow();
		int[][] board = gb.getBoard();
		//try and find spots to make four in a row
		
		
	}
	
	//returns column to drop piece 
	private int checkCardinalsDefense() {
		int col = gb.getLastColumn();
		//System.out.println("Col: " + col);
		int row = gb.getLastRow();
		//System.out.println("Row: " + row);
		int[][] board = gb.getBoard();
		//keep list of in a rows and find longest if this is ineffective
		//checking left and right
		//left
		int c = 0;
		for(int i = col; i >= 0; i--) {
			if(board[i][row] == 1) {
				c++;
				if(c == 2) {
					//find open column to block
					int j;
					for(j = i; j < 7; j++) {
						if(board[j][row] != 1) {
							break;
						}
					}
					if(j == 7) {
						for(j = i; j >= 0; j--) {
							if(board[j][row] != 1) {
								break;
							}
						}
					}
					return j;
				}
			}
		}
		//right
		c = 0;
		for(int i = col; i < 7; i++) {
			if(board[i][row] == 1) {
				c++;
				if(c == 2) {
					//find open column to block
					int j;
					for(j = i; j < 7; j++) {
						if(board[j][row] != 1) {
							break;
						}
					}
					if(j == 7) {
						for(j = i; j >= 0; j--) {
							if(board[j][row] != 1) {
								break;
							}
						}
					}
					return j;
				}
			}
		}
		//check vertical
		c = 0;
		for(int i = row; i < 6; i++) {
			if(board[col][i] == 1) {
				c++;
				if(c == 3) {
					return col;
				}
			}
		}
		//couldn't find spot on cardinals
		return -1;
	}
	private int checkDiagonalsDefense() {
		return -1;
	}
	
	public int defending() {
		//gotta check cardinals to see where enemy could place next
		int sp = checkCardinalsDefense();
		//gotta check diagonals to see where enemy could place next
		if(sp == -1) {
			sp = checkDiagonalsDefense();
		}
		if(sp == -1) {
			//pick random spot to place
		}
		System.out.println("SP: " + sp);
		return sp;
		
	}
	
}
