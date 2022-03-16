/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SerialObjectClasses;

import java.io.Serializable;

/**
 * CSC600 Advanced Programming in Java
 * Project - Draw 2D Shapes Application
 * @author itmipro
 */
public class CircleData implements Serializable 
{
    private double xPos;
    private double yPos;
    private double radius;
    private String color;
    
    // Constructor
    public CircleData(double xPos, double yPos, double radius, String color)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.color = color;
    }

    /**
     * @return the xPos
     */
    public double getXPos() {
        return xPos;
    }

    /**
     * @param xPos the xPos to set
     */
    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    /**
     * @return the yPos
     */
    public double getYPos() {
        return yPos;
    }

    /**
     * @param yPos the yPos to set
     */
    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

} // end class CircleData
