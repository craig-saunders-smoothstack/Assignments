/**
 * Tuesday Assignment: Create shape hierarchy
 */
package com.ss.craig.week.one.tuesday.shapes;

/**
 * @author Craig Saunders
 *
 */
public class Triangle implements Shapes {
    private double area = 0d;
    private double side_b = 0d;
    private double height = 0d;

    /* Getters and Setters */
    public double getSide_b()
    {
        return side_b;
    }

    public void setSide_b(double side_b)
    {
        this.side_b = side_b;
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    @Override
    public double getArea()
    {
        return area;
    }

    @Override
    public void setArea()
    {
        this.area = calculateArea();
    }
    /* End Getters and Setters */

    public Triangle(double height, double side_b)
    {
        this.side_b = side_b;
        this.height = height;
        setArea();
        display();
    }

    @Override
    public void display()
    {
        System.out.println("Triagle");
        System.out.println("Total Area: " + Double.toString(calculateArea()));
        System.out.println();
    }

    @Override
    public double calculateArea()
    {
        return (getHeight() * getSide_b()) / 2d;
    }

}
