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
import shared.e;
import shared.m;
import shared.b;
//import java.util.Vector;

/**
 *
 * @author user
 */
public class PlCameraBrain1_Circle extends uruobj
{
    //Objheader xheader;
    
    PlCameraBrain1 parent;
    int circleFlags;
    public Vertex centre;
    Flt radius;
    Uruobjectref centreObject;
    Uruobjectref POAObj;
    Flt cirPerSec;
    
    public PlCameraBrain1_Circle(context c) throws readexception
    {
        //if(hasHeader) xheader = new Objheader(c);
        
        parent = new PlCameraBrain1(c);//,false);
        circleFlags = c.readInt();
        centre = new Vertex(c);
        radius = new Flt(c);
        centreObject = new Uruobjectref(c);
        POAObj = new Uruobjectref(c);
        cirPerSec = new Flt(c);
    }
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        c.writeInt(circleFlags);
        centre.compile(c);
        radius.compile(c);
        centreObject.compile(c);
        POAObj.compile(c);
        cirPerSec.compile(c);
    }
}
