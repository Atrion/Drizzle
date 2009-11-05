/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auto;

import shared.Flt;
import uru.moulprp.*;
import shared.m;
import uru.moulprp.x00A2Pythonfilemod.Pythonlisting;
import java.io.File;
import java.util.Vector;
import shared.*;
import java.util.HashMap;
import uru.UruCrypt;
import uru.context;
import auto.conversion.FileInfo;
import auto.conversion.Info;
import uru.moulprp.textfile;


public class mystv //was myst5Fixes
{
    public static AllGames.GameInfo getGameInfo()
    {
        AllGames.GameInfo r = new AllGames.GameInfo();
        r.GameName = "MystV";
        r.DetectionFile = "MystV.exe";
        r.agemodifier = new conversion.AgeModifier() {
            public void ModifyAge(Info info, FileInfo file, textfile tf) {
                final String[][] alcugsOptionals = {
                    {"DescentMystV","Page=dsntFootRgns,97,1"},
                    {"Direbo","Page=drboAdditions,98,1"},
                    {"KveerMystV","Page=kverFootRgns,97,1"},
                    {"Laki","Page=lakiFootRgns,99,1"},
                    {"MystMystV","Page=mystFootRgns,89,1"},
                    {"Siralehn","Page=srlnFootRgns,96,1"},
                    {"Tahgira","Page=thgrFootRgns,97,1"},
                    {"Todelmer","Page=tdlmFootRgns,92,1"},
                };
                for(String[] agepair: alcugsOptionals)
                {
                    if(file.agename.equals(agepair[0]))
                    {
                        tf.appendLine(agepair[1]);
                    }
                }

                //Remove all the KveerMystV pages except kverReleeshan and dusttest from the .age file.
                boolean makeMinimalReleeshan = true;
                if(makeMinimalReleeshan)
                {
                    if(file.agename.equals("Kveer"))
                    {
                        //textfile agefile = textfile.createFromBytes(decryptedData);
                        for(textfile.textline line: tf.getLines())
                        {
                            String l = line.getString();
                            if(l.startsWith("Page="))
                            {
                                line.setString("#"+l); //comment it out.
                            }
                        }
                        tf.appendLine("Page=kverReleeshan,22"); //remove the ,1 from the end so that it loads.
                        //agefile.appendLine("Page=dusttest,90"); //leave it alone.
                        //decryptedData = agefile.saveToBytes();
                    }
                }

                //add dusttest page, dynamically loaded.
                if(file.agename.equals("Descent") || file.agename.equals("Todelmer") || file.agename.equals("Tahgira") || file.agename.equals("Siralehn") || file.agename.equals("Laki") || file.agename.equals("Kveer"))
                {
                    //textfile agefile = textfile.createFromBytes(decryptedData);
                    tf.appendLine("Page=dusttest,90");
                    //decryptedData = agefile.saveToBytes();
                }

            }
        };
        r.renameinfo.prefices.put("Descent", 94);
        r.renameinfo.prefices.put("Direbo", 93);
        r.renameinfo.prefices.put("Kveer", 92);
        r.renameinfo.prefices.put("Laki", 91);
        r.renameinfo.prefices.put("Myst", 90);
        r.renameinfo.prefices.put("Siralehn", 89);
        r.renameinfo.prefices.put("Tahgira", 88);
        r.renameinfo.prefices.put("Todelmer", 87);
        r.renameinfo.agenames.put("Descent", "DescentMystV");
        r.renameinfo.agenames.put("Kveer", "KveerMystV");
        r.renameinfo.agenames.put("Myst", "MystMystV");
        r.addAgeFiles("Descent", new String[]{"Descent.age",});
        r.addAgeFiles("Direbo", new String[]{"Direbo.age",});
        r.addAgeFiles("Kveer", new String[]{"Kveer.age",});
        r.addAgeFiles("Laki", new String[]{"Laki.age",});
        r.addAgeFiles("Myst", new String[]{"Myst.age",});
        r.addAgeFiles("Siralehn", new String[]{"Siralehn.age",});
        r.addAgeFiles("Tahgira", new String[]{"Tahgira.age",});
        r.addAgeFiles("Todelmer", new String[]{"Todelmer.age",});
        r.MusicFiles = new String[]{
            //GameIntro.bik has music.
            "dsntElevatorMusic.ogg",
            "dsntRestAreaMusic_Loop.ogg",
            "KverRandMusic01.ogg", "KverRandMusic02.ogg", "KverRandMusic03.ogg", "kverRandMusic04.ogg", "kverRandMusic05.ogg",
            "lakiArena-RevealMusic.ogg",
            "lakiRandMusic01.ogg", "lakiRandMusic02.ogg", "lakiRandMusic03.ogg", "lakiRandMusic04.ogg", "lakiRandMusic05.ogg", "lakiRandMusic06.ogg", "lakiRandMusic07.ogg", "lakiRandMusic08.ogg", "lakiRandMusic09.ogg",
            "lakiWindmill-PuzzleMusic_Loop.ogg",
            "mystAmbMusic.ogg",
            "srlnMainMusic_Loop.ogg",
            "srlnRandMusic01.ogg", "srlnRandMusic02.ogg", "srlnRandMusic03.ogg", "srlnRandMusic04.ogg", "srlnRandMusic05.ogg", "srlnRandMusic06.ogg", "srlnRandMusic07.ogg", "srlnRandMusic08.ogg", "srlnRandMusic09.ogg", "srlnRandMusic10.ogg", "srlnRandMusic11.ogg", "srlnRandMusic12.ogg", "srlnRandMusic13.ogg", "srlnRandMusic14.ogg", "srlnRandMusic15.ogg",
            "tdlmAmbMusic01_loop.ogg",
            "xBubbleMusic.ogg",
            "dsntEsher-Intro_Mx.ogg",
            "dsntYeesha-Imager01Mx.ogg",
            "dsntYeesha-Imager02Mx.ogg",
            "dsntYeesha-Imager03Mx.ogg",
            "kverConc03MxPart01.ogg",
            "kverConc03MxPart02.ogg",
            "kverYeesha-IntroMx.ogg",
            "kverYeeshaConc02Mx.ogg",
            "lakiEsher-Arena_Mx.ogg",
            "lakiEsher-Keep_Mx.ogg",
            "lakiEsher-Villa_Mx.ogg",
            "mystEsher-Conc01Mx.ogg",
            "mystEsher-Conc02Mx.ogg",
            "tdlmEsher-TodelmerP1_Mx.ogg",
            "thgrIceFildsMx_loop.ogg",
        };
        return r;
    }

