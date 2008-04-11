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

import uru.context; import uru.readexception;
import uru.Bytestream;
import uru.e;
import uru.m;
import uru.Bytedeque;

/**
 *
 * @author user
 */
public class Uruobjectref extends uruobj
{
    byte hasRef;
    Uruobjectdesc xdesc;
    
    public Uruobjectref(context c)
    {
        hasRef = c.in.readByte();
        if(hasRef != 0)
        {
            xdesc = new Uruobjectdesc(c);
        }
        
    }
    //public static Uruobjectref create(Bytestream data)
    //{
    //    return new Uruobjectref(data);
    //}
    public void compile(Bytedeque deque)
    {
        deque.writeByte(hasRef);
        if(hasRef != 0)
        {
            xdesc.compile(deque);
        }
    }
    
    public String toString()
    {
        if(hasRef==0)
        {
            return "none";
        }
        else
        {
            return xdesc.toString();
        }
    }
}
