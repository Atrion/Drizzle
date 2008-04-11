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

import java.io.File;
import uru.m;
import uru.FileUtils;
import uru.moulprp.prpfile;
import uru.moulprp.prputils;
import uru.context; import uru.readexception;
import uru.Bytestream;

/**
 *
 * @author user
 */
public class allprpfiles
{
    //doesn't actually parse the objects, just the header and objectindex.
    public static void parseAllPrpfiles(PrpfileCallbackInterface callback, String prpdirname)
    {
        File prpfolder = new File(prpdirname);
        if (!prpfolder.isDirectory() || !prpfolder.exists())
        {
            m.err("Prp directory not in proper format or not found.");
            return;
        }
        
        File[] files = prpfolder.listFiles();
        m.msg("Parsing files... count="+Integer.toString(files.length));
        for(int i=0;i<files.length;i++)
        {
            File curfile = files[i];
            if(curfile.getName().toLowerCase().endsWith(".prp"))
            {
                //open prp file and process it.
                byte[] filedata = FileUtils.ReadFile(curfile);
                //prpfile prp = prputils.ProcessAll(filedata);
                context c = context.createDefault(new Bytestream(filedata));
                //c.readversion = 3;
                PrpHeader header = new PrpHeader(c);
                PrpObjectIndex objectindex = new PrpObjectIndex(c.Fork(new Bytestream(c.in,header.offsetToObjectIndex)));
                prpfile prp = new prpfile();
                prp.header = header;
                prp.objectindex = objectindex;
                
                //do callback.
                callback.handlePrpfile(prp);
            }
        }
    }

    public interface PrpfileCallbackInterface
    {
        void handlePrpfile(prpfile prp);
    }
    
    public interface RootobjCallbackInterface
    {
        void handleRootobj(prpfile prp, PrpObjectIndex.ObjectindexObjecttypeObjectdesc obj);
    }
    
    public static void parseAllRootobjs(RootobjCallbackInterface roCallback, String prpdirname)
    {
        class prpcallback implements PrpfileCallbackInterface
        {
            RootobjCallbackInterface roCallback;
            public prpcallback(RootobjCallbackInterface callback2)
            {
                roCallback = callback2;
            }
            public void handlePrpfile(prpfile prp)
            {
                int numtypes = prp.objectindex.indexCount;
                //int numobjects = prp.objects.size();
                for(int i=0;i<numtypes;i++)
                {
                    int numobjects = prp.objectindex.types[i].objectcount;
                    for(int j=0;j<numobjects;j++)
                    {
                        //rootobj curobj = prp.objects.get(i);
                        PrpObjectIndex.ObjectindexObjecttypeObjectdesc objdesc = prp.objectindex.types[i].descs[j];
                        roCallback.handleRootobj(prp, objdesc);
                    }
                }
            }
        }
        
        parseAllPrpfiles(new prpcallback(roCallback),prpdirname);
    }
}
