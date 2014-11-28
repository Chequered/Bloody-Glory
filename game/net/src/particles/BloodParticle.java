package net.src.particles;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;


public class BloodParticle extends Particle{
	
	private double maxLifeTime = 2.8;
	private double minLifeTime = 2;
	private double minVelocity = 0;
	private double maxVelocity = 2.8;
	private double lifeTime;
	
	public BloodParticle(Image sprite, float x, float y, float velocity) {
		super(sprite, x, y, velocity);
		this.maxLifeTime += Math.random() * 1.5;
		this.maxVelocity += Math.random() * 1.5;
		this.velocity += minVelocity + Math.random() * maxVelocity;
		if(this.velocity > maxVelocity){
			this.velocity = maxVelocity;
		}
		this.lifeTime = minLifeTime + Math.random() * maxLifeTime;
		this.weight = 2;
		this.scale = 0.2f;
		this.collideWithObjects = true;
		this.hitbox = new Circle(x, y, 4);
	}

	public void update(int delta){
		super.update(delta);
		double mX = Math.cos(Math.toRadians(direction));
		double mY = Math.sin(Math.toRadians(direction));
		
		double vX = mX * (float) velocity;
		double vY = mY * (float) velocity;
		
		this.x += vX;
		this.y += vY;

		if(this.lifeTime <= 0 && alive)
		{
			this.alive = false;
		}else{
			this.lifeTime -= 0.1;
		}
	}
	
}
