package net.src.bloodyglory;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import net.src.gameobjects.Entity;
import net.src.managers.Manager;
import net.src.managers.TeamManager;

public class GameManager extends Manager{

	public static GameManager instant;
	public static boolean inSession;
	
	private ArrayList<Manager> _managers = new ArrayList<Manager>();
	
	public TeamManager teamManager;
	
	public GameManager(){
		teamManager = new TeamManager();
		_managers.add(teamManager);
		inSession = true;
	}
	
	@Override
	public void update(ArrayList<Entity> entities, int delta)
			throws SlickException {
		for(Manager manager : _managers){
			manager.update(entities, delta);
		}
		
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		for(Manager manager : _managers){
			manager.render(container, g);
		}
	}
	
	public void addManager(Manager manager){
		_managers.add(manager);
	}
	
}
