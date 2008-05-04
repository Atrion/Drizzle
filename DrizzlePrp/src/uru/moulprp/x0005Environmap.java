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

/**
 *
 * @author user
 */
public class x0005Environmap extends uruobj
{
    //Objheader xheader;
    x0003Bitmap parent;
    x0004MipMap[] sides = new x0004MipMap[6];
    
    public x0005Environmap(context c)//,boolean hasHeader)
    {
        //if(hasHeader) xheader = new Objheader(c);
        parent = new x0003Bitmap(c);//,false);
        for(int i=0;i<6;i++)
        {
            if(c.readversion==4)
            {
                //c.readByte(); //0
                //c.readInt(); //-1
                //c.readBytes(9); //0
                Uruobjectref unused = new Uruobjectref(c);
            }
            sides[i] = new x0004MipMap(c);//,false);
        }
    }
    public void compile(Bytedeque deque)
    {
        parent.compile(deque);
        for(int i=0;i<6;i++)
        {
            sides[i].compile(deque);
        }
    }
}
