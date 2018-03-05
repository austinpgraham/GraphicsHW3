package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

public class Star extends DynamicDrawable
{
    private float degreeSkip;
    private float alpha;

    public Star(int pointCount, float alpha)
    {
        this.degreeSkip = 360f / (float)pointCount;
        this.alpha = alpha;
    }

    /* Draws the eight point starts at the top right.
	 * @param gl: the GL2 Context
	 * @param gl: the center of the star
	 * @param radius: Larger radius of star points
	 * @param color: Color of the star
	 * @param alpha: Fade of star color
	 */
	private void drawStar(GL2 gl, Point center, float radius, float[] color, float alpha)
	{
		final float SCALE = 0.4f;
		// Enable alpha blending
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glBegin(GL.GL_TRIANGLE_FAN);
		gl.glColor4f(color[0], color[1], color[2], alpha);
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

    public void update(GL2 gl, Point pos, boolean ...state)
    {
        final float[] YELLOW = new float[]{1.0f, 1.0f, 0f};
        this.drawStar(gl, pos, 0.08f, YELLOW, this.alpha);
    }
}