package Snake;
import java.io.File;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * @author CodingBurmer
 */
public class RenderPanel extends JPanel
{
    int colorcount1 = 60;
    int colorcount2 = 0;
    int colorcount3 = 0;
	//public final Color rendom = new Color(colorcount);
    ImageIcon essen;
    

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Snake snake = Snake.snake; // das snake(oder andere Name) vom Type Snake = dem snake aus der Klasse Snake. wie z.b. Car.geschwindigkeit 

		
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 805, 700);
		//Spielfeld

		
		
		g.setColor(Color.blue);
		for (Point point : snake.snakeParts)
		{
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}
		//Schlangenkörper
		
		
		
		g.setColor(Color.cyan);
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		//Kopf
		
		
//		g.setColor(ColorMix.colorMixM());	
//		g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE, 15, 15);
		String cwd = new File("").getAbsolutePath();
		String path = cwd + "/Snake/Graphics/Foot.png";
		essen = new ImageIcon(path);

		essen.setImage(essen.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
		essen.paintIcon(this, g,snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE);
		//Essen
		
		g.setColor(Color.RED);
		for(Point enemy: snake.enemyParts) {
		    g.fillRect(enemy.x * Snake.SCALE, enemy.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		}
		g.setColor(Color.cyan);
        g.fillRect(snake.enemyHead.x * Snake.SCALE, snake.enemyHead.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		//feinde
		
		
		String string = "Score: " + snake.score + ", Length: " + snake.tailLength + ", Time: " + snake.time / 20;
		g.setColor(Color.white);	
		g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);
		//Infos

		
		
		string = "Game Over!";
		String string2 ="Press Space to restart the Game";
		if (snake.over)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 4);
			g.drawString(string2, (int) (getWidth() / 2 - string2.length() * 2.5f), (int) snake.dim.getHeight() / 3);
		}
		//Game Over

		
		
		string = "Paused!";
		if (snake.paused && !snake.over)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 4);
		}
		//Pause
		
		string = "Press Soace to start the Game";
        if (!snake.over)
        {
            g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 4);
        }
		//Start
	}
}