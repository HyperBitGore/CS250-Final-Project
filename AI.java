import java.awt.Color;
import java.util.ArrayList;

enum State{
	SEARCHING, EXECUTING
}




public class AI {
	private State state;
	private Gameboard gb; // copy of gameboard
	private ArrayList<Piece> plan; //plan of where to place pieces
	
	
	public AI(Gameboard g) {
		state = State.SEARCHING;
		g = gb;
		plan = new ArrayList<>();
	}
	
	public void grabGameboard(Gameboard g) {
		gb = g;
	}
	
	public int turn() {
		int ret = 0;
		ret = defending();
		if(ret == -1) {
			switch(state) {
			case SEARCHING:
				int r = searching();
				ret = plan.get(r).getColumn();
				plan.remove(r);
				break;
			case EXECUTING:
				//follow plan from searching turn, check if plan gets blocked and change
				if(plan.size() > 0) {
					ret = plan.get(0).getColumn();
				}else {
					state = State.SEARCHING;
					ret = (int) (Math.random() * 6);
				}
				break;
			}
		}
		return ret;
		
	}
	
	public int executing() {
		//check if desired spot has been filled
		
		//place piece
		return 0;
	}
	
	private ArrayList<Piece> checkDiagonalSearch(){
		//check left diagonal
		ArrayList<Piece> pieces = new ArrayList();
		int[][] board = gb.getBoard();
		//we check each column, and every row that's possible in that column
		for(int i = board.length - 1; i >= 0; i--) {
			//now check diagonals
			//dont need to check above 3 as we can't get a diagonal up there
			for(int j = board[i].length - 1; j > 3; j--) {
				for(int k = i; k >= 0; k--) {
					if(board[k][j] == 0 || board[k][j] == 2) {
						pieces.add(new Piece(0, 0, k, j, Color.YELLOW));
					}else {
						pieces.clear();
					}
					if(pieces.size() >= 4) {
						return pieces;
					}
				}
			}
		}
		//check right diagonal
		for(int i = 0; i < board.length; i++) {
			//now check diagonals
			//dont need to check above 3 as we can't get a diagonal up there
			for(int j = board[i].length - 1; j > 3; j--) {
				for(int k = i; k < board.length; k++) {
					if(board[k][j] == 0 || board[k][j] == 2) {
						pieces.add(new Piece(0, 0, k, j, Color.YELLOW));
					}else {
						pieces.clear();
					}
					if(pieces.size() >= 4) {
						return pieces;
					}
				}
			}
		}
		return pieces;
	}
	
	private ArrayList<Piece> checkHorizontalSearch() {
		ArrayList<Piece> columns = new ArrayList<>();
		int[][] board = gb.getBoard();
		//check all rows too, and check for own pieces in a row to find easier place to win
		for(int j = 0; j < 6; j++) {
			for(int i = 0; i < board.length; i++) {
				if(board[i][j] == 0 || board[i][j] == 2) {
					columns.add(new Piece(0, 0, i, j, Color.RED));
				}else {
					columns.clear();
				}
				if(columns.size() == 4) {
					return columns;
				}
			}
		}
		
		return columns;
	}
	private ArrayList<Piece> checkVerticalSearch(){
		ArrayList<Piece> columns = new ArrayList<>();
		int[][] board = gb.getBoard();
		//find first column where you can stack 4
		for(int i = 0; i < board.length; i++) {
			int c = 0;
			for(int j = board[i].length - 1; j >= 0; j--) {
				if(board[i][j] == 0 || board[i][j] == 2) {
					columns.add(new Piece(0, 0, i, j, Color.RED));
				}else {
					columns.clear();
				}
				if(columns.size() == 4) {
					return columns;
				}
			}
		}
		return columns;
	}
	
	
	public int searching() {
		plan.clear();
		//try and find spots to make four in a row
		ArrayList<Piece> sp;
		//check diagonals first
		sp = checkDiagonalSearch();
		//check cardinals
		if(sp.size() <= 0) {
			sp = checkHorizontalSearch();
		}
		//if can't find anywhere else try just stacking vertically
		if(sp.size() <= 0) {
			sp = checkVerticalSearch();
		}
		int[][] b = gb.getBoard();
		for(int i = sp.size() - 1; i >= 0; i--) {
			if(b[sp.get(i).getColumn()][sp.get(i).getRow()] == 0) {
				plan.add(sp.get(i));
			}
		}
		state = State.EXECUTING;
		return 0;
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
				if(c == 3) {
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
				if(c == 3) {
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
		return sp;
		
	}
	
}
