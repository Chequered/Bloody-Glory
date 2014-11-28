package debug;

import java.util.ArrayList;

import net.src.bloodyglory.Engine;
import net.src.gameobjects.Entity;
import net.src.settings.Settings;
import net.src.systems.GameSystem;
import net.src.ui.UIElement;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Debug extends GameSystem {

	public static Debug debug;

	private ArrayList<UIElement> _uiElements = new ArrayList<UIElement>();

	public DebugMode mode;
	private GameContainer container;
	
	public Debug(){
		debug = this;
	}
	
	@Override
	public void update(ArrayList<Entity> entities, GameContainer container,
			int delta) throws SlickException {
		getInput(container);
		for(UIElement ui : _uiElements){
			ui.update(delta);
		}
		if(mode == DebugMode.Edit){
			entityDrag(container);
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		if(Settings.DEBUG)
		{
			g.drawString("" + this.mode, 10, 50);
			for(UIElement ui : _uiElements){
				ui.render(container, g);
			}
			for(Entity entity : Engine.instant.entities)
			{
				g.drawString(entity.name , entity.x + entity.width / 4,entity.y - 30);
				g.draw(entity.hitbox);
				if(Engine.renderHitboxes){
					g.draw(entity.hitbox);			
				}
			}
		}
	}
	

	private void getInput(GameContainer container){
		this.container = container;
		Input i = container.getInput();
		if(!Settings.DEBUG){
			if(i.isKeyPressed(Input.KEY_GRAVE)){
				enableDebug(container);
			} 
		}
		if(i.isKeyPressed(Input.KEY_GRAVE) ){
			disableDebug(container);
		}
		if(Settings.DEBUG){
			if(i.isKeyPressed(Input.KEY_F1)){
				mode = DebugMode.Debug;
			}
			if(i.isKeyPressed(Input.KEY_F2)){
				mode = DebugMode.Edit;
			}
		}
	}
	
	private void enableDebug(GameContainer container){
		Settings.DEBUG = true;
		container.setShowFPS(true);		
	}
	
	private void disableDebug(GameContainer container){
		Settings.DEBUG = false;
		container.setShowFPS(false);
	}

	//Entity Dragging
	private boolean dragging;
	private Entity entityDragging;
	private void entityDrag(GameContainer container){
		Shape m = new Rectangle(container.getInput().getMouseX(), container.getInput().getMouseY(), 1, 1);
		for(Entity e : Engine.instant.entities){
			if(e.hitbox.intersects(m) && !dragging){
				
				if(container.getInput().isMouseButtonDown(0)){				
					dragging = true;
					entityDragging = e;
				}
			}
			if(dragging && entityDragging != null){
				entityDragging.x = container.getInput().getMouseX();	
				entityDragging.y = container.getInput().getMouseY();
				entityDragging.updateHitboxPos();
				if(!container.getInput().isMouseButtonDown(0))
				{
					dragging = false;
				}
			}
		}
	}
	
	public static void log(String txt){
		System.out.println(txt);
	}
	
	public static void log(int txt){
		System.out.println(txt);
	}

	public static void log(float txt){
		System.out.println(txt);
	}
		
	public static void log(double txt){
		System.out.println(txt);
	}
	
	public static void log(boolean txt){
		System.out.println(txt);
	}
	
	private void toggleBool(int keyCode, boolean bool){
		Input i = container.getInput();
		if(!bool){
			if(i.isKeyPressed(keyCode)){
				bool = true;
			} 
		}else if(i.isKeyPressed(keyCode) ){
			bool = false;
		}
	}
}
