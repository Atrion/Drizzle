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

public class AutoMod_Relto
{

    public static void ModRelto_FixPineTrees(prpfile prp)
    {
        //PythonFileMod stuff
        PrpRootObject pfm_ro = prp.findObject("cPythYeeshaPage7 - Jumping Pinnacles_0", Typeid.plPythonFileMod);
        uru.moulprp.x00A2Pythonfilemod pfm2 = uru.moulprp.x00A2Pythonfilemod.createDefault();
        pfm2.pyfile = Urustring.createFromString("psnlYeeshaPageChanges");
        for(int i=0;i<18;i++) //do we really need 18 copies, or just 1?
        {
            pfm2.addListing(uru.moulprp.x00A2Pythonfilemod.Pythonlisting.createWithInteger(1, 21));
            pfm2.addListing(uru.moulprp.x00A2Pythonfilemod.Pythonlisting.createWithString(2, Bstr.createFromString("0,2,4")));
        }
        //PrpRootObject pfm2_ro = prp.findObject("cPythYeeshaPage21 - PineTrees_0", Typeid.plPythonFileMod);
        PrpRootObject pfm2_ro = PrpRootObject.createFromDescAndObject(Uruobjectdesc.createDefaultWithTypeNamePrp(Typeid.plPythonFileMod, "cPythYeeshaPage21 - PineTrees_0", prp), pfm2);
        prp.addObject(pfm2_ro);

        //set PythonFileMods on all pine trees.
        for(String s: new String[]{"Ponderosa17","Ponderosa18","Ponderosa19","Ponderosa20","Ponderosa21","Ponderosa22","Ponderosa24","Ponderosa25","Ponderosa26","Ponderosa27","Ponderosa28","Ponderosa32","Ponderosa33","Ponderosa34","Ponderosa35","Ponderosa36",/*"Ponderosa39","Ponderosa40",*/"Ponderosa41","Ponderosa42",})
        {
            PrpRootObject so_ro2 = prp.findObject(s, Typeid.plSceneObject);
            uru.moulprp.x0001Sceneobject so2 =so_ro2.castTo();
            so2.clearObjectrefs2(); //this shouldn't be needed, but it may be a good idea incase it has already been modded.
            so2.addToObjectrefs2(pfm2_ro.getref());
            so_ro2.markAsChanged();
        }

        //fix the tree on the island; without this it stays visible even when the island is visible.  Make Ponderosa39 like Ponderosa40.
        //actually, we don't need this anymore, since it already has the correct value from the Pots and A'moac'a versions.
        /*PrpRootObject so_ro = prp.findObject("Ponderosa39", Typeid.plSceneObject);
        uru.moulprp.x0001Sceneobject so = so_ro.castTo();
        so.clearObjectrefs2();
        so.addToObjectrefs2(pfm_ro.getref());
        so_ro.markAsChanged();*/

        //fix the tree whose collider remains even when the tree is invisible.
        PrpRootObject so_ro = prp.findObject("TreeCollision27", Typeid.plSceneObject);
        uru.moulprp.x0001Sceneobject so = so_ro.castTo();
        so.clearObjectrefs2(); //there shouldn't be anything in this one anyway.
        so.addToObjectrefs2(pfm2_ro.getref());
        so_ro.markAsChanged();


    }
    public static void ModRelto_AddBookCovers(prpfile prp)
    {
        for(int i=15;i<=36;i++)
        {
            String book = Integer.toString(i);
            if(i<10) book = "0"+book;

            PrpRootObject book01 = prp.findObject("ShelfA_book"+book, Typeid.plDrawInterface);
            uru.moulprp.plDrawInterface di = book01.castTo();
            for(uru.moulprp.plDrawInterface.SubsetGroupRef sgr: di.subsetgroups)
            {
                PrpRootObject dsroot = prp.findObjectWithRef(sgr.span);
                dsroot.markAsChanged();
                uru.moulprp.PlDrawableSpans ds = dsroot.castTo();
                uru.moulprp.PlDrawableSpans.PlDISpanIndex sg = ds.DIIndices[sgr.subsetgroupindex];
                for(int subsetind: sg.indices)
                {
                    uru.moulprp.PlDrawableSpans.PlIcicle ss = ds.icicles[subsetind];
                    Uruobjectref matrefb = ds.materials.get(ss.parent.parent.materialindex);
                    uru.moulprp.x0007Material mat = prp.findObjectWithRef(matrefb).castTo();
                    uru.moulprp.x0006Layer layer = prp.findObjectWithRef(mat.layerrefs.get(0)).castTo();
                    //matclone.layerrefs.get(0)

                    //found it, now add a material and change ss.materialindex to point to it.
                    //int width=1024; int height=1024;
                    int width=512; int height=512;
                    PrpRootObject map = auto.hackFactory.createAndAddDynamicTextMap(prp, "book"+book+"DynTexture", width, height);
                    map.markAsChanged();
                    uru.moulprp.x0007Material matclone = mat.deepClone();
                    uru.moulprp.x0006Layer layerclone = layer.deepClone();
                    layerclone.texture = map.getref();
                    Uruobjectref layerref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plLayer, "book"+book+"Layer", prp);
                    matclone.layerrefs.get(0).shallowCopyFrom(layerref);
                    Uruobjectref matref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.hsGMaterial, "book"+book+"Mat", prp);

                    ss.parent.parent.materialindex = ds.addMaterial(matref);

                    prp.extraobjects.add(PrpRootObject.createFromDescAndObject(matref.xdesc, matclone));
                    prp.extraobjects.add(PrpRootObject.createFromDescAndObject(layerref.xdesc, layerclone));

                    //create pythonfilemod to get a python hook to these objects.
                    AutoMod_Shared.CreatePythonfilemodReference(prp,"dustReltoDynCovers",map.getref(),8,"dustBook"+book);


                    //resize the uv coords by doubling them. so we only need a quarter of the .jpg file.
                    uru.moulprp.PlDrawableSpans.PlGBufferGroup bg = ds.groups[ss.parent.groupIdx];
                    uru.moulprp.PlDrawableSpans.PlGBufferGroup.SubMesh submesh = bg.submeshes[ss.parent.VBufferIdx];
                    uru.moulprp.PlDrawableSpansEncoders.DecompressedPotsVertices verts = submesh.decompressAllAndSave(bg.fformat);
                    for(int j=ss.parent.VStartIdx;j<ss.parent.VStartIdx+ss.parent.VLength;j++)
                    {
                        /*for(float[] fs: verts.uvss[j])
                        {
                            fs[0] = fs[0]*2.0f;
                            fs[1] = fs[1]*2.0f;
                        }*/
                        for(float[][] fss: verts.uvss)
                        {
                            fss[0][j] = fss[0][j]*2.0f;
                            fss[1][j] = fss[1][j]*2.0f;
                        }
                    }
                    //byte[] newrawdata = verts.compileall();
                    //submesh._rawdata = newrawdata;
                    //uru.moulprp.PlDrawableSpansEncoders.DecompressedPotsVertices verts2 = submesh.decompressAll(bg.fformat);
                    m.msg("Done expanding uv coords for: "+ds.toString()+"  "+Integer.toString(ss.parent.VBufferIdx)+"  "+Integer.toString(ss.parent.VStartIdx)+"  "+Integer.toString(ss.parent.VLength));

                }
            }
        }

    }

}
