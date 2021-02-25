/**
 * Tuesday Assignment: Create shape hierarchy
 */
package com.ss.craig.week.one.tuesday.shapes;

/**
 * @author Craig Saunders
 *
 */
public class Rectangle implements Shapes {
    private double area = 0d;
    private double side_a = 0d;
    private double side_b = 0d;

    /* Getters and Setters */
    public double getSide_a()
    {
        return side_a;
    }

    public void setSide_a(double side_a)
    {
        this.side_a = side_a;
    }

    public double getSide_b()
    {
        return side_b;
    }

    public void setSide_b(double side_b)
    {
        this.side_b = side_b;
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

    public Rectangle(double side_a, double side_b)
    {
        this.side_a = side_a;
        this.side_b = side_b;
        setArea();
        display();
    }

    @Override
    public double calculateArea()
    {
        return getSide_a() * getSide_b();
    }

    @Override
    public void display()
    {
        System.out.println("Rectangle");
        System.out.println("Total Area: " + Double.toString(calculateArea()));
        System.out.println();
    }
}
