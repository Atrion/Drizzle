/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uam;

import shared.*;
import java.io.ByteArrayInputStream;

public class ThreadDownloadAndProcess extends Thread
{
    Jobs job = Jobs.none;
    
    String age;
    String ver;
    String mir;
    String potsfolder;
    String whirlpool;
    
    String server;
    gui.Gui gui;
    shared.delegate callback;
    
    public static enum Jobs
    {
        none,
        downloadConfig,
        downloadAge7z,
    }
    
    public static void downloadConfig(String server, shared.delegate callback)
    {
        ThreadDownloadAndProcess thread = new ThreadDownloadAndProcess();
        thread.job = Jobs.downloadConfig;
        thread.server = server;
        //thread.gui = gui;
        //thread.potsfolder = potsfolder;
        thread.callback = callback;
        thread.start();
    }
    public static void engage(String age,String ver,String mir,String potsfolder,String whirlpool)
    {
        ThreadDownloadAndProcess thread = new ThreadDownloadAndProcess();
        thread.job = Jobs.downloadAge7z;
        thread.age = age;
        thread.ver = ver;
        thread.mir = mir;
        thread.potsfolder = potsfolder;
        thread.whirlpool = whirlpool;
        thread.start();
    }
    
    public ThreadDownloadAndProcess()
    {
        
    }
    
    @Override public void run()
    {
        if(job==Jobs.none)
        {
            //do nothing.
        }
        else if(job==Jobs.downloadAge7z)
        {
            //prepare output file.
            String outputfolder = potsfolder+Uam.ageArchivesFolder;
            FileUtils.CreateFolder(outputfolder);
            String outputfile = outputfolder+age+"-"+ver+".7z";

            //download file.
            ThreadDownloader.downloadAsFile(mir, outputfile);
            InvisibleModal modal = InvisibleModal.createAndShow();

            //check integrity.
            m.status("Checking integrity...");
            byte[] hash = shared.CryptHashes.GetWhirlpool(outputfile);
            String hashstr = b.BytesToHexString(hash);
            boolean isgood = whirlpool.equals(hashstr);
            if(!isgood)
            {
                m.err("Bad file integrity. The Age downloaded wasn't what was expected, perhaps because the version on the server is corrupted.");
                FileUtils.DeleteFile(outputfile);
                return;
            }
            m.status("File integrity is good!");
            
            //extract.
            shared.sevenzip.extract(outputfile, potsfolder);
            
            m.status("Age installed!");
            
            modal.hideInvisibleModal();
        }
        else if(job==Jobs.downloadConfig)
        {
            String file = server+"/uam.status.txt";
            byte[] result = ThreadDownloader.downloadAsBytes(file);
            InvisibleModal modal = InvisibleModal.createAndShow();
            
            //parse config file.
            ByteArrayInputStream in = new ByteArrayInputStream(result);
            UamConfig ageList = new UamConfig(in);
        
            //callback
            callback.callback(ageList);
            
            modal.hideInvisibleModal();
        }
        
    }
    
}
