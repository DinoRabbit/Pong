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
	private int boundUpper, boundLower;

	public Opponent(int x, int y, ID id, Handler handler, int diff) 
	{
		super(x, y, id);
		this.handler = handler;
		
		//Set upper and lower bounds based on difficulty, used to calculate movement
		switch(diff)
		{
			case(1): 
				boundUpper = 100;
				boundLower = 0;
				break;
			case(2): 
				boundUpper = 50;
				boundLower = 50;
				break;
			default: 
				boundUpper = 100;
				boundLower = 20;
				
		}
				
		velY = 4;	
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 16, 100);
	}

	public void tick() 
	{
		//Logic for opponent to follow the ball
		int dest = 0;
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Ball) dest = (int)tempObject.getY();
		}
		
		if((y + boundLower) < dest && dest > (y + boundUpper)) y += velY;
		else if((y + boundLower) > dest && dest < (y + boundUpper)) y -= velY;
		y = Pong.clamp(y, 0, Pong.HEIGHT - 132);
		
		//Reverse the VelY when we hit the top or bottom of the window
		//if(y <= 0 || y >= Pong.HEIGHT - 172) velY *= -1;
		
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
					if(tempObject.getVelX() > 0)
					{
						tempObject.setVelX((int) -tempObject.getVelX());
						if(velY > 0 && tempObject.getVelY() < 0) tempObject.setVelY((int) -tempObject.getVelY());
						else if(velY < 0 && tempObject.getVelY() > 0) tempObject.setVelY((int) -tempObject.getVelY());
						
					}
				}
			}		
		}
	}

	public void render(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 16, 100);
	}

}

