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

import uru.context; import shared.readexception;
import uru.Bytestream;
import uru.Bytedeque;
import shared.e;
import shared.m;
import shared.b;
import shared.readexception;
//import java.util.Vector;


public class plMatrixConstant extends uruobj
{
    Urustring name;
    PrpController.uk xuk;
    
    public plMatrixConstant(context c) throws readexception
    {
        name = new Urustring(c);
        if(c.readversion==6)
        {
            xuk = new PrpController.uk(c);
        }
        m.warn("PlMatrixConstant has untested Pots form, since it doesn't seem to actually occur in Pots.");
    }
    
    public void compile(Bytedeque c)
    {
        name.compile(c);
    }
    
}
