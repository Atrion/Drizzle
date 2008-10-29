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
import shared.readexception;
//import java.util.Vector;

//aka hsBounds3Ext
public class BoundingBox extends uruobj
{
    public int flags;
    public int mode;
    public Vertex min;
    public Vertex max;
    public Vertex xboundingboxcorner;
    public Vertex xdiff0;
    public Flt xdot0;
    public Flt xmag20;
    public Vertex xdiff1;
    public Flt xdot1;
    public Flt xmag21;
    public Vertex xdiff2;
    public Flt xdot2;
    public Flt xmag22;

    public BoundingBox(context c) throws readexception
    {
        Bytestream data = c.in;
        flags = data.readInt();
        mode = data.readInt();
        min = c.readObj(Vertex.class);
        max = c.readObj(Vertex.class);
        if((flags&0x00000001)==0)
        {
            xboundingboxcorner = c.readObj(Vertex.class);
            xdiff0 = c.readObj(Vertex.class); //axis
            xdot0 = c.readObj(Flt.class); //x
            xmag20 = c.readObj(Flt.class); //y
            xdiff1 = c.readObj(Vertex.class); //axis
            xdot1 = c.readObj(Flt.class); //x
            xmag21 = c.readObj(Flt.class); //y
            xdiff2 = c.readObj(Vertex.class); //axis
            xdot2 = c.readObj(Flt.class); //x
            xmag22 = c.readObj(Flt.class); //y
        }
    }
    public void transform(Transmatrix mat)
    {
        m.warn("BoundingBox transform may not be correct.");
        min = mat.mult(min);
        max = mat.mult(max);
        if((flags&0x00000001)==0)
        {
            xboundingboxcorner = mat.mult(xboundingboxcorner);
        }
    }
    public void compile(Bytedeque data)
    {
        data.writeInt(flags);
        data.writeInt(mode);
        min.compile(data);
        max.compile(data);

        if((flags&0x00000001)==0)
        {
            xboundingboxcorner.compile(data);
            xdiff0.compile(data);
            xdot0.compile(data);
            xmag20.compile(data);
            xdiff1.compile(data);
            xdot1.compile(data);
            xmag21.compile(data);
            xdiff2.compile(data);
            xdot2.compile(data);
            xmag22.compile(data);

        }

    }

}
