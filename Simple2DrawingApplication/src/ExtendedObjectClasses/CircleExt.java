
package ExtendedObjectClasses;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Jeremy DeWalt
 */
public class CircleExt extends Circle {
    
    private boolean circleSelected = false;
    private String color;
    
    
    public CircleExt(double x, double y, double radius, String color) {
        super(x,y,radius);
        super.setFill(caseShapeColor(color));
        this.color = color;
        
                 
    }
    

    // returns if the circle is selected or not
    
    public boolean isCircleSelected() {
        return circleSelected;
    }

    // to set the circle as selected once clicked on
    
    public void setCircleSelected(boolean circleSelected) {
        this.circleSelected = circleSelected;
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
