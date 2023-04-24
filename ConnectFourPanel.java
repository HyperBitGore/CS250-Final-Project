import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Particle{
	private float x;
	private float y;
	private int updateC;
	private float y_inc = -2.0f;
	private float x_inc;
	private boolean left;
	public Particle(int x, int y, boolean left) {
		updateC = 0;
		this.left = left;
		if(left) {
			x_inc = (float) (-Math.random() * 2.5f + 2.0f);
		}else {
			x_inc = (float) (Math.random() * 2.5f + 2.0f);;
		}
		this.x = x;
		this.y = y;
	}
	
	public void update(Graphics g) {
		y_inc += 0.05f;
		if(left) {
			x_inc += 0.01f;
		}else {
			x_inc -= 0.01f;
		}
		y += y_inc;
		x += x_inc;
		updateC++;
		int xr = (int) x;
		int yr = (int) y;
		g.drawOval(xr, yr, 2, 2);
	}
	public int getCount() {
		return updateC;
	}
}


class Firework{
	private int x;
	private int y;
	private int w;
	private int h;
	private ArrayList<Particle> points;
	private int updateCount;
	private Color color;
	
	public Firework(int x, int y, int w, int h, Color color) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.color = color;
		updateCount = 0;
		points = new ArrayList<>();
		int n = 50;
		for(int i = 0; i < n; i++) {
			boolean f = false;
			if(i > 25) {
				f = true;
			}
			int xr = (int) (Math.random() * w + x);
			int yr = (int) (Math.random() * h + y);
			points.add(new Particle(xr, yr, f));
		}
	}
	
	public boolean updateDraw(Graphics g) {
		g.setColor(color);
		for(Particle p : points) {
			p.update(g);
		}
		updateCount++;
		return (updateCount >= 200);
	}
}


public class ConnectFourPanel extends JPanel {
	
	private Gameboard gb;
	private Player pl1;
	private Player pl2;
	private Piece sel_piece;
	private AI opp_ai;
	private boolean ai;
	private boolean won;
	private ArrayList<Piece> win_pieces;
	private ArrayList<Firework> fireworks;
	private int l_win = -1;
	private int fire_update = 0;
	private JLabel player1;
	private JLabel player2;
	
	public void resetGame() {
		 gb = new Gameboard();
		 pl1.setTurn(true);
		 won = false;
		 sel_piece.setColumn(0);
		 sel_piece.setC(pl1.getCol());
		 if(ai) {
			 opp_ai = new AI(gb);
		 }
		 fireworks.clear();
		 l_win = -1;
		 revalidate();
		 repaint();
		 requestFocus();
	}
	
	public void changeSelColumn(int column) {
		if(column >= 0 && column < 7) {
			sel_piece.setColumn(column);
			sel_piece.setX((sel_piece.getColumn() * 48) + 24);
		}
	}
	
