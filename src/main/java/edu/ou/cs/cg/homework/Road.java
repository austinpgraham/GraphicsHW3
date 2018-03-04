package edu.ou.cs.cg.homework;

import java.awt.geom.*;
import javax.media.opengl.*;

public class Road extends Drawable
{
    private Point min_dim;
    private Point max_dim;
    private final float HEIGHT;
    private final float DELTA;
    private final float OFFSET;

    public Road(Point min, Point max)
    {
        this.HEIGHT = 0.4f;
        this.DELTA = 0.2f;
        this.OFFSET = 0.05f;
        this.min_dim = min;
        this.max_dim = max;
    }

    /* Draw the road
	 * @param gl: The GL context
	 */
	private void drawRoad(GL2 gl)
	{
		// Constants for road color
		final float GRAY_VAL = 0.61f;
		// Start for the road lines
		float rootX = this.min_dim.getFloatX() - OFFSET;
		float rootY = -1.0f;

		// Draw gray background to the road
		final Point one = new Point(rootX, rootY);
		final Point two = new Point(this.max_dim.getFloatX(), rootY);
		final Point three = new Point(this.max_dim.getFloatX(), this.min_dim.getFloatY() + HEIGHT);
		final Point four = new Point(rootX, this.min_dim.getFloatY() + HEIGHT);
		final float[] color = new float[]{GRAY_VAL, GRAY_VAL, GRAY_VAL};
		Utils.drawQuad(gl, one, two, three, four, color);

		// Draw the lines on the road
		final float[] white = new float[]{1.0f, 1.0f, 1.0f};
		Utils.drawLine(gl, new Point(rootX, -1.0f + HEIGHT), new Point(this.max_dim.getFloatX(), -1.0f + HEIGHT), white);
		Utils.drawLine(gl, new Point(rootX, -1.0f), new Point(this.max_dim.getFloatX(), -1.0f), white);
		for(float x = rootX; x < this.max_dim.getFloatX() + 0.15f; x += DELTA)
		{
			Utils.drawLine(gl, new Point(x, -1.0f), new Point(x + OFFSET, -1.0f + HEIGHT), white);
		}
    }
    
    public void update(GL2 gl)
    {
        this.drawRoad(gl);
    }

    public float getHeight()
    {
        return this.HEIGHT;
    }

    public float getDelta()
    {
        return this.DELTA;
    }
}