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
import shared.readexception;

/**
 *
 * @author user
 */
public strictfp class Vertex extends uruobj
{
    Flt x;
    Flt y;
    Flt z;
    
    public Vertex(context c) throws readexception
    {
        x = c.readObj(Flt.class);
        y = c.readObj(Flt.class);
        z = c.readObj(Flt.class);
    }
    //static public Vertex create(Bytestream data)
    //{
    //    return new Vertex(data);
    //}
    public Vertex(Flt x2, Flt y2, Flt z2)
    {
        x = x2;
        y = y2;
        z = z2;
    }
    private Vertex(){}
    public static Vertex zero()
    {
        return new Vertex(new Flt(0), new Flt(0), new Flt(0));
    }
    public static Vertex createFromFloats(float x, float y, float z)
    {
        Vertex result = new Vertex();
        result.x = Flt.createFromJavaFloat(x);
        result.y = Flt.createFromJavaFloat(y);
        result.z = Flt.createFromJavaFloat(z);
        return result;
    }
    public void compile(Bytedeque data)
    {
        x.compile(data);
        y.compile(data);
        z.compile(data);
    }
    public float dot(Vertex v2)
    {
        float result = this.x.toJavaFloat()*v2.x.toJavaFloat() + this.y.toJavaFloat()*v2.y.toJavaFloat() + this.z.toJavaFloat()*v2.z.toJavaFloat();
        return result;
    }
    public double[] convertToDouble4Vector()
    {
        double[] result = new double[4];
        result[0] = x.toJavaFloat();
        result[1] = y.toJavaFloat();
        result[2] = z.toJavaFloat();
        result[3] = 1.0;
        return result;
    }
    public double[][] convertToDouble4x1Matrix()
    {
        double[][] result = new double[4][1];
        result[0][0] = x.toJavaFloat();
        result[1][0] = y.toJavaFloat();
        result[2][0] = z.toJavaFloat();
        result[3][0] = 1.0;
        return result;
    }
    public static Vertex createFromDouble4x1Matrix(double[][] mat)
    {
        Vertex result = new Vertex();
        double x = mat[0][0];
        double y = mat[1][0];
        double z = mat[2][0];
        double h = mat[3][0];
        result.x = Flt.createFromJavaFloat((float)(x/h));
        result.y = Flt.createFromJavaFloat((float)(y/h));
        result.z = Flt.createFromJavaFloat((float)(z/h));
        return result;
    }
    public Vertex add(Vertex v)
    {
        Flt x2 = this.x.add(v.x);
        Flt y2 = this.y.add(v.y);
        Flt z2 = this.z.add(v.z);
        Vertex result = new Vertex(x2,y2,z2);
        return result;
    }
    public void addModify(Vertex v)
    {
        x = this.x.add(v.x);
        y = this.y.add(v.y);
        z = this.z.add(v.z);
    }
    public String toString()
    {
        return x.toString()
                +":"+y.toString()
                +":"+z.toString();
    }

}
