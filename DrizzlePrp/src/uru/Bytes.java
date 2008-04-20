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
import java.lang.Byte;

public class Bytes
{
    byte[] bytes;
    
    public Bytes(byte[] data)
    {
        bytes = data;
    }
    public Bytes(int size)
    {
        bytes = new byte[size];
    }
    public Bytes(Vector<Byte> bytevector)
    {
        bytes = new byte[bytevector.size()];
        for(int i=0;i<bytes.length;i++)
        {
            bytes[i] = bytevector.get(i);
        }
    }
    public Bytes(String string)
    {
        bytes = b.StringToBytes(string);
    }
    public static Bytes create(byte[] data)
    {
        Bytes result = new Bytes(data);
        return result;
    }
    public String toString()
    {
        return b.BytesToString(bytes);
    }
    public Bytes append(Bytes appendage)
    {
        byte[] result = new byte[bytes.length+appendage.length()];
        this.writeIntoBuffer(result, 0);
        appendage.writeIntoBuffer(result, this.length());
        return new Bytes(result);
    }
    public void writeIntoBuffer(byte[] buffer, int pos)
    {
        for(int i=0;i<bytes.length;i++)
        {
            buffer[pos+i] = bytes[i];
        }
    }
    public void appendToVector(Vector<Byte> vector)
    {
        for(int i=0;i<bytes.length;i++)
        {
            vector.add(bytes[i]);
        }
    }
    public boolean startsWith(Bytes startPhrase)
    {
        if(startPhrase.length()>bytes.length) return false;
        for(int i=0;i<startPhrase.length();i++)
        {
            if(startPhrase.bytes[i]!=bytes[i]) return false;
        }
        return true;
    }
    public Bytes remove(byte bytetoremove)
    {
        Vector<Byte> result = new Vector<Byte>();
        int i=0;
        //boolean possibleinstance = false;
        //int possibleinstance = -1;
        while(i<bytes.length)
        {
            byte curbyte = bytes[i];
            if(bytetoremove==curbyte)
            {
                //match, so write newbytes
                i++;
            }
            else
            {
                result.add(curbyte);
                i++;
            }
        }
        return new Bytes(result);
    }
    public Bytes replace(byte oldbyte, Bytes newbytes)
    {
        Vector<Byte> result = new Vector<Byte>();
        int i=0;
        //boolean possibleinstance = false;
        //int possibleinstance = -1;
        while(i<bytes.length)
        {
            byte curbyte = bytes[i];
            if(oldbyte==curbyte)
            {
                //match, so write newbytes
                newbytes.appendToVector(result);
                i = i+newbytes.length();
            }
            else
            {
                result.add(curbyte);
                i++;
            }
        }
        return new Bytes(result);
    }
    public Bytes replace(Bytes oldbytes, Bytes newbytes)
    {
        Vector<Byte> result = new Vector<Byte>();
        int i=0;
        //boolean possibleinstance = false;
        //int possibleinstance = -1;
        while(i<bytes.length)
        {
            byte curbyte = bytes[i];
            if(oldbytes.bytes[0]==curbyte && oldbytes.length()<bytes.length-i)
            {
                //possible instance...
                //possibleinstance = i; //mark where we left off
                //if()
                //{
                //    //can't be an instance.
                //}
                int j = 0;
                while(j<oldbytes.length() && bytes[i+j]==oldbytes.bytes[j])
                {
                    j++;
                }
                if(j==oldbytes.length())
                {
                    //match, so write newbytes
                    newbytes.appendToVector(result);
                    i = i+j;
                }
                else
                {
                    result.add(curbyte);
                    i++;
                }
            }
            else
            {
                result.add(curbyte);
                i++;
            }
        }
        return new Bytes(result);
    }
    public int length()
    {
        return bytes.length;
    }
    public Bytes[] split(byte splitter)
    {
        int length = bytes.length;
        
        //count the subsets
        int splittercount = 0;
        for(int i=0;i<length;i++)
        {
            if(bytes[i]==splitter) splittercount++;
        }
        Bytes[] result = new Bytes[splittercount+1];
        
        //find the size of each subset
        int cursubset = 0;
        int posinsubset = 0;
        for(int i=0;i<length;i++)
        {
            if(bytes[i]==splitter)
            {
                result[cursubset] = new Bytes(posinsubset); //every subset except the last
                //lastsplitterpos = i;
                cursubset++;
                posinsubset = 0;
            }
            else
            {
                posinsubset++;
            }
        }
        result[cursubset] = new Bytes(posinsubset); //last bunch
        
        //copy the bytes to each subset
        cursubset = 0;
        posinsubset = 0;
        for(int i=0;i<length;i++)
        {
            byte curbyte = bytes[i];
            if(curbyte==splitter)
            {
                //lastsplitterpos = i;
                cursubset++;
                posinsubset = 0;
            }
            else
            {
                result[cursubset].bytes[posinsubset] = curbyte;
                posinsubset++;
            }
        }
        
        return result;
    }
}
