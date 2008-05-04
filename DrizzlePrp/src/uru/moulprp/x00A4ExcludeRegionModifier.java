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
public class x00A4ExcludeRegionModifier extends uruobj
{
    //Objheader xheader;
    PlSingleModifier parent;
    int count;
    Uruobjectref[] safepoints;
    byte seek;
    Flt seektime;
    
    public x00A4ExcludeRegionModifier(context c) throws readexception
    {
        //if(hasHeader) xheader = new Objheader(c);
        
        parent = new PlSingleModifier(c);//,false);
        count = c.readInt();
        safepoints = c.readVector(Uruobjectref.class, count);
        seek = c.readByte();
        seektime = new Flt(c);
    }
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        c.writeInt(count);
        c.writeVector(safepoints);
        c.writeByte(seek);
        seektime.compile(c);
    }
}
