/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

/**
 * Draws a star object
 */
public class Star extends DynamicDrawable
{
	// Define constant colors for the star
	private final float[] YELLOW = new float[]{1.0f, 1.0f, 0f};
	private final float[] ORANGE = new float[]{1.0f, 140f/255f, 0f};

	// The degree difference at each point
	private float degreeSkip;
	
	// Alpha value
	private float alpha;

	// Current drawing color
	private float[] color = YELLOW;

	// Center of the star
	private Point center;

	/**
	 * Construct the star
	 */
    public Star(int pointCount, float alpha, Point center)
    {
        this.degreeSkip = 360f / (float)pointCount;
		this.alpha = alpha;
		this.center = center;
    }

    /**
	 *  Draws the eight point starts at the top right.
	 * @param gl: the GL2 Context
	 * @param gl: the center of the star
	 * @param radius: Larger radius of star points
	 * @param color: Color of the star
	 * @param alpha: Fade of star color
	 */
	private void drawStar(GL2 gl, float radius, float alpha)
	{
		final float SCALE = 0.4f;
		// Enable alpha blending
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glBegin(GL.GL_TRIANGLE_FAN);
		gl.glColor4f(this.color[0], this.color[1], this.color[2], alpha);
		// Get and draw center
		gl.glVertex2d(center.getX(), center.getY());
		double centx = center.getX();
		double centy = center.getY();
		// Draw outer points
		for(double angle = 90; angle <= 450; angle+=this.degreeSkip)
		{
			double x = centx + Math.cos(Math.toRadians(angle))*radius;
			double y = centy + Math.sin(Math.toRadians(angle))*radius;
			// Scale in the smaller points in the star
			double in_x = centx + Math.cos(Math.toRadians(angle+this.degreeSkip / 2f))*radius*SCALE;
			double in_y = centy + Math.sin(Math.toRadians(angle+this.degreeSkip / 2f))*radius*SCALE;
			gl.glVertex2d(x,y);
			gl.glVertex2d(in_x, in_y);
		}
		gl.glEnd();
	}

	/**
	 * Update the darawable object
	 */
    public void update(GL2 gl, Point pos, boolean ...state)
    {
        this.drawStar(gl, 0.08f, this.alpha);
	}
	
	/**
	 * Unfocus this star
	 */
	public void setOutFocus()
	{
		this.color = YELLOW;
	}

	/**
	 * Focus this starr
	 */
	public void setInFocus()
	{
		this.color = ORANGE;
	}

	/**
	 * Set the center of the star
	 */
	public void setCenter(Point center)
	{
		this.center = center;
	}
}