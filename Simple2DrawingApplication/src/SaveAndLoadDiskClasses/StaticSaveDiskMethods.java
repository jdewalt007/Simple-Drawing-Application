/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SaveAndLoadDiskClasses;


import ExtendedObjectClasses.DrawingPane;
import ExtendedObjectClasses.CircleExt;
import ExtendedObjectClasses.RectangleExt;

import SerialObjectClasses.CircleData;
import SerialObjectClasses.DrawingPaneData;
import SerialObjectClasses.RectangleData;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Jeremy DeWalt
 */
public abstract class StaticSaveDiskMethods {
    
    private static ObjectOutputStream output;
    
    
    
    public static void saveDiskFile(File saveFile)  {
        
        try {
           
            FileOutputStream fileOut = new FileOutputStream(saveFile.getAbsolutePath());
            output = new ObjectOutputStream(fileOut);
            
        } catch(IOException ex)  {
                      
            System.err.println("Error opening file.");
            System.exit(1);          
        }  
    }
    
    
    public static void addDrawingObjects(DrawingPane dp)  {
        
        
        try {
            DrawingPaneData dp_data = new DrawingPaneData(dp.getDrawingName(), dp.getWidth(), dp.getHeight());
            output.writeObject(dp_data);
            
            
            if (!dp.getChildren().isEmpty())  {
                
                for (Object o : dp.getChildren()) {  
                    
                    if (o instanceof CircleExt) {
                    
                        CircleExt c = (CircleExt) o;
                        CircleData circleData = new CircleData(c.getCenterX(), c.getCenterY(), c.getRadius(),c.getColor());
                        output.writeObject(circleData);
                    }
                    else {
                    
                        if (o instanceof RectangleExt) {
                    
                            RectangleExt r = (RectangleExt) o;
                            RectangleData rectangleData = new RectangleData(r.getX(), r.getY(), r.getWidth(),r.getHeight(),r.getColor());
                            output.writeObject(rectangleData);   
                        }
                    }
                }
            }
            
        } catch (IOException ex)   {
            
            System.err.println("Error writing to file. Terminating.");
            System.exit(1);
        }       
    }  
    
    
    public static void closeFile()  {
   
      try  {
      
         if (output != null)
            output.close();
         
      } catch(IOException ioException)  {
      
          System.err.println("Error closing file. Terminating.");
      }
    }
            
}
