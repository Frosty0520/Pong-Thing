package objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

import main.GUI;

public class Player {

	private Rectangle playerRect;
	private Color playerColor;
	private Color scoreColor;
	private float speed;
	private int score;
	
	public Player() {
		//start the rectangle at 0,0 and reposition after initializing
		playerRect = new Rectangle(0, 0, 15, 100);
		playerColor = Color.pink;
		speed = 5;
		scoreColor = Color.white;
		score = 0;
	} //end of default constructor
	
	//getters and setters
	public Rectangle getPlayer() {
		return this.playerRect;
	} //end of getPlayer method
	
	public Color getColor() {
		return this.playerColor;
	} //end of getColor method
	
	public float getSpeed() {
		return this.speed;
	} //end of getSpeed method
	
	public Color getScoreColor() {
		return this.scoreColor;
	} //end of getScoreColor method
	
	public int getScore() {
		return this.score;
	} //end of getScore method
	
	public void setScore(int newScore) {
		this.score = newScore;
	} //end of setScore method
	
	//top and bottom edges of player
	private float getPlayerTop() {
		return playerRect.getY();
	} //end of getPlayerTop method
	
	private float getPlayerBottom() {
		return playerRect.getY() + playerRect.getHeight();
	} //end of getPlayerBottom method
	
	//movement
	public void moveUp() {
		playerRect.setCenterY(playerRect.getCenterY() - speed);
	} //end of moveUp method
	
	public void moveDown() {
		playerRect.setCenterY(playerRect.getCenterY() + speed);
	} //end of moveDown method
	
	//collision with top and bottom of screen
	public boolean collidesScreenTop() {
		if(getPlayerTop() < 0) {
			return true;
		} 
		return false;
	} //end of collidesScreenTop method
	
	public boolean collidesScreenBottom() {
		if(getPlayerBottom() > GUI.SCREEN_HEIGHT) {
			return true;
		} 
		return false;
	} //end of collidesScreenBottom method
} //end of Player class
