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
public class x0016DrawInterface extends uruobj
{
    //Objheader xheader;
    PlObjInterface parent;
    int subsetgroupcount;
    SubsetGroup[] subsetgroups;
    int visregioncount;
    Uruobjectref[] visibleregion;
    
    public x0016DrawInterface(context c) throws readexception
    {
        //if(hasHeader) xheader = new Objheader(c);
        parent = new PlObjInterface(c);//,false);
        subsetgroupcount = c.in.readInt();
        subsetgroups = new SubsetGroup[subsetgroupcount];
        for(int i=0;i<subsetgroupcount;i++)
        {
            subsetgroups[i] = new SubsetGroup(c);
        }
        visregioncount = c.in.readInt();
        visibleregion = new Uruobjectref[visregioncount];
        for(int i=0;i<visregioncount;i++)
        {
            visibleregion[i] = new Uruobjectref(c);
        }
        
    }
    public void compile(Bytedeque data)
    {
        parent.compile(data);
        data.writeInt(subsetgroupcount);
        for(int i=0;i<subsetgroupcount;i++)
        {
            subsetgroups[i].compile(data);
        }
        data.writeInt(visregioncount);
        for(int i=0;i<visregioncount;i++)
        {
            visibleregion[i].compile(data);
        }
    }
    
    static public class SubsetGroup extends uruobj
    {
        int subsetgroupindex;
        Uruobjectref span;
        
        public SubsetGroup(context c)
        {
            subsetgroupindex = c.in.readInt();
            span = new Uruobjectref(c);
        }
        
        public void compile(Bytedeque data)
        {
            data.writeInt(subsetgroupindex);
            span.compile(data);
        }
    }
}
