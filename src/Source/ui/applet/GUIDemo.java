/*
The applet - the view for our program.
When inialized, creates the model and controllers.
Uses double buffered graphics to reduce screen flickering.
*/

package ui.applet;
                                    /*
import interfaces.ComparableShape;  */
import interfaces.Resettable;
import java.applet.Applet;
import java.awt.*;
import java.util.Vector;

import event.ShapeMouseHandler;

import shapes.Shape;
import shapes.Oval;
import shapes.Line;
import shapes.Rectangle;
import ui.panels.MainPanel;
import model.Model;

public class GUIDemo extends Applet implements Resettable {
        
    MainPanel mainPanel;
    Model model;
    
    Graphics bufferGraphics;
    Image bufferImage;
  
    /*
    initiate the applet, pass it to the newly created model, create the main
    controllers.
    */
    public void init() {
      
        resize(1000, 400);
        model = new Model(this);
        mainPanel = new MainPanel(model);
        add(mainPanel);
        ShapeMouseHandler mouseHandler = new ShapeMouseHandler(model);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }
    
    /*
     * Double buffering to eliminate possible screen flickering when drawing/moving/etc
     */
    public void update(Graphics g) {
      
        if (bufferImage == null)
        {
            bufferImage = createImage(this.getSize().width,
                                      this.getSize().height);
            bufferGraphics = bufferImage.getGraphics();
        }
      
        bufferGraphics.setColor(getBackground());
        bufferGraphics.fillRect(0, 0, this.getSize().width,
                                this.getSize().height);
      
        bufferGraphics.setColor(getForeground());
      
        drawShapes(bufferGraphics);
        g.drawImage(bufferImage, 0, 0, this);
        
//      model.compareShapes();  
            
    }
    
    /*
     * Ensure each call to paint or repaint goes through the double buffer
     */
    public void paint(Graphics g) {
      
        update(g);
    }
    
    /*
     * Draw all created shapes onto whatever surface necessary
     * (e.g., the double buffer or the applet itself).
     * Will also draw the visual selection cue for the selected shape, making
     * sure the visual cue is on "top" of all other drawings.
     */
    public void drawShapes(Graphics g) {
      
        Vector<Shape> activeShapes = null;
        Shape activeShape = null;
        Shape selectedShape = null;
      
        activeShapes = model.getVisibleShapes();
        selectedShape = model.getSelectedShape();
      
        for (int i = 0; i < activeShapes.size(); i++) {
          
            activeShape = activeShapes.get(i);
            activeShape.draw(g);
        }
        
        if (model.isAShapeSelected()) selectedShape.drawVisualSelectionCue(g);
        
        System.out.println("**************"); 
        System.out.println(model);
        // print short description of all active shapes
        for (int i = 0; i < activeShapes.size(); i++) {
            
            activeShape = activeShapes.get(i);
            if (activeShape instanceof Oval)
                System.out.println("Element " + (i+1) + " is an oval");
            else if (activeShape instanceof Line)
                System.out.println("Element " + (i+1) + " is a line");
            else if (activeShape instanceof Rectangle)
                System.out.println("Element " + (i+1) + " is a rectangle");
            else System.out.println("Element " + (i+1) + " is a triangle");
        }
        // print detailed description of each active shape
        for (int i = 0; i < activeShapes.size(); i++) {
          
            activeShape = activeShapes.get(i);
            System.out.println("Shape " + (i+1) + "\n" + activeShape);
        }
    }
    
    /*
     * Reset the applet and model to the default state.
     * MainPanel controls all other panels, so calling the MainPanel instance
     * resetComponents() will reset all other controllers (and, by extension,
     * the model and applet).
     */
    public void resetComponents() {
      
        mainPanel.resetComponents();
    }
}