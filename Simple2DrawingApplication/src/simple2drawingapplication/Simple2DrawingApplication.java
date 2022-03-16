/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple2drawingapplication;

import ExtendedObjectClasses.DrawingPane;
import ExtendedObjectClasses.CircleExt;
import ExtendedObjectClasses.RectangleExt;

import SerialObjectClasses.CircleData;
import SerialObjectClasses.DrawingPaneData;
import SerialObjectClasses.RectangleData;

import static DialogBoxClass.StaticDialogBoxMethods.newDrawingPaneDataDialog;
import static DialogBoxClass.StaticDialogBoxMethods.newCircleDataDialog;
import static DialogBoxClass.StaticDialogBoxMethods.newRectangleDataDialog;
import static DialogBoxClass.StaticDialogBoxMethods.modifyCircleExtDialog;
import static DialogBoxClass.StaticDialogBoxMethods.modifyRectangleExtDialog;


import Drawing.JavaFX.Demos.Configuration.ConfigurationUtility;
import Drawing.JavaFX.Demos.Configuration.DataSource;



import static SaveAndLoadDiskClasses.StaticSaveDiskMethods.saveDiskFile;
import static SaveAndLoadDiskClasses.StaticSaveDiskMethods.addDrawingObjects;
import static SaveAndLoadDiskClasses.StaticSaveDiskMethods.closeFile;
import static SaveAndLoadDiskClasses.StaticLoadDiskMethods.loadDiskFile;
import static SaveAndLoadDiskClasses.StaticLoadDiskMethods.retrieveDrawingObjects;
import static SaveAndLoadDiskClasses.StaticLoadDiskMethods.closeDiskFile;



import java.io.File;
import java.util.Optional;




import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 *
 * @author Jeremy DeWalt
 */
public class Simple2DrawingApplication extends Application {
    
    
    private DrawingPane dp;
    private Shape currentShapeSelected = null;
    private boolean pendingChange = false;
    final private FileChooser fileChooser = new FileChooser();
    
    
    
