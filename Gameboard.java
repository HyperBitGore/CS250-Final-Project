import java.util.ArrayList;

public class Gameboard {
	ArrayList<Piece> pieces;
	boolean[][] board = new boolean[7][6];
	public Gameboard() {
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				board[i][j] = false;
			}
		}
	}
	boolean createPiece(int column) {
		//calculate end position and return false if column is filled
		int end = 0;
		for(int i = 0; i < 6; i++, end++) {
			if(board[column][i]) {
				i = 6;
			}
		}
		//fill board cell it ends on
		board[column][end] = true;
		//add piece to pieces
		Piece p = new Piece();
		pieces.add(p);
		return true;
	}
	
}
