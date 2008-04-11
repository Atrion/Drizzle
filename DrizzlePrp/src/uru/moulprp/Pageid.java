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
import uru.b;
import uru.m;

/**
 *
 * @author user
 */
//this is a class I made myself, to encapsulate changing page ids.
public class Pageid extends uruobj
{
    int rawdata;
    
    public Pageid(context c)
    {
        if(c.readversion==6)
        {
            rawdata = c.in.readInt();
        }
        else if(c.readversion==3)
        {
            int fixme = c.in.readInt();
            fixme = (fixme & 0x000000FF) | ((fixme & 0x0000FF00) << 8);
            rawdata = fixme;
        }
    }
    public void compile(Bytedeque deque)
    {
        int newdata = (rawdata & 0x000000FF) | ((rawdata & 0x00FF0000) >>> 8);
        //todo: comment out the next line, it will break things.
        byte sp = _staticsettings.sequencePrefix;
        if(sp!=0x00)
        {
            m.msg("Using forced sequence prefix.");
            int FAKErawdata = b.ByteToInt32(sp) << 16;
            newdata = (rawdata & 0x000000FF) | ((FAKErawdata & 0x00FF0000) >>> 8);
        }
        deque.writeInt(newdata);
    }
    
    public String toString()
    {
        return "Pageid=0x"+Integer.toHexString(rawdata);
    }

}
