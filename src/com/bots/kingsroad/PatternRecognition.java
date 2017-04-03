package com.bots.kingsroad;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.bots.kingsroad.dto.Area;

import java.awt.AWTException;



class PatternRecognition {
	
    public Area getMatchLocation(String scanFile, String templateFile, String outFile,
            int match_method) throws AWTException {
    	
    	
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	
    	
        System.out.println("\nRunning Template Matching");

        
        Mat img   = Imgcodecs.imread(scanFile);    
        Mat templ = Imgcodecs.imread(templateFile);

        // / Create the result matrix
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

        // / Do the Matching and Normalize
        Imgproc.matchTemplate(img, templ, result, match_method);
        
        
        //Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        
        // / Localizing the best match with minMaxLoc
        MinMaxLocResult mmr = Core.minMaxLoc(result);

        Point matchLoc;
        if (match_method == Imgproc.TM_SQDIFF
                || match_method == Imgproc.TM_SQDIFF_NORMED) {
            matchLoc = mmr.minLoc;
        } else {
            matchLoc = mmr.maxLoc;
        }

        System.out.println("matching location x: "+matchLoc.x);
        System.out.println("matching location y: "+matchLoc.y);
        System.out.println("width: "+templ.cols());
        System.out.println("height: "+templ.rows());
        
        
        Area area = new Area();
        area.x = matchLoc.x;
        area.y = matchLoc.y;
        area.width = templ.cols();
        area.height = templ.rows();
        area.generateCenterX();
        area.generateCenterY();
      
        
        // / Show me what you got
        Imgproc.rectangle(img, matchLoc, new Point(matchLoc.x + templ.cols(),
                matchLoc.y + templ.rows()), new Scalar(0, 255, 0));

        matchLoc.x = matchLoc.x + templ.cols()/2 - 10;
        matchLoc.y = matchLoc.y + templ.rows()/2 - 10;
        
        Imgproc.rectangle(img, matchLoc, new Point(matchLoc.x + 20,
                matchLoc.y + 20), new Scalar(0, 255, 0));
        
        // Save the visualized detection.
        System.out.println("Writing " + outFile);
        Imgcodecs.imwrite(outFile, img);
        
        return area;
    }
}

