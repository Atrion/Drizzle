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
public class PlOccluder extends uruobj
{
    //Objheader xheader;
    
    PlObjInterface parent;
    int u1;
    int u2;
    Flt[] u3;
    short counta;
    SubOccluder[] blocks;
    Uruobjectref scenenode;
    short countd;
    Uruobjectref[] visRegion;
    
    public PlOccluder(context c) throws readexception
    {
        //if(hasHeader) xheader = new Objheader(c);
        
        parent = new PlObjInterface(c);//,false);
        u1 = c.readInt();
        u2 = c.readInt();
        u3 = c.readVector(Flt.class, 7);
        counta = c.readShort();
        blocks = c.readVector(SubOccluder.class, counta);
        scenenode = new Uruobjectref(c);
        countd = c.readShort();
        visRegion = c.readVector(Uruobjectref.class, countd);
        
    }
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        c.writeInt(u1);
        c.writeInt(u2);
        c.writeVector(u3);
        c.writeShort(counta);
        c.writeVector(blocks);
        scenenode.compile(c);
        c.writeShort(countd);
        c.writeVector(visRegion);
    }
    
    public static class SubOccluder extends uruobj
    {
        int countb;
        Flt[] floats1;
        int countc;
        Flt[] floats2;
        
        public SubOccluder(context c) throws readexception
        {
            countb = c.readInt();
            int countb2 = (countb==0) ? 2 : countb; //if countb=0, set it to 2.
            floats1 = c.readVector(Flt.class, countb2*4);
            countc = c.readInt();
            floats2 = c.readVector(Flt.class, countc*3);
            
        }
        
        public void compile(Bytedeque c)
        {
            c.writeInt(countb);
            c.writeVector(floats1);
            c.writeInt(countc);
            c.writeVector(floats2);
        }
    }
}
