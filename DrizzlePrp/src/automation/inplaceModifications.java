/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import uru.moulprp.*;
import shared.FileUtils;
import uru.context;
import uru.Bytestream;
import shared.Bytes;
import java.io.File;

public class inplaceModifications
{
    public static void translateAllObjects(String filename, String outfolder, float x, float y, float z)
    {
        //info:
        //CoordinateInterface's localToWorlds seems to override the PlDrawInterface's subset's localToWorld
        //ones to do yet: clustergroups, boundinterface, boundingbox
        
        //read file
        byte[] filedata = FileUtils.ReadFile(filename);
        context c = context.createFromBytestream(new Bytestream(filedata));
        c.curFile = filename;
        prpfile prp = uru.moulprp.prpprocess.ProcessAllObjects(c,true); //read raw
        prp.filename = filename;
        
        //create matrix
        Transmatrix translation = Transmatrix.createFromVector(x, y, z);
        Transmatrix invtranslation = Transmatrix.createFromVector(-x, -y, -z);
        Vertex translationvertex = Vertex.createFromFloats(x, y, z);
        
        //move drawable spans.
        PrpRootObject[] drawablespans = prp.FindAllObjectsOfType(Typeid.plDrawableSpans);
        for(PrpRootObject obj: drawablespans)
        {
            obj.hasChanged = true; //make sure to write new version.
            PlDrawableSpans drawspan = obj.castTo();
            
            if(drawspan.subsetcount>0)
            {
                //drawspan.xLocalBounds.transform(translation);
                //drawspan.xWorldBounds.transform(translation);
                //drawspan.xMaxWorldBounds.transform(translation);
            }
            for(int i=0;i<drawspan.subsets.length;i++)
            {
                //drawspan.subsets[i].localBounds.transform(translation);
                //drawspan.subsets[i].worldBounds.transform(translation);
            }
            
            for(int i=0;i<drawspan.localToWorlds.length;i++)
            {
                int dummy=0;
                //drawspan.localToWorlds[i] = translation.mult(drawspan.localToWorlds[i]);
            }
            for(int i=0;i<drawspan.worldToLocals.length;i++)
            {
                int dummy=0;
                //drawspan.worldToLocals[i] = drawspan.worldToLocals[i].mult(invtranslation);
            }
            
            for(int i=0;i<drawspan.subsets.length;i++)
            {
                
                /*boolean hascoordinterface = false;
                for(PrpRootObject drawobj: prp.FindAllObjectsOfType(Typeid.plDrawInterface))
                {
                    x0016DrawInterface drawint = drawobj.castTo();
                    for(x0016DrawInterface.SubsetGroup subgrp: drawint.subsetgroups)
                    {
                        if(obj.header.desc.equals(subgrp.span.xdesc) && subgrp.subsetgroupindex==drawspan.subsets[i].meshindex)
                        {
                            //found a drawinterface that uses this group. Try to find a coordinate interface now.
                            for(PrpRootObject scobj: prp.FindAllObjectsOfType(Typeid.plSceneObject))
                            {
                                x0001Sceneobject so = scobj.castToSceneObject();
                                if(drawobj.header.desc.equals(so.spaninfo.xdesc))
                                {
                                    if(so.regioninfo.xdesc!=null)
                                    {
                                        hascoordinterface = true;
                                    }
                                }
                            }
                            
                        }
                    }
                }
                if(hascoordinterface) continue;*/
                
                //moves e.g. tree trunks.
                drawspan.subsets[i].localToWorld = translation.mult(drawspan.subsets[i].localToWorld);
                drawspan.subsets[i].worldToLocal = drawspan.subsets[i].worldToLocal.mult(invtranslation);
            }
            
            /*drawspan.matrixsetcount = 1;
            drawspan.localToWorlds = new Transmatrix[1];
            drawspan.localToWorlds[0] = translation;
            drawspan.worldToLocals = new Transmatrix[1];
            drawspan.worldToLocals[0] = invtranslation;
            drawspan.localToBones = new Transmatrix[1];
            drawspan.localToBones[0] = translation;
            drawspan.boneToLocals = new Transmatrix[1];
            drawspan.boneToLocals[0] = invtranslation;
            for(int i=0;i<drawspan.subsets.length;i++)
            {
                drawspan.subsets[i].blendflag = 1;
                drawspan.subsets[i].blendindex = 0;
            }*/
        }
        
        //move coordinate interfaces.
        PrpRootObject[] coordinateinterfaces = prp.FindAllObjectsOfType(Typeid.plCoordinateInterface);
        for(PrpRootObject obj: coordinateinterfaces)
        {
            obj.hasChanged = true;
            x0015CoordinateInterface ci = obj.castTo();
            
            //moves e.g. spawnpoint
            ci.localToWorld = translation.mult(ci.localToWorld);
            ci.worldToLocal = ci.worldToLocal.mult(invtranslation);
            
            //moves e.g. ground
            ci.localToParent = translation.mult(ci.localToParent);
            ci.parentToLocal = ci.parentToLocal.mult(invtranslation);
        }
        
        //move physicals
        PrpRootObject[] physicals = prp.FindAllObjectsOfType(Typeid.plHKPhysical);
        for(PrpRootObject obj: physicals)
        {
            obj.hasChanged = true;
            PlHKPhysical phys = obj.castTo();
            
            /*boolean hascoordinterface = false;
            for(PrpRootObject scobj: prp.FindAllObjectsOfType(Typeid.plSceneObject))
            {
                x0001Sceneobject so = scobj.castToSceneObject();
                PrpRootObject siminterface = prp.findObjectWithRef(so.animationinfo);
                if(siminterface==null) continue;
                x001CSimulationInterface simint = siminterface.castTo();
                if(obj.header.desc.equals(simint.physical.xdesc))
                {
                    if(so.regioninfo.xdesc!=null)
                    {
                        hascoordinterface = true;
                        break;
                    }
                }
            }
            if(hascoordinterface) continue;*/

            
            if(phys.havok.mass.equals(Flt.zero()))
            {
                int dummy=0;
                phys.havok.transformVertices(translation);
            }
            else
            {
                int dummy=0;
                phys.havok.position = phys.havok.position.add(Vertex.createFromFloats(x, y, z));
            }
            
            //phys.havok.position = phys.havok.position.add(Vertex.createFromFloats(x, y, z));
            
            //move e.g. water detection
            //phys.havok.transformVertices(translation);
        }
        
        //wavesets
        for(PrpRootObject obj: prp.FindAllObjectsOfType(Typeid.plWaveSet7))
        {
            obj.hasChanged = true;
            PlWaveSet7 waveset = obj.castTo();

            //only u7 needs to be changed.  u8 and u13 would seem to be relevant, but I didn't need them.
            waveset.sub1.u7 = waveset.sub1.u7.add(z);
            //waveset.sub1.u8 = waveset.sub1.u8.add(translationvertex);
            //waveset.sub1.u13 = waveset.sub1.u13.add(translationvertex);
            
            int dummy=0;
        }
        
        //viewfacemodifiers
        for(PrpRootObject obj: prp.FindAllObjectsOfType(Typeid.plViewFaceModifier))
        {
            obj.hasChanged = true;
            
            PlViewFaceModifier vfm = obj.castTo();
            vfm.LocalToParent = translation.mult(vfm.LocalToParent);
            vfm.ParentToLocal = vfm.ParentToLocal.mult(invtranslation);
            //vfm.
        }
        
        //save changes.
        String filename2 = outfolder+"/dat/"+new File(filename).getName();
        Bytes result = prp.saveAsBytes();
        FileUtils.WriteFile(filename2, result);
                
    }
    public static void translateAllObjects(PrpRootObject[] objects)
    {
        
    }
}
