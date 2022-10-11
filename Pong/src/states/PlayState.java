package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.GUI;
import objects.Ball;
import objects.Player;

public class PlayState extends BasicGameState {
	
	Input input;
	Ball ball;
	Player genericPlayer;
	Player player1, player2;
	Player[] players;
	
	public void init(GameContainer window, StateBasedGame sbg) throws SlickException {
		input = window.getInput();
		ball = new Ball();
		genericPlayer = new Player();
		player1 = new Player();
		player2 = new Player();
		player1.getPlayer().setLocation(20, GUI.SCREEN_HEIGHT/2 - 50);
		player2.getPlayer().setLocation(GUI.SCREEN_WIDTH - player2.getPlayer().getWidth() - 20, GUI.SCREEN_HEIGHT/2 - 50);
		players = new Player[] {player1, player2};
	} //end of init method
	
	public void update(GameContainer window, StateBasedGame sbg, int delta) throws SlickException {
		
		//pause the game
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			//enter a pause state
			sbg.enterState(2);
		}
		
		//ball is constantly moving
		ball.move();
		
		//collision with right and left edges
		if(ball.hitsScreenRight()) {
			//player 1 gets a point and the direction is switched
			player1.setScore(player1.getScore() + 1);
			ball.setDirectionX(-1);
		} 
		else if(ball.hitsScreenLeft()) {
			//player 2 gets a point and the direction is switched
			player2.setScore(player2.getScore() + 1);
			ball.setDirectionX(1);
		} 
		
		//collision with top and bottom edges
		if(ball.hitsScreenTop()) {
			ball.setDirectionY(1);
		} 
		else if(ball.hitsScreenBottom()) {
			ball.setDirectionY(-1);
		} 
		
		//player 1 movement
		if(input.isKeyDown(Input.KEY_W)) {
			player1.moveUp();
		} 
		
		if(input.isKeyDown(Input.KEY_S)) {
			player1.moveDown();
		} 
		
		//player 2 movement
		if(input.isKeyDown(Input.KEY_UP)) {
			player2.moveUp();
		} 
		
		if(input.isKeyDown(Input.KEY_DOWN)) {
			player2.moveDown();
		} 
		
		/*check collision with player and switch directions accordingly*/
		
		for(Player p : players) {
			if(ball.collidesTop(p)) {
				//change direction on x axis and make the ball move upward
				switch(ball.getDirectionX()) {
				case -1:
					ball.setDirectionX(1);
					ball.setDirectionY(-1);
					break;
				case 1:
					ball.setDirectionX(-1);
					ball.setDirectionY(-1);
					break;
				} //end of switch
			}
			else if(ball.collidesBottom(p)) {
				//change direction on x axis and make the ball move downward
				switch(ball.getDirectionX()) {
				case -1:
					ball.setDirectionX(1);
					ball.setDirectionY(1);
					break;
				case 1:
					ball.setDirectionX(-1);
					ball.setDirectionY(1);
					break;
				} //end of switch
			}
			else if(ball.collidesMiddle(p)) {
				//change direction on x axis and ball doesn't move on y axis
				switch(ball.getDirectionX()) {
				case -1:
					ball.setDirectionX(1);
					ball.setDirectionY(0);
					break;
				case 1:
					ball.setDirectionX(-1);
					ball.setDirectionY(0);
					break;
				}
			}
		} //end of player loop
	} //end of update method
	
	public void render(GameContainer window, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(ball.getColor());
		g.fill(ball.getBall());
		g.setColor(genericPlayer.getColor());
		for(Player p : players) {
			g.fill(p.getPlayer());
		} //end of player loop
		
		g.setColor(genericPlayer.getScoreColor());
		g.drawString("" + player1.getScore(), 125, 25);
		g.drawString("" + player2.getScore(), GUI.SCREEN_WIDTH - 125, 25);
	} //end of render method

	public int getID() {
		return 1;
	} //end of getID method
} //end of PlayState class
