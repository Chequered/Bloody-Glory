package net.src.systementities;

import net.src.bloodyglory.Engine;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class LightMapBlock {

	protected Shape hitbox;
	protected Rectangle rect;
	protected double lightStrength = 1;
	protected boolean render = true;
	protected Image img;
	private Color squareColor = Color.black;
	private GradientFill fill;
	
	public LightMapBlock(float x, float y, float width, float height) throws SlickException{
		this.hitbox = new Rectangle(x, y, width, height);
		this.hitbox.setCenterX(x);
		this.hitbox.setCenterY(y);
		fill = new GradientFill(0, 100 / 2, squareColor, 0, 100 - 1, squareColor, true);
		img = new Image("assets/systemEntities/black.png");
	}
	
	public void setLightStrength(double strength){
		this.lightStrength = strength;
	}
	
	public void update(GameContainer container, int delta){
		if(hitbox.contains(container.getInput().getAbsoluteMouseX(), container.getInput().getAbsoluteMouseY())){
			render = false;
		}else{
			render = true;
		}
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException{
		if(render){
			//render alphamap
		}
	}
	
	public boolean getRender(){
		return render;
	}
	
	public void setRender(boolean bool){
		render = bool;
	}
	
	
}
