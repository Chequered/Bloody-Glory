package net.src.bloodyglory;

import net.src.settings.Settings;
import net.src.states.GameState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
	
	public static AppGameContainer application;
	
	public Main(String name) {
		super(Engine.WINDOW_NAME);
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Main("Main Setup"));
		application = app;
		
		app.setDisplayMode(Settings.SCREEN_WIDTH,  Settings.SCREEN_HEIGHT, false);
		if(Settings.LIMIT_FRAMERATE){
			app.setTargetFrameRate(Settings.TARGET_FRAMERATE);
		}
		app.setShowFPS(false);
		app.setAlwaysRender(true);
		app.setFullscreen(Settings.FULLSCREEN);
		app.start();
		
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new GameState());
	}
}
