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
import uru.e;
import uru.m;
import uru.Bytedeque;
//import java.util.Vector;

/**
 *
 * @author user
 */
public class PrpHeader extends uruobj
{
    //Bytestream data;

    int version;
    Pageid pageid;
    short pagetype;
    Urustring agename;
    Urustring pagename;
    short u1;
    int payloadlength;
    int offsetToFirstObject;
    int offsetToObjectIndex;
    short u2;
    int u3;
    int u4;
    
    //PrpObjectIndex objectindex;

    public PrpHeader(context c)
    {
        Bytestream data = c.in;
        //data = datas;
        version = data.readInt(); e.ensure(version,5,6);
        
        //assign the context's version.
        if(version==5) c.readversion = 3;
        else if (version==6) c.readversion = 6;
        
        if(c.readversion==6) //moul
        {
            //version = data.readInt(); e.ensure(version,6); //version 6 for MOUL and possibly MystV.
            pageid = new Pageid(c); //the id of this page(aka prp file).
            pagetype = data.readShort(); e.ensure(pagetype,0,4,8,16,20); //should this be a byte? //0=page, 4=global, 8=texture/builtin. 16 was used for garden_district_itinerantbugcloud. 20 was used in a GlobalAnimation.
            agename = new Urustring(c); //the name of the age this prp file belongs to.
            pagename = new Urustring(c); //the pagename of this prp file.
            u1 = data.readShort(); e.ensure(u1,0x46);//sub-version?
            payloadlength = data.readInt(); //length of data after this header.
            offsetToFirstObject = data.readInt(); //absolute offset to first object(should be immediately after this header.)
            offsetToObjectIndex = data.readInt(); //absolute offset to object index.
            u2 = data.readShort();
            u3 = data.readInt();
            u4 = data.readInt();
            uru.FileUtils.AppendText(_staticsettings.outputdir+"pageid.txt", "agename="+agename+" pagename="+pagename+" pageid="+pageid.toString()+"\n");
        }
        else if(c.readversion==3) //pots
        {
            //version = data.readInt(); e.ensure(version,5);
            pageid = new Pageid(c);
            pagetype = data.readShort(); e.ensure(pagetype,0,4,8,16); //16 was garden_district_itinerantbugcloud
            agename = new Urustring(c);
            Urustring district = new Urustring(c);
            pagename = new Urustring(c);
            short majorversion = data.readShort(); e.ensure(majorversion,63);
            short minorversion = data.readShort(); e.ensure(minorversion,12);
            int unknown3 = data.readInt(); e.ensure(unknown3,0);
            int unknown4 = data.readInt(); e.ensure(unknown4,8);
            payloadlength = data.readInt();
            offsetToFirstObject = data.readInt();
            offsetToObjectIndex = data.readInt();
        }
        //process the object index, which is *not* a part of this struct.
        //objectindex = new PrpObjectIndex(new Bytestream(data,offsetToObjectIndex));
    }
    public void compile(Bytedeque deque)
    {
        m.msg("compile not implemented");
        
    }
    

}
    
