//******************************************************************************
// Copyright (C) 2016 University of Oklahoma Board of Trustees.
//******************************************************************************
// Last modified: Tue Feb  20 14:12 2016 by Chris Weaver
//******************************************************************************
// Major Modification History:
//
// 20180220 [graham]:	Original file.
//
//******************************************************************************
// Notes: Imitates the image given in the homework 2 description.
//
//******************************************************************************

package edu.ou.cs.cg.homework;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;

//******************************************************************************

/**
 * The <CODE>Homework02</CODE> class.<P>
 *
 * @author  Austin Graham
 * @version %I%, %G%
 */
public final class Homework03
	implements GLEventListener
{
	//**********************************************************************
	// Public Class Members
	//**********************************************************************

	public static final GLU		GLU = new GLU();
	public static final GLUT	GLUT = new GLUT();
	public static final Random	RANDOM = new Random();

	//**********************************************************************
	// Private Members
	//**********************************************************************

	// State (internal) variables
	private int				k = 0;		// Just an animation counter

	private int				w;			// Canvas width
	private int				h;			// Canvas height
	private TextRenderer	renderer;

	// Outline Bounds of the window
	private static final Point MAX = new Point(2.0f, 1.0f);
	private static final Point MIN = new Point(-2.0f, -1.0f);

	// Outline background limits
	private static final float ROAD_LIM = -0.6f;
	private static final float GRASS_LIM = -0.05f;

	private static Background background = new Background(MIN, MAX, GRASS_LIM, ROAD_LIM);
	private static Hopscotch hop = new Hopscotch(ROAD_LIM);
	private static Moon moon = new Moon(MIN, MAX);
	private static GreenHouse greenHouse = new GreenHouse(MIN, MAX, ROAD_LIM);
	private static BrownHouse leftBrownHouse = new BrownHouse(MIN, MAX, ROAD_LIM, new Point(0.1f, ROAD_LIM + 0.1f), true, false);
	private static BrownHouse rightBrownHouse = new BrownHouse(MIN, MAX, ROAD_LIM, new Point(MAX.getFloatX() - 2*0.075f - 0.5f - 0.02f, ROAD_LIM + 0.01f), false, true);
	private static FenceLine farLeft = new FenceLine();
	private static FenceLine leftGreen = new FenceLine();
	private static FenceLine rightLeftSide = new FenceLine();
	private static FenceLine farRight = new FenceLine();
	private static FenceLine leftSecond = new FenceLine();
	private static ArrayList<FenceLine> fences = new ArrayList<FenceLine>();

	//**********************************************************************
	// Main
	//**********************************************************************

	public static void main(String[] args)
	{
		GLProfile		profile = GLProfile.getDefault();
		GLCapabilities	capabilities = new GLCapabilities(profile);
		GLCanvas		canvas = new GLCanvas(capabilities);

		JFrame 			frame = new JFrame("Homework03");

		canvas.setPreferredSize(new Dimension(1100, 550));

		frame.setBounds(50, 50, 1050, 500);
		frame.getContentPane().add(canvas);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

		canvas.addGLEventListener(new Homework03());
		canvas.addKeyListener(new EnvironmentKeyListener(hop, background.getRoad(), greenHouse, leftBrownHouse, rightBrownHouse, fences));

		FPSAnimator		animator = new FPSAnimator(canvas, 60);

		animator.start();

		for(int i = 0; i < 4; i++)
		{
			farLeft.addFencePost(new FencePost(false));
			leftGreen.addFencePost(new FencePost(false));
			leftSecond.addFencePost(new FencePost(false));
			rightLeftSide.addFencePost(new FencePost(false));
			rightLeftSide.addFencePost(new FencePost(true));
		}

		farRight.addFencePost(new FencePost(false));
		farRight.addFencePost(new FencePost(true));

		fences.add(farLeft);
		fences.add(leftGreen);
		fences.add(rightLeftSide);
		fences.add(farRight);
		fences.add(leftSecond);
	}

	//**********************************************************************
	// Override Methods (GLEventListener)
	//**********************************************************************

	public void		init(GLAutoDrawable drawable)
	{
		w = drawable.getWidth();
		h = drawable.getHeight();

		renderer = new TextRenderer(new Font("Serif", Font.PLAIN, 18),
									true, true);
	}

	public void		dispose(GLAutoDrawable drawable)
	{
		renderer = null;
	}

	public void		display(GLAutoDrawable drawable)
	{
		update();
		render(drawable);
	}

	public void		reshape(GLAutoDrawable drawable, int x, int y, int w, int h)
	{
		this.w = w;
		this.h = h;
	}

	//**********************************************************************
	// Private Methods (Rendering)
	//**********************************************************************

	private void	update()
	{
		k++;									// Counters are useful, right?
	}

	private void	render(GLAutoDrawable drawable)
	{
		GL2		gl = drawable.getGL().getGL2();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);		// Clear the buffer

		// Scale the GL coordinate system to the window
		setProjection(gl);

		background.update(gl);

		hop.update(gl);

		moon.update(gl);

		greenHouse.update(gl);

		// Draw far left fence
		for(float x = MIN.getFloatX() + 0.02f, count = 0; count < 4; x += 0.075f, count++)
		{
			final Point start = new Point(x, ROAD_LIM + 0.01f);
			farLeft.getPosts().get((int)count).update(gl, start);
		}

		// Draw right side fence against the left side of the green house
		for(float x = MIN.getFloatX() + 0.86f, count = 0; count < 4; x += 0.075f, count++)
		{
			final Point start = new Point(x, ROAD_LIM + 0.01f);
			leftGreen.getPosts().get((int)count).update(gl, start);
		}

		// Draw the right side fence on the other side of the right house
		for(float x = MIN.getFloatX() + 1.18f, count = 0; count < 4; x += 0.075f, count++)
		{
			final Point start = new Point(x, ROAD_LIM + 0.01f);
			leftSecond.getPosts().get((int)count).update(gl, start);
		}

		// Draw far right fence
		final Point first = new Point(MAX.getFloatX() - 2*0.075f - 0.02f, ROAD_LIM + 0.01f);
		farRight.getPosts().get(0).update(gl, first);
		final Point reflected = new Point(MAX.getFloatX() - 0.075f - 0.02f, ROAD_LIM + 0.01f);
		farRight.getPosts().get(1).update(gl, reflected);

		leftBrownHouse.update(gl);

		rightBrownHouse.update(gl);

		// Draw the groups of reflected fence posts
		int post = 0;
		for(float x = MAX.getFloatX() - 3*0.075f - 0.02f-0.5f; x > MAX.getFloatX() - 10*0.075f - 0.02f-0.5f; x -= 2*0.075f)
		{
			final Point first_left = new Point(x - 0.075f, ROAD_LIM + 0.01f);
			rightLeftSide.getPosts().get(post).update(gl, first_left);
			final Point reflected_left = new Point(x, ROAD_LIM + 0.01f);
			rightLeftSide.getPosts().get(post + 1).update(gl, reflected_left);
			post += 2;
		}

		// // Draw eight point starts in the sky
		// final float[] YELLOW = new float[]{1.0f, 1.0f, 0f};
		// final Point2D.Float star1 = new Point2D.Float(1.0f, MAX.getFloatY() - 0.2f);
		// this.drawEightPointStar(gl, star1, 0.08f, YELLOW, 1f);
		// final Point2D.Float star2 = new Point2D.Float(1.3f, MAX.getFloatY() - 0.3f);
		// this.drawEightPointStar(gl, star2, 0.08f, YELLOW, 1f);
		// final Point2D.Float star3 = new Point2D.Float(1.6f, MAX.getFloatY() - 0.25f);
		// this.drawEightPointStar(gl, star3, 0.08f, YELLOW, 1f);
		// final Point2D.Float star4 = new Point2D.Float(1.7f, MAX.getFloatY() - 0.5f);
		// this.drawEightPointStar(gl, star4, 0.08f, YELLOW, 0.5f);
		// final Point2D.Float star5 = new Point2D.Float(1.55f, MAX.getFloatY() - 0.7f);
		// this.drawEightPointStar(gl, star5, 0.08f, YELLOW, 0.3f);

		// Draw the kite and string
		this.drawKite(gl);
	}

	//**********************************************************************
	// Private Methods (Coordinate System)
	//**********************************************************************

	private void	setProjection(GL2 gl)
	{
		GLU		glu = new GLU();

		gl.glMatrixMode(GL2.GL_PROJECTION);			// Prepare for matrix xform
		gl.glLoadIdentity();						// Set to identity matrix
		glu.gluOrtho2D(-2.0f, 2.0f, -1.0f, 1.0f);	// 2D translate and scale
	}

	/* Draw the kite and string
	 * @param gl: The GL context
	 */
	private void drawKite(GL2 gl)
	{
		final float[] BLUE = new float[]{70f/255f,130f/255f,180f/255f};
		final Point2D.Float center = new Point2D.Float(1.0f, 0.4f);
		// Draw both halves of the kite
		this.drawKitePiece(gl, center, 0.25f, 90, 180, BLUE);
		this.drawKitePiece(gl, center, 0.25f, 270, 360, BLUE);

		// Draw pieces of the string one at a time
		final float[] GRAY = new float[]{0.8f, 0.8f, 0.8f};
		Point2D.Float start = new Point2D.Float(MAX.getFloatX() - 3*0.075f - 0.02f-0.5f, ROAD_LIM + 0.4f);
		Point2D.Float end = new Point2D.Float((float)start.getX() - 0.2f, (float)start.getY() + 0.15f);
		Utils.drawLine(gl, start, end, GRAY, 2.0f);
		start = end;
		end = new Point2D.Float((float)start.getX() - 0.12f, (float)start.getY() + 0.1f);
		Utils.drawLine(gl, start, end, GRAY, 2.0f);
		start = end;
		end = new Point2D.Float((float)start.getX() - 0.08f, (float)start.getY() + 0.11f);
		Utils.drawLine(gl, start, end, GRAY, 2.0f);
		start = end;
		end = new Point2D.Float((float)start.getX() +0.02f, (float)start.getY() + 0.03f);
		Utils.drawLine(gl, start, end, GRAY, 2.0f);
		start = end;
		end = new Point2D.Float((float)start.getX() - 0.04f, (float)start.getY() + 0.04f);
		Utils.drawLine(gl, start, end, GRAY, 2.0f);
		start = end;
		end = new Point2D.Float((float)start.getX() - 0.04f, (float)start.getY() + 0.02f);
		Utils.drawLine(gl, start, end, GRAY, 2.0f);
		start = end;
		Utils.drawLine(gl, start, center, GRAY, 2.0f);
	}

	/*
	 * Overload of the draw circle that will include individual 
	 * triangle outlines.
	 */
	private void drawKitePiece(GL2 gl, Point2D center, float radius, double start, double end, float[] color)
	{
		final float[] GRAY = new float[]{0.6f, 0.6f, 0.6f};
		int len = 6;
		double[] vert = new double[12];
		gl.glBegin(GL.GL_TRIANGLE_FAN);
		gl.glColor3f(color[0], color[1], color[2]);
		gl.glVertex2d(center.getX(), center.getY());
		double centx = center.getX();
		double centy = center.getY();
		for(double angle = start, i = 0; angle <= end; angle+=18, i+= 2)
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
			final Point2D.Float from = new Point2D.Float((float)vert[i], (float)vert[i+1]);
			final Point2D.Float to = new Point2D.Float((float)vert[i+2], (float)vert[i+3]);
			Utils.drawLine(gl, from, to, GRAY, 5.0f, 0.3f);
			Utils.drawLine(gl, center, from, GRAY, 5.0f, 0.3f);
			Utils.drawLine(gl, center, to, GRAY, 5.0f, 0.3f);
		}
	}
}

//******************************************************************************
