package com.example.awesomefat.csc300_spring2018_shuntingyard;

/**
 * Created by awesomefat on 2/22/18.
 */

public class Queue
{
    private Node front;
    private Node end;

    public Queue()
    {
        this.front = null;
        this.end = null;
    }

    public boolean isEmpty()
    {
        return this.front == null;
    }

    //Polymorphism
    private void enqueue(Node n)
    {
        if(this.end == null)
        {
            this.end = n;
        }
        else
        {
            this.end.setNextNode(n);
            this.end = n;
        }

        if(this.front == null)
        {
            this.front = this.end;
        }
    }

    public void enqueue(OpNode op)
    {
        this.enqueue((Node)op);
    }

    public void enqueue(NumNode op)
    {
        this.enqueue((Node)op);
    }

    public void enqueue(char payload)
    {
        OpNode on = new OpNode(payload);
        this.enqueue(on);
    }

    public void enqueue(int payload)
    {
        NumNode nn = new NumNode(payload);
        this.enqueue(nn);
    }

    public Node dequeue()
    {
        if(this.front == null)
        {
            return this.front;
        }
        else
        {
            Node node2Return = this.front;
            if(this.end == this.front)
            {
                this.front = null;
                this.end = null;
            }
            else
            {
                this.front = this.front.getNextNode();
                node2Return.setNextNode(null);
            }
            return node2Return;
        }
    }
}
