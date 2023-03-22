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
	boolean createPiece(int column) {
		//calculate end position and return false if column is filled
		int end = 0;
		for(int i = 0; i < 6; i++, end++) {
			if(board[column][i] == 1) {
				i = 6;
			}
		}
		end--;
		//fill board cell it ends on
		board[column][end] = 1;
		lastRow = end;
		lastColumn = column;
		//add piece to pieces
		Piece p = new Piece();
		pieces.add(p);
		return true;
	}
	//returns last move made
	public int getLastColumn() {
		return lastColumn;
	}
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
	public static void main(String[] args) {
		Gameboard g = new Gameboard();
		g.createPiece(0);
		g.createPiece(0);
		g.createPiece(0);
		int[][] b = g.getBoard();
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(b[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}
