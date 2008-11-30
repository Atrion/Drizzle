/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import java.io.File;
import java.util.Vector;
import uru.vault.*;
import shared.*;

public class vaultAutomation
{
    public static void saveImages(String infolder, String outfolder)
    {
        Vector<vfile> vfiles = readFolder(infolder);
        Vector<NodeTypes.ImageType> images = findRecordsOfType(vfiles, NodeTypes.ImageType.class);
        int i=0;
        for(NodeTypes.ImageType image: images)
        {
            m.msg("Agename: "+image.getAgeName());
            m.msg("Caption: "+image.getCaption());
            byte[] data = image.getImageData();
            String outfilename = outfolder+"/"+Sanitise.SanitiseFilename(image.getAgeName()+"-("+Integer.toString(i)+")-"+image.getCaption())+".jpg";
            FileUtils.WriteFile(outfilename, data);
            i++;
        }
    }
    
    public static Vector<vfile> readFolder(String infolder)
    {
        Vector<vfile> vfiles = new Vector();
        
        File folder = new File(infolder);
        for(File child: folder.listFiles())
        {
            if(child.getName().endsWith(".v"))
            {
                vfile vf = vfile.createFromFilename(child.getAbsolutePath());
                vfiles.add(vf);
            }
        }
        return vfiles;
    }
    
    public static <T> Vector<T> findRecordsOfType(Vector<vfile> vfiles, Class<T> cls)
    {
        Vector<T> result = new Vector();
        
        for(vfile vf: vfiles)
        {
            T t = vf.node.castTo(cls);
            if(t!=null) result.add(t);
        }
        
        return result;
    }
}