	public void fireworkAnimation() {
		//int n = (int) (Math.random() * 5);
		Color c;
		if(l_win == 1) {
			c = Color.RED;
		}else {
			c = Color.YELLOW;
		}
		int x = (int) (Math.random() * 350);
		int y = (int) (Math.random() * 300);
		fireworks.add(new Firework(x, y, 50, 50, c));
	}
	
	
	public ConnectFourPanel(boolean ai) {
		gb = new Gameboard();
		pl1 = new Player(true, new Color(255, 50, 50));
		pl2 = new Player(false, new Color(255, 255, 0));
		sel_piece = new Piece(0, 0, 0, 0, pl1.getCol());
		opp_ai = new AI(gb);
		won = false;
		fireworks = new ArrayList<>();
		setLayout(null);
		player1 = new JLabel("Player 1: 0");
		player1.setForeground(new Color(255, 255, 255));
		player1.setBackground(new Color(255, 255, 255));
		player1.setFont(new Font("Agency FB", Font.PLAIN, 28));
		player1.setBounds(10, 400, 200, 34);
		add(player1);
		
		player2 = new JLabel("Player 2: 0");
		player2.setForeground(new Color(255, 255, 255));
		player2.setFont(new Font("Agency FB", Font.PLAIN, 28));
		player2.setBounds(10, 440, 200, 34);
		add(player2);
		
		
		setFocusable(true);
		requestFocus();
		
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			   switch(e.getKeyCode()) {
			   case KeyEvent.VK_A:
				   changeSelColumn(sel_piece.getColumn()-1);
				   break;
			   case KeyEvent.VK_D:
				   changeSelColumn(sel_piece.getColumn() + 1);
				   break;
			   case KeyEvent.VK_SPACE:
				   if(won) {
					   won = false;
					   resetGame();
				   }else {
					   if(pl1.isTurn()) {
						   gb.createPiece(sel_piece.getColumn(), 1, pl1.getCol());
						   sel_piece.setC(pl2.getCol());
						   if(!ai) {
							   pl1.setTurn(false);
							   
						   }else {
							   opp_ai.grabGameboard(gb);
							   int p = opp_ai.turn();
							   if(p != -1) {
								   gb.createPiece(p, 2, pl2.getCol());
							   }
							   sel_piece.setC(pl1.getCol());
						   }
					   }else {
						   gb.createPiece(sel_piece.getColumn(), 2, pl2.getCol());
						   sel_piece.setC(pl1.getCol());
						   pl1.setTurn(true);
					   }
					   if(gb.getWinState(1)) {
						  won = true;
						  win_pieces = gb.getWin();
						  l_win = 1;
						  fireworkAnimation();
						  pl1.setWins(pl1.getWins() + 1);
						  player1.setText("Player 1: " + pl1.getWins());
						   // gb = new Gameboard();
						  // pl1.setTurn(true);
					   }else if(gb.getWinState(2)) {
						   won = true;
						   win_pieces = gb.getWin();
						   l_win = 2;
						   pl2.setWins(pl2.getWins() + 1);
						   player2.setText("Player 2: " + pl2.getWins());
						   fireworkAnimation();
						   //gb = new Gameboard();
					   }
				   }
				   break;
			   }
			   revalidate();
			   repaint();
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		g.clearRect(0, 0, getWidth(), getHeight());
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, getWidth(), getHeight());
		gameLoop(g);
	}
	
	
	
	private void gameLoop(Graphics g) {
		//drawing grid
		g.setColor(new Color(255, 255, 255));
		int[][] board = gb.getBoard();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				g.drawRect(10 + i * 50, 40 + j * 48, 50, 48);
			}
		}
		
		
		//drawing selection piece
		g.setColor(sel_piece.getC());
		g.drawOval(sel_piece.getX(), sel_piece.getY(), 30, 30);
		
		//drawing currently placed pieces
		ArrayList<Piece> pieces = gb.getPieces();
		
		for(Piece i : pieces) {
			g.setColor(i.getC());
			g.drawOval(i.getX(), i.getY(), 30, 30);
		}
		
		for(int i = 0; i < fireworks.size();) {
			if(fireworks.get(i).updateDraw(g)) {
				fireworks.remove(i);
			}else {
				i++;
			}
		}
		
		if(won) {
			g.setColor(Color.GREEN);
			int x1 =  20 + win_pieces.get(0).getColumn() * 50;
			int y1 = 50 + (win_pieces.get(0).getRow() * 48);
			int x2 = 20 + (win_pieces.get(win_pieces.size() - 1).getColumn() * 50);
			int y2 = 50 + (win_pieces.get(win_pieces.size() - 1).getRow() * 48);
			g.drawLine(x1, y1, x2, y2);
			fire_update++;
			if(fire_update >= 40) {
				fireworkAnimation();
				fire_update = 0;
			}
			/*int x = 10 + win_pieces.get(0).getColumn() * 50;
			int y = 40 + (win_pieces.get(win_pieces.size() - 1).getRow() * 48);
			int w = 50 + (win_pieces.get(win_pieces.size() - 1).getColumn() - win_pieces.get(0).getColumn()) * 50;
			int h = 48 + (win_pieces.get(0).getRow() - win_pieces.get(win_pieces.size() - 1).getRow()) * 48;
			
			System.out.println("X: " + x + " w: " + w + " h: " + h + " y:" + y);
			g.drawOval(x, y, w, h);*/
		}
		revalidate();
		repaint();
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
