import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class ConnectFourGUI extends JPanel {
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public ConnectFourGUI() {
		setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("CONNECT 4");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		add(lblNewLabel, BorderLayout.NORTH);

	}

}
