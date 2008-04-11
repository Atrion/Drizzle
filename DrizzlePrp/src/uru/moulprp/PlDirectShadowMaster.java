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
//import java.util.Vector;

/**
 *
 * @author user
 */
public class PlDirectShadowMaster extends uruobj
{
    //Objheader xheader;
    
    PlRegionBase parent;
    Flt f1;
    Flt f2;
    Flt f3;
    int u1;
    int u2;
    Flt f4;
    
    public PlDirectShadowMaster(context c) throws readexception
    {
        //if(hasHeader) xheader = new Objheader(c);
        
        parent = new PlRegionBase(c);//,false);
        f1 = new Flt(c);
        f2 = new Flt(c);
        f3 = new Flt(c);
        u1 = c.readInt();
        u2 = c.readInt();
        f4 = new Flt(c);
    }
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        f1.compile(c);
        f2.compile(c);
        f3.compile(c);
        c.writeInt(u1);
        c.writeInt(u2);
        f4.compile(c);
    }
}
