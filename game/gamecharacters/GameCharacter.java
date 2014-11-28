package gamecharacters;

import net.src.bloodyglory.Engine;
import net.src.data.Recources;
import net.src.data.Team;
import net.src.gameobjects.Entity;
import net.src.gameobjects.EntityLayer;
import net.src.particles.ParticleType;
import net.src.projectiles.Projectile;
import net.src.systementities.ParticleGroup;

import org.lwjgl.input.Controller;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;


public class GameCharacter extends Entity{

	protected float movementSpeed;
	protected double shotStanddown;
	protected Team team;
	public boolean alive;
	public int hitPoints;
	public double strength;
	public double agilty;
		
	public GameCharacter(String name, float x, float y, Image sprite) {
		super(name, x, y, sprite);
	}

	public void giveStats(Team team, int hitPoints, float movementSpeed, double strength, double agilty){
		this.hitPoints = hitPoints;
		this.movementSpeed = movementSpeed;
		this.strength = strength / 10;
		this.agilty = agilty;
		this.alive = true;
		this.team = team;
	}
	
	@Override
	public void render(GameContainer container, Graphics g){
		super.render(container, g);
		if(alive){
			g.drawString("HP: " + hitPoints ,x + width / 4, y - 15);
		}
	}

	public void controllerButtonPressed(int controller, int button) {
		
	}

	@Override
	public void onCollision() {		
	}
	
	private void kill() throws SlickException{
		sprite = new Image("assets/people/enemy_dead.png");
		alive = false;
	}
	
	public Team getTeam(){
		return team;
	}
	
	public void attackWithProjectile(Projectile projectile) throws SlickException{
			if(projectile.velocity > 0){
				this.hitPoints -= projectile.velocity * 1.5;
				sprayBlood(projectile);
				if(this.hitPoints <= 0){
					kill();
				}
			}
	}
	
	private void sprayBlood(Projectile projectile) throws SlickException{
		ParticleGroup bloodParticles = new ParticleGroup(120, ParticleType.Blood, new Image("assets/particles/blood_fancy.png"), new Vector2f(x, y), 1);
		bloodParticles.setDirection(projectile.sprite.getRotation(), 25);
		bloodParticles.addCollisonLayer(EntityLayer.WorldObjects);
		bloodParticles.addCollisonLayer(EntityLayer.Characters);
		Engine.instant.particleSystem.addParticleGroup(bloodParticles);
		
		ParticleGroup bloodParticlesDetails = new ParticleGroup(55, ParticleType.Blood, new Image("assets/particles/blood_fancy_small.png"), new Vector2f(x, y), 1);
		bloodParticlesDetails.setDirection(projectile.sprite.getRotation(), 42);
		bloodParticlesDetails.addCollisonLayer(EntityLayer.WorldObjects);
		bloodParticlesDetails.addCollisonLayer(EntityLayer.Characters);
		Engine.instant.particleSystem.addParticleGroup(bloodParticlesDetails);
	}
	
}
