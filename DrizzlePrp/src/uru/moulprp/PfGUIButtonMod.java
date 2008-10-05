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
import shared.readexception;
//import java.util.Vector;


public class PfGUIButtonMod extends uruobj
{
    PfGUIControlMod parent;
    int refcount;
    Uruobjectref[] refs;
    Urustring str;
    int refcount2;
    Uruobjectref[] refs2;
    Urustring str2;
    int u1;
    byte b1;
    int xu1;
    int xu2;
    int xu3;
    Uruobjectref xref3;
    
    public PfGUIButtonMod(context c) throws readexception
    {
        parent = new PfGUIControlMod(c);
        refcount = c.readInt();
        refs = c.readArray(Uruobjectref.class, refcount); //really uruobjectrefs?
        str = new Urustring(c);
        refcount2 = c.readInt();
        refs2 = c.readArray(Uruobjectref.class, refcount2); //really uruobjectrefs?
        str2 = new Urustring(c);
        if(c.readversion==3)
        {
            //b1 is always 0
            //u1: 1(x1) 2(x4) 0(x~230)
            //so, we could just write 0,0
            u1 = c.readInt();
            b1 = c.readByte();
            //m.msg("pfguibuttonmod: delme: "+Integer.toString(u1)+":"+Byte.toString(b1));
        }
        else if(c.readversion==4||c.readversion==6) //does 6 go here?
        {
            //these have no clear relation to the pots flags above.
            xu1 = c.readInt();
            xu2 = c.readInt();
            xu3 = c.readInt();
            xref3 = new Uruobjectref(c);
            //m.msg("pfguibuttonmod: delme: "+Integer.toString(xu1)+":"+Integer.toString(xu2)+":"+Integer.toString(xu3)+":"+xref3.toString());
        }
    }
    
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        c.writeInt(refcount);
        c.writeArray(refs);
        str.compile(c);
        c.writeInt(refcount2);
        c.writeArray(refs2);
        str2.compile(c);

        //these next 2 lines are justified by the fact that they are usually 0, and I couldn't see a relation between them and the new flags.
        c.writeInt(0);
        c.writeByte((byte)0);
    }
    public static class PfGUIDialogMod extends uruobj
    {
        PlSingleModifier parent;
        Uruobjectref ref;
        byte[] bs1;
        int refcount;
        Uruobjectref[] refs;
        int u1;
        Uruobjectref ref2;
        int u2;
        //wacky thing
        whattheheck wha1;
        Uruobjectref ref3;
        
        public PfGUIDialogMod(context c) throws readexception
        {
            parent = new PlSingleModifier(c);
            ref = new Uruobjectref(c);
            bs1 = c.readBytes(128);
            refcount = c.readInt();
            refs = c.readArray(Uruobjectref.class, refcount);
            u1 = c.readInt();
            ref2 = new Uruobjectref(c);
            u2 = c.readInt();
            wha1 = new whattheheck(c);
            ref3 = new Uruobjectref(c);
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            ref.compile(c);
            c.writeBytes(bs1);
            c.writeInt(refcount);
            c.writeArray(refs);
            c.writeInt(u1);
            ref2.compile(c);
            c.writeInt(u2);
            wha1.compile(c);
            ref3.compile(c);
        }
            
    }
    static class whattheheck
    {
        Flt[] xflts;
        int xu3;
        Urustring xstr1;
        byte xb4;
        byte xb5;

        public whattheheck(context c) throws readexception
        {
            //16 flt, 1 int, 1 urustring, 2 bytes
            xflts = c.readArray(Flt.class, 16);
            xu3 = c.readInt();
            xstr1 = new Urustring(c);
            xb4 = c.readByte();
            xb5 = c.readByte();
        }
        public void compile(Bytedeque c)
        {
            c.writeArray(xflts);
            c.writeInt(xu3);
            xstr1.compile(c);
            c.writeByte(xb4);
            c.writeByte(xb5);
        }
    }
    
    public static class PfGUIKnobCtrl extends uruobj
    {
        PfGUIValueCtrl parent;
        int count;
        Uruobjectref[] refs;
        Urustring str;
        Vertex v1;
        Vertex v2;
        
        public PfGUIKnobCtrl(context c) throws readexception
        {
            parent = new PfGUIValueCtrl(c);
            count = c.readInt();
            refs = c.readArray(Uruobjectref.class, count);
            str = new Urustring(c);
            v1 = new Vertex(c);
            v2 = new Vertex(c);
            if(c.readversion==4)
            {
                int u1 = c.readInt();
                int u2 = c.readInt();
                int u3 = c.readInt();
                int u4 = c.readInt();
                Flt f1 = new Flt(c);
                Flt f2 = new Flt(c);
                Flt f3 = new Flt(c);
                Flt f4 = new Flt(c);
            }
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeInt(count);
            c.writeArray(refs);
            str.compile(c);
            v1.compile(c);
            v2.compile(c);
        }
    }
    
    public static class PfGUIValueCtrl extends uruobj
    {
        PfGUIControlMod parent;
        Flt f1;
        Flt f2;
        Flt f3;
        
        public PfGUIValueCtrl(context c) throws readexception
        {
            parent = new PfGUIControlMod(c);
            f1 = new Flt(c);
            f2 = new Flt(c);
            f3 = new Flt(c);
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            f1.compile(c);
            f2.compile(c);
            f3.compile(c);
        }
    }
    
    public static class PfGUIControlMod extends uruobj
    {
        PlSingleModifier parent;
        int u1;
        byte b1;
        int u2; //something irrelevant done with this.
        byte b2;
        Uruobjectref xref1;
        Uruobjectref xref2;
        byte b3;
         //Flt[] xflts;
         //int xu3;
         //Urustring xstr1;
         //byte xb4;
         //byte xb5;
        whattheheck wha1;
        byte b4;
        int[] ints1;
        //HsBitVector bv1;
        Uruobjectref ref1;
        Uruobjectref ref2;
        
        public PfGUIControlMod(context c) throws readexception
        {
            parent = new PlSingleModifier(c);
            u1 = c.readInt();
            b1 = c.readByte();
            u2 = c.readInt(); //case 0:nothing, case 1: read int x, and x bytes, case2,3: nothing?
            b2 = c.readByte();
            if(b2!=0)
            {
                xref1 = new Uruobjectref(c);
                xref2 = new Uruobjectref(c);
            }
            b3 = c.readByte();
            if(b3!=0)
            {
                wha1 = new whattheheck(c);
            }
            b4 = c.readByte();
            if(b4!=0)
            {
                ints1 = c.readInts(b.ByteToInt32(b4));
            }
            //bv1 = new HsBitVector(c); //if it has more than 255 entries, this is broken.
            if(parent.flagvector.count!=0 && (parent.flagvector.get(0)&0x40)!=0 )
            {
                ref1 = new Uruobjectref(c); //92 is bitvector count, 88 is ref to bitvector vals
            }
            ref2 = new Uruobjectref(c);
        }
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeInt(u1);
            c.writeByte(b1);
            c.writeInt(u2);
            c.writeByte(b2);
            if(b2!=0)
            {
                xref1.compile(c);
                xref2.compile(c);
            }
            c.writeByte(b3);
            if(b3!=0)
            {
                wha1.compile(c);
            }
            c.writeByte(b4);
            if(b4!=0)
            {
                c.writeInts(ints1);
            }
            if(parent.flagvector.count!=0 && (parent.flagvector.get(0)&0x40)!=0 )
            {
                ref1.compile(c);
            }
            ref2.compile(c);
        }
    }
}
