package net.src.systementities;

import java.util.ArrayList;

import net.src.bloodyglory.Engine;
import net.src.gameobjects.EntityLayer;
import net.src.particles.BloodParticle;
import net.src.particles.Particle;
import net.src.particles.ParticleType;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class ParticleGroup {

	protected int 	particlesTotal;
	protected int 	particlesLeft;
	
	protected float direction;
	protected float spread;
	
	protected ArrayList<Image> particleSprites = new ArrayList<Image>();
	protected Image particleSprite;
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	
	public ParticleGroup(int particlesTotal, ParticleType type, ArrayList<Image> particleSprites, Vector2f startPos, float velocity){
		this.particlesTotal = particlesTotal;
		this.particlesLeft = particlesTotal;
		if(type == ParticleType.Blood){
			for(int i = 0; i < particlesTotal; i++){
				BloodParticle particle = new BloodParticle(particleSprites.get(randomSprite()), startPos.x, startPos.y, velocity);
				particles.add(particle);
				Engine.instant.particles.add(particle);
			}			
		}
		else{
			for(int i = 0; i < particlesTotal; i++){
				Particle particle = new Particle(particleSprites.get(randomSprite()), startPos.x, startPos.y, velocity);
				particles.add(particle);
				Engine.instant.particles.add(particle);
			}
		}
	}
	
	public ParticleGroup(int particlesTotal, ParticleType type, Image particleSprite, Vector2f startPos, float velocity){
		this.particlesTotal = particlesTotal;
		this.particlesLeft = particlesTotal;
		if(type == ParticleType.Blood){
			for(int i = 0; i < particlesTotal; i++){
				BloodParticle particle = new BloodParticle(particleSprite, startPos.x, startPos.y, velocity);
				particles.add(particle);
				Engine.instant.particles.add(particle);
			}			
		}
		else{
			for(int i = 0; i < particlesTotal; i++){
				Particle particle = new Particle(particleSprites.get(randomSprite()), startPos.x, startPos.y, velocity);
				particles.add(particle);
				Engine.instant.particles.add(particle);
			}
		}
	}
	
	public void update(GameContainer container, int delta){
		for(Particle particle : particles){
			if(particle.isAlive()){
				particle.update(delta);
			}
		}
	}
	
	public void setDirection(float direction, float spread){
		for(Particle particle : particles){
			particle.setDirection(direction, spread);
		}
	}
	
	public void addCollisonLayer(EntityLayer layer){
		for(Particle particle : particles){
			particle.addCollisionLayer(layer);
		}
	}
	
	private int randomSprite(){
		return ((int)Math.floor(Math.random() * particles.size() - 1));
	}
}
