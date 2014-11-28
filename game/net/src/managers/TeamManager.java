package net.src.managers;

import gamecharacters.GameCharacter;

import java.util.ArrayList;

import net.src.bloodyglory.Engine;
import net.src.bloodyglory.GameManager;
import net.src.data.Team;
import net.src.gameobjects.Entity;
import net.src.systems.GameSystem;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TeamManager extends Manager{

	public ArrayList<Team> allTeams = new ArrayList<Team>();
	public ArrayList<Team> livingTeams = new ArrayList<Team>();
	public ArrayList<Team> deadTeams = new ArrayList<Team>();
	
	public Team victoriousTeam;
	
	
	@Override
	public void update(ArrayList<Entity> entities, int delta)
			throws SlickException {

		if(GameManager.inSession){
			if(livingTeams.size() == 1){
				setWinningTeam(livingTeams.get(0));
				victoriousTeam.addScore(1);
				resetStage();
			}
			
			for(Team team : allTeams){
				team.update(delta);
				if(team.livingTeamMembers.size() <= 0 && !deadTeams.contains(team)){
					killTeam(team);
				}
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) {	
	}

	public void createTeam(GameCharacter[] characters, String teamName, int startingPoints){
		Team team = new Team(teamName, startingPoints);
		for(int i = 0; i < characters.length; i ++){
			team.addTeamMember(characters[i]);
		}
		addTeam(team);
	}
	
	public void addTeam(Team team){
		allTeams.add(team);
		livingTeams.add(team);		
	}
	
	public ArrayList<Team> getTeams(){
		return allTeams;
	}
	
	public void killTeam(Team team){
		deadTeams.add(team);
		livingTeams.remove(team);
	}

	public void resetStage(){
		for(Team team : deadTeams){
			livingTeams.add(team);
		}
		deadTeams.clear();
		victoriousTeam = null;
		GameManager.inSession = false;
	}
	
	public void setWinningTeam(Team team){
		victoriousTeam = team;
	}
	
}
