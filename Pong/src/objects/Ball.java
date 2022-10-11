package objects;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

import main.GUI;

public class Ball {

	//for calculating starting direction
	Random rand;
	
	private Circle ballCirc;
	private Color ballColor;
	private float speedY, speedX;
	private int directionX, directionY;
	
	public Ball() {
		rand = new Random();
		ballColor = Color.red;
		speedY = 3;
		speedX = 3;
		directionX = startingDirection();
		directionY = 0;
		ballCirc = new Circle(250, 250, 15);
	} //end of default constructor
	
	//getters and setters
	public Circle getBall() {
		return this.ballCirc;
	} //end of getBall method
	
	public Color getColor() {
		return this.ballColor;
	} //end of getColor method
	
	public float getSpeedY() {
		return this.speedY;
	} //end of getSpeedY method
	
	public float getSpeedX() {
		return this.speedX;
	} //end of getSpeedX method

	//movement code
	public int getDirectionX() {
		return this.directionX;
	} //end of getDirectionX method

	public void setDirectionX(int directionX) {
		this.directionX = directionX;
	} //end of setDirectionX method

	public int getDirectionY() {
		return this.directionY;
	} //end of getDirectionY method

	public void setDirectionY(int directionY) {
		this.directionY = directionY;
	} //end of setDirectionY method
	
	//collision with screen edges
	public boolean hitsScreenTop() {
		if(getTopEdge() < 0) {
			return true;
		} 
		return false;
	} //end of hitsScreenTop method

	public boolean hitsScreenRight() {
		if(getRightEdge() > GUI.SCREEN_WIDTH) {
			return true;
		} 
		return false;
	} //end of hitsScreenRight method
	
	public boolean hitsScreenBottom() {
		if(getBottomEdge() > GUI.SCREEN_HEIGHT) {
			return true;
		} 
		return false;
	} //end of hitsScreenBottom method
	
	public boolean hitsScreenLeft() {
		if(getLeftEdge() < 0) {
			return true;
		} 
		return false;
	} //end of hitsScreenLeft method
	
	//collision with player
	public boolean collidesTop(Player p) {
		Rectangle player = p.getPlayer();
		if(ballCirc.getCenterY() < player.getCenterY() && ballCirc.intersects(player)) {
			return true;
		} 
		return false;
	} //end of collidesTop method
	
	public boolean collidesBottom(Player p) {
		Rectangle player = p.getPlayer();
		if(ballCirc.getCenterY() > player.getCenterY() && ballCirc.intersects(player)) {
			return true;
		} 
		return false;
	} //end of collidesBottom method
	
	//left and right edges off ball
	private float getRightEdge() {
		return ballCirc.getX() + ballCirc.getRadius()*2;
	} //end of getRightEdge method
	
	private float getLeftEdge() {
		return ballCirc.getX();
	} //end of getLeftEdge method
	
	//top and bottom edges of ball
	private float getTopEdge() {
		return ballCirc.getY();
	} //end of getTopEdge method
	
	private float getBottomEdge() {
		return ballCirc.getY() + ballCirc.getRadius()*2;
	} //end of getBottomEdge method
	
	//movement code
	public void move() {
		ballCirc.setCenterX(ballCirc.getCenterX() + speedX * directionX);
		ballCirc.setCenterY(ballCirc.getCenterY() + speedY * directionY);
	} //end of move method
	
	//calculate starting direction
	private int startingDirection() {
		//50/50 chance of the ball moving in either direction
		int num = rand.nextInt(10) + 1;
		if(num > 5) {
			//move right
			return 1;
		}
		//move left
		return -1;
	} //end of startingDirection method
	
} //end of Ball class
