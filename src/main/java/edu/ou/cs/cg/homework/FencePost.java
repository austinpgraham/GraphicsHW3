package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

public class FencePost extends DynamicDrawable
{
    private final float WIDTH = 0.075f;
    private float HEIGHT = 0.4f;

    private boolean reflected;

    public FencePost(boolean reflected)
    {
        this.reflected = reflected;
    }

    /* Draw a fence post
	 * @param gl: The GL context
	 * @param source: The bottom left corner
	 */
	private void drawFencePost(GL2 gl, Point source)
	{
		// Define the colors
		final float[] BEIGE = new float[]{0.9f, 0.837f, 0.735f};
		final float[] BLACK = new float[]{0f, 0f, 0f};

		// Define and outline
		final Point start = (Point)source;
		final Point right_bot = new Point((float)start.getX() + WIDTH, (float)start.getY());
		final Point right_top = new Point((float)start.getX() + WIDTH, (float)start.getY() + HEIGHT);
		final Point left_top = new Point((float)start.getX(), (float)right_top.getY() - 0.05f);
		Utils.drawQuad(gl, start, right_bot, right_top, left_top, BEIGE);
		Utils.drawLine(gl, start, right_bot, BLACK);
		Utils.drawLine(gl, right_bot, right_top, BLACK);
		Utils.drawLine(gl, right_top, left_top, BLACK);
		Utils.drawLine(gl, left_top, start, BLACK);
    }

    /*
	 * Does the same thing as the above method, but
	 * reflects the fence post.
	 */
	private void drawReflectedFencePost(GL2 gl, Point source)
	{
		final float[] BEIGE = new float[]{0.9f, 0.837f, 0.735f};
		final float[] BLACK = new float[]{0f, 0f, 0f};

		final Point start = (Point)source;
		final Point right_bot = new Point((float)start.getX() + WIDTH, (float)start.getY());
		final Point right_top = new Point((float)start.getX() + WIDTH, (float)start.getY() + HEIGHT - 0.05f);
		final Point left_top = new Point((float)start.getX(), (float)start.getY() + HEIGHT);
		Utils.drawQuad(gl, start, right_bot, right_top, left_top, BEIGE);
		Utils.drawLine(gl, start, right_bot, BLACK);
		Utils.drawLine(gl, right_bot, right_top, BLACK);
		Utils.drawLine(gl, right_top, left_top, BLACK);
		Utils.drawLine(gl, left_top, start, BLACK);
	}
	
	/**
	 * Update the drawable object
	 */
    public void update(GL2 gl, Point pos, boolean ...state)
    {
        if (!this.reflected)
        {
            this.drawFencePost(gl, pos);
        }
        else 
        {
            this.drawReflectedFencePost(gl, pos);
        }
    }

	/**
	 * Adjust height of the fence post
	 * only if the adjustment results in
	 * a positive height.
	 */
    public void adjustHeight(float amount)
    {
		if(this.HEIGHT + amount - 0.05f > 0)
		{
			this.HEIGHT += amount;
		}
    }
}