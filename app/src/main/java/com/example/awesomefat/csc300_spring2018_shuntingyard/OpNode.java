package com.example.awesomefat.csc300_spring2018_shuntingyard;

/**
 * Created by awesomefat on 2/22/18.
 */

public class OpNode extends Node
{
    private char payload;

    public OpNode(char payload)
    {
        super(); //call the parent's constructor
        this.payload = payload;
    }

    public char getPayload() {
        return payload;
    }
}
