/*
This is the model (of Model-View-Controller fame)
for our shape painting program.  It holds states of lots of important
variables.
*/

package model;

import java.awt.Color;
import shapes.Rectangle;
import shapes.Oval;
import shapes.Line;
import shapes.Triangle;
import shapes.Shape;
import java.awt.Container;
import java.util.HashMap;
import java.util.Vector;

import interfaces.Resettable;      /*
import interfaces.ComparableShape; */

public class Model implements Resettable {

    // to hold whatever cotainer we need (in this case, an applet)
    private Container container;
    
    // collections for actions and shapes
    public enum ActionChoice {
        DRAW, MOVE, RESIZE,
        REMOVE, CHANGE, FILL;
    }
    
    public enum ShapeChoice {
        RECTANGLE, OVAL, LINE, TRIANGLE;
    }
    
    /*
    create a hashmap for string -> color.  handy for ensuring we only need
    to use the string variable instead of defining a Color variable and
    String variable and keeping getters/setters/references straight.
    */
    public final static String BLACK = "Black";
    public final static String BLUE = "Blue";
    public final static String CYAN = "Cyan";
    public final static String GRAY = "Gray";
    public final static String GREEN = "Green";
    public final static String MAGENTA = "Magenta";
    public final static String ORANGE = "Orange";
    public final static String PINK = "Pink";
    public final static String RED = "Red";
    public final static String WHITE = "White";
    public final static String YELLOW = "Yellow";
    
    public final static HashMap<String, Color> colorMap = 
            new HashMap<String, Color>();
    
    static {
        colorMap.put("Black", Color.BLACK);
        colorMap.put("Blue", Color.BLUE);    
        colorMap.put("Cyan", Color.CYAN);
        colorMap.put("Gray", Color.GRAY);
        colorMap.put("Green", Color.GREEN);
        colorMap.put("Magenta", Color.MAGENTA);
        colorMap.put("Orange", Color.ORANGE);
        colorMap.put("Pink", Color.PINK);
        colorMap.put("Red", Color.RED);
        colorMap.put("White", Color.WHITE);
        colorMap.put("Yellow", Color.YELLOW);
    }
    
    /*
    describe the state of the model.  the controller and view portions of
    the program will use these a lot.
    */
    private ActionChoice actionChoice = ActionChoice.DRAW;
    private ShapeChoice shapeChoice = ShapeChoice.RECTANGLE;
    private String lineColor = BLACK;
    private boolean fill = false;
    private String fillColor = null;
/*  private String comparableShapeMessage = "Need two shapes to compare.";
    use this if implementing interface ComparableShape and related
    functionality.
    */
    
    /*
    for performing operations on the correct shape (e.g., drawing, selecting)
    */
    private Shape currentShape;
    private Shape selectedShape;
    private Vector<Shape> visibleShapes = new Vector<Shape>();
    
    public Shape createShape() {
      
        /*
        create the correct shape.  the shape mouse handler will handle
        assigning interesting values (e.g., not "0").
        */
        switch (shapeChoice) {
            case RECTANGLE:
                currentShape = new Rectangle(0,0,colorMap.get(lineColor),false, 
                                         0,0,fill,colorMap.get(fillColor));
                break;
            case OVAL:
                currentShape = new Oval(0,0,colorMap.get(lineColor),false,
                                        0,0,fill,colorMap.get(fillColor));
                break;
            case TRIANGLE:
                int[] shapeXPoints = {0,0,0};
                int[] shapeYPoints = {0,0,0};
                currentShape = new Triangle(0,0,colorMap.get(lineColor),false,
                                            shapeXPoints,shapeYPoints,fill,colorMap.get(fillColor));
                break;
            case LINE:
                currentShape = new Line(0,0,colorMap.get(lineColor),false,
                                        50,0);
                break;
        }
        
        // put the shape in the vector
        visibleShapes.add(currentShape);
          
        return currentShape;
    }
    
    public Shape getCurrentShape() {
      
        return currentShape;
    }
    
    public void setCurrentShape(Shape currentShape) {
      
        this.currentShape = currentShape;
    }
    
    public Vector<Shape> getVisibleShapes() {
      
        return visibleShapes;
    }
    
