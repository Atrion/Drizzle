/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import shared.Flt;
import uru.moulprp.*;
import shared.m;
import uru.moulprp.x00A2Pythonfilemod.Pythonlisting;
import java.io.File;
import java.util.Vector;
import shared.*;

public class myst5Fixes
{
    public static void convertABunchOfMyst5Stuff(String myst5folder, String potsfolder)
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
            m.err("The Myst5 folder you selected doesn't exist.");
            return;
        }
        if(!myst5file.isDirectory())
        {
            m.err("The Myst5 folder you selected must be a folder, not a file.");
            return;
        }
        File myst5exe = new File(myst5folder+"/MystV.exe");
        if(!myst5exe.exists())
        {
            m.err("The Myst5 folder you selected doesn't seem to contain Myst5.  Please select the folder that contains MystV.exe");
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
        Vector<String> files = uru.generics.convertArrayToVector(automation.fileLists.mystvFiles);
        Vector<String> oggfiles = uru.generics.convertArrayToVector(automation.fileLists.mystvOggsNotInPotsNorMoulofflineMinusSpeeches);
        files.addAll(oggfiles);

        try{
            automation.mystAutomation.convertMyst5ToPots(myst5folder, potsfolder, files, true);
        }catch(shared.cancelexception e){
            m.warn("Conversion cancelled.");
        }
        
        
        shared.State.AllStates.pop();
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
                m.msg("Modifying bink: "+pathtobinkfile+" length="+Float.toString(length));
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

}
