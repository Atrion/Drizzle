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

import shared.readexception;
import uru.context; import shared.readexception;
import shared.m;
import uru.Bytedeque;

/**
 *
 * @author user
 */
public class PrpRootObject extends uruobj
{
    public Objheader header;
    PrpObject prpobject;
    
    public PrpRootObject(context c) throws readexception
    {
        header = new Objheader(c);
        //String breakname = "map #2159";
        String breakname = "respwedges";
        Typeid breaktype = null;
        //Typeid breaktype = Typeid.plATCAnim;
        if(header.desc.objectname.toString().toLowerCase().startsWith(breakname.toLowerCase()) && (breaktype==null || breaktype==header.desc.objecttype))
        {
            int breakdummy = 0;
        }
        prpobject = new PrpObject(c, header.objecttype);
    }
    
    public void compile(Bytedeque c)
    {
        //header.compile(c); //should we have this here?
        prpobject.compile(c);
    }
    
    public String toString()
    {
        return prpobject.toString();
    }
    
    public <T> T castTo() //java.lang.Class<T> objclass)
    {
        T result = (T)this.prpobject.object;
        return result;
    }
    
    
}
