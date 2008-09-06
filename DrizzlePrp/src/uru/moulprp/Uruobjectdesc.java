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
import uru.e;
import shared.m;
import uru.Bytedeque;

/**
 *
 * @author user
 */
public class Uruobjectdesc extends uruobj
{
    byte flag;
    //int pageid;
    public Pageid pageid;
    //short pagetype;
    Pagetype pagetype;
    byte xu1;
    //short objecttype;
    public Typeid objecttype;
    public int objectnumber;
    public Urustring objectname;
    //int xsomeid;
    //int xclientid;
    //byte xu1;
    short xm5unknown;
    
    public Uruobjectdesc(context c)
    {
        Bytestream data = c.in;
        
        flag = data.readByte(); e.ensureflags(flag,0x00,0x02,0x04);//should be 0 normally,1 and 2 also happen, but we need to study them. 0x04 occurs in Myst5 for example.
        //pageid = data.readInt();
        pageid = new Pageid(c);
        if(pageid.prefix==97)
        {
            int dummy=0;
        }
        //pagetype = data.readShort(); e.ensure(pagetype,0,4,8,16,20); //should this be a byte? //0=page, 4=global, 8=texture/builtin.
        
        if(c.readversion==3)
        {
            pagetype = new Pagetype(c);
            if(flag==0x02)
            {
                xu1 = data.readByte();
            }
            objecttype = Typeid.Read(c);
            objectname = new Urustring(c);
        }
        else if(c.readversion==4)
        {
            xm5unknown = c.readShort(); //only in crowthistle?
            pagetype = new Pagetype(c);
            objecttype = Typeid.Read(c);
            objectnumber = data.readInt(); //this objects unique number in the list.(the numbering starts anew for each objecttype in each page).
            objectname = new Urustring(c);
            if(flag==0x02 || flag==0x04)
            {
                xu1 = data.readByte();
            }
        }
        else if(c.readversion==6)
        {
            pagetype = new Pagetype(c);
            if(flag==0x02)
            {
                xu1 = data.readByte();
            }
            objecttype = Typeid.Read(c);
            objectnumber = data.readInt(); //this objects unique number in the list.(the numbering starts anew for each objecttype in each page).
            objectname = new Urustring(c);
        }
        
        _staticsettings.reportReference(this);
    }
    //public static Uruobjectdesc create(Bytestream data)
    //{
    //    return new Uruobjectdesc(data);
    //}
    public void compile(Bytedeque deque)
    {
        deque.writeByte(flag);
        pageid.compile(deque);
        //deque.writeShort(pagetype);
        pagetype.compile(deque);
        if(flag==0x02)
        {
            deque.writeByte(xu1);
        }
        objecttype.compile(deque);
        //deque.appendInt(objectnumber); //not in pots
        objectname.compile(deque);
    }
    public String toString()
    {
        return objectname.toString();
    }
}
