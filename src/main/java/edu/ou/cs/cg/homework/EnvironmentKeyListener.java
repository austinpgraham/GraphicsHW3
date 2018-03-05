/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

/**
 * Implments a key listener for the GL canvas
 */
public class EnvironmentKeyListener implements KeyListener
{
    // The hopscotch drawn object
    private Hopscotch hop;

    // The road drawn object
    private Road road;

    // The green house drawn object
    private GreenHouse gh;

    // The left brown house drawn object
    private BrownHouse lbh;

    // The right brown house drawn object
    private BrownHouse rbh;

    // The list of fence groupings
    private ArrayList<FenceLine> fences;

    // The kite drawn object
    private Kite kite;

    // The collection of stars
    private StarCollection stars;

    // If shift has been pressed
    private boolean shiftPressed = false;

    /**
     * Build the key listener
     */
    public EnvironmentKeyListener(Hopscotch hop, Road road, GreenHouse gh, BrownHouse lbh, BrownHouse rbh, ArrayList<FenceLine> fences, Kite kite, StarCollection stars)
    {
        this.hop = hop;
        this.road = road;
        this.gh = gh;
        this.lbh = lbh;
        this.rbh = rbh;
        this.fences = fences;
        this.kite = kite;
        this.stars = stars;
    }

    public void keyTyped(KeyEvent e) 
    {
        // Not implemented
    }

    /**
     * If a key is released
     * @param e: The Key event
     */
    public void keyReleased(KeyEvent e)
    {
        // Get size of one slab of the road
        float slabWidth = this.road.getDelta();
        
        // Determine how much hopscotch should skip by if necessary
        float goBy = this.shiftPressed ? slabWidth : slabWidth*0.1f;

        // Based on the key that was released...
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                // Move hopscotch left
                this.hop.incrementBy(-goBy);
                break;
            case KeyEvent.VK_RIGHT:
                // Move hopscotch right
                this.hop.incrementBy(goBy);
                break;
            case KeyEvent.VK_UP: 
                // If there is room, move hopscotch up
                if(this.hop.isWithinUpperLimit())
                {
                    this.hop.incrementYBy(0.1f*slabWidth);
                }
                break;
            case KeyEvent.VK_DOWN:
                // If there is room, move hopscotch down
                if(this.hop.isWithinLowerLimit())
                {
                    this.hop.incrementYBy(-0.1f*slabWidth);
                }
                break;
            case KeyEvent.VK_SHIFT:
                // Mark shift key as released
                this.shiftPressed = false;
                break;
            case KeyEvent.VK_W:
                // Toggle the windows closed/open
                this.lbh.toggleWindows();
                this.rbh.toggleWindows();
                this.gh.toggleWindows();
                break;
            case KeyEvent.VK_PAGE_DOWN:
                // Move all fences down by 0.01f
                for(FenceLine line: this.fences)
                {
                    line.adjustHeight(-0.01f);
                }
                break;
            case KeyEvent.VK_PAGE_UP:
                // Move all fences up by 0.01f
                for(FenceLine line: this.fences)
                {
                    line.adjustHeight(0.01f);
                }
                break;
            // Set the wing count to the number
            // pressed
            case KeyEvent.VK_1:
                this.kite.setWingCount(1);
                break;
            case KeyEvent.VK_2:
                this.kite.setWingCount(2);
                break;
            case KeyEvent.VK_3:
                this.kite.setWingCount(3);
                break;
            case KeyEvent.VK_4:
                this.kite.setWingCount(4);
                break;
            case KeyEvent.VK_5:
                this.kite.setWingCount(5);
                break;
            case KeyEvent.VK_6:
                this.kite.setWingCount(6);
                break;
            case KeyEvent.VK_7:
                this.kite.setWingCount(7);
                break;
            case KeyEvent.VK_8:
                this.kite.setWingCount(8);
                break;
            case KeyEvent.VK_9:
                this.kite.setWingCount(9);
                break;
            case KeyEvent.VK_ENTER:
                this.stars.cycleFocus();
                break;
        } 
    }

    // If shift is pressed, mark as pressed
    public void keyPressed(KeyEvent e) 
    {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT)
        {
            this.shiftPressed = true;
        }
    }
}