package com.bots.kingsroad.event;

public class EventFlowManager {
	
	EventHandler eventHandler = new EventHandler();
	String currentQuestTemplate;
	String currentDifficultyTemplate;
	boolean useFood;
	
	public EventFlowManager(String questTemplate, String difficultyTemplate, boolean useFood){
		currentQuestTemplate      = questTemplate;
		currentDifficultyTemplate = difficultyTemplate;
		useFood = this.useFood;
	}
	
	public int processNextEvent(){
		
		if(eventHandler.isInMission()){
			
			System.out.println("IN MISSION");
	
			if(eventHandler.reviveFree()) return 2;
			
			if(eventHandler.endLevel()) return 0;
			
			if(eventHandler.returnToTown()) return 1;
			
			
			if(!eventHandler.isInAutoMode()){
				if(eventHandler.activateAutoMode()) return 4;
			}
			
			//TODO this has to be last or constantly trying to use food with no food in a loop
			if(useFood){
				if(eventHandler.isLowHealth()){
					if(eventHandler.useFood()) return 3;
				}
			}
		
		}
		
		
				
		if(eventHandler.isInTown()){
			
			eventHandler.closePopUpsInTown();
			
			eventHandler.goToMap();
		}
		
		
		if(eventHandler.isOnMap()){
			if(!eventHandler.selectPlayAloneOnMap() && !eventHandler.selectPlayMapOnMap()){
				if(!eventHandler.selectQuestDifficultyOnMap(currentDifficultyTemplate)){
					   eventHandler.goToQuestOnMap(currentQuestTemplate); 
				}else{
					return 0;
				}
			}else{
				return 0;
			}
		}
		
		if(eventHandler.refreshOnDisconnect()){
			return 5;
		}
		
		if(eventHandler.play()){
			return 6;
		}
		
		//TODO: Might want to find a better place for it
		eventHandler.closeAnouncements();
		
		return 0;
	}

}