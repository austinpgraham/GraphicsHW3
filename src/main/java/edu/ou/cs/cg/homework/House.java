/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

/**
 * Base class for a drawable house object
 */
public class House extends DimensionedDrawable
{
    // Dimensions of the house objects
    protected final float WIDTH = 0.5f;
    protected final float HEIGHT = 0.5f;

    // If the windows should be closed or not
    protected boolean windowsClosed;

    // the road limir coordinate
    protected float road_lim;

    // Define separate objects drawn onto the house
    protected Chimney chimney = new Chimney();

    protected Window window = new Window();

    protected Door door = new Door();

    /**
     * Base house construction
     */
    public House(Point min, Point max, float road_lim)
    {
        super(min, max);
        this.windowsClosed = false;
        this.road_lim = road_lim;
    }

    /**
     * Stubbed draw function
     */
    protected void drawHouse(GL2 gl){}

    /**
     * Update the drawable house object
     */
    public void update(GL2 gl)
    {
        this.drawHouse(gl);
    }

    /**
     * Toggle the windows closed
     */
    public void toggleWindows()
    {
        this.windowsClosed = !this.windowsClosed;
    }
}