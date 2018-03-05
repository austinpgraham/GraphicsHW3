package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

public class Galaxy extends Drawable
{
	private int STAR_COUNT = 0;
	private float redAmount = 0.8f;
	private boolean decreasing = true;

    //*********************************************//
	// Three change equations for Lorenz equations //
	//*********************************************//
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
    
    /* Draw galazy using Lorenz equations
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
    
    public void update(GL2 gl)
    {
		this.STAR_COUNT +=10;
		if (this.STAR_COUNT >= 20000)
		{
			this.STAR_COUNT = 0;
		}

		if(this.decreasing)
		{
			this.redAmount -= 0.001f;
		}
		else
		{
			this.redAmount += 0.001f;
		}

		if(this.redAmount < 0f || this.redAmount > 0.8f)
		{
			this.decreasing = !this.decreasing;
		}
		
        this.drawGalaxy(gl);
    }
}