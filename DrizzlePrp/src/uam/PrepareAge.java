/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uam;

import shared.*;
import java.util.ArrayList;
import java.io.File;

public class PrepareAge
{
    public static void DoAllWork(String archivefolder, String shardfolder, String tempfolder)
    {
        final boolean testing = false;

        //set the temp folder.
        if(tempfolder==null) tempfolder = archivefolder+"/temp/";

        //update the xml file for UAM.
        ArrayList<String> newarchives;
        if(!testing)
        {
            newarchives = uam.UamConfigNew.generateStatusFile(archivefolder);
        }
        else
        {
            m.msg("disabled xml update, change before release!");
            newarchives = new ArrayList();
            newarchives.add("H:/a/uam/ages/Vogokh_Oglahn--2010Aug20.7z");
        }

        if(!shardfolder.equals(""))
        {
            final String[] manualages = {"offlineki",};

            m.msg();
            m.msg("Preparing shard files...");
            for(String archive: newarchives)
            {
                //find if this is an Age we wish to do manually.
                File archf = new File(archive);
                boolean ignore = false;
                for(String manualage: manualages)
                {
                    if(archf.getName().startsWith(manualage+"--")) ignore = true;
                }
                if(!ignore)
                {
                    DoShardAgeWork(archive, shardfolder, tempfolder);
                }
            }
        }

    }
    public static void DoShardAgeWork(String archivestr, String shardfolder, String tempfolder)
    {
        File archive = new File(archivestr);
        String name = archive.getName();
        m.msg("Processing ", name);

        //extract the archive to the tempfolder
        String extractpath = tempfolder+"/"+name;
        FileUtils.CreateFolder(extractpath);
        boolean success = shared.sevenzip.extract(archivestr, extractpath);
        if(!success) m.throwUncaughtException("error extracting archive.");

        PrepareFileForShard(extractpath, "/", shardfolder);
    }
    private static void PrepareFileForShard(String basepath, String relfilename, String shardfolder)
    {
        File f = new File(basepath+relfilename);
        String name = f.getName();
        if(f.isDirectory())
        {
            for(File child: f.listFiles())
            {
                PrepareFileForShard(basepath, relfilename+"/"+child.getName(), shardfolder);
            }
        }
        else if(f.isFile())
        {
            //regular files
            if(name.endsWith(".prp")||name.endsWith(".age")||name.endsWith(".fni")||name.endsWith(".sum")||name.endsWith(".ogg"))
            {
                FileUtils.CopyFile(f.getAbsolutePath(), shardfolder+relfilename, true, true, true);
                return;
            }

            //misc files
            if(name.endsWith(".txt"))
            {
                FileUtils.CopyFile(f.getAbsolutePath(), shardfolder+relfilename, true, true, true);
                return;
            }

            //sdl
            if(name.endsWith(".sdl"))
            {
                FileUtils.CopyFile(f.getAbsolutePath(), shardfolder+relfilename, true, true, true);
                return;
            }

            //pak
            if(name.endsWith(".pak"))
            {
                //patch it
                //onliner.Python.PatchPak(byte[] pakdata);
                byte[] patched = onliner.Python.PatchPak(f.getAbsolutePath());
                FileUtils.WriteFile(shardfolder+relfilename, patched, true, true);

                //check for remaining problems
                onliner.Python.CheckForProblems(shardfolder+relfilename);

                return;
            }

            //otherwise it was unhandled
            m.err("Unhandled file: ", relfilename);
            return;
        }
    }
}
