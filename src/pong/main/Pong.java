package pong.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

//This is where the game logic is implemented

public class Pong extends Canvas implements Runnable 
{
	
	private static final long serialVersionUID = -473349850293143017L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	
	//private Random r;
	private Handler handler;
	private HUD hud;
	private Menu menu;
	
	//Enumeration to determine the state of the game
	public enum STATE 
	{
		Menu,
		Select,
		Help,
		Game,
		End
	};
	
	public static STATE gameState = STATE.Menu;
	
	public Pong()
	{
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		new Window(WIDTH, HEIGHT, "Game", this);
	}
	
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Simple game loop to keep the game running
	public void run()
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			
			if(running)
				render();
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick()
	{
		if(gameState == STATE.Game)
		{
			if(!paused)
			{
				hud.tick();
				handler.tick();
				
				/*if()
				{
					once the hud is implemented, this code
					will determine a game over/win
				}*/
			}
		}		
		else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select)
		{
			handler.tick();
			menu.tick();	
		}
	}
	
	//Render the window background as well as call render for hud, handler, and menu
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(paused)
		{
			g.setColor(Color.white);
			g.drawString("PAUSED", 100, 100);
		}
		if(gameState == STATE.Game)
		{
			hud.render(g);
			handler.render(g);
		}
		else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select)
		{
			menu.render(g);
			handler.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	//Method to be used to keep an object (the player mainly) from leaving the window
	public static float clamp(float var, float min, float max)
	{
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String [] args)
	{
		new Pong();
	}
}

