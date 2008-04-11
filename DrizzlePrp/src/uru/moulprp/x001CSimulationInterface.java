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
//import java.util.Vector;

/**
 *
 * @author user
 */
public class x001CSimulationInterface extends uruobj
{
    //Objheader xheader;
    PlObjInterface parent;
    //int count;
    //int[] props;
    HsBitVector props;
    int u2;
    Uruobjectref physical;
    
    public x001CSimulationInterface(context c) throws readexception
    {
        //if(hasHeader) xheader = new Objheader(c);
        
        parent = new PlObjInterface(c);//,false);
        //count = c.readInt();
        //props = c.in.readInts(count);
        props = new HsBitVector(c); //always empty in both pots and moul.
        u2 = c.readInt(); //always zero in both pots and moul.
        physical = new Uruobjectref(c);
    }
    public void compile(Bytedeque data)
    {
        parent.compile(data);
        //data.writeInt(count);
        //data.writeInts(props);
        props.compile(data);
        data.writeInt(u2);
        physical.compile(data);
    }
}
