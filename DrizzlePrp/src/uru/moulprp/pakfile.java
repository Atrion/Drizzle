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

package uru.moulprp;

import uru.context; import shared.readexception;
import shared.FileUtils;
import shared.m;
import shared.b;
import java.io.File;
import shared.IBytestream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
//untested and incomplete.
public class pakfile
{
    public int objectcount;
    public IndexEntry[] indices;
    public PythonObject[] objects;

    int pythonversion;

    public void packPakFile(String inputFolder)
    {
        File inputDir = new File(inputFolder);
        File[] files = inputDir.listFiles();
        for(int i=0;i<files.length;i++)
        {
            File curfile = files[i];
            String curfilename = curfile.getName();
            if(curfilename.endsWith(".pyc"))
            {
                String internalName = curfilename.substring(0,curfilename.length()-5);
                
            }
        }
    }
    public List<pythondec.pycfile> extractPakFile(boolean prependPYCHeader)
    {
        List<pythondec.pycfile> r = new ArrayList();

        for(int i=0;i<objectcount;i++)
        {
            int size = objects[i].objectsize;
            byte[] rawdata = objects[i].rawCompiledPythonObjectData;
            String name = indices[i].objectname.toString();
            if(prependPYCHeader)
            {
                //The first 4 bytes are the magic number for that version of python.  The next 4 are a timestamp that doesn't matter, so I just set it to 0.
                byte[] header = null;
                if(pythonversion==22)
                {
                    header = new byte[]{(byte)0x2D,(byte)0xED,(byte)0x0D,(byte)0x0A,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
                }
                else if(pythonversion==23)
                {
                    header = new byte[]{(byte)0x3B,(byte)0xF2,(byte)0x0D,(byte)0x0A,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
                }
                else
                {
                    m.err("unhandled python version in extractPakFile");
                }
                rawdata = b.appendBytes(header,rawdata);
            }

            shared.IBytestream c = shared.ByteArrayBytestream.createFromByteArray(rawdata);
            pythondec.pycfile pyc = new pythondec.pycfile(c);
            pyc.filename = name;

            r.add(pyc);
        }
        
        return r;
    }
    public void extractPakFile(boolean prependPYCHeader, String outfolder)
    {
        for(int i=0;i<objectcount;i++)
        {
            int size = objects[i].objectsize;
            byte[] rawdata = objects[i].rawCompiledPythonObjectData;
            String name = indices[i].objectname.toString();
            if(prependPYCHeader)
            {
                //The first 4 bytes are the magic number for that version of python.  The next 4 are a timestamp that doesn't matter, so I just set it to 0.
                byte[] header = null;
                if(pythonversion==22)
                {
                    header = new byte[]{(byte)0x2D,(byte)0xED,(byte)0x0D,(byte)0x0A,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
                }
                else if(pythonversion==23)
                {
                    header = new byte[]{(byte)0x3B,(byte)0xF2,(byte)0x0D,(byte)0x0A,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
                }
                else
                {
                    m.err("unhandled python version in extractPakFile");
                }
                rawdata = b.appendBytes(header,rawdata);
            }
            
            //String filename = _staticsettings.outputdir+name+".pyc";
            String filename = outfolder+"/"+name;
            if(filename.endsWith(".py")) filename += "c";
            else filename+=".pyc";
            FileUtils.WriteFile(filename, rawdata,true,true);
        }
    }

    public static pakfile create(String filename, auto.AllGames.GameConversionSub g)
    {
        pakfile r = new pakfile(filename,g.g,true);
        return r;
    }
    public pakfile(String f, auto.AllGames.GameInfo g, boolean readPythonObjects)
    {
        this(new File(f),g,readPythonObjects);
    }
    public pakfile(File f, auto.AllGames.GameInfo g, boolean readPythonObjects)
    {
        //byte[] data = shared.FileUtils.ReadFile(f);
        //data = uru.UruCrypt.DecryptWhatdoyousee(data);
        this.pythonversion = g.PythonVersion;
        byte[] data = uru.UruCrypt.DecryptAny(f.getAbsolutePath(), g);
        IBytestream c = shared.ByteArrayBytestream.createFromByteArray(data);
        objectcount = c.readInt();
        indices = new IndexEntry[objectcount];
        for(int i=0;i<objectcount;i++)
        {
            indices[i] = new IndexEntry(c, g.game.readversion);
        }
        if(readPythonObjects)
        {
            objects = new PythonObject[objectcount];
            for(int i=0;i<objectcount;i++)
            {
                int offset = indices[i].offset;
                IBytestream c2 = c.Fork(offset);
                objects[i] = new PythonObject(c2, g.game.readversion);
            }
        }
    }
    
    public static class IndexEntry
    {
        public Urustring objectname;
        public int offset;
        
        public IndexEntry(IBytestream c, int readversion)
        {
            objectname = new Urustring(c, readversion);
            offset = c.readInt();
        }
        
        //public static IndexEntry
    }
    
    public static class PythonObject
    {
        int objectsize;
        byte[] rawCompiledPythonObjectData;
        
        public PythonObject(IBytestream c, int readversion)
        {
            objectsize = c.readInt();
            //account for bug in some Python utils:
            if(objectsize+c.getAbsoluteOffset()>c.getFilelength()) objectsize-=4;
            rawCompiledPythonObjectData = c.readBytes(objectsize);
        }
        
        private PythonObject(){}
        
        public static PythonObject create(byte[] rawCompiledHeaderlessPythonObjectData)
        {
            PythonObject result = new PythonObject();
            int length = rawCompiledHeaderlessPythonObjectData.length;
            result.objectsize = length;
            result.rawCompiledPythonObjectData = rawCompiledHeaderlessPythonObjectData;
            return result;
        }
    }
}
