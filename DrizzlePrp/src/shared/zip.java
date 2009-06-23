/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import java.util.zip.Inflater;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.Vector;
import java.util.zip.ZipFile;
import java.util.Enumeration;
import java.util.zip.ZipEntry;

public class zip
{
    public static byte[] decompressZlib(byte[] input)
    {
        try
        {
            //byte[] compressedData = c.readBytes(compressedLength);
            Inflater decomp = new Inflater();
            decomp.setInput(input);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            while(!decomp.finished())
            {
                int num = decomp.inflate(buffer);
                out.write(buffer,0,num);
            }
            out.close();
            byte[] result = out.toByteArray();
            return result;
        }
        catch(Exception e)
        {
            throw new shared.uncaughtexception("Exception during zip decompression.");
        }
    }
    public static byte[] decompressGzip(byte[] input)
    {
        try
        {
            //byte[] compressedData = c.readBytes(compressedLength);
            //Inflater decomp = new java.util.zip.Inflater();
            ByteArrayInputStream instr = new ByteArrayInputStream(input);
            GZIPInputStream ginstr = new java.util.zip.GZIPInputStream(instr);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int num;
            while((num=ginstr.read(buffer))>0)
            {
                //int num = decomp.inflate(buffer);
                out.write(buffer,0,num);
            }
            ginstr.close();
            out.close();
            byte[] result = out.toByteArray();
            return result;
        }
        catch(Exception e)
        {
            throw new shared.uncaughtexception("Exception during gzip decompression.");
        }
    }
    public static byte[] compressGzip(byte[] input)
    {
        try
        {
            ByteArrayOutputStream baout = new ByteArrayOutputStream();
            GZIPOutputStream out = new GZIPOutputStream(baout);

            ByteArrayInputStream in = new ByteArrayInputStream(input);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();

            out.finish();
            out.close();
            byte[] result = baout.toByteArray();
            return result;

        }
        catch(Exception e)
        {
            throw new shared.uncaughtexception("Exception during gzip compression.");
        }
    }
    public static Vector<String> getAllEntries(String zipfilename)
    {
        Vector<String> result = new Vector();
        try{
            ZipFile zf = new java.util.zip.ZipFile(zipfilename);
            java.util.Enumeration entries = zf.entries();
            while(entries.hasMoreElements())
            {
                ZipEntry ze = (ZipEntry)entries.nextElement();
                String name = ze.getName();
                result.add(name);
                //m.msg(name);
            }
        }catch(Exception e)
        {
            throw new shared.uncaughtexception("Error while reading zipfile. "+e.getMessage());
        }
        return result;
    }
}
