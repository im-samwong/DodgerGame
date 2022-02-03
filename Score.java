import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Score extends Rectangle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int SCREEN_WIDTH;
	static int SCREEN_HEIGHT;
	static int rounds;
	String powername = "";
	String powertimer = "";
	
	Score(int SCREEN_WIDTH, int SCREEN_HEIGHT) {
		Score.SCREEN_HEIGHT = SCREEN_HEIGHT;
		Score.SCREEN_WIDTH = SCREEN_WIDTH;

	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas",Font.PLAIN,60));
		g.drawString(String.valueOf((int)(rounds/10))+String.valueOf((int)(rounds%10)), (SCREEN_WIDTH/2)-50, 50);
	
		g.setColor(new Color(255, 119, 0));
		g.setFont(new Font("Monospaced",Font.PLAIN, 20));
		g.drawString("POWER UP: " + powername + " " + powertimer, 20, SCREEN_HEIGHT - 20);
	
		if(GamePanel.lost)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Consolas",Font.PLAIN,60));	
			g.drawString("GAME OVER!", SCREEN_WIDTH/2 - 160,SCREEN_HEIGHT/2);
			g.setFont(new Font("Consolas",Font.PLAIN,30));
			g.drawString("Your score was: " + rounds, SCREEN_WIDTH/2 - 150,SCREEN_HEIGHT/2 + 50);
			g.setFont(new Font("Consolas",Font.PLAIN,20));
			g.drawString("Click 'space' to play again", SCREEN_WIDTH/2 - 140,SCREEN_HEIGHT/2 + 80);
		}
	}
}

