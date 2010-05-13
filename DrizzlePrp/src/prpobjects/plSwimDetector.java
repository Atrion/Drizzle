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

package prpobjects;

import uru.context; import shared.readexception;
import uru.Bytestream;
import uru.Bytedeque;
import shared.e;
import shared.m;
import shared.b;
import shared.readexception;
//import java.util.Vector;
import shared.*;


public class plSwimDetector extends uruobj
{
    PlSimpleRegionSensor parent;
    byte b1;
    Flt f2;
    Flt f3;
    
    public plSwimDetector(context c) throws readexception
    {
        parent = new PlSimpleRegionSensor(c);
        b1 = c.readByte();
        f2 = new Flt(c);
        f3 = new Flt(c);
        
    }
    
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        c.writeByte(b1);
        f2.compile(c);
        f3.compile(c);
    }
    
    public static class PlSimpleRegionSensor extends uruobj
    {
        plSingleModifier parent;
        byte b1;
        PrpTaggedObject xobj2;
        byte b3;
        PrpTaggedObject xobj4;
        
        public PlSimpleRegionSensor(context c) throws readexception
        {
            parent = new plSingleModifier(c);
            b1 = c.readByte();
            if(b1!=0)
            {
                xobj2 = new PrpTaggedObject(c);
            }
            b3 = c.readByte();
            if(b3!=0)
            {
                xobj4 = new PrpTaggedObject(c);
            }
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeByte(b1);
            if(b1!=0)
            {
                xobj2.compile(c);
            }
            c.writeByte(b3);
            if(b3!=0)
            {
                xobj4.compile(c);
            }
        }
    }
    
    public static class PlSwimMsg extends uruobj
    {
        PrpMessage.PlMessage parent;
        byte b1;
        Uruobjectref ref2;
        
        public PlSwimMsg(context c) throws readexception
        {
            parent = new PrpMessage.PlMessage(c);
            b1 = c.readByte();
            ref2 = new Uruobjectref(c);
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeByte(b1);
            ref2.compile(c);
        }
    }
}
