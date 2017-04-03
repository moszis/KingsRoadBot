package com.bots.kingsroad;

import java.awt.Robot;
import java.awt.event.InputEvent;

import com.bots.kingsroad.dto.Area;

public class OutputManager {

	public boolean clickTargetArea(Area area, boolean clickCenter, int ranCenterOffset, int numClicks){
		
		//TODO: write random logic; for now just click center
		
    	try{
            Robot bot = new Robot();
            bot.mouseMove((int)area.centerX, (int)area.centerY);    
            
            for(int x = 0; x < numClicks; x++){
            	System.out.println("CLICKC!!");
	            bot.mousePress(InputEvent.BUTTON1_MASK);
	            bot.mouseRelease(InputEvent.BUTTON1_MASK);
	            Thread.sleep(50);
            }
            return true;
    	}catch (Exception e){
    		System.err.println(e);
    		
    		return false;
    	}
    	
		
	}
}
