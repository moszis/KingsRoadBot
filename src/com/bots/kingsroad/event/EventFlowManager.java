package com.bots.kingsroad.event;

public class EventFlowManager {
	
	EventHandler eventHandler = new EventHandler();
	String currentQuestTemplate;
	String currentDifficultyTemplate;
	
	public EventFlowManager(String questTemplate, String difficultyTemplate){
		currentQuestTemplate      = questTemplate;
		currentDifficultyTemplate = difficultyTemplate;
	}
	
	public boolean processNextEvent(){
		
		if(eventHandler.isInMission()){
			
			System.out.println("IN MISSION");
			
			if(eventHandler.isLowHealth()){
				eventHandler.useFood();
			}
			
			if(eventHandler.reviveFree()) return true;
			
			if(eventHandler.endLevel()) return true;
			
			if(eventHandler.returnToTown()) return true;
			
			if(!eventHandler.isInAutoMode()){
				return eventHandler.activateAutoMode();
			}else{
				//Heal if needed and activated.
				//
			}
			
			//End Level ?
		}
		
		eventHandler.closePopUpsInTown();
				
		if(eventHandler.isInTown()){
			return eventHandler.goToMap();
		}
		
		if(eventHandler.isOnMap()){
			if(!eventHandler.selectPlayAloneOnMap()){
				if(!eventHandler.selectQuestDifficultyOnMap(currentDifficultyTemplate)){
					return eventHandler.goToQuestOnMap(currentQuestTemplate); 
				}else{
					return true;
				}
			}else{
				return true;
			}
		}
		
		return false;
	}

}