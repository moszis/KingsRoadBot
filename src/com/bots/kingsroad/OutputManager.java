package com.bots.kingsroad;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
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

	boolean defaultClickCenter = true;
	int defaultRanCenterOffset = 0;
	int defaultNumClicks = 1;
	
	public boolean clickTargetArea(Area area){
		return clickTargetArea(area, defaultClickCenter, defaultRanCenterOffset, defaultNumClicks);
	}
	
	
	public boolean clickTargetArea(Area area, boolean clickCenter, int ranCenterOffset, int numClicks){
		
		//TODO: write random logic; for now just click center
		
    	try{
            Robot bot = new Robot();
            
            //Robot bot = new Robot(MouseInfo.getPointerInfo().getDevice());
            
            bot.mouseMove((int)area.centerX, (int)area.centerY);    
           
            for(int x = 0; x < numClicks; x++){
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
	
	public boolean pressKey(int keyCode){
		
		//TODO: write random logic; for now just click center
		
    	try{
            Robot bot = new Robot();
            bot.keyPress(keyCode);
            bot.keyRelease(keyCode); 

            return true;
    	}catch (Exception e){
    		System.err.println(e);
    		
    		return false;
    	}
    	
		
	}
	
	
	
	public boolean createDesktopScreenshot(String format, String outputFile){
		
    	try{
    		Robot bot = new Robot();

            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = bot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(outputFile));

            return true;
        
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
            
            return false;
        }
		
	}
	
	public boolean createAllScreensScreenshot(String format, String outputFile){
		try{
		  GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		  GraphicsDevice[] screens = ge.getScreenDevices();
		  
		  Rectangle allScreenBounds = new Rectangle();
		  for (GraphicsDevice screen : screens) {
		   Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();
		   
		   allScreenBounds.width += screenBounds.width;
		   allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);      
		  }
		  Robot robot = new Robot();
		  BufferedImage screenShot = robot.createScreenCapture(allScreenBounds);
		  
          ImageIO.write(screenShot, format, new File(outputFile));
          
		}catch(Exception e){
			return false;
		}
		
		  return true;
	}
}
