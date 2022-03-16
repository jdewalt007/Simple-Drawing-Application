/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtendedObjectClasses;

import javafx.scene.layout.Pane;

/**
 *
 * @author Jeremy DeWalt
 */
public class DrawingPane extends Pane  {
    
    private String drawingName;
    
    
    public  DrawingPane(String name, double width, double height)  {
            
        
        drawingName = name;
        super.setPrefWidth(width);
        super.setPrefHeight(height);
                          
    }

    /**
     * @return the drawingName
     */
    public String getDrawingName() {
        return drawingName;
    }

    /**
     * @param drawingName the drawingName to set
     */
    public void setDrawingName(String drawingName) {
        this.drawingName = drawingName;
    }

    
    
   
    
}
