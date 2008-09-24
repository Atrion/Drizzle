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
public class x0015CoordinateInterface extends uruobj
{
    //Objheader xheader;
    PlObjInterface parent;
    public Transmatrix localToParent; //1 and 2 should be inverses of each other.
    public Transmatrix parentToLocal;
    public Transmatrix localToWorld; //3 and 4 should be inverses of each other.
    public Transmatrix worldToLocal;
    int count;
    Uruobjectref[] children;
    
    public x0015CoordinateInterface(context c) throws readexception
    {
        if(c.curRootObject.objectname.toString().toLowerCase().startsWith("ropeladder"))
        {
            int dummy=0;
        }

        Bytestream data = c.in;
        //if(hasHeader) xheader = new Objheader(c);
        parent = new PlObjInterface(c);//,false);
        localToParent = new Transmatrix(c);
        parentToLocal = new Transmatrix(c);
        localToWorld = new Transmatrix(c);
        worldToLocal = new Transmatrix(c);
        count = data.readInt();
        children = new Uruobjectref[count];
        for(int i=0;i<count;i++)
        {
            children[i] = new Uruobjectref(c);
        }
    }
    public void compile(Bytedeque deque)
    {
        parent.compile(deque);
        localToParent.compile(deque);
        parentToLocal.compile(deque);
        localToWorld.compile(deque);
        worldToLocal.compile(deque);
        deque.writeInt(count);
        for(int i=0;i<count;i++)
        {
            children[i].compile(deque);
        }
    }
}
