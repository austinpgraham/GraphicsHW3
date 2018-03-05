package edu.ou.cs.cg.homework;

import java.util.List;
import java.util.ArrayList;

public class StarCollection
{
    private ArrayList<Star> stars;
    private int inFocusStar = 0;

    public StarCollection()
    {
        this.stars = new ArrayList<Star>();
        this.stars.add(null);
    }

    public void addStar(Star star)
    {
        this.stars.add(star);
    }

    public List<Star> getStars()
    {
        return this.stars.subList(1, this.stars.size());
    }

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

    public Star getFocusedStar()
    {
        return this.stars.get(this.inFocusStar);
    }
}