package com.bots.kingsroad.event;

import java.util.concurrent.TimeUnit;

public class EventFlowManager {
	
	EventHandler eventHandler = new EventHandler();
	String currentQuestTemplate;
	String currentDifficultyTemplate;
	boolean useFood;
	
	long lastMissionStartTime = 0;
	long inMissionTime = 0;
	boolean inMission = false;
	boolean inTown    = false;
	boolean onMap     = false;
	
	
	public EventFlowManager(String questTemplate, String difficultyTemplate, boolean useFood){
		currentQuestTemplate      = questTemplate;
		currentDifficultyTemplate = difficultyTemplate;
		useFood = this.useFood;
	}
	
	public int processNextEvent(){
		
		if(inMission){
			if(TimeUnit.NANOSECONDS.toMinutes(inMissionTime) > 5){
				System.out.println("MORE THAN @ MINUTES!!**************");
				//return to town
			}else{
				pause(5);
			}
		}
		
		if(eventHandler.isInMission()){
			System.out.println("IN MISSION");
			if(!inMission){
				inMission = true;
				lastMissionStartTime = System.nanoTime();
			}

			inMissionTime = System.nanoTime() - lastMissionStartTime;
			inTown        = false;
			onMap         = false;
	
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
			
			inMissionTime = 0;
			inMission = false;
			inTown    = true;
			onMap     = false;
			
			eventHandler.closePopUpsInTown();
			
			eventHandler.goToMap();
		}
		
		
		if(eventHandler.isOnMap()){

			inMissionTime = 0;
			inMission = false;
			inTown    = false;
			onMap     = true;
			
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
			
			inMissionTime = 0;
			inMission = false;
			inTown    = false;
			onMap     = false;
			
			return 5;
		}
		
		if(eventHandler.play()){
			
			inMissionTime = 0;
			inMission = false;
			inTown    = false;
			onMap     = false;
			
			return 6;
		}
		
		//TODO: Might want to find a better place for it
		eventHandler.closeAnouncements();
		
		return 0;
	}

	
    private void pause(int seconds){
    	try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
    }
}