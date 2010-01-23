/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uru.moulprp;

import uru.*;
import shared.*;

public class PfGUIControlMod extends uruobj
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

}
