/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import java.io.InputStream;
import SevenZip.Archive.IInArchive;
import SevenZip.Archive.SevenZip.Handler;
import SevenZip.IInStream;
import SevenZip.MyRandomAccessFile;
import java.io.RandomAccessFile;
import java.io.IOException;
import SevenZip.Archive.SevenZipEntry;
import SevenZip.ArchiveExtractCallback;

//7zip needs to do random file access.
//And you need to implement IInStream, which MyRandomAccessFile does for actual files.
//I could easily create a similar one for Byte Arrays if we want to keep this in memory.
public class sevenzip
{
    public void test(String filename)
    {
        try
        {
            //use this for files on disk.
            FileIInStream istream = new FileIInStream(filename);

            IInArchive archive = new Handler();
            int result = archive.Open(istream);
            if(result!=0)
            {
                m.err("Problem opening/reading 7z file.");
            }
            
            ArchiveExtractCallback callback = new ArchiveExtractCallback();
            callback.Init(archive);
            
            //the -1 says extract all files, so we don't need the 1st argument.
            //the NExtract_NAskMode_kExtract means we want to extract the files.
            int result2 = archive.Extract(null, -1, IInArchive.NExtract_NAskMode_kExtract, callback);
            if(result2!=0)
            {
                //error
                m.err("Error during extraction.");
            }
            long numerrors = callback.NumErrors;
            if(numerrors!=0)
            {
                //errors during extraction.
                m.err("Errors during extraction: "+Long.toString(numerrors));
            }
            
            //this block goes through all the entries.
            int count = archive.size();
            for(int i=0;i<count;i++)
            {
                SevenZipEntry entry = archive.getEntry(i);
                //entry.
            }
            
            
            archive.close();
        }
        catch(Exception e)
        {
            
        }
    }
    
    public static class FileIInStream extends IInStream
    {
        RandomAccessFile f;
        public FileIInStream(String filename) throws IOException
        {
            f  = new RandomAccessFile(filename, "r");
        }
        public long Seek(long offset, int seekOrigin) throws IOException
        {
            if (seekOrigin==STREAM_SEEK_SET)
            {
                f.seek(offset);
            }
            else if (seekOrigin==STREAM_SEEK_CUR)
            {
                f.seek(offset + f.getFilePointer());
            }
            return f.getFilePointer();
        }
        public int read() throws IOException
        {
            return f.read();
        }
        public int read(byte [] data, int off, int size) throws IOException
        {
            return f.read(data,off,size);
        }
        public void close() throws IOException
        {
            f.close();
            f = null;
        }   
    }
}
