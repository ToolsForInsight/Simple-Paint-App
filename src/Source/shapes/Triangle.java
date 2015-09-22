/*
A triangle class.
*/
package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Array;
import interfaces.ComparableShape;

public class Triangle extends Shape implements ComparableShape {
  
    private int xOne, xTwo, xThree, yOne, yTwo, yThree;
    private int[] xPoints, yPoints;
    private double sideOneLength, sideTwoLength, sideThreeLength;
    private Color fillColor;
    private boolean fill;
    private double perimeter;
    private double area;

    /*
    Create a Triangle with arguments.
    */
    public Triangle(int topLeftX, int topLeftY, Color lineColor, 
                    boolean isSelected, int[] xPoints, int[] yPoints,
                    boolean fill, Color fillColor) {

        super(topLeftX, topLeftY, lineColor, isSelected);
        this.xPoints = xPoints;
        this.xOne = xPoints[0];
        this.xTwo = xPoints[1];
        this.xThree = xPoints[2];
        this.yPoints = yPoints;
        this.yOne = yPoints[0];
        this.yTwo = yPoints[1];
        this.yThree = yPoints[2];
        this.fill = fill;
        this.fillColor = fillColor;
        doAndSetCalculations();
    }
    
    /*
    Calculate the length of side one.
    */
    public double calculateSideOne() {
        
        double length = Math.sqrt(
                          Math.pow((Array.getDouble(getXPoints(), 0) -
                                    Array.getDouble(getXPoints(), 1)), 2) +
                          Math.pow((Array.getDouble(getYPoints(), 0) -
                                    Array.getDouble(getYPoints(), 1)), 2)
                          );
        return length;
    }
    
    /*
    Calculate the length of side two.
    */
    public double calculateSideTwo() {
      
        double length = Math.sqrt(
                          Math.pow((Array.getDouble(getXPoints(), 1) -
                                    Array.getDouble(getXPoints(), 2)), 2) +
                          Math.pow((Array.getDouble(getYPoints(), 1) -
                                    Array.getDouble(getYPoints(), 2)), 2)
                          );
        return length;
    }
    
    /*
    Calculate the length of side three.
    */
    public double calculateSideThree() {
      
        double length = Math.sqrt(
                          Math.pow((Array.getDouble(getXPoints(), 0) -
                                    Array.getDouble(getXPoints(), 2)), 2) +
                          Math.pow((Array.getDouble(getYPoints(), 0) -
                                    Array.getDouble(getYPoints(), 2)), 2)
                          ); 
        return length;
    }

    /*
    Calculate the perimeter.
    */
    public double calculatePerimeter() {
      
        double total = sideOneLength + sideTwoLength + sideThreeLength;
        return total;
    }
    
    /*
    Calculate area based on three vertices.
    */
    public double calculateArea() {
        
        double total = Math.abs(((xPoints[0]*(yPoints[1] - yPoints[2]) +
                                  xPoints[1]*(yPoints[2] - yPoints[0]) +
                                  xPoints[2]*(yPoints[0] - yPoints[1]))/2));
        return total;
    }
    
    /*
    One method to calculate and set all Triangle properties.
    */
    public void doAndSetCalculations() {
      
        sideOneLength = calculateSideOne();
        sideTwoLength = calculateSideTwo();
        sideThreeLength = calculateSideThree();
        perimeter = calculatePerimeter();
        area = calculateArea();
    }
    
    /*
     *  Draw the triangle.  Save the state of the object before changing it,
     *  then set the state back when done.
     *  If it's selected, draw two other surrounding triangles to indicate
     *  that it's selected.
     */
    @Override
    public void draw(Graphics g) {

        Color oldColor = g.getColor();
        
        if (isFill()) {
            g.setColor(getFillColor());
            g.fillPolygon(getXPoints(), getYPoints(), 3);
        }
        
        g.setColor(getLineColor());
        g.drawPolygon(getXPoints(), getYPoints(), 3);
        
        g.setColor(oldColor);
    }
    
    /*
    Show that a triangle is slected by highlighting its vertices
    with red squares
    */
    public void drawVisualSelectionCue(Graphics g) {
        
        Color oldColor = g.getColor();
        
        g.setColor(Color.RED);
            
        g.drawRect(xPoints[0]-5, yPoints[0]-5, 10, 10);
        g.drawRect(xPoints[1]-5, yPoints[1]-5, 10, 10);
        g.drawRect(xPoints[2]-5, yPoints[2]-5, 10, 10);
        
        g.setColor(oldColor);
    }

