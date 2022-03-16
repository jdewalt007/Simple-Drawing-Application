/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DialogBoxClass;

import ExtendedObjectClasses.CircleExt;
import ExtendedObjectClasses.RectangleExt;
import SerialObjectClasses.CircleData;
import SerialObjectClasses.DrawingPaneData;
import SerialObjectClasses.RectangleData;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author Jeremy DeWalt
 */
public class StaticDialogBoxMethods {
    
    public static DrawingPaneData newDrawingPaneDataDialog()
    {
        Dialog<DrawingPaneData> newDPdlg = new Dialog<DrawingPaneData>();
        newDPdlg.setTitle("New Drawing Pane");
        newDPdlg.setHeaderText("Enter drawing pane information");
        newDPdlg.setResizable(false);
        
        Label lblWidth = new Label("Width: ");
        Label lblHeight = new Label("Height: ");
        Label lblName = new Label("Drawing Pane Name: ");
        
        TextField textWidth = new TextField();
        TextField textHeight = new TextField();
        TextField textName = new TextField();
        
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        
        GridPane grdNewDrawingPane = new GridPane();
        grdNewDrawingPane.addRow(0, lblWidth, textWidth);
        grdNewDrawingPane.addRow(1, lblHeight, textHeight);
        grdNewDrawingPane.addRow(2, lblName, textName);
        grdNewDrawingPane.setAlignment(Pos.CENTER);
        grdNewDrawingPane.setVgap(5);
        grdNewDrawingPane.setHgap(1);
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5,5,5,5));
        vbox.getChildren().addAll(grdNewDrawingPane, separator);
        
        newDPdlg.getDialogPane().setContent(vbox);
        newDPdlg.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        newDPdlg.setResultConverter(new Callback<ButtonType, DrawingPaneData>()
        {
            @Override
            public DrawingPaneData call(ButtonType param)
            {
                if (param != ButtonType.OK)
                    return null;
                
                // public DrawingPaneData(String dName, double width, double height)
                DrawingPaneData newDrawingPane = new DrawingPaneData(
                        textName.getText(),
                        Double.parseDouble(textWidth.getText()),
                        Double.parseDouble(textHeight.getText()));
                
                return newDrawingPane;
            }
        });
        
        Optional<DrawingPaneData> result = newDPdlg.showAndWait();
        
        if (!result.isPresent())
            return null;
        
        return result.get();
    }

    public static  CircleData newCircleDataDialog()
    {
        Dialog<CircleData> newCdlg = new Dialog<CircleData>();
        newCdlg.setTitle("New Circle");
        newCdlg.setHeaderText("Enter circle information");
        newCdlg.setResizable(false);
        
        Label lblXpos = new Label("X-Position: ");
        Label lblYpos = new Label("Y-Position: ");
        Label lblRadius = new Label("Radius: ");
        Label lblColor = new Label("Fill-Color: ");
        
        TextField textXpos = new TextField();
        TextField textYpos = new TextField();
        TextField textRadius = new TextField();
        
        ComboBox <String> cboxColor = new ComboBox<>();
        cboxColor.getItems().addAll("RED","BLUE","GREEN","YELLOW","GREY");
        cboxColor.setPromptText("BLUE");
        cboxColor.setValue("BLUE");
        
        
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        
        
        GridPane grdNewCircle = new GridPane();
        grdNewCircle.addRow(0, lblXpos, textXpos);
        grdNewCircle.addRow(1, lblYpos, textYpos);
        grdNewCircle.addRow(2, lblRadius, textRadius);
        grdNewCircle.addRow(3, lblColor, cboxColor);
        grdNewCircle.setAlignment(Pos.CENTER);
        grdNewCircle.setVgap(5);
        grdNewCircle.setHgap(1);
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5,5,5,5));
        vbox.getChildren().addAll(grdNewCircle, separator);
        
        newCdlg.getDialogPane().setContent(vbox);
        newCdlg.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        newCdlg.setResultConverter(new Callback<ButtonType, CircleData>()
        {
            @Override
            public CircleData call(ButtonType param)
            {
                if (param != ButtonType.OK)
                    return null;
                
                // public CircleData(double xPos, double yPos, double radius, 
                //    String color)
                CircleData newCircleRecord = new CircleData(
                        Double.parseDouble(textXpos.getText()),
                        Double.parseDouble(textYpos.getText()),
                        Double.parseDouble(textRadius.getText()),
                        cboxColor.getValue());   
                
                return newCircleRecord;
            }
        });
        
        Optional<CircleData> result = newCdlg.showAndWait();
        
        if (!result.isPresent())
            return null;
        
        return result.get();
    }

    
    
    public static CircleExt modifyCircleExtDialog(CircleExt c) {
        
        
        
        Dialog<CircleExt> dlg = new Dialog<CircleExt>();
        dlg.setTitle("Modify Selected Circle");
        dlg.setHeaderText("Update information of the selected circle");
        dlg.setResizable(false);
        
        Label lblXpos = new Label("X-Position: ");
        Label lblYpos = new Label("Y-Position: ");
        Label lblRadius = new Label("Radius: ");
        Label lblColor = new Label("Fill-Color: ");
        
        TextField textXpos = new TextField();
        textXpos.setText(String.valueOf(c.getCenterX()));

        TextField textYpos = new TextField();
        textYpos.setText(String.valueOf(c.getCenterY()));

        TextField textRadius = new TextField();
        textRadius.setText(String.valueOf(c.getRadius()));

        ComboBox <String> cboxColor = new ComboBox <> ();
        cboxColor.getItems().addAll("RED","BLUE","GREEN","YELLOW","GREY");
        cboxColor.setPromptText(c.getColor());
        cboxColor.setValue(c.getColor());
        
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        
        GridPane grdModCircle = new GridPane();
        grdModCircle.addRow(0, lblXpos, textXpos);
        grdModCircle.addRow(1, lblYpos, textYpos);
        grdModCircle.addRow(2, lblRadius, textRadius);
        grdModCircle.addRow(3, lblColor, cboxColor);
        grdModCircle.setAlignment(Pos.CENTER);
        grdModCircle.setVgap(5);
        grdModCircle.setHgap(1);
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5,5,5,5));
        vbox.getChildren().addAll(grdModCircle, separator);
        
        dlg.getDialogPane().setContent(vbox);
        dlg.getDialogPane().getButtonTypes().addAll(ButtonType.OK, 
                ButtonType.CANCEL);
        
        
        dlg.setResultConverter(new Callback<ButtonType, CircleExt>()
        {
            @Override
            public CircleExt call(ButtonType param)
            {
                if (param != ButtonType.OK)
                    return null;
                
                
                               
                c.setCenterX(Double.parseDouble(textXpos.getText()));
                c.setCenterY(Double.parseDouble(textYpos.getText()));
                c.setRadius(Double.parseDouble(textRadius.getText()));
                c.setColor(cboxColor.getValue());
       
                return c;  // the modified CircleExt object in the dp DrawingPane
            }
        });
        
        Optional<CircleExt> result = dlg.showAndWait();
        
        if (!result.isPresent())
            return null;
        
        return result.get();
    } // end method public static  CircleExt ModifyCircleExtDialog(CircleExt c)
    
    
    public static RectangleData newRectangleDataDialog()
    {
        Dialog<RectangleData> newRdlg = new Dialog<RectangleData>();
        newRdlg.setTitle("New Rectangle");
        newRdlg.setHeaderText("Enter rectangle information");
        newRdlg.setResizable(false);
        
        Label lblXpos = new Label("X-Position: ");
        Label lblYpos = new Label("Y-Position: ");
        Label lblWidth = new Label ("Width: ");
        Label lblHeight = new Label ("Height: ");
        Label lblColor = new Label("Fill-Color: ");
        
        TextField textXpos = new TextField();
        TextField textYpos = new TextField();
        TextField textWidth = new TextField();
        TextField textHeight = new TextField();
     
        ComboBox <String> cboxColor = new ComboBox<>();
        cboxColor.getItems().addAll("RED","BLUE","GREEN","YELLOW","GREY");
        cboxColor.setPromptText("BLUE");
        cboxColor.setValue("BLUE");
        
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        
        GridPane grdNewRectangle = new GridPane();
        grdNewRectangle.addRow(0, lblXpos, textXpos);
        grdNewRectangle.addRow(1, lblYpos, textYpos);
        grdNewRectangle.addRow(2, lblWidth, textWidth);
        grdNewRectangle.addRow(3, lblHeight, textHeight);
        grdNewRectangle.addRow(4, lblColor, cboxColor);
        grdNewRectangle.setAlignment(Pos.CENTER);
        grdNewRectangle.setVgap(5);
        grdNewRectangle.setHgap(1);
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5,5,5,5));
        vbox.getChildren().addAll(grdNewRectangle, separator);
        
        newRdlg.getDialogPane().setContent(vbox);
        newRdlg.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        newRdlg.setResultConverter(new Callback<ButtonType, RectangleData>()
        {
            @Override
            public RectangleData call(ButtonType param)
            {
                if (param != ButtonType.OK)
                    return null;
                
                // public RectangleData(double xPos, double yPos, double width, 
                //    double height, String color)
                RectangleData newRectangleRecord = new RectangleData(
                        Double.parseDouble(textXpos.getText()),
                        Double.parseDouble(textYpos.getText()),
                        Double.parseDouble(textWidth.getText()),
                        Double.parseDouble(textHeight.getText()),
                        cboxColor.getValue());
                
                return newRectangleRecord;
            }
        });
        
        Optional<RectangleData> result = newRdlg.showAndWait();
        
        if (!result.isPresent())
            return null;
        
        return result.get();
    }
    
    
    
    public static RectangleExt modifyRectangleExtDialog(RectangleExt r) {
        
        Dialog<RectangleExt> dlg = new Dialog<RectangleExt>();
        dlg.setTitle("Modify Selected Rectangle");
        dlg.setHeaderText("Update information of the selected rectangle");
        dlg.setResizable(false);
        
        Label lblXpos = new Label("X-Position: ");
        Label lblYpos = new Label("Y-Position: ");
        Label lblWidth = new Label ("Width: ");
        Label lblHeight = new Label ("Height: ");
        Label lblColor = new Label("Fill-Color: ");
        
        TextField textXpos = new TextField();
        textXpos.setText(String.valueOf(r.getX()));

        TextField textYpos = new TextField();
        textYpos.setText(String.valueOf(r.getY()));

        TextField textWidth = new TextField();
        textWidth.setText(String.valueOf(r.getWidth()));

        TextField textHeight = new TextField();
        textHeight.setText(String.valueOf(r.getHeight()));

        ComboBox <String> cboxColor = new ComboBox <> ();
        cboxColor.getItems().addAll("RED","BLUE","GREEN","YELLOW","GREY");
        cboxColor.setPromptText(r.getColor());
        cboxColor.setValue(r.getColor());
        
        
        Separator separator = new Separator();
        separator.setOrientation(Orientation.HORIZONTAL);
        
        GridPane grdNewRectangle = new GridPane();
        grdNewRectangle.addRow(0, lblXpos, textXpos);
        grdNewRectangle.addRow(1, lblYpos, textYpos);
        grdNewRectangle.addRow(2, lblWidth, textWidth);
        grdNewRectangle.addRow(3, lblHeight, textHeight);
        grdNewRectangle.addRow(4, lblColor, cboxColor);
        grdNewRectangle.setAlignment(Pos.CENTER);
        grdNewRectangle.setVgap(5);
        grdNewRectangle.setHgap(1);
        
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(5,5,5,5));
        vbox.getChildren().addAll(grdNewRectangle, separator);
        
        dlg.getDialogPane().setContent(vbox);
        dlg.getDialogPane().getButtonTypes().addAll(ButtonType.OK, 
                ButtonType.CANCEL);
        
        dlg.setResultConverter(new Callback<ButtonType, RectangleExt>()
        {
            @Override
            public RectangleExt call(ButtonType param)
            {
                if (param != ButtonType.OK)
                    return null;
               
                
                
                r.setX(Double.parseDouble(textXpos.getText()));
                r.setY(Double.parseDouble(textYpos.getText()));
                r.setWidth(Double.parseDouble(textWidth.getText()));
                r.setHeight(Double.parseDouble(textHeight.getText()));
                r.setColor(cboxColor.getValue());
                
                
                return r;  // the modified RectangleExt object in the dp DrawingPane
            }
        });
        
        Optional<RectangleExt> result = dlg.showAndWait();
        
        if (!result.isPresent())
            return null;
        
        return result.get();
    } // end method public static RectangleExt modRectangleExtDialog(RectangleExt r)
           
}
