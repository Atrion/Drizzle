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

/**
 *
 * @author user
 */
public class Uruobjectdesc extends uruobj
{
    byte flag;
    //int pageid;
    Pageid pageid;
    short pagetype;
    byte xu1;
    //short objecttype;
    Typeid objecttype;
    int objectnumber;
    Urustring objectname;
    //int xsomeid;
    //int xclientid;
    //byte xu1;
    
    public Uruobjectdesc(context c)
    {
        Bytestream data = c.in;
        
        flag = data.readByte(); e.ensure(flag,0x00,0x02);//should be 0 normally,1 and 2 also happen, but we need to study them.
        //pageid = data.readInt();
        pageid = new Pageid(c);
        pagetype = data.readShort(); e.ensure(pagetype,0,4,8,16,20); //should this be a byte? //0=page, 4=global, 8=texture/builtin.
        if(flag==0x02)
        {
            xu1 = data.readByte();
        }
        //objecttype = data.readShort();
        objecttype = Typeid.Read(c);
        if(c.readversion==6)
        {
            objectnumber = data.readInt(); //this objects unique number in the list.(the numbering starts anew for each objecttype in each page).
        }
        else if(c.readversion==3)
        {
            //do nothing
        }
        objectname = new Urustring(c);
        //if(flag==0x02 || flag==0x04)
        //{
        //    xu1 = data.readByte();
        //}
        
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
        deque.writeShort(pagetype);
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
