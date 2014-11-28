package net.src.gameobjects;

import net.src.data.Team;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;

public class Prop extends Entity{

	public Prop(String name, float x, float y, Image sprite) {
		super(name, x, y, sprite);
		this.scale = 2;
		this.hitbox = new Circle(x, y, height / 2);
	}

	@Override
	public Team getTeam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCollision() {
		// TODO Auto-generated method stub
		
	}
}
