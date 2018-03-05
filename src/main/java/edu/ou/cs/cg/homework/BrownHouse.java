/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

/**
 * Draws a brown house
 */
public class BrownHouse extends House
{
	// If this house has the star at the top
	private boolean hasStar;
	
	// If this house has the symbol on the door
    private boolean hasDoorSymbol;

	// Second window object not accounted for in
	// base house object
    private Window secondWindow = new Window();

	// Point to start drawing
	private Point start;

	/**
	 * Construct house object
	 * @param min: Minimum window dimensions
	 * @param max: Maximum window dimensions
	 * @param road_lim: Upper road limit
	 * @param start: Point to start drawing
	 * @param hasStar: If this house should have star at the top
	 * @param hasDoorSymbol: If this house has the door symbol
	 */
    public BrownHouse(Point min, Point max, float road_lim, Point start, boolean hasStar, boolean hasDoorSymbol)
    {
        super(min, max, road_lim);
        this.hasStar = hasStar;
        this.hasDoorSymbol = hasDoorSymbol;
        this.start = start;
    }

    /**
	 * The the brown house on the left
	 * @param gl: The GL context
	 */
    @Override
	protected void drawHouse(GL2 gl)
	{
		// Define the dimensions
		final float WIDTH = 0.5f;
		final float HEIGHT = 0.5f;

		// Define the colors
		final float[] BROWN = new float[]{153f/255f, 76f/255f, 0f/255f};
		final float[] BLACK = new float[]{0f, 0f, 0f};
		final float[] YELLOW = new float[]{1.0f, 1.0f, 0f};

		// Draw the outline and chimney
		//final Point this.start = new Point(0.1f, this.road_lim + 0.1f);
		final Point right_bot = new Point((float)this.start.getX() + WIDTH, (float)this.start.getY());
		final Point right_top = new Point((float)this.start.getX() + WIDTH, (float)this.start.getY() + HEIGHT);
		final Point left_top = new Point((float)this.start.getX(), (float)this.start.getY() + HEIGHT);
		this.chimney.update(gl, new Point((float)this.start.getX() + 0.06f, this.start.getFloatY()), false);
		Utils.drawQuad(gl, this.start, right_bot, right_top, left_top, BROWN);
		Utils.drawLine(gl, this.start, right_bot, BLACK);
		Utils.drawLine(gl, right_bot, right_top, BLACK);
		Utils.drawLine(gl, left_top, this.start, BLACK);
		// Draw the rooftop
		final Point roofPoint = new Point((float)this.start.getX() + WIDTH / 2.0f, right_top.getFloatY()+0.25f);
		Utils.drawTriangle(gl, left_top, roofPoint, right_top, BROWN);
		Utils.drawLine(gl, left_top, roofPoint, BLACK);
        Utils.drawLine(gl, roofPoint, right_top, BLACK);
        
		// Draw the door and windows
		final Point doorStart = new Point((float)this.start.getX() + 0.02f, this.start.getFloatY());
		this.door.update(gl, doorStart);
		final Point firstWindow = new Point((float)this.start.getX() + 0.22f, this.start.getFloatY() + 0.11f);
		final Point secondWindow = new Point((float)this.start.getX() + 0.22f + 0.12f + 0.02f, this.start.getFloatY() + 0.11f);
		this.window.update(gl, firstWindow, this.windowsClosed);
        this.secondWindow.update(gl, secondWindow, this.windowsClosed);
        
        if(this.hasStar)
        {
            // Draw the star at the top
            final Point startPos = new Point((float)roofPoint.getX(), (float)roofPoint.getY() - 0.15f);
            Star star = new Star(5, 1.0f, startPos);
            star.update(gl, null);
        }

        if(this.hasDoorSymbol)
        {
            // Draw the symbol on the door
            final Point doorSymbol = new Point((float)doorStart.getX()+0.06f, this.road_lim + 0.2f);
            Utils.drawCircle(gl, doorSymbol, 0.045f, 0.0f, 360f, 45, YELLOW, true);
        }
	}
}