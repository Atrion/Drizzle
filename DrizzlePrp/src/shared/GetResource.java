/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import java.awt.Image;
import java.net.URL;
import java.awt.image.BufferedImage;
import shared.m;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.File;
import shared.Bytedeque2;

public class GetResource
{
    public static boolean hasResource(String path)
    {
        URL url = GetResource.class.getResource(path);
        return (url!=null);
    }
    
    public static Image getResourceAsImage(/*Object relativeObj,*/ String path)
    {
        try
        {
            URL url = GetResource.class.getResource(/*".."+*/path);
            //URL url = relativeObj.getClass().getResource(path);
            //javax.swing.ImageIcon image = new javax.swing.ImageIcon(url,"");
            BufferedImage img = javax.imageio.ImageIO.read(url);
            return img;
        }
        catch(Exception e)
        {
            m.err("Unable to load Image resource:"+path);
            return null;
        }
    }
    
    public static String getResourceAsString(String path)
    {
        try
        {
            URL url = GetResource.class.getResource(/*".."+*/path);
            InputStream in = url.openStream();
            int bytesread = 0;
            byte[] buffer = new byte[1024];
            StringBuilder result = new StringBuilder();
            while(bytesread!=-1)
            {
                bytesread = in.read(buffer,0,1024);
                if(bytesread!=-1)
                {
                    result.append(new String(buffer,0,bytesread));
                }
            }
            String result2 = result.toString();
            in.close();
            return result2;
        }
        catch(Exception e)
        {
            m.err("Unable to load Image resource:"+path);
            return null;
        }
    }
    
    public static File getResourceAsFile(String path, boolean deleteOnExit)
    {
        try
        {
            File temp = File.createTempFile("Drizzle", null);
            if(deleteOnExit) temp.deleteOnExit();
            FileOutputStream out = new FileOutputStream(temp);
            
            URL url = GetResource.class.getResource(path);
            InputStream in = url.openStream();
            int bytesread = 0;
            byte[] buffer = new byte[1024];
            //StringBuilder result = new StringBuilder();
            while(bytesread!=-1)
            {
                bytesread = in.read(buffer,0,1024);
                if(bytesread!=-1)
                {
                    //result.append(new String(buffer,0,bytesread));
                    out.write(buffer, 0, bytesread);
                }
            }
            //String result2 = result.toString();
            in.close();
            out.flush();
            out.close();
            return temp;
        }
        catch(Exception e)
        {
            m.err("Unable to open file resource:"+path);
            e.printStackTrace();
            return null;
        }
    }

    public static Bytes getResourceAsBytes(String path)
    {
        try
        {
            Bytedeque2 result = new Bytedeque2();

            URL url = GetResource.class.getResource(path);
            InputStream in = url.openStream();
            int bytesread = 0;
            byte[] buffer = new byte[1024];
            while(bytesread!=-1)
            {
                bytesread = in.read(buffer,0,1024);
                if(bytesread!=-1)
                {
                    result.writeBytes(buffer, 0, bytesread);
                }
            }
            in.close();
            Bytes result2 = result.getBytes();
            return result2;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new shared.uncaughtexception("Error while reading resource as Bytes.");
        }
    }
}
