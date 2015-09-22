/*
Our shape class.  Abstract class, top of the hierarchy.
*/
package shapes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {

    /*
    All shapes will have a "home" (x,y), a line color, and capacity for
    selection
    */
    private int topLeftX, topLeftY;
    private Color lineColor;
    private boolean isSelected;
    /*  
     *   Possibly include orientation in future builds.
     *   Would allow 360 degree rotation of the shape.
     *   Would need to add "Rotate" as an action choice, etc.
     */
    //private int orientation;
    
    // Shape constructor
    public Shape(int topLeftX, int topLeftY, Color lineColor,
                 boolean isSelected) {
      
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.lineColor = lineColor;
        this.isSelected = false;
    }

    // all shapes will draw themselves, but may have different ways of drawing
    public abstract void draw(Graphics g);
    // all shapes will be selectable, but may have different ways of showing 
    // that they are selected
    public abstract void drawVisualSelectionCue(Graphics g);
    // all shapes will test if the contain an (x,y)
    public abstract boolean containsLocation(int x, int y);

    /*
    All shapes will make an (x,y) their new "home" (x,y)
    */
    public void moveTo(int topLeftX, int topLeftY) {
    
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
    }
    
    public void setLineColor(Color lineColor) {

        this.lineColor = lineColor;
    }
    
    public Color getLineColor() {
      
        return lineColor;
    }

    public int getTopLeftX() {
      
        return topLeftX;
    }

    public void setTopLeftX(int topLeftX) {
        
        this.topLeftX = topLeftX;
    }

    public int getTopLeftY() {
      
        return topLeftY;
    }

    public void setTopLeftY(int topLeftY) {
       
        this.topLeftY = topLeftY;
    }
    
    public boolean getIsSelected() {
      
        return isSelected;
    }
    
    public void setIsSelected(boolean isSelected) {
      
        this.isSelected = isSelected;
    }
}