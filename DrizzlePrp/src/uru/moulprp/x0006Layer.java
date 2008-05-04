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
public class x0006Layer extends uruobj
{
    //Objheader xheader;
    x0041LayerInterface parent;
    int flags1;
    int flags2;
    int flags3;
    int flags4;
    int flags5;
    Transmatrix matrix;
    Rgba ambient;
    Rgba diffuse;
    Rgba emissive;
    Rgba specular;
    int vertexshader;
    int opacity; //float 0 to 1
    int lodbias;
    int u1; //a float with integer! values.
    Uruobjectref texture;
    Uruobjectref shader1;
    Uruobjectref shader2;
    Transmatrix identity;
    
    public x0006Layer(context c)//,boolean hasHeader)
    {
        Bytestream data = c.in;
        //if(hasHeader) xheader = new Objheader(c);
        parent = new x0041LayerInterface(c);//,false);
        flags1 = data.readInt();
        flags2 = data.readInt();
        flags3 = data.readInt();
        flags4 = data.readInt();
        flags5 = data.readInt();
        matrix = new Transmatrix(c);
        ambient = new Rgba(data);
        diffuse = new Rgba(data);
        emissive = new Rgba(data);
        specular = new Rgba(data);
        vertexshader = data.readInt();
        opacity = data.readInt();
        lodbias = data.readInt();
        u1 = data.readInt();
        texture = new Uruobjectref(c);
        shader1 = new Uruobjectref(c);
        shader2 = new Uruobjectref(c);
        identity = new Transmatrix(c);
        
        
    }
    public void compile(Bytedeque data)
    {
        parent.compile(data);
        data.writeInt(flags1);
        data.writeInt(flags2);
        data.writeInt(flags3);
        data.writeInt(flags4);
        data.writeInt(flags5);
        matrix.compile(data);
        ambient.compile(data);
        diffuse.compile(data);
        emissive.compile(data);
        specular.compile(data);
        data.writeInt(vertexshader);
        data.writeInt(opacity);
        data.writeInt(lodbias);
        data.writeInt(u1);
        texture.compile(data);
        shader1.compile(data);
        shader2.compile(data);
        identity.compile(data);
        
    }
}
