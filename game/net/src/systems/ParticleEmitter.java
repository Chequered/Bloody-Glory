package net.src.systems;

import java.io.File;
import java.util.ArrayList;

import net.src.bloodyglory.Engine;
import net.src.gameobjects.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class ParticleEmitter extends GameSystem{

	protected ParticleSystem particleSystem;
	protected ConfigurableEmitter emitter;
	protected float flowtime;
	
	protected float flowedTime;
	
	public ParticleEmitter(String sprite, float flowtime, String xmlFileLocation, float x, float y, float rotation, int amount) throws SlickException{
		particleSystem = new ParticleSystem(sprite, amount );
		this.flowtime = flowtime;
		
		try{
			File xmlFile = new File(xmlFileLocation);
			emitter = ParticleIO.loadEmitter(xmlFile);
			emitter.setPosition(0, 0);
			emitter.usePoints(particleSystem);
			emitter.angularOffset.setValue(rotation + 90);
			particleSystem.addEmitter(emitter);
			particleSystem.setPosition(x, y);
		} catch(Exception e){
		}
	}
	
	public void update(ArrayList<Entity> entities, GameContainer container, int delta){
		if(flowedTime < flowtime){
			particleSystem.update(delta);
		}else if(flowedTime < flowtime * 2){
			//emitter
			emitter.scaleY.setMax(emitter.scaleY.getMax() * 2);
			particleSystem.update(delta);
		}
		if(flowedTime <= flowtime * 2){
			flowedTime += 0.02 * delta;
		}
	}
	
	public void render(){
		particleSystem.render();
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
}
