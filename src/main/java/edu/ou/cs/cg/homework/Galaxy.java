/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

/**
 * Draw the background galaxy.
 */
public class Galaxy extends Drawable
{
	// The number of stars in the galaxy
	private int STAR_COUNT = 0;

	// The amount of red in the color of the stars
	private float redAmount = 0.8f;

	// If the amount of red is decreasing
	private boolean decreasing = true;

	/**
	 * The following three functions map the equations
	 * for each dimension of a position of a star at
	 * a given time.
	 */
	private double dx(double x, double y, double z)
	{
		return -10*(x - y);
	}

	private double dy(double x, double y, double z)
	{
		return -x*z + 28*x - y;
	}

	private double dz(double x, double y, double z)
	{
		return x*y - 8*z/3;
    }
    
    /**
	 * Draw galazy using Lorenz equations
	 * @param gl: The GL context
	 */
	private void drawGalaxy(GL2 gl)
	{
		// Start point for galaxy
		float x = 0.5f;
		float y = 0.9f;
		float z = 1f;
		// Draw as points 
		gl.glBegin(GL2.GL_POINTS);
		gl.glColor3f(this.redAmount, 0.5f, 0.5f);
		// Time lapse
		float dt = 0.01f;
		// Draw 20000 points
		for(int i=0;i<this.STAR_COUNT;i++)
		{
			// Define new point as change from 
			// previous point
			x += (float)dx(x,y,z)*dt;
			y += (float)dy(x,y,z)*dt;
			z += (float)dz(x,y,z)*dt;
			gl.glVertex2f(0.05f*x, -0.05f*y);
		}
		gl.glEnd();
    }
	
	/**
	 * Update the galaxy object
	 * @param gl: The GL context
	 */
    public void update(GL2 gl)
    {
		// Update the star count
		this.STAR_COUNT +=10;
		if (this.STAR_COUNT >= 20000)
		{
			this.STAR_COUNT = 0;
		}

		// Update the amount of red in the stars
		if(this.decreasing)
		{
			this.redAmount -= 0.001f;
		}
		else
		{
			this.redAmount += 0.001f;
		}

		// Switch directions if necessary
		if(this.redAmount < 0f || this.redAmount > 0.8f)
		{
			this.decreasing = !this.decreasing;
		}
		
		// Draw the galaxy
        this.drawGalaxy(gl);
    }
}