package com.bots.kingsroad.event;

import com.bots.kingsroad.OutputManager;
import com.bots.kingsroad.PatternRecognition;
import com.bots.kingsroad.dto.Area;

public class EventHandler {

	String mapGuyTemplate           = "mapGuyTemplate.png";
	String mapIndicatorTempalte     = "mapIndicatorTemplate.png";
	String missionIndicatorTemplate = "missionIndicatorTemplate.png";
	String autoModeActiveTemplate   = "autoModeActiveTemplate.png";
	String autoModeInactiveTemplate = "autoModeInactiveTemplate.png";
	
	PatternRecognition patternRecognition = new PatternRecognition();
	OutputManager outputManager = new OutputManager();
	
	public boolean isInTown(){
		
		if(patternRecognition.isMatch(mapGuyTemplate, 70)){
			return true;
		}else{
			return false;
		}	
	}
	
	
	public boolean isOnMap(){
		
		if(patternRecognition.isMatch(mapIndicatorTempalte, 70)){
			return true;
		}else{
			return false;
		}	
	}
	
	public boolean isInMission(){
		
		if(patternRecognition.isMatch(missionIndicatorTemplate, 70)){
			return true;
		}else{
			return false;
		}	
	}
	
	public boolean isInAutoMode(){
		
		if(patternRecognition.isMatch(autoModeActiveTemplate, 70)){
			return true;
		}else{
			return false;
		}	
	}
	
	
	public boolean goToMap(){
		Area area = patternRecognition.getMatchArea(mapGuyTemplate);
		
		if(area != null){
			return outputManager.clickTargetArea(area);	
		}
		
		return false;
	}
	
	
	public boolean goToQuestOnMap(String questTemplate){
		Area area = patternRecognition.getMatchArea(questTemplate);
		
		if(area != null){
			return outputManager.clickTargetArea(area);
		}
		
		return false;
	}
	
	
	public boolean activateAutoMode(){
		Area area = patternRecognition.getMatchArea(autoModeInactiveTemplate);
		
		if(area != null){
			return outputManager.clickTargetArea(area);
		}
		
		return false;
	}
	
	
}
