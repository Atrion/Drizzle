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


public class PlPostEffectMod extends uruobj
{
    PlSingleModifier parent;
    HsBitVector u1;
    Flt f1;
    Flt f2;
    Flt f3;
    Flt f4;
    Uruobjectref ref;
    Transmatrix t1;
    Transmatrix t2;
    
    public PlPostEffectMod(context c) throws readexception
    {
        parent = new PlSingleModifier(c);
        u1 = new HsBitVector(c);
        f1 = new Flt(c);
        f2 = new Flt(c);
        f3 = new Flt(c);
        f4 = new Flt(c);
        ref = new Uruobjectref(c);
        t1 = new Transmatrix(c);
        t2 = new Transmatrix(c);
    }
    
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        u1.compile(c);
        f1.compile(c);
        f2.compile(c);
        f3.compile(c);
        f4.compile(c);
        ref.compile(c);
        t1.compile(c);
        t2.compile(c);
    }
    
}