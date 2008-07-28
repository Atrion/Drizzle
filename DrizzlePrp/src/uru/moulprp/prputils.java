/*
    Drizzle - A general Myst tool.
    Copyright (C) 2008  Dustin Bernard.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/ 

package uru.moulprp;

import uru.context; import shared.readexception;
import uru.Bytestream;
import uru.Bytedeque;
import shared.m;
import uru.b;
import uru.e;
import java.util.Vector;
import shared.FileUtils;
import shared.readexception;
import java.io.File;
import shared.Bytes;

/**
 *
 * @author user
 */
public class prputils
{

    
    public static void ThisIsJustATemplate(context c)
    {
        //Bytestream data = c.in;

        PrpHeader header = new PrpHeader(c);
        
        //do header work.
        
        //process the object index, which is *not* a part of this struct.
        PrpObjectIndex objectindex = new PrpObjectIndex(c.Fork(new Bytestream(c.in,header.offsetToObjectIndex)));
        int numobjecttypes = objectindex.indexCount;
        for(int i_type=0;i_type<numobjecttypes;i_type++)
        {
            Typeid type = objectindex.types[i_type].type;
            int numObjects = objectindex.types[i_type].objectcount;
            
            //do per-type work.
            
            for(int j_obj=0;j_obj<numObjects;j_obj++)
            {
                Uruobjectdesc desc = objectindex.types[i_type].descs[j_obj].desc;
                int offset = objectindex.types[i_type].descs[j_obj].offset;
                int size = objectindex.types[i_type].descs[j_obj].size;
                
                //do per-object work.
                
            }
        }
    }

    public static String MakeObjectIndexReport(byte[] data)
    {
        context c = context.createFromBytestream(new Bytestream(data));
        
        StringBuilder report = new StringBuilder();
        
        PrpHeader header = new PrpHeader(c);
        
        //do header work.
        report.append("header:\n");
        report.append("    age:"+header.agename.toString()+"\n");
        report.append("    pagename:"+header.pagename.toString()+"\n");
        report.append("    pagetype:"+header.pagetype.toString()+"\n");
        report.append("    pageid:"+header.pageid.toString()+"\n");
        report.append("Object types:\n");
        
        //process the object index, which is *not* a part of this struct.
        PrpObjectIndex objectindex = new PrpObjectIndex(c.Fork(new Bytestream(c.in,header.offsetToObjectIndex)));
        int numobjecttypes = objectindex.indexCount;
        for(int i=0;i<numobjecttypes;i++)
        {
            Typeid type = objectindex.types[i].type;
            int numObjects = objectindex.types[i].objectcount;
            
            //do per-type work.
            report.append("    typeid:"+type.toString()+"\n");
            report.append("    Num objects:"+Integer.toString(numObjects)+"\n");
            
            for(int j=0;j<numObjects;j++)
            {
                Uruobjectdesc desc = objectindex.types[i].descs[j].desc;
                int offset = objectindex.types[i].descs[j].offset;
                int size = objectindex.types[i].descs[j].size;
                
                //do per-object work.
                report.append("        desc:"+desc.toString()+"\n");
                report.append("        offset:0x"+Integer.toHexString(offset)+"\n");
                report.append("        length:0x"+Integer.toHexString(size)+"\n");
                
            }
        }
        
        return report.toString();
    }
    
