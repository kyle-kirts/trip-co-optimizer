package com.tco.misc;

import java.util.ArrayList;

public class Places extends ArrayList<Place> {
    public int getPlace(Place place)
    {
        for(int i=0; i<this.size(); i++)
        {
            if(this.get(i).equals(place)) return i;
        }
        return -1;
    }
}