    /*public static void CopyMusic(String myst5folder, String potsfolder)
    {
        m.status("Checking the folders you gave...");
        if(!detectinstallation.isFolderMyst5(myst5folder)) return;
        if(!detectinstallation.isFolderPots(potsfolder)) return;

        for(String filename: auto.fileLists.myst5Music)
        {
            String infile = myst5folder + "/sfx/" + filename;
            String outfile = potsfolder + "/MyMusic/" + filename;

            FileUtils.CopyFile(infile, outfile, true, true);
        }

        m.status("Done copying Myst5 music!");

    }*/
    public static void convertABunchOfMyst5Stuff(String myst5folder, String potsfolder)
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
        if(!auto.AllGames.getMystV().isFolderX(myst5folder)) return;
        if(!auto.AllGames.getPots().isFolderX(potsfolder)) return;
        
        m.status("Starting conversion...");
        Vector<String> files = uru.generics.convertArrayToVector(auto.fileLists.mystvFiles);
        Vector<String> oggfiles = uru.generics.convertArrayToVector(auto.fileLists.mystvOggsNotInPotsNorMoulofflineMinusSpeeches);
        files.addAll(oggfiles);

        try{
            convertMyst5ToPots(myst5folder, potsfolder, files, true);
        }catch(shared.cancelexception e){
            m.warn("Conversion cancelled.");
        }
        
        
        //shared.State.AllStates.pop();
        m.state.pop();
        m.status("Dont forget to run SoundDecompress.exe in your Pots folder, in order to get the sounds working!  (You can also click the SoundDecompress button on the form if you prefer.) (If SoundDecompress crashes, it means you have to log into Uru, quit, then try again.)");
        //m.status("Dont forget to run SoundDecompress.exe; the button is at UAM->SoundDecompress. (If SoundDecompress crashes, it means you have to log into Uru, quit, then try again.)");
        m.status("Conversion completed!");
    }
    
    public static void fixBinks(String finalname, prpfile prp, String infolder)
    {
        String agename = finalname.toLowerCase();
        String pagename = prp.header.pagename.toString().toLowerCase();
        
        for(PrpRootObject obj: prp.FindAllObjectsOfType(Typeid.plLayerBink))
        {
            PlLayerBink binkobj = obj.castTo();
            if((binkobj.parent.parent.tc.flags&0x2)!=0) //if loop
            {
                //Set loop length based on bink video length.
                //These changes require you to delte the Age's sav file.
                String pathtobinkfile = infolder+"/"+binkobj.parent.filename.toString();
                IBytestream binkc = SerialBytestream.createFromFilename(pathtobinkfile);
                bink.binkfile binkfile = new bink.binkfile(binkc);
                float length = binkfile.getLengthInSeconds();
                length = length*59.0f/60.0f; //Cyan's timing seems to be off by this much.
                m.msg("Modifying bink: ",pathtobinkfile," length=",Float.toString(length));
                binkobj.parent.parent.tc.flags &= ~0x1; //turn off the "stopped" flag.
                //binkobj.parent.parent.tc.flags |= 0x20; //turn on the easingIn flag.
                //binkobj.parent.parent.tc.flags = 0x22; //can this be removed?
                //binkobj.parent.parent.tc.flags |= 0x88;
                //binkobj.parent.parent.tc.loopEnd = Flt.createFromJavaFloat(length-1.0f);
                binkobj.parent.parent.tc.loopEnd = Flt.createFromJavaFloat(length);
                //binkobj.parent.parent.tc.end = Flt.createFromJavaFloat(length);
            }
            /*String filename = bink.parent.filename.toString().toLowerCase();
            if(
                filename.equals("direbo.bik")||
                filename.equals("reststop1.bik")||
                filename.equals("reststop2.bik")||
                filename.equals("reststop3.bik")||
                filename.equals("reststop4.bik")
                )
            {
                bink.parent.parent.tc.loopEnd = Flt.createFromJavaFloat(30.0f);
            }*/
        }
    }

    public static void fixClickables(String finalname, prpfile prp)
    {
        //restore limited clickables
        //String[] clickables = {};
        String agename = finalname.toLowerCase();
        String pagename = prp.header.pagename.toString().toLowerCase();
        
        if(agename.equals("direbo") && pagename.equals("restage"))
        {
            //clickables = new String[]{
            restoreClickability(prp, "PedButton02ClickProxyLaki");
            restoreClickability(prp, "PedButton03ClickProxyLaki");
            restoreClickability(prp, "PedButton04ClickProxyLaki");
            restoreClickability(prp, "PedButton05ClickProxyLaki");
            restoreClickability(prp, "PedButton02ClickProxyTdlm");
            restoreClickability(prp, "PedButton03ClickProxyTdlm");
            //restoreClickability(prp, "PedButton04ClickProxyTdlm");
            restoreClickability(prp, "PedButton05ClickProxyTdlm");
            restoreClickability(prp, "PedButton02ClickProxyThgr");
            restoreClickability(prp, "PedButton03ClickProxyThgr");
            restoreClickability(prp, "PedButton04ClickProxyThgr");
            restoreClickability(prp, "PedButton05ClickProxyThgr");
            restoreClickability(prp, "PedButton02ClickProxySrln");
            restoreClickability(prp, "PedButton03ClickProxySrln");
            //restoreClickability(prp, "PedButton04ClickProxySrln");
            restoreClickability(prp, "PedButton05ClickProxySrln");
            //};
        }
        if(agename.equals("laki") && pagename.equals("exterior"))
        {
            //clickables = new String[]{
                //restoreClickability(prp, "PedButton01ClickProxy"); //don't link to self.
            restoreClickability(prp, "PedButton02ClickProxy");
            restoreClickability(prp, "PedButton03ClickProxy");
            restoreClickability(prp, "PedButton04ClickProxy");
            restoreClickability(prp, "PedButton05ClickProxy");
            //};
            makeClickableUsePythonfilemod(prp, "ClickPed1DireboLinkProxy", "fakelink", "Laki", "LinkInTake");
            makeClickableUsePythonfilemod(prp, "ClickPed3DireboLinkProxy", "fakelink", "Laki", "LinkInTake");
            //makeClickableUsePythonfilemod(prp, "Ped1ClickableProxy", "linktoage", "Todelmer", "LinkInPointDefault"); //this is the tablet button.
            //PedestalClickableProxy is the tablet symbol on the take
            makeClickableUsePythonfilemod(prp, "TakeOrDireboLinkProxy", "linktoage", "Direbo", "LinkInPoint2"); //link to direbo
        }
        if(agename.equals("laki") && pagename.equals("lakiarenavillaint"))
        {
            makeClickableUsePythonfilemod(prp, "ClickPed2DireboLinkProxy", "fakelink", "Laki", "LinkInTake");
        }
        if(agename.equals("siralehn") && pagename.equals("exterior"))
        {
            //clickables = new String[]{
            //restoreClickability(prp, "PedButton01ClickProxy", //don't link to self.
            restoreClickability(prp, "PedButton02ClickProxy");
            restoreClickability(prp, "PedButton03ClickProxy");
            //restoreClickability(prp, "PedButton04ClickProxy"); //links to the area with Esher on the beach.
            restoreClickability(prp, "PedButton05ClickProxy");
            //};
            makeClickableUsePythonfilemod(prp, "DireboLinkProxy", "linktoage", "Direbo", "LinkInPoint1");
            makeClickableUsePythonfilemod(prp, "TakeLinkProxy", "fakelink", "Siralehn", "LinkInTake");
            prp.removeObject(Typeid.plSceneObject,"LandTopCollision"); //so we can jump off the top
            prp.removeObject(Typeid.plSceneObject,"XrgnKeepDoor"); //so we can enter the keep
        }
        /*if(agename.equals("siralehn") && pagename.equals("srlnkeepinter"))
        {
            clickables = new String[]{
                "LakiTakeLinkProxy",
                "SrlnTakeLinkProxy",
                "TdlmTakeLinkProxy",
                "ThgrTakeLinkProxy",
                //"SrlnKeepClickableProxy",
            };
            
            PrpRootObject resp = prp.findObject("RespLakiActivateKeep", Typeid.plResponderModifier);
            //createPythonLoader(prp, 8, resp.header.desc.toRef());
            //PlLogicModifier logmod = prp.findObject("ClickLakiKeepTakeLink", Typeid.plLogicModifier).castTo();
            //logmod.conditionalcount = 2;
            //logmod.conditionals = new Uruobjectref[]{logmod.conditionals[2],logmod.conditionals[3]};
            //int dummy=0;
        }*/

        //for(String clickable: clickables)
        //{
        //    restoreClickability(prp, clickable);
        //}

        if(agename.equals("siralehn") && pagename.equals("rock"))
        {
            makeClickableUsePythonfilemod(prp, "TakeLinkProxy", "fakelink", "Siralehn", "LinkInTake"); //same name, different page from above.
        }
        if(agename.equals("tahgira") && pagename.equals("icecave"))
        {
            //disable physics on IceCaveCrackMesh
            prp.removeObject(Typeid.plSceneObject,"IceCaveCrackMesh");
            //restoreClickability(prp, "PedButton01ClickProxy");
            restoreClickability(prp, "PedButton02ClickProxy");
            restoreClickability(prp, "PedButton03ClickProxy");
            restoreClickability(prp, "PedButton04ClickProxy");
            restoreClickability(prp, "PedButton05ClickProxy");
            makeClickableUsePythonfilemod(prp, "DireboLinkProxy", "linktoage", "Direbo", "LinkInPoint4");
        }
        if(agename.equals("tahgira") && pagename.equals("exterior"))
        {
            makeClickableUsePythonfilemod(prp, "TakeLink1Proxy", "fakelink", "Tahgira", "LinkInTake");
            makeClickableUsePythonfilemod(prp, "TakeLink2Proxy", "fakelink", "Tahgira", "LinkInTake");
            makeClickableUsePythonfilemod(prp, "TakeLink3Proxy", "fakelink", "Tahgira", "LinkInTake");
        }
        if(agename.equals("todelmer") && pagename.equals("exterior"))
        {
            //restoreClickability(prp, "PedButton01ClickProxy");
            restoreClickability(prp, "PedButton02ClickProxy");
            restoreClickability(prp, "PedButton03ClickProxy");
            //restoreClickability(prp, "PedButton04ClickProxy");
            restoreClickability(prp, "PedButton05ClickProxy");
            makeClickableUsePythonfilemod(prp, "DireboLinkProxy", "linktoage", "Direbo", "LinkInPoint3");
            makeClickableUsePythonfilemod(prp, "TakeLinkProxy", "fakelink", "Todelmer", "LinkInTake");
            makeClickableUsePythonfilemod(prp, "TakeLinkProxy01", "fakelink", "Todelmer", "LinkInTake");
        }
        if(agename.equals("todelmer") && pagename.equals("interiorpillar1"))
        {
            prp.removeObject(Typeid.plSceneObject,"XrgnStairs01"); //the blocker for the stairs in the building on the main pillar.
        }
        if(agename.equals("todelmer") && pagename.equals("interiorpillar3"))
        {
            prp.removeObject(Typeid.plSceneObject,"XrgnStairs01"); //the blocker for the stairs in the building on the main pillar.
        }
        if(agename.equals("mystmystv") && pagename.equals("island"))
        {
            prp.removeObject(Typeid.plSceneObject, "PlanetariumDoorBlocker");
            
            PlRandomSoundMod rsm = prp.findObject("cSfxRndThunder", Typeid.plRandomSoundMod).castTo();
            rsm.parent.state = 0; //turn it on
            rsm.parent.mode = 3; //must disable the kOneCmd
            //rsm.parent.minDelay = Flt.createFromJavaFloat(4); //just picked a number.
            //rsm.parent.maxDelay = Flt.createFromJavaFloat(30); //just picked a number.
            rsm.parent.minDelay = Flt.createFromJavaFloat(4); //just picked a number.
            rsm.parent.maxDelay = Flt.createFromJavaFloat(20); //just picked a number.
            
            //PlRandomSoundMod rs2 = prp.findObject("cSfxRandomCreaks", Typeid.plRandomSoundMod).castTo();
            //rs2.parent.state = 0;
        }

    }
    public static void createPythonLoader(prpfile prp, int type, Uruobjectref ref)
    {
        if(type==8) //responder
        {
            x00A2Pythonfilemod mod = x00A2Pythonfilemod.createDefault();
            mod.pyfile = Urustring.createFromString("dusttest");
            mod.addListing(Pythonlisting.createWithString(4, 1, Bstr.createFromString("storeattrib")));
            mod.addListing(Pythonlisting.createWithRef(type, 10, ref));
            Uruobjectref modref = Uruobjectref.createDefaultWithTypeNamePage(Typeid.plPythonFileMod, ref.xdesc.objectname.toString()+"_pfm", prp.header.pageid);
            PrpRootObject modroot = PrpRootObject.createFromDescAndObject(modref.xdesc, mod);
            
            PrpRootObject sn = null;
            for(PrpRootObject obj: prp.objects)
                if(obj.header.objecttype==Typeid.plSceneNode)
                    sn = obj;
            
            
            x0001Sceneobject so = x0001Sceneobject.createDefaultWithScenenode(sn.header.desc.toRef());
            so.addToObjectrefs2(modref);
            Uruobjectref soref = Uruobjectref.createDefaultWithTypeNamePage(Typeid.plSceneObject, ref.xdesc.objectname.toString()+"_so", prp.header.pageid);
            PrpRootObject soroot = PrpRootObject.createFromDescAndObject(soref.xdesc, so);
            
            prp.extraobjects.add(soroot);
            prp.extraobjects.add(modroot);
        }
    }
    public static void modifyPythonfilemod(prpfile prp, String sceneobjectname, String... pythonparams)
    {
        m.warn("Untested modifyPythonfilemod.");
        x0001Sceneobject so = prp.findObject(sceneobjectname, Typeid.plSceneObject).castTo();
        PlLogicModifier logmod=null;
        for(Uruobjectref curref: so.objectrefs2)
        {
            if(curref.hasref() && curref.xdesc.objecttype==Typeid.plLogicModifier)
            {
                logmod = prp.findObjectWithRef(curref).castTo();
                break;
            }
        }
        if(logmod==null) throw new shared.uncaughtexception("modifyPythonfilemod couldn't find a ref.");
        if(logmod.parent.message.type!=Typeid.plNotifyMsg) throw new shared.uncaughtexception("modifyPythonfilemod found the wrong type.");
        PrpMessage.PlNotifyMsg msg = (PrpMessage.PlNotifyMsg)logmod.parent.message.prpobject.object;
        if(msg.parent.refcount!=1) throw new shared.uncaughtexception("Should only be one ref in modifyPythonfilemod.");
        x00A2Pythonfilemod pfm = prp.findObjectWithRef(msg.parent.refs[0]).castTo(x00A2Pythonfilemod.class);
        pfm.pyfile = Urustring.createFromString("dusttest");
        pfm.clearListings();
        for(int i=0;i<pythonparams.length;i++)
        {
            pfm.addListing(x00A2Pythonfilemod.Pythonlisting.createWithString(4, i+1, Bstr.createFromString(pythonparams[i])));
        }
        
    }
    public static void makeClickableUsePythonfilemod(prpfile prp, String sceneobjectname, String... pythonparams)
    {
        x0001Sceneobject so = prp.findObject(sceneobjectname, Typeid.plSceneObject).castTo();
        PlLogicModifier logmod=null;
        for(Uruobjectref curref: so.objectrefs2)
        {
            if(curref.hasref() && curref.xdesc.objecttype==Typeid.plLogicModifier)
            {
                logmod = prp.findObjectWithRef(curref).castTo();
            }
        }

        //PlLogicModifier logmod = prp.findObject(c, Typeid.plLogicModifier).castTo();
        x00A2Pythonfilemod pfm = x00A2Pythonfilemod.createDefault();
        pfm.pyfile = Urustring.createFromString("dusttest");
        pfm.addListing(x00A2Pythonfilemod.Pythonlisting.createWithString(4, 1, Bstr.createFromString(pythonparams[0])));//"linktoage"
        pfm.addListing(x00A2Pythonfilemod.Pythonlisting.createWithString(4, 2, Bstr.createFromString(pythonparams[1])));//"Direbo")));
        pfm.addListing(x00A2Pythonfilemod.Pythonlisting.createWithString(4, 3, Bstr.createFromString(pythonparams[2])));//"LinkInPointDefault")));
        Uruobjectref pfmref = Uruobjectref.createDefaultWithTypeNamePage(Typeid.plPythonFileMod, sceneobjectname+"_pfm" , prp.header.pageid);
        PrpRootObject pfmroot = PrpRootObject.createFromDescAndObject(pfmref.xdesc, pfm);
        prp.extraobjects.add(pfmroot); //don't forget to add the PFM!
        so.addToObjectrefs2(pfmref); //at least one sceneobject must reference the PFM, or it won't load.
        //logmod.parent.disabled = 0;
        makeLogicModifierUsePythonFileMod(logmod,pfmref);
    }
    public static void makeLogicModifierUsePythonFileMod(PlLogicModifier logicmod, Uruobjectref pythonfilemod)
    {
        if(logicmod.parent.message.type!=Typeid.plNotifyMsg)
        {
            m.err("makeLogicModifierUsePythonFileMod can only handle plNotifyMsg currently.");
            return;
        }
        PrpMessage.PlNotifyMsg notify = PrpMessage.PlNotifyMsg.createWithRef(pythonfilemod);
        logicmod.parent.message = PrpTaggedObject.createWithTypeidUruobj(Typeid.plNotifyMsg, notify);
    }
    public static void restoreClickability(prpfile prp, String clickableSceneObject)
    {
        x0001Sceneobject so = prp.findObject(clickableSceneObject, Typeid.plSceneObject).castTo();
        for(Uruobjectref ref: so.objectrefs2)
        {
            if(ref.hasref() && ref.xdesc.objecttype==Typeid.plLogicModifier)
            {
                PlLogicModifier lm2 = prp.findObjectWithRef(ref).castTo();
                lm2.parent.disabled = 0; //set disabled to false.
                /*for(Uruobjectref condref: lm2.conditionals)
                {
                    if(condref.hasref()&&condref.xdesc.objecttype==Typeid.plActivatorConditionalObject)
                    {
                        PlActivatorConditionalObject aco2 = prp.findObjectWithRef(condref).castTo();
                        aco2.parent.satisfied = 1;
                        int dummy=0;
                    }                                
                }*/
                //int dummy=0;
            }
        }
    }
    public static void convertMyst5ToPots(String infolder, String outfolder, Vector<String> files, boolean makeMinimalReleeshan)
    {
        class compileDecider implements uru.moulprp.prputils.Compiler.Decider
        {
            public boolean isObjectToBeIncluded(Uruobjectdesc desc)
            {
                //return false;
                Typeid type = desc.objecttype;
                String name = desc.objectname.toString();
                Pageid pageid = desc.pageid;

                //blacklist
                if(type==Typeid.plBoundInterface&&name.equals("PartColl08")&&pageid.prefix==0x5C&&pageid.suffix==0x23) return false; //Kveer
                if(type==Typeid.plBoundInterface&&name.equals("PartColl07")&&pageid.prefix==0x5C&&pageid.suffix==0x23) return false; //Kveer
                if(type==Typeid.plBoundInterface&&name.equals("PartColl06")&&pageid.prefix==0x5C&&pageid.suffix==0x23) return false; //Kveer
                if(type==Typeid.plBoundInterface && pageid.prefix==89) //Siralehn/Noloben
                {
                    if(name.equals("Cone01") || name.equals("doorParticleColliderMesh") || name.startsWith("RainDef0") || name.startsWith("RainDef1") || name.equals("RsinDefTop"))
                    {
                        return false;
                    }
                }
                if(type==Typeid.plBoundInterface && pageid.prefix==88) //tahgira
                {
                    if(name.equals("CaveSnowKiller01")) return false;
                    if(name.equals("FieldBubbleKiller")) return false; //this makes tahgira crash when you link there in 3rd person, I think.
                }

                if(type==Typeid.plBoundInterface)
                {
                    int dummy=0;
                }

                Typeid[] typeequals = new Typeid[]{
                    Typeid.plSceneNode, Typeid.plSceneObject, Typeid.plCoordinateInterface, Typeid.plSpawnModifier, Typeid.plFilterCoordInterface, //not having plFilterCoordInterface can crash Uru.

                    Typeid.plMipMap, Typeid.plCubicEnvironMap,
                    Typeid.plLayer, Typeid.hsGMaterial, Typeid.plDrawableSpans,
                    Typeid.plViewFaceModifier,
                    Typeid.plLayerAnimation,
                    Typeid.plLayerLinkAnimation,
                    Typeid.plLayerBink, //must have the .bnk files copied over or Uru will crash.

                    Typeid.plHKPhysical, Typeid.plSimulationInterface,
                    Typeid.plDirectionalLightInfo, Typeid.plOmniLightInfo, Typeid.plSpotLightInfo,
                    Typeid.plAGMasterMod, Typeid.plAGModifier, Typeid.plATCAnim,
                    Typeid.plParticleSystem, Typeid.plParticleLocalWind, Typeid.plParticleCollisionEffectDie,
                    Typeid.plAudioInterface, Typeid.plRandomSoundMod, Typeid.plSoundBuffer, Typeid.plWinAudio, Typeid.plWin32StreamingSound, Typeid.plWin32StaticSound, Typeid.plStereizer,
                    Typeid.plDrawInterface,
                    Typeid.plSoftVolumeSimple,
                    Typeid.plOccluder, Typeid.plShadowCaster, Typeid.plSoftVolumeInvert, Typeid.plSoftVolumeUnion, Typeid.plSoftVolumeIntersect,
                    Typeid.plObjectInBoxConditionalObject, Typeid.plObjectInVolumeDetector,
                    Typeid.plActivatorConditionalObject, Typeid.plFacingConditionalObject, Typeid.plVolumeSensorConditionalObject,
                    Typeid.plInterfaceInfoModifier, Typeid.plLogicModifier,
                    Typeid.plPointShadowMaster,
                    Typeid.plDynamicEnvMap,
                    Typeid.plWaveSet7,
                    Typeid.plPickingDetector, Typeid.plMsgForwarder, Typeid.plLineFollowMod, Typeid.plExcludeRegionModifier,
                    Typeid.plPythonFileMod,
                    Typeid.plResponderModifier,
                    Typeid.plParticleCollisionEffectBounce, Typeid.plPostEffectMod,

                    Typeid.pfGUIButtonMod,
                    Typeid.pfGUIDialogMod,
                    Typeid.plAGAnimBink,
                    Typeid.plAnimEventModifier,
                    Typeid.plAxisAnimModifier,
                    Typeid.plMobileOccluder,
                    Typeid.plDirectShadowMaster,
                    Typeid.plVisRegion,
                    Typeid.plParticleFlockEffect,
                    Typeid.plImageLibMod,

                    Typeid.plMobileOccluder,
                    Typeid.plArmatureMod,
                    Typeid.plArmatureEffectsMgr,

                    Typeid.plParticleCollisionEffectBeat,
                    Typeid.plParticleFadeVolumeEffect,
                    Typeid.plParticleFollowSystemEffect,

                    Typeid.pfGUIKnobCtrl,

                    Typeid.plBoundInterface,
                };
                String[] namestarts={
                    /*//"boulder01",
                    "bridge poles05", //works when plLayerBink is present.
                    "bubble01haloa01", //works!*/
                    //"partcoll06", //breaks it.
                    //"partcoll07", //breaks it.
                    //"partcoll08", //breaks it.
                    //"partcoll09",
                    //"partcollidtablet",
                    //"particlegroundcollide",
                    //"craterupper", //works
                    //"spawnwindmill",
                    //"startpoint01_1",
                    //"box",
                    //"cone",
                    //"door",
                    //"raindef0",
                    //"raindef1",
                    //"raindeflector",
                    //"rsin",
                    //"CaveSnowKiller01",
                    //"FieldBubbleKiller",
                };
                for(Typeid curtype: typeequals) if(curtype==type) return true;
                for(String start: namestarts) if(name.toLowerCase().startsWith(start.toLowerCase())) return true;

                if(type==Typeid.plCoordinateInterface)
                {
                    for(String start: new String[]{
                        "arenagateleft",
                        //"arenalift",
                    }) if(name.toLowerCase().startsWith(start.toLowerCase())) return true;
                }

                m.msg("Skipping type(3): ",type.toString());
                return false;
            }
        }

        if(!makeMinimalReleeshan) m.warn("Currently, making a KveerMystV that isn't just releeshan isn't supported.  Nag Dustin to fix it!");

        //HashMap<String, Integer> prefices = new HashMap<String, Integer>();
        //prefices.put("Descent", 94);
        //prefices.put("Direbo", 93);
        //prefices.put("Kveer", 92);
        //prefices.put("Laki", 91);
        //prefices.put("Myst", 90);
        //prefices.put("Siralehn", 89);
        //prefices.put("Tahgira", 88);
        //prefices.put("Todelmer", 87);

        //HashMap<String, String> agenames = new HashMap<String, String>();
        //agenames.put("Descent", "DescentMystV");
        //agenames.put("Kveer", "KveerMystV");
        //agenames.put("Myst", "MystMystV");

        cmap<String,cmap<String,Integer>> authored = new cmap();
        //authored.put("Myst","Additions",89);
        authored.put("Myst","FootRgns",89);
        authored.put("Direbo","Additions",98);
        authored.put("Descent","FootRgns",97);
        authored.put("Tahgira","FootRgns",97);
        authored.put("Todelmer","FootRgns",92);
        authored.put("Kveer","FootRgns",97); //Releeshan

        Typeid[] readable = mystAutomation.moulReadable;

        //create folders...
        FileUtils.CreateFolder(outfolder+"/dat/");

        //Handle .bik files...
        Vector<String> bikfiles = common.filterFilenamesByExtension(files, ".bik");
        for(String filename: bikfiles)
        {
            String infile = infolder + "/avi/" + filename;
            String outfile = outfolder + "/avi/" + filename;

            FileUtils.CopyFile(infile, outfile, true, false);
        }

        //Handle .ogg files...
        Vector<String> oggfiles = common.filterFilenamesByExtension(files, ".ogg");
        for(String filename: oggfiles)
        {
            String infile = infolder + "/sfx/" + filename;
            String outfile = outfolder + "/sfx/" + filename;

            FileUtils.CopyFile(infile, outfile, true, false);
        }

        //Handle .fni files...
        Vector<String> fnifiles = common.filterFilenamesByExtension(files, ".fni");
        for(String filename: fnifiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + common.replaceAgenameIfApplicable(filename, AllGames.getMystV().g.renameinfo.agenames);

            Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
            Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);

            //laki needs these lines.
            if(agename.toLowerCase().equals("laki"))
            {
                textfile fnifile = textfile.createFromBytes(decryptedData);
                //fnifile.appendLine("Graphics.Renderer.SetClearColor 0 0 0");
                //fnifile.appendLine("Graphics.Renderer.Fog.SetDefColor 0 0 0");
                fnifile.appendLine("Graphics.Renderer.Fog.SetDefLinear 0 0 0");
                decryptedData = fnifile.saveToBytes();
            }

            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }


        //Handle .age files...
        AllGames.getMystV().ConvertGame(infolder, outfolder);
        /*Vector<String> agefiles = common.filterFilenamesByExtension(files, ".age");
        for(String filename: agefiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfile = outfolder + "/dat/" + common.replaceAgenameIfApplicable(filename, agenames);

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

            //Remove all the KveerMystV pages except kverReleeshan and dusttest from the .age file.
            if(makeMinimalReleeshan)
            {
                if(agename.equals("Kveer"))
                {
                    textfile agefile = textfile.createFromBytes(decryptedData);
                    for(textfile.textline line: agefile.getLines())
                    {
                        String l = line.getString();
                        if(l.startsWith("Page="))
                        {
                            line.setString("#"+l); //comment it out.
                        }
                    }
                    agefile.appendLine("Page=kverReleeshan,22"); //remove the ,1 from the end so that it loads.
                    //agefile.appendLine("Page=dusttest,90"); //leave it alone.
                    decryptedData = agefile.saveToBytes();
                }
            }

            //add dusttest page, dynamically loaded.
            if(agename.equals("Descent") || agename.equals("Todelmer") || agename.equals("Tahgira") || agename.equals("Siralehn") || agename.equals("Laki") || agename.equals("Kveer"))
            {
                textfile agefile = textfile.createFromBytes(decryptedData);
                agefile.appendLine("Page=dusttest,90");
                decryptedData = agefile.saveToBytes();
            }

            //add any pages that are authored.*/
            /*if(shared.State.AllStates.getStateAsBoolean("includeAuthoredMaterial") && authored.get(agename) != null)
            {
                //for(Pair<String,Integer> curauthprp: authored.get(agename))
                for(Pair<String,Integer> curauthprp: authored.get(agename).getAllElements())
                {
                    String pagename = curauthprp.left;
                    int pagenum = curauthprp.right;

                    textfile agefile = textfile.createFromBytes(decryptedData);
                    agefile.appendLine("Page="+pagename+","+Integer.toString(pagenum));
                    decryptedData = agefile.saveToBytes();
                }

                //if(agename.equals("Myst"))
                //{
                //    textfile agefile = textfile.createFromBytes(decryptedData);
                //    agefile.appendLine("Page=Additions,89");
                //    decryptedData = agefile.saveToBytes();
                //}
                //if(agename.equals("Direbo"))
                //{
                //    textfile agefile = textfile.createFromBytes(decryptedData);
                //    agefile.appendLine("Page=Additions,98");
                //    decryptedData = agefile.saveToBytes();
                //}
            }*/


            /*Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }*/


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


        //Handle .prp files...
        Vector<String> prpfiles = common.filterFilenamesByExtension(files, ".prp");
        for(String filename: prpfiles)
        {

            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/dat/" + filename;
            String outfilename = common.replaceAgenameIfApplicable(filename, AllGames.getMystV().g.renameinfo.agenames).replaceFirst("_", "_District_");
            String outfile = outfolder + "/dat/" + outfilename;

            if(shared.GetResource.hasResource("/files/myst5/"+outfilename))
            {
                Bytes bytes = shared.GetResource.getResourceAsBytes("/files/myst5/"+outfilename);
                bytes.saveAsFile(outfile);
            }
            //else if(shared.GetResource.hasResource("files/authored/"+outfilename))
            //{
            //    if(shared.State.AllStates.getStateAsBoolean("includeAuthoredMaterial"))
            //    {
            //        Bytes bytes = shared.GetResource.getResourceAsBytes("/files/authored/"+outfilename);
            //        bytes.saveAsFile(outfile);
            //    }
            //}
            else
            {
                //Bytes prpdata = Bytes.createFromFile(infile);
                //Bytestream bytestream = Bytestream.createFromBytes(prpdata);
                IBytestream bytestream = shared.SerialBytestream.createFromFilename(infile);
                context c = context.createFromBytestream(bytestream);
                c.curFile = filename; //helpful for debugging.

                //modify sequence prefix if Age is in list.
                Integer prefix = AllGames.getMystV().g.renameinfo.prefices.get(agename);
                if(prefix!=null)
                {
                    c.sequencePrefix = prefix;
                }

                //modify agename if Age is in list.
                String newAgename = AllGames.getMystV().g.renameinfo.agenames.get(agename);
                if(newAgename!=null)
                {
                    c.ageName = newAgename;
                }

                prpfile prp = prpfile.createFromContext(c, readable);

                myst5ProcessPrp(prp,agename,AllGames.getMystV().g.renameinfo.agenames,outfolder,infolder);

                //Bytes prpoutputbytes = prp.saveAsBytes(new compileDecider());
                //prpoutputbytes.saveAsFile(outfile);
                prp.saveAsBytes(new compileDecider()).writeAllBytesToFile(outfile);

                c.close();
            }
        }


        //Handle .sum files...
        Vector<String> sumfiles = common.filterFilenamesByExtension(files, ".sum");
        for(String filename: sumfiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            //Bytes sum1 = uru.moulprp.sumfile.createSumfile(outfolder+"/dat/", common.replaceAgenameIfApplicable(agename, agenames));
            Bytes sum1 = uru.moulprp.sumfile.createEmptySumfile();
            FileUtils.WriteFile(outfolder+"/dat/"+common.replaceAgenameIfApplicable(filename, AllGames.getMystV().g.renameinfo.agenames), sum1);
        }

        //Handle .sdl files...
        /*Vector<String> sdlfiles = common.filterFilenamesByExtension(files, ".sdl");
        for(String filename: sdlfiles)
        {
            String agename = common.getAgenameFromFilename(filename);
            String infile = infolder + "/sdl/" + filename;
            String outfile = outfolder + "/sdl/" + common.replaceAgenameIfApplicable(filename, agenames);

            Bytes encryptedData = FileUtils.ReadFileAsBytes(infile);
            Bytes decryptedData = UruCrypt.DecryptEoa(encryptedData);

            uru.moulprp.sdlfile sdl = new uru.moulprp.sdlfile(decryptedData);

            Bytes wdysData = UruCrypt.EncryptWhatdoyousee(decryptedData);
            FileUtils.WriteFile(outfile, wdysData);
        }*/


        //All done!
        m.msg("Done MystV work!");
    }
    public static void myst5ProcessPrp(prpfile prp, String agename, HashMap<String, String> agenames,String outfolder, String infolder)
    {
        auto.postmod.PostMod_RemoveDynamicCamMap.PostMod_RemoveDynamicCampMap(prp);

        String newagename = agenames.get(agename);
        if(newagename!=null)
        {
            auto.postmod.PostMod_RemoveDynamicCamMap.PostMod_ChangeVerySpecialPython(prp, agename, newagename);
        }
        String newAgename = (newagename==null)?agename:newagename;

        auto.postmod.PostMod_RemoveDynamicCamMap.PostMod_RemoveLadders(prp);

        PostMod_InvertEnvironmaps2(prp);

        PostMod_AutomateMyst5(prp,infolder,newAgename);

    }
    public static void PostMod_InvertEnvironmaps2(prpfile prp)
    {
        String age = prp.header.agename.toString();
        String page = prp.header.pagename.toString();
        //instead of a manual list, does it depend on a layer flag?
        //this works, but it's simpler to just use the manual list, so we don't have to hop accross different prps.
        boolean manual = true;
        if(!manual)
        {
            for(PrpRootObject ro: prp.FindAllObjectsOfType(Typeid.plLayer))
            {
                uru.moulprp.x0006Layer layer = ro.castTo();
                if(layer.texture.hasref() && layer.texture.xdesc.objecttype==Typeid.plCubicEnvironMap)
                {
                    if((layer.flags5&0x400000)!=0) //kMiscCam2Screen flag
                    {
                        //do something!
                        m.status("flagset: "+age+": "+layer.texture.toString());
                        int dummy=0;
                    }
                }
            }
        }
        else
        {
            if(page.equals("Textures"))
            {
                if(age.equals("Direbo"))
                {
                    PostMod_InvertEnvironmaps(prp,"xbublakitake_fr*0.hsm");
                    PostMod_InvertEnvironmaps(prp,"xbubsrlntake_fr*0.hsm");
                    PostMod_InvertEnvironmaps(prp,"xbubtdlmtake_fr*0.hsm");
                    PostMod_InvertEnvironmaps(prp,"xbubthgrtake_fr*0.hsm");
                }
                if(age.equals("KveerMystV")) //we don't really need to do this one, because we just do the Releeshan part.
                {
                    PostMod_InvertEnvironmaps(prp,"xbublakikeep_fr*0.hsm");
                    PostMod_InvertEnvironmaps(prp,"xbubsrlnkeep_fr*0.hsm");
                    PostMod_InvertEnvironmaps(prp,"xbubtdlmkeep_fr*0.hsm");
                    PostMod_InvertEnvironmaps(prp,"xbubthgrkeep_fr*0.hsm");
                }
                if(age.equals("Siralehn"))
                {
                    PostMod_InvertEnvironmaps(prp,"xbubdrbotake01_fr*0.hsm");
                    PostMod_InvertEnvironmaps(prp,"xbubkverkeep_fr*0.hsm");
                }
                if(age.equals("Laki"))
                {
                    PostMod_InvertEnvironmaps(prp,"xbubdrbotake01_fr*0.hsm");
                    PostMod_InvertEnvironmaps(prp,"xbubkverkeep_fr*0.hsm");
                }
                if(age.equals("Tahgira"))
                {
                    PostMod_InvertEnvironmaps(prp,"xbubdrbotake01_fr*0.hsm");
                    PostMod_InvertEnvironmaps(prp,"xbubkverkeep_fr*0.hsm");
                }
                if(age.equals("Todelmer"))
                {
                    PostMod_InvertEnvironmaps(prp,"xbubdrbotake01_fr*0.hsm");
                    PostMod_InvertEnvironmaps(prp,"xbubkverkeep_fr*0.hsm");
                }
            }
        }
    }
    public static void PostMod_InvertEnvironmaps(prpfile prp, String objname)
    {
        PrpRootObject ro = prp.findObject(objname, Typeid.plCubicEnvironMap);
        uru.moulprp.x0005Environmap em = ro.castTo();
        em.invert();
    }
    public static void PostMod_AutomateMyst5(prpfile prp, String infolder, String newAgename)
    {
        mystv.fixClickables(newAgename, prp);
        mystv.fixBinks(newAgename, prp, infolder);
        //fix direbo links.
        PrpRootObject[] objs = prputils.FindAllObjectsOfType(prp, Typeid.plPythonFileMod);
        for(PrpRootObject obj: objs)
        {
            x00A2Pythonfilemod pfm = obj.castTo();
            if(newAgename.toLowerCase().equals("descentmystv")||newAgename.toLowerCase().equals("direbo"))
            {
                if(pfm.pyfile.toString().toLowerCase().equals("xlinkingbookguipopup"))
                {
                    String oldlink = pfm.listings.get(2).xString.toString();
                    String age;
                    String spawnpoint;
                    if(oldlink.equals("DireboLaki"))
                    {
                        age = "Direbo";
                        spawnpoint = "LinkInPoint2";
                    }
                    else if(oldlink.equals("DireboSrln"))
                    {
                        age = "Direbo";
                        spawnpoint = "LinkInPoint1";
                    }
                    else if(oldlink.equals("DireboThgr"))
                    {
                        age = "Direbo";
                        spawnpoint = "LinkInPoint4";
                    }
                    else if(oldlink.equals("DireboTdlm"))
                    {
                        age = "Direbo";
                        spawnpoint = "LinkInPoint3";
                    }
                    else if(oldlink.equals("DescentRestAreaA"))
                    {
                        age = "DescentMystV";
                        spawnpoint = "LinkInFromThgrDirebo";
                    }
                    else if(oldlink.equals("DescentRestAreaB"))
                    {
                        age = "DescentMystV";
                        spawnpoint = "LinkInFromTdlmDirebo";
                    }
                    else if(oldlink.equals("DescentRestAreaC"))
                    {
                        age = "DescentMystV";
                        spawnpoint = "LinkInFromSrlnDirebo";
                    }
                    else if(oldlink.equals("DescentRestAreaD"))
                    {
                        age = "DescentMystV";
                        spawnpoint = "LinkInFromLakiDirebo";
                    }
                    else
                    {
                        m.err("Broken linking book in prpprocess.");
                        age="";
                        spawnpoint="";
                    }
                    pfm.pyfile = Urustring.createFromString("dusttest");
                    pfm.clearListings();
                    //pfm.listcount = 3;
                    //pfm.listings = new Pythonlisting[3];
                    pfm.addListing(Pythonlisting.createWithString(4, 1, Bstr.createFromString("linktoage")));
                    pfm.addListing(Pythonlisting.createWithString(4, 2, Bstr.createFromString(age)));
                    pfm.addListing(Pythonlisting.createWithString(4, 3, Bstr.createFromString(spawnpoint)));
                    /*pfm.listings[0] = new Pythonlisting();
                    pfm.listings[0].index = 1;
                    pfm.listings[0].type = 4; //string
                    pfm.listings[0].xString = Bstr.createFromString("linktoage");
                    pfm.listings[1] = new Pythonlisting();
                    pfm.listings[1].index = 2;
                    pfm.listings[1].type = 4; //string
                    pfm.listings[1].xString = Bstr.createFromString(age);
                    pfm.listings[2] = new Pythonlisting();
                    pfm.listings[2].index = 3;
                    pfm.listings[2].type = 4; //string
                    pfm.listings[2].xString = Bstr.createFromString(spawnpoint);*/

                    //Vector<Pythonlisting> pls = new Vector<Pythonlisting>();
                    //for(Pythonlisting pl: pfm.listings)
                }
            }
        }

        //pd1 = prp.findObjectWithRef(aco1.pickingdetectors[0]).castTo();
        //pd2 = prp.findObjectWithRef(aco2.pickingdetectors[0]).castTo();
        //lm2.parent.u2 = 0; //set disabled to false.
        int dummy=0;

        if(newAgename.toLowerCase().equals("descentmystv") && prp.header.pagename.toString().toLowerCase().equals("dsntgreatshaftlowerrm"))
        {
            PlHKPhysical erf = prp.findObject("ElevRisingFloor", Typeid.plHKPhysical).castTo();
            erf.ode.convertee.coltype = 0x200;
            erf.ode.convertee.LOSDB = 0x44;
            erf.ode.convertee.group = new HsBitVector(4);
            erf.ode.convertee.flagsdetect = 0x0;
            erf.ode.convertee.mass = Flt.one();
        }
    }
}
