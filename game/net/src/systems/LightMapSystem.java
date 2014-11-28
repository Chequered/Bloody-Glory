package net.src.systems;

import java.util.ArrayList;

import net.src.gameobjects.Entity;
import net.src.settings.Settings;
import net.src.systementities.LightMapBlock;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class LightMapSystem extends GameSystem{

	public static final int LIGHTMAPBLOCK_WIDTH = 25;
	public static final int LIGHTMAPBLOCK_HEIGHT = 25;
	
	private ArrayList<LightMapBlock> _lightMapBlocks = new ArrayList<LightMapBlock>();
	
	public LightMapSystem() throws SlickException{
		int blocksWidth = Settings.SCREEN_WIDTH / LIGHTMAPBLOCK_WIDTH;
		int blocksHeight = Settings.SCREEN_HEIGHT / LIGHTMAPBLOCK_HEIGHT;
		for(int i = 0; i < blocksWidth; i++){
			for(int j = 0; j < blocksHeight; j++){
				LightMapBlock block = new LightMapBlock(i * LIGHTMAPBLOCK_WIDTH, j * LIGHTMAPBLOCK_HEIGHT, LIGHTMAPBLOCK_WIDTH, LIGHTMAPBLOCK_HEIGHT);
				_lightMapBlocks.add(block);
			}
		}
	}

	@Override
	public void update(ArrayList<Entity> entities, GameContainer container, int delta)
			throws SlickException {
		for(LightMapBlock block : _lightMapBlocks){
			block.update(container, delta);
		}
		
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		for(LightMapBlock block : _lightMapBlocks){
			block.render(container, g);
		}
	}
	
}