    /*
    Calculate areas of three triangles resulting from a given (x,y) and the 
    three points of the Triangle.
    If the sum of the areas of the three triangles is equal to the area of the 
    Triangle, the given (x,y) is within the Triangle.
    */
    public boolean containsLocation(int x, int y) {

        double areaOne, areaTwo, areaThree, totalSmallerAreas;
      
        areaOne = Math.abs(((x*(yPoints[1] - yPoints[2]) +
                             xPoints[1]*(yPoints[2] - y) +
                             xPoints[2]*(y - yPoints[1]))/2));

        areaTwo = Math.abs(((xPoints[0]*(y - yPoints[2]) +
                             x*(yPoints[2] - yPoints[0]) +
                             xPoints[2]*(yPoints[0] - y))/2));
      
        areaThree = Math.abs(((xPoints[0]*(yPoints[1] - y) +
                               xPoints[1]*(y - yPoints[0]) +
                               x*(yPoints[0] - yPoints[1]))/2));
      
        totalSmallerAreas = areaOne+areaTwo+areaThree;
        
        if ((totalSmallerAreas) >= (area - 1) &&
            (totalSmallerAreas) <= (area + 1)) return true;
        return false;
    }
    
    /*
    Move the Triangle to a given (x,y), keeping all properties (area, side
    length, etc.) except vertices the same.
    */
    public void moveTo(int x, int y) {
      
        int xDifference = (x - xOne);
        int yDifference = (y - yOne);
        
      
        setXOne(xOne + xDifference);
        setYOne(yOne + yDifference);
      
        setXTwo(xTwo + xDifference);
        setYTwo(yTwo + yDifference);
        
        setXThree(xThree + xDifference);
        setYThree(yThree + yDifference);
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
    
    public void setXOne(int xOne) {
      
        this.xPoints[0] = xOne;
        this.xOne = xOne;
    }
  
    public int getXOne() {
    
        return xOne;
    }
  
    public void setXTwo(int xTwo) {
    
        this.xPoints[1] = xTwo;
        this.xTwo = xTwo;
    }

    public int getXTwo() {
  
        return xTwo;
    }

    public void setXThree(int xThree) {
      
        this.xPoints[2] = xThree;
        this.xThree = xThree;
    }
    
    public int getXThree() {
      
        return xThree;
    }
    
    public void setYOne(int yOne) {
      
        this.yPoints[0] = yOne;
        this.yOne = yOne;
    }

    public int getYOne() {
  
        return yOne;
    }

    public void setYTwo(int yTwo) {
  
        this.yPoints[1] = yTwo;
        this.yTwo = yTwo;
    }

    public int getYTwo() {

        return yTwo;
    }

    public void setYThree(int yThree) {
    
        this.yPoints[2] = yThree;
        this.yThree = yThree;
    }
  
    public int getYThree() {
    
        return yThree;
    }
    
    public int[] getXPoints() {
      
      return xPoints;
    }

    public void setXPoints(int xOne, int xTwo, int xThree) {
  
      int[] points = {xOne, xTwo, xThree};
      this.xOne = xOne;
      this.xTwo = xTwo;
      this.xThree = xThree;
      this.xPoints = points;
    }

    public int[] getYPoints() {
  
        return yPoints;
    }

    public void setYPoints(int yOne, int yTwo, int yThree) {
   
        int[] points = {yOne, yTwo, yThree};
        this.yOne = yOne;
        this.yTwo = yTwo;
        this.yThree = yThree;
        this.yPoints = points;
    }
    
    public double getSideOneLength() {
      
        return sideOneLength;
    }

    public void setSideOneLength(double sideOneLength) {
  
        this.sideOneLength = sideOneLength;
    }
    
    public double getSideTwoLength() {
      
      return sideTwoLength;
    }

    public void setSideTwoLength(double sideTwoLength) {

        this.sideTwoLength = sideTwoLength;
    }
  
    public double getSideThreeLength() {
    
      return sideThreeLength;
    }

    public void setSideThreeLength(double sideThreeLength) {

        this.sideThreeLength = sideThreeLength;
    }
  
    public String toString() {
    
        return "Triangle: \n\txOne = " + getXOne() + "\n\tyOne = " +
               getYOne() + "\n\tSide One: " + getSideOneLength() +
               "\n\tSide Two: " + getSideTwoLength() + "\n\tSide Three: " +
               getSideThreeLength() + "\n\tPerimeter: " + getPerimeter() +
               "\n\tArea: " + getArea();
    }
}