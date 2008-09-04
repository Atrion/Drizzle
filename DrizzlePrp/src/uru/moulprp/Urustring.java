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
import shared.m;
import uru.e;
import uru.b;
import uru.Bytedeque;

/**
 *
 * @author user
 */
public class Urustring extends uruobj
{
    byte[] unencryptedString; //unencrypted form of this string.

    public static Urustring createFromString(String s)
    {
        Urustring result = new Urustring();
        result.unencryptedString = b.StringToBytes(s);
        return result;
    }
    
    private Urustring(){}
    
    public Urustring(context c)
    {
        if(c.readversion==6 || c.readversion==3)
        {
            Bytestream data = c.in;
            short lengthbytes = data.readShort();
            if ((lengthbytes & 0xF000)==0) data.skipBytes(2); //skip 2 bytes, if first half-byte is set. These bytes are apparently irrelevant, anyway.
            int actuallength = lengthbytes & 0xFFF;
            if((lengthbytes & 0xF000)!=0xF000)
            {
                m.msg("urustring doesn't start with F.");
            }
            if (actuallength > 255)
            {
                m.msg("urustring over 255 bytes.");
            }
            unencryptedString = new byte[actuallength];
            //if((actuallength > 0) && (urustring[startpos]>0x7F))
            if((actuallength > 0) && ((data.peekByte() & 0x80) != 0))
            {
                //encrypted...
                for (int i = 0; i<actuallength; i++)
                {
                    unencryptedString[i] = (byte)~data.readByte();
                }
             }
            else
            {
                //unencrypted...
                //if(actuallength==0) m.msg("string is empty."); //not a problem.
                if(actuallength!=0) m.msg("urustring is not encrypted.");
                for (int i = 0; i<actuallength; i++)
                {
                    unencryptedString[i] = data.readByte();
                }
            }
        }
        else if(c.readversion==4)
        {
            byte[] key = { 109, 121, 115, 116, 110, 101, 114, 100 }; //ascii for "mystnerd"
            short len = c.readShort();
            byte[] result = new byte[len];
            for(int i=0;i<len;i++)
            {
                result[i] = (byte)(c.readByte() ^ key[i%8]);
            }
            unencryptedString = result;
        }
        e.ensureString(unencryptedString); //make sure its a text string.
    }
    public void compile(Bytedeque deque)
    {
        int actuallength = unencryptedString.length;
        //byte[] result = new byte[actuallength+2];
        
        //write header
        short startint = (short)(0xF000 | actuallength);
        byte[] startbytes = b.Int16ToBytes(startint);
        //b.CopyBytes(startbytes,result,0);
        deque.writeBytes(startbytes);
        
        //encode bytes
        for(int i=0;i<actuallength;i++)
        {
            //result[i+2] = (byte)~string[i];
            deque.writeByte((byte)~unencryptedString[i]);
        }
        
        //return result;
    }
    
    public String toString()
    {
        return new String(unencryptedString);
    }
    
    public Urustring(byte[] unencrypted)
    {
        unencryptedString = unencrypted;
    }
}

