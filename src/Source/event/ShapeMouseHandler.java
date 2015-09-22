/*
ShapeMouseHandler handles the user input for this program.
The user gives this program input solely from the mouse.
This class is part of the controller in the M-V-C architecture
of the program.  It tells the model what to do based on user input.
*/
package event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Model;

import shapes.Rectangle;
import shapes.Triangle;
import shapes.Oval;
import shapes.Line;
import shapes.Shape;

/**
 *
 * @author ryanwilliamconnor
 */
public class ShapeMouseHandler extends MouseAdapter {

    private Model model;
    // start x and y location used to mark where the upper left corner of a shape is.
    private int startX;
    private int startY;
    
    private Shape shape;
    
    // Constructor.  Sets the model for this Listener.

    /**
     *
     * @param model
     */
        public ShapeMouseHandler(Model model) {
      
        this.model = model;
    }
    
    /*
    Manipulate the model based on the user pressing the mouse.
    If the action selected is "Draw", we want one press of the mouse to draw
    a default version of the chosen shape.
    If the action selected is "Move", we want one press of the mouse to select
    a shape, then a second press of the mouse to move the selected shape 
    to the indicated coordinates.
    If the action selected is "Remove", remove the shape from the model and
    screen.
    If the action selected is "Change", set the line color, fill, and fill
    color to the selected options.
    */
    public void mousePressed(MouseEvent e) {
        
        if (model.getActionChoice() == Model.ActionChoice.DRAW)
        {
            // if a shape is already selected, deselect that shape.
            if (model.isAShapeSelected())
            {
                shape.setIsSelected(false);
                model.setSelectedShape(null);
            }
            // original upper left x and y of the shape.
            startX = e.getX();
            startY = e.getY();
            // have the model create a new shape for us.
            shape = model.createShape();
            // only allow calls to shape's method if shape
            // was successfully created.
            if (shape!=null)
            {
                // set its upper left x and y to where the mouse was pressed.
                shape.setTopLeftX(e.getX());
                shape.setTopLeftY(e.getY());
                // set appropriate qualities based on type of shape. 
                if (shape instanceof Rectangle || shape instanceof Oval)
                {
                    ((Rectangle)shape).setWidth(50);
                    ((Rectangle)shape).setHeight(50);
                    
                    if (shape instanceof Oval)
                        ((Oval)shape).doAndSetCalculations();
                    else ((Rectangle)shape).doAndSetCalculations();
                }
                if (shape instanceof Triangle)
                {
                    ((Triangle)shape).setXPoints(e.getX(),e.getX()-25,
                                                 e.getX()+25);
                    ((Triangle)shape).setYPoints(e.getY(),e.getY()+30,
                                                 e.getY()+30);
                    ((Triangle)shape).doAndSetCalculations();
                }
                if (shape instanceof Line)
                {
                    ((Line)shape).setXTwo(e.getX()+50);
                    ((Line)shape).setYTwo(e.getY());
                    ((Line)shape).doAndSetCalculations();
                }
            }
        }
        // we're not in draw mode
        else
        {
            // a shape is already selected
            if (model.isAShapeSelected())
            {
                //move the shape if we're in move mode.
                if (model.getActionChoice() == Model.ActionChoice.MOVE)
                {
                    shape.moveTo(e.getX(), e.getY()); 
                }
                //we just pressed.  deselect the shape.
                shape.setIsSelected(false);
                model.setSelectedShape(null);
            }
            // a shape was not aleady selected.  so try to select one.
            else model.selectExistingShape(e.getX(), e.getY());
            // if the user selected a shape, maybe do stuff 
            // to the shape based on the action chosen
            // if "Move" is selected, do nothing but select the shape and set
            // startX and startY.
            if (model.isAShapeSelected())
            {
                shape = model.getSelectedShape();
                startX = shape.getTopLeftX();
                startY = shape.getTopLeftY();
                // remove the shape
                if (model.getActionChoice() == Model.ActionChoice.REMOVE)
                {
                    shape.setIsSelected(false);
                    model.setSelectedShape(null);
                    model.removeSelectedShape(shape);
                }
                // set the shape's fill, fill color and line color to whatever
                // is currently chosen
                if (model.getActionChoice() == Model.ActionChoice.CHANGE)
                {
                    shape.setLineColor(Model.colorMap.get(model.getLineColor()));
                
                    if (shape instanceof Rectangle)
                    {
                        ((Rectangle)shape).setFillColor(Model.colorMap.get(model.getFillColor()));
                        ((Rectangle)shape).setFill(model.isFill());
                    }
                    if (shape instanceof Triangle)
                    {
                        ((Triangle)shape).setFillColor(Model.colorMap.get(model.getFillColor()));
                        ((Triangle)shape).setFill(model.isFill());
                    }
                
                    shape.setIsSelected(false);
                    model.setSelectedShape(null);
                }
            }
        }
        // tell the model to repaint the applet or application.
        model.repaint();
    }
    
    /*
    Manipulate the model based on the user dragging the mouse (after pressed).
    If the action is "Resize" and a shape is selected, or if the action is
    "Draw", continuously resize the shape based on the dragging mouse.
    */
    public void mouseDragged(MouseEvent e) {
      
        if ((model.isAShapeSelected() &&
             model.getActionChoice() == Model.ActionChoice.RESIZE)
            ||
            (model.getActionChoice() == Model.ActionChoice.DRAW)
           ) {
            // cast the shape to get the right draw and calculations
            if (shape instanceof Rectangle || shape instanceof Oval)
            {
                shape.setTopLeftX(Math.min(startX, e.getX()));
                shape.setTopLeftY(Math.min(startY, e.getY()));
                ((Rectangle)shape).setWidth(Math.abs(startX - e.getX()));
                ((Rectangle)shape).setHeight(Math.abs(startY - e.getY()));
                if (shape instanceof Oval)
                    ((Oval)shape).doAndSetCalculations();
                else ((Rectangle)shape).doAndSetCalculations();
            }
            
            if (shape instanceof Triangle)
            {
                ((Triangle)shape).setXThree(e.getX());
                ((Triangle)shape).setYThree(e.getY());
                ((Triangle)shape).doAndSetCalculations();
            }
        
            if (shape instanceof Line)
            {
                ((Line)shape).setXTwo(e.getX());
                ((Line)shape).setYTwo(e.getY());
                ((Line)shape).doAndSetCalculations();
            }   
        }
        // tell the model to repaint the applet or application.
        model.repaint();
    }

    /*
    Manipulate the model based on the user releasing the mouse after pressing
    and dragging.
    Only needed to "deselect" a resized, selected shape, which removes the
    visual highlighting cues.
    */
    public void mouseReleased(MouseEvent e) {

        if (model.isAShapeSelected() &&
            (model.getActionChoice() == Model.ActionChoice.RESIZE)) 
        {
                shape.setIsSelected(false);
                model.setSelectedShape(null);
                
                model.repaint();
        }
    }
}