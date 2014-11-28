package net.src.ui;

import java.util.ArrayList;

import net.src.bloodyglory.Engine;
import net.src.bloodyglory.GameManager;
import net.src.data.Team;
import net.src.settings.Settings;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;

public class GameTeamScore extends UIElement{

	protected ArrayList<Team> teams = new ArrayList<Team>();
	protected boolean centered;
	
	public GameTeamScore(Vector2f position, boolean center, TrueTypeFont font){
		this.position = position;
		this.centered = center;
		this.font = font;
	}
	
	@Override
	public void update(int delta) {		
		
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		if(getVisible()){
			String textToRender = "";
			String textToRenderLower = "";
			ArrayList<String> teamNames = new ArrayList<String>();
			ArrayList<String> teamScores = new ArrayList<String>();
			if(teams.size() == 2){
				for(int i = 0; i < 2; i ++){
					if(i == 0){
						textToRender += teams.get(i).getName() + ": " + teams.get(i).getTeamScore() + " | ";						
					}else{
						textToRender += teams.get(i).getTeamScore() + " :" + teams.get(i).getName();
					}
				}			
			}else if(teams.size() == 3){
				for(int i = 0; i < 3; i ++){
					teamNames.add(teams.get(i).getName());	
					teamScores.add(textToRenderLower += teams.get(i).getTeamScore()); 
				}
			}
			if(!centered){
				g.drawString(textToRender, position.x, position.y);
				g.drawString(textToRenderLower, position.x, position.y);
			}else{
				g.drawString(textToRender, Settings.SCREEN_WIDTH / 2 - font.getWidth(textToRender) / 2, position.y);
				g.drawString(textToRenderLower, Settings.SCREEN_WIDTH / 2 - font.getWidth(textToRender) / 2, position.y + 20);
			}
		}
	}	
	
	public void addTeam(Team team){
		teams.add(team);
	}
	
	public void addTeam(ArrayList<Team> team){
		teams.addAll(team);
	}
	
}
