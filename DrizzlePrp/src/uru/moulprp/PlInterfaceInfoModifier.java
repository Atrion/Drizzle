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


public class PlInterfaceInfoModifier extends uruobj
{
    PlSingleModifier parent;
    int count;
    Uruobjectref[] logicmodifiers; //according to cobbs these are LogicModifiers
    
    public PlInterfaceInfoModifier(context c) throws readexception
    {
        parent = new PlSingleModifier(c);
        count = c.readInt();
        logicmodifiers = c.readArray(Uruobjectref.class, count);
        
    }
    
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        c.writeInt(count);
        c.writeArray(logicmodifiers);
    }
    
}
