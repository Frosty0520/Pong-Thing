package states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.GUI;

//start screen for game
public class StartState extends BasicGameState {

	Input input;
	
	public void init(GameContainer window, StateBasedGame sbg) throws SlickException {
		
		input = window.getInput();
		
	} //end of init method

	public void update(GameContainer window, StateBasedGame sbg, int delta) throws SlickException {
		if(input.isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(1);
		}
	} //end of update method

	public void render(GameContainer window, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(GUI.FONT_COLOR);
		g.drawString("Press Enter to start", 160, 250);
	} //end of render method

	public int getID() {
		return 0;
	} //end of getID method

} //end of StartState class
