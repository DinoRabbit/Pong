package pong.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//The Ball for the game

public class Ball extends GameObject 
{

	private HUD hud;
	private Handler handler;
	
	public Ball(int x, int y, int mode, ID id, Handler handler, HUD hud) 
	{
		super(x, y, id);
		this.hud = hud;
		this.handler = handler;
		
		if(mode <= 0) velX = -5;
		else velX = 5;
		
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
		
		if(x <= 0)
		{
			handler.addObject(new Ball(Pong.WIDTH/2, Pong.HEIGHT/2, 1, ID.Ball, handler, hud));
			hud.opponentPoint();
			handler.removeObject(this);
		}
		else if(x >= Pong.WIDTH - 16)
		{
			handler.addObject(new Ball(Pong.WIDTH/2, Pong.HEIGHT/2, -1, ID.Ball, handler, hud));
			hud.playerPoint();
			handler.removeObject(this);
		}
	}
	
	//Render the ball in the window
	public void render(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillOval((int)x, (int)y, 16, 16);
	}

}
