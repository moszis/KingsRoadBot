package com.bots.kingsroad.event;

import java.awt.event.KeyEvent;

import com.bots.kingsroad.OutputManager;
import com.bots.kingsroad.PatternRecognition;
import com.bots.kingsroad.dto.Area;

public class EventHandler {

	String templateFolder = System.getProperty("templateFolder");
	
	String mapGuyTemplate           = templateFolder+"Byron.png";
	String mapIndicatorTempalte     = templateFolder+"CloseMapButton.png";
	String missionIndicatorTemplate = templateFolder+"QuestBookIcon.png";
	String autoModeActiveTemplate   = templateFolder+"AutoModeActive.png";
	String autoModeInactiveTemplate = templateFolder+"AutoModeInactive.png";
	String playAloneButtonTemplate  = templateFolder+"PlayAloneButton.png";
	String reviveFreeButtonTemplate = templateFolder+"ReviveFreeButton.png";
	String lowHealthTemplate        = templateFolder+"HealthLowUseFood.png";
	String endLevelTemplate         = templateFolder+"EndLevelButton.png";
	String returnToTownTemplate     = templateFolder+"ReturnToTownButton.png";
	String refreshTemplate          = templateFolder+"RefreshOnDisconnect.png";
	String playButtonTemplate       = templateFolder+"PlayButton.png";
	
	String closePopUp1              = templateFolder+"ClosePopUp1InTown.png";
	String closePopUp2              = templateFolder+"ClosePopUp2InTown.png";
	String closePopUp3              = templateFolder+"ClosePopUp3InTown.png";
	
	String ssSpecialsPopUp          = templateFolder+"SecretSocietySpecials.png";
	
	PatternRecognition patternRecognition = new PatternRecognition();
	OutputManager outputManager = new OutputManager();
	
	public boolean isInTown(){
		
		if(patternRecognition.isMatch(mapGuyTemplate, 60)){
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
		Area area = patternRecognition.getMatchArea(mapGuyTemplate, 60);
		
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
			boolean success = outputManager.clickTargetArea(area);
			outputManager.moveMouseToTopLeftCorner();
			return success;
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
		
		area = patternRecognition.getMatchArea(closePopUp3);
		
		if(area != null && area.isMatch()){
			outputManager.clickTargetArea(area);
		}
	}
	
	
	public void closeAnouncements(){
		Area area = patternRecognition.getMatchArea(ssSpecialsPopUp);
		
		if(area != null && area.isMatch()){
			outputManager.clickTargetArea(area);
		}
	}
	
	public boolean refreshOnDisconnect(){
		Area area = patternRecognition.getMatchArea(refreshTemplate);
		
		if(area != null && area.isMatch()){
			return outputManager.clickTargetArea(area);
		}
		
		return false;
	}
	
	public boolean play(){
		Area area = patternRecognition.getMatchArea(playButtonTemplate);
		
		if(area != null && area.isMatch()){
			return outputManager.clickTargetArea(area);
		}
		
		return false;
	}
	
	
}
