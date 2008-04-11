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

package uru;

//import gui.Main;
import uru.m;

/**
 *
 * @author user
 */
public class UruCrypt {
    public static byte[] EncryptUruMessage(byte[] string, int offset)
    {
        byte[] result = new byte[string.length];
        
        for(int i=0;i<string.length;i++)
        {
            int k = (i + offset) % 8;
            int j = b.ByteToInt32(string[i]) << k;
            int l = b.ByteToInt32(string[i]) >>> (8 - k);
            result[i] = (byte)(j | l);
        }
        
        return result;
    }
    public static byte[] DecryptUruMessage(byte[] string, int offset)
    {
        byte[] result = new byte[string.length];
        
        for(int i=0;i<string.length;i++)
        {
            int k = (i + offset) % 8;
            int j = b.ByteToInt32(string[i]) >>> k;
            int l = b.ByteToInt32(string[i]) << (8 - k);
            result[i] = (byte)(j | l);
        }
        
        return result;
    }
    
    //allows for sizes under 2^12, but perhaps it should be 2^8.
    public static byte[] EncryptUrustring(byte[] string)
    {
        int actuallength = string.length;
        byte[] result = new byte[actuallength+2];
        
        //write header
        short startint = (short)(0xF000 | actuallength);
        byte[] startbytes = b.Int16ToBytes(startint);
        b.CopyBytes(startbytes,result,0);
        
        //encode bytes
        for(int i=0;i<actuallength;i++)
        {
            result[i+2] = (byte)~string[i];
        }
        
        return result;
    }
    
    //allows for sizes under 2^12, but perhaps it should be 2^8.
    public static byte[] DecryptUrustring(byte[] urustring)
    {
        int startpos;

        short lengthbytes = b.BytesToInt16(urustring,0);
        if ((lengthbytes & 0xF000)==0) startpos = 4; //skip 2 bytes, if first half-byte is set. These bytes are apparently irrelevant, anyway.
        else startpos = 2;
        int actuallength = lengthbytes & 0xFFF;
        if (actuallength > 255)
        {
            m.warn("urustring over 255 bytes:"+ new String(urustring));
        }
        byte[] result = new byte[actuallength];
        //if((actuallength > 0) && (urustring[startpos]>0x7F))
        if((actuallength > 0) && ((urustring[startpos] & 0x80) != 0))
        {
            //encrypted...
            for (int i = 0; i<actuallength; i++)
            {
                result[i] = (byte)~urustring[startpos+i];
            }
        }
        else
        {
            //unencrypted...
            for (int i = 0; i<actuallength; i++)
            {
                result[i] = urustring[startpos+i];
            }
        }
        return result;
    }

}
