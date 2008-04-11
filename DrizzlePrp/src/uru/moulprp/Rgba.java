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

/**
 *
 * @author user
 */
public class Rgba extends uruobj
{
    int r;
    int g;
    int b;
    int a;
    
    public Rgba(Bytestream data)
    {
        r = data.readInt(); //red //float from 0.0 to 1.0
        g = data.readInt(); //green //float from 0.0 to 1.0
        b = data.readInt(); //blue //float from 0.0 to 1.0
        a = data.readInt(); //alpha //float from 0.0 to 1.0
    }
    public void compile(Bytedeque deque)
    {
        deque.writeInt(r);
        deque.writeInt(g);
        deque.writeInt(b);
        deque.writeInt(a);
    }

    public String toString()
    {
        return Flt.toString(r)
                +":"+Flt.toString(g)
                +":"+Flt.toString(b)
                +":"+Flt.toString(a);
    }
    
}
