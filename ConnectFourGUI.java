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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;





public class ConnectFourGUI extends JFrame {
	private JLabel playArea;
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
		gamePanel.setBounds(10, 11, 500, 550);
		getContentPane().add(gamePanel);
		//panel_1.setLayout(new GridLayout(SIDE1, SIDE2, 5, 0));		
		gamePanel.setBackground(new Color(192, 192, 192));
		
		
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
