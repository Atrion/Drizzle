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
import java.util.Vector;

/**
 *
 * @author user
 */
public class x0001Sceneobject extends uruobj
{
    //Objheader xheader;
    PlSynchedObject parent;
    public Uruobjectref spaninfo; //draw
    public Uruobjectref animationinfo; //simulation
    public Uruobjectref regioninfo; //coordinate
    public Uruobjectref soundinfo; //audio
    public int count1;
    public Vector<Uruobjectref> objectrefs1 = new Vector<Uruobjectref>();
    //public Uruobjectref[] objectrefs1;
    public int count2;
    public Vector<Uruobjectref> objectrefs2 = new Vector<Uruobjectref>();
    //public Uruobjectref[] objectrefs2;
    Uruobjectref scenenode;
    
    public x0001Sceneobject(context c) throws readexception //,boolean hasHeader)
    {
        Bytestream data = c.in;
        //if(hasHeader) xheader = new Objheader(c);
        parent = new PlSynchedObject(c);//,false);
        spaninfo = new Uruobjectref(c); //drawinterface
        animationinfo = new Uruobjectref(c); //simulation interface
        regioninfo = new Uruobjectref(c); //coordinateinterface
        soundinfo = new Uruobjectref(c); //audio interface
        count1 = data.readInt();
        /*objectrefs1 = new Uruobjectref[count1];
        for(int i=0;i<count1;i++)
        {
            objectrefs1[i] = new Uruobjectref(c);
        }*/
        objectrefs1 = c.readVector(Uruobjectref.class, count1);
        count2 = data.readInt();
        /*objectrefs2 = new Uruobjectref[count2];
        for(int i=0;i<count2;i++)
        {
            objectrefs2[i] = new Uruobjectref(c);
        }*/
        objectrefs2 = c.readVector(Uruobjectref.class, count2);
        scenenode = new Uruobjectref(c);
    }
    private x0001Sceneobject(){}
    public static x0001Sceneobject createDefaultWithScenenode(Uruobjectref scenenode)
    {
        x0001Sceneobject result = new x0001Sceneobject();
        result.parent = PlSynchedObject.createDefault();
        result.spaninfo = Uruobjectref.none();
        result.animationinfo = Uruobjectref.none();
        result.regioninfo = Uruobjectref.none();
        result.soundinfo = Uruobjectref.none();
        result.count1 = 0;
        //result.objectrefs1 = new Uruobjectref[0];
        result.objectrefs1 = new Vector<Uruobjectref>();
        result.count2 = 0;
        //result.objectrefs2 = new Uruobjectref[0];
        result.objectrefs2 = new Vector<Uruobjectref>();
        result.scenenode = scenenode;
        return result;
    }
    public void addToObjectrefs2(Uruobjectref ref)
    {
        count2++;
        objectrefs2.add(ref);
    }
    public void compile(Bytedeque deque)
    {
        
        parent.compile(deque);
        spaninfo.compile(deque);
        animationinfo.compile(deque);
        regioninfo.compile(deque);
        soundinfo.compile(deque);
        deque.writeInt(count1);
        /*for(int i=0;i<count1;i++)
        {
            objectrefs1[i].compile(deque);
        }*/
        deque.writeVector(objectrefs1);
        deque.writeInt(count2);
        /*for(int i=0;i<count2;i++)
        {
            objectrefs2[i].compile(deque);
        }*/
        deque.writeVector(objectrefs2);
        scenenode.compile(deque);
    }
    /*public Uruobjectref getChildByType(Typeid type)
    {
        return null;
    }*/
}