    // constuctor.  
    public Model (Container container) {
      
        this.container = container;
    }
    
/*  public int compareShapes() {
        
        if (visibleShapes[0] != null && visibleShapes[1] != null)
        {
            if (visibleShapes[0] instanceof ComparableShape && visibleShapes[1] instanceof ComparableShape)
            {
                double areaShapeOne = ((ComparableShape)visibleShapes[0]).getArea();
                double areaShapeTwo = ((ComparableShape)visibleShapes[1]).getArea();
            
                if (areaShapeOne > areaShapeTwo)
                {
                    setComparableShapeMessage("Shape One has a larger area than Shape Two.");
                    return 1;
                }
                if (areaShapeOne < areaShapeTwo) 
                {
                    setComparableShapeMessage("Shape Two has a larger area than Shape One.");
                    return 2;
                }
                if (areaShapeOne == areaShapeTwo)
                {
                    setComparableShapeMessage("Shapes One and Two have the same area.");
                    return 0;
                }
            }
            else if (visibleShapes[0] instanceof ComparableShape || visibleShapes[1] instanceof ComparableShape)
                setComparableShapeMessage("Cannot compare lines to closed shapes.");
            else setComparableShapeMessage("Comparison between lines not yet programmed!");
        }
        return -1;
    } */
    
    /*
    Tell the view to repaint
    */
    public void repaint() {
      
        container.repaint();
    }
    
    /*
    Select a created and drawn shape.
    Tests whether an (x,y) is within a visible shape.  If so, adds to potential
    selections.
    Selects the last drawn shape from the potential selections.
    */
    public void selectExistingShape(int xWithin, int yWithin) {
        
        Vector<Shape> potentialSelections = new Vector<Shape>();
      
        for (Shape visibleShape : visibleShapes) {
        
            if (visibleShape.getIsSelected())
                visibleShape.setIsSelected(false);
            
            if (visibleShape.containsLocation(xWithin,  yWithin))
                potentialSelections.add(visibleShape);
        }
        
        if (potentialSelections.size() >= 1)
        {
            selectedShape = potentialSelections.get(potentialSelections.size()-1);
            selectedShape.setIsSelected(true);
        }
    }
    
    public Shape getSelectedShape() {
      
        return selectedShape;
    }
    
    /*
    Check if there exists at least one shape that is selected
    in the set of all drawn shapes.
    */
    public boolean isAShapeSelected() {
      
        boolean selected = false;
        
        for (Shape shape : visibleShapes) {
          
            if (shape.getIsSelected())
            {
              selected = true;
              break;
            }
        }
        return selected;
    }
    
    /*
    remove a shape from the model
    */
    public void removeSelectedShape(Shape selectedShape) {
      
        visibleShapes.remove(selectedShape);
    }
 
    public void setSelectedShape(Shape selectedShape) {
        this.selectedShape = selectedShape;
    }
    
    /*
    reset all the model's members to default values.
    */
    public void resetComponents() {
      
        actionChoice = ActionChoice.DRAW;
        shapeChoice = ShapeChoice.RECTANGLE;
        currentShape = null;
        visibleShapes.clear();
        lineColor = BLACK;
        fill = false;
        fillColor = null;
        selectedShape = null;
/*      comparableShapeMessage = "Need two shapes to compare.";    */
        if (container instanceof Resettable) {
            ((Resettable)container).resetComponents();
        }
    }

    public boolean isFill() {
      return fill;
    }

    public void setFill(boolean fill) {
      this.fill = fill;
    }
    
    public String getFillColor() {
      return fillColor;
    }
    
    public void setFillColor(String fillColor) {
      this.fillColor = fillColor;
    }
    
    public String getLineColor() {
      return lineColor;
    }
    
    public void setLineColor(String lineColor) {
      this.lineColor = lineColor;
    }
    
    public void setActionChoice(ActionChoice actionChoice) {
      this.actionChoice = actionChoice;
    }
    
    public ActionChoice getActionChoice() {
      return actionChoice;
    }
    
    public void setShapeChoice(ShapeChoice shapeChoice) {
      this.shapeChoice = shapeChoice;
    }
    
    public ShapeChoice getShapeChoice() {
      return shapeChoice;
    }
    
/*  public String getComparableShapeMessage() {
      return comparableShapeMessage;
    }
    
    public void setComparableShapeMessage(String comparableShapeMessage) {
      this.comparableShapeMessage = comparableShapeMessage;
    }  */
    
    public String toString() {
      
        return "Model:\n\tAction: " + actionChoice +
               "\n\tLine Color: " + lineColor + "\n\tFill: " + fill +
               "\n\tFill Color: " + fillColor + "\n\tChoice: " + shapeChoice +
               "\n\tSelected Shape: " + "\n\t    " + selectedShape;
    }
}