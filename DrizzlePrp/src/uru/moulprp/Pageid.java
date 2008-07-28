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
import uru.b;
import shared.m;
import shared.Bytes;

/**
 *
 * @author user
 */
//this is a class I made myself, to encapsulate changing page ids.
public class Pageid extends uruobj
{
    int prefix;
    int suffix;
    context ctx;
    
    public Pageid(context c)
    {
        int rawdata;
        if(c.readversion==6) // moul
        {
            //first 16 bits is sequence prefix, next is pageid?
            rawdata = c.in.readInt();
        }
        else if(c.readversion==3) // TPOTS 
        {
            int fixme = c.in.readInt();
            fixme = (fixme & 0x000000FF) | ((fixme & 0x0000FF00) << 8);
            rawdata = fixme;
        }
        else if(c.readversion==4) // Crowthwistle
        {
            int fixme = Bytes.Int16ToInt32(c.readShort());
            fixme = (fixme & 0x000000FF) | ((fixme & 0x0000FF00) << 8);
            rawdata = fixme;
            
        }
        else rawdata = 0;
        prefix = rawdata >> 16;
        suffix = rawdata << 16 >> 16;
         
        ctx = c;
    }
    public int getRawData()
    {
        return prefix << 16 | suffix;
    }
    public void compile(Bytedeque deque)
    {
        if(ctx.sequencePrefix!=null)
        {
            prefix = ctx.sequencePrefix;
            m.msg("Using forced sequence prefix 0x"+Integer.toHexString(prefix));
            // I have no clue why, but this seems to be necessary
            // BultIn and Textures have a prefix which is one HIGHER than the rest of the pages for that age
            // found for both Cyan and fan-created ages, it is what both PRPTool and PlasmaExplorer show
            // so I'll just believe it
            if (suffix <= 0x20) {
                ++prefix;
            }
        }
        int rawdata = getRawData();
        // convert it to TPOTS format
        m.msg("Writing "+toString());
        int newdata = (rawdata & 0x000000FF) | ((rawdata & 0x00FF0000) >>> 8);
        deque.writeInt(newdata);
    }
    
    public String toString()
    {
        return "Prefix=0x"+Integer.toHexString(prefix)+", Suffix=0x"+Integer.toHexString(suffix);
    }

}
