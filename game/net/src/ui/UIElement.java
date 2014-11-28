package net.src.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;

public abstract class UIElement {
	
	protected boolean visible = true;
	protected boolean debug;
	protected Vector2f position;
	protected TrueTypeFont font;
	
	public abstract void update(int delta);
	
	public abstract void render(GameContainer container, Graphics g);
	

	public void setPosition(float x, float y){
		position.x = x;
		position.y = y;
	}
	
	public Vector2f getPosition(){
		return position;
	}
	
	public void setVisible(boolean state){
		visible = state;
	}
	
	public boolean getVisible(){
		return visible;
	}
	
}
