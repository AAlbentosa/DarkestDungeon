package graphics.panels;

import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Dashboard extends JPanel{
	
	public Dashboard() {
		setLayout(null);
		
		JLabel character1 = new JLabel("New label");
		character1.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/characters/dashboard/shooter.png")));
		character1.setBounds(338, 227, 197, 271);
		add(character1);
		
		JLabel character3 = new JLabel("New label");
		character3.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/characters/dashboard/robot.png")));
		character3.setBounds(468, 208, 197, 271);
		add(character3);
		
		JLabel character2 = new JLabel("New label");
		character2.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/characters/dashboard/sherif.png")));
		character2.setBounds(168, 197, 197, 271);
		add(character2);
		
		JLabel character4 = new JLabel("New label");
		character4.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/characters/dashboard/vikingo.png")));
		character4.setBounds(635, 208, 197, 271);
		add(character4);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/avatar.png")));
		lblNewLabel_1.setBounds(0, -6, 86, 111);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("blacmasterss");
		lblNewLabel_2.setBackground(new Color(0, 0, 0));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(149, 11, 117, 54);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/userbar.png")));
		lblNewLabel_4.setBounds(0, -25, 479, 148);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBackground(new Color(0, 0, 0));
		lblNewLabel_3.setBounds(30, 116, 115, 40);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/background.png")));
		lblNewLabel.setBounds(0, 0, 960, 600);
		add(lblNewLabel);
		
		
		
		
		
		
		
		
	}
}
