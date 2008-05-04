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
import shared.readexception;
//import java.util.Vector;

//aka hsBounds3Ext
public class BoundingBox extends uruobj
{
    int flags;
    int mode;
    Vertex min;
    Vertex max;
    Vertex xboundingboxcorner;
    Vertex xdiff0;
    Flt xdot0;
    Flt xmag20;
    Vertex xdiff1;
    Flt xdot1;
    Flt xmag21;
    Vertex xdiff2;
    Flt xdot2;
    Flt xmag22;

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
            xdiff0 = c.readObj(Vertex.class);
            xdot0 = c.readObj(Flt.class);
            xmag20 = c.readObj(Flt.class);
            xdiff1 = c.readObj(Vertex.class);
            xdot1 = c.readObj(Flt.class);
            xmag21 = c.readObj(Flt.class);
            xdiff2 = c.readObj(Vertex.class);
            xdot2 = c.readObj(Flt.class);
            xmag22 = c.readObj(Flt.class);
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
