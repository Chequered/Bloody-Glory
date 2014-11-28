package net.src.systems;

import java.util.ArrayList;

import net.src.gameobjects.Entity;
import net.src.ui.UIElement;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class UISystem extends GameSystem{

	private ArrayList<UIElement> _uiElements = new ArrayList<UIElement>();
	
	@Override
	public void update(ArrayList<Entity> entities, GameContainer container, int delta)
			throws SlickException {
		for(UIElement ui : _uiElements){
			ui.update(delta);
		}
		
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		for(UIElement ui : _uiElements){
			ui.render(container, g);
		}		
	}
	
}
