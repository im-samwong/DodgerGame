import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;



public class Enemy extends Rectangle {
	
	

	private static final long serialVersionUID = 1L;
		Image astroid;
		Random random;
		int yVelocity;
		int xVelocity;
		int speed = 1;
		
		
	Enemy(int x, int y, int ENEMY_HEIGHT, int ENEMY_WIDTH) {
		super(x,y,ENEMY_HEIGHT,ENEMY_WIDTH);

		random = new Random();
	
		int randomXDirection = random.nextInt(2);
		if(random.nextInt(2) == 0)
			setXDirection(randomXDirection*speed + 1);
		else setXDirection(-randomXDirection*speed - 1);
		if(yVelocity >= 10)
			setYDirection((int)(10)*speed + 1);
		else if(Score.rounds/6 - 1 >= 0)
				setYDirection((int)(Score.rounds/6)*speed + 1);
			else setYDirection(speed);

	}
	
	 public void setXDirection(int xDirection) {
		 xVelocity = xDirection;
	 }
	 
	 public void setYDirection(int yDirection) {
		 yVelocity = yDirection;
	 }
	 
	 public void move() {
		 x += xVelocity;
		 y += yVelocity;
		 
	 }
	 
	 public void draw(Graphics g) {
		 
		 astroid = new ImageIcon(getClass().getClassLoader().getResource("astroidr.png")).getImage();
		 Graphics2D g2D = (Graphics2D) g;
		 g2D.drawImage(astroid, x, y, height, width, null);
		 
	 }

}
