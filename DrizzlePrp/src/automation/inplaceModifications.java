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
import shared.m;
import org.apache.commons.math.linear.RealMatrix;
import org.apache.commons.math.linear.RealMatrixImpl;
import java.util.Vector;

public class inplaceModifications
{
    public static void atranslateAllObjects(String filename, String outfolder, float x, float y, float z)
    {
        
        //read file
        byte[] filedata = FileUtils.ReadFile(filename);
        File f = new File(filename);
        context c = context.createFromBytestream(new Bytestream(filedata));
        c.curFile = filename;
        prpfile prp = uru.moulprp.prpprocess.ProcessAllObjects(c,true); //read raw
        prp.filename = filename;
        
        //hacks
        if(f.getName().toLowerCase().equals("ahnysphere01_district_sphere01.prp"))
        {
            //perhaps the way to automate this is: drawinterfaces that use a layeranimation and don't already have a coordinateinterface, need one created.
            PrpRootObject outtide = prp.findObject("PoolSurfaceOuterTide", Typeid.plSceneObject);
            PrpRootObject intide = prp.findObject("PoolSurfaceInnerTide", Typeid.plSceneObject);
            hackFactory.createAndAddCoordinateInterface(prp, outtide);
            hackFactory.createAndAddCoordinateInterface(prp, intide);
            prp.mergeExtras();
            
            //translate the swimdetectregion, so it is below the spawnpoints but still above the swimsurfaces.
            //info: current swimdetectregion01 z:-148.52159(bottom) to -48.52159(top)
            //              spawnpoints: -84.7542 -82.03227 -81.3682 -81.43706 -89.75337 (and some duplicates) (lowest quab spawn is -85.97157)
            //              swimsurfaces: -91.871 -100.0 (and some duplicates)
            //So, -42 will take the top of the swimregion below the spawnpoints, but above the swimsurfaces.
            PrpRootObject sdrroot = prp.findObject("SwimDetectRegion01", Typeid.plHKPhysical);
            PlHKPhysical sdr = sdrroot.castTo();
            Transmatrix translation = Transmatrix.createFromVector(0, 0, -42);
            sdr.havok.transformVertices(translation);
        }
        
        //translate...
        translateAllObjects(prp, x, y ,z);
        
        //save changes.
        String filename2 = outfolder+"/dat/"+new File(filename).getName();
        Bytes result = prp.saveAsBytes();
        FileUtils.WriteFile(filename2, result);
        
    }

