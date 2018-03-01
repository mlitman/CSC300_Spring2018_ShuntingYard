package com.example.awesomefat.csc300_spring2018_shuntingyard;

/**
 * Created by awesomefat on 2/22/18.
 */

public class OpNode extends Node
{
    private char payload;
    private int priority;

    public OpNode(char payload)
    {
        super(); //call the parent's constructor
        this.payload = payload;
        this.priority = OpNode.getPriority(this.payload);
    }

    private static int getPriority(char op)
    {
        if(op == '+' || op == '-')
        {
            return 2;
        }
        else
        {
            return 3;
        }
    }

    public int getPriority() {
        return priority;
    }

    public char getPayload() {
        return payload;
    }
}
