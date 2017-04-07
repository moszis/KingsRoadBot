package com.bots.kingsroad;

import java.util.concurrent.TimeUnit;

import com.bots.kingsroad.dto.Area;
import com.bots.kingsroad.event.EventFlowManager;

public class Runner extends Thread{
	
	private volatile boolean runBot = true;

	String templateFolder = System.getProperty("templateFolder");
	
	String questTemplate      = templateFolder+"MapMissionCalmwaterCrossing.png";
	String difficultyTemplate = templateFolder+"PlayChampionButton.png";
    String format = "jpg";
    String scanAreaFile = "Desktop." + format;

    int secondsBetweenCycles = 2;
    
    boolean testMode = false;
    
    
	OutputManager outputManager           = new OutputManager();
    EventFlowManager eventFlowManager     = new EventFlowManager(questTemplate, difficultyTemplate);
    
	public void run(){
		
		System.out.print("Running....");

		if(testMode){
			runTest();
		}else{
		    while(runBot){
		    	System.out.print(".");
	
		    	outputManager.createDesktopScreenshot(format, scanAreaFile);
	            
		    	eventFlowManager.processNextEvent();
		
	            pause();
		    }
		}
	}
	
	public void runTest(){

		outputManager.createAllScreensScreenshot(format, scanAreaFile);
		PatternRecognition pr = new PatternRecognition();
		String testTemplate = System.getProperty("templateFolder")+System.getProperty("testTemplate");
		System.out.println(testTemplate);
		
		Area area = pr.getMatchArea(testTemplate);
		pr.generateMatchOutputFile(testTemplate);
		
		outputManager.clickTargetArea(area);
	}
	
	
	//Figure out how to take global input to control the bot; or write some jframe gimik
    public void stopRunning(){
    	runBot = false;
    }
    
    private void pause(){
    	try {
			TimeUnit.SECONDS.sleep(secondsBetweenCycles);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
    }

}
