/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import shared.b;
import java.util.Vector;
import shared.*;
import java.io.InputStream;
import java.io.IOException;

public class StreamBytestream extends IBytestream
{
    InputStream in;
    
    public StreamBytestream(InputStream in)
    {
        this.in = in;
    }
    public InputStream getChildStreamIfExists()
    {
        return in;
    }

    public void skip(long n)
    {
        throw new uncaughtexception("unimplemented");
    }

    protected int read()
    {
        try{
            return in.read();
        }catch(IOException e){
            throw new uncaughtnestedexception(e);
        }
    }
    public byte readByte()
    {
        try{
            return (byte)in.read();
        }catch(IOException e){
            throw new uncaughtnestedexception(e);
        }
    }
    public byte[] readBytes(int num)
    {
        try{
            byte[] r = new byte[num];

            //method1
            //in.read(r);
            //return r;

            //method2
            int n = 0;
            while (n < num)
            {
                int count = in.read(r, n, num - n);
                if (count < 0)
                    throw new uncaughtexception("Read past end of stream.");
                n += count;
            }
            return r;
        }catch(IOException e){
            throw new uncaughtnestedexception(e);
        }
    }
    public int readInt()
    {
        try{
            int ch1 = in.read();
            int ch2 = in.read();
            int ch3 = in.read();
            int ch4 = in.read();
            return ((ch1 << 0) + (ch2 << 8) + (ch3 << 16) + (ch4 << 24));
        }catch(IOException e){
            throw new uncaughtnestedexception(e);
        }
    }
    public short readShort()
    {
        try{
            int ch1 = in.read();
            int ch2 = in.read();
            return (short)((ch1 << 0) + (ch2 << 8));
        }catch(IOException e){
            throw new uncaughtnestedexception(e);
        }
    }
    public int getAbsoluteOffset()
    {
        return -1;
    }
    public int getFilelength()
    {
        return -1;
    }
    public int getBytesRemaining()
    {
        //return getFilelength()-getAbsoluteOffset();
        return -1;
    }
    public IBytestream Fork(long offset)
    {
        throw new uncaughtexception("unimplemented");
    }
    
    public String toString()
    {
        try{
            return "available: "+Integer.toString(in.available());
        }catch(Exception e){
            return "exception";
        }
    }
}
