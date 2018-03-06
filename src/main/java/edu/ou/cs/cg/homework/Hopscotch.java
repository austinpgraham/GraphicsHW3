/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

/**
 * Draws the hopscotch drawable object
 */
public class Hopscotch extends Drawable
{
	// the limit 
    private float lim;
    private float startX;

    private final float DELTA = 0.1f;
    private final float OFFSET = 0.015f;
	private final float HEIGHT = DELTA;
	private final float PART_HEIGHT = 0.13f;

	private final float ROAD_LIM;

    public Hopscotch(float road_lim)
    {
		this.lim = road_lim;
		this.ROAD_LIM = road_lim;
        this.startX = 0f;
    }

    /* Draw the hopscotch pattern on the road
	 * @param gl: GL context
	 */
	private void drawHopscotch(GL2 gl)
	{
		// Define the color for the pattern
		final float[] outline = new float[]{1.0f, 1.0f, 1.0f};
		final float[] fill = new float[]{0.9f, 0.837f, 0.735f};

		// Draw the first rectangles
		final Point startRect = new Point(this.startX, this.lim - 0.3f);
		this.drawHopRect(gl, startRect, outline, fill);
		this.drawHopRect(gl, new Point((float)startRect.getX() + DELTA, (float)startRect.getY()), outline, fill);
		final Point endHalf = new Point((float)startRect.getX() + DELTA*2, (float)startRect.getY());
		this.drawHopRect(gl, endHalf, outline, fill);

		// Draw the two in the middle - first time
		final Point endHalfBot =  new Point((float)endHalf.getX() + DELTA, (float)endHalf.getY());
		final Point endHalfTop = new Point((float)endHalf.getX() + DELTA + OFFSET,(float)endHalf.getY() + HEIGHT);
		final Point startHalf = this.computeStartDiagRect(endHalfBot, endHalfTop);
		this.drawHopRect(gl, startHalf, outline, fill);
		this.drawHopRect(gl, new Point((float)startHalf.getX() - OFFSET, (float)startHalf.getY() - HEIGHT), outline, fill);

		// Draw the rect in the middle
		final Point endSecondHalf = new Point((float)startRect.getX() + DELTA*4, (float)startRect.getY());
		this.drawHopRect(gl, endSecondHalf, outline, fill);

		// Draw the second two in the middle
		final Point endSecHalfBot =  new Point((float)endSecondHalf.getX() + DELTA, (float)endSecondHalf.getY());
		final Point endSecHalfTop = new Point((float)endSecondHalf.getX() + DELTA + OFFSET,(float)endSecondHalf.getY() + HEIGHT);
		final Point startSecHalf = this.computeStartDiagRect(endSecHalfBot, endSecHalfTop);
		this.drawHopRect(gl, startSecHalf, outline, fill);
		this.drawHopRect(gl, new Point((float)startSecHalf.getX() - OFFSET, (float)startSecHalf.getY() - HEIGHT), outline, fill);

		// Draw last rect
		this.drawHopRect(gl, new Point((float)startRect.getX() + DELTA*6, (float)startRect.getY()), outline, fill);
	}

    /* Draw a single hopscotch rectangle
	 * @param gl: the GL context
	 * @param sourcePoint: Bottom left point in rect
	 * @param outlineColor: outline color
	 * @param fillColor: Fill color of rect
	 */
	private void drawHopRect(GL2 gl, Point sourcePoint, float[] outlineColor, float[] fillColor)
	{
		// Define dimensions
		final float DELTA = 0.1f;
		final float OFFSET = 0.015f;
		final float HEIGHT = DELTA;
		final float LINE_WIDTH = 3.0f;

		// Define vertices and outline
		final Point one = (Point)sourcePoint;
		final Point two =  new Point((float)one.getX() + DELTA, (float)one.getY());
		final Point three = new Point((float)one.getX() + DELTA + OFFSET,(float)one.getY() + HEIGHT);
		final Point four = new Point((float)one.getX() + OFFSET, (float)one.getY() + HEIGHT);
		Utils.drawQuad(gl, one, two, three, four, fillColor);
		Utils.drawLine(gl, one, two, outlineColor, LINE_WIDTH);
		Utils.drawLine(gl, two, three, outlineColor, LINE_WIDTH);
		Utils.drawLine(gl, three, four, outlineColor, LINE_WIDTH);
		Utils.drawLine(gl, four, one, outlineColor, LINE_WIDTH);
	}

    /*
	 * Compute the point to start the stacked hopscotch rect.
	 */
	private Point computeStartDiagRect(Point start, Point end)
	{
		float rise = (float)(end.getY() - start.getY()) / 2.0f;
		float run = (float)(end.getX() - start.getX()) / 2.0f;
		return new Point((float)start.getX() + run, (float)start.getY() + rise);
	}

	/**
	 * Update the hopscotch drawable object
	 */
    public void update(GL2 gl)
    {
        this.drawHopscotch(gl);
    }

	/**
	 * Increment the x position by the given amount
	 * @param amount: The amount to increment by
	 */
    public void incrementBy(float amount)
    {
        this.startX += amount;
	}
	
	/**
	 * Increment the y position by the given amount
	 * @param amount: The amount to increment by
	 */
	public void incrementYBy(float amount)
	{
		this.lim += amount;
	}

	/**
	 * If the object is within the upper coordinates
	 */
	public boolean isWithinUpperLimit()
	{
		return this.lim < this.ROAD_LIM + this.PART_HEIGHT;
	}

	/**
	 * If the object is within the lower coordinates
	 */
	public boolean isWithinLowerLimit()
	{
		return this.lim > -1f + this.PART_HEIGHT*3;
	}
}