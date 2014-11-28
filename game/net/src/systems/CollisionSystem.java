package net.src.systems;

import gamecharacters.GameCharacter;

import java.util.ArrayList;

import net.src.bloodyglory.Engine;
import net.src.gameobjects.Entity;
import net.src.gameobjects.EntityLayer;
import net.src.particles.Particle;
import net.src.projectiles.Projectile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class CollisionSystem extends GameSystem{

	private void checkProjectile(Entity projectile, ArrayList<GameCharacter> characters) throws SlickException{
		for(GameCharacter character : characters){
			if(projectile.getTeam() != character.getTeam()){
				Projectile proj = (Projectile) projectile;
				if(projectile.hitbox.intersects(character.hitbox) && character.alive){
					character.attackWithProjectile(proj);
					projectile.onCollision();
				}
			}
		}
	}
	
	private void checkEntity(ArrayList<Entity> entities, Entity entity)
	{
		for(Entity ent : entities)
		{
			if(ent.hitbox.intersects(entity.hitbox))
			{
				
			}
		}
	}
	
	private void checkParticle(ArrayList<Entity> entities, Particle particle){
		for(Entity entity : entities){
			for(EntityLayer layer : particle.collisionLayers()){
				if(layer == entity.getEntityLayer()){
					if(particle.getHitbox().intersects(entity.hitbox)){
						particle.setAlive(false);
						particle.setCollidesWithObjects(false);
					}
				}
			}
		}
	}
	
	public void update(ArrayList<Entity> entities, GameContainer container, int delta) throws SlickException{
		for(Entity entity : entities){
			if(entity instanceof Projectile){
				checkProjectile(entity, Engine.instant.characters);
			}else{
				checkEntity(entities, entity);
			}
		}
		for(Particle particle : Engine.instant.particles){
			if(particle.collidesWithObjects()){
				checkParticle(entities, particle);
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
}
