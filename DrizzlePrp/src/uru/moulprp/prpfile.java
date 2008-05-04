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

//import java.util.Vector;
import java.lang.reflect.Field;
import shared.m;
import uru.context;
import shared.Bytes;
/**
 *
 * @author user
 */
public class prpfile
{
    PrpHeader header;
    //Vector<PrpRootObject> objects;
    PrpRootObject[] objects;
    PrpObjectIndex objectindex;
    
    public prpfile(){}
    
    public static prpfile createFromContext(context c)
    {
        prpfile result = prputils.ProcessAllMoul(c, false);
        return result;
    }
    
    public Bytes saveAsBytes()
    {
        Bytes result = prputils.Compiler.RecompilePrp(this);
        return result;
    }
    
}
