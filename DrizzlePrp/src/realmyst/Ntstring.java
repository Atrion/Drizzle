/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package realmyst;

import shared.*;
import java.util.Vector;
import shared.b;

public class Ntstring
{
    byte[] bytes;
    public Ntstring(IBytestream c)
    {
        Vector<Byte> readbytes = new Vector<Byte>();
        byte curbyte;
        do
        {
            curbyte = c.readByte();
            readbytes.add(curbyte);
        }while(curbyte!=0);
        bytes = new byte[readbytes.size()];
        for(int i=0;i<bytes.length;i++)
        {
            bytes[i] = readbytes.get(i);
        }
    }
    public String toString()
    {
        if(bytes[bytes.length-1]==0)
            return b.NullTerminatedBytesToString(bytes);
        else
            return b.BytesToString(bytes);
    }
}
