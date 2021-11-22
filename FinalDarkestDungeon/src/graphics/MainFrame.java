package graphics;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import graphics.panels.SignIn;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setUndecorated(true);
		getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		setSize(711,502);//Dimension del frame
		setLocationRelativeTo(null);
	}
}
