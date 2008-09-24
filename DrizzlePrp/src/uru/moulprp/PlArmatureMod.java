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


public class PlArmatureMod extends uruobj
{
    PlAGMasterMod parent;
    Uruobjectref ref;
    Urustring s1;
    int count;
    PrpTaggedObject[] objects;
    byte b1;
    Uruobjectref xref2;
    int u1;
    byte b2;
    Uruobjectref xref3;
    //offset 48???
    Vertex v1;
    Vertex v2;
    Flt f1;
    Flt f2;
    
    PlArmatureModBase xbase;
    int xu1;
    byte xb1;
    Uruobjectref xref;
    
    public PlArmatureMod(context c) throws readexception
    {
        if(c.readversion==4)
        {
            xbase = new PlArmatureModBase(c);
            s1 = new Urustring(c);
            xu1 = c.readInt();
            xb1 = c.readByte();
            if(xb1!=0)
            {
                xref = new Uruobjectref(c);
            }
            throw new shared.readwarningexception("PlArmatureMod: can read okay, but throwing exception to ignore, since I haven't implemented conversion.");
        }
        else if(c.readversion==3)
        {
            parent = new PlAGMasterMod(c);
            //c.readInt();
            ref = new Uruobjectref(c);
            s1 = new Urustring(c);
            count = c.readInt();
            objects = c.readVector(PrpTaggedObject.class, count);
            b1 = c.readByte();
            if(b1!=0)
            {
                xref2 = new Uruobjectref(c);
            }
            u1 = c.readInt();
            b2 = c.readByte();
            if(b2!=0)
            {
                xref3 = new Uruobjectref(c);
            }
            v1 = new Vertex(c);
            v2 = new Vertex(c);
            f1 = new Flt(c);
            f2 = new Flt(c);
        }
    }
    
    public void compile(Bytedeque c)
    {
        m.warn("compile not implemented."+this.toString());
        m.warn("not tested with pots."+this.toString());
    }
    
    public static class PlArmatureModBase extends uruobj
    {
        PlAGMasterMod parent;
        int count1;
          //uruobjectref
          //int count
          //uruobjectref[count]
        int count2;
          //??? * count2
        PrpTaggedObject[] objects;
        Uruobjectref ref1;
        
        public PlArmatureModBase(context c) throws readexception
        {
            parent = new PlAGMasterMod(c);
            count1 = c.readInt();
            c.readVector(subPlArmatureModBase.class, count1);
            count2 = c.readInt();
                //Urustring str = new Urustring(c);
            objects = c.readVector(PrpTaggedObject.class, count2);
            ref1 = new Uruobjectref(c);
        }
        
        public static class subPlArmatureModBase extends uruobj
        {
            Uruobjectref ref;
            int count;
            Uruobjectref[] refs;
            
            public subPlArmatureModBase(context c) throws readexception
            {
                ref = new Uruobjectref(c);
                count = c.readInt();
                refs = c.readVector(Uruobjectref.class, count);
            }
        }
    }
    
    
    
}
