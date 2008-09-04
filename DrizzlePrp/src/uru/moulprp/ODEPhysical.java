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
import shared.readexception;
//import java.util.Vector;


public class ODEPhysical extends uruobj
{
    PlSynchedObject parent;
    int type;
    
    int vertexcount;
    Vertex[] vertices;
    int surfacecount;
    int[] surfaces;
    int xu3;
    byte[] xu3s;
    
    Flt f8;
    int u8;
    int u9;
    int u10;
    int u11;
    int u12;
    short u13;
    Uruobjectref sceneobject;
    Uruobjectref scenenode;
    
    public ODEPhysical(context c) throws readexception
    {
        parent = new PlSynchedObject(c);
        type = c.readInt();
        if(type==5)
        {
            vertexcount = c.readInt(); //vertex count
            vertices = c.readVector(Vertex.class, vertexcount); //vertices

            surfacecount = c.readInt(); //face count
            surfaces = c.readInts(3*surfacecount); //faces
            for(int point: surfaces)
            {
                if(point >= 0x00010000)
                {
                    throw new readexception("ODEPhysical has more than 2^16 points, so it can't be converted.");
                }
            }
            
            xu3 = c.readInt(); //equals surfacecount?
            xu3s = c.readBytes(xu3); //one byte for each surface?
            
        }
        else if(type==1) //box?
        {
            m.msg("Untested ODE case1...");
            Flt f1 = new Flt(c);
            Flt f2 = new Flt(c);
            Flt f3 = new Flt(c);
        }
        else if(type==2) //sphere?
        {
            m.msg("Untested ODE case2...");
            Flt u4 = new Flt(c); //same as 6
        }
        else if(type==6) //ellipse???
        {
            m.msg("Untested ODE case6...");
            Flt u4 = new Flt(c); //same as 2
            Flt u6 = new Flt(c);
        }
        else
        {
            m.msg("Untested ODE case unknown...");
        }
        
        f8 = new Flt(c);
        u8 = c.readInt();
        u9 = c.readInt();
        u10 = c.readInt();
        u11 = c.readInt();
        u12 = c.readInt();
        u13 = c.readShort();
        
        sceneobject = new Uruobjectref(c); //plSceneObject
        scenenode = new Uruobjectref(c); //plSceneNode
        
        m.msg("f8="+f8.toString()+";u8="+Integer.toString(u8)
                +";u9="+Integer.toString(u9)+";u10="+Integer.toString(u10)
                +";u11="+Integer.toString(u11)+";u12="+Integer.toString(u12)
                +";u13="+Short.toString(u13));
        if(u8==0x02000000)
        {
            int dummy=0;
        }
        else
        {
            int dummy=0;
        }
            
    }
    
    public void compile(Bytedeque c)
    {
        m.warn("compile not implemented."+this.toString());
        m.warn("not tested with pots."+this.toString());
    }
    
    public void compileSpecial(Bytedeque c)
    {
        //m.err("ODE compile not implemented.");
        
        parent.compile(c);
        Vertex.zero().compile(c);//position
        Quat.identity().compile(c);//orientation;
        Flt.zero().compile(c);//mass
        Flt.zero().compile(c);//RC
        Flt.zero().compile(c);//EL
        
        c.writeInt(4); //format, proxy bounds/mesh bounds
        c.writeShort((short)0);//u1 short
        c.writeShort((short)0x200);//coltype short
        c.writeInt(0);//flagsdetect int
        c.writeInt(0);//flagsrespond int
        c.writeByte((byte)0);//u2 byte
        c.writeByte((byte)0);//u3 byte
        
        if(type==5)
        {
            //write vertices
            c.writeInt(vertexcount);
            e.ensure(vertices.length==vertexcount);
            c.writeVector(vertices);

            //write surfaces
            c.writeInt(surfacecount);
            for(int point: surfaces)
            {
                short point2 = (short)point;
                c.writeShort(point2);
            }
            
        }
        else
        {
            m.err("cricial error: ODEPhysical: currently unable to compile this type");
        }
        
        sceneobject.compile(c);
        new HsBitVector(0).compile(c);//group hsbitvector
        scenenode.compile(c);
        c.writeInt(0x44);//LOSDB int, could also be 0x40 or 0x45, I guess.
        Uruobjectref.none().compile(c);//subworld uruobjectref
        Uruobjectref.none().compile(c);//soundgroup uruobjectref
        
    }
    
}
