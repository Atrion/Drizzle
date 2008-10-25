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
//import java.util.Vector;

//sub_5C1A10
public class x0003Bitmap extends uruobj
{
    //Objheader xheader;
    //x0002Keyedobject parent;
    byte version;
    byte bpp;
    byte cpb;
    short flags;
    byte type;
    byte xtexel_size;
    byte subtype;
    int u1; //low modified time.
    int u2; //high modified time.
    
    public x0003Bitmap(context c)//,boolean hasHeader)
    {
        Bytestream data = c.in;
        //if(hasHeader) xheader = new Objheader(c);
        //parent = new x0002Keyedobject(data);
        version = data.readByte(); e.ensureflags(version,2);
        bpp = data.readByte(); e.ensureflags(bpp,32);
        cpb = data.readByte(); //unknown //space?
        flags = data.readShort();
        type = data.readByte();
        if(type!=0 && type!=2)
        {
            xtexel_size = data.readByte(); //bytes per 4x4 texel.
        }
        subtype = data.readByte(); e.ensureflags(subtype,0,1,5); //1=DXT1, 2=DXT2, ... 5=DXT5
        u1 = data.readInt();
        u2 = data.readInt();
    }
    public void compile(Bytedeque deque)
    {
        deque.writeByte(version);
        deque.writeByte(bpp);
        deque.writeByte(cpb);
        deque.writeShort(flags);
        deque.writeByte(type);
        if(type!=0 && type!=2)
        {
            deque.writeByte(xtexel_size);
        }
        deque.writeByte(subtype);
        deque.writeInt(u1);
        deque.writeInt(u2);
    }
    private x0003Bitmap(){}
    public static x0003Bitmap createForDynamicTextMap()
    {
        x0003Bitmap result = new x0003Bitmap();
        
        result.version = 2;
        result.bpp = 32;
        result.cpb = 1;
        result.flags = 1; //0 is none, 1 is that it has an alpha channel.
        result.type = 0;
        result.subtype = 0;
        result.u1 = 0;
        result.u2 = 0;
        
        return  result;
    }
}
