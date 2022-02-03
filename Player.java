import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;



public class Player extends Rectangle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Image spaceship;
	Image space;
	int yVelocity;
	int xVelocity;
	int speed = 10;
	boolean tiny = false;
	
	Player(int x, int y, int PLAYER_HEIGHT, int PLAYER_WIDTH) {
		super(x, y, PLAYER_HEIGHT, PLAYER_WIDTH);
	
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W) 
			setYDirection(-speed);
		
		if(e.getKeyCode()==KeyEvent.VK_S) 
			setYDirection(speed);
		
		if(e.getKeyCode()==KeyEvent.VK_A) 
			setXDirection(-speed);
		
		if(e.getKeyCode()==KeyEvent.VK_D) 
			setXDirection(speed);
		
		if(e.getKeyCode()==38) 
			setYDirection(-speed);
		
		if(e.getKeyCode()==40) 
			setYDirection(speed);
		
		if(e.getKeyCode()==37) 
			setXDirection(-speed);
		
		if(e.getKeyCode()==39) 
			setXDirection(speed);	
			
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W) 
			setYDirection(0);
		
		if(e.getKeyCode()==KeyEvent.VK_S) 
			setYDirection(0);
		
		if(e.getKeyCode()==KeyEvent.VK_A) 
			setXDirection(0);
		
		if(e.getKeyCode()==KeyEvent.VK_D) 
			setXDirection(0);
		
		if(e.getKeyCode()==38) 
			setYDirection(0);
		
		if(e.getKeyCode()==40) 
			setYDirection(0);
		
		if(e.getKeyCode()==37) 
			setXDirection(0);
		
		if(e.getKeyCode()==39) 
			setXDirection(0);
	}
	
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	
	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}
	
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}
	
	
	public void draw(Graphics g) {

		spaceship = new ImageIcon(getClass().getClassLoader().getResource("rocketr.png")).getImage();
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(spaceship, x, y, width, height, null);
		
		
	}
	
}