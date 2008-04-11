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
import uru.readexception;
//import java.util.Vector;


public class PlCameraRegionDetector extends uruobj
{
    PlDetectorModifier parent;
    int count;
    subcamregdet[] subcamregdets;
    
    public PlCameraRegionDetector(context c) throws readexception
    {
        parent = new PlDetectorModifier(c);
        count = c.readInt();
        subcamregdets = new subcamregdet[count];
        for(int i=0;i<count;i++)
        {
            subcamregdets[i] = new subcamregdet(c);
        }
        
    }
    
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        c.writeInt(count);
        for(int i=0;i<count;i++)
        {
            subcamregdets[i].compile(c);
        }
        
    }
    
    public static class subcamregdet extends uruobj
    {
        PrpTaggedObject object;
        //byte[] u1;
        
        public subcamregdet(context c) throws readexception
        {
            object = new PrpTaggedObject(c);
        }
        
        public void compile(Bytedeque c)
        {
            object.compile(c);
        }
    }
}
