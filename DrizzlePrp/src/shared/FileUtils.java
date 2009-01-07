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

package shared;

//import java.io.FileReader;
//import gui.*;
import uru.*;
import shared.m;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;

import java.nio.channels.FileChannel;

/**
 *
 * @author user
 */
public class FileUtils {
    
    //only properly handles files less than 4GB.
    
    private static String initialWorkingDirectory; //you may have to call some function in FileUtils on startup for this to be correct.
    private static final long freespaceMargin = 10000000;
    
    static
    {
        initialWorkingDirectory = GetPresentWorkingDirectory();
    }
    
    public static boolean HasFreeSpace(String filename, long minimum)
    {
        return HasFreeSpace(new File(filename), minimum);
    }
    
    public static boolean HasFreeSpace(File file, long minimum)
    {
        long usablespace = file.getUsableSpace();
        boolean result = usablespace > minimum + freespaceMargin;
        return result;
    }
    
    public static String GetInitialWorkingDirectory()
    {
        return initialWorkingDirectory;
    }
    
    public static String GetPresentWorkingDirectory()
    {
        //return new File(".").getPath();
        return new File(".").getAbsolutePath();
    }
    public static boolean Exists(String filename)
    {
        File file = new File(filename);
        return file.exists();
    }
    public static void DeleteFile(String filename)
    {
        File file = new File(filename);
        if(file.exists())
        {
            boolean result = file.delete();
            if(!result) m.err("Unable to delete file: "+filename);
        }
    }
    public static void CopyFile(String infile, String outfile, boolean overwrite)
    {
        File in = new File(infile);
        File out = new File(outfile);
        FileChannel inchan = null;
        FileChannel outchan = null;
        if(out.exists())
        {
            if(!overwrite) return;
            
            FileUtils.DeleteFile(outfile);
        }
        
        
        try
        {
            inchan = new FileInputStream(in).getChannel();
            outchan = new FileOutputStream(out).getChannel();
            inchan.transferTo(0, inchan.size(), outchan);
        }
        catch(Exception e)
        {
            m.err("Unable to copy file "+infile+" to "+outfile);
        }
        finally
        {
            try
            {
                if(inchan!=null) inchan.close();
                if(outchan!=null) outchan.close();
            }
            catch(Exception e)
            {
                m.err("Unable to close file copying channel.");
            }
        }
    }
    static public byte[] ReadFile(String filename)
    {
        File file = new File(filename);
        return ReadFile(file);
    }
    static public String ReadFileAsString(File filename)
    {
        return new String(ReadFile(filename));
    }
    static public String ReadFileAsString(String filename)
    {
        File file = new File(filename);
        return ReadFileAsString(file);
    }
    static public Bytes ReadFileAsBytes(String filename)
    {
        File file = new File(filename);
        return ReadFileAsBytes(file);
    }
    public static Bytes ReadFileAsBytes(File filename)
    {
        return new Bytes(ReadFile(filename));
    }
    static public byte[] ReadFile(File filename)
    {
        try
        {
            //FileReader reader = new FileReader(filename);
            FileInputStream reader = new FileInputStream(filename);
            int filelength = (int)filename.length(); //loss of precision.
            byte[] result = new byte[filelength];
            int bytesread = reader.read(result,0,filelength);
            if(bytesread!=filelength) throw new Exception("Incorrect # of bytes read.");
            reader.close();
            return result;
        }
        catch(Exception e)
        {
            m.err("Error reading file:"+filename.getAbsolutePath()+":"+e.getMessage());
            return null;
        }
        
    }
    
    static public void WriteFile(String filename, byte[] content)
    {
        File file = new File(filename);
        WriteFile(file,content);
    }
    static public void WriteFile(String filename, Bytes content)
    {
        WriteFile(filename,content.getByteArray());
    }
    static public void WriteFile(File filename, byte[] content)
    {
        try
        {
            FileOutputStream writer = new FileOutputStream(filename);
            int filelength = content.length;
            writer.write(content);
            writer.flush();
            writer.close();
            int actuallength = (int)filename.length(); //loss of precision.
            if(actuallength != filelength) throw new Exception("Error writing file, not correct length.");
            writer.close();
        }
        catch(Exception e)
        {
            m.err("Error writing file:"+filename.getAbsolutePath()+":"+e.getMessage());
        }
        
    }
    static public void AppendText(String filename, String text)
    {
        try
        {
            FileOutputStream writer = new FileOutputStream(filename,true); //append
            byte[] bytes = b.StringToBytes(text);
            writer.write(bytes);
            writer.close();
        }
        catch(Exception e)
        {
            m.err("Error appending file:"+filename);
        }
    }
    static public void CreateFolder(String filename)
    {
        File f = new File(filename);
        f.mkdirs();
    }
}
