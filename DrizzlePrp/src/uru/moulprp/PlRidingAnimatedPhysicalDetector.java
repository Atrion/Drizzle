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
import uru.Bytedeque;
import uru.e;
import uru.m;
import uru.b;
import uru.readexception;
//import java.util.Vector;


public class PlRidingAnimatedPhysicalDetector extends uruobj
{
    PlSingleModifier parent;
    byte u1;
    PrpTaggedObject xu2;
    byte u3;
    PrpTaggedObject xu4;
    
    public PlRidingAnimatedPhysicalDetector(context c) throws readexception
    {
        e.ensure(c.readversion==6);
        
        parent = new PlSingleModifier(c);
        u1 = c.readByte();
        if(u1!=0)
        {
            xu2 = new PrpTaggedObject(c);
        }
        u3 = c.readByte();
        if(u3!=0)
        {
            xu4 = new PrpTaggedObject(c);
        }

        throw new readexception("plRidingAnimatedPhysicalDetector: can read okay, but failing in order to ignore.");
    }
    
    public void compile(Bytedeque c)
    {
        m.warn("compile not implemented."+this.toString());
        m.warn("not tested with pots."+this.toString());
    }
    
}
