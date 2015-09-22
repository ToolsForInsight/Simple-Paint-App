/*
A rectangle class.
*/
package shapes;

import java.awt.Color;
import java.awt.Graphics;
import interfaces.ComparableShape;

public class Rectangle extends Shape implements ComparableShape {

    private int height, width;
    private Color fillColor;
    private boolean fill;
    
    private double perimeter;
    private double area;
    
    /*
    constructor for our rectangle
    */
    public Rectangle(int topLeftX, int topLeftY, Color lineColor, 
                     boolean isSelected, int width, int height, boolean fill,
                     Color fillColor) {
      
        super(topLeftX, topLeftY, lineColor, isSelected);
        this.width = width;
        this.height = height;
        this.fill = fill;
        this.fillColor = fillColor;
        
        doAndSetCalculations();
    }
    
    /*
    calculate a rectangle's perimeter
    */
    public double calculatePerimeter() {
     
        int total = 0;
        
        total = (height+width)*2;
        return total;
    }
    
    /*
    calculate a rectangle's area
    */
    public double calculateArea() {

        double total = 0;
        
        total = (double)height*(double)width;
        return total;
    }
    
    /*
    draw the rectangle
    */
    public void draw(Graphics g) {
        // Be nice.  Save the state of the object before changing it.
        Color oldColor = g.getColor();
        if (isFill()) {
            g.setColor(getFillColor());
            g.fillRect(getTopLeftX(), getTopLeftY(), getWidth(), getHeight());
        }
        g.setColor(getLineColor());
        g.drawRect(getTopLeftX(), getTopLeftY(), getWidth(), getHeight());
        // Set the state back when done.
        
        g.setColor(oldColor);
    }
    
    /*
    give the user visual cue by highlighting vertices of the rectangle
    with red squares
    */
    public void drawVisualSelectionCue(Graphics g) {
        
        Color oldColor = g.getColor();        
        
        g.setColor(Color.RED);
            
        g.drawRect(getTopLeftX()-5, getTopLeftY()-5, 10, 10);
        g.drawRect(getTopLeftX()+width-5, getTopLeftY()-5, 10, 10);
        g.drawRect(getTopLeftX()-5, getTopLeftY()+height-5, 10, 10);
        g.drawRect(getTopLeftX()+width-5, getTopLeftY()+height-5, 10, 10);
        
        g.setColor(oldColor);
    }
    
    /*
    determine if (x, y) is inside the rectangle
    */
    public boolean containsLocation(int x, int y) {
      
        if (getTopLeftX() <= x &&
            getTopLeftY() <= y && 
            getTopLeftX() + getWidth() >= x &&
            getTopLeftY() + getHeight() >= y)
        {
            return true;
        }
        return false;
    }
    
    /*
    calculate some rectangle variables
    */
    public void doAndSetCalculations() {
      
        perimeter = calculatePerimeter();
        area = calculateArea();
    }
    
    public void setFillColor(Color fillColor) {
       
        this.fillColor = fillColor;
    }
    
    public Color getFillColor() {
      
        return fillColor;
    }
    
    public void setFill(boolean fill) {
       
       this.fill = fill;
    }
    
    public boolean isFill() {
      
        return fill;
    }
    
    public void setPerimeter(double perimeter) {
      
        this.perimeter = perimeter;
    }
    
    public double getPerimeter() {
   
        return perimeter;
    }
    
    public void setArea(double area) {
        
        this.area = area;
    }
    
    public double getArea() {
    
        return area;
    }

    public int getWidth() {
     
        return width;
    }

    public void setWidth(int width) {
    
        this.width = width;
    }

    public int getHeight() {
    
        return height;
    }

    public void setHeight(int height) {
     
        this.height = height;
    }
    
    public String toString() {
      
        return "Rectangle: \n\tx = " + getTopLeftX() + "\n\ty = " + getTopLeftY() +
               "\n\tw = " + getWidth() + "\n\th = " + getHeight() +
               "\n\tPerimeter: " + getPerimeter() + "\n\tArea: " + getArea();
    }
}