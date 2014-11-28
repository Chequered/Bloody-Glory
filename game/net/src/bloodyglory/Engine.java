package net.src.bloodyglory;

import gamecharacters.GameCharacter;

import java.util.ArrayList;

import net.src.gameobjects.Entity;
import net.src.particles.Particle;
import net.src.systems.CollisionSystem;
import net.src.systems.GameSystem;
import net.src.systems.LightMapSystem;
import net.src.systems.ParticleSystem;
import net.src.systems.RenderSystem;
import net.src.systems.UISystem;
import net.src.ui.UIElement;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import debug.Debug;
import debug.DebugMode;

public class Engine {

	public static final String VERSION_NUMBER = "0.1.2";
	public static final String WINDOW_NAME = "Alpha " + VERSION_NUMBER;
	public static final double MAX_THROWING_STRENGTH = 8;
	public static final boolean renderHitboxes = false;
	
	public static Engine instant;
	
	public ParticleSystem particleSystem = new ParticleSystem();
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<GameCharacter> characters = new ArrayList<GameCharacter>();
	public ArrayList<GameSystem> systems = new ArrayList<GameSystem>();
	public ArrayList<UIElement> uiElements = new ArrayList<UIElement>();
	public ArrayList<Particle> particles = new ArrayList<Particle>();
		
	private Debug debug;
	
	public Engine() throws SlickException{
		instant = this;
		
		systems.add(new CollisionSystem());
		systems.add(new RenderSystem());
		systems.add(new UISystem());
		systems.add(new LightMapSystem());
		systems.add(new Debug());
		
		debug = Debug.debug;
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
	}
	
	public void addCharacter(GameCharacter gc){
		characters.add(gc);
	}
	
	
	public void addUIElement(UIElement ui){
		uiElements.add(ui);
	}
	
	public void update(GameContainer container, int delta) throws SlickException{
		for (GameSystem sys : systems){
			sys.update(entities, container, delta);
		}
		for (Entity entity : entities){
			if(debug.mode != DebugMode.Edit){
				entity.update(container, delta);
			}
		}
		for (Entity character : characters){
			if(debug.mode != DebugMode.Edit){
				character.update(container, delta);
			}
		}
		for (UIElement ui : uiElements){
			ui.update(delta);
		}
		particleSystem.update(entities, container, delta);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException{
		for (GameSystem sys : systems){
			sys.render(container, g);
		}
		for(UIElement ui : uiElements){
			ui.render(container, g);
		}
	}	
}
