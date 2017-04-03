package com.bots.kingsroad;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
	
	public boolean createDesktopScreenshot(String format, String outputFile){
		
    	try{
            Robot robot = new Robot();

            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(outputFile));
             
            System.out.println("A full screenshot saved! "+outputFile);
            
            return true;
        
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
            
            return false;
        }
		
	}
}