    public static prpfile ProcessAllMoul(byte[] data)
    {
        context c = context.createFromBytestream(new Bytestream(data));
        return ProcessAllMoul(c, false);
    }
    /*public static prpfile ProcessAll(context c)
    {
        if(c.readversion==6)
        {
            return ProcessAllMoul(c,false);
        }
        else if(c.readversion==3)
        {
            return ProcessPotsPrp(c);
        }
        m.err("processall: Unknown readversion.");
        return null;
    }*/
    public static prpfile ProcessAllMoul(context c, boolean reportProgress)
    {
        Bytestream data = c.in;

        PrpHeader header = new PrpHeader(c);
        
        //todo: this should be in the proper place, not here.
            //fix payiferen pageid conflict problem.
            if (header.agename.toString().toLowerCase().equals("payiferen"))
            {
                //_staticsettings.sequencePrefix = 0x63;
                c.sequencePrefix = 0x63;
            }
            else if (header.agename.toString().toLowerCase().equals("kveer"))
            {
                //_staticsettings.sequencePrefix = 0x62;
                c.sequencePrefix = 0x62;
            }
            else if (header.agename.toString().toLowerCase().equals("edertsogal"))
            {
                //_staticsettings.sequencePrefix = 0x61;
                c.sequencePrefix = 0x61;
            }

        //do header work.
        //_staticsettings.onHeaderLoaded(header);
        prpfile result = new prpfile();
        Vector<PrpRootObject> objects = new Vector<PrpRootObject>();
        result.header = header;
        
        //process the object index, which is *not* a part of this struct.
        PrpObjectIndex objectindex = new PrpObjectIndex(c.Fork(new Bytestream(data,header.offsetToObjectIndex)));
        //_staticsettings.onObjectIndexLoaded(objectindex);
        result.objectindex = objectindex;
        
        int numobjecttypes = objectindex.indexCount;
        for(int i=0;i<numobjecttypes;i++)
        {
            Typeid type = objectindex.types[i].type;
            //if(type==Typeid.plSceneObject) continue;
            int numObjects = objectindex.types[i].objectcount;
            
            //do per-type work.
            if(reportProgress) m.msg("type="+type.toString());
            for(int j=0;j<numObjects;j++)
            {
                Uruobjectdesc desc = objectindex.types[i].descs[j].desc;
                int offset = objectindex.types[i].descs[j].offset;
                int size = objectindex.types[i].descs[j].size;
                
                //do per-object work.
                if(!_staticsettings.doVisit(desc)) continue; //should we process this object?
                //rootobj object = null;
                PrpRootObject object = null;
                context stream = c.Fork(new Bytestream(data,offset));
                stream.curRootObject = desc;
                stream.curRootObjectOffset = offset;
                stream.curRootObjectSize = size;
                stream.curRootObjectEnd = offset+size;

                //process the object, which is *not* a part of this struct.
                //to disable an object type, simply comment out its "object=" line.
                _staticsettings.currentRootObj = desc; //used for reporting.
                boolean handled = true;
                switch(desc.objecttype)
                {
                    case plSceneNode:
                    case plSceneObject:
                    case plMipMap:
                    case plCubicEnvironMap:
                    case plLayer:
                    case hsGMaterial:
                    case plParticleSystem:
                    case plBoundInterface:
                    case plAudioInterface:
                    case plWinAudio:
                    case plCoordinateInterface:
                    case plDrawInterface:
                    case plSpawnModifier:
                    case plDrawableSpans:
                    case plDirectionalLightInfo:
                    case plOmniLightInfo:
                    case plPythonFileMod:
                    case plPointShadowMaster:
                    case plSimulationInterface:
                    case plViewFaceModifier:
                    case plSittingModifier:
                    case plStereizer:
                    case plSoundBuffer:
                    case plRandomSoundMod:
                    case plWin32StreamingSound:
                    case plWin32StaticSound:
                    case plParticleLocalWind:
                    case plParticleCollisionEffectDie:
                    case plExcludeRegionModifier:
                    case plCameraBrain1:
                    case plCameraBrain1_Avatar:
                    case plCameraBrain1_Fixed:
                    case plCameraBrain1_Circle:
                    case plCameraModifier1:
                    case plAGModifier:
                    case plOccluder:
                    case plDynamicTextMap:
                    case plParticleCollisionEffectBounce:
                    case plSpotLightInfo:
                    case plShadowCaster:
                    case plDirectShadowMaster:
                    case plRelevanceRegion:
                    case plSoftVolumeSimple:
                    case plResponderModifier:
                    case plParticleFlockEffect:
                    case plFadeOpacityMod:
                    case plClusterGroup:
                    case plVisRegion:
                    case plSoftVolumeUnion:
                    case plObjectInVolumeDetector:
                    case plObjectInBoxConditionalObject:
                    case plInterfaceInfoModifier:
                    case plVolumeSensorConditionalObject:
                    case plLogicModifier:
                    case plActivatorConditionalObject:
                    case plFacingConditionalObject:
                    case plOneShotMod:
                    case plAvLadderMod:
                    case plDynaFootMgr:
                    case plPickingDetector:
                    case plCameraRegionDetector:
                    case plHKPhysical:
                    case plSoftVolumeIntersect:
                    case plEAXListenerMod:
                    case plPhysicalSndGroup:
                    case plSeekPointMod:
                    case plRailCameraMod:
                    case plLayerAnimation:
                    case plATCAnim:
                    case plAGMasterMod:
                    case plPanicLinkRegion:
                    case plLineFollowMod:
                    case plMsgForwarder:
                    case plAnimEventModifier:
                    case plMultiStageBehMod:
                    case plImageLibMod:
                    case plLimitedDirLightInfo:
                    case plAgeGlobalAnim:
                    case plDynaPuddleMgr:
                    case plWaveSet7:
                    case plDynamicEnvMap:
                    case plRidingAnimatedPhysicalDetector:
                    case plGrassShaderMod:
                    case plDynamicCamMap:
                    case plSoftVolumeInvert:
                        try
                        {
                            object = new PrpRootObject(stream);
                        }catch(readexception e){}
                        break;
                    default:
                        //TODO: restore this line, it's just kind of annoying.
                        m.msg("unhandled object type:"+desc.objecttype.toString());
                        handled = false;
                        
                        //scan for string references in unparsed object:
                        if(_staticsettings.tryToFindReferencesInUnknownObjects)
                        {
                            Bytestream bs = new Bytestream(data,offset);
                            byte[] bytes = bs.readBytes(size);
                            //Urustring.attemptRecoveryScan(bytes);
                            for(int curbyte=0;curbyte<bytes.length;curbyte++)
                            {
                                if(b.ByteToInt32(bytes[curbyte])==0xF0)
                                {
                                    if (curbyte==0) continue;
                                    int l = b.ByteToInt32(bytes[curbyte-1]);
                                    if (curbyte+l>=bytes.length) continue;
                                    byte[] str = new byte[l];
                                    for(int i2=0;i2<l;i2++)
                                    {
                                        str[i2] = b.not(bytes[curbyte+i2+1]);
                                    }
                                    if(e.isGoodString(str))
                                    {
                                        _staticsettings.reportFoundUnknownReference(str);
                                    }
                                }
                            }
                        }
                        break;
                }
                if(object==null)
                {
                    handled = false;
                    //m.warn("Prp: object is null");
                }
                if(handled)
                {
                    objects.add(object);
                    
                    //if(object!=null && result!=null) result.objects.add(object);
                    int shortby = offset+size-stream.in.getAbsoluteOffset();
                    if(shortby!=0)
                    {
                        if(desc.objecttype!=Typeid.plHKPhysical)
                            m.msg("Prp: Object was not the expected size. It was off by:"+Integer.toString(shortby));
                    }
                }
                //_staticsettings.onObjectLoaded(object);
                
            }
        }
        
        m.msg("Process All was successful!");
        result.objects = uru.generics.convertVectorToArray(objects,PrpRootObject.class);
        return result;
        
    }

    public static void DumpObjects(byte[] data, Typeid typetodump)
    {
        context c = context.createFromBytestream(new Bytestream(data));
        
        PrpHeader header = new PrpHeader(c);
        
        //do header work.
        
        //process the object index, which is *not* a part of this struct.
        PrpObjectIndex objectindex = new PrpObjectIndex(c.Fork(new Bytestream(c.in,header.offsetToObjectIndex)));
        int numobjecttypes = objectindex.indexCount;
        for(int i=0;i<numobjecttypes;i++)
        {
            Typeid type = objectindex.types[i].type;
            int numObjects = objectindex.types[i].objectcount;
            
            //do per-type work.
            
            for(int j=0;j<numObjects;j++)
            {
                Uruobjectdesc desc = objectindex.types[i].descs[j].desc;
                int offset = objectindex.types[i].descs[j].offset;
                int size = objectindex.types[i].descs[j].size;
                
                //do per-object work.
                if(typetodump==null || desc.objecttype==typetodump)
                {
                    Bytestream bs = new Bytestream(c.in,offset);
                    byte[] bytes = bs.readBytes(size);
                    shared.FileUtils.WriteFile(_staticsettings.outputdir+desc.toString()+".dat", bytes);
                }
                
            }
        }
    }

