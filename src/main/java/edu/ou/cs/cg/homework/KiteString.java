/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import java.util.ArrayList;

import javax.media.opengl.*;

/**
 * Implemenation of drawable kite string
 */
public class KiteString extends Drawable
{
    // The start point of the kite string
    private Point start;
    
    // The alpha of the kite string
    private float alpha = 1.0f;

    // List of points in the string
    private ArrayList<Point> points;

    // If this string has finished drawing
    private boolean isFinished = true;

    /**
     * Construct the object with designated start and end
     */
    public KiteString(Point start)
    {
        this.start = start;
        this.points = new ArrayList<Point>();
        this.points.add(start);

    }

    /**
     * Add a point to the string object
     */
    public void addPoint(Point newPoint)
    {
        this.points.add(newPoint);
    }

    /**
     * Append the end point and 
     * set the alpha to full saturation
     */
    public void finish()
    {
        this.alpha = 1.0f;
        this.isFinished = true;
    }

    /**
     * Remove all points in the string
     */
    public void reset()
    {
        this.points = new ArrayList<Point>();
        this.points.add(start);
        this.alpha = 0.5f;
        this.isFinished = false;
    }

    /**
     * Draw the string
     */
    private void drawString(GL2 gl)
    {
        final float[] GRAY = new float[]{0.8f, 0.8f, 0.8f};
        for(int i = 1; i < this.points.size(); i++)
        {
            Utils.drawLine(gl, this.points.get(i-1), this.points.get(i), GRAY, 2, this.alpha);
        }
    }

    /**
     * Update the drawable object
     */
    public void update(GL2 gl)
    {
        this.drawString(gl);
    }

    /**
     * Get number of points in kite string
     */
    public int getPointCount()
    {
        return this.points.size();
    }

    /**
     * Get if the current string has finished
     */
    public boolean isFinished()
    {
        return this.isFinished;
    }

    /**
     * Get the final point in the kite string
     */
    public Point getFinalPoint()
    {
        return this.points.get(this.points.size() - 1);
    }
}