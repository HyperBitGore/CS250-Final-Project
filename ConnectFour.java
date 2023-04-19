import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
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
		boolean loop = true;
		int turn = 1;
		Gameboard gb = new Gameboard();
		int[][] b = gb.getBoard();
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				System.out.print(b[j][i] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		for(int k = 1; k < 8; k++) 
			System.out.print(k + " ");
		System.out.println();
		System.out.println();
		
		while(loop) {
			if(turn == 1) {
				System.out.println("Player 1: Enter the column to play your piece");
				System.out.print("Column: ");
				Scanner in = new Scanner(System.in);
				try {
					int column = in.nextInt();
					if(column > 7)
						System.out.println("Please enter a number 1-7");
					else if(column == 1) {
						if(b[0][0] == '0') 
							b[0][0] = '1';
							
//						else
//							b[0][]
					}
				} catch (NumberFormatException nfe) {
					// TODO Auto-generated catch block
					System.out.println("Please enter a number 1-7");
				} catch (InputMismatchException ime) {
					System.out.println("Please enter a number 1-7");
				}
	
			}
		}
	}

}
