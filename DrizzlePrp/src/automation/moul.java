/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import shared.*;
import java.io.File;
import java.util.Vector;
import java.util.HashMap;
import uru.moulprp.Typeid;
import uru.moulprp.Uruobjectdesc;
import uru.moulprp.Pageid;
import uru.moulprp.prpfile;
import uru.moulprp.*;

public class moul
{
    public static void convertMoul(String myst5folder, String potsfolder)
    {
        m.state.push();
        m.state.curstate.showConsoleMessages = true;
        m.state.curstate.showErrorMessages = true;
        m.state.curstate.showNormalMessages = false;
        m.state.curstate.showWarningMessages = false;
        m.state.curstate.showStatusMessages = true;

        
        
        shared.State.AllStates.push();
        //shared.State.AllStates.revertToDefaults();
        shared.State.AllStates.setState("removeDynamicCamMap", true);
        shared.State.AllStates.setState("makePlLayersWireframe", false);
        shared.State.AllStates.setState("changeVerySpecialPython", true);
        shared.State.AllStates.setState("translateSmartseeks", false);
        shared.State.AllStates.setState("removeLadders", true);
        shared.State.AllStates.setState("automateMystV", true);
        //shared.State.AllStates.setState("includeAuthoredMaterial", shared.State.AllStates.getStateAsBoolean("includeAuthoredMaterial")); //this line doesn't really do anything, just there to remind you.
        shared.State.AllStates.setState("includeAuthoredMaterial", false);
        
        //verify folders
        m.status("Checking the folders you gave...");
        File myst5file = new File(myst5folder);
        if(!myst5file.exists())
        {
            m.err("The MOUL folder you selected doesn't exist.");
            return;
        }
        if(!myst5file.isDirectory())
        {
            m.err("The MOUL folder you selected must be a folder, not a file.");
            return;
        }
        File myst5exe = new File(myst5folder+"/tos.txt");
        if(!myst5exe.exists())
        {
            m.err("The MOUL folder you selected doesn't seem to contain MOUL.  Please select the folder that contains tos.txt");
            return;
        }
        File potsfile = new File(potsfolder);
        if(!potsfile.exists())
        {
            m.err("The PathOfTheShell/CompleteChronicles folder you selected doesn't exist.");
            return;
        }
        if(!potsfile.isDirectory())
        {
            m.err("The PathOfTheShell/CompleteChronicles folder you selected must be a folder, not a file.");
            return;
        }
        File potsexe = new File(potsfile+"/UruExplorer.exe");
        if(!potsexe.exists())
        {
            m.err("The Pots folder you selected doesn't seem to contain Pots.  Please select the folder that contains UruExplorer.exe");
            return;
        }
        
        m.status("Starting conversion...");
        Vector<String> files = fileLists.moulSimplicityList();
        automation.mystAutomation.convertMoulToPots(myst5folder, potsfolder, files, true);
        
        
        
        shared.State.AllStates.pop();
        m.state.pop();
        m.status("Dont forget to run SoundDecompress.exe in your Pots folder, in order to get the sounds working!  (You can also click the SoundDecompress button on the form if you prefer.) (If SoundDecompress crashes, it means you have to log into Uru, quit, then try again.)");
        //m.status("Dont forget to run SoundDecompress.exe; the button is at UAM->SoundDecompress. (If SoundDecompress crashes, it means you have to log into Uru, quit, then try again.)");
        m.status("Conversion completed!");
    }
    
