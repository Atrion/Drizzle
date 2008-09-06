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


public class PlLayerBink extends uruobj
{
    PlLayerAVI parent;
    //int u1;
    
    public PlLayerBink(context c) throws readexception
    {
        if(c.readversion!=4) throw new readexception("PlLayerBink currently can only read from myst5");
        //although the class is present in Pots, it isn't actually present in any prp files.
        parent = new PlLayerAVI(c);
        
        //u1 = c.readInt();
        
    }
    
    public void compile(Bytedeque c)
    {
        m.msg("PlLayerBink: attempting to convert; may cause Uru to crash.");
        parent.compile(c);
    }
    
    public static class PlLayerAVI extends uruobj
    {
        PlLayerAnimation parent;
        //int u1;
        //byte[] bs1;
        Bstr filename;
        Uruobjectref uref1;
        Uruobjectref uref2;
        int u2;
        
        public PlLayerAVI(context c) throws readexception
        {
            parent = new PlLayerAnimation(c);
            //u1 = c.readInt();
            //bs1 = c.readBytes(u1);
            filename = new Bstr(c);
            uref1 = new Uruobjectref(c); //none in direbo, at least.
            uref2 = new Uruobjectref(c); //none in direbo, at least.
            u2 = c.readInt(); //0 in direbo, at least.
            if((u2&4)!=0)
            {
                m.msg("PlLayerAVI: Unhandled case.");
            }
            if(shared.State.AllStates.getStateAsBoolean("reportAviFiles")) m.msg("AviFile: "+filename.toString());
        }
        
        public void compile(Bytedeque c)
        {
            //m.warn("compile not implemented."+this.toString());
            //m.warn("not tested with pots."+this.toString());
            parent.compile(c);
            filename.compile(c);
            //uref1, uref2, and u2 aren't present in pots.
        }
        
    }
}
