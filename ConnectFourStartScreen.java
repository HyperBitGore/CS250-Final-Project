import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.ButtonGroup;

public class ConnectFourStartScreen extends JFrame {

	private JPanel contentPane;
	private JLabel lblTitle;
	private JLabel lblAuthor;
	private JMenuBar menuBar;
	private JMenu mnControl;
	private JMenuItem mntmHighScoreReset;
	private JMenuItem mntmExitGame;
	private JMenu mnAbout;
	private JMenuItem mntmCredits;
	private JPanel panel;
	private JButton btnOnePlayerStart;
	private JButton btnTwoPlayerStart;
	private JButton btnHighScores;
	private JButton btnExit;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectFourStartScreen frame = new ConnectFourStartScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConnectFourStartScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 490);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnControl = new JMenu("Control");
		menuBar.add(mnControl);
		
		mntmHighScoreReset = new JMenuItem("Reset High Scores");
		mntmHighScoreReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetHighScore_Clicked();
			}
		});
		mnControl.add(mntmHighScoreReset);
		
		mntmExitGame = new JMenuItem("Exit Game");
		mntmExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitGame_Clicked();
			}
		});
		mnControl.add(mntmExitGame);
		
		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		mntmCredits = new JMenuItem("Credits");
		mntmCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutMenu_Clicked();
			}
		});
		mnAbout.add(mntmCredits);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(Color.GREEN, 2, true));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		lblTitle = new JLabel("CONNECT 4");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.GREEN);
		lblTitle.setFont(new Font("Agency FB", Font.PLAIN, 50));
		contentPane.add(lblTitle, BorderLayout.NORTH);
		
		lblAuthor = new JLabel("by: Hunter & Kellen");
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthor.setFont(new Font("Agency FB", Font.PLAIN, 20));
		lblAuthor.setForeground(Color.GREEN);
		contentPane.add(lblAuthor, BorderLayout.SOUTH);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		btnOnePlayerStart = new JButton("One Player Game");
		btnOnePlayerStart.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		buttonGroup.add(btnOnePlayerStart);
		btnOnePlayerStart.setFont(new Font("Agency FB", Font.PLAIN, 14));
		btnOnePlayerStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnOnePlayerStart);
		
		btnTwoPlayerStart = new JButton("Two Player Game");
		buttonGroup.add(btnTwoPlayerStart);
		btnTwoPlayerStart.setFont(new Font("Agency FB", Font.PLAIN, 14));
		btnTwoPlayerStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnTwoPlayerStart);
		
		btnHighScores = new JButton("High Scores");
		buttonGroup.add(btnHighScores);
		btnHighScores.setFont(new Font("Agency FB", Font.PLAIN, 14));
		btnHighScores.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnHighScores);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitGame_Clicked();
			}
		});
		btnExit.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		buttonGroup.add(btnExit);
		btnExit.setFont(new Font("Agency FB", Font.PLAIN, 14));
		btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnExit);
	}

	protected void aboutMenu_Clicked() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "Created by: Kellen Kopp & Hunter Lisowski\n" + "For: CS250 Final Project\n" + "Date: 2-8-2023", getTitle(), JOptionPane.INFORMATION_MESSAGE);
	}

	protected void resetHighScore_Clicked() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "Are you sure you want to delete high scores? This cannot be undone.", getTitle(), JOptionPane.ERROR_MESSAGE);
	}

	protected void exitGame_Clicked() {
		// TODO Auto-generated method stub
		System.exit(EXIT_ON_CLOSE);
	}

}
