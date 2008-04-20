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

import java.util.Vector;

/**
 *
 * @author user
 */
public class b
{
    public static boolean isEqual(byte[] bs1, byte[] bs2)
    {
        if(bs1.length!=bs2.length) return false;
        for( int i=0; i<bs1.length; i++ )
        {
            if(bs1[i]!=bs2[i]) return false;
        }
        return true;
    }
    public static byte shr(byte a, byte b)
    {
        return (byte)(ByteToInt32(a) >>> ByteToInt32(b));
    }
    public static byte shr(byte a, int dist)
    {
        return (byte)(ByteToInt32(a) >>> dist);
    }
    public static byte shl(byte a, byte b)
    {
        //improve: this probably doesn't need the ByteToInt32 calls.
        return (byte)(ByteToInt32(a) << ByteToInt32(b));
    }
    public static byte shl(byte a, int dist)
    {
        //improve: this probably doesn't need the ByteToInt32 calls.
        return (byte)(ByteToInt32(a) << dist);
    }
    public static byte or(byte a, byte b)
    {
        //improve: this probably doesn't need the ByteToInt32 calls.
        return (byte)(ByteToInt32(a) | ByteToInt32(b));
    }
    public static byte and(byte a, byte b)
    {
        //improve: this probably doesn't need the ByteToInt32 calls.
        return (byte)(ByteToInt32(a) & ByteToInt32(b));
    }
    public static byte not(byte a)
    {
        //improve: this probably doesn't need the ByteToInt32 calls.
        return (byte)(~ByteToInt32(a));
    }
    public static byte xor(byte a, byte b)
    {
        //improve: this probably doesn't need the ByteToInt32 calls.
        return (byte)(ByteToInt32(a) ^ ByteToInt32(b));
    }
    public static byte xor(byte a, int b)
    {
        //improve: this probably doesn't need the ByteToInt32 calls.
        return (byte)(ByteToInt32(a) ^ b);
    }
    public static byte or(byte a, int b)
    {
        //improve: this probably doesn't need the ByteToInt32 calls.
        return (byte)(ByteToInt32(a) | b);
    }
    public static byte and(byte a, int b)
    {
        //improve: this probably doesn't need the ByteToInt32 calls.
        return (byte)(ByteToInt32(a) & b);
    }
    public static int ByteToInt32(byte b)
    {
        return ((int)b)&0xFF;
    }
    public static short ByteToInt16(byte b)
    {
        return (short)(((int)b)&0xFF);
    }
    public static int Int16ToInt32(short s)
    {
        return ((int)s)&0xFFFF;
    }
    public static long Int32ToInt64(int i)
    {
        return ((long)i)&0xFFFFFFFFL;
    }
    public static int BytesToInt32(byte[] bytes, int startpos)
    {
        //return (((int)bytes[startpos+0])<<0) | (((int)bytes[startpos+1])<<8) | (((int)bytes[startpos+2])<<16) | (((int)bytes[startpos+3])<<24);
        int a = ByteToInt32(bytes[startpos+0])<<0;
        int b = ByteToInt32(bytes[startpos+1])<<8;
        int c = ByteToInt32(bytes[startpos+2])<<16;
        int d = ByteToInt32(bytes[startpos+3])<<24;
        int result = a | b | c | d;
        return result;
    }
    public static short BytesToInt16(byte[] bytes, int startpos)
    {
        int a = ByteToInt32(bytes[startpos+0])<<0;
        int b = ByteToInt32(bytes[startpos+1])<<8;
        short result = (short)( a | b );
        return result;
        
    }
    public static long BytesToInt64(byte[] bytes, int startpos)
    {
        int a = ByteToInt32(bytes[startpos+0])<<0;
        int b = ByteToInt32(bytes[startpos+1])<<8;
        int c = ByteToInt32(bytes[startpos+2])<<16;
        int d = ByteToInt32(bytes[startpos+3])<<24;
        int e = ByteToInt32(bytes[startpos+0])<<32;
        int f = ByteToInt32(bytes[startpos+1])<<40;
        int g = ByteToInt32(bytes[startpos+2])<<48;
        int h = ByteToInt32(bytes[startpos+3])<<56;
        int result = a | b | c | d | e | f | g | h;
        return result;
    }
    public static byte[] ByteToBytes(byte by)
    {
        byte[] result = new byte[1];
        result[0] = by;
        return result;
    }
    /*private static int loadBytesIntoInt32Endian(byte[] bytes, int startpos)
    {
        //return (bytes[startpos+0]<<24) | (bytes[startpos+1]<<16) | (bytes[startpos+2]<<8) | (bytes[startpos+3]<<0);
        int a = ByteToInt32(bytes[startpos+0])<<24;
        int b = ByteToInt32(bytes[startpos+1])<<16;
        int c = ByteToInt32(bytes[startpos+2])<<8;
        int d = ByteToInt32(bytes[startpos+3])<<0;
        int result = a | b | c | d;
        return result;
    }*/
    public static void loadInt32IntoBytes(int value, byte[] bytes, int startpos)
    {
        bytes[startpos+0] = (byte)((value >> 0) & 0xFF);
        bytes[startpos+1] = (byte)((value >> 8) & 0xFF);
        bytes[startpos+2] = (byte)((value >> 16) & 0xFF);
        bytes[startpos+3] = (byte)((value >> 24) & 0xFF);
    }
    public static byte[] Int32ToBytes(int value)
    {
        byte[] result = new byte[4];
        result[0] = (byte)((value >> 0) & 0xFF);
        result[1] = (byte)((value >> 8) & 0xFF);
        result[2] = (byte)((value >> 16) & 0xFF);
        result[3] = (byte)((value >> 24) & 0xFF);
        return result;
    }
    public static byte[] Int16ToBytes(short value)
    {
        byte[] result = new byte[2];
        result[0] = (byte)((value >> 0) & 0xFF);
        result[1] = (byte)((value >> 8) & 0xFF);
        return result;
    }
    /*private static void loadInt32IntoBytesEndian(int value, byte[] bytes, int startpos)
    {
        bytes[startpos+0] = (byte)((value >> 24) & 0xFF);
        bytes[startpos+1] = (byte)((value >> 16) & 0xFF);
        bytes[startpos+2] = (byte)((value >> 8) & 0xFF);
        bytes[startpos+3] = (byte)((value >> 0) & 0xFF);
    }*/

