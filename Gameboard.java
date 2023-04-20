import java.awt.Color;
import java.util.ArrayList;

public class Gameboard {
	private ArrayList<Piece> pieces;
	private int[][] board = new int[7][6];
	private int lastColumn = -1;
	private int lastRow = -1;
	public Gameboard() {
		pieces = new ArrayList<>();
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				board[i][j] = 0;
			}
		}
	}
	//columns go from 0-6
	boolean createPiece(int column, int player, Color c) {
		//calculate end position and return false if column is filled
		int end = 0;
		for(int i = 0; i < 6; i++, end++) {
			if(board[column][i] != 0) {
				end = i - 1;
				break;
			}
		}
		if(end == -1) {
			return false;
		}
		else if(end == 6) {
			end--;
		}
		
		//fill board cell it ends on
		board[column][end] = player;
		lastRow = end;
		lastColumn = column;
		//calculate the x and y
		//y goes from 100 to 500
		//x goes from 10 to 300
		int x = 10 + column * 50;
		int y = 40 + (end * 48);
		//add piece to pieces
		Piece p = new Piece(x, y, column, end, c);
		pieces.add(p);
		return true;
	}
	public boolean checkDiagonals(int player, int column, int row) {
		int c = 0;
		int j;
		int i;
		//top left diagonal
		for(i = column, j = row; i >= 0 && j >= 0; i--, j--) {
			if(board[i][j] == player) {
				c++;
			}else if(c== 4){
				return true;
			}else {
				break;
			}
		}
		c = 0;
		//bottom right diagonal
		for(i = column, j = row; i >= 0 && j < 6; i--, j++) {
			if(board[i][j] == player) {
				c++;
			}else if(c== 4){
				return true;
			}else {
				break;
			}
		}
		c = 0;
		//bottom left diagonal
		for(i = column, j = row; i < 7 && j >= 0; i++, j--) {
			if(board[i][j] == player) {
				c++;
			}else if(c== 4){
				return true;
			}else {
				break;
			}
		}
		c = 0;
		//top right diagonal
		for(i = column, j = row; i < 7 && j < 6; i++, j++) {
			if(board[i][j] == player) {
				c++;
			}else if(c== 4){
				return true;
			}else {
				break;
			}
		}
		return false;
		
	}
	
	public boolean checkCardinals(int player, int column, int row) {
		int c = 0;
		//going left
		for(int i = row; i >= 0; i--) {
			if(board[column][i] == player) {
				c++;
			}else if(c == 4){
				return true;
			}else {
				break;
			}
		}
		c = 0;
		//going right
		for(int i = row; i < 6; i++) {
			if(board[column][i] == player) {
				c++;
			}else if(c == 4){
				return true;
			}else {
				break;
			}
		}
		c = 0;
		//going up
		for(int i = column; i >= 0; i--) {
			if(board[i][row] == player) {
				c++;
			}else if(c == 4){
				return true;
			}else {
				break;
			}
		}
		c = 0;
		//going down
		for(int i = column; i < 7; i++) {
			if(board[i][row] == player) {
				c++;
			}else if(c == 4){
				return true;
			}else {
				break;
			}
		}
		return false;
	}
	public boolean getWinState(int player) {
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				if(board[i][j] == player) {
					if(checkCardinals(player, i, j) || checkDiagonals(player, i, j)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//returns last move column
	public int getLastColumn() {
		return lastColumn;
	}
	//returns last move row
	public int getLastRow() {
		return lastRow;
	}
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}
	public int[][] getBoard() {
		return board;
	}
	
	
}
