
public class Player {
	private boolean turn;
	private int wins;
	private int moves;
	
	public Player(boolean turn, int moves) {
		this.turn = turn;
		this.wins = 0;
		this.moves = moves;
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

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}
	
	
	
}
