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

import uru.context;
import shared.Bytes;
import uru.Bytedeque;
import uru.e;

public class Pagetype extends uruobj
{
    short pagetype;
    
    public Pagetype(context c)
    {
        if(c.readversion==6 || c.readversion==3)
        {
            pagetype = c.readShort();
        }
        else if(c.readversion==4)
        {
            pagetype = Bytes.ByteToInt16(c.readByte());
        }
        e.ensure(pagetype,0,4,8,16,20); //should this be a byte? //0=page, 4=global, 8=texture/builtin. 16 was used for garden_district_itinerantbugcloud. 20 was used in a GlobalAnimation.
    }
    
    public void compile(Bytedeque c)
    {
        c.writeShort(pagetype);
    }
    
    public String toString()
    {
        return Short.toString(pagetype);
    }
}
