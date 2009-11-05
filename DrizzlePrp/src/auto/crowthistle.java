/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auto;

import shared.m;
import java.io.File;
import java.util.Vector;
import shared.FileUtils;
import uru.moulprp.*;
import java.util.HashMap;
import shared.*;
import uru.UruCrypt;
import uru.context;
import auto.conversion.FileInfo;
import auto.conversion.Info;

public class crowthistle
{
    public static AllGames.GameInfo getGameInfo()
    {
        AllGames.GameInfo r = new AllGames.GameInfo();
        r.GameName = "Crowthistle";
        r.DetectionFile = "CT.exe";
        r.renameinfo.prefices.put("MarshScene", 96);
        r.renameinfo.prefices.put("MountainScene", 95);
        r.agemodifier = new conversion.AgeModifier() {
            public void ModifyAge(Info info, FileInfo file, textfile tf) {
                final String[][] alcugsOptionals = {
                    {"MarshScene","Page=mrshFootRgns,93,1"},
                    {"MountainScene","Page=mntnFootRgns,55,1"},
                };
                for(String[] agepair: alcugsOptionals)
                {
                    if(file.agename.equals(agepair[0]))
                    {
                        tf.appendLine(agepair[1]);
                    }
                }
            }
        };
        r.addAgeFiles("MarshScene", new String[]{"MarshScene.age",});
        r.addAgeFiles("MountainScene", new String[]{"MountainScene.age",});
        r.MusicFiles = new String[]{
            "mntnAmbientMx.ogg",
            "mrshAmbientMx.ogg",
        };
        return r;
    }
    /*public static void CopyMusic(String crowfolder, String potsfolder)
    {
        m.status("Checking the folders you gave...");
        if(!detectinstallation.isFolderCrowthistle(crowfolder)) return;
        if(!detectinstallation.isFolderPots(potsfolder)) return;

        for(String filename: auto.fileLists.crowMusic)
        {
            String infile = crowfolder + "/sfx/" + filename;
            String outfile = potsfolder + "/MyMusic/" + filename;

            FileUtils.CopyFile(infile, outfile, true, true);
        }

        m.status("Done copying Crowthistle music!");
    }*/

    public static void convertCrowthistle(String crowfolder, String outfolder)
    {
        m.state.push();
        m.state.curstate.showConsoleMessages = true;
        m.state.curstate.showErrorMessages = true;
        m.state.curstate.showNormalMessages = false;
        m.state.curstate.showWarningMessages = false;
        m.state.curstate.showStatusMessages = true;

        
        
        //shared.State.AllStates.push();
        //shared.State.AllStates.revertToDefaults();
        //shared.State.AllStates.setState("removeDynamicCamMap", true);
        //shared.State.AllStates.setState("makePlLayersWireframe", false);
        //shared.State.AllStates.setState("changeVerySpecialPython", true);
        //shared.State.AllStates.setState("translateSmartseeks", false);
        //shared.State.AllStates.setState("removeLadders", true);
        //shared.State.AllStates.setState("automateMystV", true);
        //shared.State.AllStates.setState("includeAuthoredMaterial", shared.State.AllStates.getStateAsBoolean("includeAuthoredMaterial")); //this line doesn't really do anything, just there to remind you.
        //shared.State.AllStates.setState("includeAuthoredMaterial", false);
        
        //verify folders
        m.status("Checking the folders you gave...");
        if(!auto.AllGames.getCrowthistle().isFolderX(crowfolder))return;
        if(!auto.AllGames.getPots().isFolderX(outfolder)) return;
        
        m.status("Starting conversion...");
        //Vector<String> files = fileLists.crowthistleSimplicityList();
        convertCrowthistleToPots(crowfolder, outfolder);
        
        
        
        //shared.State.AllStates.pop();
        m.state.pop();
        m.status("Dont forget to run SoundDecompress.exe in your Pots folder, in order to get the sounds working!  (You can also click the SoundDecompress button on the form if you prefer.) (If SoundDecompress crashes, it means you have to log into Uru, quit, then try again.)");
        //m.status("Dont forget to run SoundDecompress.exe; the button is at UAM->SoundDecompress. (If SoundDecompress crashes, it means you have to log into Uru, quit, then try again.)");
        m.status("Conversion completed!");
        
    }

