package gamecharacters;

import net.src.bloodyglory.Engine;
import net.src.projectiles.Projectile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class NonPlayerCharacter extends GameCharacter{

	public NonPlayerCharacter(String name, float x, float y, Image sprite) {
		super(name, x, y, sprite);
		
	}

	@Override
	public void update(GameContainer container, int delta){
		GetUserInput(container);
	}
	
	private void GetUserInput(GameContainer container){
		rotateToMouse(container);
	}

	private void rotateToMouse(GameContainer container) {
		Input userInput = container.getInput();
		float mouseX = userInput.getMouseX();
		float mouseY = userInput.getMouseY();
		float xDistance = mouseX - x;
		float yDistance = mouseY - y;
		double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
		sprite.setRotation((float)angleToTurn);
	}


	
	public void attackWithProjectile(Projectile projectile) throws SlickException{
	}
	
}
