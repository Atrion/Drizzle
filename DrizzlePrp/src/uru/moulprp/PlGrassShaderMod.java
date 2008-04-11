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


public class PlGrassShaderMod extends uruobj
{
    PlSynchedObject parent;
    Uruobjectref ref;
    Flt[] u1;
    Flt[] u2;
    Flt[] u3;
    Flt[] u4;
    
    public PlGrassShaderMod(context c) throws readexception
    {
        e.ensure(c.readversion==6);
        
        parent = new PlSynchedObject(c);
        ref = new Uruobjectref(c);
        u1 = c.readVector(Flt.class, 6);
        u2 = c.readVector(Flt.class, 6);
        u3 = c.readVector(Flt.class, 6);
        u4 = c.readVector(Flt.class, 6);

        throw new readexception("plGrassShaderMod: can read okay, but failing in order to ignore.");
    }
    
    public void compile(Bytedeque c)
    {
        m.warn("compile not implemented."+this.toString());
        m.warn("not tested with pots."+this.toString());
    }
    
}
