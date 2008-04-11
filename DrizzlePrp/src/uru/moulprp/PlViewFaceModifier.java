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
public class PlViewFaceModifier extends uruobj
{
    //Objheader xheader;
    
    PlSingleModifier parent;
    Vertex scale;
    Transmatrix LocalToParent;
    Transmatrix ParentToLocal;
    Uruobjectref xFaceObj;
    Vertex offset;
    BoundingBox xMaxBounds;
    
    public PlViewFaceModifier(context c) throws readexception
    {
        //if(hasHeader) xheader = new Objheader(c);
        
        parent = new PlSingleModifier(c);//,false);
        scale = new Vertex(c);
        LocalToParent = new Transmatrix(c);
        ParentToLocal = new Transmatrix(c);
        if((parent.flagvector.values[0] & 0x0100)!=0)
        {
            xFaceObj = new Uruobjectref(c);
        }
        offset = new Vertex(c);
        if((parent.flagvector.values[0] & 0x0800)!=0)
        {
            xMaxBounds = new BoundingBox(c);
        }
        
    }
    public void compile(Bytedeque data)
    {
        parent.compile(data);
        scale.compile(data);
        LocalToParent.compile(data);
        ParentToLocal.compile(data);
        
        if((parent.flagvector.values[0] & 0x0100)!=0)
        {
            m.msg("haven't been here in viewfacemodifier");
            xFaceObj.compile(data);
        }
        offset.compile(data);
        if((parent.flagvector.values[0] & 0x0800)!=0)
        {
            xMaxBounds.compile(data);
        }
    }
}
