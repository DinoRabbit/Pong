package pong.main;

import java.awt.Graphics;
import java.awt.Rectangle;

//Abstract class shared among all objects in the game

public abstract class GameObject 
{
	
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	
	public GameObject(float x, float y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	//Getters and Setters
	public void setX(int x)
	{
		this.x = x;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public float getX()
	{
		return x;
	}
	public float getY()
	{
		return y;
	}
	public void setID(ID id)
	{
		this.id = id;
	}
	public ID getId()
	{
		return id;
	}
	public void setVelX(int velX)
	{
		this.velX = velX;
	}
	public void setVelY(int velY)
	{
		this.velY = velY;
	}
	public float getVelX()
	{
		return velX;
	}
	public float getVelY()
	{
		return velY;
	}
}
