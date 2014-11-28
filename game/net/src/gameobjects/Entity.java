package net.src.gameobjects;

import net.src.data.Team;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import debug.Debug;

public abstract class Entity {

	protected Debug debug;
	
	public float x;
	public float y;
	public float width;
	public float height;
	public Image sprite;
	public Shape hitbox;
	public String name;
	public boolean visable;
	public EntityLayer entityLayer;
	public float scale;
	
	public Entity(String name, float x, float y, Image sprite){
		this.name = name;
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();	
		this.visable = true;
		this.scale = 1;
		this.hitbox = new Rectangle(x, y, width * scale, height * scale);
		this.hitbox.setCenterX(x);
		this.hitbox.setCenterY(y);
		this.entityLayer = EntityLayer.None;
		this.debug = Debug.debug;
	}

	public void update(GameContainer container, int delta) throws SlickException {
		this.hitbox.setCenterX(x);
		this.hitbox.setCenterY(y);
	}
	
	public void render(GameContainer container, Graphics g){
		sprite.drawCentered(x, y);
	}
	
	public EntityLayer getEntityLayer(){
		return entityLayer;
	}
	
	public void setEntityLayer(EntityLayer layer){
		this.entityLayer = layer;
	}
	
	public abstract Team getTeam();
	
	public abstract void onCollision();
	
	public void onDebugDrag(){
		
	}

	public void updateHitboxPos() {
		this.hitbox.setCenterX(x);
		this.hitbox.setCenterY(y);
	}
	
}
