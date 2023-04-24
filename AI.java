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
				if(!checkExecution()) {
					int re = searching();
					ret = plan.get(re).getColumn();
					plan.remove(re);
				}else {
					//follow plan from searching turn, check if plan gets blocked and change
					if(plan.size() > 0) {
						ret = plan.get(0).getColumn();
					}else {
						int re = searching();
						ret = plan.get(re).getColumn();
						plan.remove(re);
					}
				}
				break;
			}
			}
		return ret;
		
	}
	
	public boolean checkExecution() {
		Piece p = plan.get(0);
		int[][] board = gb.getBoard();
		if(board[p.getColumn()][p.getRow()] != 0) {
			return false;
		}
		//place piece
		return true;
	}
	
	private ArrayList<Piece> checkDiagonal(int column, int row){
		//top left diagonal
		int i = column;
		int j = row;
		ArrayList<Piece> pieces = new ArrayList<>();
		int[][] board = gb.getBoard();
		for(i = column, j = row; i >= 0 && j >= 0; i--, j--) {
			if(board[i][j] == 0 || board[i][j] == 2) {
				pieces.add(new Piece(0, 0, i, j, Color.RED));
				if(pieces.size() >= 4) {
					return pieces;
				}
			} else {
				pieces.clear();
			}
		}
		//bottom right diagonal
		for(i = column, j = row; i >= 0 && j < 6; i--, j++) {
			if(board[i][j] == 0 || board[i][j] == 2) {
				pieces.add(new Piece(0, 0, i, j, Color.RED));
				if(pieces.size() >= 4) {
					return pieces;
				}
			} else {
				pieces.clear();
			}
		}
		//bottom left diagonal
		for(i = column, j = row; i < 7 && j >= 0; i++, j--) {
			if(board[i][j] == 0 || board[i][j] == 2) {
				pieces.add(new Piece(0, 0, i, j, Color.RED));
				if(pieces.size() >= 4) {
					return pieces;
				}
			} else {
				pieces.clear();
			}
		}
		//top right diagonal
		for(i = column, j = row; i < 7 && j < 6; i++, j++) {
			if(board[i][j] == 0 || board[i][j] == 2) {
				pieces.add(new Piece(0, 0, i, j, Color.RED));
				if(pieces.size() >= 4) {
					return pieces;
				}
			} else {
				pieces.clear();
			}
		}
		return pieces;
	}
	
	private ArrayList<Piece> checkDiagonalSearch(){
		//check left diagonal
		ArrayList<Piece> pieces = new ArrayList<>();
		int[][] board = gb.getBoard();
		//we check each column, and every row that's possible in that column
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				pieces = checkDiagonal(i, j);
				if(pieces.size() > 0) {
					return pieces;
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
					if(columns.size() >= 4) {
						return columns;
					}
				}else {
					columns.clear();
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
					if(columns.size() >= 4) {
						return columns;
					}
				}else {
					columns.clear();
				}
			}
		}
		return columns;
	}
	private ArrayList<Piece> randomPiece(){
		ArrayList<Piece> sp = new ArrayList<>();
		int[][] board = gb.getBoard();
		for(int i = 0; i < board.length; i++) {
			if(board[i][0] == 0) {
				sp.add(new Piece(0, 0, i, 0, Color.RED));
				break;
			}
		}
		return sp;
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
		if(sp.size() <= 0) {
			sp = randomPiece();
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
	private int checkDiagonalsDefense(){
		//top left diagonal
		int i = gb.getLastColumn();
		int j = gb.getLastRow();
		int[][] board = gb.getBoard();
		int c = 0;
		for(i = gb.getLastColumn(), j = gb.getLastRow(); i >= 0 && j >= 0; i--, j--) {
			if(board[i][j] == 1) {
				c++;
				if(c >= 3) {
					return i;
				}
			} else {
				c = 0;
			}
		}
		c = 0;
		//bottom right diagonal
		for(i = gb.getLastColumn(), j = gb.getLastRow(); i >= 0 && j < 6; i--, j++) {
			if(board[i][j] == 1) {
				c++;
				if(c >= 3) {
					return i;
				}
			} else {
				c = 0;
			}
		}
		c = 0;
		//bottom left diagonal
		for(i = gb.getLastColumn(), j = gb.getLastRow(); i < 7 && j >= 0; i++, j--) {
			if(board[i][j] == 1) {
				c++;
				if(c >= 3) {
					return i;
				}
			} else {
				c = 0;
			}
		}
		c = 0;
		//top right diagonal
		for(i = gb.getLastColumn(), j = gb.getLastRow(); i < 7 && j < 6; i++, j++) {
			if(board[i][j] == 1) {
				c++;
				if(c >= 3) {
					return i;
				}
			} else {
				c = 0;
			}
		}
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
