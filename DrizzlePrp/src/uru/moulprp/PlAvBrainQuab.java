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

import shared.Flt;
import uru.context; import shared.readexception;
import uru.Bytestream;
import uru.Bytedeque;
import shared.e;
import shared.m;
import shared.b;
import shared.readexception;
//import java.util.Vector;


public class PlAvBrainQuab extends uruobj
{
    int u1;
    byte b1;
    Uruobjectref xref;
    int u2;
    Flt f1;
    Double64 d1;
    
    public PlAvBrainQuab(context c) throws readexception
    {
        u1 = c.readInt();
        b1 = c.readByte();
        if(b1!=0)
        {
            xref = new Uruobjectref(c);
        }
        u2 = c.readInt();
        f1 = new Flt(c);
        d1 = new Double64(c);
        
    }
    
    public void compile(Bytedeque c)
    {
        m.warn("compile not implemented.",this.toString());
        m.warn("not tested with pots.",this.toString());
    }
    
}
