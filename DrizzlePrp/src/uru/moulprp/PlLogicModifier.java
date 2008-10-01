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


public class PlLogicModifier extends uruobj
{
    public PlLogicModBase parent;
    int conditionalcount;
    public Uruobjectref[] conditionals;
    int u1;
    
    Uruobjectref xref;
    
    public PlLogicModifier(context c) throws readexception
    {
        parent = new PlLogicModBase(c);
        conditionalcount = c.readInt();
        conditionals = c.readVector(Uruobjectref.class, conditionalcount);
        u1 = c.readInt();
        if(c.readversion==4)
        {
            xref = new Uruobjectref(c); //e.g. KeepClickLinkLaki(plPickingDetector)
        }
    }
    
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        c.writeInt(conditionalcount);
        c.writeVector(conditionals);
        c.writeInt(u1);
    }
    
    public static class PlLogicModBase extends uruobj
    {
        PlSingleModifier parent;
        int count; //we don't handle it if this is not 0. This could be changed in the future, though.
        //short[] unused;
        public PrpMessage message;
        HsBitVector u1;
        byte u2;
        
        public PlLogicModBase(context c) throws readexception
        {
            parent = new PlSingleModifier(c);
            count = c.readInt(); e.ensure(count==0); //we don't handle the other situation, which is an array of PrpMessages
            //unused = c.readInts(count);
            message = new PrpMessage(c);
            u1 = new HsBitVector(c);
            u2 = c.readByte();

        }

        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeInt(count);
            //unhandled part if count!=0
            message.compile(c);
            u1.compile(c);
            c.writeByte(u2);
        }
    }
    
}
