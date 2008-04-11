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
public class x0015CoordinateInterface extends uruobj
{
    //Objheader xheader;
    PlObjInterface parent;
    Transmatrix matrix1; //1 and 2 should be inverses of each other.
    Transmatrix matrix2;
    Transmatrix matrix3; //3 and 4 should be inverses of each other.
    Transmatrix matrix4;
    int count;
    Uruobjectref[] u1;
    
    public x0015CoordinateInterface(context c) throws readexception
    {
        Bytestream data = c.in;
        //if(hasHeader) xheader = new Objheader(c);
        parent = new PlObjInterface(c);//,false);
        matrix1 = new Transmatrix(c);
        matrix2 = new Transmatrix(c);
        matrix3 = new Transmatrix(c);
        matrix4 = new Transmatrix(c);
        count = data.readInt();
        u1 = new Uruobjectref[count];
        for(int i=0;i<count;i++)
        {
            u1[i] = new Uruobjectref(c);
        }
    }
    public void compile(Bytedeque deque)
    {
        parent.compile(deque);
        matrix1.compile(deque);
        matrix2.compile(deque);
        matrix3.compile(deque);
        matrix4.compile(deque);
        deque.writeInt(count);
        for(int i=0;i<count;i++)
        {
            u1[i].compile(deque);
        }
    }
}
