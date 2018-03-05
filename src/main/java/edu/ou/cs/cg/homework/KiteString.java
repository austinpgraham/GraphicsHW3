package edu.ou.cs.cg.homework;

import java.util.ArrayList;

import javax.media.opengl.*;

public class KiteString extends Drawable
{
    private Point start;
    private Point end;
    private float alpha = 1.0f;

    private ArrayList<Point> points;

    public KiteString(Point start, Point end)
    {
        this.start = start;
        this.end = end;
        this.points = new ArrayList<Point>();
        this.points.add(start);

    }

    public void addPoint(Point newPoint)
    {
        this.points.add(newPoint);
    }

    public void finish()
    {
        this.points.add(end);
        this.alpha = 1.0f;
    }

    public void reset()
    {
        this.points = new ArrayList<Point>();
        this.points.add(start);
        this.alpha = 0.5f;
    }

    private void drawString(GL2 gl)
    {
        final float[] GRAY = new float[]{0.8f, 0.8f, 0.8f};
        for(int i = 1; i < this.points.size(); i++)
        {
            Utils.drawLine(gl, this.points.get(i-1), this.points.get(i), GRAY, 2, this.alpha);
        }
    }

    public void update(GL2 gl)
    {
        this.drawString(gl);
    }
}