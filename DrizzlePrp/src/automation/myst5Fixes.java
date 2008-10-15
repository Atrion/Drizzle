/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import uru.moulprp.*;
import shared.m;
import uru.moulprp.x00A2Pythonfilemod.Pythonlisting;

public class myst5Fixes
{
    public static void fixClickables(String finalname, prpfile prp)
    {
        //restore limited clickables
        String[] clickables = {};
        String agename = finalname.toLowerCase();
        String pagename = prp.header.pagename.toString().toLowerCase();
        
        if(agename.equals("direbo") && pagename.equals("restage"))
        {
            clickables = new String[]{
                "PedButton02ClickProxyLaki",
                "PedButton03ClickProxyLaki",
                "PedButton04ClickProxyLaki",
                "PedButton05ClickProxyLaki",
                "PedButton02ClickProxyTdlm",
                "PedButton03ClickProxyTdlm",
                //"PedButton04ClickProxyTdlm",
                "PedButton05ClickProxyTdlm",
                "PedButton02ClickProxyThgr",
                "PedButton03ClickProxyThgr",
                "PedButton04ClickProxyThgr",
                "PedButton05ClickProxyThgr",
                "PedButton02ClickProxySrln",
                "PedButton03ClickProxySrln",
                //"PedButton04ClickProxySrln",
                "PedButton05ClickProxySrln",

            };
        }
        if(agename.equals("siralehn") && pagename.equals("exterior"))
        {
            clickables = new String[]{
                //"PedButton01ClickProxy", //don't link to self.
                "PedButton02ClickProxy",
                "PedButton03ClickProxy",
                //"PedButton04ClickProxy", //links to the area with Esher on the beach.
                "PedButton05ClickProxy",
            };
        }
        if(agename.equals("laki") && pagename.equals("exterior"))
        {
            clickables = new String[]{
                //"PedButton01ClickProxy", //don't link to self.
                "PedButton02ClickProxy",
                "PedButton03ClickProxy",
                "PedButton04ClickProxy",
                "PedButton05ClickProxy",
            };
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

        for(String clickable: clickables)
        {
            restoreClickability(prp, clickable);
        }

        if(agename.equals("siralehn") && pagename.equals("exterior"))
        {
            makeClickableUsePythonfilemod(prp, "DireboLinkProxy", "linktoage", "Direbo", "LinkInPoint1");
            makeClickableUsePythonfilemod(prp, "TakeLinkProxy", "fakelink", "Siralehn", "LinkInTake");
            prp.removeObject(Typeid.plSceneObject,"LandTopCollision"); //so we can jump off the top
            prp.removeObject(Typeid.plSceneObject,"XrgnKeepDoor"); //so we can enter the keep
        }
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
