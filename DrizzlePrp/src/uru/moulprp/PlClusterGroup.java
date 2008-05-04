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

//I reverse-engineered this myself, via decompilation.
public class PlClusterGroup extends uruobj
{
    //hsKeyedObject
    short varA;
    short varB;
    short varC;
    byte[] block1;
    byte[] block2;
    
    Uruobjectref ref;
    int count;
    subclustergroup[] subclustergroups;
    int count2;
    Uruobjectref[] refs;
    int count3;
    Uruobjectref[] refs2;
    Flt u1;
    Flt u2;
    int u3;
    
    Uruobjectref endref;
    
    public PlClusterGroup(context c) throws readexception
    {
        //sub_7de8f0
        varA = c.readShort();
        varB = c.readShort();
        varC = c.readShort();
        int A = b.Int16ToInt32(varA);
        int B = b.Int16ToInt32(varB);
        int C = b.Int16ToInt32(varC);
        int X = this.getx(varB);
        block1 = c.readBytes(A*X);
        block2 = c.readBytes(6*C);
        
        ref = new Uruobjectref(c);  //material
        count = c.readInt();
        subclustergroups = new subclustergroup[count];
        for(int i=0;i<count;i++)
        {
            subclustergroups[i] = new subclustergroup(c,varA);
        }

        count2 = c.readInt();
        refs = new Uruobjectref[count2];
        for(int i=0;i<count2;i++)
        {
            refs[i] = new Uruobjectref(c); //visregion
        }
        
        count3 = c.readInt();
        refs2 = new Uruobjectref[count3];
        for(int i=0;i<count3;i++)
        {
            refs2[i] = new Uruobjectref(c); //empty
        }
        
        u1 = new Flt(c);
        u2 = new Flt(c);
        u3 = c.readInt(); //values are 0x40000000, 40000004, 40000005, ...8, ...9, ...c, ...d, ...e, ...f, ...1, 20000004, 20000005, and perhaps a few others.  Pots and Moul seem to agree on the kinds that appear.
        
        endref = new Uruobjectref(c); //scenenode
    }
    private int getx(short B)
    {
        int X = 0;
        
        if (((B>>>0) & 0x01)!=0) X+=12;
        if (((B>>>1) & 0x01)!=0) X+=12;
        if (((B>>>2) & 0x01)!=0) X+=4;
        if (((B>>>3) & 0x01)!=0) X+=4;
        if (((B>>>4) & 0x0F)!=0) X+=12*((B>>>4) & 0x0F);
        if (((B>>>8) & 0x03)!=0) X+=4*((B>>>8) & 0x03);
        if (((B>>>10) & 0x01)!=0) X+=4;
        
        return X;
    }
    public void compile(Bytedeque c)
    {
        //sub_7de8f0
        c.writeShort(varA);
        c.writeShort(varB);
        c.writeShort(varC);
        int A = b.Int16ToInt32(varA);
        int B = b.Int16ToInt32(varB);
        int C = b.Int16ToInt32(varC);
        int X = this.getx(varB);
        c.writeBytes(block1);
        c.writeBytes(block2);

        ref.compile(c);
        c.writeInt(count);
        for(int i=0;i<count;i++)
        {
            subclustergroups[i].compile(c,varA);
        }

        c.writeInt(count2);
        for(int i=0;i<count2;i++)
        {
            refs[i].compile(c);
        }
        //c.writeInt(0);
        
        c.writeInt(count3);
        for(int i=0;i<count3;i++)
        {
            refs2[i].compile(c);
        }
        
        u1.compile(c);
        u2.compile(c);
        c.writeInt(u3);
        
        endref.compile(c);
    }
    
    public static class subclustergroup extends uruobj
    {
        byte varF;
        Flt u2;
        int count;
        subsubcg[] subsubcgs;
        
        public subclustergroup(context c, short varA) throws readexception
        {
            varF = c.readByte();
            u2 = new Flt(c);
            
            count = c.readInt();
            subsubcgs = new subsubcg[count];
            for(int i=0;i<count;i++)
            {
                subsubcgs[i] = new subsubcg(c,varF, varA);
            }
        }
        
        public void compile(Bytedeque c, short varA)
        {
            c.writeByte(varF);
            u2.compile(c);
            
            c.writeInt(count);
            for(int i=0;i<count;i++)
            {
                subsubcgs[i].compile(c,varF, varA);
            }
        }
        
        public static class subsubcg extends uruobj
        {
            byte[] u1;
            byte[] u2;
            byte[] u3;
            
            public subsubcg(context c, byte varF, short varA) throws readexception
            {
                int varG = b.Int16ToInt32(varA);
                u1 = c.readBytes(48);
                int special1 = this.getspecial1(varF);
                u2 = c.readBytes(varG*special1);
                int special2 = this.getspecial2(varF);
                u3 = c.readBytes(varG*special2);
            }
            
            public void compile(Bytedeque c, byte varF, short varA)
            {
                int varG = b.Int16ToInt32(varA);
                c.writeBytes(u1);
                int special1 = this.getspecial1(varF);
                c.writeBytes(u2);
                int special2 = this.getspecial2(varF);
                c.writeBytes(u3);
            }
            
            private int getspecial2(byte varF) //sub_7d5200
            {
                int v1 = (b.ByteToInt32(varF) & 0x0F);
                if(v1<=64)
                {
                    if(v1==64) return 2;
                    if(v1==16 || v1==32) return 1;
                    return 0;
                }
                if(v1==128) return 3;
                if(v1!=256) return 0;
                return 4;
            }
            private int getspecial1(byte varF)
            {
                int v1 = (b.ByteToInt32(varF) & 0x0F);
                switch(v1)
                {
                    case 1: return 3;
                    case 2: return 6;
                    case 4: return 4;
                    case 8: return 1;
                    default:
                        //throw new readexception("plclustergroup: problem in subsubcg.");
                        return 0;
                }
            }
        }
    }
}
