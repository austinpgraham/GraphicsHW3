/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

/**
 * Draws a window object
 */
public class Window extends DynamicDrawable
{
	// Dimensions of window drawable objects
    private float DIM = 0.12f;
    private float LINE_WIDTH = 3.0f;

    /**
	 *  Draw a window object
	 * @param gl: The GL context
	 * @param pos: The bottom left corner
	 */
	private void drawWindow(GL2 gl, Point pos, boolean isClosed)
	{
		// Define the colors
		final float[] LIGHT_PURPLE = new float[]{230f/255f, 230f/255f, 250f/255f};
		final float[] YELLOW = new float[]{1f, 250f/255f, 205f/255f};
		final float[] BLACK = new float[]{0f, 0f, 0f};

		// Draw the outline of the window
		final Point start = (Point)pos;
		final Point right_bot = new Point((float)start.getX() + DIM, (float)start.getY());
		final Point right_top = new Point((float)start.getX() + DIM, (float)start.getY() + DIM);
		final Point left_top = new Point((float)start.getX(), (float)start.getY()+DIM);
		final Point top_middle = new Point((float)left_top.getX() + DIM / 2.0f, (float)left_top.getY());
		final Point bot_middle = new Point((float)start.getX() + DIM / 2.0f, (float)start.getY());
		final Point left_middle = new Point((float)left_top.getX(), (float)left_top.getY() - DIM / 2.0f);
		final Point right_middle = new Point((float)right_top.getX(), (float)right_top.getY() - DIM / 2.0f);
		Utils.drawQuad(gl, start, right_bot, right_top, left_top, LIGHT_PURPLE);
		// If the window is not closed, draw the light in the middle
		if(!isClosed)
		{
			Utils.drawTriangle(gl, start, right_bot, top_middle, YELLOW);
			Utils.drawLine(gl, start, top_middle, BLACK);
			Utils.drawLine(gl, top_middle, right_bot, BLACK);
		}
		Utils.drawLine(gl, start, right_bot, BLACK, LINE_WIDTH);
		Utils.drawLine(gl, right_bot, right_top, BLACK, LINE_WIDTH);
		Utils.drawLine(gl, right_top, left_top, BLACK, LINE_WIDTH);
		Utils.drawLine(gl, left_top, start, BLACK, LINE_WIDTH);
		Utils.drawLine(gl, left_middle, right_middle, BLACK, LINE_WIDTH);
		Utils.drawLine(gl, top_middle, bot_middle, BLACK, LINE_WIDTH);
    }
	
	/**
	 * Update the drawable object
	 */
    public void update(GL2 gl, Point pos, boolean ...state)
    {
        this.drawWindow(gl, pos, state[0]);
    }
}