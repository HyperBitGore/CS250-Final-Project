import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
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

public class ConnectFourGUI extends JFrame {
	private JLabel lblNewLabel;
	private JLabel lbl_Player1;
	private JLabel lbl_Player2;
	private JButton btnPause;
	private JButton btnExit;
	private JLabel lblPlayer_1_Indicator;
	private JLabel lblPlayer_2_Indicator;
	private JLabel lblColumn0;
	private JLabel lblColumn1;
	private JLabel lblColumn2;
	private JLabel lblColumn3;
	private JLabel lblColumn4;
	private JLabel lblColumn5;
	private JLabel lblColumn6;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectFourGUI frame = new ConnectFourGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the panel.
	 */
	public ConnectFourGUI() {
		setBackground(Color.BLACK);
		setForeground(new Color(0, 0, 0));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblNewLabel = new JLabel("CONNECT 4");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		add(lblNewLabel);
		
		lblPlayer_1_Indicator = new JLabel(">");
		lblPlayer_1_Indicator.setForeground(Color.WHITE);
		add(lblPlayer_1_Indicator);
		
		lbl_Player1 = new JLabel("PLAYER 1");
		lbl_Player1.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_Player1.setForeground(Color.GREEN);
		add(lbl_Player1);
		
		lblColumn0 = new JLabel("___");
		lblColumn0.setForeground(Color.WHITE);
		add(lblColumn0);
		
		lblColumn1 = new JLabel("___");
		lblColumn1.setForeground(Color.WHITE);
		add(lblColumn1);
		
		lblColumn2 = new JLabel("___");
		lblColumn2.setForeground(Color.WHITE);
		lblColumn2.setBackground(Color.WHITE);
		add(lblColumn2);
		
		lblColumn3 = new JLabel("___");
		lblColumn3.setForeground(Color.WHITE);
		add(lblColumn3);
		
		lblColumn4 = new JLabel("___");
		lblColumn4.setForeground(Color.WHITE);
		add(lblColumn4);
		
		lblColumn5 = new JLabel("___");
		lblColumn5.setForeground(Color.WHITE);
		add(lblColumn5);
		
		lblColumn6 = new JLabel("___");
		lblColumn6.setForeground(Color.WHITE);
		add(lblColumn6);
		
		btnPause = new JButton("PAUSE");
		btnPause.setBackground(Color.DARK_GRAY);
		btnPause.setForeground(Color.BLACK);
		btnPause.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		add(btnPause);
		
		lblPlayer_2_Indicator = new JLabel(">");
		lblPlayer_2_Indicator.setForeground(Color.WHITE);
		add(lblPlayer_2_Indicator);
		
		lbl_Player2 = new JLabel("PLAYER 2");
		lbl_Player2.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl_Player2.setForeground(Color.GREEN);
		add(lbl_Player2);
		
		btnExit = new JButton("EXIT");
		btnExit.setBackground(Color.DARK_GRAY);
		btnExit.setForeground(Color.BLACK);
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnExit);
		
		setVisible(true);
		

		
	}


	

}
