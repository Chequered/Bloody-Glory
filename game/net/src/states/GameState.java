package net.src.states;

import gamecharacters.PlayableCharacter;

import java.awt.Font;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.src.bloodyglory.Engine;
import net.src.bloodyglory.GameManager;
import net.src.bloodyglory.Main;
import net.src.data.Team;
import net.src.gameobjects.EntityLayer;
import net.src.gameobjects.Prop;
import net.src.particles.ParticleType;
import net.src.settings.InputType;
import net.src.settings.Settings;
import net.src.systementities.ParticleGroup;
import net.src.ui.GameTeamScore;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {

	private Engine _engine;
	private GameManager _gameManager;
	
	private TrueTypeFont font;
    Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
	    
    Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
    
	@Override
	public void init(GameContainer container, StateBasedGame sbg)
			throws SlickException {
		
		_engine = new Engine();
		_gameManager = new GameManager();
		
		font = new TrueTypeFont(awtFont, false);
				
		PlayableCharacter player = new PlayableCharacter("Djamali", Settings.SCREEN_WIDTH - 400, 200, new Image("assets/people/player.png")); //create a new Playable character named Player
		PlayableCharacter player2 = new PlayableCharacter("Daan", Settings.SCREEN_WIDTH - 450, 200, new Image("assets/people/enemy.png")); 
		
		player.setEntityLayer(EntityLayer.Characters);
		player2.setEntityLayer(EntityLayer.Characters);
		
		for(int i = 0; i < 10; i++){
			Prop barrel = new Prop("Barrel", (float) Math.random() * 800, (float)  Math.random() * 600, new Image("assets/props/barrel.png"));
			barrel.setEntityLayer(EntityLayer.WorldObjects);
			_engine.addEntity(barrel);
		}
		
		Team team1 = new Team("The good guys", 0); // create a new Team
		Team team2 = new Team("The not so good guys", 0);
		Team team3 = new Team("The SUPER good guys", 0);
		
		team1.addTeamMember(player); //add the members to the team
		team2.addTeamMember(player2);
		
		_gameManager.teamManager.addTeam(team1); //add the team to the team manager
		_gameManager.teamManager.addTeam(team2);
		_gameManager.teamManager.addTeam(team3);
		
		player.giveStats(team1, 400, (float) 0.2 , 0.4, 1); //team, hitpoints, movementspeed, strength, agilty
		player2.giveStats(team2, 60, (float) 0.2, 0.4, 1); 
				
		//player.giveController(0, InputType.Controller);
		player2.giveController(1, InputType.Keyboard);
		///player3.giveController(4, InputType.Controller);
		
		_engine.addCharacter(player); //add the player the the global entity list
		_engine.addCharacter(player2);
		
		GameTeamScore teamScore = new GameTeamScore(new Vector2f(0, 50), true, font); //create a new score counter
		teamScore.addTeam(_gameManager.teamManager.getTeams()); //give it the teams
		
		_engine.addUIElement(teamScore); //add the score counter to the UISystem
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		
		_engine.update(container, delta);
		_gameManager.update(_engine.entities, delta);
		getGlobalInput(container); //get keyboard / mouse input used for pausing / menus etc		
	}
		
	@Override
	public void render(GameContainer container, StateBasedGame state, Graphics g)
			throws SlickException {
		_engine.render(container, g);
		_gameManager.render(container, g);
	}
	
	private void getGlobalInput(GameContainer container) throws SlickException{
		if(container.getInput().isKeyDown(Input.KEY_ESCAPE)){
			if(Main.application.isFullscreen()){
				Main.application.setFullscreen(false);
			}
		}
	}
	
	@Override
	public int getID() {
		return 0;
	}
}
