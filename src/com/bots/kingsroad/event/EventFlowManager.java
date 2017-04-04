package com.bots.kingsroad.event;

public class EventFlowManager {
	
	EventHandler eventHandler = new EventHandler();
	String currentQuestTemplate;
	
	public EventFlowManager(String questTemplate){
		currentQuestTemplate = questTemplate;
	}
	
	public boolean processNextEvent(){
		
		if(eventHandler.isInMission()){
			if(!eventHandler.isInAutoMode()){
				return eventHandler.activateAutoMode();
			}else{
				//Heal if needed and activated.
				//
			}
			
			//End Level ?
		}
		
		if(eventHandler.isInTown()){
			return eventHandler.goToMap();
		}
		
		if(eventHandler.isOnMap()){
			return eventHandler.goToQuestOnMap(currentQuestTemplate);
		}
		
		return false;
	}

}
