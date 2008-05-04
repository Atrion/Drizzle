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


public class PlOneShotMod extends uruobj
{
    PlMultiModifier parent;
    Urustring animobjectname;
    Flt seekDuration;
    byte drivable;
    byte reversable;
    byte smartseek;
    byte noseek;
    
    public PlOneShotMod(context c) throws readexception
    {
        parent = new PlMultiModifier(c);
        animobjectname = new Urustring(c);
        seekDuration = new Flt(c);
        drivable = c.readByte();
        reversable = c.readByte();
        smartseek = c.readByte();
        noseek = c.readByte();
        
    }
    
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        animobjectname.compile(c);
        seekDuration.compile(c);
        c.writeByte(drivable);
        c.writeByte(reversable);
        c.writeByte(smartseek);
        c.writeByte(noseek);
                
    }
    
}
