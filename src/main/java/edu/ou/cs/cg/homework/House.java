package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

public class House extends DimensionedDrawable
{
    protected final float WIDTH = 0.5f;
    protected final float HEIGHT = 0.5f;

    protected boolean windowsClosed;

    protected float road_lim;

    protected Chimney chimney = new Chimney();

    protected Window window = new Window();

    protected Door door = new Door();

    public House(Point min, Point max, float road_lim)
    {
        super(min, max);
        this.windowsClosed = false;
        this.road_lim = road_lim;
    }

    protected void drawHouse(GL2 gl){}

    public void update(GL2 gl)
    {
        this.drawHouse(gl);
    }

    public void toggleWindows()
    {
        this.windowsClosed = !this.windowsClosed;
    }
}