    public static void translateAllObjects(prpfile prp, float x, float y, float z)
    {
        //info:
        //CoordinateInterface's localToWorlds seems to override the PlDrawInterface's subset's localToWorld
        //ones to do yet: clustergroups, boundinterface, boundingbox
        
        //create matrix
        Transmatrix translation = Transmatrix.createFromVector(x, y, z);
        Transmatrix invtranslation = Transmatrix.createFromVector(-x, -y, -z);
        Vertex translationvertex = Vertex.createFromFloats(x, y, z);
        
        //find groups of things
        PrpRootObject[] coordinateinterfaces = prp.FindAllObjectsOfType(Typeid.plCoordinateInterface);
        PrpRootObject[] drawablespans = prp.FindAllObjectsOfType(Typeid.plDrawableSpans);
        PrpRootObject[] physicals = prp.FindAllObjectsOfType(Typeid.plHKPhysical);
        PrpRootObject[] clustergroups = prp.FindAllObjectsOfType(Typeid.plClusterGroup);
        PrpRootObject[] viewfacemodifiers = prp.FindAllObjectsOfType(Typeid.plViewFaceModifier);
        PrpRootObject[] sceneobjects = prp.FindAllObjectsOfType(Typeid.plSceneObject);
        PrpRootObject[] wavesets = prp.FindAllObjectsOfType(Typeid.plWaveSet7);
        PrpRootObject[] dynenvmaps = prp.FindAllObjectsOfType(Typeid.plDynamicEnvMap);
        PrpRootObject[] occluders = prp.FindAllObjectsOfType(Typeid.plOccluder);
        PrpRootObject[] softvolsimps = prp.FindAllObjectsOfType(Typeid.plSoftVolumeSimple);

        //animations
        PrpRootObject[] layeranims = prp.FindAllObjectsOfType(Typeid.plLayerAnimation);
        PrpRootObject[] anims = prp.FindAllObjectsOfType(Typeid.plATCAnim);
        PrpRootObject[] mastermods = prp.FindAllObjectsOfType(Typeid.plAGMasterMod);
        PrpRootObject[] agmods = prp.FindAllObjectsOfType(Typeid.plAGModifier);
        /*for(PrpRootObject obj: prp.FindAllObjectsOfType(Typeid.plLayer))
        {
            x0006Layer o = obj.castTo();
            for(Flt flt: shared.FindAllDescendants.FindAllDescendantsByClass(Flt.class, obj))
            {
                if(flt.approxequals(8.3f, 1))
                {
                    int dummy=0;
                }
            }
            obj.hasChanged = true;
            o.matrix = translation.mult(o.matrix);
        }
        for(PrpRootObject obj: prp.FindAllObjectsOfType(Typeid.plDynaRippleMgr))
        {
            obj.castTo();
            for(Flt flt: shared.FindAllDescendants.FindAllDescendantsByClass(Flt.class, obj))
            {
                if(flt.approxequals(8.3f, 1))
                {
                    int dummy=0;
                }
            }
        }
        for(PrpRootObject obj: mastermods)
        {
            obj.hasChanged = true;
            PlAGMasterMod anim = obj.castTo();

            for(Flt flt: shared.FindAllDescendants.FindAllDescendantsByClass(Flt.class, anim))
            {
                if(flt.approxequals(8.3f, 1))
                {
                    int dummy=0;
                }
            }
            
            
            Vector<PrpController.uk> uks = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.uk.class, anim);
            for(PrpController.uk u: uks)
            {
                u.u1.addModify(translationvertex);
            }
            
            Vector<PrpController.plCompoundPosController> pcs = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.plCompoundPosController.class, anim);
            for(PrpController.plCompoundPosController pc: pcs)
            {
                pc.pos3.xkeylist.keys[0].value.addModify(z);
                pc.pos3.xkeylist.keys[1].value.addModify(z);
                int dummy=0;
            }
            
            Vector<PrpController.plSimplePosController> spcs = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.plSimplePosController.class, anim);
            for(PrpController.plSimplePosController pc: spcs)
            {
                pc.xpoint3controller.xkeylist.keys[0].value.addModify(translationvertex);
                pc.xpoint3controller.xkeylist.keys[1].value.addModify(translationvertex);
                int dummy=0;
            }
        }
        for(PrpRootObject obj: agmods)
        {
            obj.hasChanged = true;
            PlAGModifier anim = obj.castTo();
            for(Flt flt: shared.FindAllDescendants.FindAllDescendantsByClass(Flt.class, anim))
            {
                if(flt.approxequals(8.3f, 1))
                {
                    int dummy=0;
                }
            }
            
            
            Vector<PrpController.uk> uks = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.uk.class, anim);
            for(PrpController.uk u: uks)
            {
                u.u1.addModify(translationvertex);
            }
            
            Vector<PrpController.plCompoundPosController> pcs = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.plCompoundPosController.class, anim);
            for(PrpController.plCompoundPosController pc: pcs)
            {
                pc.pos3.xkeylist.keys[0].value.addModify(z);
                pc.pos3.xkeylist.keys[1].value.addModify(z);
                int dummy=0;
            }
            
            Vector<PrpController.plSimplePosController> spcs = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.plSimplePosController.class, anim);
            for(PrpController.plSimplePosController pc: spcs)
            {
                pc.xpoint3controller.xkeylist.keys[0].value.addModify(translationvertex);
                pc.xpoint3controller.xkeylist.keys[1].value.addModify(translationvertex);
                int dummy=0;
            }
        }
        for(PrpRootObject obj: layeranims)
        {
            obj.hasChanged = true;
            PlLayerAnimation anim = obj.castTo();
            
            for(Flt flt: shared.FindAllDescendants.FindAllDescendantsByClass(Flt.class, anim))
            {
                if(flt.approxequals(8.3f, 1))
                {
                    int dummy=0;
                }
            }
            
            Vector<PrpController.plMatrix44Controller> mcs = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.plMatrix44Controller.class, anim);
            for(PrpController.plMatrix44Controller mc: mcs)
            {
                mc.keys[0].value = translation.mult(mc.keys[0].value);
                mc.keys[1].value = translation.mult(mc.keys[1].value);
            }
            
            Vector<PrpController.uk> uks = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.uk.class, anim);
            for(PrpController.uk u: uks)
            {
                u.u1.addModify(translationvertex);
            }
            
            Vector<PrpController.plCompoundPosController> pcs = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.plCompoundPosController.class, anim);
            for(PrpController.plCompoundPosController pc: pcs)
            {
                pc.pos3.xkeylist.keys[0].value.addModify(z);
                pc.pos3.xkeylist.keys[1].value.addModify(z);
                int dummy=0;
            }
            
            Vector<PrpController.plSimplePosController> spcs = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.plSimplePosController.class, anim);
            for(PrpController.plSimplePosController pc: spcs)
            {
                pc.xpoint3controller.xkeylist.keys[0].value.addModify(translationvertex);
                pc.xpoint3controller.xkeylist.keys[1].value.addModify(translationvertex);
                int dummy=0;
            }
        }*/
        
        //do the animations, through the sceneobjects.
        for(PrpRootObject obj: sceneobjects)
        {
            x0001Sceneobject o = obj.castTo();
            for(Uruobjectref ref: o.objectrefs2)
            {
                if(ref.hasref() && ref.xdesc.objecttype==Typeid.plAGMasterMod)
                {
                    PlAGMasterMod mm = prp.findObjectWithRef(ref).castTo();
                    for(Uruobjectref ref2: mm.ATCAnim)
                    {
                        if(ref2.hasref() && ref2.xdesc.objecttype==Typeid.plATCAnim)
                        {
                            //found an ATCAnim, translate it.
                            PrpRootObject animroot = prp.findObjectWithRef(ref2);
                            PlATCAnim anim = animroot.castTo();
                            boolean hasparent = findCIParent(obj.getref(), coordinateinterfaces) != null;
                            
                            for(int i=0;i<anim.parent.effectcount;i++)
                            {
                                PrpTaggedObject p1 = anim.parent.effects[i*2+0];
                                PrpTaggedObject p2 = anim.parent.effects[i*2+1];
                                
                                if(p1.type==Typeid.plMatrixChannelApplicator) //make sure we're dealing with the right type.
                                {
                                    if(!hasparent)
                                    {
                                        Vector<PlAGAnim.plMatrixControllerChannel> mccs = shared.FindAllDescendants.FindAllDescendantsByClass(PlAGAnim.plMatrixControllerChannel.class, anim);
                                        for(PlAGAnim.plMatrixControllerChannel mcc: mccs)
                                        {
                                            m.msg("Changing ATCAnim plMatrixControllerChannel's uk("+animroot.toString()+") from:"+mcc.u3.u1.toString());
                                            //change the uk
                                            animroot.hasChanged = true;
                                            mcc.u3.u1.addModify(translationvertex);
                                            //mcc.u3.u1.addModify(Vertex.createFromFloats(0, 0, -1));
                                        }

                                        Vector<PrpController.plCompoundPosController> pcs = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.plCompoundPosController.class, anim);
                                        for(PrpController.plCompoundPosController pc: pcs)
                                        {
                                            m.msg("Changing ATCAnim plCompoundPosController's("+animroot.toString()+") z from:"+pc.pos3.xkeylist.keys[0].value.toString());
                                            animroot.hasChanged = true;
                                            for(PrpController.hsScalarKey key: pc.pos3.xkeylist.keys)
                                            {
                                                key.value.addModify(z);
                                            }
                                            //pc.pos3.xkeylist.keys[0].value.addModify(z);
                                            //pc.pos3.xkeylist.keys[1].value.addModify(z);
                                            int dummy=0;
                                        }

                                        Vector<PrpController.plSimplePosController> spcs = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.plSimplePosController.class, anim);
                                        for(PrpController.plSimplePosController pc: spcs)
                                        {
                                            m.msg("Changing ATCAnim plSimplePosController("+animroot.toString()+") from:"+pc.xpoint3controller.xkeylist.keys[0].value.toString());
                                            animroot.hasChanged = true;
                                            for(PrpController.hsPoint3Key key: pc.xpoint3controller.xkeylist.keys)
                                            {
                                                key.value.addModify(translationvertex);
                                            }
                                            //pc.xpoint3controller.xkeylist.keys[0].value.addModify(translationvertex);
                                            //pc.xpoint3controller.xkeylist.keys[1].value.addModify(translationvertex);
                                            int dummy=0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        //for(PrpRootObject obj: anims)
        //{
        //    obj.hasChanged = true;
        //    PlATCAnim anim = obj.castTo();
            
            /*for(Flt flt: shared.FindAllDescendants.FindAllDescendantsByClass(Flt.class, anim))
            {
                if(flt.approxequals(8.3f, 1))
                {
                    int dummy=0;
                }
            }*/
            //fixes rotate lever. breaks door lever and rotate button.
            /*Vector<PrpController.uk> uks = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.uk.class, anim);
            for(PrpController.uk u: uks)
            {
                u.u1.addModify(translationvertex);
            }*/
            
            //find a parent if it exists.
            //Uruobjectref parent = findCIParent(anim., coordinateinterfaces);
            
            
            /*Vector<PlAGAnim.plMatrixControllerChannel> mccs = shared.FindAllDescendants.FindAllDescendantsByClass(PlAGAnim.plMatrixControllerChannel.class, anim);
            for(PlAGAnim.plMatrixControllerChannel mcc: mccs)
            {
                uruobj o2 = mcc.u2.prpobject.object;
                if(o2 instanceof PrpController.plTMController)
                {
                    PrpController.plTMController o3 = (PrpController.plTMController)o2;
                    if(o3.type1==0 && o3.type2==3 && o3.type3==0)
                    {
                        //change the uk
                        //mcc.u3.u1.addModify(translationvertex);
                        //mcc.u3.u1.addModify(Vertex.createFromFloats(0, 0, -1));
                    }
                }
            }*/
            
            
            /*Vector<PrpController.plCompoundPosController> pcs = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.plCompoundPosController.class, anim);
            for(PrpController.plCompoundPosController pc: pcs)
            {
                pc.pos3.xkeylist.keys[0].value.addModify(z);
                pc.pos3.xkeylist.keys[1].value.addModify(z);
                int dummy=0;
            }*/
            
            /*Vector<PrpController.plSimplePosController> spcs = shared.FindAllDescendants.FindAllDescendantsByClass(PrpController.plSimplePosController.class, anim);
            for(PrpController.plSimplePosController pc: spcs)
            {
                pc.xpoint3controller.xkeylist.keys[0].value.addModify(translationvertex);
                pc.xpoint3controller.xkeylist.keys[1].value.addModify(translationvertex);
                int dummy=0;
            }*/
            
        //}
        
        //move soft volume simples, works! e.g. visible regions in the lighthouse.
        for(PrpRootObject obj: softvolsimps)
        {
            //m.warn("soft volume simples not tested, because of no examples");
            obj.hasChanged = true;
            PlSoftVolumeSimple sv = obj.castTo();
            if(sv.prpvolumeisect.isect instanceof PrpVolumeIsect.PlConvexIsect)
            {
                PrpVolumeIsect.PlConvexIsect ci = (PrpVolumeIsect.PlConvexIsect)sv.prpvolumeisect.isect;
                for(PrpVolumeIsect.PlConvexPlane plane: ci.planes)
                {
                    plane.point.addModify(translationvertex);
                    plane.distance = Flt.createFromJavaFloat(plane.normal.dot(plane.point));
                    plane.scaledDist = Flt.createFromJavaFloat(plane.scaledNormal.dot(plane.point));
                }
            }
        }
        
        //move occluders, fixes the problem with leaves sometimes disappearing, and the lighthouse disappearing from some places.
        for(PrpRootObject obj: occluders)
        {
            obj.hasChanged = true;
            PlOccluder occ = obj.castTo();
            occ.fWorldBounds.transform(translation);
            for(PlOccluder.plCullPoly cullpoly: occ.blocks)
            {
                cullpoly.fCenter = cullpoly.fCenter.add(translationvertex);
                for(Vertex v: cullpoly.vertices)
                {
                    v.addModify(translationvertex);
                }
            }
        }
        
        //move dynamic env maps
        for(PrpRootObject obj: dynenvmaps)
        {
            m.warn("Uncertain if dynamicenvmaps are translated right; need example.");
            obj.hasChanged = true;
            PlDynamicEnvMap dem = obj.castTo();
            for(Flt flt: shared.FindAllDescendants.FindAllDescendantsByClass(Flt.class, dem))
            {
                if(flt.approxequals(8.3f, 1))
                {
                    int dummy=0;
                }
            }
            dem.fPos = dem.fPos.add(translationvertex);
        }
        
        //move drawable spans.
        for(PrpRootObject obj: drawablespans)
        {
            obj.hasChanged = true; //make sure to write new version.
            PlDrawableSpans drawspan = obj.castTo();
            
            for(Flt flt: shared.FindAllDescendants.FindAllDescendantsByClass(Flt.class, drawspan))
            {
                if(flt.approxequals(8.3f, 1))
                {
                    int dummy=0;
                }
            }
            //do the bounds
            if(drawspan.subsetcount>0)
            {
                //drawspan.xLocalBounds.transform(translation);
                drawspan.xWorldBounds.transform(translation);
                drawspan.xMaxWorldBounds.transform(translation);
            }
            for(int i=0;i<drawspan.subsets.length;i++)
            {
                //drawspan.subsets[i].localBounds.transform(translation);
                PlDrawableSpans.SpanSubset ss = drawspan.subsets[i];
                drawspan.subsets[i].worldBounds.transform(translation);
            }
            if(drawspan.xspacetree!=null) //fixes e.g. treetrunks
            {
                for(PlDrawableSpans.x0240plSpaceTree.Nodes node: drawspan.xspacetree.nodes2)
                {
                    node.boundingbox.transform(translation);
                }
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
                
                //this block breaks, e.g. trees aren't translated.  So not everything with a coordinterface shouldn't be translated.
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
        for(PrpRootObject obj: coordinateinterfaces)
        {
            obj.hasChanged = true;
            x0015CoordinateInterface ci = obj.castTo();
            
            for(Flt flt: shared.FindAllDescendants.FindAllDescendantsByClass(Flt.class, ci))
            {
                if(flt.approxequals(8.3f, 1))
                {
                    int dummy=0;
                }
            }
            //find a parent if it exists.
            Uruobjectref parent = findCIParent(ci, coordinateinterfaces);
            
            //moves e.g. spawnpoint
            ci.localToWorld = translation.mult(ci.localToWorld);
            ci.worldToLocal = ci.worldToLocal.mult(invtranslation);
            
            //moves e.g. ground
            if(parent==null)
            {
                ci.localToParent = translation.mult(ci.localToParent);
                ci.parentToLocal = ci.parentToLocal.mult(invtranslation);
            }
            else
            {
                //do nothing.
                int dummy=0;
            }
        }
        
        //move physicals
        for(PrpRootObject obj: physicals)
        {
            obj.hasChanged = true;
            PlHKPhysical phys = obj.castTo();
            
            if(obj.header.desc.objectname.toString().toLowerCase().equals("swimdetectregion01"))
            {
                int dummy=0;
            }
            
            //this next section breaks, e.g. the ground.  So not everything with a coordinterface shouldn't be translated.
            /*boolean hascoordinterface = false;
            for(PrpRootObject scobj: sceneobjects)
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
            //if(true)
            {
                //e.g. ground
                int dummy=0;
                phys.havok.transformVertices(translation);
            }
            else
            {
                //e.g. pressureregions, savecloths
                int dummy=0;
                phys.havok.position = phys.havok.position.add(Vertex.createFromFloats(x, y, z));
            }
            
            //phys.havok.position = phys.havok.position.add(Vertex.createFromFloats(x, y, z));
            
            //move e.g. water detection
            //phys.havok.transformVertices(translation);
        }
        
        //wavesets, works!  see water
        for(PrpRootObject obj: wavesets)
        {
            obj.hasChanged = true;
            PlWaveSet7 waveset = obj.castTo();

            //only u7 needs to be changed.  u8 and u13 would seem to be relevant, but I didn't need them.
            waveset.sub1.u7 = waveset.sub1.u7.add(z);
            m.warn("Should we be changing u8 and u13, too?");
            waveset.sub1.u8 = waveset.sub1.u8.add(translationvertex);
            waveset.sub1.u13 = waveset.sub1.u13.add(translationvertex);
            
            int dummy=0;
        }
        
        //viewfacemodifiers, works!  see glow in windows
        for(PrpRootObject obj: viewfacemodifiers)
        {
            obj.hasChanged = true;
            
            PlViewFaceModifier vfm = obj.castTo();
            vfm.LocalToParent = translation.mult(vfm.LocalToParent);
            vfm.ParentToLocal = vfm.ParentToLocal.mult(invtranslation);
            
            vfm.offset = vfm.offset.add(translationvertex); //this doesn't seem to do anything.
            
            vfm.xMaxBounds.transform(translation); //this doesn't seem needed.
        }
        
        //clustergroups, works! e.g. grass
        for(PrpRootObject obj: clustergroups)
        {
            obj.hasChanged = true;
            PlClusterGroup cg = obj.castTo();
            
            //translate vertices
            /*for(PlClusterGroup.FData fdata: cg.fData2)
            {
                fdata.v1 = fdata.v1.add(translationvertex);
            }*/
 
            //move fL2W
            for(PlClusterGroup.plCluster cluster: cg.subclustergroups)
            {
                for(PlClusterGroup.plCluster.plSpanInstance inst: cluster.fInstances)
                {
                    double[][] data = Flt.convertFltArrayToDoubleArray(inst.fL2W);
                    Transmatrix mat = Transmatrix.createFrom3x4Array(data);
                    //RealMatrix mat = Transmatrix.createMatrixFromDoubleArray(data);
                    //inst.fL2W
                    Transmatrix mat2 = translation.mult(mat);
                    Flt[][] fL2Wnew = mat2.convertTo3x4FltArray();
                    inst.fL2W = fL2Wnew;
                }
            }
        }
        
                
    }
    public static void translateAllObjects(PrpRootObject[] objects)
    {
        
    }
    
    public static Uruobjectref findCIParent(x0015CoordinateInterface ci, PrpRootObject[] coordinateinterfaces)
    {
        Uruobjectref so = ci.parent.sceneobject;
        return findCIParent(so, coordinateinterfaces);
    }
    public static Uruobjectref findCIParent(Uruobjectref sceneobject, PrpRootObject[] coordinateinterfaces)
    {
        for(PrpRootObject obj: coordinateinterfaces)
        {
            x0015CoordinateInterface ci2 = obj.castTo();
            for(Uruobjectref ref: ci2.children)
            {
                if(ref.equals(sceneobject)) return ref;
            }
        }
        return null;
    }
}
