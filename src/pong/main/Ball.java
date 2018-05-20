package pong.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//The Ball for the game

public class Ball extends GameObject 
{

	public Ball(int x, int y, ID id) 
	{
		super(x, y, id);
		
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 16, 16);
	}


	public void tick() 
	{
		x += velX;
		y += velY;
		
		//Keep the ball in the window -- will need to add scoring capabilities later.
		
		if(y <= 0 || y >= Pong.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= Pong.WIDTH - 16) velX *= -1;
	}
	
	//Render the ball in the window
	public void render(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillOval((int)x, (int)y, 16, 16);
	}

}
