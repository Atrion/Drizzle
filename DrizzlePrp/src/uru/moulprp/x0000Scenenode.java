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
import uru.e;
import uru.m;
import uru.b;
//import java.util.Vector;

/**
 *
 * @author user
 */
public class x0000Scenenode extends uruobj
{
    //Objheader xheader;
    int count1;
    Uruobjectref[] objectrefs1; //all x0001Sceneobject
    int count2;
    Uruobjectref[] objectrefs2; //misc others.
    
    public x0000Scenenode(context c)
    {
        Bytestream data = c.in;
        ////if(hasHeader) xheader = new Objheader(c);
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
        
    }
    public void compile(Bytedeque deque)
    {
        m.msg("compile not implemented");
    }
    public void compileSpecial(Bytedeque deque)
    {
        //prputils.Compiler.isNormalObjectToBeIncluded(desc);
        int newcount1 = 0;
        int newcount2 = 0;

        //count refs to be used from the first group.
        for(int i=0;i<count1;i++)
        {
            Uruobjectref curref = objectrefs1[i];
            if(curref.hasRef!=0)
            {
                if(prputils.Compiler.isNormalObjectToBeIncluded(curref.xdesc))
                {
                    newcount1++;
                }
            }
        }
        //count refs to be used from the second group.
        for(int i=0;i<count2;i++)
        {
            Uruobjectref curref = objectrefs2[i];
            if(curref.hasRef!=0)
            {
                if(prputils.Compiler.isNormalObjectToBeIncluded(curref.xdesc))
                {
                    newcount2++;
                }
            }
        }
        
        //output first group.
        deque.writeInt(newcount1);
        for(int i=0;i<count1;i++)
        {
            Uruobjectref curref = objectrefs1[i];
            if(curref.hasRef!=0)
            {
                if(prputils.Compiler.isNormalObjectToBeIncluded(curref.xdesc))
                {
                    curref.compile(deque);
                }
            }
        }
        
        //output second group.
        deque.writeInt(newcount2);
        for(int i=0;i<count2;i++)
        {
            Uruobjectref curref = objectrefs2[i];
            if(curref.hasRef!=0)
            {
                if(prputils.Compiler.isNormalObjectToBeIncluded(curref.xdesc))
                {
                    curref.compile(deque);
                }
            }
        }
    }
}
