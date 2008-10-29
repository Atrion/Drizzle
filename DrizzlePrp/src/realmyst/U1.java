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

package realmyst;

import shared.*;

public class U1
{
    int tag;
    int blockSizeMaybe;
    int sub1;
    Bstr xsubs;
    int sub2;
    
    public U1(IBytestream c)
    {
        tag = c.readInt(); e.ensure(Typeid.has(tag));
        blockSizeMaybe = c.readInt(); //size of rest of block?

        sub1 = c.readInt();
        if(sub1!=0)
        {
            //45a950 block
            xsubs = new Bstr(c);
            int dummy=0;
        }
        sub2 = c.readInt();
        for(int j=0;j<sub2;j++)
        {
            //45a950 block
            //m.err("unhandled");
            Bstr subs2 = new Bstr(c);
        }
        
    }
}
