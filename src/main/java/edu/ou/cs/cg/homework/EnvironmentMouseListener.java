package edu.ou.cs.cg.homework;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class EnvironmentMouseListener implements MouseListener, MouseMotionListener
{
    private StarCollection stars;
    private float horizon;
    private KiteString ks;

    public EnvironmentMouseListener(StarCollection stars, KiteString str, float horizon)
    {
        this.stars = stars;
        this.horizon = horizon;
        this.ks = str;
    }

    private Point translateToGL(float x, float y)
    {
        float glX = 4*x / 1100f - 2f;
        float glY = 2*y / 550f - 1f;
        return new Point(glX, -glY);
    }

    public void mouseReleased(MouseEvent e) 
    {
        this.ks.finish();
    }

    public void mousePressed(MouseEvent e) 
    {
        Point glPoint = this.translateToGL(e.getX(), e.getY());
        this.ks.reset();
        this.ks.addPoint(glPoint);
    }

    public void mouseDragged(MouseEvent e)
    {
        Point glPoint = this.translateToGL(e.getX(), e.getY());
        this.ks.addPoint(glPoint);
    }

    public void mouseMoved(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e) 
    {
        
    }

    public void mouseEntered(MouseEvent e) 
    {
        
    }

    public void mouseClicked(MouseEvent e) 
    {
        Point glPoint = this.translateToGL(e.getX(), e.getY());
        Star focusStar = this.stars.getFocusedStar();
        if(focusStar != null && glPoint.getFloatY() > this.horizon)
        {
            focusStar.setCenter(glPoint);
        }
    }
}