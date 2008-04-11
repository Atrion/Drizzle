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


public class PlConditionalObject extends uruobj
{
    byte satisfied;
    byte toggle;
    
    public PlConditionalObject(context c) throws readexception
    {
        satisfied = c.readByte();
        toggle = c.readByte();
        
    }
    
    public void compile(Bytedeque c)
    {
        c.writeByte(satisfied);
        c.writeByte(toggle);
    }
    
}
