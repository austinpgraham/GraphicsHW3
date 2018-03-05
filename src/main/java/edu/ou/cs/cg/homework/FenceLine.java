/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import java.util.ArrayList;

/**
 * Tracks a series of fence pieces
 */
public class FenceLine
{
    // The list of fence posts
    private ArrayList<FencePost> posts;

    /**
     * Construct the object
     */
    public FenceLine()
    {
        this.posts = new ArrayList<FencePost>();
    }

    /**
     * Add fence post to list
     * @param post: post to add
     */
    public void addFencePost(FencePost post)
    {
        this.posts.add(post);
    }

    /**
     * Gets the current fence posts
     */
    public ArrayList<FencePost> getPosts()
    {
        return this.posts;
    }

    /**
     * Adjust the height of all fence posts 
     * in this series.
     * @param amount: Amount to adjust by
     */
    public void adjustHeight(float amount)
    {
        for(FencePost p: this.posts)
        {
            p.adjustHeight(amount);
        }
    }
}