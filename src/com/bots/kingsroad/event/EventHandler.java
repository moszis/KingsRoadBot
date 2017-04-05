package com.bots.kingsroad.event;

import java.awt.event.KeyEvent;

import com.bots.kingsroad.OutputManager;
import com.bots.kingsroad.PatternRecognition;
import com.bots.kingsroad.dto.Area;

public class EventHandler {

	String mapGuyTemplate           = "Byron.png";
	String mapIndicatorTempalte     = "CloseMapButton.png";
	String missionIndicatorTemplate = "QuestBookIcon.png";
	String autoModeActiveTemplate   = "AutoModeActive.png";
	String autoModeInactiveTemplate = "AutoModeInactive.png";
	String playAloneButtonTemplate  = "PlayAloneButton.png";
	String reviveFreeButtonTemplate = "ReviveFreeButton.png";
	String lowHealthTemplate        = "HealthLowUseFood.png";
	String endLevelTemplate         = "EndLevelButton.png";
	String returnToTownTemplate     = "ReturnToTownButton.png";
	
	String closePopUp1              = "ClosePopUp1InTown.png";
	String closePopUp2              = "ClosePopUp2InTown.png";
	
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
	
	public boolean isLowHealth(){
		
		if(patternRecognition.isMatch(lowHealthTemplate, 70)){
			return true;
		}else{
			return false;
		}	
	}
	
	
	public boolean goToMap(){
		Area area = patternRecognition.getMatchArea(mapGuyTemplate);
		
		if(area != null && area.isMatch()){
			return outputManager.clickTargetArea(area);	
		}
		
		return false;
	}
	
	
	public boolean goToQuestOnMap(String questTemplate){
		Area area = patternRecognition.getMatchArea(questTemplate);
		
		if(area != null && area.isMatch()){
			return outputManager.clickTargetArea(area);
		}
		
		return false;
	}
	
	
	public boolean selectQuestDifficultyOnMap(String difficultyTemplate){
		Area area = patternRecognition.getMatchArea(difficultyTemplate);
		
		if(area != null && area.isMatch()){
			return outputManager.clickTargetArea(area);
		}
		
		return false;
	}
	
	public boolean selectPlayAloneOnMap(){
		Area area = patternRecognition.getMatchArea(playAloneButtonTemplate);
		
		if(area != null && area.isMatch()){
			return outputManager.clickTargetArea(area);
		}
		
		return false;
	}
	
	public boolean activateAutoMode(){
		Area area = patternRecognition.getMatchArea(autoModeInactiveTemplate);
		
		if(area != null && area.isMatch()){
			return outputManager.clickTargetArea(area);
		}
		
		return false;
	}
	
	public boolean reviveFree(){
		Area area = patternRecognition.getMatchArea(reviveFreeButtonTemplate);
		
		if(area != null && area.isMatch()){
			return outputManager.clickTargetArea(area);
		}
		
		return false;
	}
	
	public boolean useFood(){

		return outputManager.pressKey(KeyEvent.VK_Q);

	}
	
	
	public boolean endLevel(){
		Area area = patternRecognition.getMatchArea(endLevelTemplate);
		
		if(area != null && area.isMatch()){
			return outputManager.clickTargetArea(area);
		}
		
		return false;
	}
	
	
	public boolean returnToTown(){
		Area area = patternRecognition.getMatchArea(returnToTownTemplate);
		
		if(area != null && area.isMatch()){
			return outputManager.clickTargetArea(area);
		}
		
		return false;
	}
	
	public void closePopUpsInTown(){
		Area area = patternRecognition.getMatchArea(closePopUp1);
		
		if(area != null && area.isMatch()){
			outputManager.clickTargetArea(area);
		}
		
		area = patternRecognition.getMatchArea(closePopUp2);
		
		if(area != null && area.isMatch()){
			outputManager.clickTargetArea(area);
		}
	}
}
