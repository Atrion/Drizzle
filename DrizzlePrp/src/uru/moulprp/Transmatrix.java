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
import uru.e;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;

//aka hsMatrix44
public strictfp class Transmatrix extends uruobj
{
    byte isnotIdentity;
    int[] xmatrix = new int[16]; //raw data (floats are 32bit, so they fit in integer.)
    
    private Transmatrix(){}
    
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
    public static Transmatrix createDefault()
    {
        Transmatrix result = new Transmatrix();
        result.isnotIdentity = 0;
        return result;
    }
    /*public void fillInMatrix()
    {
        if(isnotIdentity==0)
        {
            for(int i=0;i<4;i++)
                for(int j=0;j<4;j++)
                    result[i][j] = i==j?1:0;
        }
    }*/
    public static Transmatrix createFromVector(float x, float y, float z)
    {
        double[][] doublemat = new double[4][4];
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                doublemat[i][j] = i==j?1:0;
        doublemat[0][3] = x;
        doublemat[1][3] = y;
        doublemat[2][3] = z;
        RealMatrix rm = new RealMatrixImpl(doublemat);
        return createFromMatrix(rm);
    }
    public Transmatrix mult(Transmatrix t2)
    {
        double[][] m1 = this.convertToDoubleArray();
        double[][] m2 = t2.convertToDoubleArray();
        RealMatrix rm1 = new RealMatrixImpl(m1);
        RealMatrix rm2 = new RealMatrixImpl(m2);
        RealMatrix result = rm1.multiply(rm2);
        Transmatrix result2 = Transmatrix.createFromMatrix(result);
        return result2;
    }
    public Vertex mult(Vertex v)
    {
        double[][] m1 = this.convertToDoubleArray();
        double[][] m2 = v.convertToDouble4x1Matrix();
        RealMatrix rm1 = new RealMatrixImpl(m1);
        RealMatrix rm2 = new RealMatrixImpl(m2);
        RealMatrix result = rm1.multiply(rm2);
        double[][] result2 = result.getData();
        Vertex result3 = Vertex.createFromDouble4x1Matrix(result2);
        return result3;
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
    public double[][] convertToDoubleArray()
    {
        double[][] result = new double[4][4];
        if(isnotIdentity==0)
        {
            for(int i=0;i<4;i++)
                for(int j=0;j<4;j++)
                    result[i][j] = i==j?1:0;
        }
        else
        {
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<4;j++)
                {
                    int datum = xmatrix[i*4+j];
                    result[i][j] = Flt.createFromData(datum).toJavaFloat();
                }
            }
        }
        return result;
        
    }
    public Flt[][] convertToFltArray()
    {
        Flt[][] result = new Flt[4][4];
        if(isnotIdentity==0)
        {
            for(int i=0;i<4;i++)
                for(int j=0;j<4;j++)
                    result[i][j] = i==j?Flt.one():Flt.zero();
        }
        else
        {
            for(int i=0;i<4;i++)
            {
                for(int j=0;j<4;j++)
                {
                    int datum = xmatrix[i*4+j];
                    result[i][j] = Flt.createFromData(datum);
                }
            }
        }
        return result;
    }
    
    public RealMatrix convertToMatrix()
    {
        double[][] rawdata = new double[4][4];
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                int datum = xmatrix[i*4+j];
                rawdata[i][j] = (double)Flt.createFromData(datum).toJavaFloat();
            }
        }
        RealMatrix rm = new RealMatrixImpl(rawdata);
        return rm;
    }
    
    public static Transmatrix createFromMatrix(RealMatrix rm)
    {
        double[][] rawdata = rm.getData();
        Transmatrix result = new Transmatrix();
        result.isnotIdentity = 1;
        result.xmatrix = new int[16];
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                float f = (float)rawdata[i][j];
                int rawflt = Flt.createFromJavaFloat(f).rawdata;
                result.xmatrix[i*4+j] = rawflt;
            }
        }
        return result;
    }
    
    //returns null if not possible.
    public Vertex convertTo3Vector()
    {
        Flt[][] flts = this.convertToFltArray();
        Flt x = flts[0][3];
        Flt y = flts[1][3];
        Flt z = flts[2][3];
        //e.ensure(flts[3][3]==1);
        return new Vertex(x,y,z);
    }
}
