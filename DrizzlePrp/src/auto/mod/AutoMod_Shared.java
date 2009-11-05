/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auto.mod;

import uru.moulprp.prpfile;
import shared.m;
import uru.moulprp.Urustring;
import uru.moulprp.Bstr;
import uru.moulprp.PrpRootObject;
import uru.moulprp.Typeid;
import uru.moulprp.Uruobjectdesc;
import uru.moulprp.Uruobjectref;

import uru.moulprp.x0001Sceneobject;
import uru.moulprp.x00A2Pythonfilemod.Pythonlisting;

public class AutoMod_Shared
{
    public static void AddXDustChronicleShowHideToObject(prpfile prp, x0001Sceneobject sceneobject, String sdlvar, String valToMatch, boolean reverse)
    {
        //create pfm
        //add the pfm to the prp if it doesn't have it already:
        Uruobjectref pfmref;
        String pfmname = "PythXDustChronicleShowHide"+sdlvar+(reverse?"Rev":"");
        PrpRootObject pfmobj = prp.findObject(pfmname, Typeid.plPythonFileMod);
        if(pfmobj==null)
        {
            uru.moulprp.x00A2Pythonfilemod pfm = uru.moulprp.x00A2Pythonfilemod.createDefault();
            pfm.pyfile = Urustring.createFromString("xDustChronicleShowHide");
            pfm.addListing(uru.moulprp.x00A2Pythonfilemod.Pythonlisting.createWithString(1, Bstr.createFromString(sdlvar)));
            pfm.addListing(uru.moulprp.x00A2Pythonfilemod.Pythonlisting.createWithString(2, Bstr.createFromString(valToMatch)));
            pfm.addListing(uru.moulprp.x00A2Pythonfilemod.Pythonlisting.createWithBoolean(3, !reverse));
            //no pythonlistings here...
            pfmref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plPythonFileMod, pfmname, prp);
            pfmobj = PrpRootObject.createFromDescAndObject(pfmref.xdesc, pfm);
            prp.addObject(pfmobj);
        }
        else
        {
            pfmref = pfmobj.header.desc.toRef();
        }
        sceneobject.addToObjectrefs2(pfmref);

    }

    public static void AddXAgeSDLBoolShowHideToObject(prpfile prp, x0001Sceneobject sceneobject, String sdlvar, boolean reverse)
    {
        //create pfm
        //add the pfm to the prp if it doesn't have it already:
        Uruobjectref pfmref;
        String pfmname = "PythXAgeSDLBoolShowHide"+sdlvar+(reverse?"Rev":"");
        PrpRootObject pfmobj = prp.findObject(pfmname, Typeid.plPythonFileMod);
        if(pfmobj==null)
        {
            uru.moulprp.x00A2Pythonfilemod pfm = uru.moulprp.x00A2Pythonfilemod.createDefault();
            pfm.pyfile = Urustring.createFromString("xAgeSDLBoolShowHide");
            pfm.addListing(uru.moulprp.x00A2Pythonfilemod.Pythonlisting.createWithString(1, Bstr.createFromString(sdlvar)));
            pfm.addListing(uru.moulprp.x00A2Pythonfilemod.Pythonlisting.createWithBoolean(2, !reverse));
            //no pythonlistings here...
            pfmref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plPythonFileMod, pfmname, prp);
            pfmobj = PrpRootObject.createFromDescAndObject(pfmref.xdesc, pfm);
            prp.addObject(pfmobj);
        }
        else
        {
            pfmref = pfmobj.header.desc.toRef();
        }
        sceneobject.addToObjectrefs2(pfmref);

    }
    public static void AddXDustShowToObject(prpfile prp, x0001Sceneobject sceneobject)
    {
        //create pfm
        //add the pfm to the prp if it doesn't have it already:
        Uruobjectref pfmref;
        PrpRootObject pfmobj = prp.findObject("PythXDustShow", Typeid.plPythonFileMod);
        if(pfmobj==null)
        {
            uru.moulprp.x00A2Pythonfilemod pfm = uru.moulprp.x00A2Pythonfilemod.createDefault();
            pfm.pyfile = Urustring.createFromString("xDustShow");
            //no pythonlistings here...
            pfmref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plPythonFileMod, "PythXDustShow", prp);
            pfmobj = PrpRootObject.createFromDescAndObject(pfmref.xdesc, pfm);
            prp.addObject(pfmobj);
        }
        else
        {
            pfmref = pfmobj.header.desc.toRef();
        }
        sceneobject.addToObjectrefs2(pfmref);

    }

    public static void CreatePythonfilemodReference(prpfile prp, String pyfilenameWithoutExtension, Uruobjectref objref, int type, String arbitraryPfmName)
    {
        //create pfm
        uru.moulprp.x00A2Pythonfilemod pfm = uru.moulprp.x00A2Pythonfilemod.createDefault();
        pfm.pyfile = Urustring.createFromString(pyfilenameWithoutExtension);
        pfm.addListing(Pythonlisting.createWithString(4, 1, Bstr.createFromString(objref.xdesc.objectname.toString())));
        pfm.addListing(Pythonlisting.createWithRef(type, 2, objref));
        //pfm.addListing(Pythonlisting.createWithString(4, 3, Bstr.createFromString(objref.xdesc.objecttype.toString())));
        Uruobjectref pfmref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plPythonFileMod, arbitraryPfmName, prp);
        PrpRootObject pfmroot = PrpRootObject.createFromDescAndObject(pfmref.xdesc, pfm);

        //find scenenode.
        PrpRootObject sn = null;
        for(PrpRootObject obj: prp.objects)
            if(obj.header.objecttype==Typeid.plSceneNode)
                sn = obj;

        //create sceneobject
        x0001Sceneobject so = x0001Sceneobject.createDefaultWithScenenode(sn.header.desc.toRef());
        so.addToObjectrefs2(pfmref);
        Uruobjectref soref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plSceneObject, arbitraryPfmName+"_so", prp);
        PrpRootObject soroot = PrpRootObject.createFromDescAndObject(soref.xdesc, so);

        //add scenenode and sceneobject
        sn.markAsChanged();
        soroot.markAsChanged();
        pfmroot.markAsChanged();
        prp.extraobjects.add(soroot);
        prp.extraobjects.add(pfmroot);

    }

}
