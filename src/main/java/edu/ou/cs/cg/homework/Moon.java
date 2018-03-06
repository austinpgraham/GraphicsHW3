/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

/**
 * Draws the moon
 */
public class Moon extends DimensionedDrawable
{
	/**
	 * Construct teh moon object
	 */
    public Moon(Point min_dim, Point max_dim)
    {
        super(min_dim,  max_dim);
    }

    /**
	 *  Draw the moon object
	 * @param gl: The GL context
	 */
	private void drawMoon(GL2 gl)
	{
		final float[] WHITE = new float[]{255f, 255f, 255f};
		final float[] DARK_GRAY = new float[]{81f/255f, 82f/255f, 98f/255f};
		final Point center = new Point(this.min_dim.getFloatX() + 0.3f, 0.7f);
		final Point small_center = new Point(this.min_dim.getFloatX() + 0.35f, 0.72f);
		// Draw a background white circle, then another dark gray one on top
		Utils.drawCircle(gl, center, 0.2f, 0.0, 360.0, WHITE, false);
		Utils.drawCircle(gl, small_center, 0.16f, 0.0, 360f, DARK_GRAY, false);
	}

	/**
	 * Update the drawable object
	 */
    @Override
    public void update(GL2 gl)
    {
        this.drawMoon(gl);
    }
}