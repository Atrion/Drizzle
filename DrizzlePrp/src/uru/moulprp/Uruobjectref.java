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
import uru.e;
import shared.m;
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
        if(c.readversion==6||c.readversion==3)
        {
            hasRef = c.in.readByte();
            if(hasRef != 0)
            {
                xdesc = new Uruobjectdesc(c);
            }
        }
        else if(c.readversion==4)
        {
            xdesc = new Uruobjectdesc(c);
            //try to tell if this is a valid reference...
            if(xdesc.objectname.unencryptedString.length==0)
            {
                hasRef = 0;
            }
            else
            {
                hasRef = 1;
            }
       }
        
    }
    
    public static Uruobjectref none()
    {
        Uruobjectref result = new Uruobjectref();
        result.hasRef = 0;
        return result;
    }
    
    private Uruobjectref(){}
    
    public static Uruobjectref createFromUruobjectdesc(Uruobjectdesc desc)
    {
        Uruobjectref result = new Uruobjectref();
        result.hasRef = 1;
        result.xdesc = desc;
        return result;
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
