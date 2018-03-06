/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

/**
 * Define the drawable Kite Object
 */
public class Kite extends DimensionedDrawable
{
	// Number of subsections within the kite
	private int wingCount = 5;

	// Alpha value of kite
	private float alpha = 1.0f;

	// The center of the kite
	private Point center = new Point(10.0f, 10.4f);

	/**
	 * Construct the Kite object
	 */
    public Kite(Point min, Point max)
    {
        super(min, max);
    }

    /**
	 *  Draw the kite and string
	 * @param gl: The GL context
	 */
	private void drawKite(GL2 gl)
	{
		final float[] BLUE = new float[]{70f/255f,130f/255f,180f/255f};
		// Draw both halves of the kite
		this.drawKitePiece(gl, this.center, 0.25f, 90, 180, BLUE, this.alpha);
		this.drawKitePiece(gl, this.center, 0.25f, 270, 360, BLUE, this.alpha);
	}

	/*
	 * Overload of the Utils draw circle that will include individual 
	 * triangle outlines.
	 */
	private void drawKitePiece(GL2 gl, Point center, float radius, double start, double end, float[] color, float alpha)
	{
        final float[] GRAY = new float[]{0.6f, 0.6f, 0.6f};
        float skipBy = 90f/this.wingCount;
		int len = this.wingCount + 1;
		double[] vert = new double[len*2];
		gl.glBegin(GL.GL_TRIANGLE_FAN);
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glColor4f(color[0], color[1], color[2], this.alpha);
		gl.glVertex2d(center.getX(), center.getY());
		double centx = center.getX();
		double centy = center.getY();
		// Draw each subsection based on teh numberof wings specified
		for(double angle = start, i = 0; angle <= end; angle+=skipBy, i+= 2)
		{
			double x = centx + Math.cos(Math.toRadians(angle))*radius;
			double y = centy + Math.sin(Math.toRadians(angle))*radius;
			gl.glVertex2d(x,y);
			// Store points to outline
			vert[(int)i] = x;
			vert[(int)i+1] = y;
		}
		gl.glEnd();
		// Outline each piece of the kite
		for(int i = 0; i < vert.length - 2; i+=2)
		{
			final Point from = new Point((float)vert[i], (float)vert[i+1]);
			final Point to = new Point((float)vert[i+2], (float)vert[i+3]);
			Utils.drawLine(gl, from, to, GRAY, 5.0f, 0.3f);
			Utils.drawLine(gl, center, from, GRAY, 5.0f, 0.3f);
			Utils.drawLine(gl, center, to, GRAY, 5.0f, 0.3f);
		}
	}

	/**
	 * Update the drawable kite object
	 */
    public void update(GL2 gl)
    {
        this.drawKite(gl);
    }

	/**
	 * Set the number of wings in the kite
	 * @param count: Number of wings
	 */
    public void setWingCount(int count)
    {
        this.wingCount = count;
	}
	
	/**
	 * Set the center of the kite
	 * @param newCenter: The new center of the kite
	 */
	public void setCenter(Point newCenter)
	{
		this.center = newCenter;
	}

	/**
	 * Set the alpha of the kite
	 * @param newAlpha: The new alpha value
	 */
	public void setAlpha(float newAlpha)
	{
		this.alpha = newAlpha;
	}
}