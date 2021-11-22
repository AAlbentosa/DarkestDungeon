package graphics;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame{
		
		private JPanel contentPane;
		
		public GameFrame() {
			setUndecorated(true);
			//getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
			//setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
			setSize(960,600);//Dimension del frame
			setLocationRelativeTo(null);
		}
}
