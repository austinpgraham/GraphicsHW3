/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Provides a mouse listener for the GL canvas
 */
public class EnvironmentMouseListener implements MouseListener, MouseMotionListener
{
    // The collection of stars
    private StarCollection stars;

    // Limit of the horizon
    private float horizon;

    // The drawn kite string
    private KiteString ks;

    private boolean hasDragged = false;

    /**
     * Construct the listener
     */
    public EnvironmentMouseListener(StarCollection stars, KiteString str, float horizon)
    {
        this.stars = stars;
        this.horizon = horizon;
        this.ks = str;
    }

    /**
     * Translate x,y point to GL coordinates
     * @param x: X coordinate
     * @param y: Y coordinage
     */
    private Point translateToGL(float x, float y)
    {
        float glX = 4*x / 1100f - 2f;
        float glY = 2*y / 550f - 1f;
        return new Point(glX, -glY);
    }

    /**
     * If the mouse button is released
     */
    public void mouseReleased(MouseEvent e) 
    {
        // Connect kite string to kite
        if(this.hasDragged)
        {
            this.ks.finish();
        }
        else
        {
            this.ks.reset();
        }
        this.hasDragged = false;
    }

    /**
     * If mouse button is pressed
     */
    public void mousePressed(MouseEvent e) 
    {
        // reset the kite string
        this.ks.reset();
    }

    /**
     * If the mouse is dragged
     */
    public void mouseDragged(MouseEvent e)
    {
        // Translate the point and add to kite string
        Point glPoint = this.translateToGL(e.getX(), e.getY());
        this.ks.addPoint(glPoint);
        this.hasDragged = true;
    }

    public void mouseMoved(MouseEvent e)
    {
        // Not implemented
    }

    public void mouseExited(MouseEvent e) 
    {
        // Not implemented
    }

    public void mouseEntered(MouseEvent e) 
    {
        // Not implemented
    }

    /**
     * If the mouse is clicked
     */
    public void mouseClicked(MouseEvent e) 
    {
        // Translate the point and moved the focused star 
        // to that GL point
        Point glPoint = this.translateToGL(e.getX(), e.getY());
        Star focusStar = this.stars.getFocusedStar();
        if(focusStar != null && glPoint.getFloatY() > this.horizon)
        {
            focusStar.setCenter(glPoint);
        }
    }
}