    /*private static short byteToInt16(byte b)
    {
        short result = (short)(b&0xFF);
        return result;
    }*/
    public static void CopyBytes(byte[] from, int fromstart, byte[] to, int tostart, int length)
    {
        for(int i=0;i<length;i++)
        {
            to[tostart+i] = from[fromstart+i];
        }
    }
    public static void CopyBytes(byte[] from, byte[] to, int tostart)
    {
        CopyBytes(from,0,to,tostart,from.length);
    }

    public static byte[][] splitBytes(byte[] bytesToSplit, byte splitter)
    {
        int length = bytesToSplit.length;
        
        //count the subsets
        int splittercount = 0;
        for(int i=0;i<length;i++)
        {
            if(bytesToSplit[i]==splitter) splittercount++;
        }
        byte[][] result = new byte[splittercount+1][];
        
        //find the size of each subset
        int cursubset = 0;
        int posinsubset = 0;
        for(int i=0;i<length;i++)
        {
            if(bytesToSplit[i]==splitter)
            {
                result[cursubset] = new byte[posinsubset]; //every subset except the last
                //lastsplitterpos = i;
                cursubset++;
                posinsubset = 0;
            }
            else
            {
                posinsubset++;
            }
        }
        result[cursubset] = new byte[posinsubset]; //last bunch
        
        //copy the bytes to each subset
        cursubset = 0;
        posinsubset = 0;
        for(int i=0;i<length;i++)
        {
            byte curbyte = bytesToSplit[i];
            if(curbyte==splitter)
            {
                //lastsplitterpos = i;
                cursubset++;
                posinsubset = 0;
            }
            else
            {
                result[cursubset][posinsubset] = curbyte;
                posinsubset++;
            }
        }
        
        return result;
    }
    
