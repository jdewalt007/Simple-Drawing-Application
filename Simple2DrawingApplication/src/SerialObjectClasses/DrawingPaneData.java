/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SerialObjectClasses;

import java.io.Serializable;

/**
 *
 * @author itmipro
 */
public class DrawingPaneData implements Serializable
{
    private String drawingName;
    private double width;
    private double height;
    
    // Constructor
    public DrawingPaneData(String dName, double width, double height)
    {
        this.drawingName = dName;
        this.width = width;
        this.height = height;
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

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

} // end class DrawingPaneData