    public static void ReportCrossLinks(byte[] data)
    {
        context c = context.createFromBytestream(new Bytestream(data));
        
        _staticsettings.reportReferences = true;
        _staticsettings.tryToFindReferencesInUnknownObjects = true;
        ProcessAllMoul(c,false);
        //String report = "Cross-Reference report:\n\n" + _staticsettings.referenceReport.toString() + "\n\nScanned Reference Report:\n\n" + _staticsettings.scannedReferenceReport.toString();
        String report = "howfound;fromname;fromtype;fromnumber;toname;totype;tonumber;topageid\n" + _staticsettings.referenceReport.toString() + _staticsettings.scannedReferenceReport.toString();
        FileUtils.WriteFile(_staticsettings.outputdir+"crosslinkreport.csv", report.getBytes());
        
    }
    
    //doesn't quite work right!
    public static void ReportDeep(byte[] data)
    {
        context c = context.createFromBytestream(new Bytestream(data));
        //_staticsettings.reportReferences = true;
        //_staticsettings.tryToFindReferencesInUnknownObjects = true;
        prpfile prp = prpprocess.ProcessAllObjects(c);
        uru.reflection.reflectionReportToFile(prp);
        //String report = "Cross-Reference report:\n\n" + _staticsettings.referenceReport.toString() + "\n\nScanned Reference Report:\n\n" + _staticsettings.scannedReferenceReport.toString();
        //FileUtils.WriteFile(_staticsettings.outputdir+"deepreport.txt", report.getBytes());
        
    }
    
    public static class Compiler
    {
        public static void RecompilePrp(byte[] data)
        {
            context c = context.createFromBytestream(new Bytestream(data));
            //c.outputVertices = true; //works but not used.
            //c.vertices = new Vector<java.lang.Float>(); //works but not used.
            
            
            //prpfile prp = ProcessAllMoul(c,false);
            prpfile prp = prpfile.createFromContext(c);
            
            Bytes fullbyte = RecompilePrp(prp);
            String filename = prp.header.agename.toString()+"_District_"+prp.header.pagename.toString()+".prp";
            FileUtils.WriteFile(_staticsettings.outputdir+filename, fullbyte);
        }
        
        public static Bytes RecompilePrp(prpfile prp)
        {
            //fix payiferen pageid conflict problem.
            /*if (prp.header.agename.toString().toLowerCase().equals("payiferen"))
            {
                _staticsettings.sequencePrefix = 0x63;
            }
            else if (prp.header.agename.toString().toLowerCase().equals("kveer"))
            {
                _staticsettings.sequencePrefix = 0x62;
            }
            else if (prp.header.agename.toString().toLowerCase().equals("edertsogal"))
            {
                _staticsettings.sequencePrefix = 0x61;
            }*/
            //else if (prp.header.agename.toString().toLowerCase().equals("marshscene"))
            //{
            //    _staticsettings.sequencePrefix = 96;
            //}
            //else if (prp.header.agename.toString().toLowerCase().equals("mountainscene"))
            //{
            //    _staticsettings.sequencePrefix = 95;
            //}

            //fix problem with materials(referenced from plDrawableSpans) that point to LayerAnimations.
            //fixMaterial(prp);
            

            ////count # of object types //we don't need to do this, since the old version doesn't have the count in the header.

            Bytedeque headerdeque = new Bytedeque();
            Bytedeque oideque = new Bytedeque();

            //calculate header size and create first part of header deque. in=prp out=headerdeque
            PrpHeader header = prp.header;
            headerdeque.writeInt(5);
            header.pageid.compile(headerdeque);
            //headerdeque.writeShort(header.pagetype);
            header.pagetype.compile(headerdeque);
            prp.header.agename.compile(headerdeque);
            byte[] districtbytes = {'D','i','s','t','r','i','c','t'};
            Urustring district = new Urustring(districtbytes);
            district.compile(headerdeque);
            prp.header.pagename.compile(headerdeque);
            headerdeque.writeShort((short)63);
            headerdeque.writeShort((short)12);
            headerdeque.writeInt(0);
            headerdeque.writeInt(8);
            //byte[] header1 = headerdeque.getAllBytes();
            //int headersize = header1.length + 12;
            //int curpos = headersize; //put our pointer to the end of the header.

            //filter and compile objects: in=prp out=compiledObjects,uncompiledObjects
            Vector<byte[]> compiledObjects = new Vector<byte[]>();
            Vector<PrpRootObject> uncompiledObjects = new Vector<PrpRootObject>();
            //java.util.Iterator<rootobj> iter = prp.objects.iterator();
            boolean haveEncounteredSceneNode = false;
            //while(iter.hasNext())
            int numobjs = prp.objects.length;
            for(int i=0;i<numobjs;i++)
            {
                //rootobj curobj = iter.next();
                PrpRootObject curobj = prp.objects[i];
                //if(i==494)
                //{
                //    i = 494;
                //}
                //Typeid type = null;
                //try{
                //Objheader alk = curobj.getHeader();
                //Uruobjectdesc dlk = alk.desc;
                //type = dlk.objecttype;
                Typeid type = curobj.header.desc.objecttype;
                //}catch(Exception e)
                //{
                //    int ai=0;
                //}
                //e.equals(null);}
                //int number = curobj.getHeader().desc.objectnumber;
                //String name = curobj.getHeader().desc.objectname.toString();
                //Pageid pageid = curobj.getHeader().desc.pageid;

                //handle normal objects
                if(isNormalObjectToBeIncluded(curobj.header.desc) || type==type.plSceneNode)
                {
                    uncompiledObjects.add(curobj);
                    
                    Bytedeque deque = new Bytedeque();
                    deque.curRootObject = curobj.header.desc;
                    if(type==type.plSceneNode) //handle scene node; there should only be one of these per prp.
                    {
                        e.ensure(haveEncounteredSceneNode==false);
                        haveEncounteredSceneNode = true;
                        curobj.header.compile(deque);
                        ((x0000Scenenode)curobj.prpobject.object).compileSpecial(deque); //uses the isNormalObjectToBeIncluded function.
                    }
                    else
                    {
                        curobj.header.compile(deque);
                        curobj.compile(deque);
                    }
                    byte[] dequedata = deque.getAllBytes();
                    compiledObjects.add(dequedata);
                }

                    
            }
            //e.ensure(haveEncounteredSceneNode==true); //only relevant to prps with a scene node, not BuiltIn and Textures.
            
            //calculate size of all objects, type count, count for each type. in=compiledObjects, uncompiledObjects out=sizeOfAllObjects,typeInfo.
            int sizeOfAllObjects = 0;
            int numObjects = uncompiledObjects.size(); e.ensure(numObjects==compiledObjects.size());
            Typeid lasttype = Typeid.nil; //since there should be nil rootobj we this will change on the first loop.
            int numTypes = 0;
            //Vector<Integer> typeCounts = new Vector<Integer>();
            //java.util.EnumMap<Typeid,Integer> typeInfo = new java.util.EnumMap<Typeid,Integer>();
            java.util.HashMap<Typeid,Integer> typeInfo = new java.util.HashMap<Typeid,Integer>();
            for(int i3=0;i3<numObjects;i3++)
            {
                sizeOfAllObjects += compiledObjects.get(i3).length;
                PrpRootObject curobj = uncompiledObjects.get(i3);
                if(curobj.header.objecttype!=lasttype) //if new type encountered...
                {
                    lasttype = curobj.header.objecttype;
                    numTypes++;
                    
                    //make a count entry for this new type
                    e.ensure(!typeInfo.containsKey(lasttype)); //we shouldn't already have this key.
                    typeInfo.put(lasttype, 1);
                }
                else
                {
                    //increment count for this type
                    //int oldcount = typeCounts.get(numTypes);
                    //typeCounts.set(numTypes, oldcount+1);
                    int oldcount = typeInfo.get(lasttype);
                    typeInfo.put(lasttype, oldcount + 1);
                }
            }
            
            //compile object index. in=headerdeque,oideque,uncompiledObjects,compiledObjects out=headersize,oideque
            int headersize = headerdeque.getAllBytes().length + 12;
            int offset = headersize; //put our pointer to the end of the header.
            Typeid lasttype2 = Typeid.nil;
            oideque.writeInt(numTypes);
            for(int i=0;i<numObjects;i++)
            {
                PrpRootObject curobj = uncompiledObjects.get(i);
                if(curobj.header.objecttype!=lasttype2) //if new type encountered...
                {
                    lasttype2 = curobj.header.objecttype;
                    lasttype2.compile(oideque); //add typeid
                    //java.util.
                    int objcount = typeInfo.get(lasttype2);
                    oideque.writeInt(objcount); //add typecount
                }
                curobj.header.desc.compile(oideque); //add desc
                oideque.writeInt(offset); //add offset.
                int objectsize = compiledObjects.get(i).length;
                oideque.writeInt(objectsize); //add size.
                
                offset += objectsize; //move forward the size of the object.
            }
            
            //finish compiling header. in=headerdeque,oideque,sizeofallobjects,headersize. out=headerdeque
            int filedatasize = sizeOfAllObjects + oideque.getAllBytes().length;
            int firstoffset = headersize;
            int indexoffset = headersize + sizeOfAllObjects;// + oideque.getAllBytes().length;
            headerdeque.writeInt(filedatasize);
            headerdeque.writeInt(firstoffset);
            headerdeque.writeInt(indexoffset);
            
            //put it all together. in=headerdeque, compiledObjects, oideque. out=fulldeque
            Bytedeque fulldeque = new Bytedeque();
            fulldeque.writeBytedeque(headerdeque);
            for(int i=0;i<numObjects;i++)
            {
                fulldeque.writeBytes(compiledObjects.get(i));
            }
            fulldeque.writeBytedeque(oideque);
            
            //save to file. in=fulldeque out=!!!
            byte[] fullbyte = fulldeque.getAllBytes();
            //String filename = header.agename.toString()+"_District_"+header.pagename.toString()+".prp";
            //FileUtils.WriteFile(_staticsettings.outputdir+filename, fullbyte);

            //cleanup.
            //fix payiferen pageid conflict problem.
            //if (prp.header.agename.toString().toLowerCase().equals("payiferen"))
            //{
                //_staticsettings.sequencePrefix = 0x00;
            //}
            m.msg("Recompilated completed!");
            return new Bytes(fullbyte);
        }
        
