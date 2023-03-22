import java.util.Arrays;
import java.awt.Color;
import java.util.ArrayList;

public class ConnectFour {
	public Player[] players = new Player[2];
	public ConnectFour() {
		Player p = new Player(true, new Color(255, 50, 50));
		players[0] = p;
		p = new Player(false, new Color(0, 0, 0));
		players[1] = p;
	}
	
	public static void main(String[] args) {
		//ConnectFourGUI game = new ConnectFourGUI();
		ConnectFour p = new ConnectFour();
		for(int i = 0; i < p.players.length; i++) {
			if(p.players[i].isTurn()) {
				//do players turn
				p.players[i].setTurn(false);
			}else {
				//will be players turn after this players
				p.players[i].setTurn(true);
			}
		}
		
		
	}

}
