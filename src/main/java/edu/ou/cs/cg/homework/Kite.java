package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

public class Kite extends DimensionedDrawable
{
    private final float ROAD_LIM;
    
    private int wingCount = 5;

    public Kite(Point min, Point max, float road_lim)
    {
        super(min, max);
        this.ROAD_LIM = road_lim;
    }

    /* Draw the kite and string
	 * @param gl: The GL context
	 */
	private void drawKite(GL2 gl)
	{
		final float[] BLUE = new float[]{70f/255f,130f/255f,180f/255f};
		final Point center = new Point(1.0f, 0.4f);
		// Draw both halves of the kite
		this.drawKitePiece(gl, center, 0.25f, 90, 180, BLUE);
		this.drawKitePiece(gl, center, 0.25f, 270, 360, BLUE);

		// Draw pieces of the string one at a time
		// final float[] GRAY = new float[]{0.8f, 0.8f, 0.8f};
		// Point start = new Point(this.max_dim.getFloatX() - 3*0.075f - 0.02f-0.5f, ROAD_LIM + 0.4f);
		// Point end = new Point((float)start.getX() - 0.2f, (float)start.getY() + 0.15f);
		// Utils.drawLine(gl, start, end, GRAY, 2.0f);
		// start = end;
		// end = new Point((float)start.getX() - 0.12f, (float)start.getY() + 0.1f);
		// Utils.drawLine(gl, start, end, GRAY, 2.0f);
		// start = end;
		// end = new Point((float)start.getX() - 0.08f, (float)start.getY() + 0.11f);
		// Utils.drawLine(gl, start, end, GRAY, 2.0f);
		// start = end;
		// end = new Point((float)start.getX() +0.02f, (float)start.getY() + 0.03f);
		// Utils.drawLine(gl, start, end, GRAY, 2.0f);
		// start = end;
		// end = new Point((float)start.getX() - 0.04f, (float)start.getY() + 0.04f);
		// Utils.drawLine(gl, start, end, GRAY, 2.0f);
		// start = end;
		// end = new Point((float)start.getX() - 0.04f, (float)start.getY() + 0.02f);
		// Utils.drawLine(gl, start, end, GRAY, 2.0f);
		// start = end;
		// Utils.drawLine(gl, start, center, GRAY, 2.0f);
	}

	/*
	 * Overload of the draw circle that will include individual 
	 * triangle outlines.
	 */
	private void drawKitePiece(GL2 gl, Point center, float radius, double start, double end, float[] color)
	{
        final float[] GRAY = new float[]{0.6f, 0.6f, 0.6f};
        float skipBy = 90f/this.wingCount;
		int len = this.wingCount + 1;
		double[] vert = new double[len*2];
		gl.glBegin(GL.GL_TRIANGLE_FAN);
		gl.glColor3f(color[0], color[1], color[2]);
		gl.glVertex2d(center.getX(), center.getY());
		double centx = center.getX();
		double centy = center.getY();
		for(double angle = start, i = 0; angle <= end; angle+=skipBy, i+= 2)
		{
			double x = centx + Math.cos(Math.toRadians(angle))*radius;
			double y = centy + Math.sin(Math.toRadians(angle))*radius;
			gl.glVertex2d(x,y);
			vert[(int)i] = x;
			vert[(int)i+1] = y;
		}
		gl.glEnd();
		for(int i = 0; i < vert.length - 2; i+=2)
		{
			final Point from = new Point((float)vert[i], (float)vert[i+1]);
			final Point to = new Point((float)vert[i+2], (float)vert[i+3]);
			Utils.drawLine(gl, from, to, GRAY, 5.0f, 0.3f);
			Utils.drawLine(gl, center, from, GRAY, 5.0f, 0.3f);
			Utils.drawLine(gl, center, to, GRAY, 5.0f, 0.3f);
		}
	}

    public void update(GL2 gl)
    {
        this.drawKite(gl);
    }

    public void setWingCount(int count)
    {
        this.wingCount = count;
    }
}