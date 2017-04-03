package com.bots.kingsroad;

import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Runner extends Thread{
	
	private volatile boolean runBot = true;
	
	PatternRecognition patternRecognition = new PatternRecognition();
	
	public void run(){
		
		System.out.print("Running....");

	    while(runBot){
	    	System.out.print(".");
	    	
	    	//Step 1 get new screenshot
	    	
            String format = "jpg";
            String scanArea = "Desktop." + format;
            
	    	try{
	            Robot robot = new Robot();

	            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
	            ImageIO.write(screenFullImage, format, new File(scanArea));
	             
	            System.out.println("A full screenshot saved! "+scanArea);
            
	        } catch (AWTException | IOException ex) {
	            System.err.println(ex);
	        }
            
	    	//Step 2 Find the location
	    	Point matchLoc = new Point();
	    	try{
	    		
	    		matchLoc = patternRecognition.getMatchLocation(scanArea, "templateFile.png", "result.jpg", Imgproc.TM_CCOEFF);
	    	
	    		System.out.println("## Location Found: "+matchLoc.x+ " : "+matchLoc.y);
	    		
	        } catch (AWTException ex) {
	            System.err.println(ex);
	        }
            
	    	
	    	//Step 3 Press the location
	    	try{
	            Robot bot = new Robot();
	            bot.mouseMove((int)matchLoc.x, (int)matchLoc.y);           
	            bot.mousePress(InputEvent.BUTTON1_MASK);
	            bot.mouseRelease(InputEvent.BUTTON1_MASK);
	    	}catch (Exception e){
	    		System.err.println(e);
	    	}
	    	
	    	try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException ie) {
				// TODO Auto-generated catch block
				ie.printStackTrace();
			}

	    }

	}
	
    public void stopRunning(){
    	runBot = false;
    }

}
