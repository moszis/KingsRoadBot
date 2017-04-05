package com.bots.kingsroad;

import java.util.concurrent.TimeUnit;
import com.bots.kingsroad.event.EventFlowManager;

public class Runner extends Thread{
	
	private volatile boolean runBot = true;

	String questTemplate      = "MapMissionHuntingTrail.png";
	String difficultyTemplate = "PlayChampionButton.png";
    String format = "jpg";
    String scanAreaFile = "Desktop." + format;

    int secondsBetweenCycles = 2;
    
	OutputManager outputManager           = new OutputManager();
    EventFlowManager eventFlowManager     = new EventFlowManager(questTemplate, difficultyTemplate);
    
	public void run(){
		
		System.out.print("Running....");

	    while(runBot){
	    	System.out.print(".");

	    	outputManager.createDesktopScreenshot(format, scanAreaFile);
            
	    	eventFlowManager.processNextEvent();
	
            pause();
	    }

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