    //static HashMap<Uruobjectdesc, Uruobjectdesc> refReassigns;
    public static void proccessPrp(prpfile prp, String agename, HashMap<String, String> agenames, String outfolder, String infolder, HashMap<Uruobjectdesc, Uruobjectdesc> refReassigns)
    {
        String pagename = prp.header.pagename.toString();
        if(agename.equals("city") && (pagename.equals("bahroFlyers_arch") || pagename.equals("bahroFlyers_city1")
                || pagename.equals("islmBahroShoutFerry") || pagename.equals("islmBahroShoutLibrary") || pagename.equals("islmBahroShoutPalace")))
        {
            prpfile textprp = prpfile.createFromFile(infolder + "/dat/city_District_Textures.prp", true);
            prpdistiller.distiller.distillTextures(prp, textprp, new String[]{}, refReassigns);
        }
        if(agename.equals("Neighborhood") && pagename.equals("nb01BahroPedestalShout"))
        {
            prpfile textprp = prpfile.createFromFile(infolder + "/dat/Neighborhood_District_Textures.prp", true);
            //prpdistiller.distiller.distillTextures(prp, textprp, new String[]{}, refReassigns);
            
            prpfile prp1 = prpfile.createFromFile(infolder + "/dat/Neighborhood_District_nb01.prp", false);
            Vector<prpfile> prpfiles = new Vector();
            prpfiles.add(prp1);
            prpfiles.add(textprp);
            //HashMap<Uruobjectdesc, Uruobjectdesc> refReassigns = new HashMap();
            //prpdistiller.distiller.distillEverythingOneLayerDeep(prp, prpfiles, refReassigns);
            //prpdistiller.distiller.distillEverythingOneLayerDeep(prp, prpfiles, refReassigns);
            //prpdistiller.distiller.distillEverythingOneLayerDeep(prp, prpfiles, refReassigns);
            int levels = prpdistiller.distiller.distillEverything(prp, prpfiles, refReassigns);
            m.msg("Levels deep: "+Integer.toString(levels));
        }
        if(agename.equals("CustomAvatars") && pagename.equals("Blake"))//"Bahro1"))
        {
            //String newname = "Bahro1";
            //String newname = "kg";
            String newname = "Blake";
            PlAliasModifier alias = PlAliasModifier.createFromName(newname);
            Uruobjectref aliasref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plAliasModifier,"LODAvatar01",prp);
            PrpRootObject aliasroot = PrpRootObject.createFromDescAndObject(aliasref.xdesc, alias);
            prp.extraobjects.add(aliasroot);
            prp.mergeExtras();
        }
        if(agename.equals("Kveer") && pagename.equals("KveerHalls"))
        {
            //change pythonfilemod from Myst to MystMystV.
            x00A2Pythonfilemod pfm = prp.findObject("cPythLinkBookMyst", Typeid.plPythonFileMod).castTo();
            pfm.getListingByIndex(4).xString = Bstr.createFromString("MystMystV");
            
            //change respondermodifier from Myst to MystMystV.
            PlResponderModifier rm = prp.findObject("cRespLinkOutMyst", Typeid.plResponderModifier).castTo();
            PrpMessage.PlLinkToAgeMsg ltam = rm.messages[0].commands[1].message.castTo();
            ltam.ageLinkStruct.xageinfo.ageFilename = Wpstr.create("MystMystV");
            //ltam.ageLinkStruct.xageinfo.ageInstanceName = Wpstr.create("MystMystV");
            int dummy=0;
        }
        if(agename.equals("Kveer") && pagename.equals("KveerHalls"))
        {
            //GUI_District_BkBookImages:  Pots -2:55(type4) Moul -2:57(type4) Shard -2:55(type4
            x0006Layer layer = prp.findObject("Map #6995", Typeid.plLayer).castTo();
            //String texturefilename = infolder+"/dat/"+agename+"_District_Textures.prp";
            //String texturefilename = infolder+"/dat/GUI_District_BkBookImages.prp";
            //prpfile textureprp = prpfile.readHeaderAndIndexFromFile(texturefilename);
            //Uruobjectdesc mipmap = textureprp.findDescInIndex("xlinkpanelmystisland*1#0.hsm", Typeid.plMipMap);
            //Uruobjectref mmref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plMipMap, "xlinkpanelmystisland*1#0.hsm", textureprp);
            Uruobjectref mmref = Uruobjectref.createDefaultWithTypeNamePagePagetype(Typeid.plMipMap, "xlinkpanelmystisland*1#0.hsm", Pageid.createFromPrefixSuffix(-2, 55), Pagetype.createWithType(4));
            //layer.texture = mipmap.toRef();
            layer.texture = mmref;
        }
        if(agename.equals("EderDelin") && pagename.equals("garden"))
        {
            x00A2Pythonfilemod pfm = prp.findObject("cPythYeeshaPage15_0", Typeid.plPythonFileMod).castTo();
            pfm.getListingByIndex(2).xInteger = 18;
            pfm = prp.findObject("cPythYeeshaPage15_1", Typeid.plPythonFileMod).castTo();
            pfm.getListingByIndex(2).xInteger = 18;
            pfm = prp.findObject("cPythYeeshaPage15_2", Typeid.plPythonFileMod).castTo();
            pfm.getListingByIndex(2).xInteger = 18;
        }
        if(agename.equals("Ahnonay") && pagename.equals("EngineerHut"))
        {
            //just disable these objects, so user's can't click them.
            prp.removeObject(Typeid.plHKPhysical, "SaveClothClkRegion");
            prp.removeObject(Typeid.plHKPhysical, "BahroRockBook");
            
            //set new python files:
            uru.moulprp.x00A2Pythonfilemod pfm1 = prp.findObject("cPythDoorConsole", Typeid.plPythonFileMod).castTo();
            pfm1.pyfile = Urustring.createFromString("ahnyKadishDoorMOUL");
            uru.moulprp.x00A2Pythonfilemod pfm2 = prp.findObject("cPythHutInside", Typeid.plPythonFileMod).castTo();
            pfm2.pyfile = Urustring.createFromString("ahnyKadishHutMOUL");
            uru.moulprp.x00A2Pythonfilemod pfm3 = prp.findObject("cSaveCloth7", Typeid.plPythonFileMod).castTo();
            pfm3.pyfile = Urustring.createFromString("ahnySaveClothMOUL");

            //set initialiseOnFirstUpdate to false, so that it does ServerInitComplete.
            uru.moulprp.x00A2Pythonfilemod pfm4 = prp.findObject("cPythPOTSsymbol", Typeid.plPythonFileMod).castTo();
            pfm4.getListingByIndex(10).xBoolean = 0;
        }
        if(agename.equals("Ahnonay") && pagename.equals("Vortex"))
        {
            uru.moulprp.x00A2Pythonfilemod pfm = prp.findObject("PythVogondolaRide", Typeid.plPythonFileMod).castTo();
            pfm.pyfile = Urustring.createFromString("ahnyVogondolaRideV2MOUL.py");
            
            //set initialiseOnFirstUpdate to false, so that it does ServerInitComplete.
            uru.moulprp.x00A2Pythonfilemod pfm2 = prp.findObject("cPythRigAnims", Typeid.plPythonFileMod).castTo();
            pfm2.getListingByIndex(7).xBoolean = 0;
        }
        if(agename.equals("GreatTreePub") && pagename.equals("Pub"))
        {
            //change pythonfilemod from AhnonayCathedral to AhnonayMOUL.
            x00A2Pythonfilemod pfm = prp.findObject("cPythLinkBookAhnonay", Typeid.plPythonFileMod).castTo();
            pfm.getListingByIndex(4).xString = Bstr.createFromString("AhnonayMOUL");
            
            //change respondermodifier from AhnonayCathedral to AhnonayMOUL.
            PlResponderModifier rm = prp.findObject("cRespLinkOutAhnonay", Typeid.plResponderModifier).castTo();
            PrpMessage.PlLinkToAgeMsg ltam = rm.messages[0].commands[1].message.castTo();
            ltam.ageLinkStruct.xageinfo.ageFilename = Wpstr.create("AhnonayMOUL");

            //change Ahnonay image.
            x0006Layer layer = prp.findObject("Map #69950", Typeid.plLayer).castTo();
            Uruobjectref mmref = Uruobjectref.createDefaultWithTypeNamePagePagetype(Typeid.plMipMap, "xlinkpanelahnonayvortex*1#0.hsm", Pageid.createFromPrefixSuffix(-2, 55), Pagetype.createWithType(4));
            layer.texture = mmref;
        
        }
    }
    
    /*public static void convertMoulToPots(String infolder, String outfolder, Vector<String> files)
    {
        class compileDecider implements uru.moulprp.prputils.Compiler.Decider
        {
            public boolean isObjectToBeIncluded(Uruobjectdesc desc)
            {
                Typeid type = desc.objecttype;
                int number = desc.objectnumber;
                String name = desc.objectname.toString();
                Pageid pageid = desc.pageid;

                //blacklist
                if(type==type.plSceneNode) return false; //do not allow Scene node in here, it must be treated separately.
                //if(pageid.getRawData()==0x220024 && type==type.plResponderModifier && name.equals("RespWedges")) return false; //livebahrocaves pod district problem. (crashes when linking.)
                //if(pageid.getRawData()==0x2A0025 && type==type.plResponderModifier && name.equals("cRespExcludeRgn")) return false; //minkata cameras district problem. (crashes when going to night).
                if(pageid.prefix==0x22 && pageid.suffix==0x24 && type==type.plResponderModifier && name.equals("RespWedges")) return false; //livebahrocaves pod district problem. (crashes when linking.)
                if(pageid.prefix==0x2A && pageid.suffix==0x25 && type==type.plResponderModifier && name.equals("cRespExcludeRgn")) return false; //minkata cameras district problem. (crashes when going to night).

                return true;
            }
        }

        HashMap<String, Integer> prefices = new HashMap<String, Integer>();
        prefices.put("Payiferen", 99);
        prefices.put("Kveer", 98);
        prefices.put("EderTsogal", 97);
        prefices.put("Neighborhood02",86);
        prefices.put("Personal",85);
        prefices.put("GreatTreePub",84);
        
        HashMap<String, String> agenames = new HashMap<String, String>();
        agenames.put("Kveer", "KveerMOUL");
        agenames.put("Neighborhood02", "KirelMOUL");
        agenames.put("Personal", "PersonalMOUL");
        
        //these map from filename to oldpagenum to newpagenum
        //HashMap<String, HashMap<Integer,Integer>> suffices = new HashMap();
        //suffices.put("GUI_District_YeeshaPageGUI.prp", new Pair(50,86));
        //suffices.put("GUI_District_jalakControlPanel.prp", new Pair(68,90));
        cmap<String,cmap<Integer,Integer>> pagenums = new cmap();
        //suffices.put( "GUI", 50, 86); //YeeshaPageGUI
        //suffices.put( "GUI", 68, 90); //jalakControlPanel
        pagenums.put( "GUI", 49, 85); //YeeshaPageGUI
        pagenums.put( "GUI", 67, 89); //jalakControlPanel
        //add 1 to all these global ones.
        //There is an extra male animation: MaleTreadWater than has no female counterpart.  Just a mistake?
        pagenums.put( "GlobalAnimations", 291, 300); //FemaleAmazed
        pagenums.put( "GlobalAnimations", 292, 301); //
        pagenums.put( "GlobalAnimations", 326, 302); //FemaleAskQuestion
        pagenums.put( "GlobalAnimations", 325, 303); //
        pagenums.put( "GlobalAnimations",  77, 304); //FemaleBeckonBig
        pagenums.put( "GlobalAnimations", 144, 305); //
        pagenums.put( "GlobalAnimations", 145, 306); //FemaleBeckonSmall
        pagenums.put( "GlobalAnimations", 146, 307); //
        pagenums.put( "GlobalAnimations", 197, 308); //FemaleBookAccept
        pagenums.put( "GlobalAnimations", 202, 309); //
        pagenums.put( "GlobalAnimations", 198, 310); //FemaleBookAcceptIdle
        pagenums.put( "GlobalAnimations", 203, 311); //
        pagenums.put( "GlobalAnimations", 194, 312); //FemaleBookOffer
        pagenums.put( "GlobalAnimations", 199, 313); //
        pagenums.put( "GlobalAnimations", 196, 314); //FemaleBookOfferFinish
        pagenums.put( "GlobalAnimations", 201, 315); //
        pagenums.put( "GlobalAnimations", 195, 316); //FemaleBookOfferIdle
        pagenums.put( "GlobalAnimations", 200, 317); //
        pagenums.put( "GlobalAnimations", 293, 318); //FemaleBow
        pagenums.put( "GlobalAnimations", 294, 319); //
        pagenums.put( "GlobalAnimations", 258, 320); //FemaleCallMe
        pagenums.put( "GlobalAnimations", 259, 321); //
        pagenums.put( "GlobalAnimations", 260, 322); //FemaleCower
        pagenums.put( "GlobalAnimations", 261, 323); //
        pagenums.put( "GlobalAnimations", 295, 324); //FemaleCrazy
        pagenums.put( "GlobalAnimations", 309, 325); //
        pagenums.put( "GlobalAnimations", 280, 326); //FemaleCringe
        pagenums.put( "GlobalAnimations", 285, 327); //
        pagenums.put( "GlobalAnimations", 296, 328); //FemaleCrossArms
        pagenums.put( "GlobalAnimations", 310, 329); //
        pagenums.put( "GlobalAnimations", 297, 330); //FemaleDoh
        pagenums.put( "GlobalAnimations", 311, 331); //
        pagenums.put( "GlobalAnimations", 298, 332); //FemaleFlinch
        pagenums.put( "GlobalAnimations", 312, 333); //
        pagenums.put( "GlobalAnimations", 141, 334); //FemaleGlobalScopeGrab, like FemaleScopeGrab in pots
        pagenums.put( "GlobalAnimations",  70, 335); //
        pagenums.put( "GlobalAnimations", 142, 336); //FemaleGlobalScopeHold, like FemaleScopeHold in pots
        pagenums.put( "GlobalAnimations",  71, 337); //
        pagenums.put( "GlobalAnimations", 143, 338); //FemaleGlobalScopeRelease, like FemaleScopeRelease in pots
        pagenums.put( "GlobalAnimations",  72, 339); //
        pagenums.put( "GlobalAnimations", 262, 340); //FemaleGroan
        pagenums.put( "GlobalAnimations", 263, 341); //
        pagenums.put( "GlobalAnimations", 344, 342); //FemaleKITap
        pagenums.put( "GlobalAnimations", 343, 343); //
        pagenums.put( "GlobalAnimations", 282, 344); //FemaleKneel
        pagenums.put( "GlobalAnimations", 313, 345); //
        pagenums.put( "GlobalAnimations",  43, 346); //FemaleLeanLeft
        pagenums.put( "GlobalAnimations",  16, 347); //
        pagenums.put( "GlobalAnimations",  44, 348); //FemaleLeanRight
        pagenums.put( "GlobalAnimations",  17, 349); //
        pagenums.put( "GlobalAnimations", 299, 350); //FemaleLookAround
        pagenums.put( "GlobalAnimations", 314, 351); //
        pagenums.put( "GlobalAnimations", 264, 352); //FemaleOkay
        pagenums.put( "GlobalAnimations", 265, 353); //
        pagenums.put( "GlobalAnimations", 266, 354); //FemaleOverHere
        pagenums.put( "GlobalAnimations", 267, 355); //
        pagenums.put( "GlobalAnimations", 300, 356); //FemalePeer
        pagenums.put( "GlobalAnimations", 315, 357); //
        pagenums.put( "GlobalAnimations", 301, 358); //FemaleSalute
        pagenums.put( "GlobalAnimations", 316, 359); //
        pagenums.put( "GlobalAnimations", 302, 360); //FemaleScratchHead
        pagenums.put( "GlobalAnimations", 317, 361); //
        pagenums.put( "GlobalAnimations", 303, 362); //FemaleShakeFist
        pagenums.put( "GlobalAnimations", 318, 363); //
        pagenums.put( "GlobalAnimations", 304, 364); //FemaleShoo
        pagenums.put( "GlobalAnimations", 319, 365); //
        pagenums.put( "GlobalAnimations", 305, 366); //FemaleSlouchSad
        pagenums.put( "GlobalAnimations", 320, 367); //
        pagenums.put( "GlobalAnimations", 268, 368); //FemaleStop
        pagenums.put( "GlobalAnimations", 269, 369); //
        pagenums.put( "GlobalAnimations", 270, 370); //FemaleTalkHand
        pagenums.put( "GlobalAnimations", 321, 371); //
        pagenums.put( "GlobalAnimations", 272, 372); //FemaleTapFoot
        pagenums.put( "GlobalAnimations", 273, 373); //
        pagenums.put( "GlobalAnimations", 306, 374); //FemaleTaunt
        pagenums.put( "GlobalAnimations", 322, 375); //
        pagenums.put( "GlobalAnimations", 275, 376); //FemaleThumbsDown
        pagenums.put( "GlobalAnimations", 277, 377); //
        pagenums.put( "GlobalAnimations", 283, 378); //FemaleThumbsDown2
        pagenums.put( "GlobalAnimations", 286, 379); //
        pagenums.put( "GlobalAnimations", 274, 380); //FemaleThumbsUp
        pagenums.put( "GlobalAnimations", 276, 381); //
        pagenums.put( "GlobalAnimations", 284, 382); //FemaleThumbsUp2
        pagenums.put( "GlobalAnimations", 287, 383); //
        pagenums.put( "GlobalAnimations", 278, 384); //FemaleWallSlide
        pagenums.put( "GlobalAnimations", 279, 385); //
        pagenums.put( "GlobalAnimations", 307, 386); //FemaleWaveLow
        pagenums.put( "GlobalAnimations", 323, 387); //
        pagenums.put( "GlobalAnimations", 308, 388); //FemaleWinded
        pagenums.put( "GlobalAnimations", 324, 389); //
        //include kg*
        
        Typeid[] readable = mystAutomation.moulReadable;
        
        //create folders...
        FileUtils.CreateFolder(outfolder+"/dat/");
        
        //Handle .fni files...
        Vector<String> fnifiles = common.filterFilenamesByExtension(files, ".fni");
        for(String filename: fnifiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + common.replaceAgenameIfApplicable(filename, agenames);
            
            byte[] encryptedData = FileUtils.ReadFile(infile);
            byte[] decryptedData = uru.UruCrypt.DecryptWhatdoyousee(encryptedData);// UruCrypt.DecryptEoa(encryptedData);
            byte[] wdysData = uru.UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        
        //Handle .age files...
        Vector<String> agefiles = filterFilenamesByExtension(files, ".age");
        for(String filename: agefiles)
        {
            String agename = getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + replaceAgenameIfApplicable(filename, agenames);
            
            if(agename.toLowerCase().equals("personal")) m.warn("Relto may corrupt your savegame, be sure to back up your /sav folder!");
            
            byte[] encryptedData = FileUtils.ReadFile(infile);
            byte[] decryptedData = UruCrypt.DecryptWhatdoyousee(encryptedData); //UruCrypt.DecryptEoa(encryptedData);
            
            //modify sequence prefix if Age is in list.
            Integer prefix = prefices.get(agename);
            if(prefix!=null)
            {
                textfile agefile = textfile.createFromBytes(decryptedData);
                agefile.setVariable("SequencePrefix", Integer.toString(prefix));
                decryptedData = agefile.saveToByteArray();
            }
            
            //modify Minkata's Age file.
            if(agename.toLowerCase().equals("minkata"))
            {
                textfile agefile = textfile.createFromBytes(decryptedData);
                agefile.appendLine("Page=minkDusttestDay,11");
                agefile.appendLine("Page=minkDusttestNight,12");
                agefile.appendLine("Page=minkDusttest,10");
                decryptedData = agefile.saveToByteArray();
            }
            
            byte[] wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }
        
        
        //Handle .prp files...
        Vector<String> prpfiles = filterFilenamesByExtension(files, ".prp");
        for(String filename: prpfiles)
        {
            String agename = getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + replaceAgenameIfApplicable(filename, agenames);//.replace("_", "_District_");
            
            Bytes prpdata = Bytes.createFromFile(infile);
            Bytestream bytestream = Bytestream.createFromBytes(prpdata);
            context c = context.createFromBytestream(bytestream);
            c.curFile = filename; //helpful for debugging.
            
            //modify sequence prefix if Age is in list.
            Integer prefix = prefices.get(agename);
            if(prefix!=null)
            {
                c.sequencePrefix = prefix;
            }
            
            //modify sequence suffix if Age is in list.
            cmap<Integer,Integer> suffix = pagenums.get(agename);
            if(suffix!=null)
            {
                c.pagenumMap = suffix;
            }
            
            //modify agename if Age is in list.
            String newAgename = agenames.get(agename);
            if(newAgename!=null)
            {
                c.ageName = newAgename;
            }

            prpfile prp = prpfile.createFromContext(c, readable);
            
            processPrp(prp,agename,agenames,outfolder,infolder);
            
            Bytes prpoutputbytes = prp.saveAsBytes(new compileDecider());
            prpoutputbytes.saveAsFile(outfile);
        }
        
        
        //Handle .sum files...
        Vector<String> sumfiles = filterFilenamesByExtension(files, ".sum");
        for(String filename: sumfiles)
        {
            String agename = getAgenameFromFilename(filename);
            Bytes sum1 = uru.moulprp.sumfile.createSumfile(outfolder+"/dat/", replaceAgenameIfApplicable(agename, agenames));
            FileUtils.WriteFile(outfolder+"/dat/"+replaceAgenameIfApplicable(filename, agenames), sum1);
        }
        
        
        //All done!
        m.msg("Done Moul work!");
    }
    */
}
