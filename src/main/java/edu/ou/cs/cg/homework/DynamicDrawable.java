/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

/**
 * Provide base drawable class that expects
 * a source point to be drawn.
 */
abstract class DynamicDrawable
{
    abstract public void update(GL2 gl, Point pos, boolean ...state);
}