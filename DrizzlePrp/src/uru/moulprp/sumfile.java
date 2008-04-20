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
import uru.Bytestream;
import uru.context;
import uru.readexception;
import uru.Bytedeque;
import uru.b;

public class sumfile
{
    int filecount;
    int u1; //always 0?
    sumfileFileinfo[] files;
    
    public sumfile(byte[] filedata, boolean isencrypted) throws readexception
    {
        if(isencrypted)
        {
            filedata = uru.UruUtils.DecryptWhatdoyousee(filedata);
        }
        
        context c = new context(new Bytestream(filedata));
        
        filecount = c.readInt();
        u1 = c.readInt();
        files = c.readVector(sumfileFileinfo.class, filecount);
    }
    
    public static class sumfileFileinfo extends uruobj
    {
        Urustring filename;
        byte[] md5;
        int timestamp;
        int u2; //always 0?
        
        //private sumfileFileinfo()
        //{
        //}
        
        public sumfileFileinfo(context c)
        {
            filename = new Urustring(c);
            md5 = c.readBytes(16);
            timestamp = c.readInt();
            u2 = c.readInt();
        }
    }
    
    public static void createSumfile(String infolder, String agename, String outfolder)
    {
        File datdir = new File(infolder);
        if(!datdir.exists() || !datdir.isDirectory())
        {
            m.err("Dat folder not found.");
            return;
        }
        
        //count the files.
        int count = 0;
        File[] datfiles = datdir.listFiles();
        for(int i=0;i<datfiles.length;i++)
        {
            String curfilename = datfiles[i].getName();
            if(curfilename.startsWith(agename+"_") && curfilename.endsWith(".prp"))
            {
                //add this file.
                count++;
            }
        }
        
        Bytedeque c = new Bytedeque();
        c.writeInt(count);
        c.writeInt(0);
        for(int i=0;i<datfiles.length;i++)
        {
            String curfilename = datfiles[i].getName();
            if(curfilename.startsWith(agename+"_") && curfilename.endsWith(".prp"))
            {
                //add this file.
                
                //filename
                byte[] path = {'d','a','t','\\'};
                byte[] filenameBytes = b.appendBytes(path, b.StringToBytes(curfilename));
                Urustring filename = new Urustring(filenameBytes);
                filename.compile(c);
                
                //md5
                byte[] filecontents = uru.FileUtils.ReadFile(datfiles[i]);
                byte[] md5 = uru.CryptHashes.GetMd5(filecontents);
                c.writeBytes(md5);
                
                //long timestamp = datfiles[i].lastModified()/1000L;
                //int timestamp2 = (int)timestamp;
                //c.writeInt(timestamp2);
                c.writeInt(0); //supposed to be timestamp, but doesn't matter. # of seconds since epoch
                c.writeInt(0);
            }
        }
        
        byte[] result = c.getAllBytes();
        result = uru.UruUtils.EncryptWhatdoyousee(result);
        
        uru.FileUtils.WriteFile(outfolder+agename+".sum", result);
    }
}
