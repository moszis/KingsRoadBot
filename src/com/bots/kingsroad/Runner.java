package com.bots.kingsroad;

import java.util.concurrent.TimeUnit;

import org.opencv.imgproc.Imgproc;

import com.bots.kingsroad.dto.Area;

import java.awt.AWTException;

public class Runner extends Thread{
	
	private volatile boolean runBot = true;
	
	PatternRecognition patternRecognition = new PatternRecognition();
	OutputManager outputManager           = new OutputManager();
	
	String mapGuyTemplate   = "";
	String zoneTemplate     = "";
	String endLevelTemplate = "";
    String format = "jpg";
    String scanAreaFile = "Desktop." + format;
    
    int secondsBetweenCycles = 2;
    
	public void run(){
		
		System.out.print("Running....");

	    while(runBot){
	    	System.out.print(".");
	    	
	    	//Step 1 get new screenshot

	    	outputManager.createDesktopScreenshot(format, scanAreaFile);
            
	    	//Step 2 Find the location
	    	//TODO: need ability to find best matching event
	    	Area area = new Area();
	    	try{
	    		//TM_SQDIFF
	    		//TM_CCOEFF
	    		area = patternRecognition.getMatchLocation(scanAreaFile, "templateFile.png", "result.jpg", Imgproc.TM_CCOEFF_NORMED);
	    	
	    		System.out.println("## Location Found: "+area.x+ " : "+area.y);
	    		
	        } catch (AWTException ex) {
	            System.err.println(ex);
	        }
            
	    	
	    	//Step 3 Press the location
            outputManager.clickTargetArea(area, true, 0, 0);
            
            //Step 4 sleep
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
