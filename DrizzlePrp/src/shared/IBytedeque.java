/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import java.util.Vector;

public abstract class IBytedeque
{
    abstract public void writeInt(int n);
    abstract public void writeShort(short n);
    abstract public IBytedeque Fork();
    abstract public byte[] getAllBytes();
    abstract public void writeBytes(byte[] bytes);
    abstract public void writeByte(byte b);
    abstract public void writeShorts(short[] shorts);
    
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
}
