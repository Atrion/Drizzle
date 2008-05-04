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
public class Vertex extends uruobj
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
    public void compile(Bytedeque data)
    {
        x.compile(data);
        y.compile(data);
        z.compile(data);
    }
    
    public Vertex add(Vertex v)
    {
        Flt x2 = this.x.add(v.x);
        Flt y2 = this.y.add(v.y);
        Flt z2 = this.z.add(v.z);
        Vertex result = new Vertex(x2,y2,z2);
        return result;
    }
    public String toString()
    {
        return x.toString()
                +":"+y.toString()
                +":"+z.toString();
    }

}
