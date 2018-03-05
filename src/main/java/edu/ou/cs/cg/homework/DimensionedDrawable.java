package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

public class DimensionedDrawable extends Drawable
{
    protected Point min_dim;
    protected Point max_dim;

    public DimensionedDrawable(Point min_dim, Point max_dim)
    {
        this.min_dim = min_dim;
        this.max_dim = max_dim;
    }

    public void update(GL2 gl){}
}