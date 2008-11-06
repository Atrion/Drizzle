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

package realmyst;

import shared.*;

//SceneObject?
public class Count3Undone
{
    Typeid type;
    int size;
    
    int possibility;
    
    public Count3Undone(IBytestream c)
    {
        int startpos = c.getAbsoluteOffset();
        String start = "0x"+Integer.toHexString(startpos);
        type = Typeid.read(c); e.ensure(type==Typeid.count3);
        size = c.readInt();
        String end = "0x"+Integer.toHexString(startpos+size);
        
        //skip the rest.
        IBytestream d = c.Fork();
        //if(size>184)
        {
            //byte[] rawdata = c.readBytes(size-8);
            byte[] rawdata1 = c.readBytes(size-8-4);
            possibility = c.readInt(); //the texture index! -1 presumably means no texture.
            m.msg("Possibility: "+Integer.toString(possibility));
            if(true)return;
        }
        
        //just use a fork
        c = d;
        
        int u1 = c.readInt();  e.ensure(u1,1);
        
        StringAndByte sb2 = new StringAndByte(c);
        //int bytecount = c.readInt();
        //if(bytecount>256) return;
        //c.readBytes(bytecount);
        //byte endbyte = c.readByte();
        //if(true)return;
        //if(sb2.str.toString().startsWith("myst..base_mountain"))
        {
            int dummy=0;
        }
        int u3 = c.readInt(); e.ensure(u3,0,1);
        if(u3==1) 
        {
            StringAndByte sb3a = new StringAndByte(c);
            int dummy=0;
        }
        int u4 = c.readInt(); e.ensure(u4,3);
        int u5 = c.readInt(); e.ensure(u5,0);
        int u6 = c.readInt(); //e.ensure(u6,5,8,4,13,37,12,36,21,9,1,33); //13
        int u7 = c.readInt(); e.ensure(u7,1); //todo: rsetore this.
        int u8 = c.readInt(); e.ensure(u8,0,0x40c0,0x80,0x4000,0x4080,0x4040,0x60c0,0x200c0,0x20040,0x40d0,0x4050,0x4010,0x4090); //16576,128, 0x4000
        int u8b=0;
        if((u8&0x4000)!=0)
        {
            u8b = c.readInt(); e.ensure(u8b,0,2,0x20,130,8,34);
        }
        int u9 = c.readInt(); e.ensure(u9,0,128,2,3,8,32,130,1,34,160); //128
        int u10 = c.readInt(); //e.ensure(u10,0,1,0x2000,0x40,0x41,0x200,0x2040,3,2,0x4000); //1,0x2000
        int u11 = c.readInt(); //e.ensure(u11,0,0x2000,0x40,0x1,0x11,0x19,0x8,0x243,0x80,0x41,2,0x100,0x18,0x2040); //0x2000,0x40
        int u12 = c.readInt(); e.ensure(u12,0,16,17,8,4,1,24,128,256,32);
        int u13 = c.readInt(); //e.ensure(u13,0,4); //sometimes a float.
        //m.msg("count3: u8b="+Integer.toString(u8b)+" u9="+Integer.toString(u9));
        //if(true)return;
        Flt u14 = new Flt(c);
        Flt u15 = new Flt(c);
        Flt u16 = new Flt(c);
        Flt u17 = new Flt(c);
        Flt u18 = new Flt(c);
        Flt f19 = new Flt(c);
        Flt f20 = new Flt(c);
        Flt f21 = new Flt(c);
        Flt f22 = new Flt(c);
        Flt f23 = new Flt(c);
        int u25=0; Bstr s26=null;//int u26=0; Flt f27=null; Flt f28=null;
        if((u8&0x2000)!=0)
        {
            u25 = c.readInt(); e.ensure(u25,4,0);
            //u26 = c.readInt(); e.ensure(u26,8);
            //f27 = new Flt(c);
            //f28 = new Flt(c);
            s26 = new Bstr(c);
        }
        int u29=0; Bstr s30=null;
        if((u8&0x20000)!=0)
        {
            u29 = c.readInt(); e.ensure(u29,0);
            s30 = new Bstr(c);
        }
        if((u8&0x10)!=0) //16 floats; a matrix?  xform state?
        {
            Flt f31 = new Flt(c);
            //int u32 = c.readInt(); e.ensure(u32,0x80000000);
            Flt u32 = new Flt(c);
            //int u33 = c.readInt(); e.ensure(u33,0);
            Flt u33 = new Flt(c);
            Flt f34 = new Flt(c);
            //int u35 = c.readInt(); e.ensure(u35,0,0x35000000,0xc0000000);
            Flt u35 = new Flt(c);
            Flt f36 = new Flt(c);
            int u36 = c.readInt(); e.ensure(u36,0);
            //int u37 = c.readInt(); e.ensure(u35,0);
            Flt u37 = new Flt(c);
            //int u38 = c.readInt(); e.ensure(u38,0);
            Flt u38 = new Flt(c);
            //int u39 = c.readInt(); e.ensure(u39,0x80000000);
            Flt u39 = new Flt(c);
            Flt f40 = new Flt(c);
            //int u41 = c.readInt(); e.ensure(u41,0);
            Flt u41 = new Flt(c);
            int u42 = c.readInt(); e.ensure(u42,0);
            int u43 = c.readInt(); e.ensure(u43,0);
            int u44 = c.readInt(); e.ensure(u44,0);
            Flt f45 = new Flt(c);
            int dummy=0;
        }
        if((u8&0x4000)!=0)
        {
            //u8b = c.readInt(); e.ensure(u8b,0,2,0x20,130,8,34);
        }
        int u24 = c.readInt(); //e.ensure(u24,-1,13,50,0,7,20,22,47,27,28,41); //50
        
        
        int bytesleft = (startpos+size)-c.getAbsoluteOffset();
        if(bytesleft!=0)
        {
            int dummy=0;
        }
        
    }
}
