package com.mycompany.bonus;

/**
 *
 * @author Vlad Adriana
 */
import java.util.*;
public class Solution {
    protected Road roadList[];
    protected float length;
    protected float duration;

    public Solution(Road[] roadList, float length, float duration) {
        this.roadList = roadList;
        this.length = length;
        this.duration = duration;
    }

    public Road[] getRoadList() {
        return roadList;
    }

    public void setRoadList(Road[] roadList) {
        this.roadList = roadList;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
                
        return "Solution{" + "roadList=" + Arrays.toString(roadList) + ", length=" + length + ", duration=" + duration + '}';
        
    }
    
}
