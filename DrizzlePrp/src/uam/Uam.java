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
    public static UamConfigNew ageList;
    //public static HashMap<String,InstallStatus> ageInstallStatus;
    public static InstallInfo installInfo;
    public static final String ageArchivesFolder = "/agearchives/";
    public static final String versionSep = "--";
    public static final String statusFilename = "uam.status.xml";
    //public static final int version = 16;
    
    public static class InstallInfo
    {
        public HashMap<String, AgeInstallInfo> ages = new HashMap();
        public int numNotInstalled;
        public int numUnknown;
        public int numUpdatable;
        public boolean fullyUpToDate;
        
        public AgeInstallInfo getOrCreateAge(String age)
        {
            AgeInstallInfo result = ages.get(age);
            if(result==null)
            {
                result = new AgeInstallInfo();
                ages.put(age, result);
            }
            return result;
        }
        
        public AgeInstallInfo getAge(String age)
        {
            return ages.get(age);
        }
        
        public void countStats()
        {
            this.numNotInstalled = 0;
            this.numUnknown = 0;
            this.numUpdatable = 0;
            for(AgeInstallInfo ageinfo: ages.values())
            {
                switch(ageinfo.installationStatus)
                {
                    case latestVersionInCache:
                        break;
                    case noVersionsExist:
                        break;
                    case nonLatestVersionInCache:
                        this.numUpdatable++;
                        break;
                    case notInCache:
                        this.numUnknown++;
                        break;
                    case notInstalled:
                        this.numNotInstalled++;
                        break;
                    default:
                        m.err("Unhandled installation type.");
                        break;
                }
            }
            this.fullyUpToDate = (this.numUnknown==0 && this.numNotInstalled==0 && this.numUpdatable==0);
        }
        
        public void printStatsMessage()
        {
            this.countStats();
            String result;
            if(this.fullyUpToDate)
            {
                result = "You have everything installed!";
            }
            else
            {
                StringBuilder result2 = new StringBuilder();
                if(this.numNotInstalled!=0) result2.append("Ages not installed: "+Integer.toString(this.numNotInstalled)+"    ");
                if(this.numUpdatable!=0) result2.append("Ages updatable: "+Integer.toString(this.numUpdatable)+"    ");
                if(this.numUnknown!=0) result2.append("Ages with unknown installation status: "+Integer.toString(this.numUnknown)+"    ");
                result = result2.toString();
            }
            m.msgsafe(result);
        }
    }
    public static class AgeInstallInfo
    {
        public InstallStatus installationStatus = InstallStatus.notInstalled;
        public HashMap<String, InstallStatus> versions = new HashMap();
        
        /*public AgeInstallInfo(InstallStatus installationStatus)
        {
            this.installationStatus = installationStatus;
        }*/
        
    }
    public static enum InstallStatus
    {
        noVersionsExist,
        notInstalled,
        latestVersionInCache,
        nonLatestVersionInCache,
        notInCache,
        ;
        
        public boolean isInstalled()
        {
            return !(this==notInstalled || this==noVersionsExist);
        }
    }

    public static void RunTests(String potsfolder)
    {
        if(!automation.detectinstallation.isFolderPots(potsfolder)) return;
        boolean ignoreKnownOverrides = shared.State.AllStates.getStateAsBoolean("uamig");

        m.msg("Checking for python file duplicates...");
        java.util.HashMap<String, Vector<String>> pyfiles = new java.util.HashMap();
        Vector<File> pakfiles = shared.FileUtils.FindAllFiles(potsfolder+"/Python/", ".pak", false);
        for(File f: pakfiles)
        {
            //m.msg(f.getName());
            uru.moulprp.pakfile pak = new uru.moulprp.pakfile(f, 3, true);
            for(uru.moulprp.pakfile.IndexEntry ind: pak.indices)
            {
                String pyname = ind.objectname.toString();
                Vector<String> paklist = pyfiles.get(pyname);
                if(paklist==null)
                {
                    pyfiles.put(pyname, new Vector());
                    pyfiles.get(pyname).add(f.getName());
                }
                else pyfiles.get(pyname).add(f.getName());
            }
        }
        for(String pyfile: pyfiles.keySet())
        {
            Vector<String> paklist = pyfiles.get(pyfile);
            boolean complain = false;
            if(paklist.size()==2)
            {
                if(ignoreKnownOverrides && (paklist.contains("moul.pak")||paklist.contains("offlineki.pak")||paklist.contains("UruLibraryManager.pak")))
                {
                    //ignore
                }
                else
                {
                    complain = true;
                }
            }
            else if(paklist.size()>2)
            {
                complain = true;
            }

            if(complain)
            {
                String complaint = "The file "+pyfile+" was found in these files: ";
                for(String pakfile: paklist)
                {
                    complaint += pakfile+", ";
                }
                m.msg(complaint);
            }
        }
        m.msg("Done checking for python file duplicates.");


        m.msg("Checking for sequence prefix duplicates...");
        java.util.HashMap<String, String> seqprefs = new java.util.HashMap();
        Vector<File> agefiles = shared.FileUtils.FindAllFiles(potsfolder+"/dat/", ".age", false);
        for(File f: agefiles)
        {
            uru.moulprp.textfile agefile = uru.moulprp.textfile.createFromBytes(uru.UruCrypt.DecryptWhatdoyousee(FileUtils.ReadFile(f)));
            String seq = agefile.getVariable("SequencePrefix");
            if(seqprefs.containsKey(seq))
            {
                m.msg("Sequence Prefix from "+f.getName()+" already used in "+seqprefs.get(seq));
            }
            else
            {
                seqprefs.put(seq, f.getName());
            }
        }
        m.msg("Done checking for sequence prefix duplicates.");

    }
    public static void launchUru()
    {
        shared.Exec.LaunchProgram(getPotsFolder()+"/"+"UruSetup.exe", "Uru");
        /*String potsfolder = getPotsFolder()+"/";
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
        }*/

    }
    public static void launchSoundDecompress()
    {
        shared.Exec.LaunchProgram(getPotsFolder()+"/"+"SoundDecompress.exe", "SoundDecompress");
    }
    public static String getPotsFolder()
    {
        return shared.State.AllStates.getStateAsString("uamRoot");
    }
    /*public static void DownloadAge7Zip(String age,String ver,String mir,String potsfolder)
    {
    }*/
    public static void listAvailableAges()
    {
        if(ageList==null)
        {
            m.msg("You have to get the list of available Ages first.");
            return;
        }
        /*for(String s: ageList.getAllAgeNames())
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
        }*/
        for(UamConfigNew.UamConfigData.Age age: ageList.data.ages)
        {
            m.msg("Age: "+age.filename);
            for(UamConfigNew.UamConfigData.Age.Version ver: age.versions)
            {
                m.msg("  Ver: "+ver.name);
                for(UamConfigNew.UamConfigData.Age.Version.Mirror mir: ver.mirrors)
                {
                    m.msg("    Mir: "+mir.url);
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
