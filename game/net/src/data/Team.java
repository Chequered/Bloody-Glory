package net.src.data;

import gamecharacters.GameCharacter;

import java.util.ArrayList;

public class Team {
	
	protected String teamName;
	protected int teamScore;
	
	public ArrayList<GameCharacter> allTeamMembers = new ArrayList<GameCharacter>();
	public ArrayList<GameCharacter> livingTeamMembers = new ArrayList<GameCharacter>();
	public ArrayList<GameCharacter> deadTeamMembers = new ArrayList<GameCharacter>();
	
	public Team(String teamName, int startingScore){
		this.teamName = teamName;
		this.teamScore = startingScore;
	}
	
	public void addTeamMember(GameCharacter member){
		allTeamMembers.add(member);
		livingTeamMembers.add(member);
	}
	
	private void killTeamMember(GameCharacter member){
		if(member != null){
			livingTeamMembers.remove(member);
			deadTeamMembers.add(member);
		}
	}
	
	public void update(int delta){
		GameCharacter gChar = null;
		for(GameCharacter character : livingTeamMembers){
			if(!character.alive){
				gChar = character;
			}
		}
		killTeamMember(gChar);
	}

	public String getName() {
		return teamName;
	}
		
	public int getTeamScore(){
		return teamScore;
	}
	
	public void addScore(int points){
		this.teamScore += points;
	}
	
}
