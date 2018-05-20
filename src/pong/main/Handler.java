package pong.main;

import java.awt.Graphics;
import java.util.LinkedList;

//A class to handle functionality with the game objects (removing, adding, etc.)

public class Handler 
{	
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	//Tick all game objects
	public void tick()
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	//Render all game objects
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}		
	}
	
	//Remove all game objects
	public void clearAll()
	{
		for(int i = 0; i < object.size(); i++)
		{
			object.clear();
		}	
		
	}
	
	//Add/Remove an Object
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
}
