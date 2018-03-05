package edu.ou.cs.cg.homework;

import java.util.ArrayList;

public class FenceLine
{
    private ArrayList<FencePost> posts;

    public FenceLine()
    {
        this.posts = new ArrayList<FencePost>();
    }

    public void addFencePost(FencePost post)
    {
        this.posts.add(post);
    }

    public ArrayList<FencePost> getPosts()
    {
        return this.posts;
    }

    public void adjustHeight(float amount)
    {
        for(FencePost p: this.posts)
        {
            p.adjustHeight(amount);
        }
    }
}