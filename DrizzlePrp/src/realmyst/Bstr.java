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

import shared.readexception;
import shared.b;
import shared.*;

/**
 *
 * @author user
 */
public class Bstr
{
    int strlen;
    byte[] string;
    
    public Bstr(IBytestream c, boolean lastByteShouldBeNulled)
    {
        strlen = c.readInt();
        string = new byte[strlen];
        for(int i=0;i<strlen;i++)
        {
            string[i] = c.readByte();
        }
        
        if(lastByteShouldBeNulled)
        {
            string[strlen-1] = 0;
        }
    }
    public Bstr(IBytestream c)
    {
        strlen = c.readInt();
        string = new byte[strlen];
        for(int i=0;i<strlen;i++)
        {
            string[i] = c.readByte();
        }
    }
    private Bstr(){}
    public static Bstr createFromNothing()
    {
        Bstr result = new Bstr();
        result.strlen = 0;
        result.string = new byte[0];
        return result;
    }
    
    public static Bstr createFromString(String s)
    {
        Bstr result = new Bstr();
        result.string = b.StringToNullTerminatedBytes(s);
        result.strlen = result.string.length;
        return result;
    }
    //static public Bstr create(Bytestream data)
    //{
    //    return new Bstr(data);
    //}
    /*public void compile(Bytedeque deque)
    {
        deque.writeInt(strlen);
        deque.writeBytes(string);
    }*/
    
    public String toString()
    {
        if(string.length==0) return "";
        if(string[string.length-1]==0)
            return b.NullTerminatedBytesToString(string);
        else
            return b.BytesToString(string);
    }

}
