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
public class PlSoftVolumeSimple extends uruobj
{
    //Objheader xheader;
    
    PlSoftVolume parent;
    Flt u1;
    PrpVolumeIsect prpvolumeisect;
    
    public PlSoftVolumeSimple(context c) throws readexception
    {
        //if(hasHeader) xheader = new Objheader(c);
        
        parent = new PlSoftVolume(c);//,false);
        u1 = new Flt(c);
        prpvolumeisect = new PrpVolumeIsect(c);
        
    }
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        u1.compile(c);
        prpvolumeisect.compile(c);
    }
}
