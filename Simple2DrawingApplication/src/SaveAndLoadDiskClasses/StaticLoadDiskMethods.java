/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SaveAndLoadDiskClasses;

import ExtendedObjectClasses.CircleExt;
import ExtendedObjectClasses.DrawingPane;
import ExtendedObjectClasses.RectangleExt;
import SerialObjectClasses.CircleData;
import SerialObjectClasses.DrawingPaneData;
import SerialObjectClasses.RectangleData;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


/**
 *
 * @author Jeremy DeWalt
 */
public class StaticLoadDiskMethods {
    
    private static ObjectInputStream input;
    
    
    public static void loadDiskFile(File loadFile)
   {
      try
      {
          
         FileInputStream fileIn = new FileInputStream(loadFile.getAbsolutePath());
         input = new ObjectInputStream(fileIn);
      }
      catch(IOException ioException)
      {
         System.err.println("Error opening file.");
         System.exit(1);
      }
   }
    
    
    public static void retrieveDrawingObjects(DrawingPane dp) {
        
        
        try {
            
            DrawingPaneData dp_data = (DrawingPaneData) input.readObject();
            dp.getChildren().clear();
            dp.setDrawingName(dp_data.getDrawingName());
            dp.setPrefSize(dp_data.getWidth(), dp_data.getHeight());
            
            while(true) {
            
                Object o = input.readObject();

                if (o instanceof CircleData) {
                
                    CircleData circleData = (CircleData) o;
                    CircleExt circleExt = new CircleExt(circleData.getXPos(), circleData.getYPos(), 
                                                        circleData.getRadius(), circleData.getColor());
                    
                    dp.getChildren().add(circleExt);    
                    
                }
                else {
                
                    RectangleData rectangleData = (RectangleData) o;
                    RectangleExt rectangleExt = new RectangleExt(rectangleData.getXPos(), rectangleData.getYPos(),
                                                                  rectangleData.getWidth(), rectangleData.getHeight(),
                                                                  rectangleData.getColor());
                    
                    dp.getChildren().add(rectangleExt);
                
                }           
            }                  
                  
        } catch(EOFException endofFileException)   {
            
            System.out.println("No more Drawing Shapes");
            
        }  catch(ClassNotFoundException classNotFoundException)   {
            
            System.err.println("Invalid object type. Terminating.");
            
        }  catch(IOException ioException)   {
            
            System.err.println("Error reading from file. Terminating.");
        }    
    }
    
    
    public static void closeDiskFile()   {
        
      try   {
          
         if (input != null)
            input.close();
      }  catch(IOException ioException)   {
          
            System.err.println("Error closing file. Terminating.");
            System.exit(1);
      }
   }    
}
