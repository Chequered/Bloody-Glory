package net.src.projectiles;

import net.src.data.Team;
import net.src.gameobjects.Entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Projectile extends Entity{

	public double velocity;
	protected double weight;
	protected double shooterStrength;
	protected Team team;
	
	public Projectile(String name, float x, float y, Image sprite) {
		super(name, x, y, sprite);
	}
	
	public void giveStats(Team team, double weight, double shooterStrength){
		this.weight = weight;
		this.shooterStrength = shooterStrength;
		this.velocity = 10 + this.shooterStrength;
		this.team = team;
	}
	
	public void update(GameContainer container, int delta) throws SlickException{
		super.update(container, delta);
		
		moveProjectile(delta);
	}
	
	float mX;
	float mY;
	private void moveProjectile(int delta){
		
		float mX = (float) Math.cos(Math.toRadians(sprite.getRotation()));
		float mY = (float) Math.sin(Math.toRadians(sprite.getRotation()));
		
		float vX = mX * (float) velocity;
		float vY = mY * (float) velocity;
		
		this.x += vX;
		this.y += vY;
		
		if(velocity > 0){
			velocity -= weight / 10 * delta;			
		}else{
			velocity = 0;
		}
	}
	
	
	public Team getTeam(){
		return team;
	}
	
	public void onCollision(){
		velocity = 0;
	}
	
}
