package com.example.awesomefat.csc300_spring2018_shuntingyard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Stack;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity
{
    private Queue inputQ;
    private OpStack opStack;
    private Queue outputQ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //"10+3-2" -> turn into a queue of NumNodes and OpNodes
        this.inputQ = new Queue();
        this.outputQ = new Queue();
        this.opStack = new OpStack();
    }

    private String removeSpaces(String s)
    {
        String answer = "";
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) != ' ')
            {
                answer = answer + s.charAt(i);
            }
        }
        return answer;
    }

    private void testQ(Queue q)
    {
        while(!q.isEmpty())
        {
            Node n = q.dequeue();
            if(n instanceof NumNode)
            {
                NumNode temp = (NumNode)n;
                System.out.println(temp.getPayload());
            }
            else
            {
                OpNode temp = (OpNode)n;
                System.out.println(temp.getPayload());
            }
        }
    }

    private void parseString(String s)
    {
        String currNumber = "";
        String digits = "0123456789";
        for(int i = 0; i < s.length(); i++)
        {
            if(digits.indexOf(s.charAt(i)) != -1)
            {
                currNumber = currNumber + s.charAt(i);
            }
            else
            {
                this.inputQ.enqueue(Integer.parseInt(currNumber));
                currNumber = "";
                this.inputQ.enqueue(s.charAt(i));
            }
        }
        this.inputQ.enqueue(Integer.parseInt(currNumber));
        this.testQ(this.inputQ);
    }

    private void fillInputQ(String s)
    {
        StringTokenizer st = new StringTokenizer(s,"+-*/", true);
        String temp;
        String ops = "+-*/";
        while(st.hasMoreTokens())
        {
            temp = st.nextToken().trim();
            if(ops.indexOf(temp.charAt(0)) == -1)
            {
                this.inputQ.enqueue(Integer.parseInt(temp));
            }
            else
            {
                //"+" -> '+'
                this.inputQ.enqueue(temp.charAt(0));
            }
        }
        //this.testQ(this.inputQ);
    }

    private void processInputQ()
    {
        Node currNode;
        while(!this.inputQ.isEmpty())
        {
            currNode = this.inputQ.dequeue();
            if(currNode instanceof NumNode)
            {
                this.outputQ.enqueue((NumNode)currNode);
            }
            else
            {
                this.opStack.push((OpNode)currNode, this.outputQ);
            }
        }
        this.opStack.clearOpStack(this.outputQ);
    }

    private int doMath(int num1, int num2, char op)
    {
        if(op == '+')
        {
            return num1 + num2;
        }
        else if(op == '-')
        {
            return num1 - num2;
        }
        else if(op == '*')
        {
            return num1 * num2;
        }
        else
        {
            return num1 / num2;
        }
    }

    private void processOutputQueue2()
    {
        Stack<NumNode> solutionStack = new Stack<NumNode>();
        Node temp;

        while(!this.outputQ.isEmpty())
        {
            temp = this.outputQ.dequeue();
            int num1;
            int num2;
            int answer;
            if(temp instanceof OpNode)
            {
                //doMath
                num2 = solutionStack.pop().getPayload();
                num1 = solutionStack.pop().getPayload();
                answer = doMath(num1, num2, ((OpNode) temp).getPayload());
                solutionStack.push(new NumNode(answer));
            }
            else
            {
                solutionStack.push((NumNode)temp);
            }
        }
        System.out.println("Final Answer: " + solutionStack.pop().getPayload());
    }

    private void processOutputQueue()
    {
        //ultimately show the answer on the screen
        Node temp;
        int num1 = 0;
        int num2 = 0;
        boolean fillNum1 = true;

        while(!this.outputQ.isEmpty())
        {
            temp = this.outputQ.dequeue();
            if(temp instanceof OpNode)
            {
                num1 = this.doMath(num1, num2, ((OpNode)temp).getPayload());
            }
            else
            {
                if(fillNum1)
                {
                    num1 = ((NumNode)temp).getPayload();
                    fillNum1 = false;
                }
                else
                {
                    num2 = ((NumNode)temp).getPayload();
                }
            }
        }
        System.out.println("Final Answer: " + num1);
    }
    public void onClickMeButtonPressed(View v)
    {
        EditText inputET = (EditText)this.findViewById(R.id.inputET);
        String valueWithoutSpaces = this.removeSpaces(inputET.getText().toString());
        this.fillInputQ(inputET.getText().toString());
        this.processInputQ();
        this.processOutputQueue2();
        //this.testQ(this.outputQ);
    }
}
