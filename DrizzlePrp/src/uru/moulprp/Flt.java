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

import uru.Bytestream;
import uru.Bytedeque;
import uru.context; import shared.readexception;
import shared.m;

//just a Float, but I shouldn't use that name, since java uses it.
strictfp public class Flt extends uruobj
{
    int rawdata;
    
    public Flt(context c)
    {
        rawdata = c.in.readInt();
        if(this.approxequals((float)190.0352, (float)0.01))
        {
            int dummy=0;
        }
    }
    public Flt(int intToConvert)
    {
        switch(intToConvert)
        {
            case 0:
                rawdata = 0x00000000;
                break;
            case 1:
                rawdata = 0x3F800000;
                break;
            default:
                m.err("Conversion from this int is not currently supported.");
                break;
        }
    }
    public static Flt zero()
    {
        return new Flt(0);
    }
    public static Flt one()
    {
        return new Flt(1);
    }
    private Flt()
    {
    }
    public Flt(float f)
    {
        this.rawdata = Float.floatToRawIntBits(f);
    }
    private void assign(float f)
    {
        this.rawdata = Float.floatToRawIntBits(f);
    }
    public static Flt createFromJavaFloat(float f)
    {
        Flt result = new Flt();
        result.rawdata = Float.floatToRawIntBits(f);
        return result;
    }
    public static Flt createFromData(int datum)
    {
        Flt result = new Flt();
        result.rawdata = datum;
        return result;
    }
    /*public Flt(double f)
    {
        this.rawdata = Float.floatToRawIntBits((float)f);
    }*/
    public void compile(Bytedeque deque)
    {
        deque.writeInt(rawdata);
    }
    public String toString()
    {
        //float value = java.lang.Float.intBitsToFloat(rawdata);
        //return "Float=" + java.lang.Float.toString(this.toJavaFloat());
        return java.lang.Float.toString(this.toJavaFloat());
    }
    public static String toString(int intvalue)
    {
        float fl = java.lang.Float.intBitsToFloat(intvalue);
        String result = java.lang.Float.toString(fl);
        return result;
    }
    public float toJavaFloat()
    {
        float result = java.lang.Float.intBitsToFloat(rawdata);
        return result;
    }
    
    //Is this perhaps a tad over-complicated?
    public Flt add(Flt f)
    {
        float f1 = f.toJavaFloat();
        float f2 = this.toJavaFloat();
        float result3 = f1+f2;
        return new Flt(result3);
    }
    public Flt add(float f)
    {
        float f1 = this.toJavaFloat();
        float result = f1+f;
        return Flt.createFromJavaFloat(result);
    }
    public void addModify(float f)
    {
        float f1 = this.toJavaFloat();
        float result = f1+f;
        this.assign(result);
    }
    public Flt sub(Flt f)
    {
        return this.add(f.neg());
    }
    public Flt neg()
    {
        float neg = -1*this.toJavaFloat();
        return new Flt(neg);
    }
    public Flt mult(float f)
    {
        float f1 = this.toJavaFloat();
        float result = f1*f;
        return new Flt(result);
    }
    public Flt mult(Flt f)
    {
        float f1 = this.toJavaFloat();
        float f2 = f.toJavaFloat();
        float result = f1*f2;
        return new Flt(result);
    }
    public static double[][] convertFltArrayToDoubleArray(Flt[][] arr)
    {
        int a = arr.length;
        int b = arr[0].length;
        double[][] result = new double[a][b];
        for(int i=0;i<a;i++)
            for(int j=0;j<b;j++)
                result[i][j] = arr[i][j].toJavaFloat();
        return result;
    }
    public boolean approxequals(float f, float tolerance)
    {
        float f2 = this.toJavaFloat();
        float diff = f2-f;
        diff = java.lang.Math.abs(diff);
        if(diff<tolerance)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean approxequals(float f)
    {
        float tolerance = (float)0.000001;
        return approxequals(f,tolerance);
    }
    public boolean equals(Object o)
    {
        if(o==null) return false;
        if(o instanceof Float)
        {
            Flt o2 = Flt.createFromJavaFloat((Float)o);
            return o2.rawdata==this.rawdata;
        }
        if(o instanceof Flt)
        {
            return ((Flt)o).rawdata==this.rawdata;
        }
        return false;
    }
}