    public static void convertCrowthistleToPots(String crowthistlefolder, String outfolder)
    {
        class crowDecider implements uru.moulprp.prputils.Compiler.Decider
        {
            public boolean isObjectToBeIncluded(Uruobjectdesc desc)
            {
                Typeid type = desc.objecttype;
                String name = desc.objectname.toString();
                if(desc.objecttype==Typeid.plSceneNode) return true;
                if(desc.objecttype==Typeid.plCoordinateInterface) return true;
                if(desc.objecttype==Typeid.plSpawnModifier) return true;
                if(desc.objecttype==Typeid.plPythonFileMod) return true;
                if(desc.objecttype==Typeid.plSceneObject) return true;
                if(desc.objecttype==Typeid.plMipMap) return true;
                if(desc.objecttype==Typeid.plCubicEnvironMap) return true;
                //plLayer, plMaterial, plDrawInterface, plDrawableSpans
                if(type==Typeid.plLayer) return true;
                if(type==Typeid.hsGMaterial) return true;
                if(type==Typeid.plDrawableSpans) return true;
                Typeid[] typeequals = new Typeid[]{
                    Typeid.plViewFaceModifier,
                    Typeid.plLayerAnimation,
                    Typeid.plPythonFileMod,
                    Typeid.plBoundInterface,

                    Typeid.plHKPhysical, Typeid.plSimulationInterface,
                    Typeid.plDirectionalLightInfo, Typeid.plOmniLightInfo,
                    Typeid.plAGMasterMod, Typeid.plAGModifier, Typeid.plATCAnim,
                    Typeid.plParticleSystem, Typeid.plParticleLocalWind, Typeid.plParticleCollisionEffectDie,
                    Typeid.plMsgForwarder,
                    Typeid.plAudioInterface, Typeid.plRandomSoundMod, Typeid.plSoundBuffer, Typeid.plWinAudio, Typeid.plWin32StreamingSound, Typeid.plWin32StaticSound,
                    Typeid.plDrawInterface,
                    Typeid.plSoftVolumeSimple,
                    Typeid.plResponderModifier,
                    Typeid.plOccluder, Typeid.plShadowCaster, Typeid.plSoftVolumeInvert, Typeid.plSoftVolumeUnion,
                    Typeid.plStereizer,
                };
                String[] namestartswith = new String[]{
                    "a",
                    "b",
                    "cpyth","d","e","f",
                    "basket",
                    "bigrock",
                    //"bigtree",
                    "blueflower",
                    //"boat",
                    "boulder",
                    "bowl",
                    "box",
                    "bridge",
                    "bucket",
                };
                String[] nameequals = new String[]{
                    "bigtree01",
                    "bigtree02",
                    "bigtree03",
                    "bigtree04",
                    "bigtree05",
                    "bigtree06",
                    "bigtree07",
                    "bigtree08",
                    "Fern08",
                };


                for(int i=0;i<nameequals.length;i++)
                {
                    if(name.toLowerCase().equals(nameequals[i].toLowerCase())) return true;
                }
                for(int i=0;i<namestartswith.length;i++)
                {
                    if(name.toLowerCase().startsWith(namestartswith[i].toLowerCase())) return true;
                }
                for(int i=0;i<typeequals.length;i++)
                {
                    if(type==typeequals[i]) return true;
                }

                m.msg("Skipping type(2): ",type.toString());
                return false;
            }

        }

        //HashMap<String, Integer> prefices = new HashMap<String, Integer>();
        //prefices.put("MarshScene", 96);
        //prefices.put("MountainScene", 95);

        //HashMap<String, String> agenames = new HashMap<String, String>();

        String[] fnifiles = {
            "MarshScene.fni",
            "MountainScene.fni",
        };
        //String[] agefiles = {
        //    "MarshScene.age",
        //    "MountainScene.age",
        //};
        //String[] sumfiles = {
        //    "MarshScene.sum",
        //    "MountainScene.sum",
        //};
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

        Vector<String> files = new Vector();
        files.add("MarshScene.(others)");

        cmap<String,cmap<String,Integer>> authored = new cmap();
        authored.put("MarshScene","FootRgns",93);

        //create folders...
        FileUtils.CreateFolder(outfolder+"/dat/");

        //convert .fni files...
        for(String filename: fnifiles)
        {
            String infile = crowthistlefolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + filename;

            Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
            Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);
            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }

        //convert .age files...
        AllGames.getCrowthistle().ConvertGame(crowthistlefolder, outfolder);
        /*for(String filename: agefiles)
        {
            String infile = crowthistlefolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + filename;
            String agename = common.getAgenameFromFilename(filename);

            Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
            Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);

            //modify sequence prefix if Age is in list.
            Integer prefix = prefices.get(agename);
            if(prefix!=null)
            {
                textfile agefile = textfile.createFromBytes(decryptedData);
                agefile.setVariable("SequencePrefix", Integer.toString(prefix));
                decryptedData = agefile.saveToBytes();
            }*/

