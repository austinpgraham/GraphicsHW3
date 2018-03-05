package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

public class Background extends DimensionedDrawable
{
    private float grass_lim;
    private float road_lim;

    private Galaxy galaxy;
    private Road road;

    public Background(Point min_dim, Point max_dim, float grass_lim, float road_lim)
    {
        super(min_dim, max_dim);
        this.grass_lim = grass_lim;
        this.road_lim = road_lim;

        this.galaxy = new Galaxy();
        this.road = new Road(this.min_dim, this.max_dim);
    }

    /* Draw the sky background
	 * @param gl: The GL context
	 */
	private void drawSky(GL2 gl)
	{
		final Point one = new Point(this.min_dim.getFloatX(), this.max_dim.getFloatY());
		final Point two = new Point(this.max_dim.getFloatX(), this.max_dim.getFloatY());
		final Point three = new Point(this.max_dim.getFloatX(), this.grass_lim);
		final Point four = new Point(this.min_dim.getFloatX(), this.grass_lim);
		final float[] purple = new float[]{12f/255f, 18f/255f, 30f/255f};
		final float[] green = new float[]{144f/255f, 129f/255f, 100f/255f};
		// Draw with gradient
		Utils.drawQuadGradient(gl, one, two, three, four, purple, green);
    }
    
    /* Draw the grass background
	 * @param gl: GL context
	 */
	private void drawGrass(GL2 gl)
	{
		// Define points and draw with gradient
		final Point one = new Point(this.min_dim.getFloatX(), this.grass_lim);
		final Point two = new Point(this.max_dim.getFloatX(), this.grass_lim);
		final Point three = new Point(this.max_dim.getFloatX(), this.road_lim);
		final Point four = new Point(this.min_dim.getFloatX(), this.road_lim);
		final float[] purple = new float[]{81f/255f, 65f/255f, 63f/255f};
		final float[] green = new float[]{98f/255f, 142f/255f, 84f/255f};
		Utils.drawQuadGradient(gl, one, two, three, four, purple, green);
    }
    
    public Galaxy getGalaxy()
    {
        return this.galaxy;
    }

    public Road getRoad()
    {
        return this.road;
    }

    @Override
    public void update(GL2 gl)
    {
        this.drawSky(gl);

        this.galaxy.update(gl);

        this.drawGrass(gl);

        this.road.update(gl);
    }
}