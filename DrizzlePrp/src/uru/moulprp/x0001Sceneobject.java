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
//import java.util.Vector;

/**
 *
 * @author user
 */
public class x0001Sceneobject extends uruobj
{
    //Objheader xheader;
    PlSynchedObject parent;
    Uruobjectref spaninfo;
    Uruobjectref animationinfo;
    Uruobjectref regioninfo;
    Uruobjectref soundinfo;
    int count1;
    //Vector<Uruobjectref> objectrefs1 = new Vector<Uruobjectref>();
    Uruobjectref[] objectrefs1;
    int count2;
    //Vector<Uruobjectref> objectrefs2 = new Vector<Uruobjectref>();
    Uruobjectref[] objectrefs2;
    Uruobjectref scenenode;
    
    public x0001Sceneobject(context c)//,boolean hasHeader)
    {
        Bytestream data = c.in;
        //if(hasHeader) xheader = new Objheader(c);
        parent = new PlSynchedObject(c);//,false);
        spaninfo = new Uruobjectref(c);
        animationinfo = new Uruobjectref(c);
        regioninfo = new Uruobjectref(c);
        soundinfo = new Uruobjectref(c);
        count1 = data.readInt();
        objectrefs1 = new Uruobjectref[count1];
        for(int i=0;i<count1;i++)
        {
            objectrefs1[i] = new Uruobjectref(c);
        }
        count2 = data.readInt();
        objectrefs2 = new Uruobjectref[count2];
        for(int i=0;i<count2;i++)
        {
            objectrefs2[i] = new Uruobjectref(c);
        }
        scenenode = new Uruobjectref(c);
    }
    public void compile(Bytedeque deque)
    {
        
        parent.compile(deque);
        spaninfo.compile(deque);
        animationinfo.compile(deque);
        regioninfo.compile(deque);
        soundinfo.compile(deque);
        deque.writeInt(count1);
        for(int i=0;i<count1;i++)
        {
            objectrefs1[i].compile(deque);
        }
        deque.writeInt(count2);
        for(int i=0;i<count2;i++)
        {
            objectrefs2[i].compile(deque);
        }
        scenenode.compile(deque);
    }
}
