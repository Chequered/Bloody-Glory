package net.src.projectiles;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class StoneProjectile extends Projectile {
	
	public StoneProjectile(String name, float x, float y, Image sprite) {
		super(name, x, y, sprite);
	}
	
	public void update(GameContainer container, int delta) throws SlickException{
		super.update(container, delta);
	}
	
}
