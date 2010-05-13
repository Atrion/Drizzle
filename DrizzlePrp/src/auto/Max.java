/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auto;

import auto.conversion.FileInfo;
import auto.conversion.Info;
import java.io.File;
import shared.m;
import java.util.ArrayList;
import shared.FileUtils;
import java.util.Vector;
import prpobjects.Typeid;
import prpobjects.prpfile;

public class Max
{

    public static void convert3dsmaxToPots(String maxfolder, String potsfolder, String agenames)
    {
        File datfolder = new File(maxfolder+"/dat/");
        ArrayList<String> agenamelist = new ArrayList();
        boolean isempty = true;
        for(String item: agenames.split(","))
        {
            String agename = item.trim();
            if(!agename.equals(""))
            {
                isempty = false;
                boolean found = false;
                for(File f: datfolder.listFiles())
                {
                    if(f.isFile() && f.getName().toLowerCase().equals(agename.toLowerCase()+".age"))
                    {
                        String realagename = f.getName().replace(".age", "");
                        agenamelist.add(realagename);
                        found = true;
                        break;
                        //m.msg("Found Age: "+agename);
                    }
                }
                //agenamelist.add(agename);
                if(!found) m.warn("Unable to find Age: ",agename);
            }
        }

        if(isempty)
        {
            //m.msg("You must specify an Age to conver");
            m.msg("No Ages specified; converting all Ages in 3dsmax's export folder...");
            convert3dsmaxToPots(maxfolder,potsfolder);
            return;
        }
        else
        {
            convert3dsmaxToPots(maxfolder,potsfolder,agenamelist);
        }
    }
    public static void convert3dsmaxToPots(String maxfolder, String potsfolder, ArrayList<String> ages)
    {
        File datfolder = new File(maxfolder+"/dat/");

        if(ages.size()==0)
        {
            m.warn("None of the Ages were found in the 3dsmax export folder.");
            return;
        }

        //convert each found Age:
        for(String agename: ages)
        {
            m.status("Converting Age: ",agename);
            m.increaseindentation();

            //convert .age file:
            String agefilename = maxfolder + "/dat/"+agename+".age";
            byte[] agefilebs_enc = FileUtils.ReadFile(agefilename);
            uru.UruFileTypes type = uru.UruCrypt.DetectType(agefilebs_enc, auto.AllGames.getPots().g);
            if(type!=uru.UruFileTypes.unencrypted)
            {
                //bug with PlasmaPlugin
                m.warn(agefilename," should be plain text, *not* encrypted.  Create it with a regular text editor.");
            }
            byte[] agefilebs = uru.UruCrypt.DecryptAny(agefilebs_enc, type);
            //byte[] agefilebs = uru.UruCrypt.DecryptAny(agefilename, auto.AllGames.getPots().g);
            prpobjects.textfile agefile = prpobjects.textfile.createFromBytes(agefilebs);
            String prefixstr = agefile.getVariable("SequencePrefix");
            int prefix = Integer.parseInt(prefixstr); //bugfix for PlasmaPlugin
            byte[] agefileoutbs = agefile.saveToByteArray();
            agefileoutbs = uru.UruCrypt.EncryptWhatdoyousee(agefileoutbs);
            String agefileout = potsfolder+"/dat/"+agename+".age";
            FileUtils.WriteFile(agefileout, agefileoutbs, true, true);

            //convert .sum file:
            String sumfileout = potsfolder+"/dat/"+agename+".sum";
            FileUtils.WriteFile(sumfileout, prpobjects.sumfile.createEmptySumfile().getByteArray(),true,true);

            //convert .fni file:
            String fnifilename = maxfolder + "/dat/"+agename+".fni";
            File fnifile = new File(fnifilename);
            prpobjects.textfile fnifilet;
            if(fnifile.exists())
            {
                byte[] fnifilebs = uru.UruCrypt.DecryptAny(fnifilename, auto.AllGames.getPots().g);
                fnifilet = prpobjects.textfile.createFromBytes(fnifilebs);
            }
            else
            {
                //create a default
                fnifilet = new prpobjects.textfile();
                fnifilet.appendLine("Graphics.Renderer.SetYon 100000");
                //fnifilet.appendLine("Graphics.Renderer.Fog.SetDefLinear 1 1000 1");
                fnifilet.appendLine("Graphics.Renderer.Fog.SetDefLinear 0 0 0");
                fnifilet.appendLine("Graphics.Renderer.Fog.SetDefColor 0 0 0");
                fnifilet.appendLine("Graphics.Renderer.SetClearColor 0 0 0");
            }
            byte[] fnifileoutbs = uru.UruCrypt.EncryptWhatdoyousee(fnifilet.saveToByteArray());
            String fnifileout = potsfolder+"/dat/"+agename+".fni";
            FileUtils.WriteFile(fnifileout, fnifileoutbs, true, true);

            //convert .prp files:
            ArrayList<String> prpfiles = new ArrayList();
            for(File f: datfolder.listFiles())
            {
                if(f.isFile() && f.getName().startsWith(agename+"_District_") && f.getName().endsWith(".prp"))
                {
                    prpfiles.add(f.getName());
                }
            }

            final MaxInfo maxinfo = new MaxInfo();
            for(String prp: prpfiles)
            {
                m.status("Converting Prp: ",prp);
                m.increaseindentation();

                Vector<String> files = new Vector();
                files.add(prp);
                auto.AllGames.GameInfo moulinfo = auto.moul.getGameInfo();
                moulinfo.renameinfo.prefices.put(agename, prefix); //bugfix for PlasmaPlugin
                final auto.conversion.PostConversionModifier pcm = moulinfo.prpmodifier;
                moulinfo.prpmodifier = new auto.conversion.PostConversionModifier() {
                    public void ModifyPrp(Info info, FileInfo file, prpfile prp) {
                        //do the original Moul post conversion modifier:
                        pcm.ModifyPrp(info, file, prp);

                        //new stuff:
                        maxinfo.numSpawnPoints += prp.FindAllObjectsOfType(Typeid.plSpawnModifier).length;

                        maxinfo.numLights += prp.FindAllObjectsOfType(Typeid.plDirectionalLightInfo).length;
                        maxinfo.numLights += prp.FindAllObjectsOfType(Typeid.plOmniLightInfo).length;
                        maxinfo.numLights += prp.FindAllObjectsOfType(Typeid.plLimitedDirLightInfo).length;
                        maxinfo.numLights += prp.FindAllObjectsOfType(Typeid.plSpotLightInfo).length;

                        maxinfo.numPhysics += prp.FindAllObjectsOfType(Typeid.plSimulationInterface).length;
                    }
                };
                auto.AllGames.GameConversionSub maxconv = new auto.AllGames.GameConversionSub(moulinfo);
                maxconv.ConvertFiles(maxfolder, potsfolder, files);

                m.decreaseindentation();
            }

            if(maxinfo.numSpawnPoints==0)
            {
                m.warn("Your Age doesn't have a spawnpoint; you won't be able to link into it correctly.");
            }
            if(maxinfo.numLights==0)
            {
                m.warn("Your Age doesn't have any lights; everything may appear black.");
            }
            if(maxinfo.numPhysics==0)
            {
                m.warn("Your Age doesn't have any colliders; you will fall right through the ground.");
            }

            m.decreaseindentation();
            m.status("Done converting Age!: ",agename);
        }
    }
    private static boolean ensureFolders(String maxfolder, String potsfolder)
    {
        if(!auto.AllGames.getPots().isFolderX(potsfolder))
            return false;
        File datfolder = new File(maxfolder+"/dat/");
        if(!datfolder.exists() || !datfolder.isDirectory())
        {
            m.err("The 3dsmax folder must be set to the folder that the 3dsmax plugin exports to, and so should contain a 'dat' folder.");
            return false;
        }
        File test = new File(maxfolder+"/UruExplorer.exe");
        if(test.exists())
        {
            m.err("The 3dsmax export folder should not be Uru's folder.  Create a folder just for 3dsmax to export to.");
            return false;
        }
        return true;
    }
    public static void convert3dsmaxToPots(String maxfolder, String potsfolder)
    {
        //check folders:
        if(!ensureFolders(maxfolder,potsfolder)) return;
        File datfolder = new File(maxfolder+"/dat/");

        //find ages in export folder:
        ArrayList<String> ages = new ArrayList();
        for(File f: datfolder.listFiles())
        {
            if(f.isFile() && f.getName().endsWith(".age"))
            {
                String agename = f.getName().replace(".age", "");
                ages.add(agename);
                //m.msg("Found Age: "+agename);
            }
        }

        convert3dsmaxToPots(maxfolder,potsfolder,ages);

        
    }
    private static class MaxInfo
    {
        int numSpawnPoints = 0;
        int numLights = 0;
        int numPhysics = 0;
    }

    
}
