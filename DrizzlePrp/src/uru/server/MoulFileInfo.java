/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uru.server;

import shared.*;

public class MoulFileInfo
{
    Utf16 filename;
    Utf16 Downloadname;
    Utf16 Hash;
    Utf16 CompressedHash;
    int Filesize; //two shorts, reversed order.  the other two ints are like this too.
    private short u1; //null to terminate xFilesize
    int Compressedsize;
    private short u2; //null to terminate xCompressedsize
    int Flags;
    private short u3; //null to terminate xFlags

    //boolean hasfields = true;

    public MoulFileInfo(IBytestream c)
    {
        filename = new Utf16(c);
        Downloadname = new Utf16(c);
        Hash = new Utf16(c);
        CompressedHash = new Utf16(c);
        Filesize = c.readIntAsTwoShorts();
        u1 = c.readShort(); //0
        Compressedsize = c.readIntAsTwoShorts();
        u2 = c.readShort(); //0
        Flags = c.readIntAsTwoShorts();
        u3 = c.readShort(); //0
    }
    public MoulFileInfo(){}

    public void write(IBytedeque c)
    {
        filename.write(c);
        Downloadname.write(c);
        Hash.write(c);
        CompressedHash.write(c);
        c.writeIntAsTwoShorts(Filesize);
        c.writeShort(u1);
        c.writeIntAsTwoShorts(Compressedsize);
        c.writeShort(u2);
        c.writeIntAsTwoShorts(Flags);
        c.writeShort(u3);
    }

    public String toString()
    {
        return "filename="+filename.toString()+" downname="+Downloadname.toString()
            +" hash="+Hash.toString()+" comphash="+CompressedHash.toString()
            +" filesize="+Integer.toString(Filesize)+" compsize="+Integer.toString(Compressedsize)
            +" flags="+Integer.toString(Flags);
        //return "filename="+filename.toString();
    }
}
