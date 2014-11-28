package net.src.managers;

import java.util.ArrayList;

import net.src.gameobjects.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public abstract class Manager {

	public abstract void update(ArrayList<Entity> entities, int delta) throws SlickException;

	public abstract void render(GameContainer container, Graphics g);
	
}
