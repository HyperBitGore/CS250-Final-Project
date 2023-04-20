import java.awt.Color;

public class Piece {
	private int x, y;
	private int column;
	private int row;
	private Color c;
	public Piece(int x, int y, int column, int row, Color c) {
		this.x = x;
		this.y = y;
		this.column = column;;
		this.row = row;
		this.c = c;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	public String toString() {
		return "Piece: x: " + x + " y: " + y + " column: " + column + " row: " + row;
	}

}
