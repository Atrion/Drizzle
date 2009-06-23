/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import java.util.Vector;
import java.util.Iterator;

public abstract class IBytedeque
{
    abstract public void writeInt(int n);
    abstract public void writeShort(short n);
    abstract public IBytedeque Fork();
    abstract public byte[] getAllBytes();
    abstract public void writeBytes(byte[] bytes);
    abstract public void writeByte(byte b);
    abstract public void writeShorts(short[] shorts);
    abstract protected Iterator<byte[]> getIterator();
    
    public <T extends ICompilable> void writeArray(T[] vector)
    {
        int length = vector.length;
        for(int i=0;i<length;i++)
        {
            vector[i].compile(this);
        }
    }
    public <T extends ICompilable> void writeVector(Vector<T> vector)
    {
        int length = vector.size();
        for(int i=0;i<length;i++)
        {
            vector.get(i).compile(this);
        }
    }
    public void writeInts(int[] ints)
    {
        for(int i=0;i<ints.length;i++)
        {
            writeInt(ints[i]);
        }
    }
    public void writeMultiDimensionInts(int[][] data)
    {
        for(int i=0;i<data.length;i++)
        {
            this.writeInts(data[i]);
        }
    }
    public void writeAllBytesToFile(String filename)
    {
        try{
            java.io.FileOutputStream writer = new java.io.FileOutputStream(filename);

            Iterator<byte[]> iterator = this.getIterator();
            while(iterator.hasNext())
            {
                writer.write(iterator.next());
            }
            //int filelength = content.length;
            //writer.write(content);
            writer.flush();
            writer.close();
        }
        catch(Exception e)
        {
            m.err("Error writing file:",filename+":"+e.getMessage());
        }
    }
    
}
