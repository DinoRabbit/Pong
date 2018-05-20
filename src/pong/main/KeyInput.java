package pong.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import pong.main.Pong.STATE;

//Class to handler key input(player movement)

public class KeyInput extends KeyAdapter
{
	private Handler handler;
	private boolean [] keyDown = new boolean[2];
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
		keyDown[0] = false;
		keyDown[1] = false;
	}
	
	//Handle when a key is pressed
	public void keyPressed(KeyEvent e)
	{
		//Depending on the key, change the player velocity accordingly
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player)
			{
				if(key == KeyEvent.VK_W) { tempObject.setVelY(-5); keyDown[0] = true; }
				if(key == KeyEvent.VK_S) { tempObject.setVelY(5); keyDown[1] = true; }
			}
		}
		//Pause game if P is pressed
		if(key == KeyEvent.VK_P)
		{
			if(Pong.gameState == STATE.Game)
				Pong.paused = !Pong.paused;
		}
		//Exit game if ESC is pressed
		if(key == KeyEvent.VK_ESCAPE) System.exit(0);
	}
	
	//Handle when a key is released
	public void keyReleased(KeyEvent e)
	{
		//Set the appropriate boolean to false in the keyDown array.
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player)
			{
				if(key == KeyEvent.VK_W) keyDown[0] = false; 
				if(key == KeyEvent.VK_S) keyDown[1] = false;
				
				//if both keys are released, VelY becomes 0
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
			}
		}
	}
}