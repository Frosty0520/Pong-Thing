package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import states.PauseState;
import states.PlayState;
import states.ResultState;
import states.StartState;

public class GUI extends StateBasedGame {
	
	private static final boolean ALWAYS_RENDER = true;
	private static final boolean FULLSCREEN = false;
	private static final int FRAMERATE = 60;
	public static final int SCREEN_WIDTH = 500;
	public static final int SCREEN_HEIGHT = 500;
	public static final Color FONT_COLOR = Color.red;
	
	public GUI(String name) {
		super(name);
	} //end of default constructor

	public static void main(String[] args) throws SlickException {
		AppGameContainer gui = new AppGameContainer(new GUI("Pong"));
		gui.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, FULLSCREEN);
		gui.setTargetFrameRate(FRAMERATE);
		gui.setAlwaysRender(ALWAYS_RENDER);
		gui.start();
	} //end of main method

	public void initStatesList(GameContainer arg0) throws SlickException {
		this.addState(new StartState());
		this.addState(new PlayState());
		this.addState(new PauseState());
		this.addState(new ResultState());
	} //end of initStatesList method
	
} //end of GUI class
