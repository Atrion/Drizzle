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
import java.util.Vector;

/**
 *
 * @author user
 */
//aka hsGMaterial
public class x0007Material extends uruobj
{
    //Objheader xheader;
    public PlSynchedObject parent;
    public int u1;
    public int flags;
    public int layercount;
    public int lightmapcount;
    //public Uruobjectref[] layerrefs;
    //public Uruobjectref[] maplayerrefs;
    public Vector<Uruobjectref> layerrefs = new Vector<Uruobjectref>();
    public Vector<Uruobjectref> maplayerrefs = new Vector<Uruobjectref>();
    
    public x0007Material(context c) throws readexception //,boolean hasHeader)
    {
        Bytestream data = c.in;
        //if(hasHeader) xheader = new Objheader(c);
        parent = new PlSynchedObject(c);//,false);
        u1 = data.readInt(); e.ensureflags(u1,0); //loadflags
        flags = data.readInt(); e.ensureflags(flags,0x00,0x0400,0x1000,0x2000,0x2400); //compflags
        layercount = data.readInt();
        lightmapcount = data.readInt();
        //layerrefs = new Uruobjectref[layercount];
        for(int i=0;i<layercount;i++)
        {
            //layerrefs[i] = new Uruobjectref(c);
            layerrefs.add(new Uruobjectref(c));
        }
        //maplayerrefs = new Uruobjectref[lightmapcount];
        for(int i=0;i<lightmapcount;i++)
        {
            //maplayerrefs[i] = new Uruobjectref(c);
            maplayerrefs.add(new Uruobjectref(c));
        }
    }
    public void compile(Bytedeque data)
    {
        parent.compile(data);
        data.writeInt(u1);
        data.writeInt(flags);
        /*data.writeInt(layercount);
        data.writeInt(lightmapcount);
        for(int i=0;i<layercount;i++)
        {
            layerrefs[i].compile(data);
        }
        for(int i=0;i<lightmapcount;i++)
        {
            maplayerrefs[i].compile(data);
        }*/
        data.writeInt(layerrefs.size());
        data.writeInt(maplayerrefs.size());
        for(int i=0;i<layerrefs.size();i++)
        {
            layerrefs.get(i).compile(data);
        }
        for(int i=0;i<maplayerrefs.size();i++)
        {
            maplayerrefs.get(i).compile(data);
        }
    }
    private x0007Material(){}
    public x0007Material deepClone()
    {
        x0007Material result = new x0007Material();
        result.parent = parent.deepClone();
        result.flags = flags;
        result.layercount = layercount;
        result.layerrefs = new Vector();
        for(Uruobjectref ref: layerrefs)
        {
            result.layerrefs.add(ref.deepClone());
        }
        result.u1 = u1;
        result.lightmapcount = lightmapcount;
        result.maplayerrefs = new Vector();
        for(Uruobjectref ref: maplayerrefs)
        {
            result.maplayerrefs.add(ref.deepClone());
        }
        return result;
    }
}
