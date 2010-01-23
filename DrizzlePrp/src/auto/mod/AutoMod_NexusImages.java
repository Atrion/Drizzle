/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auto.mod;

import uru.moulprp.prpfile;
import uru.moulprp.PrpRootObject;
import uru.moulprp.Typeid;
import uru.moulprp.x0006Layer;
import uru.moulprp.Uruobjectref;
import uru.moulprp.Pageid;
import uru.moulprp.Pagetype;
import uru.moulprp.x00A2Pythonfilemod;
import uru.moulprp.x00A2Pythonfilemod.Pythonlisting;

public class AutoMod_NexusImages
{
    public static void DustinModNexusBookMachine(prpfile prp)
    {
        //replace the texture with our dynamictextmap:
        //PrpRootObject layerroot = prp.findObject("Map #8251", Typeid.plLayer);
        PrpRootObject layerroot = prp.findObject("Map #825", Typeid.plLayer);
        layerroot.hasChanged = true;
        x0006Layer layer = layerroot.castTo();
        Uruobjectref dtmref = Uruobjectref.createDefaultWithTypeNamePagePagetype(Typeid.plDynamicTextMap, "dustDynamicBookTexture", Pageid.createFromPrefixSuffix(-2, 64), Pagetype.createWithType(4));
        //layer.texture = dtmroot.getref();
        layer.texture = dtmref;

        //add ptAttribDynamicMap to the pythonfilemod.
        //PrpRootObject pfmroot = prp.findObject("cPythMachineBrain", Typeid.plPythonFileMod);
        //pfmroot.hasChanged = true;
        //x00A2Pythonfilemod pfm = pfmroot.castTo();
        //pfm.addListing(Pythonlisting.createWithRef(9, 90, dtmroot.getref()));

    }

    public static void DustinModKIBlackBar(prpfile prp)
    {
        //create the dynamictextmap
        //int width = 1024; int height = 1024;
        int width = 512; int height = 512; //let's reduce this a bit, but we can still reuse it.
        PrpRootObject dtmroot = auto.hackFactory.createAndAddDynamicTextMap(prp, "dustDynamicBookTexture", width, height);

        //add ptAttribDynamicMap to the pythonfilemod.
        PrpRootObject pfmroot = prp.findObject("KIHandler", Typeid.plPythonFileMod);
        pfmroot.hasChanged = true;
        x00A2Pythonfilemod pfm = pfmroot.castTo();
        pfm.addListing(Pythonlisting.createWithRef(9, 90, dtmroot.getref()));

        //create references to the dynamictextmaps used by the book gui.
        Uruobjectref bkref1 = Uruobjectref.createDefaultWithTypeNamePagePagetype(Typeid.plDynamicTextMap, "LeftDTMap2_dynText", Pageid.createFromPrefixSuffix(-2, 54), Pagetype.createWithType(4));
        pfm.addListing(Pythonlisting.createWithRef(9, 91, bkref1));
        Uruobjectref bkref2 = Uruobjectref.createDefaultWithTypeNamePagePagetype(Typeid.plDynamicTextMap, "RightDTMap2_dynText", Pageid.createFromPrefixSuffix(-2, 54), Pagetype.createWithType(4));
        pfm.addListing(Pythonlisting.createWithRef(9, 92, bkref2));
        Uruobjectref bkref3 = Uruobjectref.createDefaultWithTypeNamePagePagetype(Typeid.plDynamicTextMap, "TurnBackDTMap3_dynText", Pageid.createFromPrefixSuffix(-2, 54), Pagetype.createWithType(4));
        pfm.addListing(Pythonlisting.createWithRef(9, 93, bkref3));
        Uruobjectref bkref4 = Uruobjectref.createDefaultWithTypeNamePagePagetype(Typeid.plDynamicTextMap, "TurnFrontDTMap3_dynText", Pageid.createFromPrefixSuffix(-2, 54), Pagetype.createWithType(4));
        pfm.addListing(Pythonlisting.createWithRef(9, 94, bkref4));
    }
}