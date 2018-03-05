/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

/**
 * Draws a chimney with or without smoke.
 * a bit hardcoded, but works for the
 * time being
 */
public class Chimney extends DynamicDrawable
{
    private final float HEIGHT = 0.77f;
    private final float WIDTH = 0.1f;

    /**
	 * Draw a smoke column
	 * @param gl: The GL context
	 * @param left: The left start
	 * @param right: The right start
	 */
	private void drawSmoke(GL2 gl, Point left, Point right)
	{
		double startX_left = left.getX();
		double startY_left = left.getY();
		double startX_right = right.getX();
		double startY_right = right.getY();
		gl.glBegin(GL2.GL_QUAD_STRIP);
		gl.glColor3f(0.5f, 0.5f, 0.5f);
		gl.glVertex2d(left.getX(), left.getY());
		gl.glVertex2d(right.getX(), right.getY());
		gl.glVertex2d(startX_left, startY_left + 0.05f);
		gl.glVertex2d(startX_right, startY_right + 0.05f);
		gl.glVertex2d(startX_left + 0.03f, startY_left + 0.07f);
		gl.glVertex2d(startX_right, startY_right + 0.07f);
		gl.glVertex2d(startX_left + 0.04f, startY_left + 0.1f);
		gl.glVertex2d(startX_right + 0.03f, startY_right + 0.1f);
		gl.glVertex2d(startX_left + 0.05f, startY_left + 0.13f);
		gl.glVertex2d(startX_right + 0.02f, startY_right + 0.13f);
		gl.glVertex2d(startX_left + 0.01f, startY_left + 0.17f);
		gl.glVertex2d(startX_right + 0.03f, startY_right + 0.17f);
		gl.glVertex2d(startX_left + 0.03f, startY_left + 0.2f);
		gl.glVertex2d(startX_right + 0.02f, startY_right + 0.2f);
		gl.glVertex2d(startX_left + 0.05f, startY_left + 0.24f);
		gl.glVertex2d(startX_right + 0.03f, startY_right + 0.24f);
		gl.glVertex2d(startX_left + 0.07f, startY_left + 0.26f);
		gl.glVertex2d(startX_right + 0.04f, startY_right + 0.26f);
		gl.glEnd();
	}

    /**
	 * Draw a chimney
	 * @param gl: The GL context
	 * @param po: Bottom left corner
	 * @param smoke: Draw with smoke
	 */
	private void drawChimney(GL2 gl, Point pos, boolean smoke)
	{
		// Define colors
		final float[] BROWN = new float[]{128f/255f, 0f/255f, 0f/255f};
		final float[] BLACK = new float[]{0f, 0f, 0f};

		// Define outline and draw smoke if necessary
		final Point start = (Point)pos;
		final Point bot_right = new Point((float)start.getX() + WIDTH, (float)start.getY());
		final Point top_right = new Point((float)start.getX() + WIDTH, (float)start.getY() + HEIGHT);
		final Point top_left = new Point((float)start.getX(), (float)start.getY() + HEIGHT);
		if(smoke)
		{
			this.drawSmoke(gl, new Point((float)top_left.getX()+0.01f, (float)top_left.getY()), new Point((float)top_right.getX()-0.01f, (float)top_right.getY()));
		}
		Utils.drawQuad(gl, start, bot_right, top_right, top_left, BROWN);
		Utils.drawLine(gl, start, bot_right, BLACK);
		Utils.drawLine(gl, bot_right, top_right, BLACK);
		Utils.drawLine(gl, top_right, top_left, BLACK);
		Utils.drawLine(gl, top_left, start, BLACK);
	}

	/**
	 * Updates the drawing of the chimney
	 */
    public void update(GL2 gl, Point pos, boolean ...state)
    {
        this.drawChimney(gl, pos, state[0]);
    }
}