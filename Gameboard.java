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
	boolean createPiece(int column, int player) {
		//calculate end position and return false if column is filled
		int end = 0;
		for(int i = 0; i < 6; i++, end++) {
			if(board[column][i] == 1) {
				end = i - 1;
				break;
			}
		}
		if(end == 6) {
			end--;
		}
		
		//fill board cell it ends on
		board[column][end] = player;
		lastRow = end;
		lastColumn = column;
		//calculate the x and y
		//y goes from 100 to 500
		//x goes from 10 to 300
		int x = 10 + (end * 48);
		int y = 100 + (column * 50);
		//add piece to pieces
		Piece p = new Piece(x, y, column, end);
		pieces.add(p);
		return true;
	}
	public boolean getWinState(int player) {
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
	
	public static void main(String[] args) {
		Gameboard g = new Gameboard();
		g.createPiece(0, 1);
		g.createPiece(0, 1);
		g.createPiece(0, 1);
		g.createPiece(1, 1);
		g.createPiece(2, 1);
		g.createPiece(2, 1);
		int[][] b = g.getBoard();
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(b[i][j] + " ");
			}
			System.out.println();
		}
		for(Piece p : g.getPieces()) {
			System.out.println(p.toString());
		}
	}
	
}
