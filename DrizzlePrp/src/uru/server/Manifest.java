/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uru.server;

import shared.*;
import uru.server.MoulFileInfo;
import java.util.ArrayList;
import uru.Bytedeque;

//This is a custom class representing the .mfs_moul manifest files created by the Drizzle dataserver generator, and does not represent any Uru classes.
public class Manifest extends mystobj
{
    private boolean israw;
    private byte[] rawdata;

    private int count;
    private ArrayList<MoulFileInfo> files = new ArrayList<MoulFileInfo>();
    short uk; //always 0?  Might just be a null terminator for the entire thing.

    /*public Manifest(String filename, boolean readraw)
    {
        israw = readraw;

        if(readraw)
        {
            rawdata = FileUtils.ReadFile(filename);
        }
        else
        {
            IBytestream c = shared.SerialBytestream.createFromFilename(filename);
            while(c.getBytesRemaining()!=0)
            {
                MoulFileInfo mfi = new MoulFileInfo(c);
                files.add(mfi);
            }

        }
    }*/

    public ArrayList<MoulFileInfo> getFiles()
    {
        parse();
        return files;
    }

    private void parse()
    {
        if(israw)
        {
            israw = false;
            IBytestream c = shared.ByteArrayBytestream.createFromByteArray(rawdata);
            for(int i=0;i<count;i++)
            {
                files.add(new MoulFileInfo(c));
            }
            uk = c.readShort(); e.ensure(uk==0);
        }
    }

    public Manifest(IBytestream c, boolean readraw)
    {
        count = c.readInt();
        int charcount = c.readInt();
        if(readraw)
        {
            israw = true;
            rawdata = c.readBytes(charcount*2);
        }
        else
        {
            israw = false;
            for(int i=0;i<count;i++)
            {
                files.add(new MoulFileInfo(c));
            }
            uk = c.readShort(); e.ensure(uk==0);
        }
    }

    public Manifest(){}

    public void write(IBytedeque c)
    {

        if(israw)
        {
            c.writeInt(count);
            c.writeInt(rawdata.length/2);
            c.writeBytes(rawdata);
        }
        else
        {
            c.writeInt(files.size());

            //find the size of the rest, so that we can find the length.
            Bytedeque c2 = new Bytedeque();
            for(int i=0;i<files.size();i++)
            {
                files.get(i).write(c2);
            }
            c2.writeShort(uk);
            byte[] data = c2.getAllBytes();

            c.writeInt(data.length/2);
            c.writeBytes(data);
        }
    }

}
