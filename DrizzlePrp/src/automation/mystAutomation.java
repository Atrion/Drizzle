/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import shared.FileUtils;
import shared.Bytes;
import uru.UruCrypt;
import uru.moulprp.textfile;
import java.util.HashMap;
import uru.Bytestream;
import uru.context;
import uru.moulprp.prpfile;
import shared.m;

public class mystAutomation
{
    public static void convertEoaToWhatdoyousee(String infile, String outfile)
    {
        Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
        Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);
        Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
        FileUtils.WriteFile(outfile, wdysData);
    }
    public static String getAgenameFromFilename(String filename)
    {
        int pos = filename.lastIndexOf("/");
        if(pos != -1)
        {
            filename = filename.substring(pos+1);
        }
        
        pos = filename.lastIndexOf("\\");
        if(pos != -1)
        {
            filename = filename.substring(pos+1);
        }
        
        pos = filename.lastIndexOf(".");
        if(pos != -1)
        {
            filename = filename.substring(0, pos);
        }
        
        pos = filename.indexOf("_");
        if(pos != -1)
        {
            filename = filename.substring(0,pos);
        }
        
        return filename;
    }
    public static void convertCrowthistleToPots(String crowthistlefolder, String potsfolder)
    {
        HashMap<String, Integer> prefices = new HashMap<String, Integer>();
        prefices.put("MarshScene", 96);
        prefices.put("MountainScene", 95);
        
        String[] fnifiles = {
            "MarshScene.fni",
            "MountainScene.fni",
        };
        String[] agefiles = {
            "MarshScene.age",
            "MountainScene.age",
        };
        String[] prpfiles = {
            "MarshScene_Exterior.prp",
            "MarshScene_Extras.prp",
            "MarshScene_MWInterior.prp",
            "MarshScene_Textures.prp",
            "MarshScene_TourCamera.prp",
            "MarshScene_WaterHorses.prp",
            "MountainScene_Courtyard.prp",
            "MountainScene_EllenHallInterior.prp",
            "MountainScene_Exterior.prp",
            "MountainScene_Extras.prp",
            "MountainScene_Textures.prp",
            "MountainScene_TourCamera.prp",
            "MountainScene_tw_g1_g2.prp",
            "MountainScene_tw_g1_g3.prp",
            "MountainScene_tw_g1_hm.prp",
            "MountainScene_tw_g2_g1.prp",
            "MountainScene_tw_g2_g3.prp",
            "MountainScene_tw_g2_hm.prp",
            "MountainScene_tw_g3_g1.prp",
            "MountainScene_tw_g3_g2.prp",
            "MountainScene_tw_g3_hm.prp",
            "MountainScene_tw_hm_g1.prp",
            "MountainScene_tw_hm_g2.prp",
            "MountainScene_tw_hm_g3.prp",
            "MountainScene_tw_shape.prp",
            "MountainScene_tw_w1.prp",
            "MountainScene_tw_w2.prp",
            "MountainScene_tw_w3.prp",
            "MountainScene_Vista.prp",
        };
        
        //convert .fni files...
        for(String filename: fnifiles)
        {
            String infile = crowthistlefolder + "/dat/" + filename;
            String outfile = potsfolder + "/dat/" + filename;
            
            Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
            Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);
            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        //convert .age files...
        for(String filename: agefiles)
        {
            String infile = crowthistlefolder + "/dat/" + filename;
            String outfile = potsfolder + "/dat/" + filename;
            String agename = getAgenameFromFilename(filename);
            
            Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
            Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);
            
            //modify sequence prefix if Age is in list.
            Integer prefix = prefices.get(agename);
            if(prefix!=null)
            {
                textfile agefile = textfile.createFromBytes(decryptedData);
                agefile.setVariable("SequencePrefix", Integer.toString(prefix));
                decryptedData = agefile.saveToBytes();
            }
            
            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        //convert .prp files...
        for(String filename: prpfiles)
        {
            String infile = crowthistlefolder + "/dat/" + filename;
            String outfile = potsfolder + "/dat/" + filename;
            String agename = getAgenameFromFilename(filename);
            
            Bytes prpdata = Bytes.createFromFile(infile);
            Bytestream bytestream = Bytestream.createFromBytes(prpdata);
            context c = context.createFromBytestream(bytestream);
            
            //modify sequence prefix if Age is in list.
            Integer prefix = prefices.get(agename);
            if(prefix!=null)
            {
                c.sequencePrefix = prefix;
            }
            
           prpfile prp = prpfile.createFromContext(c);
           Bytes prpoutputbytes = prp.saveAsBytes();
           prpoutputbytes.saveAsFile(outfile);
        }
        
        m.msg("Done Crowthistle work!");
    }
    
}
