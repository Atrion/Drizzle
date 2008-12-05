/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filesearcher;

import java.io.File;
import java.util.Vector;
import shared.*;

public class search
{
    public static boolean searchForStrings(File f, String[] searchstrs)
    {
        byte[] data = FileUtils.ReadFile(f);
        String filedata = b.BytesToString(data);
        for(String searchstr: searchstrs)
        {
            int pos = filedata.indexOf(searchstr);
            if(pos==-1) return false;
        }
        return true;
    }
    public static boolean searchForString(File f, String searchstr)
    {
        return (searchForStringPos(f,searchstr)!=-1);
    }
    public static int searchForStringPos(File f, String searchstr)
    {
        byte[] data = FileUtils.ReadFile(f);
        String filedata = b.BytesToString(data);
        int index = filedata.indexOf(searchstr);
        //return (index!=-1);
        return index;
    }
    public static Vector<File> getAllFilesWithExtension(String folder, boolean recurse, String extension)
    {
        Vector<File> allfiles = getallfiles(folder,recurse);
        Vector<File> result = new Vector();
        for(File f: allfiles)
        {
            if(f.getName().endsWith(extension))
            {
                result.add(f);
            }
        }
        return result;
    }
    public static Vector<File> getallfiles(String folder, boolean recurse)
    {
        File dir = new File(folder);
        Vector<File> result = new Vector<File>();
        _getallfiles(result,dir,recurse);
        return result;
    }
    static void _getallfiles(Vector<File> result, File dir, boolean recurse)
    {
        File[] children = dir.listFiles();
        if(children==null)
        {
            int dummy=0;
            return;
        }
        for(File child: children)
        {
            if(child.isDirectory())
            {
                if(recurse)
                {
                    _getallfiles(result,dir,recurse);
                }
            }
            else
            {
                result.add(child);
            }
        }
    }
    /*public static File[] getallfiles(String folder, String regexp, boolean recurse)
    {
        File dir = new File(folder);
        return getallfiles(dir,regexp,recurse);
    }
    public static File[] getallfiles(File dir, String regexp, boolean recurse)
    {
        File[] children = dir.listFiles();
        for(File child: children)
        {
            String name = child.getName();
            java.util.regex.
        }
    }*/
}
