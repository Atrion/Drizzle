/*
    Drizzle - A general Myst tool.
    Copyright (C) 2008  Dustin Bernard.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/ 

package uru.moulprp;

import uru.context; import shared.readexception;
import uru.Bytestream;
import uru.b;
import shared.m;
import uru.Bytedeque;

//aka hsMatrix44
public class Transmatrix extends uruobj
{
    byte isnotIdentity;
    int[] xmatrix = new int[16]; //raw data (floats are 32bit, so they fit in integer.)
    
    public Transmatrix(context c)
    {
        if(c.readversion==6)
        {
            isnotIdentity = c.in.readByte();
            if(isnotIdentity!=0)
            {
                xmatrix = c.in.readInts(16);
            }
        }
        else if(c.readversion==3||c.readversion==4)
        {
            isnotIdentity = 1; //this byte doesn't exist in pots.
            xmatrix = c.in.readInts(16);
        }
    }
    
    public void compile(Bytedeque deque)
    {
        //there is no isnotIdentity flag in pots, it always has the full matrix.
        if(isnotIdentity==1)
        {
            deque.writeInts(xmatrix);
        }
        else
        {
            Flt zero = new Flt(0);
            Flt one = new Flt(1);

            one.compile(deque);
            zero.compile(deque);
            zero.compile(deque);
            zero.compile(deque);

            zero.compile(deque);
            one.compile(deque);
            zero.compile(deque);
            zero.compile(deque);

            zero.compile(deque);
            zero.compile(deque);
            one.compile(deque);
            zero.compile(deque);

            zero.compile(deque);
            zero.compile(deque);
            zero.compile(deque);
            one.compile(deque);

        }
    }

    /*public float[] toFloats()
    {
        float[] result = new float[16];
        for(int i=0;i<16;i++)
        {
            result[i] = Float.intBitsToFloat(xmatrix[i]);
        }
        return result;
    }*/
    public String toString()
    {
        //float[] m2 = toFloats();
        String result = "";
        if(isnotIdentity==0)
        {
            result += "identity matrix";
        }
        else
        {
            for(int i=0;i<16;i++)
            {
                result += ":"+uru.moulprp.Flt.toString(xmatrix[i]);
            }
        }
        return result;
    }
    
}
