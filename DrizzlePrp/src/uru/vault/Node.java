/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uru.vault;

import shared.*;
import uru.moulprp.HsBitVector;
import uru.moulprp.Wpstr;
import uru.moulprp.Bstr;

public class Node implements NodeTypes.ImageType
{
    public HsBitVector bv1;
    public int u2;
    public byte b3;
    public int u4;
    public int u5;
    public int u6;
    public Timestamp d7;
    public int xu8;
    public Timestamp xd9;
    public Timestamp xd10;
    public Wpstr xs11;
    //
    public int blob1;
    public int xu12;
    public int xu13;
    public int xu14;
    public int xu15;
    public int xu16;
    public int xu17;
    public int xu18;
    public int xu19;
    public Wpstr xu20;
    public Wpstr xu21;
    public Wpstr xu22;
    public Wpstr xu23;
    public Wpstr xu24;
    public Wpstr xu25;
    public Wpstr xu26;
    public Wpstr xu27;
    public Wpstr xu28;
    public Wpstr xu29;
    public Bstr xu30;
    public Bstr xu31;
    //
    //
    public int blob2;
    public int blob3;
    
    public Node(IBytestream c)
    {
        bv1 = new HsBitVector(c);
        e.ensure(bv1.count==2);
        e.ensure(bv1.get(0)==-1);
        e.ensure(bv1.get(1)==7);
        
        u2 = c.readInt();
        b3 = c.readByte(); //node type
        u4 = c.readInt();
        u5 = c.readInt();
        u6 = c.readInt();
        d7 = new Timestamp(c);
        if(bv1.flag(6)) xu8 = c.readInt();
        if(bv1.flag(7)) xd9 = new Timestamp(c);
        if(bv1.flag(9)) xd10 = new Timestamp(c);
        if(bv1.flag(10)) xs11 = new Wpstr(c);
        //if(flag(11)) 
        if(bv1.flag(11))
        {
            blob1 = c.readInt();
            int blob1b = c.readInt();
        }
        if(bv1.flag(12)) xu12 = c.readInt();
        if(bv1.flag(13)) xu13 = c.readInt();
        if(bv1.flag(14)) xu14 = c.readInt();
        if(bv1.flag(15)) xu15 = c.readInt();
        if(bv1.flag(16)) xu16 = c.readInt();
        if(bv1.flag(17)) xu17 = c.readInt();
        if(bv1.flag(18)) xu18 = c.readInt();
        if(bv1.flag(19)) xu19 = c.readInt();
        if(bv1.flag(20)) xu20 = new Wpstr(c);
        if(bv1.flag(21)) xu21 = new Wpstr(c);
        if(bv1.flag(22)) xu22 = new Wpstr(c);
        if(bv1.flag(23)) xu23 = new Wpstr(c);
        if(bv1.flag(24)) xu24 = new Wpstr(c);
        if(bv1.flag(25)) xu25 = new Wpstr(c);
        if(bv1.flag(26)) xu26 = new Wpstr(c);
        if(bv1.flag(27)) xu27 = new Wpstr(c);
        if(bv1.flag(28)) xu28 = new Wpstr(c);
        if(bv1.flag(29)) xu29 = new Wpstr(c);
        if(bv1.flag(30)) xu30 = new Bstr(c);
        if(bv1.flag(31)) xu31 = new Bstr(c);
        //if(flag(32)) 
        if(bv1.flag(32))
        {
            blob2 = c.readInt();
            int blob2b = c.readInt();
        }
        //if(flag(33)) 
        if(bv1.flag(32))
        {
            blob3 = c.readInt();
            int blob3b = c.readInt();
        }
    }

    
    /*public Typeid gettype()
    {
        switch(b3)
        {
            case 25: return Typeid.ImageNode;
            default: return Typeid.unknown;
        }
    }*/
    public <T> T castTo(Class<T> cls)
    {
        if(cls==NodeTypes.ImageType.class && b3==25) return (T)this;
        
        return null;
    }
    public String getAgeName()
    {
        return xs11.toString();
    }
    public String getCaption()
    {
        return xu20.toString();
    }
    public byte[] getImageData()
    {
        return xu30.string;
    }
    
}
