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
import uru.Bytedeque;
import uru.e;
import shared.m;
import uru.b;
//import java.util.Vector;

/**
 *
 * @author user
 */
public class PlSynchedObject extends uruobj
{
    //Objheader xheader;
    //x0002Keyedobject parent;
    int flags;
    short xstringcount;
    Wpstr[] sdllinks;
    
    /*public PlSynchedObject(context c) //handy
    {
        this(c);//,false);
    }*/
    public PlSynchedObject(context c)//,boolean hasHeader)
    {
        Bytestream data = c.in;
        //if(hasHeader) xheader = new Objheader(c);
        //parent = new x0002Keyedobject(data);
        flags = data.readInt(); e.ensure(flags,0x00,0x04,0x0C,0x10,0x20,0x28,0x38,0x80,0x84,0x8C); //if fails, check cobbs
        if ((flags & 0x10)!=0)
        {
            xstringcount = data.readShort();
            int count = b.Int16ToInt32(xstringcount);
            sdllinks = new Wpstr[count];
            for(int i=0;i<count;i++)
            {
                sdllinks[i] = new Wpstr(c);
            }
            
        }
        if((flags & 0x40)!=0)
        {
            //haven't implemented this yet.
            m.err("plsynchedobject: haven't implemented this yet.");
        }
    }
    public void compile(Bytedeque deque)
    {
        deque.writeInt(flags);
        if ((flags & 0x10)!=0)
        {
            deque.writeShort(xstringcount);
            int count = b.Int16ToInt32(xstringcount);
            for(int i=0;i<count;i++)
            {
                sdllinks[i].compile(deque);
            }
        }
    }
}
