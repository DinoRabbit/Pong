package pong.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

//Game Heads-up display to show and keep track of each player's score.

public class HUD 
{
	private int playerScore = 0;
	private int opponentScore = 0;
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		//display for the player and opponent scores
		Font fnt = new Font("aerial", 1, 30);
		
		g.setColor(Color.white);
		
		g.setFont(fnt);
		
		g.drawString("Score: " + playerScore, 5, 30);
		g.drawString("Score: " + opponentScore, Pong.WIDTH - 130, 30);
	}
	
	//getters and setters
	public int getPlayerScore()
	{
		return playerScore;
	}
	public int getOpponentScore()
	{
		return opponentScore;
	}
	public void playerPoint()
	{
		playerScore++;
	}
	
	public void opponentPoint()
	{
		opponentScore++;
	}
	
	public void resetScore()
	{
		playerScore = 0;
		opponentScore = 0;
	}
}
