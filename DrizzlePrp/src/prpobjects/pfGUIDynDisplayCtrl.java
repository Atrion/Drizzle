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

package prpobjects;

import shared.Vertex;
import shared.Flt;
import uru.context; import shared.readexception;
import uru.Bytestream;
import uru.Bytedeque;
import shared.e;
import shared.m;
import shared.b;
import shared.readexception;
//import java.util.Vector;


public class pfGUIDynDisplayCtrl extends uruobj
{
    pfGUIControlMod parent;
    Refvector textureMaps;
    Refvector layers;
    Refvector xmaterials; //these materials point to the layers.
    
    public pfGUIDynDisplayCtrl(context c) throws readexception
    {
        parent = new pfGUIControlMod(c);
        textureMaps = new Refvector(c);
        layers = new Refvector(c);
        if(c.readversion==3||c.readversion==6)
        {
            xmaterials = new Refvector(c);
        }
        else
        {
            m.throwUncaughtException("unhandled");
        }
    }
    
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        textureMaps.compile(c);
        layers.compile(c);
        xmaterials.compile(c); //this might not have been created!!  Should we grab the materials from the layers?
    }
}
