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
import shared.e;
import shared.m;
import shared.b;
//import java.util.Vector;

/**
 *
 * @author user
 */
public class x0056OmniLightInfo extends uruobj
{
    //Objheader xheader;
    x0054LightInfo parent;
    Flt att1; //attenuation constant 1.
    Flt att2; //attenuation constant 2.
    Flt att3; //attenuation constant 3.
    Flt cutoffdistance;
    
    public x0056OmniLightInfo(context c) throws readexception
    {
        Bytestream data = c.in;
        //if(hasHeader) xheader = new Objheader(c);
        
        parent = new x0054LightInfo(c);//,false);
        att1 = new Flt(c);
        att2 = new Flt(c);
        att3 = new Flt(c);
        cutoffdistance = new Flt(c);
    }
    public void compile(Bytedeque data)
    {
        parent.compile(data);
        att1.compile(data);
        att2.compile(data);
        att3.compile(data);
        cutoffdistance.compile(data);
    }
}
