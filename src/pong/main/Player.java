package pong.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

//Class for the player object

public class Player extends GameObject 
{
	Random r = new Random();
	private Handler handler;

	public Player(int x, int y, ID id, Handler handler) 
	{
		super(x, y, id);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 16, 150);
	}

	public void tick() 
	{
		y+=velY;
		
		//Keep the player inside of the window
		y = Pong.clamp(y, 0, Pong.HEIGHT - 170);
		
		collision();
	}
	
	//When we collide with the ball object, reverse the ball's direction
	private void collision()
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Ball)
			{
				if(getBounds().intersects(tempObject.getBounds()))
				{
					tempObject.setVelX((int) -tempObject.getVelX());
					tempObject.setVelY((int) -tempObject.getVelY());
				}
			}		
		}
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 16, 150);
	}

}
