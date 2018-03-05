package edu.ou.cs.cg.homework;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class EnvironmentKeyListener implements KeyListener
{
    private Hopscotch hop;
    private Road road;
    private GreenHouse gh;
    private BrownHouse lbh;
    private BrownHouse rbh;
    private ArrayList<FenceLine> fences;

    private boolean shiftPressed = false;

    public EnvironmentKeyListener(Hopscotch hop, Road road, GreenHouse gh, BrownHouse lbh, BrownHouse rbh, ArrayList<FenceLine> fences)
    {
        this.hop = hop;
        this.road = road;
        this.gh = gh;
        this.lbh = lbh;
        this.rbh = rbh;
        this.fences = fences;
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
            case KeyEvent.VK_UP: 
                if(this.hop.isWithinUpperLimit())
                {
                    this.hop.incrementYBy(0.1f*slabWidth);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(this.hop.isWithinLowerLimit())
                {
                    this.hop.incrementYBy(-0.1f*slabWidth);
                }
                break;
            case KeyEvent.VK_SHIFT:
                this.shiftPressed = false;
                break;
            case KeyEvent.VK_W:
                this.lbh.toggleWindows();
                this.rbh.toggleWindows();
                this.gh.toggleWindows();
                break;
            case KeyEvent.VK_PAGE_DOWN:
                for(FenceLine line: this.fences)
                {
                    line.adjustHeight(-0.01f);
                }
                break;
            case KeyEvent.VK_PAGE_UP:
                for(FenceLine line: this.fences)
                {
                    line.adjustHeight(0.01f);
                }
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