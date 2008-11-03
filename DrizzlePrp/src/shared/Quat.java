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

package shared;

import uru.moulprp.*;
import shared.Flt;
import uru.Bytestream;
import uru.Bytedeque;
import uru.context; import shared.readexception;
import shared.m;

/**
 *
 * @author user
 */
public class Quat extends uruobj
{
    public Flt w;
    public Flt x;
    public Flt y;
    public Flt z;

    public Quat(IBytestream c)
    {
        w = new Flt(c);
        x = new Flt(c);
        y = new Flt(c);
        z = new Flt(c);
    }
    public Quat(context c) //deprecated
    {
        //These looked different, but changing it messed up the angle of the door in EderDelin.
        //m.msg("scrambling quat.");
        if(c.readversion==6||c.readversion==4)
        {
            w = new Flt(c);
            x = new Flt(c);
            y = new Flt(c);
            z = new Flt(c);
            //w = new Flt(c);
        }
        else if(c.readversion==3)
        {
            w = new Flt(c);
            x = new Flt(c);
            y = new Flt(c);
            z = new Flt(c);
        }
    }
    public static Quat identity()
    {
        return new Quat(new Flt(0), new Flt(0), new Flt(0), new Flt(1));
    }
    public Quat(Flt w2, Flt x2, Flt y2, Flt z2)
    {
        w = w2;
        x = x2;
        y = y2;
        z = z2;
    }
    public void compile(Bytedeque data)
    {
        w.compile(data);
        x.compile(data);
        y.compile(data);
        z.compile(data);
    }
    
    public String toString()
    {
        return w.toString()
                +":"+x.toString()
                +":"+y.toString()
                +":"+z.toString();
    }
}
    
