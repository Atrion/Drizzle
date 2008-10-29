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

/**
 *
 * @author user
 */
//untested and incomplete.
public class pakfile
{
    int objectcount;
    IndexEntry[] indices;
    PythonObject[] objects;
    
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
    
    public void extractPakFile(boolean prependPYCHeader, int pythonversion)
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
                    header = new byte[]{(byte)0x6D,(byte)0xF2,(byte)0x0D,(byte)0x0A,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
                }
                else
                {
                    m.err("unhandled python version in extractPakFile");
                }
                rawdata = b.appendBytes(header,rawdata);
            }
            
            String filename = _staticsettings.outputdir+name+".pyc";
            FileUtils.WriteFile(filename, rawdata);
        }
    }
    
    public pakfile(context c)
    {
        objectcount = c.readInt();
        indices = new IndexEntry[objectcount];
        for(int i=0;i<objectcount;i++)
        {
            indices[i] = new IndexEntry(c);
        }
        objects = new PythonObject[objectcount];
        for(int i=0;i<objectcount;i++)
        {
            objects[i] = new PythonObject(c);
        }
    }
    
    public static class IndexEntry
    {
        Urustring objectname;
        int offset;
        
        public IndexEntry(context c)
        {
            objectname = new Urustring(c);
            offset = c.readInt();
        }
        
        //public static IndexEntry
    }
    
    public static class PythonObject
    {
        int objectsize;
        byte[] rawCompiledPythonObjectData;
        
        public PythonObject(context c)
        {
            objectsize = c.readInt();
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
