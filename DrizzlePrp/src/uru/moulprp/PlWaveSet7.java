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
import uru.readexception;
//import java.util.Vector;


public class PlWaveSet7 extends uruobj
{
    PlMultiModifier parent;
    Flt u1;
    waveset7sub sub1;
    int refcount1;
    Uruobjectref[] refs1;
    int refcount2;
    Uruobjectref[] refs2;
    Uruobjectref ref3;
    Uruobjectref xref4;

    
    public PlWaveSet7(context c) throws readexception
    {
        parent = new PlMultiModifier(c);
        u1 = new Flt(c);
        sub1 = new waveset7sub(c);
        refcount1 = c.readInt();
        refs1 = c.readVector(Uruobjectref.class, refcount1);
        refcount2 = c.readInt();
        refs2 = c.readVector(Uruobjectref.class, refcount2);
        ref3 = new Uruobjectref(c);
        //_EDI+104 = PlMultiModifier.count
        //_EDI+100 = PlMultiModifier.DWArray
        if(parent.count!=0)
        {
            if((parent.DWarray[0] & 0x10000)!=0)
            {
                //m.msg("PlWaveSet7: untested.");
                xref4 = new Uruobjectref(c);
            }
        }
    }
    
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        u1.compile(c);
        sub1.compile(c);
        c.writeInt(refcount1);
        c.writeVector(refs1);
        c.writeInt(refcount2);
        c.writeVector(refs2);
        ref3.compile(c);
        
        if(parent.count!=0)
        {
            if((parent.DWarray[0] & 0x10000)!=0)
            {
                xref4.compile(c);
            }
        }
    }
    
    public static class waveset7sub extends uruobj
    {
        Flt[] u2; //5
        Flt[] u3; //5
        Flt u4;
        Vertex u5;
        Vertex u6;
        Flt u7;
        Vertex u8;
        Vertex u9;
        Vertex u10;
        Vertex u11;
        Flt[] u12; //25, actually seperately listed.
        Vertex u13;
        Flt u14;
        Flt u15;
        
        public waveset7sub(context c) throws readexception
        {
            u2 = c.readVector(Flt.class, 5);
            u3 = c.readVector(Flt.class, 5);
            u4 = new Flt(c);
            u5 = new Vertex(c);
            u6 = new Vertex(c);
            u7 = new Flt(c);
            u8 = new Vertex(c);
            u9 = new Vertex(c);
            u10 = new Vertex(c);
            u11 = new Vertex(c);
            u12 = c.readVector(Flt.class, 25);
            u13 = new Vertex(c);
            u14 = new Flt(c);
            u15 = new Flt(c);
        }
        
        public void compile(Bytedeque c)
        {
            c.writeVector(u2);
            c.writeVector(u3);
            u4.compile(c);
            u5.compile(c);
            u6.compile(c);
            u7.compile(c);
            u8.compile(c);
            u9.compile(c);
            u10.compile(c);
            u11.compile(c);
            c.writeVector(u12);
            u13.compile(c);
            u14.compile(c);
            u15.compile(c);
        }
    }
}
