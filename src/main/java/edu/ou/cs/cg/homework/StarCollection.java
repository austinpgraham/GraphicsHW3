/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import java.util.List;
import java.util.ArrayList;

/**
 * Defines a collection of star objects
 * to iterate through and modify
 */
public class StarCollection
{
    // Stars in this collection
    private ArrayList<Star> stars;

    // Index of focused star
    private int inFocusStar = 0;

    /**
     * Construct the collection
     */
    public StarCollection()
    {
        this.stars = new ArrayList<Star>();
        this.stars.add(null);
    }

    /**
     * Add a star to the collection
     */
    public void addStar(Star star)
    {
        this.stars.add(star);
    }

    /**
     * Get the list of stars not including null
     * default focus star
     */
    public List<Star> getStars()
    {
        return this.stars.subList(1, this.stars.size());
    }

    /**
     * Cylce focus through the stars. If the newly focused star
     * is no the default null star, then reset the drawing color
     */
    public void cycleFocus()
    {
        Star focusStar = this.stars.get(this.inFocusStar);
        if(focusStar != null)
        {
            focusStar.setOutFocus();
        }
        this.inFocusStar++;
        this.inFocusStar = this.inFocusStar == this.stars.size() ? 0 : this.inFocusStar;
        focusStar = this.stars.get(this.inFocusStar);
        if (focusStar != null)
        {
            focusStar.setInFocus();
        }
    }

    /**
     * Get the currently focused star
     */
    public Star getFocusedStar()
    {
        return this.stars.get(this.inFocusStar);
    }
}