package gamecharacters;

import net.src.bloodyglory.Engine;
import net.src.projectiles.Projectile;
import net.src.projectiles.StoneProjectile;
import net.src.settings.InputType;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Transform;


public class PlayableCharacter extends GameCharacter{
	
	protected Controller controller;
	protected int controllerIndex;
	
	private double throwingStrength;
	private boolean buttonDown;
	private InputType inputType;
	private boolean thrown = true;
	
	public PlayableCharacter(String name, float x, float y, Image sprite) {
		super(name, x, y, sprite);
	}

	@Override
	public void render(GameContainer container, Graphics g){
		super.render(container, g);
		if(throwingStrength >= Engine.MAX_THROWING_STRENGTH){
			g.setColor(Color.green);
		}
		if(alive){
			g.drawString("Throwing Strength: " + (int) throwingStrength, x, y - 50);
		}
		g.setColor(Color.white);
	}
	
	@Override
	public void update(GameContainer container, int delta) throws SlickException{
		super.update(container, delta);
		controller = Controllers.getController(controllerIndex);
		if(alive){
			GetUserInput(container, delta);
			HandleUserInput(container, delta);
		}
	}

	
	public void giveController(int controller, InputType inputType){
		this.inputType = inputType;
		if(inputType == InputType.Controller){
			this.controllerIndex = controller;			
		}else if(inputType == InputType.Keyboard){
			
		}
	}
	
	private void GetUserInput(GameContainer container, int delta) throws SlickException{
		Input input = container.getInput();
		
		if(inputType == InputType.Keyboard){
			if(input.isMouseButtonDown(0)){
				buttonDown = true;			
			}else{
				buttonDown = false;
			}
			if(shotStanddown <= 0){
				moveToKeys(input, delta);
			}else if(shotStanddown > 0){
				shotStanddown -= 0.2 * delta;
			}

			rotateToMouse(container);
		}else if(inputType == InputType.Controller){
			if(controller.isButtonPressed(5)){
				buttonDown = true;			
			}else{
				buttonDown = false;
			}
			if(shotStanddown <= 0){
				moveToAxis(delta);
			}else if(shotStanddown > 0){
				shotStanddown -= 0.2 * delta;
			}

			rotateToAxis();
			handleControllerActions();
		}
		
	}

	private void moveToAxis(int delta) {
		if(controller.getXAxisValue() > 0.2 || controller.getXAxisValue() < -0.2){
			x += controller.getXAxisValue() * movementSpeed * delta;			
		}
		if(controller.getYAxisValue() > 0.2 || controller.getYAxisValue() < -0.2){
			y += controller.getYAxisValue() * movementSpeed * delta;
		}		
	}

	private void handleControllerActions(){
		
	}
	
	private void HandleUserInput(GameContainer container, int delta) throws SlickException {
		if(buttonDown){
			if(throwingStrength <= Engine.MAX_THROWING_STRENGTH){
				buildStrength();
				thrown = false;
			}
		}else if(!thrown){
			shoot(delta);
			throwingStrength = 0;
		}
		
	}

	private void rotateToAxis(){
		 double horz = controller.getRXAxisValue();
	     double vert = controller.getRYAxisValue();
	     sprite.setRotation((float)Math.toDegrees(Math.atan2(vert , horz)));
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
	
	private void moveToKeys(Input input, int delta) {
		if(input.isKeyDown(Input.KEY_W)){
			y -= movementSpeed * delta;
		}
		if(input.isKeyDown(Input.KEY_A)){
			x -= movementSpeed * delta;
		}
		if(input.isKeyDown(Input.KEY_S)){
			y += movementSpeed * delta;
		}
		if(input.isKeyDown(Input.KEY_D)){
			x += movementSpeed * delta;
		}
	}
	
	private void shoot(int delta) throws SlickException{
		StoneProjectile stone = new StoneProjectile("", x, y, new Image("assets/projectiles/pillum.png"));
		stone.giveStats(this.team, 0.2, throwingStrength);
		stone.sprite.setRotation(this.sprite.getRotation());
		stone.hitbox = stone.hitbox.transform(Transform.createRotateTransform((float)Math.toRadians(stone.sprite.getRotation()), stone.x + stone.width / 2, stone.x + stone.height / 2));
		Engine.instant.addEntity(stone);
		thrown = true;
		shotStanddown = agilty + throwingStrength * delta;
	}
	
	private void buildStrength(){
		throwingStrength += strength;
	}

	
	public void attackWithProjectile(Projectile projectile) throws SlickException{
		super.attackWithProjectile(projectile);
	}
}