        //a bit of a hack, to replace LayerAnimations with other materials.
        public static void fixMaterial(prpfile prp)
        {
            int numobjs = prp.objects.length;
            Uruobjectdesc stableMaterial = null; //the material we can use instead of LayerAnimations.
            Vector<Uruobjectref> badMaterials = new Vector<Uruobjectref>();
            
            for(int i=0;i<numobjs;i++)
            {
                if(prp.objects[i].header.desc.objecttype==Typeid.plDrawableSpans)
                {
                    PlDrawableSpans curDrawableSpan = (PlDrawableSpans)prp.objects[i].prpobject.object;
                    
                    int numMaterials = curDrawableSpan.matcount;
                    for(int j=0;j<numMaterials;j++) //for each material...
                    {
                        Uruobjectref materialRef = curDrawableSpan.materials[j];
                        if(materialRef.hasRef!=0)
                        {
                            PrpRootObject curMaterial1 = findObjectWithDesc(prp,materialRef.xdesc);
                            if(curMaterial1!=null) //if null, then it should be in another page,
                            {
                                x0007Material curMaterial = (x0007Material)curMaterial1.prpobject.object;
                                boolean badmaterial = false;
                                for(int k=0;k<curMaterial.layercount;k++)
                                {
                                    Typeid layerType = curMaterial.layerrefs[k].xdesc.objecttype;
                                    if(layerType == Typeid.plLayerAnimation)
                                    {
                                        //replace this material;
                                        /*curDrawableSpan.materials[j].xdesc = stableMaterial;
                                        if(stableMaterial==null) m.err("No stable material given.");*/
                                        badMaterials.add(curDrawableSpan.materials[j]);
                                        badmaterial = true;
                                    }
                                }
                                //if this material is the first good one, make it the stable material.
                                if(!badmaterial && stableMaterial==null) stableMaterial = curDrawableSpan.materials[j].xdesc;
                            }
                            else
                            {
                                m.warn("Material is not present in this page. I don't know if it uses a plLayerAnimation.");
                            }
                        }
                    }
                }
            }
            
            if( (badMaterials.size()!=0) && (stableMaterial==null) )
            {
                m.err("Unable to find *any* stable materials.");
            }
            else
            {
                for(int i=0;i<badMaterials.size();i++)
                {
                    badMaterials.get(i).xdesc = stableMaterial;
                }
            }
        }
        
