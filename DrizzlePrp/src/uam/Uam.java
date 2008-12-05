/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uam;

import javax.swing.event.ListSelectionEvent;
import shared.b;
import shared.m;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.util.Vector;
import shared.*;
import java.util.HashMap;
import java.io.File;


public class Uam
{
    public static UamConfig ageList;
    public static HashMap<String,InstallStatus> ageInstallStatus;
    public static final String ageArchivesFolder = "/agearchives/";
    public static final String versionSep = "--";
    public static final String statusFilename = "uam.status.txt";
    
    public static enum InstallStatus
    {
        notInstalled,
        latestVersionInCache,
        nonLatestVersionInCache,
        notInCache,
    }
    public static void launchUru()
    {
        String potsfolder = getPotsFolder()+"/";
        if(!automation.detectinstallation.isFolderPots(potsfolder)) return;
        String[] command = new String[]{
            potsfolder+"UruSetup.exe",
        };
        try
        {
            java.lang.Process p = Runtime.getRuntime().exec(command, null, new File(potsfolder));
            //Process proc = Runtime.getRuntime().exec(command);
            m.status("Uru launched!");
        }
        catch(java.io.IOException e)
        {
            m.err("Unable to launch Uru.");
        }

                
    }
    public static String getPotsFolder()
    {
        return shared.State.AllStates.getStateAsString("uamRoot");
    }
    public static void DownloadAge7Zip(String age,String ver,String mir,String potsfolder)
    {
        //ensure pots folder. We don't need this here.
        //if(!automation.detectinstallation.isFolderPots(potsfolder))
        //{
        //    //return false;
        //    return;
        //}
        
        //get hash
        //String hash = ageList.getWhirlpool(age, ver);
        String hash = ageList.getSha1(age, ver);
        
        //start work in another thread.
        ThreadDownloadAndProcess.downloadAge(age,ver,mir,potsfolder,hash);
        
        //return true;
    }
    public static void listAvailableAges()
    {
        if(ageList==null)
        {
            m.msg("You have to get the list of available Ages first.");
            return;
        }
        for(String s: ageList.getAllAgeNames())
        {
            m.msg("Age: "+s);
            for(String ver: ageList.getAllVersionsOfAge(s))
            {
                m.msg("  Ver: "+ver);
                for(String mirror: ageList.getAllUrlsOfAgeVersion(s, ver))
                {
                    m.msg("    Mir: "+mirror);
                }
            }
        }
    }
    /*public static Vector<String> GetAgeList(String server, gui.Gui gui)
    {
        //GuiModal modal = new GuiModal(null,true);
        //modal.setVisible(true);
        //modal.showButDontBlock();
        
        //SevenZip.J7zip 
        
        //String file = "http://www.the-ancient-city.de/uru-ages/Abysos.rar";
        //String file = "http://www.the-ancient-city.de/uru-ages/Dustin2.rar";
        //ThreadDownloadAndProcess.downloadConfig(server,gui);
        //String file = server+"/uam.status.txt";
        //byte[] result = ThreadDownloader.downloadAsBytes(file);
        //return result;
        //if(true)return;
        
        //parse config file.
        //ByteArrayInputStream in = new ByteArrayInputStream(result);
        //ageList = new UamConfig(in);
        
        //list Ages...
        //return ageList.getAllAgeNames();
        
        
        //String statusfile = server+"/uam.status.txt";
        //String statusfile = "http://www.the-ancient-city.de/uru-ages/Abysos.rar";
        //byte[] data = uam.Downloader.DownloadFile(statusfile);
        //String datastr = b.BytesToString(data);
        //m.msg(datastr);
    }*/
}