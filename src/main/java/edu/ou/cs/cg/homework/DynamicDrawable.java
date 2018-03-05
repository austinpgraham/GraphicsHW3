package edu.ou.cs.cg.homework;

import javax.media.opengl.*;

abstract class DynamicDrawable
{
    abstract public void update(GL2 gl, Point pos, boolean ...state);
}