package com.example.awesomefat.csc300_spring2018_shuntingyard;

/**
 * Created by awesomefat on 2/27/18.
 */

public class OpStack
{
    private OpNode top;

    public OpStack()
    {
        this.top = null;
    }

    public OpNode peek()
    {
        return this.top;
    }

    public OpNode pop()
    {
        OpNode currTop = this.top;
        if(currTop != null)
        {
            this.top = (OpNode)currTop.getNextNode();
            currTop.setNextNode(null);
        }
        return currTop;
    }

    public void clearOpStack(Queue outputQ)
    {
        while(this.top != null)
        {
            outputQ.enqueue(this.pop());
        }
    }

    public void push(OpNode op, Queue outputQ)
    {
        //this is the logic for whether you can add a OpNode to the stack

        //clear the stack until it is a legal move
        while(this.peek() != null && this.peek().getPriority() >= op.getPriority())
        {
            outputQ.enqueue(this.pop());
        }

        //push op onto top of stack
        if(this.top == null)
        {
            this.top = op;
        }
        else
        {
            op.setNextNode(this.top);
            this.top = op;
        }
    }
}
