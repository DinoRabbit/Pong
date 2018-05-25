package pong.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

//Class for the opponent object

public class Opponent extends GameObject 
{
	Random r = new Random();
	private Handler handler;

	public Opponent(int x, int y, ID id, Handler handler, int spd) 
	{
		super(x, y, id);
		this.handler = handler;
		velY = spd;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 16, 150);
	}

	public void tick() 
	{
		y+=velY;
		
		//Reverse the VelY when we hit the top or bottom of the window
		if(y <= 0 || y >= Pong.HEIGHT - 172) velY *= -1;
		
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

