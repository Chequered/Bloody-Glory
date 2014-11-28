package net.src.systems;

import java.util.ArrayList;

import net.src.gameobjects.Entity;
import net.src.systementities.ParticleGroup;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class ParticleSystem extends GameSystem{

	protected ArrayList<ParticleGroup> particleGroups = new ArrayList<ParticleGroup>();
	
	public ParticleSystem(){
		
	}

	public void addParticleGroup(ParticleGroup group){
		particleGroups.add(group);
	}
	
	@Override
	public void update(ArrayList<Entity> entities, GameContainer container,
			int delta) throws SlickException {
		for(ParticleGroup group : particleGroups){
			group.update(container, delta);
		}		
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
	}

	
}
