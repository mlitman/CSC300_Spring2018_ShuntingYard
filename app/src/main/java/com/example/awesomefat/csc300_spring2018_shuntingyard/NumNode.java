package com.example.awesomefat.csc300_spring2018_shuntingyard;

/**
 * Created by awesomefat on 2/22/18.
 */

public class NumNode extends Node
{
    private int payload;

    public NumNode(int payload)
    {
        super();
        this.payload = payload;
    }

    public int getPayload() {
        return payload;
    }
}
