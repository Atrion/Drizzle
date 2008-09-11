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

public class GetResource
{
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
}
