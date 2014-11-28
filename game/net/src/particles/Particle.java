package net.src.particles;

import java.util.ArrayList;

import net.src.bloodyglory.Engine;
import net.src.gameobjects.EntityLayer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import debug.Debug;

public class Particle {
	protected Image sprite;
	protected double velocity;
	protected float direction;
	protected float spread;
	protected float x;
	protected float y;
	protected boolean collideWithObjects;
	protected boolean alive;
	protected ArrayList<EntityLayer> collisionLayers = new ArrayList<EntityLayer>();
	protected Shape hitbox;
	protected float weight;
	protected float scale = 1;
	
	public Particle(Image sprite, float x, float y, float velocity){
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		this.hitbox = new Circle(x, y, 4);
		this.alive = true;
	}
	
	public void update(int delta){
		if(alive){
			this.hitbox.setLocation(x, y);
		}
	}
	
	public Image getSprite() {
		if(sprite == null){
			Debug.log("The sprite of the particle you trying to render does not exist!");
			return null;
		}else{
			return sprite;
		}
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public Shape getHitbox(){
		return hitbox;
	}
	
	public boolean collidesWithObjects(){
		return collideWithObjects;
	}
	
	public void addCollisionLayer(EntityLayer layer){
		collisionLayers.add(layer);
	}
	
	public ArrayList<EntityLayer> collisionLayers(){
		return collisionLayers;
	}

	public void setAlive(boolean b) {
		this.alive = b;
	}
	
	public void setDirection(float direction, float spread){
		direction = direction - spread / 2;
		direction = direction + ((float) Math.random() * spread);
		this.direction = direction;
		this.spread = spread;
	}

	public void render(GameContainer container, Graphics g) {
		sprite.draw( x, y, scale);
	}

	public boolean isAlive() {
		return alive;
	}
	
	public float getScale(){
		return scale;
	}

	public void setCollidesWithObjects(boolean b) {
		collideWithObjects = b;
	}
}