        public static boolean isNormalObjectToBeIncluded(Uruobjectdesc desc)
        {
                Typeid type = desc.objecttype;
                int number = desc.objectnumber;
                String name = desc.objectname.toString();
                Pageid pageid = desc.pageid;

                String[] namestartswith = {};
                String[] nameequals = {};
                Typeid[] typeequals = {};
                
                boolean useObject = false;
                
                //blacklist
                if(type==type.plSceneNode) return false; //do not allow Scene node in here, it must be treated separately.
                if(pageid.getRawData()==0x220024 && type==type.plResponderModifier && name.equals("RespWedges")) return false; //livebahrocaves pod district problem. (crashes when linking.)
                if(pageid.getRawData()==0x2A0025 && type==type.plResponderModifier && name.equals("cRespExcludeRgn")) return false; //minkata cameras district problem. (crashes when going to night).
                             
                            
                             
                
                int useCase = 11;
                switch(useCase)
                {
                    case 0: //ederdelin main
                        //blacklist
                        if(name.equals("LampBall40")) return false; //ball in alcove lamp doesn't work. Uses LayerAnimation
                        
                        //whitelist
                        
                        //specific sceneobjects to include:
                        if(type==type.plSceneObject) return true; //test
                        //if(number==406 && type==type.plSceneObject) return true; //LinkInPointDefault.
                        if(name.equals("LinkInPointDefault")) return true;
                        //if(name.startsWith("LampRTOmniLight")) return true; //get all the omni lamps
                        //if(name.startsWith("RTFillLight")) return true; //get all the directional lamps
                        //if(name.startsWith("dlnMArbleLanternGlare")) return true;
                        //if(name.startsWith("dlnLanternGlare")) return true;
                        //if(name.startsWith("ChainPlane")) return true; //this crashes the game. It's two SceneObjects that enables two viewfacemodifiers. Those viewfacemodifier have different flags than the one's that work.  Is this a new option not available in pots? Or does it use a material that isn't loading right(i.e. one of the animated ones)?
                        namestartswith = new String[]{
                            "SnowParty","Garden02Snow", //required for snow.
                            "a",
                            "bench05","bench06","big","blend","bluedoor",
                            "bluespiraldoorglow",
                            ////"bluespiralglow", //side-effect of huge lampshade in alcove. Caused by my faked materials for plLayerAnimation?
                            "bluespiralrig",
                            "bluespiraltapestry",
                            "blulamp",
                            "bscamera", //not working.
                            "bsdoortimegage",
                            "bugpath01",
                            "cam-sitbench","canyonpillar","chainplane",
                            "clothglowgroup","clusterflowers",
                            "contemplationsteps",
                            "dln",
                            ////"dust", //crashes the game; contains plAGMasterMod
                            "fern0","fern1","fern2","fern3","fern4","fern5",
                            "followcamera", "followclosecamera",
                            ////"fountain", //has side-effect of huge lampshade.
                            "garden02snow",
                            ////"Garden2background", //has side-effect
                            ////"garden2top", //has side-effect
                            "gazebo1camera","gazebo2camera",
                            "lamp","lapm",
                            ////"leafgenerator", //crashes game, probably has LayerAnimation for leaves (no normal animation, though) The leaves don't seem visible in screenshots anyway.
                            ////"leafkiller" //not needed, since we have no leaves anyway. It doesn't appear to have anything to do with the snow either.
                            ////"lightcone", //layeranimation
                            "MaintainersMarkerCracks",
                            ////"MaintainersMarker", //layeranimation
                            "NbhdBkPodiumPost",
                            "Object",
                            "path",
                            "patiobench",
                            "pythonbox",
                            "restroom",
                            ////"rock" //stuff related to movable and falling rocks.  Is this even used?
                            "rope",
                            "rt",
                            "sfxbs",
                            ////"sfxgrass" //run on grass sound, need physics
                            "sfxsnd-amb","sfxsnd-birds","sfxsnd-winteramb", //can't hear most of these(i did hear a random bird sound); it may just be a setting and perhaps you can't hear them in moul either. But I think responderModifier needs to be implemented.
                            ////"sfxsndamb" //uses linefollowmod
                            "sfxsndfountain", //works great.
                            ////"sfxsndloon", //use animation
                            "sfxsndwinter",
                            ////"sfxstone" //run on stone sound, need physics.
                            "signpost",
                            "smallrock",
                            ////"soundcontrol", //needs respondermodifier
                            "stairway02",
                            "statuebase","statueblue","statuechrome","statueplinth",
                            "treasure",
                            "tree",
                            "yeeshapage15decal","yeeshapageleafdecal",
                            "yeeshapage15","yeeshapageleaf", //should allow use to hide/show the two pages.
                        };
                        nameequals = new String[]{
                            "BigTree06",
                            "BigTree06Decal",
                            //"fountainpool", //layeranimation
                            //"fountainpool01", //layeranimation
                            "fountainwalkwaydecal",
                            //"fountainwater", //layeranimation
                            "Garden2",
                        };
                        
                        //if(number==1 && type==type.plSpawnModifier) return true;
                        if(type==type.plSpawnModifier) return true;
                        //if(number==175 && type==type.plCoordinateInterface) return true;
                        if(type==type.plCoordinateInterface) return true;
                        if(type==type.plDrawInterface) return true;
                        //if(number==1 && type==type.plDrawableSpans) return true;
                        if(type==type.plDrawableSpans) return true;
                        if(type==type.hsGMaterial) return true;
                        if(type==type.plLayer) return true;
                        if(type==type.plOmniLightInfo) return true;
                        if(type==type.plPointShadowMaster) return true;
                        if(type==type.plCubicEnvironMap) return true;
                        if(type==type.plMipMap) return true;
                        if(type==type.plPythonFileMod) return true;
                        if(type==type.plDirectionalLightInfo) return true;
                        if(type==type.plSimulationInterface) return true;
                        if(type==type.plViewFaceModifier) return true;
                        if(type==type.plAudioInterface) return true;
                        if(type==type.plStereizer) return true;
                        if(type==type.plSoundBuffer) return true;
                        if(type==type.plRandomSoundMod) return true;
                        if(type==type.plWin32StreamingSound) return true;
                        if(type==type.plWin32StaticSound) return true;
                        if(type==type.plWinAudio) return true;
                        if(type==type.plParticleSystem) return true;
                        if(type==type.plParticleCollisionEffectDie) return true;
                        if(type==type.plParticleLocalWind) return true;
                        if(type==type.plBoundInterface) return true;
                        typeequals = new Typeid[]{
                            type.plExcludeRegionModifier
                            ,type.plCameraBrain1
                            ,type.plCameraBrain1_Avatar
                            ,type.plCameraBrain1_Circle
                            ,type.plCameraBrain1_Fixed
                            ,type.plCameraModifier1,
                            type.plAGModifier
                        };
                        
                        //unstable
                        //if(name.equals("Archway")) return true;
                        //if(name.equals("ArchwayLight")) return true;
                        //if(type==type.plLayerAnimation) return true;
                        break;
                    case 1: //include all: spyroom builtin, spyroom textures, ederdelin builtin, ederdelin textures
                        return true;
                        //break;
                    case 2: //spyroom main
                        if(name.equals("LinkInPointSpyroom")) return true;
                        if(name.equals("StartPoint01")) return true;
                        break;
                    case 3: //basic link in
                        if(name.toLowerCase().startsWith("linkinpoint")) return true;
                        if(name.toLowerCase().startsWith("startpoint")) return true;
                        break;
                    case 4: //basic drawables
                        typeequals = new Typeid[]{
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                            //"atria",
                        };
                        break;
                    case 5: //guild pub
                        //if(name.toLowerCase().startsWith("imager")) return false;
                        //if(name.toLowerCase().startsWith("billboard")) return false;
                        //if(name.toLowerCase().startsWith("cpythgpub")) return false;
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                            //"a",
                            //"b","c","d",
                            //"e","f","g",//"h","i","j",
                            //"hanging","j",
                            //"j","half","hanginglantern",
                            "hanginglightflare",
                            //"agesdlhook",
                            //"atria",
                            //"ayhoheekrm01",
                            //"ayhoheekrm02",
                            //"balcony",
                            //"ballister",
                            //"boothwood",
                            //"cam",
                            //"canvashang",
                            //"cavewalls",
                            //"circlepath",
                            //"convchair",
                            //"curtain",
                            //"doorxrgn",
                        };
                        break;
                    case 6: //livebahrocaves
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                            
                            type.plParticleCollisionEffectBounce,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                            "rt",
                            "starfield",
                        };
                        break;
                    case 7: //tetsonot
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                            
                            type.plParticleCollisionEffectBounce,
                            
