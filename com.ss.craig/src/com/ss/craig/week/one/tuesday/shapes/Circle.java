/**
 * Tuesday Assignment: Create shape hierarchy
 */
package com.ss.craig.week.one.tuesday.shapes;

/**
 * @author Craig Saunders
 *
 */
public class Circle implements Shapes {
    private double area = 0d;
    private double radius = 0d;

    /* Getters and Setters */
    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }
    @Override
    public double getArea() { return area; }    
    @Override
    public void setArea() { this.area = calculateArea(); }
    /* End Getters and Setters */

    public Circle(double radius)
    {
        this.radius = radius;
        setArea();
        display();
    }
    
    @Override
    public void display() {
        System.out.println("Circle");
        System.out.println("Total Area: "+Double.toString(calculateArea()));  
        System.out.println();
    }

    @Override
    public double calculateArea() {
        return Math.pow(radius,2)*Math.PI;
    }
}