    @Override
    public void start(Stage primaryStage) {
        
        
        DataSource ds = ConfigurationUtility.getDataSource();
        
        
        
        
        BorderPane root = new BorderPane();
        root.setPrefHeight(600);
        root.setPrefWidth(500);
        
        
        
        
        MenuBar menuBar = new MenuBar();
        
        Menu menuFile = new Menu("File");
        MenuItem menuItemDrawPane = new MenuItem("New Drawing Pane...");
        MenuItem menuItemSaveToDisk = new MenuItem("Save Drawing to Disk..."); 
        MenuItem menuItemLoadFromDisk = new MenuItem("Load Drawing From Disk...");
        MenuItem menuItemSaveToDatabase = new MenuItem("Save Drawing to Database...");
        MenuItem menuItemLoadFromDatabase = new MenuItem("Load Drawing From Database...");
        MenuItem menuItemDeleteFromDatabase = new MenuItem("Delete Drawing From Database...");
        MenuItem menuItemExit = new MenuItem("Exit");
        menuFile.getItems().add(menuItemDrawPane);
        menuFile.getItems().add(new SeparatorMenuItem());
        menuFile.getItems().addAll(menuItemSaveToDisk, menuItemLoadFromDisk);
        menuFile.getItems().add(new SeparatorMenuItem());
        menuFile.getItems().addAll(menuItemSaveToDatabase, menuItemLoadFromDatabase, menuItemDeleteFromDatabase);
        menuFile.getItems().add(new SeparatorMenuItem());
        menuFile.getItems().add(menuItemExit);
             
        
        Menu menuEdit = new Menu("Edit");
        MenuItem menuItemDelete = new MenuItem("Delete");
        MenuItem menuItemModify = new MenuItem("Modify");
        menuEdit.getItems().addAll(menuItemDelete, menuItemModify);
        
       
        Menu menuDraw = new Menu("Draw");
        MenuItem menuItemCircle = new MenuItem("Circle");
        MenuItem menuItemRectangle = new MenuItem("Rectangle");
        menuDraw.getItems().addAll(menuItemCircle, menuItemRectangle);
        
  
        menuBar.getMenus().addAll(menuFile,menuEdit,menuDraw);
        
        
        root.setTop(menuBar);
        
        
        Label bpBottomLabel = new Label();
        root.setBottom(bpBottomLabel);
        
        ScrollPane scrollPane = new ScrollPane();
        
        scrollPane.setPrefHeight(347);
        scrollPane.setPrefWidth(300);
        root.setCenter(scrollPane);
        
        
        // default DrawingPane GUI for Application created here
        dp = new DrawingPane("no-name",500, 600);
     
        dp.setBorder(new Border(new BorderStroke(Color.BLACK, 
                                                   BorderStrokeStyle.SOLID, 
                                                   CornerRadii.EMPTY, 
                                                   BorderWidths.DEFAULT)));
               
        
         scrollPane.setContent(dp);  // add default DrawingPane inside ScrollPane
                   
        // listener for cursor movement inside default DrawingPane
        dp.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                bpBottomLabel.setText(String.format("[ DRAWING: <%s> (W: %.4f, H: %.4f) ]  X =  %.4f, Y = %.4f", dp.getDrawingName(),
                                      dp.getWidth(),dp.getHeight(), event.getX(), event.getY())); 
                bpBottomLabel.setTextAlignment(TextAlignment.LEFT);                      
            }                                                                                       
        });
        
        
        menuItemDrawPane.setOnAction(new EventHandler<ActionEvent> () {
            
            @Override
            public void handle(ActionEvent event) {
                
                if (!dp.getChildren().isEmpty()) {
                    
                    Alert alertDlg = new Alert(Alert.AlertType.CONFIRMATION);
                    alertDlg.setTitle("Confirm");
                    alertDlg.setHeaderText("Press OK button to confirm. Otherwise, press CANCEL.");
                    alertDlg.setContentText("There are objects in the drawing pane.\nAre you sure that "
                            + "you want to create a new drawing pane\nand discard these objects?");
                    alertDlg.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
                    Optional<ButtonType> result = alertDlg.showAndWait();
                    
                    if (result.get() == ButtonType.OK) {
                        

                        // load the new DrawingPane Dialog
                        DrawingPaneData dp_data = newDrawingPaneDataDialog();
                        dp.setDrawingName(dp_data.getDrawingName());
                        dp.setPrefHeight(dp_data.getHeight());
                        dp.setPrefWidth(dp_data.getWidth());
                        
                        // remove child rectangle and circle objects from previous drawing pane
                        dp.getChildren().clear();
                        currentShapeSelected = null;
                    }
                    else {
                        return;
                    }
                }
                else {
                        // load the new DrawingPane Dialog
                        DrawingPaneData dp_data = newDrawingPaneDataDialog();
                        dp.setDrawingName(dp_data.getDrawingName());
                        dp.setPrefHeight(dp_data.getHeight());
                        dp.setPrefWidth(dp_data.getWidth());
                        
                        // remove child rectangle and circle objects from previous drawing pane
                        dp.getChildren().clear();
                        currentShapeSelected = null;
                }
                
            } // end override method public void handle(ActionEvent event)
            
        }); // end listener menuItemDrawPane.setOnAction(new EventHandler<ActionEvent> ()
        
        
        
        menuItemCircle.setOnAction(new EventHandler<ActionEvent> () {
            
            @Override
            public void handle(ActionEvent event) {
                
                int initialSize = dp.getChildren().size();
                createNewPaneCircle(event);
                int returnSize = dp.getChildren().size();
                
                if (initialSize != returnSize)
                    pendingChange = true;
                
            }
        });
        
        menuItemRectangle.setOnAction(new EventHandler<ActionEvent> () {
            
            @Override
            public void handle(ActionEvent event) {
                
                int initialSize = dp.getChildren().size();
                createNewPaneRectangle(event);
                int returnSize = dp.getChildren().size();
                
                if (initialSize != returnSize)
                    pendingChange = true;
                
            }                                  
        });
        
        
        menuItemModify.setOnAction(new EventHandler<ActionEvent> () {
            
            @Override
            public void handle(ActionEvent event) {
                
                if (currentShapeSelected == null)   {
                
                    Alert alertDlg = new Alert(Alert.AlertType.ERROR);

                    alertDlg.setTitle("Modify Selected Object");
                    alertDlg.setHeaderText("No selected objects found!");

                    Optional<ButtonType> result = alertDlg.showAndWait();
                }
                
                else if (currentShapeSelected instanceof CircleExt) {
                    
                        CircleExt cExt = (CircleExt) currentShapeSelected;
                        CircleExt currentCircle = cExt; 
                        CircleExt returnCircle = modifyCircleExtDialog(cExt);
                        if (!currentCircle.equals(returnCircle))
                                                                 
                            pendingChange = true;
                        
                }   
                
                else if (currentShapeSelected instanceof RectangleExt) {
                                       
                        RectangleExt rExt = (RectangleExt) currentShapeSelected;
                        RectangleExt currentRectangle = rExt;
                        RectangleExt returnRectangle = modifyRectangleExtDialog(rExt); 
                        
                        if (!currentRectangle.equals(returnRectangle))
                            
                            pendingChange = true;
                }  
                  
            } // end override method public void handle(ActionEvent event)
        
        }); // end listener menuItemModify.setOnAction(new EventHandler<ActionEvent> ()
        
        
        menuItemDelete.setOnAction(new EventHandler<ActionEvent> ()  {  
            @Override
            public void handle(ActionEvent event) {
                
                if (currentShapeSelected == null)   {
                
                    Alert alertDlg = new Alert(Alert.AlertType.ERROR);

                    alertDlg.setTitle("Modify Selected Object");
                    alertDlg.setHeaderText("No selected objects found!");

                    Optional<ButtonType> result = alertDlg.showAndWait();
                }
                
                else {
                        dp.getChildren().remove(currentShapeSelected);
                        pendingChange = true;
                        currentShapeSelected = null;
                }   
                
            }
        });
        
        
        
        
        menuItemSaveToDisk.setOnAction(new EventHandler<ActionEvent> () {
            
            @Override
            public void handle(ActionEvent event) {
                
                
                fileChooser.setTitle("Save Drawing");
                               
                // left the below statement out since assignment shows Windows default of This PC
                // fileChooser.setInitialDirectory(new File(System.getProperty("user.Home")));

                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Drawing Files", "*.drw"));                 
                File saveFile = fileChooser.showSaveDialog(primaryStage);
                
                if (saveFile != null) {   // value is null if the user selects Cancel
                     
                        saveDiskFile(saveFile);
                        addDrawingObjects(dp);
                        closeFile();  
                        pendingChange = false;
                }     
            }   
        });  
        
        
        menuItemLoadFromDisk.setOnAction(new EventHandler<ActionEvent> () {
            
            @Override
            public void handle(ActionEvent event) {
                
                
                fileChooser.setTitle("Load Drawing");         
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Drawing Files", "*.drw"));                 
                File loadFile = fileChooser.showOpenDialog(primaryStage);
                
                if (loadFile != null) {   // value is null if the user selects Cancel
                     
                        loadDiskFile(loadFile);
                        retrieveDrawingObjects(dp);
                        closeDiskFile();   
                        currentShapeSelected = null;
                        addListenerToInputShapes();         
                }     
            }   
        });   
        
        menuItemExit.setOnAction(new EventHandler<ActionEvent> () {
            
            @Override
            public void handle(ActionEvent event) {
                
                if (!pendingChange)  {
                    
                   System.exit(1);
                }
                else {
                
                    Alert alertDlg = new Alert(Alert.AlertType.CONFIRMATION);
                    alertDlg.setTitle("Confirm");
                    alertDlg.setHeaderText("Press OK button to confirm. Otherwise, press CANCEL.");
                    alertDlg.setContentText("There are pending changes to the drawing pane.\nAre you sure that "
                                             + "you want to exit the application?");
                    alertDlg.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
                    Optional<ButtonType> result = alertDlg.showAndWait();
                    
                    if (result.get() == ButtonType.OK) {
                        System.exit(1);
                    }   
                    else {return;}
                       
                }
            }
        });
        
                      
        Scene scene = new Scene(root);
        primaryStage.setTitle("Simple Drawing 2D Shapes");
        primaryStage.setScene(scene);
        primaryStage.show();         
      
    }
    
    
  
    // adds listeners to shapes retrieved from loaded drawing
    private void addListenerToInputShapes() {
        
        
        for (Object o : dp.getChildren()) {
           
            if (o instanceof CircleExt)  {
                
               CircleExt circle = (CircleExt) o; 
               circle.setOnMouseClicked(new EventHandler<MouseEvent> () {   
                   @Override
                   public void handle(MouseEvent event) {
                       
                       circle.setStroke(Color.BLACK);
                       circle.setStrokeWidth(3);

                       circle.setCircleSelected(true);

                       if (currentShapeSelected == null)
                             currentShapeSelected = circle;
                       else {
                             updateSelectedObject(circle);                                  
                       }
                   }
                });
            }  else {
            
                RectangleExt rectangleExt = (RectangleExt) o;
                RectangleExt rectangle = (RectangleExt) o; 
                rectangle.setOnMouseClicked(new EventHandler<MouseEvent> () {   
                    @Override
                    public void handle(MouseEvent event) {
                        
                        
                        rectangle.setStroke(Color.BLACK);
                        rectangle.setStrokeWidth(3);

                        rectangle.setRectangleSelected(true);

                        if (currentShapeSelected == null)
                            currentShapeSelected = rectangle;
                        else {
                                updateSelectedObject(rectangle);                                  
                        } 
                    }
                });        
             }
        }
    }
    
    
    
    
    private void updateSelectedObject(Shape shape) {
        
        if(shape.equals(currentShapeSelected))  // no change necessary if this is same shape Object clicked before
            return;
        
        currentShapeSelected.setStrokeWidth(0.0); // removes stroke from around old Shape Object
        
        if (currentShapeSelected instanceof CircleExt) {
              CircleExt circle = (CircleExt) currentShapeSelected;    // sets prior selected CircleExt Object selected value to false
              circle.setCircleSelected(false);
              
         }  
        else {
            
                RectangleExt rectangle = (RectangleExt) currentShapeSelected;  // if the currentShapeSelected Object is an instance of RectangleExt
                rectangle.setRectangleSelected(false);                     
        }
                         
        currentShapeSelected = shape;           // assigns new selected object as the current shape selected    
          
    }
    
        
    //  creates and adds CircleExt to dp --- the DrawingPane 
    //  listener for clicks on Object and Stroke update 
    private void createNewPaneCircle(ActionEvent event) {

        CircleData serialCircle = newCircleDataDialog();  // creates and returns CircleData object from DialogBox method

        CircleExt paneCircle= new CircleExt(serialCircle.getXPos(),serialCircle.getYPos(), serialCircle.getRadius(), serialCircle.getColor());  // create circle to add to APP GUI Pane
        dp.getChildren().add(paneCircle);  // adds Circle to current APP GUI Pane   

        paneCircle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                paneCircle.setStroke(Color.BLACK);
                paneCircle.setStrokeWidth(3);
                
                paneCircle.setCircleSelected(true);
                
                if (currentShapeSelected == null)
                    currentShapeSelected = paneCircle;
                else {
                        updateSelectedObject(paneCircle);                                  
                }
                               
            }
         });
    }        
       
       
    //  creates and adds RectangleExt to dp --- the DrawingPane 
    //  listener for clicks on Object and Stroke update 
    private void createNewPaneRectangle(ActionEvent event) {

        RectangleData serialRectangle = newRectangleDataDialog();  // creates and returns RectangleData object from DialogBox method

        RectangleExt paneRectangle= new RectangleExt(serialRectangle.getXPos(),serialRectangle.getYPos(), serialRectangle.getWidth(), 
                                        serialRectangle.getHeight(), serialRectangle.getColor());  // create Rectangle to add to APP GUI Pane
        dp.getChildren().add(paneRectangle);  // adds Circle to current APP GUI Pane   

        paneRectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                paneRectangle.setStroke(Color.BLACK);
                paneRectangle.setStrokeWidth(3);

                paneRectangle.setRectangleSelected(true);
                
                if (currentShapeSelected == null)
                    currentShapeSelected = paneRectangle;
                else {
                        updateSelectedObject(paneRectangle);                                  
                }

            }
         });
    }   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
