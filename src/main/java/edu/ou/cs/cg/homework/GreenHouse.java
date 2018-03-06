/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

/**
 * Draws the green house
 */
public class GreenHouse extends House
{
	/**
	 * Construct the green house
	 */
    public GreenHouse(Point min, Point max, float road_lim)
    {
        super(min, max, road_lim);
    }

    /**
	 * Draw the green house
	 * @param gl: The GL context
	 */
    @Override
	protected void drawHouse(GL2 gl)
	{
		// Define the colors
		final float[] DARK_GREEN = new float[]{85f/255f, 107f/255f, 47f/255f};
		final float[] DARK_GRAY = new float[]{0.3f, 0.3f, 0.3f};
		final float[] BLACK = new float[]{0f, 0f, 0f};

		// Draw outline and chimney
		final Point houseStart = new Point(this.min_dim.getFloatX() + 0.34f, this.road_lim + 0.01f);
		final Point right_bot = new Point((float)houseStart.getX() + WIDTH, (float)houseStart.getY());
		final Point right_top = new Point((float)houseStart.getX() + WIDTH, (float)houseStart.getY() + HEIGHT);
		final Point left_top = new Point((float)houseStart.getX(), (float)houseStart.getY() + HEIGHT);
		this.chimney.update(gl, new Point((float)houseStart.getX() + 0.35f, (float)houseStart.getY()), true);
		Utils.drawQuad(gl, houseStart, right_bot, right_top, left_top, DARK_GREEN);
		Utils.drawLine(gl, houseStart, right_bot, BLACK);
		Utils.drawLine(gl, right_bot, right_top, BLACK);
		Utils.drawLine(gl, left_top, houseStart, BLACK);

		// Draw the window and the door
		final Point windowStart = new Point((float)houseStart.getX() + 0.3f, (float)houseStart.getY() + HEIGHT / 2.0f + 0.05f);
		final Point doorStart = new Point((float)houseStart.getX()+0.1f, this.road_lim + 0.01f);
		this.window.update(gl, windowStart, this.windowsClosed);
		this.door.update(gl, doorStart);

		// Draw the roof with outline
		final Point roofSource = new Point((float)houseStart.getX() + WIDTH / 2.0f, 0.2f);
		Utils.drawTriangle(gl, roofSource, left_top, right_top, DARK_GRAY);
		Utils.drawLine(gl, roofSource, left_top, BLACK);
		Utils.drawLine(gl, roofSource, right_top, BLACK);
		Utils.drawLine(gl, right_top, left_top, BLACK);
		Utils.drawLine(gl, roofSource, new Point((float)left_top.getX() + WIDTH / 3.0f, (float)left_top.getY()), BLACK);
		Utils.drawLine(gl, roofSource, new Point((float)left_top.getX() + WIDTH / 3.0f * 2.0f, (float)left_top.getY()), BLACK);
		Utils.drawLine(gl, roofSource, new Point((float)left_top.getX() + WIDTH / 6.0f, (float)left_top.getY()), BLACK);
		Utils.drawLine(gl, roofSource, new Point((float)left_top.getX() + WIDTH / 6.0f * 5.0f, (float)left_top.getY()), BLACK);
    }
}