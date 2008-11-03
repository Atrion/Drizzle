/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import java.util.Vector;

//public abstract interface IBytestream
public abstract class IBytestream
{
    /*public static IBytestream createFromFilename(String filename)
    {
        return createFromFilenameOffset(filename,0);
    }
    public abstract static IBytestream createFromFilenameOffset(String filename, int offset);*/
    abstract public IBytestream Fork(long offset);
    abstract public byte readByte();
    abstract public byte[] readBytes(int num);
    abstract public int readInt();
    abstract public int[] readInts(int num);
    abstract public short readShort();
    //public String toString();
    //abstract public <T> Vector<T> readVector( Class<T> objclass, int size);
    //abstract public <T> T readObj(Class<T> objclass);
    //abstract public <T> T[] readArray( Class<T> objclass, int size);
    abstract public int getAbsoluteOffset();
    abstract public int getFilelength();
    abstract public int getBytesRemaining();
    
    public String sourceName = "";
    
    public <T> Vector<T> readVector( Class<T> objclass, int size)
    {
        Vector<T> result = new Vector<T>();
        for(int i=0;i<size;i++)
        {
            result.add((T)this.readObj(objclass));
        }
        return result;
    }
    public <T> T[] readArray( Class<T> objclass, int size)
    {
        T[] result = generic.makeArray(objclass, size);
        //T[] result = (T[])java.lang.reflect.Array.newInstance(objclass, size);
        for(int i=0;i<size;i++)
        {
            result[i] = (T)this.readObj(objclass);
        }
        return result;
    }
    public <T> T readObj(Class<T> objclass)
    {
        try
        {
            return (T)objclass.getConstructor(IBytestream.class).newInstance(this);
        }
        catch(java.lang.NoSuchMethodException e)
        {
            throw new uncaughtexception("IBytestream: java gunk: unable to create new instance.");
            //If an exception is being thrown here, it's probably because an inner class was attempted.  Make it static(which just means that the outer class isn't passed as a parameter.)
        }
        catch(java.lang.InstantiationException e)
        {
            throw new uncaughtexception("IBytestream: java gunk: unable to create new instance.");
            //If an exception is being thrown here, it's probably because an inner class was attempted.  Make it static(which just means that the outer class isn't passed as a parameter.)
        }
        catch(java.lang.IllegalAccessException e)
        {
            throw new uncaughtexception("IBytestream: java gunk: unable to create new instance.");
            //If an exception is being thrown here, it's probably because an inner class was attempted.  Make it static(which just means that the outer class isn't passed as a parameter.)
        }
        catch(java.lang.reflect.InvocationTargetException e)
        {
            Throwable e2 = e.getCause();
            if(e2 instanceof readexception)
            {
                //throw (readexception)e2; //rethrow.
                e2.printStackTrace();
                throw new uncaughtexception("Encountered readexception.");
            }
            else
            {
                throw new uncaughtexception("IBytestream: java gunk: unable to create new instance.");
                //If an exception is being thrown here, it's probably because an inner class was attempted.  Make it static(which just means that the outer class isn't passed as a parameter.)
            }
        }
        /*catch(Exception e)
        {
            //m.msg("Bytestream: java gunk: unable to create new instance.");
            //int sai=1;
            if(e instanceof readexception)
            {
                throw e;
            }
        }*/
        //return result;
        //mystobj newobj = T.create(this);
        //T newT = (T)newobj;
        //return newT;
    }
    public byte[][] readMultiDimensionBytes(int num1, int num2)
    {
        byte[][] result = new byte[num1][];
        for(int i=0;i<num1;i++)
        {
            result[i] = this.readBytes(num2);
        }
        return result;
    }
    public int[][] readMultiDimensionInts(int num1, int num2)
    {
        int[][] result = new int[num1][];
        for(int i=0;i<num1;i++)
        {
            result[i] = this.readInts(num2);
        }
        return result;
    }
    
}