    public static String ByteToHexString(byte b)
    {
        char[] chars = new char[2];
        int upper = ((int)b & 0xF0)>>4;
        int lower = (int)b & 0x0F;
        chars[0] = (char)(upper<10?upper+'0':upper-10+'a');
        chars[1] = (char)(lower<10?lower+'0':lower-10+'a');
        return new String(chars);
    }
    public static String BytesToHexString(byte[] bytes)
    {
        //char[] result = new char[bytes.length*2];
        StringBuilder result = new StringBuilder();
        for(int i=0;i<bytes.length;i++)
        {
            byte curbyte = bytes[i];
            result.append(ByteToHexString(curbyte));
        }
        return result.toString();
    }
    public static String BytesToString(byte[] bytes)
    {
        //String result = new String(bytes, "ISO-8859-1"); //has to have a try/catch block.
        /*String result = new String(bytes, 0); //deprecated, but acts like ascii.
        return result;*/
        int length = bytes.length;
        char[] result = new char[length];
        for(int i=0;i<length;i++)
        {
            result[i] = (char)ByteToInt16(bytes[i]);
        }
        return new String(result);
    }
    public static byte[] StringToBytes(String str)
    {
        /*byte[] result = null;
        try
        {
            result = str.getBytes("ISO-8859-1");
        }
        catch(Exception e)
        {
            m.err("Unable to convert string to bytes");
        }
        return result;*/
        int length = str.length();
        byte[] result = new byte[length];
        for(int i=0;i<length;i++)
        {
            result[i] = (byte)(str.charAt(i) & 0xFF);
        }
        return result;
    }
    public static byte[] appendBytes(byte[]... byteblocks)
    {
        int numblocks = byteblocks.length;
        
        //find the full length.
        int fulllength = 0;
        for(int i=0;i<numblocks;i++)
        {
            fulllength += byteblocks[i].length;
        }
        byte[] result = new byte[fulllength];
        
        //copy over the bytes.
        int curpos = 0;
        for(int i=0;i<numblocks;i++)
        {
            byte[] curblock = byteblocks[i];
            int curblocklength = curblock.length;
            for(int j=0;j<curblocklength;j++)
            {
                result[curpos] = curblock[j];
                curpos++;
            }
        }
        
        return result;
    }
    
    /*public static byte[] appendBytes(byte[] bytes1, byte[] bytes2)
    {
        int length = bytes1.length + bytes2.length;
        byte[] result = new byte[length];
        int curpos = 0;
        
        //copy the bytes from bytes1.
        for(int i=0;i<bytes1.length;i++)
        {
            result[curpos] = bytes1[i];
            curpos++;
        }
        
        //copy the bytes from bytes2.
        for(int i=0;i<bytes2.length;i++)
        {
            result[curpos] = bytes2[i];
            curpos++;
        }
        
        return result;
    }*/
    
    //untested
    public static byte[] byteVectorToArray(Vector<Byte> vector)
    {
        int size = vector.size();
        byte[] result = new byte[size];
        for(int i=0;i<size;i++)
        {
            result[i] = vector.get(i);
        }
        return result;
    }
    
    //untested
    public static int reverseEndianness(int value)
    {
        byte b1 = (byte)((value >> 0) & 0xFF);
        byte b2 = (byte)((value >> 8) & 0xFF);
        byte b3 = (byte)((value >> 16) & 0xFF);
        byte b4 = (byte)((value >> 24) & 0xFF);
        int result = (b1 << 24) | (b2 << 16) | (b3 << 8) | (b4 << 0);
        return result;
    }
}
