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
import uru.context; import uru.readexception;

/**
 *
 * @author user
 */
public class ShortTriplet extends uruobj
{
    short p;
    short q;
    short r;

    public ShortTriplet(context c)
    {
        p = c.readShort();
        q = c.readShort();
        r = c.readShort();
    }
    
    public void compile(Bytedeque data)
    {
        data.writeShort(p);
        data.writeShort(q);
        data.writeShort(r);
    }
    
    public String toString()
    {
        return "face1="+Short.toString(p)+" face2="+Short.toString(q)+" face3="+Short.toString(r);
    }
}
    
