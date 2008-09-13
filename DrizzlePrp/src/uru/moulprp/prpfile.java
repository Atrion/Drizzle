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
import uru.moulprp.Typeid;
import uru.moulprp.prputils.Compiler.Decider;
/**
 *
 * @author user
 */
public class prpfile
{
    public PrpHeader header;
    //Vector<PrpRootObject> objects;
    public PrpRootObject[] objects;
    public PrpObjectIndex objectindex;
    
    public prpfile(){}
    
    public static prpfile createFromContext(context c, Typeid[] typesToRead)
    {
        prpfile result = prputils.ProcessAllMoul(c, false, typesToRead);
        return result;
    }
    
    public Bytes saveAsBytes(Decider decider)
    {
        Bytes result = prputils.Compiler.RecompilePrp(this, decider);
        return result;
    }
    
}
