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

package realmyst;

import uru.e;
import uru.b;
import java.util.Vector;

//pera.dni seems to contain only .wav files.

public class dirtfile
{
    Header header;
    //DirectoryTable dirtable;
    
    dirttree tree;
    
    public dirtfile(rmcontext c)
    {
        header = new Header(c);
        //dirtable = new DirectoryTable(c.Fork(header.dirOffset));
        tree = new dirttree(c.Fork(header.dirOffset), header.FTOffset, true);
    }
    public static class Ntstring
    {
        byte[] bytes;
        public Ntstring(rmcontext c)
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
            return b.BytesToString(bytes);
        }
    }
    public static class DirtFile
    {
        byte[] rawdata;
        
        public DirtFile(rmcontext c, int length)
        {
            rawdata = c.readBytes(length);
        }
    }
    public static class dirttree
    {
        Ntstring name;
        DirtFile xfile;
        DirectoryTable xdirtable;
        FileStruct xfilestruct;
        
        boolean isdir;
        dirttree[] xchildren;
        
        public dirttree(rmcontext c, int FTOffset, boolean isdirectory)
        {
            isdir = isdirectory;
            if(isdir)
            {
                xdirtable = new DirectoryTable(c);
                name = new Ntstring(c.Fork(xdirtable.nameOffset));
                int childcount = xdirtable.numFiles;
                xchildren = new dirttree[childcount];
                for(int i=0;i<childcount;i++)
                {
                    int childoffset = xdirtable.fileOffsets[i];
                    boolean isChildDir = ( childoffset < FTOffset );
                    xchildren[i] = new dirttree(c.Fork(childoffset), FTOffset, isChildDir);
                }
            }
            else
            {
                xfilestruct = new FileStruct(c);
                name = new Ntstring(c.Fork(xfilestruct.nameOffset));
                uru.FileUtils.AppendText("c:/log.txt", name.toString()+"\n");
                xfile = new DirtFile(c.Fork(xfilestruct.offset), xfilestruct.fileLength);
            }
        }
        
        public String toString()
        {
            return name.toString();
        }
    }
    
    public static class Header
    {
        byte[] header;
        int version;
        int dirOffset;
        int FTOffset;
        int NLOffset;
        int dataOffset;
        int FTOffset2;
        
        public Header(rmcontext c)
        {
            header = c.readBytes(4); e.ensure(b.isEqual(header, new byte[]{'D','i','r','t'}));
            version = c.readInt(); e.ensure(version==0x10000);
            dirOffset = c.readInt();
            FTOffset = c.readInt();
            NLOffset = c.readInt();
            dataOffset = c.readInt();
            FTOffset2 = c.readInt(); e.ensure(FTOffset2==FTOffset);
        }
                
    }
    
    public static class DirectoryTable
    {
        int nameOffset;
        int numFiles;
        int[] fileOffsets;
        
        public DirectoryTable(rmcontext c)
        {
            nameOffset = c.readInt();
            numFiles = c.readInt();
            fileOffsets = c.readInts(numFiles);
        }
    }
    
    public static class FileStruct
    {
        int nameOffset;
        int extOffset;
        int fileLength;
        int offset;
        int isEmpty; //is this what it is?
        
        public FileStruct(rmcontext c)
        {
            nameOffset = c.readInt();
            extOffset = c.readInt();
            fileLength = c.readInt();
            offset = c.readInt();
            isEmpty = c.readInt();
        }
    }
}
