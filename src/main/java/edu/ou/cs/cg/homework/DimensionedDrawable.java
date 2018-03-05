/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

/**
 * Provides a base for a drawable component
 * that knows of the window dimensions
 */
public class DimensionedDrawable extends Drawable
{
    // Min window dimensions
    protected Point min_dim;

    // Max window dimensions
    protected Point max_dim;

    /**
     * Construct object
     * @param min_dim: Minimum dimensions
     * @param max_dim: Maximum dimensions
     */
    public DimensionedDrawable(Point min_dim, Point max_dim)
    {
        this.min_dim = min_dim;
        this.max_dim = max_dim;
    }

    /**
     * Update the drawn object.
     */
    public void update(GL2 gl){}
}