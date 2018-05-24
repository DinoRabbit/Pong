package pong.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.util.Random;

import pong.main.Pong.STATE;

//Class to draw and handle menus -- HUD still needs to be implemented

public class Menu extends MouseAdapter
{
	Pong game;
	Handler handler;
	private HUD hud;
	//private Random r = new Random();
	
	public Menu(Pong game, Handler handler, HUD hud)
	{
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	//Class for when the mouse button is pressed
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		//Change the game state and/or add objects depending on the current state and what button was pressed
		if(Pong.gameState == STATE.Menu)
		{
			//Play Button
			if(mouseOver(mx, my, 210, 150, 200, 64))
			{
				Pong.gameState = STATE.Select;
				return;
			}
			//Menu Button
			if(mouseOver(mx, my, 210, 250, 200, 64))
				Pong.gameState = STATE.Help;
			//Quit Button
			if(mouseOver(mx, my, 210, 350, 200, 64))
				System.exit(0);
		}
		
		if(Pong.gameState == STATE.Select)
		{
			//Normal Button
			if(mouseOver(mx, my, 210, 150, 200, 64))
			{
				Pong.gameState = STATE.Game;
				handler.addObject(new Player(50, Pong.HEIGHT/2 - 100, ID.Player, handler));
				handler.addObject(new Opponent(Pong.WIDTH - 70, Pong.HEIGHT/2 - 100, ID.Opponent, handler, 7));
				handler.addObject(new Ball(Pong.WIDTH/2, Pong.HEIGHT/2, -1, ID.Ball, handler, hud));
			}
			//Hard Button
			if(mouseOver(mx, my, 210, 250, 200, 64))
			{
				Pong.gameState = STATE.Game;
				handler.addObject(new Player(50, Pong.HEIGHT/2 - 100, ID.Player, handler));
				handler.addObject(new Opponent(Pong.WIDTH - 70, Pong.HEIGHT/2 - 100, ID.Opponent, handler, 15));
				handler.addObject(new Ball(Pong.WIDTH/2, Pong.HEIGHT/2, -1, ID.Ball, handler, hud));
			}
			//Back Button in Select
			if(mouseOver(mx, my, 210, 350, 200, 64))
			{
				Pong.gameState = STATE.Menu;
				return;
			}
		}
		
		if(Pong.gameState == STATE.Help)
			//Back Button in Help
			if(mouseOver(mx, my, 210, 350, 200, 64))
			{
				Pong.gameState = STATE.Menu;
				return;
			}
		if(Pong.gameState == STATE.End || Pong.gameState == STATE.Win)
			//Try Again
			if(mouseOver(mx, my, 210, 350, 200, 64))
			{
				Pong.gameState = STATE.Menu;
				hud.resetScore();
				return;
			}
	}
	
	//Mouse released is not needed, but is required by MouseAdapter
	public void mouseReleased(MouseEvent e)
	{
		
	}
	
	//Method to detect where the mouse is hovering
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if(mx > x && mx < x + width)
		{
			if(my > y && my < y + height)
			{
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public void tick()
	{
		
	}
	
	//Draw the menu, depending on the state of the game
	public void render(Graphics g)
	{
		if(Pong.gameState == STATE.Menu)
		{
			Font fnt = new Font("aerial", 1, 50);
			Font fnt2 = new Font("aerial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Particle Game Thing", 73, 70);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 282, 193);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 282, 293);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 282, 393);	
		}
		else if(Pong.gameState == STATE.Help)
		{
			Font fnt = new Font("aerial", 1, 50);
			Font fnt2 = new Font("aerial", 1, 30);
			Font fnt3 = new Font("aerial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 243, 70);
			
			g.setFont(fnt3);
			g.drawString("Use WASD to move player and dodge enemies", 105, 210);
			g.drawRect(210, 350, 200, 64);
			g.setFont(fnt2);
			g.drawString("Back", 282, 393);
		}
		else if(Pong.gameState == STATE.End)
		{
			Font fnt = new Font("aerial", 1, 50);
			Font fnt2 = new Font("aerial", 1, 30);
			Font fnt3 = new Font("aerial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("You Lose", 185, 70);
			
			g.setFont(fnt3);
			g.drawString("Final Score - Player: " + hud.getPlayerScore() + " Opponent: " + hud.getOpponentScore(), 130, 210);
			g.drawRect(210, 350, 200, 64);
			g.setFont(fnt2);
			g.drawString("Try Again", 243, 393);
		}
		else if(Pong.gameState == STATE.Win)
		{
			Font fnt = new Font("aerial", 1, 50);
			Font fnt2 = new Font("aerial", 1, 30);
			Font fnt3 = new Font("aerial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("You Win", 200, 70);
			
			g.setFont(fnt3);
			g.drawString("Final Score - Player: " + hud.getPlayerScore() + " Opponent: " + hud.getOpponentScore(), 130, 210);
			g.drawRect(210, 350, 200, 64);
			g.setFont(fnt2);
			g.drawString("Try Again", 243, 393);
		}
		if(Pong.gameState == STATE.Select)
		{
			Font fnt = new Font("aerial", 1, 50);
			Font fnt2 = new Font("aerial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Select Difficulty", 127, 70);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Normal", 263, 193);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Hard", 282, 293);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 279, 393);	
		}
	}
	
}
