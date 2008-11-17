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
        String[] filelist= new String[]{
            "Descent.age","Descent.fni","Descent.sum","Descent_dsntBahro_Idle02.prp","Descent_dsntBahro_Idle03.prp","Descent_dsntBahro_Idle04.prp","Descent_dsntBahro_Idle05.prp","Descent_dsntBahro_Idle06.prp","Descent_dsntBahro_Idle07.prp","Descent_dsntBahro_Idle08.prp","Descent_dsntBahro_Idle09.prp","Descent_dsntBahro_Shot02.prp","Descent_dsntBahro_Shot03.prp","Descent_dsntBahro_Shot04.prp","Descent_dsntBahro_Shot05.prp","Descent_dsntBahro_Shot06.prp","Descent_dsntBahro_Shot07.prp","Descent_dsntBahro_Shot08.prp","Descent_dsntBahro_Shot09.prp","Descent_dsntBahro_Tunnel01.prp","Descent_dsntBahro_Tunnel01Idle.prp","Descent_dsntBats.prp","Descent_dsntEsherIdleTopOfShaft.prp","Descent_dsntEsher_BottomOfShaft.prp","Descent_dsntEsher_FirstHub.prp","Descent_dsntEsher_Intro.prp","Descent_dsntEsher_TopOfShaft.prp","Descent_dsntGreatShaftBalcony.prp","Descent_dsntGreatShaftLowerRm.prp","Descent_dsntLowerBats.prp","Descent_dsntMapGUI.prp","Descent_dsntPostBats.prp","Descent_dsntPostShaftNodeAndTunnels.prp","Descent_dsntShaftGeneratorRoom.prp","Descent_dsntShaftTunnelSystem.prp","Descent_dsntTianaCave.prp","Descent_dsntTianaCaveNode2.prp","Descent_dsntTianaCaveTunnel1.prp","Descent_dsntTianaCaveTunnel3.prp","Descent_dsntUpperBats.prp","Descent_dsntUpperShaft.prp","Descent_dsntVolcano.prp","Descent_Textures.prp","Descent_dusttest.prp",
            "Direbo.age","Direbo.fni","Direbo.sum","Direbo_DragonFly.prp","Direbo_drboEsherIdleDirebo.prp","Direbo_drboEsher_DireboLaki.prp","Direbo_drboEsher_DireboSrln.prp","Direbo_drboEsher_DireboTdlm.prp","Direbo_drboEsher_DireboThgr.prp","Direbo_drboUrwinShape.prp","Direbo_RestAge.prp","Direbo_Textures.prp","Direbo_UrwinIdle.prp","Direbo_UrwinWalk.prp",
            "Kveer.age","Kveer.fni","Kveer.sum",/*"Kveer_bkMystBookLocked.prp","Kveer_GreatRm.prp","Kveer_KveerBats.prp","Kveer_kverAtrus.prp","Kveer_kverAtrus_1.prp","Kveer_kverAtrus_Idle.prp","Kveer_kverBahroWingsGUI.prp","Kveer_kverBahro_1.prp","Kveer_kverBahro_2.prp","Kveer_kverBahro_Ballroom01.prp","Kveer_kverBahro_Ballroom02.prp","Kveer_kverBahro_Ballroom03.prp","Kveer_kverBahro_Exit01.prp","Kveer_kverBahro_Exit02.prp","Kveer_kverBahro_Idle05.prp","Kveer_kverBahro_Idle06.prp","Kveer_kverBahro_Idle07.prp","Kveer_kverBahro_Idle08.prp","Kveer_kverBahro_Idle09.prp","Kveer_kverBahro_Shot03.prp","Kveer_kverBahro_Shot04.prp","Kveer_kverBahro_Shot05.prp","Kveer_kverBahro_Shot06.prp","Kveer_kverBahro_Shot07.prp","Kveer_kverBahro_Shot08.prp","Kveer_kverBahro_Shot09.prp","Kveer_kverConc3Music.prp","Kveer_kverEsher_1.prp",*/"Kveer_kverReleeshan.prp",/*"Kveer_kverYeesha_1.prp","Kveer_kverYeesha_Conc01.prp","Kveer_kverYeesha_Conc02.prp","Kveer_kverYeesha_Conc03.prp","Kveer_kverYeesha_ConcIntro.prp","Kveer_kverYeesha_ConcIntro2.prp","Kveer_kverYeesha_IdleForIntro.prp","Kveer_kverYeesha_Intro.prp","Kveer_Prison.prp",*/"Kveer_Textures.prp","Kveer_dusttest.prp",
            "Laki.age","Laki.fni","Laki.sum","Laki_Exterior.prp","Laki_LakiArenaVillaInt.prp","Laki_LakiCreatures.prp","Laki_lakiEsher-Arena.prp","Laki_lakiEsher-FighterBeach.prp","Laki_lakiEsher-Keep.prp","Laki_lakiEsher-Villa.prp","Laki_lakiEsherIdleKeep.prp","Laki_lakiEsherIdleVilla.prp","Laki_LakiMaze.prp","Laki_lakiMazeClue.prp","Laki_LakiTrees01.prp","Laki_PirBirdActor.prp","Laki_PirBirdChomp.prp","Laki_PirBirdIdle.prp","Laki_PirBirdSwallow.prp","Laki_PirBirdVocalize.prp","Laki_PirBirdWalk.prp","Laki_Textures.prp","Laki_dusttest.prp",
            "Myst.age","Myst.fni","Myst.sum","Myst_Island.prp","Myst_mystEsher-Conc01.prp","Myst_mystEsher-Conc02.prp","Myst_Textures.prp",
            "MystMystV_District_Additions.prp","Direbo_District_Additions.prp", //original authored material.
            "Siralehn.age","Siralehn.fni","Siralehn.sum","Siralehn_Birds.prp","Siralehn_Drawing01.prp","Siralehn_Drawing02.prp","Siralehn_Drawing03.prp","Siralehn_Drawing04.prp","Siralehn_Drawing05.prp","Siralehn_Drawing06.prp","Siralehn_Drawing07.prp","Siralehn_Drawing08.prp","Siralehn_Exterior.prp","Siralehn_rock.prp","Siralehn_srlnEsherIdleBeach.prp","Siralehn_srlnEsherIdleLab.prp","Siralehn_srlnEsher_NolobenBeach.prp","Siralehn_srlnEsher_NolobenKeep.prp","Siralehn_srlnEsher_NolobenLab.prp","Siralehn_srlnKeepInter.prp","Siralehn_Textures.prp","Siralehn_tunnels.prp","Siralehn_dusttest.prp",
            "Tahgira.age","Tahgira.fni","Tahgira.sum","Tahgira_Exterior.prp","Tahgira_IceCave.prp","Tahgira_Textures.prp","Tahgira_thgrEsherIdleIntro.prp","Tahgira_thgrEsherIdleTake.prp","Tahgira_thgrEsher_TahgiraGrave.prp","Tahgira_thgrEsher_TahgiraIntro.prp","Tahgira_thgrEsher_TahgiraTake.prp","Tahgira_thgrEsher_TahgiraThermals.prp","Tahgira_thgrEsher_TahgiraVillage.prp","Tahgira_dusttest.prp",
            "Todelmer.age","Todelmer.fni","Todelmer.sum","Todelmer_Exterior.prp","Todelmer_InteriorPillar1.prp","Todelmer_InteriorPillar3.prp","Todelmer_MiniScope.prp","Todelmer_Pod.prp","Todelmer_Sky.prp","Todelmer_tdlmEsherIdleP3.prp","Todelmer_tdlmEsherIdleRing.prp","Todelmer_tdlmEsher_TodelmerP1.prp","Todelmer_tdlmEsher_TodelmerP3.prp","Todelmer_tdlmEsher_TodelmerRing.prp","Todelmer_Textures.prp","Todelmer_dusttest.prp",
            "direbo.bik","restStop1.bik","restStop2.bik","restStop3.bik","restStop4.bik",
        };
        Vector<String> files = uru.generics.convertArrayToVector(filelist);
        Vector<String> oggfiles = uru.generics.convertArrayToVector(automation.fileLists.mystvOggsNotInPotsNorMoulofflineMinusSpeeches);
        files.addAll(oggfiles);

        automation.mystAutomation.convertMyst5ToPots(myst5folder, potsfolder, files, true);
        
        
        
        shared.State.AllStates.pop();
        m.state.pop();
        m.status("Dont forget to run SoundDecompress.exe in your Pots folder, in order to get the sounds working!");
        m.status("Conversion completed!");
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
