/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtendedObjectClasses;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jeremy DeWalt
 */
public class RectangleExt extends Rectangle  {
    
    private boolean rectangleSelected = false;
    private String color;
    
    public RectangleExt(double x_coord, double y_coord, double width, double height, String color)  {
        super(x_coord, y_coord, width, height);
         
        super.setFill(caseShapeColor(color));  
        this.color = color;
              
    }
    
    

    /**
     * @return the rectangleSelected
     */
    public boolean isRectangleSelected() {
        return rectangleSelected;
    }

    /**
     * @param rectangleSelected the rectangleSelected to set
     */
    public void setRectangleSelected(boolean rectangleSelected) {
        this.rectangleSelected = rectangleSelected;
    }
    
    private  Color caseShapeColor(String color) {

            Color fillColor = Color.BLUE;
            
            switch(color) {

                case "RED" : {
                                 fillColor = Color.RED;
                                 break;    
                             }

               case "BLUE" : {
                                 fillColor = Color.BLUE;
                                 break;
                             } 

               case "GREEN" : {
                                 fillColor = Color.GREEN;
                                 break;             
                             }

               case "YELLOW" : {
                                 fillColor = Color.YELLOW;
                                 break;             
                             }

               case "GREY" : {
                                 fillColor = Color.GREY;
                                 break;             
                             }          
            }      
            return fillColor;    
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
        super.setFill(caseShapeColor(color)); 
    }
}
