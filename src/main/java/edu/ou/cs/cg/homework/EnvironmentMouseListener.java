package edu.ou.cs.cg.homework;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnvironmentMouseListener implements MouseListener
{
    private StarCollection stars;
    private float horizon;

    public EnvironmentMouseListener(StarCollection stars, float horizon)
    {
        this.stars = stars;
        this.horizon = horizon;
    }

    private Point translateToGL(float x, float y)
    {
        float glX = 4*x / 1100f - 2f;
        float glY = 2*y / 550f - 1f;
        return new Point(glX, -glY);
    }

    public void mouseReleased(MouseEvent e) 
    {
        Point glPoint = this.translateToGL(e.getX(), e.getY());
        Star focusStar = this.stars.getFocusedStar();
        if(focusStar != null && glPoint.getFloatY() > this.horizon)
        {
            focusStar.setCenter(glPoint);
        }
    }

    public void mousePressed(MouseEvent e) 
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
        
    }
}