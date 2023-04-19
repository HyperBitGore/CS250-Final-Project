import java.awt.Color;
import java.awt.Color;

public class Player {
	private boolean turn;
	private int wins;
	private Color col;
	
	public Player(boolean turn, Color col) {
		this.turn = turn;
		this.wins = 0;
		this.col = col;
	}
		
	//getters and setters
	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public Color getCol() {
		return col;
	}
	
	public void setCol(Color col) {
		this.col = col;
	}	
	
	
}