            //add any pages that are authored.
            /*if(shared.State.AllStates.getStateAsBoolean("includeAuthoredMaterial") && authored.get(agename) != null)
            {
                for(Pair<String,Integer> curauthprp: authored.get(agename).getAllElements())
                {
                    String pagename = curauthprp.left;
                    int pagenum = curauthprp.right;

                    textfile agefile = textfile.createFromBytes(decryptedData);
                    agefile.appendLine("Page="+pagename+","+Integer.toString(pagenum));
                    decryptedData = agefile.saveToBytes();
                }
            }*/

            /*Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }*/

        //Handle .ogg files...
        Vector<String> oggfiles = auto.fileLists.crowSfxList();
        for(String filename: oggfiles)
        {
            String infile = crowthistlefolder + "/sfx/" + filename;
            String outfile = outfolder + "/sfx/" + filename;

            FileUtils.CopyFile(infile, outfile, true, false);
        }

        //convert .prp files...
        for(String filename: prpfiles)
        {
            //Runtime.getRuntime().gc();

            String infile = crowthistlefolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + filename.replaceFirst("_", "_District_");
            String agename = common.getAgenameFromFilename(filename);

            //Bytes prpdata = Bytes.createFromFile(infile);
            //Bytestream bytestream = Bytestream.createFromBytes(prpdata);
            IBytestream bytestream = shared.SerialBytestream.createFromFilename(infile);
            context c = context.createFromBytestream(bytestream);
            c.curFile = filename; //helpful for debugging.

            //modify sequence prefix if Age is in list.
            Integer prefix = AllGames.getCrowthistle().g.renameinfo.prefices.get(agename);
            if(prefix!=null)
            {
                c.sequencePrefix = prefix;
            }

            //c.typesToRead = typesToRead;

            prpfile prp = prpfile.createFromContext(c, auto.mystAutomation.crowReadable);

            //processPrp(prp,agename,agenames,outfolder,crowthistlefolder);
            crowProcessPrp(prp,agename,AllGames.getCrowthistle().g.renameinfo.agenames,outfolder,crowthistlefolder);


            //Bytes prpoutputbytes = prp.saveAsBytes(new crowDecider());
            //prpoutputbytes.saveAsFile(outfile);
            prp.saveAsBytes(new crowDecider()).writeAllBytesToFile(outfile);

            c.close();
        }

        //Handle .(others) files...
        Vector<String> otherfiles = common.filterFilenamesByExtension(files, ".(others)");
        for(String filename: otherfiles)
        {
            String agename = common.getAgenameFromFilename(filename);

            /*if(shared.State.AllStates.getStateAsBoolean("includeAuthoredMaterial") && authored.get(agename) != null)
            {
                for(Pair<String,Integer> curauthprp: authored.get(agename).getAllElements())
                {
                    String pagename = curauthprp.left;
                    int pagenum = curauthprp.right;

                    String outfilename = common.replaceAgenameIfApplicable(agename, agenames)+"_District_"+pagename+".prp";
                    String outfile = outfolder + "/dat/" + outfilename;

                    Bytes bytes = shared.GetResource.getResourceAsBytes("/files/authored/"+outfilename);
                    bytes.saveAsFile(outfile);
                }
            }*/
        }

        //create .sum files...
        //Bytes sum1 = uru.moulprp.sumfile.createSumfile(outfolder+"/dat/", "MarshScene");
        Bytes sum1 = uru.moulprp.sumfile.createEmptySumfile();
        FileUtils.WriteFile(outfolder+"/dat/MarshScene.sum", sum1);
        //Bytes sum2 = uru.moulprp.sumfile.createSumfile(outfolder+"/dat/", "MountainScene");
        Bytes sum2 = uru.moulprp.sumfile.createEmptySumfile();
        FileUtils.WriteFile(outfolder+"/dat/MountainScene.sum", sum2);


        m.msg("Done Crowthistle work!");
    }
    public static void crowProcessPrp(prpfile prp, String agename, HashMap<String, String> agenames,String outfolder, String infolder)
    {
        auto.postmod.PostMod_RemoveDynamicCamMap.PostMod_RemoveDynamicCampMap(prp);

        String newagename = agenames.get(agename);
        if(newagename!=null)
        {
            auto.postmod.PostMod_RemoveDynamicCamMap.PostMod_ChangeVerySpecialPython(prp, agename, newagename);
        }

        //shouldn't be needed because Crowthistle has no ladders:
        auto.postmod.PostMod_RemoveDynamicCamMap.PostMod_RemoveLadders(prp);
        
    }
}
