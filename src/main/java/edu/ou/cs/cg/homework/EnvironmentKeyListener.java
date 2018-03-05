package edu.ou.cs.cg.homework;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class EnvironmentKeyListener implements KeyListener
{
    private Hopscotch hop;
    private Road road;
    private GreenHouse gh;
    private BrownHouse lbh;
    private BrownHouse rbh;

    private boolean shiftPressed = false;

    public EnvironmentKeyListener(Hopscotch hop, Road road, GreenHouse gh, BrownHouse lbh, BrownHouse rbh)
    {
        this.hop = hop;
        this.road = road;
        this.gh = gh;
        this.lbh = lbh;
        this.rbh = rbh;
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
            case KeyEvent.VK_W:
                this.lbh.toggleWindows();
                this.rbh.toggleWindows();
                this.gh.toggleWindows();
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