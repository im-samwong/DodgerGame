import java.awt.Color;
import javax.swing.JFrame;



public class GameFrame extends JFrame {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static GamePanel panel;
	
	
	GameFrame() {
		
		panel = new GamePanel();
		this.add(panel);
		this.setBackground(Color.BLACK);
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Dodge The Enemies!");
		this.setLayout(null);
		
	
	}


	

}
