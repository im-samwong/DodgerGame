
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class PowerUp extends Rectangle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image powerup;
	
	PowerUp(int x, int y, int POWER_HEIGHT, int POWER_WIDTH) {
		super(x,y,POWER_HEIGHT,POWER_WIDTH);

	}
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		powerup = new ImageIcon(getClass().getClassLoader().getResource("powercu.png")).getImage();
		g2D.drawImage(powerup, x, y, height, width, null);
	}
	

}
