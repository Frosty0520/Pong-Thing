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
	Ball b;
	Player p;
	Player p1, p2;
	Player[] players;
	
	public void init(GameContainer window, StateBasedGame sbg) throws SlickException {
		input = window.getInput();
		b = new Ball();
		p = new Player();
		p1 = new Player();
		p2 = new Player();
		p1.getPlayer().setLocation(20, GUI.SCREEN_HEIGHT/2);
		p2.getPlayer().setLocation(GUI.SCREEN_WIDTH - p2.getPlayer().getWidth() - 20, GUI.SCREEN_HEIGHT/2);
		players = new Player[] {p1, p2};
	} //end of init method
	
	public void update(GameContainer window, StateBasedGame sbg, int delta) throws SlickException {
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(2);
		}
		
		//constantly move ball
		b.move();
		
		//collision with right and left edges
		if(b.hitsScreenRight()) {
			p1.setScore(p1.getScore() + 1);
			b.setDirectionX(-1);
		} 
		else if(b.hitsScreenLeft()) {
			p2.setScore(p2.getScore() + 1);
			b.setDirectionX(1);
		} 
		
		//collision with top and bottom edges
		if(b.hitsScreenTop()) {
			b.setDirectionY(1);
		} 
		else if(b.hitsScreenBottom()) {
			b.setDirectionY(-1);
		} 
		
		//player movement
		if(input.isKeyDown(Input.KEY_W)) {
			p1.moveUp();
		} 
		
		if(input.isKeyDown(Input.KEY_S)) {
			p1.moveDown();
		} 
		
		if(input.isKeyDown(Input.KEY_UP)) {
			p2.moveUp();
		} 
		
		if(input.isKeyDown(Input.KEY_DOWN)) {
			p2.moveDown();
		} 
		
		//check collision with player and switch directions accordingly
		for(Player p : players) {
			//collision with player
			if(b.collidesTop(p)) {
				switch(b.getDirectionX()) {
				case -1:
					b.setDirectionX(1);
					b.setDirectionY(-1);
					break;
				case 1:
					b.setDirectionX(-1);
					b.setDirectionY(-1);
					break;
				} //end of switch
			} 
			else if(b.collidesBottom(p)) {
				switch(b.getDirectionX()) {
				case -1:
					b.setDirectionX(1);
					b.setDirectionY(1);
					break;
				case 1:
					b.setDirectionX(-1);
					b.setDirectionY(1);
					break;
				} //end of switch
			} 
		} //end of player loop
	} //end of update method
	
	public void render(GameContainer window, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(b.getColor());
		g.fill(b.getBall());
		g.setColor(p.getColor());
		for(Player p : players) {
			g.fill(p.getPlayer());
		} //end of player loop
		
		g.setColor(p.getScoreColor());
		g.drawString("" + p1.getScore(), 125, 25);
		g.drawString("" + p2.getScore(), GUI.SCREEN_WIDTH - 125, 25);
	} //end of render method

	public int getID() {
		return 1;
	} //end of getID method
} //end of PlayState class
