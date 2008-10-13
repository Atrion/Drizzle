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
//import java.util.Vector;

//This is a little different than Cobbs.
public class PlSingleModifier extends uruobj
{
    //Objheader xheader;
    
    x001EModifier parent;
    HsBitVector flagvector;
    //int u1;
    //int flag;
    
    public PlSingleModifier(context c)//,boolean hasHeader)
    {
        //if(hasHeader) xheader = new Objheader(c);
        
        parent = new x001EModifier(c);//,false);
        flagvector = new HsBitVector(c);
        //u1 = c.readInt(); e.ensure(u1==1); //this should apparently be an hsBitVector.
        //flag = c.readInt();
    }
    private PlSingleModifier(){}
    public static PlSingleModifier createDefault()
    {
        PlSingleModifier result = new PlSingleModifier();
        result.parent = x001EModifier.createDefault();
        result.flagvector = HsBitVector.createDefault();
        return result;
    }
    public void compile(Bytedeque data)
    {
        parent.compile(data);
        flagvector.compile(data);
        //data.writeInt(u1);
        //data.writeInt(flag);
    }
    
}
