import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import javax.swing.JPanel;



public class GamePanel extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 700;
	static final Dimension SCREEN_SIZE = new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT);
	static final int PLAYER_HEIGHT = 180;
	static final int PLAYER_WIDTH = 76;
	static final int ENEMY_HEIGHT = 50;
	static final int ENEMY_WIDTH = 48;
	int PLAYER_BORDERX = 65;
	int PLAYER_BORDERY = 168;
	final int POWER_HEIGHT = 40;
	final int POWER_WIDTH = 40;
	Image image;
	Image space;
	Image spaceship;
	Image astroid;
	Image powerupmini;
	Graphics graphics;
	static Thread gameThread;
	Random random;
	static Player player;
	static PowerUp power;
	PowerUpMini powermini;
	static Enemy enemy;
	static Enemy enemy2;
	static Enemy enemy3;
	static Enemy enemy4;
	Score score;
	static boolean lost = false;
	boolean sameround = true;
	static boolean invincibility = false;
	boolean timesUp = true;
	Timer timer = new Timer();
	Timer timer2 = new Timer();
	int timeLeft = 5;
	int x;
	int i;

	
	
	GamePanel() {
		this.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		this.setPreferredSize(SCREEN_SIZE);
		this.setLocation(0, 0);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		newPlayer();
		newEnemy();
		newEnemy2();
		newEnemy3();
		newEnemy4();
		//newPower();
		//newPower2();
		power = new PowerUp(-200,0,POWER_HEIGHT,POWER_WIDTH);
		powermini = new PowerUpMini(-200,0,POWER_HEIGHT,POWER_WIDTH);
		score = new Score(SCREEN_WIDTH,SCREEN_HEIGHT);
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public void newPower2() {
		random = new Random();
		powermini = new PowerUpMini(random.nextInt(SCREEN_WIDTH-POWER_WIDTH),random.nextInt(SCREEN_HEIGHT-POWER_HEIGHT),POWER_HEIGHT,POWER_WIDTH);
	}
	
	public void newPower() {
		random = new Random();
		power = new PowerUp(random.nextInt(SCREEN_WIDTH-POWER_WIDTH),random.nextInt(SCREEN_HEIGHT-POWER_HEIGHT),POWER_HEIGHT,POWER_WIDTH);
	}
	
	public void newPlayer() {
		player = new Player(((SCREEN_WIDTH/2) - (PLAYER_WIDTH/2)),((SCREEN_HEIGHT/2) - (PLAYER_HEIGHT/2)), PLAYER_HEIGHT, PLAYER_WIDTH);
	}
	
	public void newEnemy4() {
		random = new Random();
		enemy4 = new Enemy(random.nextInt(SCREEN_WIDTH - ENEMY_WIDTH),-400,ENEMY_HEIGHT,ENEMY_WIDTH);
	}
	
	public void newEnemy3() {
		random = new Random();
		enemy3 = new Enemy(random.nextInt(SCREEN_WIDTH - ENEMY_WIDTH),-200,ENEMY_HEIGHT,ENEMY_WIDTH);
	}
	
	public void newEnemy2() {
		random = new Random();
		enemy2 = new Enemy(random.nextInt(SCREEN_WIDTH - ENEMY_WIDTH),-100,ENEMY_HEIGHT,ENEMY_WIDTH);
	}
	
	public void newEnemy() {
		random = new Random();
		enemy = new Enemy(random.nextInt(SCREEN_WIDTH - ENEMY_WIDTH),-50,ENEMY_HEIGHT,ENEMY_WIDTH);
	}
	
	public void move() {
		if(!lost) 
		{	
			player.move();
			enemy.move();
			enemy2.move();
			if(Score.rounds >= 6)
				enemy3.move();
			if(Score.rounds >= 17)
				enemy4.move();	
		}
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g2D.drawImage(image, 0, 0, this);
	
	}
	
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		space = new ImageIcon(getClass().getClassLoader().getResource("space.png")).getImage();
		g2D.drawImage(space, 0, 0, null);
		player.draw(g2D);
		enemy.draw(g2D);
		power.draw(g2D);
		powermini.draw(g2D);
		enemy2.draw(g2D);
		if(Score.rounds >= 6)
			enemy3.draw(g2D);
		if(Score.rounds >= 17)
			enemy4.draw(g2D);
		score.draw(g);
		Toolkit.getDefaultToolkit().sync();
	}
	
	public class AL extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
			if(lost)
			{
				if(e.getKeyCode() == 32)
				{
					random = new Random();
					player = new Player(((SCREEN_WIDTH/2) - (PLAYER_WIDTH/2)),((SCREEN_HEIGHT/2) - (PLAYER_HEIGHT/2)), PLAYER_HEIGHT, PLAYER_WIDTH);
					enemy = new Enemy(random.nextInt(SCREEN_WIDTH - ENEMY_WIDTH),-200,ENEMY_HEIGHT,ENEMY_WIDTH);
					enemy2 = new Enemy(random.nextInt(SCREEN_WIDTH - ENEMY_WIDTH),-250,ENEMY_HEIGHT,ENEMY_WIDTH);
					enemy3 = new Enemy(random.nextInt(SCREEN_WIDTH - ENEMY_WIDTH),-200,ENEMY_HEIGHT,ENEMY_WIDTH);
					enemy4 = new Enemy(random.nextInt(SCREEN_WIDTH - ENEMY_WIDTH),-400,ENEMY_HEIGHT,ENEMY_WIDTH);
					power.setLocation(-200, 0);
					powermini.setLocation(-200, 0);
					lost = false;
					Score.rounds = 0;
					
				}
			}
		}
		
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}
		
	}
	
	public void check() {
		if(player.x <= 0)
			player.x = 0;
		if(player.x >= SCREEN_WIDTH - PLAYER_BORDERX)
			player.x = SCREEN_WIDTH - PLAYER_BORDERX;
		if(player.y <= 0)
			player.y = 0;
		if(player.y >= SCREEN_HEIGHT - PLAYER_BORDERY)
			player.y = SCREEN_HEIGHT - PLAYER_BORDERY;
		
		random = new Random();
		
		if(Score.rounds >= 25)
		{
			if(!sameround)
			{
				if(power.x <= 0 || power.x >= (SCREEN_WIDTH - POWER_WIDTH) || power.y <= 0 || power.y >= (SCREEN_HEIGHT - POWER_HEIGHT))
				{
					if((Score.rounds) % 20 == 0)
					{
						if(random.nextInt((int)(6000/Score.rounds)) == 0)
							newPower();
					}	
				}
			}
		}
		
		if(Score.rounds < 25)
		{
			if(!sameround)
			{
				if(power.x <= 0 || power.x >= (SCREEN_WIDTH - POWER_WIDTH) || power.y <= 0 || power.y >= (SCREEN_HEIGHT - POWER_HEIGHT))
				{
					if((Score.rounds) % 15 == 0 && Score.rounds != 0)
					{
						if(random.nextInt(300) == 0)
							newPower();
					}	
				}
			}
		}
		
		if(Score.rounds >= 30)
		{
			if(!sameround)
			{
				if(powermini.x <= 0 || powermini.x >= (SCREEN_WIDTH - POWER_WIDTH) || powermini.y <= 0 || powermini.y >= (SCREEN_HEIGHT - POWER_HEIGHT))
				{
					if((Score.rounds) % 15 == 0 && Score.rounds != 0)
					{
						if(random.nextInt(6000/Score.rounds) == 0)
							newPower2();
					}	
				}
			}
		}
		
		if(Score.rounds < 30)
		{
			if(!sameround)
			{
				if(powermini.x <= 0 || powermini.x >= (SCREEN_WIDTH - POWER_WIDTH) || powermini.y <= 0 || powermini.y >= (SCREEN_HEIGHT - POWER_HEIGHT))
				{
					if((Score.rounds) % 10 == 0)
					{
						if(random.nextInt(300) == 0)
							newPower2();
					}	
				}
			}
		}
		
		if(player.intersects(power))
		{
			sameround = true;
			power.setLocation(-200, 0);
			score.powername = "Invincibility Active";
			invincibility = true;
			timer.cancel();
			timesUp = false;
			score.powertimer = "5";
			i = 2;
			timeLeft = 5;
			
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					invincibility = false;
					score.powername = "";
				}
			};
			timer = new Timer();
			timer.schedule(task, 6000);
		}	
		
		if(player.intersects(powermini))
		{
			player.tiny = true;
			sameround = true;
			powermini.setLocation(-200, 0);
			score.powername = "Shrink Mode Active";
			timer.cancel();
			timesUp = false;
			score.powertimer = "5";
			i = 2;
			timeLeft = 5;
			
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					player.tiny = false;
					score.powername = "";
				}
			};
			timer = new Timer();
			timer.schedule(task, 6000);
		}
		
		if(player.tiny)
		{
			PLAYER_BORDERX = 32;
			PLAYER_BORDERY = 82;
			player.setFrameFromDiagonal(player.x, player.y, player.x+PLAYER_BORDERX, player.y+PLAYER_BORDERY);
			
		} else {
			PLAYER_BORDERX = 65;
			PLAYER_BORDERY = 168;
			player.setFrameFromDiagonal(player.x, player.y, player.x+PLAYER_BORDERX, player.y+PLAYER_BORDERY);
				}
		
		if (i % 2 == 0 && !timesUp)
		{
			++i;
			timer2.cancel();
			
			TimerTask task1 = new TimerTask() {
				
			@Override
			public void run() {
				
				score.powertimer = String.valueOf(--timeLeft);
				i++;
				if(timeLeft <= -1)
				{
					score.powertimer = "";
					timesUp = true;
				}
			}
		};
			timer2 = new Timer();
			timer2.schedule(task1, 1000);
		}

		if(!invincibility)
		{
			if(player.intersects(enemy))
				lost = true;
			
			if(player.intersects(enemy2))
				lost = true;

			if(player.intersects(enemy3))
				lost = true;

			if(player.intersects(enemy4))
				lost = true;
		}
		
		if(enemy.x >= (SCREEN_WIDTH - ENEMY_WIDTH))
		{
			enemy.xVelocity++;
			enemy.xVelocity = -enemy.xVelocity;
		}
		if(enemy.x <= 0)
		{
			enemy.xVelocity--;
			enemy.xVelocity = -enemy.xVelocity;
		}
		if (enemy.y > SCREEN_HEIGHT)
		{
			newEnemy();
			Score.rounds++;
			sameround = false;
		}
		if (enemy2.x >= (SCREEN_WIDTH - ENEMY_WIDTH))
		{
			enemy2.xVelocity++;
			enemy2.xVelocity = -enemy2.xVelocity;
		}
		if(enemy2.x <= 0)
		{
			enemy2.xVelocity--;
			enemy2.xVelocity = -enemy2.xVelocity;
		}
		if (enemy2.y > SCREEN_HEIGHT)
		{
			newEnemy2();
			Score.rounds++;
			sameround = false;
		}
		if (enemy3.x >= (SCREEN_WIDTH - ENEMY_WIDTH))
		{
			enemy3.xVelocity++;
			enemy3.xVelocity = -enemy3.xVelocity;
		}
		if(enemy3.x <= 0)
		{
			enemy3.xVelocity--;
			enemy3.xVelocity = -enemy3.xVelocity;
		}
		if (enemy3.y > SCREEN_HEIGHT)
		{
			newEnemy3();
			Score.rounds++;
			sameround = false;
		}
		
		if (enemy4.x >= (SCREEN_WIDTH - ENEMY_WIDTH))
		{
			enemy4.xVelocity++;
			enemy4.xVelocity = -enemy4.xVelocity;
			
		}
		if(enemy4.x <= 0)
		{
			enemy4.xVelocity--;
			enemy4.xVelocity = -enemy4.xVelocity;
		}
		if (enemy4.y > SCREEN_HEIGHT)
		{
			newEnemy4();
			Score.rounds++;
			sameround = false;
		}
			
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move();
				check();
				repaint();
				delta--;
			}
		}
	}
}
		
		
	
	
	





	