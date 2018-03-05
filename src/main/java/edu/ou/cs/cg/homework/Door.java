/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

/**
 * Draws a door object
 */
public class Door extends DynamicDrawable
{
    private float HEIGHT = 0.25f;
    private float WIDTH = 0.13f;

    /** 
	 * Draw a door
	 * @param gl: The GL context
	 * @param pos: The bottom left corner
	 */
	private void drawDoor(GL2 gl, Point pos)
	{
		// Define the colors
		final float[] BLACK = new float[]{0f, 0f, 0f};
		final float[] LIGHT_BROWN = new float[]{205f/255f, 133f/255f, 63f/255f};
		final float[] LIGHT_GRAY = new float[]{0.75f, 0.75f, 0.75f};

		// Outline and draw the door
		final Point start = (Point)pos;
		final Point right_bot = new Point((float)start.getX() + WIDTH, (float)start.getY());
		final Point top_right = new Point((float)start.getX() + WIDTH, (float)start.getY() + HEIGHT);
		final Point top_left = new Point((float)start.getX(), (float)start.getY() + HEIGHT);
		final Point knob_center = new Point((float)start.getX() + 0.015f, (float)start.getY() + HEIGHT / 2.0f);
		Utils.drawQuad(gl, start, right_bot, top_right, top_left, LIGHT_BROWN);
		Utils.drawLine(gl, start, right_bot, BLACK);
		Utils.drawLine(gl, right_bot, top_right, BLACK);
		Utils.drawLine(gl, top_right, top_left, BLACK);
		Utils.drawLine(gl, top_left, start, BLACK);
		Utils.drawCircle(gl, knob_center, 0.01f, 0.0f, 360f, LIGHT_GRAY, true);
	}

	/**
	 * Update the object
	 */
    public void update(GL2 gl, Point pos, boolean...state)
    {
        this.drawDoor(gl, pos);;
    }
}