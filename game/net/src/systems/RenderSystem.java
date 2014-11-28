package net.src.systems;

import java.util.ArrayList;

import net.src.bloodyglory.Engine;
import net.src.gameobjects.Entity;
import net.src.particles.Particle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class RenderSystem extends GameSystem{

	public void render(GameContainer container, Graphics g){
		for(int i = 0; i < Engine.instant.entities.size(); i++){
			Entity entity = Engine.instant.entities.get(i);
			if(entity.visable){
				entity.render(container, g);
			}
		}
		for(int i = 0; i < Engine.instant.particles.size(); i++){
			Particle particle = Engine.instant.particles.get(i);
				particle.render(container, g);
				if(Engine.renderHitboxes){
					g.draw(particle.getHitbox());
				}
		}
		for(int i = 0; i < Engine.instant.characters.size(); i++){
			Entity entity = Engine.instant.characters.get(i);
			if(entity.visable){
				entity.render(container, g);
			}
		}
	}

	@Override
	public void update(ArrayList<Entity> entities, GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
	}
	
}
