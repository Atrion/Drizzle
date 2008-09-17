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

import shared.readexception;
import uru.context; import shared.readexception;
import shared.m;
import uru.Bytedeque;
import uru.Bytestream;

/**
 *
 * @author user
 */
public class PrpRootObject extends uruobj
{
    public Objheader header;
    PrpObject prpobject;
    public boolean isRaw = false;
    byte[] rawdata;
    int readversion;
    
    public PrpRootObject(context c, boolean isRaw, int length) throws readexception
    {
        int headerStart = c.in.getAbsoluteOffset();
        header = new Objheader(c);
        int headerEnd = c.in.getAbsoluteOffset();
        this.isRaw = isRaw;
        this.readversion = c.readversion;
        //String breakname = "map #2159";
        String breakname = "fanroomcrank_drag";
        Typeid breaktype = null;
        //Typeid breaktype = Typeid.plATCAnim;
        if(header.desc.objectname.toString().toLowerCase().startsWith(breakname.toLowerCase()) && (breaktype==null || breaktype==header.desc.objecttype))
        {
            int breakdummy = 0;
        }
        if(!isRaw)
        {
            prpobject = new PrpObject(c, header.objecttype);
        }
        else
        {
            rawdata = c.readBytes(length-(headerEnd-headerStart));
        }
    }
    
    private PrpRootObject(){}
    
    public void parseRawDataNow() throws readexception
    {
        if(isRaw)
        {
            context c = context.createFromBytestream(new Bytestream(rawdata));
            c.readversion = this.readversion;
            prpobject = new PrpObject(c, header.objecttype);
            isRaw = false;
        }
    }
    
    /*public static PrpRootObject createAsRawData(context c, int length) throws readexception
    {
        PrpRootObject result = new PrpRootObject();
        result.header = new Objheader(c);
        result.rawdata = c.readBytes(length);
        return result;
    }*/
    
    public void compile(Bytedeque c)
    {
        //header.compile(c); //should we have this here?
        if(!isRaw)
        {
            prpobject.compile(c);
        }
        else
        {
            m.warn("Untested compilation in PrpRootObject.");
            c.writeBytes(rawdata);
        }
    }
    
    public String toString()
    {
        return prpobject.toString();
    }
    
    public <T> T castTo() //java.lang.Class<T> objclass)
    {
        T result = (T)this.prpobject.object;
        return result;
    }
    
    public uruobj getObject()
    {
        return prpobject.object;
    }
    
    
}
