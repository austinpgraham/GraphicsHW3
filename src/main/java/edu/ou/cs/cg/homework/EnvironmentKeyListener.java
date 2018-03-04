package edu.ou.cs.cg.homework;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class EnvironmentKeyListener implements KeyListener
{
    private Hopscotch hop;
    private Road road;

    private boolean shiftPressed = false;

    public EnvironmentKeyListener(Hopscotch hop, Road road)
    {
        this.hop = hop;
        this.road = road;
    }

    public void keyTyped(KeyEvent e) 
    {
        
    }

    public void keyReleased(KeyEvent e)
    {
        float slabWidth = this.road.getDelta();
        float goBy = this.shiftPressed ? slabWidth : slabWidth*0.1f;
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                this.hop.incrementBy(-goBy);
                break;
            case KeyEvent.VK_RIGHT:
                this.hop.incrementBy(goBy);
                break;
            case KeyEvent.VK_SHIFT:
                this.shiftPressed = false;
                break;
        } 
    }

    public void keyPressed(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT)
        {
            this.shiftPressed = true;
        }
    }
}