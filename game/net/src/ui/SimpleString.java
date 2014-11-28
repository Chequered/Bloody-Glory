package net.src.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class SimpleString extends UIElement{

	protected String text;
	
	public SimpleString(String text, boolean visible, Vector2f position){
		this.text = text;
		this.visible = visible;
		this.position = position;
	}
	
	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		if(getVisible()){
			g.drawString(text, position.x, position.y);
		}
		
	}
	
	public void setText(String txt){
		this.text = txt;
	}
	
	public String getText(){
		return text;
	}
	
}
