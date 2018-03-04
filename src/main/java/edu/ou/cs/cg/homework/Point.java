package edu.ou.cs.cg.homework;

import java.awt.geom.Point2D;

public class Point extends Point2D.Float
{
    public Point(float x, float y)
    {
        super(x, y);
    }

    public float getFloatY()
    {
        return (float)this.getY();
    }

    public float getFloatX()
    {
        return (float)this.getX();
    }
}