                            type.plSpotLightInfo,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                        };
                        break;
                    case 8: //minkata
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                            
                            type.plParticleCollisionEffectBounce,
                            
                            type.plSpotLightInfo,

                            type.plShadowCaster,
                            type.plShadowCaster,
                            type.plDirectShadowMaster,
                            type.plRelevanceRegion,
                            type.plSoftVolumeSimple,
                            //type.plResponderModifier,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                        };
                        break;
                    case 9: //jalak, payiferen
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                            
                            type.plParticleCollisionEffectBounce,
                            
                            type.plSpotLightInfo,

                            type.plShadowCaster,
                            type.plShadowCaster,
                            type.plDirectShadowMaster,
                            type.plRelevanceRegion,
                            type.plSoftVolumeSimple,
                            //type.plResponderModifier,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                        };
                        break;
                    case 10: //negilahn, edertsogal, new ederdelin, new minkata
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                            
                            type.plParticleCollisionEffectBounce,
                            
                            type.plSpotLightInfo,

                            type.plShadowCaster,
                            type.plShadowCaster,
                            type.plDirectShadowMaster,
                            type.plRelevanceRegion,
                            type.plSoftVolumeSimple,
                            //type.plResponderModifier,
                            
                            type.plParticleFlockEffect,
                            type.plFadeOpacityMod,
                            type.plClusterGroup,
                        };
                        namestartswith = new String[]{
                            "linkinpoint",
                            "startpoint",
                        };
                        break;
                    case 11: //new minkata
                        typeequals = new Typeid[]{
                            type.plSceneObject,
                            
                            type.plCoordinateInterface,
                            type.plSpawnModifier,
                            type.plDrawInterface,
                            type.plDrawableSpans,
                            type.hsGMaterial,
                            type.plLayer,
                            type.plMipMap,
                            type.plCubicEnvironMap,
                            
                            type.plOmniLightInfo,
                            type.plPointShadowMaster,
                            type.plPythonFileMod,
                            type.plDirectionalLightInfo,
                            type.plSimulationInterface,
                            type.plViewFaceModifier,
                            type.plAudioInterface,
                            type.plStereizer,
                            type.plSoundBuffer,
                            type.plRandomSoundMod,
                            type.plWin32StreamingSound,
                            type.plWin32StaticSound,
                            type.plWinAudio,
                            type.plParticleSystem,
                            type.plParticleCollisionEffectDie,
                            type.plParticleLocalWind,
                            type.plBoundInterface,
                            type.plExcludeRegionModifier,
                            type.plCameraBrain1,
                            type.plCameraBrain1_Avatar,
                            type.plCameraBrain1_Circle,
                            type.plCameraBrain1_Fixed,
                            type.plCameraModifier1,
                            type.plAGModifier,
                            
                            type.plOccluder,
                            type.plDynamicTextMap,
                            
                            type.plParticleCollisionEffectBounce,
                            
                            type.plSpotLightInfo,

                            type.plShadowCaster,
                            type.plDirectShadowMaster,
                            type.plRelevanceRegion,
                            type.plSoftVolumeSimple,
                            
                            type.plParticleFlockEffect,
                            type.plFadeOpacityMod,
                            type.plClusterGroup,
                            type.plVisRegion,
                            type.plSoftVolumeUnion,
                            type.plObjectInVolumeDetector,
                            type.plObjectInBoxConditionalObject,
                            type.plInterfaceInfoModifier,
                            type.plVolumeSensorConditionalObject,
                            type.plLogicModifier,
                            type.plActivatorConditionalObject,
                            type.plFacingConditionalObject,
                            type.plOneShotMod,
                            type.plAvLadderMod,
                            type.plPickingDetector,
                            type.plCameraRegionDetector,
                            
                            type.plHKPhysical,
                            
                            type.plSoftVolumeIntersect,
                            type.plEAXListenerMod,
                            type.plPhysicalSndGroup,
                            type.plSeekPointMod,
                            type.plRailCameraMod,
                            type.plLayerAnimation,
                            type.plATCAnim,
                            type.plAGMasterMod,
                            type.plPanicLinkRegion,
                            type.plLineFollowMod,
                            type.plMsgForwarder,
                            type.plAnimEventModifier,
                            type.plMultiStageBehMod,

                            type.plDynaFootMgr,
                            type.plResponderModifier, //crashes POD district of LiveBahroCaves, and minkCameras district of Minkata.
                            type.plSittingModifier,
                            type.plImageLibMod,
                            type.plLimitedDirLightInfo,
                            type.plAgeGlobalAnim,
                            type.plDynaPuddleMgr,
                            type.plWaveSet7,
                            type.plDynamicEnvMap,
                            
                            //version2
                            type.plSoftVolumeInvert,
                        };
                        namestartswith = new String[]{
                            /*"linkinpoint",
                            "startpoint",
                            "cratercentral",
                            "tower",
                            "outer",
                            "centertotem",
                            "ground", //ground
                            "criticalcave", //crater around caves
                            "soccer",
                            
                            "crespring",
                            "csfxresp",
                            "respdisablejc",
                            "respdrnowedge",
                            "respjconeshot",
                            "resplinkout",
                            "RespNglnWedge",
                            "RespPayiWedge",
                            "RespSolutionSymbols",
                            "RespTetsWedge",
                            "RespWedges", //pod problem.
                            
                            "cRespExcludeRgn", //minkata problem.
                            "cRespSfxLinkIn",*/
                        };
                        break;

                }
                
                for(int i=0;i<nameequals.length;i++)
                {
                    if(name.toLowerCase().equals(nameequals[i].toLowerCase())) return true;
                }
                for(int i=0;i<namestartswith.length;i++)
                {
                    if(name.toLowerCase().startsWith(namestartswith[i].toLowerCase())) return true;
                }
                for(int i=0;i<typeequals.length;i++)
                {
                    if(type==typeequals[i]) return true;
                }
                
                return useObject;
        }
    }

    public static PrpRootObject findObjectWithDesc(prpfile prp, Uruobjectdesc desc)
    {
        int numobjects = prp.objects.length;
        for(int i=0;i<numobjects;i++)
        {
            PrpRootObject curobj = prp.objects[i];
            Uruobjectdesc curdesc = curobj.header.desc;
            /*if(curdesc==null || curdesc.objectname==null || curdesc.objectname.toString()==null||desc==null||desc.objectname==null||desc.objectname.toString()==null)
            {
                int dummy=0;
            }*/
            if(curdesc.objectname.toString().equals(desc.objectname.toString())
                    &&(curdesc.objecttype==desc.objecttype)
                    &&(curdesc.pageid.getRawData()==desc.pageid.getRawData()))
            {
                return curobj;
            }
        }
        return null;
    }
    
    public static prpfile ProcessPotsPrp(context c)//(byte[] data)
    {
        //context c = context.createDefault(new Bytestream(data));
        //c.readversion = 3; //read as pots
        
        //Bytestream data = c.in;

        PrpHeader header = new PrpHeader(c);
        
        //do header work.
        prpfile result = new prpfile();
        Vector<PrpRootObject> objects = new Vector<PrpRootObject>();
        result.header = header;
        
        //process the object index, which is *not* a part of this struct.
        PrpObjectIndex objectindex = new PrpObjectIndex(c.Fork(new Bytestream(c.in,header.offsetToObjectIndex)));
        result.objectindex = objectindex;
        
        int numobjecttypes = objectindex.indexCount;
        for(int i=0;i<numobjecttypes;i++)
        {
            Typeid type = objectindex.types[i].type;
            int numObjects = objectindex.types[i].objectcount;
            
            //do per-type work.
            for(int j=0;j<numObjects;j++)
            {
                Uruobjectdesc desc = objectindex.types[i].descs[j].desc;
                int offset = objectindex.types[i].descs[j].offset;
                int size = objectindex.types[i].descs[j].size;
                
                //do per-object work.
                PrpRootObject object = null;
                context stream = c.Fork(new Bytestream(c.in,offset));
                stream.curRootObject = desc;
                stream.curRootObjectOffset = offset;
                stream.curRootObjectSize = size;
                stream.curRootObjectEnd = offset+size;

                //process the object, which is *not* a part of this struct.
                //to disable an object type, simply comment out its "object=" line.
                
                //This next line can be used to skip all but a single type.
                //if(desc.objecttype!=Typeid.plBoundInterface) continue;

                boolean handled = true;
                switch(desc.objecttype)
                {
                    case plBoundInterface:
                    case plSceneNode:
                    case plSceneObject:
                    case plMipMap:
                    case plCubicEnvironMap:
                    case plLayer:
                    case hsGMaterial:
                    case plPointShadowMaster:
                    case plParticleSystem:
                    case plAudioInterface:
                    case plWinAudio:
                    case plCoordinateInterface:
                    case plDrawInterface:
                    case plSpawnModifier:
                    case plDrawableSpans:
                    case plDirectionalLightInfo:
                    case plOmniLightInfo:
                    case plViewFaceModifier:
                    case plPythonFileMod:
                    case plLayerAnimation:
                    case plHKPhysical:
                    case plStereizer:
                    case plSoundBuffer:
                    case plRandomSoundMod:
                    case plWin32StreamingSound:
                    case plWin32StaticSound:
                    case plParticleCollisionEffectDie:
                    case plParticleLocalWind:
                    case plSimulationInterface:
                    case plExcludeRegionModifier:
                    case plCameraBrain1:
                    case plCameraBrain1_Avatar:
                    case plCameraBrain1_Fixed:
                    case plCameraBrain1_Circle:
                    case plCameraModifier1:
                    case plAGModifier:
                    case plOccluder:
                    case plDynamicTextMap:
                    case plParticleCollisionEffectBounce:
                    case plSpotLightInfo:
                    case plShadowCaster:
                    case plDirectShadowMaster:
                    case plRelevanceRegion:
                    case plSoftVolumeSimple:
                    case plResponderModifier:
                    case plParticleFlockEffect:
                    case plFadeOpacityMod:
                    case plClusterGroup:
                    case plVisRegion:
                    case plSoftVolumeUnion:
                    case plObjectInVolumeDetector:
                    case plObjectInBoxConditionalObject:
                    case plInterfaceInfoModifier:
                    case plVolumeSensorConditionalObject:
                    case plLogicModifier:
                    case plActivatorConditionalObject:
                    case plFacingConditionalObject:
                    case plOneShotMod:
                    case plAvLadderMod:
                    case plDynaFootMgr:
                    case plPickingDetector:
                    case plCameraRegionDetector:
                    case plSoftVolumeIntersect:
                    case plEAXListenerMod:
                    case plPhysicalSndGroup:
                    case plSeekPointMod:
                    case plRailCameraMod:
                    case plATCAnim:
                    case plAGMasterMod:
                    case plPanicLinkRegion:
                    case plLineFollowMod:
                    case plMsgForwarder:
                    case plAnimEventModifier:
                    case plMultiStageBehMod:
                    case plSittingModifier:
                    case plImageLibMod:
                    case plLimitedDirLightInfo:
                    case plAgeGlobalAnim:
                    case plDynaPuddleMgr:
                    case plWaveSet7:
                    case plDynamicEnvMap:
                    case plRidingAnimatedPhysicalDetector:
                    case plGrassShaderMod:
                    case plDynamicCamMap:
                    case plSoftVolumeInvert:
                        try
                        {
                            object = new PrpRootObject(stream);
                        }
                        catch(readexception e)
                        {
                            
                        }
                        break;
                    default:
                        m.msg("unhandled object type:"+desc.objecttype.toString());
                        handled = false;
                        break;
                }
                if(object==null) handled = false;
                if(handled)
                {
                    objects.add(object);
                    
                    int shortby = offset+size-stream.in.getAbsoluteOffset();
                    if(shortby!=0)
                    {
                        //if(desc.objecttype!=Typeid.plHKPhysical)
                            m.msg("Prp: Object was not the expected size. It was off by:"+Integer.toString(shortby));
                    }
                }
                
            }
        }
        
        m.msg("Process All was successful!");
        result.objects = uru.generics.convertVectorToArray(objects,PrpRootObject.class);
        return result;
        
    }
    
    public static void findAllObjectsOfType(String prpdir, Typeid type)
    {
        class callback implements uru.moulprp.allprpfiles.RootobjCallbackInterface
        {
            Typeid type;
            public callback(Typeid type2)
            {
                type = type2;
            }
            
            public void handleRootobj(prpfile prp, PrpObjectIndex.ObjectindexObjecttypeObjectdesc obj)
            {
                if(obj.desc.objecttype==type)
                {
                    int dummybreakpoint=0;
                }
            }
        }
        
        uru.moulprp.allprpfiles.parseAllRootobjs(new callback(type),prpdir);
        
    }
    public static void FindDrawInterfacesThatUseLayerAnimations(byte[] data)
    {
        StringBuilder report = new StringBuilder();
        
        context c = context.createFromBytestream(new Bytestream(data));
        prpfile prp = ProcessAllMoul(c,false);
        for(int i=0;i<prp.objects.length;i++)
        {
            PrpRootObject curobj = prp.objects[i];
            if(curobj.header.desc.objecttype==Typeid.plDrawInterface)
            {
                x0016DrawInterface di = (x0016DrawInterface)curobj.prpobject.object;
                for(int j=0;j<di.subsetgroupcount;j++)
                {
                    int subsetgroup = di.subsetgroups[j].subsetgroupindex;
                    if(subsetgroup==-1) continue;
                    Uruobjectdesc spandesc = di.subsetgroups[j].span.xdesc;
                    PlDrawableSpans span = findObjectWithDesc(prp,spandesc).castTo();//x004CDrawableSpans.class);
                    int numsubsets = span.subsetgroups[subsetgroup].subsetcount;
                    for(int k=0;k<numsubsets;k++)
                    {
                        int subset = span.subsetgroups[subsetgroup].subsetindex[k];
                        if(subset>=span.subsets.length)
                        {
                            m.warn("Subset is not present.");
                        }
                        else
                        {
                            int material = span.subsets[subset].materialindex;
                            Uruobjectdesc matdesc = span.materials[material].xdesc;
                            if(matdesc.objectname.toString().toLowerCase().startsWith("crater"))
                            {
                                int dummy=0;
                            }
                            x0007Material mat = findObjectWithDesc(prp,matdesc).castTo();//x0007Material.class);
                            for(int l=0;l<mat.layercount;l++)
                            {
                                Typeid mattype = mat.layerrefs[l].xdesc.objecttype;
                                if(mattype==Typeid.plLayerAnimation)
                                {
                                    report.append("LayerAnimation found in: "+curobj.header.toString()+"\n");
                                }
                                else
                                {
                                    /*x0006Layer layer = prputils.findObjectWithDesc(prp, mat.layerrefs[l].xdesc).castTo();
                                    if(layer.texture.xdesc.objectname.toString().toLowerCase().startsWith("flatcraterinterior"))
                                    {
                                        int dummy=0;
                                    }*/
                                }
                            }
                        }
                    }
                    
                }
            }
        }
        FileUtils.WriteFile(_staticsettings.outputdir+"LayerAnimationReport.txt", report.toString().getBytes());
    }
    public static PrpRootObject[] FindAllObjectsOfType(prpfile prp, Typeid type)
    {
        Vector<PrpRootObject> result = new Vector<PrpRootObject>();
        
        int numobjs = prp.objects.length;
        for(int i=0;i<numobjs;i++)
        {
            PrpRootObject curobj = prp.objects[i];
            if(curobj!=null && curobj.header.desc.objecttype==type)
            {
                result.add(curobj);
            }
        }
        
        PrpRootObject[] result2 = new PrpRootObject[0];
        result2 = result.toArray(result2);
        return result2;
    }
    
    public static void ProcessAllFiles(String prpdirname)//, int version)
    {
        File prpfolder = new File(prpdirname);
        if (!prpfolder.isDirectory() || !prpfolder.exists())
        {
            m.err("Prp directory not in proper format or not found.");
            return;
        }
        
        File[] files = prpfolder.listFiles();
        m.msg("Parsing files... count="+Integer.toString(files.length));
        for(int i=0;i<files.length;i++)
        {
            File curfile = files[i];
            if(curfile.getName().toLowerCase().endsWith(".prp"))
            {
                //open prp file and process it.
                byte[] filedata = FileUtils.ReadFile(curfile);
                
                //do work.
                context c = context.createFromBytestream(new Bytestream(filedata));
                //c.readversion = version;
                c.curFile = curfile.getName();
                prpprocess.ProcessAllObjects(c);
                //if(version==3) prputils.ProcessPotsPrp(filedata);
                //if(version==6) prputils.ProcessAll(filedata);
            }
        }
        
        m.msg("Finished Processing all files.");
        
    }
    
    
}
