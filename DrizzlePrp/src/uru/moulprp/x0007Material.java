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
//aka hsGMaterial
public class x0007Material extends uruobj
{
    //Objheader xheader;
    PlSynchedObject parent;
    int u1;
    int flags;
    int layercount;
    int lightmapcount;
    Uruobjectref[] layerrefs;
    Uruobjectref[] maplayerrefs;
    
    public x0007Material(context c)//,boolean hasHeader)
    {
        Bytestream data = c.in;
        //if(hasHeader) xheader = new Objheader(c);
        parent = new PlSynchedObject(c);//,false);
        u1 = data.readInt(); e.ensure(u1,0);
        flags = data.readInt(); e.ensure(flags,0x00,0x0400,0x1000,0x2000,0x2400);
        layercount = data.readInt();
        lightmapcount = data.readInt();
        layerrefs = new Uruobjectref[layercount];
        for(int i=0;i<layercount;i++)
        {
            layerrefs[i] = new Uruobjectref(c);
        }
        maplayerrefs = new Uruobjectref[lightmapcount];
        for(int i=0;i<lightmapcount;i++)
        {
            maplayerrefs[i] = new Uruobjectref(c);
        }
    }
    public void compile(Bytedeque data)
    {
        parent.compile(data);
        data.writeInt(u1);
        data.writeInt(flags);
        data.writeInt(layercount);
        data.writeInt(lightmapcount);
        for(int i=0;i<layercount;i++)
        {
            layerrefs[i].compile(data);
        }
        for(int i=0;i<lightmapcount;i++)
        {
            maplayerrefs[i].compile(data);
        }
    }
}
