package com.bots.kingsroad;

import java.util.concurrent.TimeUnit;

import com.bots.kingsroad.dto.Area;
import com.bots.kingsroad.event.EventFlowManager;

public class Runner extends Thread{
	
	private volatile boolean runBot = true;

	String templateFolder = System.getProperty("templateFolder");
	String missionFolder = templateFolder+System.getProperty("missionSubFolder");
	
	String questTemplate      = missionFolder+"MapMission11Godwood.png";
	String difficultyTemplate = templateFolder+"PlayChampionButton.png";
    String format = "jpg";
    String scanAreaFile = "Desktop." + format;

    int secondsBetweenCycles = 2;
    
    boolean testMode = false;
    boolean useFood  = false;
    
    
	OutputManager outputManager           = new OutputManager();
    EventFlowManager eventFlowManager     = new EventFlowManager(questTemplate, difficultyTemplate, useFood);
    
	public void run(){
		
		//codes:
		// 1 - mission complete (Return to town)
		// 2 - revived
		// 3 - food used
		// 4 - auto activated
		// 5 - disconnected
		// 6 - restarted

		if(testMode){
			runTest();
		}else{
			int cycle = 1;
			int missions = 0;
			int revived = 0;
			int food    = 0;
			int auto = 0;
			int event = 0;
			int disconnected = 0;
			int restarted = 0;
			
			
			  long startTime = System.nanoTime();
		      long stopTime = 0;
		      long elapsedTime = 0;
		      long timePerMission = 0;

			
		    while(runBot){
		    	
		    	System.out.println("running cycle "+cycle);
	
		    	outputManager.createDesktopScreenshot(format, scanAreaFile);
	            
		    	event = eventFlowManager.processNextEvent();
		    	

		
	            pause();
	            
	            
	            cycle++;
	            
		    	switch (event){
			    	case 1: missions++;
			    	case 2: revived++;
			    	case 3: food++;
			    	case 4: auto++;
			    	case 5: disconnected++;
			    	case 6: restarted++;
		    	}
		    	
		    	System.out.println("missions complete: "+missions);
		    	System.out.println("revived: "+revived);
		    	System.out.println("Food Used: "+food);
		    	System.out.println("Auto activations: "+auto);
		    	System.out.println("Disconnected: "+disconnected);
		    	System.out.println("Restarted: "+restarted);
		    	
		        stopTime = System.nanoTime();
		        elapsedTime = stopTime - startTime;
		        System.out.println("Total execution time: " +
		                String.format("%d min, %d sec",
		                        TimeUnit.NANOSECONDS.toMinutes(elapsedTime),
		                        TimeUnit.NANOSECONDS.toSeconds(elapsedTime) -
		                                TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(elapsedTime))));
		        if(missions > 0 ){
		        	timePerMission = elapsedTime/missions;
			        System.out.println("Time Per mission: " +
			                String.format("%d min, %d sec",
			                        TimeUnit.NANOSECONDS.toMinutes(timePerMission),
			                        TimeUnit.NANOSECONDS.toSeconds(timePerMission) -
			                                TimeUnit.MINUTES.toSeconds(TimeUnit.NANOSECONDS.toMinutes(timePerMission))));
	
		        }
		    
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
