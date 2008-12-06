/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uam;

import shared.*;
import java.io.ByteArrayInputStream;
import java.io.File;

public class ThreadDownloadAndProcess extends Thread
{
    Jobs job = Jobs.none;
    
    String age;
    String ver;
    String mir;
    String potsfolder;
    String whirlpool;
    
    String server;
    gui.Gui guiform;
    shared.delegate callback;
    boolean doDownload;
    
    boolean wasSuccessful = false;
    
    public static enum Jobs
    {
        none,
        downloadConfig,
        downloadAge7z,
    }
    
    public static void downloadConfig(String server, String potsfolder, shared.delegate callback)
    {
        ThreadDownloadAndProcess thread = new ThreadDownloadAndProcess();
        thread.job = Jobs.downloadConfig;
        thread.server = server;
        //thread.gui = gui;
        //thread.potsfolder = potsfolder;
        thread.callback = callback;
        thread.potsfolder = potsfolder;
        thread.doDownload = true;
        thread.start();
        //m.msg("downloadconfig done.");
    }
    public static void downloadAge(String age,String ver,String mir,String potsfolder,String whirlpool)
    {
        ThreadDownloadAndProcess thread = new ThreadDownloadAndProcess();
        thread.job = Jobs.downloadAge7z;
        thread.age = age;
        thread.ver = ver;
        thread.mir = mir;
        thread.potsfolder = potsfolder;
        thread.whirlpool = whirlpool;
        thread.doDownload = true;
        thread.start();
    }
    public static void extractAge(String age,String ver,String potsfolder,String whirlpool)
    {
        ThreadDownloadAndProcess thread = new ThreadDownloadAndProcess();
        thread.job = Jobs.downloadAge7z;
        thread.age = age;
        thread.ver = ver;
        thread.potsfolder = potsfolder;
        thread.whirlpool = whirlpool;
        thread.doDownload = false;
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
            wasSuccessful = true;
        }
        else if(job==Jobs.downloadAge7z)
        {
            //prepare output file.
            String outputfolder = potsfolder+Uam.ageArchivesFolder;
            FileUtils.CreateFolder(outputfolder);
            String outputfile = outputfolder+age+uam.Uam.versionSep+ver+".7z";

            //download file.
            if(doDownload)
            {
                String tempoutputfile = outputfile;//+".part";
                if(!ThreadDownloader.downloadAsFile(mir, tempoutputfile))
                {
                    //failed
                    FileUtils.DeleteFile(tempoutputfile);
                    wasSuccessful = false;
                    return;
                }
                else
                {
                    //success
                    //File f = new File(tempoutputfile);
                    // f.
                }
            }
            
            InvisibleModal modal = InvisibleModal.createAndShow();
            try{

            //check integrity.
            m.status("Checking integrity...");
            //byte[] hash = shared.CryptHashes.GetWhirlpool(outputfile);
            byte[] hash = shared.CryptHashes.GetHash(outputfile, shared.CryptHashes.Hashtype.sha1);
            String hashstr = b.BytesToHexString(hash);
            boolean isgood = whirlpool.equals(hashstr);
            if(!isgood)
            {
                m.err("Bad file integrity. The Age downloaded wasn't what was expected, perhaps because the version on the server is corrupted.");
                FileUtils.DeleteFile(outputfile);
                wasSuccessful = false;
                return;
            }
            m.status("File integrity is good!");
            
            //extract.
            if(!shared.sevenzip.extract(outputfile, potsfolder))
            {
                wasSuccessful = false;
                return;
            }
            
            //callback
            //callback.callback(null);
            m.status("Age installed!");

            gui.UamGui.RefreshInfo(potsfolder);
            
            wasSuccessful = true;
            
            }finally{
            modal.hideInvisibleModal();
            }
        }
        else if(job==Jobs.downloadConfig)
        {
            String file = server+"/"+Uam.statusFilename;//"uam.status.txt";
            byte[] result = ThreadDownloader.downloadAsBytes(file);
            if(result==null)
            {
                //failed
                wasSuccessful = false;
                return;
            }
            
            if(potsfolder!=null)
            {
                if(new File(potsfolder+uam.Uam.ageArchivesFolder).getUsableSpace()>result.length+1000000)
                {
                    FileUtils.CreateFolder(potsfolder+uam.Uam.ageArchivesFolder);
                    FileUtils.WriteFile(potsfolder+uam.Uam.ageArchivesFolder+uam.Uam.statusFilename, result);
                }
                else
                {
                    m.err("There isn't enough free space to save the Age list to disk.");
                }
            }
            
            InvisibleModal modal = InvisibleModal.createAndShow();
            try{
            
        
            //callback
            callback.callback(result);//(ageList);

            wasSuccessful = true;
            
            }finally{
            modal.hideInvisibleModal();
            }
        }
        
    }
    
}
