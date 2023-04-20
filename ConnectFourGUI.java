import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConnectFourGUI extends JFrame {
	private JLabel playArea;
	private JLabel lblPlayerOne;
	private JLabel lblPlayerOneIndicator;
	private JLabel lblPlayerTwo;
	private JLabel lblPlayerTwoIndicator;
	private JButton btnExit;

	
	//private JPanel panel_1;
	private ConnectFourPanel gamePanel;
	private JButton btnReset;
													//game state
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ConnectFourGUI frame = new ConnectFourGUI();
					//frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the panel.
	 */
	public ConnectFourGUI(boolean ai) {
		getContentPane().setBackground(new Color(0, 0, 0));
		getContentPane().setLayout(null);
		setSize(600, 600);
		
		gamePanel = new ConnectFourPanel(ai);
		gamePanel.setForeground(new Color(128, 128, 128));
		gamePanel.setBounds(10, 11, 568, 400);
		getContentPane().add(gamePanel);
		//panel_1.setLayout(new GridLayout(SIDE1, SIDE2, 5, 0));		
		gamePanel.setBackground(new Color(192, 192, 192));
		
		lblPlayerOne = new JLabel("Player 1");
		lblPlayerOne.setForeground(new Color(255, 255, 255));
		lblPlayerOne.setBackground(new Color(255, 255, 255));
		lblPlayerOne.setFont(new Font("Agency FB", Font.PLAIN, 28));
		lblPlayerOne.setBounds(45, 430, 142, 34);
		getContentPane().add(lblPlayerOne);
		
		lblPlayerOneIndicator = new JLabel(">");
//		if(isPlayerOne == true)
//			lblPlayerOneIndicator.setForeground(new Color(255, 255, 255));
//		else
//			lblPlayerOneIndicator.setForeground(new Color(0, 0, 0));
		lblPlayerOneIndicator.setFont(new Font("Agency FB", Font.PLAIN, 28));
		lblPlayerOneIndicator.setBounds(20, 436, 15, 22);
		getContentPane().add(lblPlayerOneIndicator);
		
		lblPlayerTwo = new JLabel("Player 2");
		lblPlayerTwo.setForeground(new Color(255, 255, 255));
		lblPlayerTwo.setFont(new Font("Agency FB", Font.PLAIN, 28));
		lblPlayerTwo.setBounds(45, 465, 98, 39);
		getContentPane().add(lblPlayerTwo);
		
		lblPlayerTwoIndicator = new JLabel(">");
//		if(isPlayerOne == false)
//			lblPlayerTwoIndicator.setForeground(new Color(255, 255, 255));
//		else
//			lblPlayerTwoIndicator.setForeground(new Color(0, 0, 0));
		
		lblPlayerTwoIndicator.setFont(new Font("Agency FB", Font.PLAIN, 28));
		lblPlayerTwoIndicator.setBounds(20, 473, 15, 22);
		getContentPane().add(lblPlayerTwoIndicator);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectFourStartScreen.exitGame_Clicked();
			}
		});
		btnExit.setFont(new Font("Agency FB", Font.PLAIN, 18));
		btnExit.setBounds(487, 477, 89, 22);
		getContentPane().add(btnExit);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.resetGame();
			}
		});
		btnReset.setFont(new Font("Agency FB", Font.PLAIN, 18));
		btnReset.setBounds(487, 443, 89, 23);
		getContentPane().add(btnReset);
		
			
	}
}
