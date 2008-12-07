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

import shared.Flt;
import uru.context; import shared.readexception;
import uru.Bytestream;
import uru.Bytedeque;
import shared.e;
import shared.m;
import shared.b;
//import java.util.Vector;

/**
 *
 * @author user
 */
public class PlDrawableSpans extends uruobj
{
    //Objheader xheader;
    //PlSynchedObject parent;
    int newunknown;
    int zbias;
    int blendflags;
    int matcount;
    Uruobjectref[] materials;
    public int subsetcount;
    public SpanSubset[] subsets;
    int unused;
    int listcount;
    int[] unused2;
    //byte[] unused3;
    Uruobjectref[] unused3;
    //BoundingBox[] xboundingBoxes;
    public BoundingBox xLocalBounds;
    public BoundingBox xWorldBounds;
    public BoundingBox xMaxWorldBounds;
    //int lightcount;
    //Vector<LightInfo> lightinfos = new Vector<LightInfo>();
    GrowVector<Uruobjectref> lightinfos;
    public int matrixsetcount;
    public Transmatrix[] localToWorlds; //was blendmatrix
    public Transmatrix[] worldToLocals; //was matrix2
    public Transmatrix[] localToBones; //was matrix3
    public Transmatrix[] boneToLocals; //was matrix4
    int subsetgroupcount;
    SubsetGroup[] subsetgroups;
    int meshcount;
    Mesh[] meshes;
    Typeid embeddedtype;
    public x0240plSpaceTree xspacetree;
    Uruobjectref scenenode;
    
    public PlDrawableSpans(context c) throws readexception
    {
        Bytestream data = c.in;
        //if(hasHeader) xheader = new Objheader(c);
        //parent = new PlSynchedObject(c); //should this be a keyedobject??? No, it shouldn't!
        newunknown = data.readInt();
        
        zbias = data.readInt();
        blendflags = data.readInt();
        matcount = data.readInt(); //so far so good.
        //materials = c.readVector(Uruobjectref.class,matcount);
        materials = new Uruobjectref[matcount];
        for(int i=0;i<matcount;i++)
        {
            materials[i] = new Uruobjectref(c);
            //TODO: remove the next block, it's a test hack!
            /*if(xheader.desc.objectname.toString().equals("EderDelin_garden_00000000_0Spans"))
            {
                if(i==17 || i==24 || i==34) materials[i] = materials[0];
            }*/
            if(materials[i].hasref() && materials[i].xdesc.objectname.toString().toLowerCase().startsWith("watercurrent"))
            {
                int dummy=0;
            }
        }
        
        subsetcount = data.readInt();
        subsets = c.readArray(SpanSubset.class,subsetcount);
        for(int i=0;i<subsetcount;i++)
        {
            if(subsets[i].materialindex==58)
            {
                int breakdummy = 0;
            }
        }
        if(c.readversion==7)
        {
            //I don't think hex isle has the next field.
            unused = 0;
        }
        else
        {
            unused = data.readInt(); e.ensure(unused==0);
        }
        listcount = data.readInt(); e.ensure(listcount==subsetcount);//so far so good.
        unused2 = data.readInts(listcount);
        //unused3 = data.readBytes(subsetcount); //should all be zero. see pyprp.
        unused3 = new Uruobjectref[subsetcount];
        for(int i=0;i<subsetcount;i++)
        {
            unused3[i] = new Uruobjectref(c);
        }
        
        if(c.readversion==7)
        {
            //2 uruobjectref vectors here, but apparently they don't get called.
            //c.readInt();
            //c.readInt();
        }
        
        if(subsetcount>0)
        {
            //xboundingBoxes = c.readArray(BoundingBox.class,3);
            xLocalBounds = new BoundingBox(c);
            xWorldBounds = new BoundingBox(c);
            xMaxWorldBounds = new BoundingBox(c);
        }

        //lightcount = data.readInt();
        /*LightInfo newLightInfo;
        do
        {
            newLightInfo = data.readObj();
            lightinfos.add(newLightInfo);
        }
        while(newLightInfo.lightcount > 0);*/
        //The lightinfos is read very differently in pyprp, the final int is actually part of another structure, which happens to always? be zero.
        lightinfos = new GrowVector<Uruobjectref>(Uruobjectref.class, c);
        
        matrixsetcount = data.readInt();
        localToWorlds = c.readArray(Transmatrix.class,matrixsetcount);
        worldToLocals = c.readArray(Transmatrix.class,matrixsetcount);
        localToBones = c.readArray(Transmatrix.class,matrixsetcount);
        boneToLocals = c.readArray(Transmatrix.class,matrixsetcount);
        subsetgroupcount = data.readInt(); //hexisle line 346
        subsetgroups = c.readArray(SubsetGroup.class,subsetgroupcount); //fDIIndices, hexisle starts at line 386
        meshcount = data.readInt(); //so far so good.
        meshes = c.readArray(Mesh.class,meshcount); //plGBufferGroups
        embeddedtype = Typeid.Read(c);
        switch(embeddedtype)
        {
            case plSceneNode: //means null here.
                break;
            case nil:
                break;
            case plSpaceTree:
                xspacetree = new x0240plSpaceTree(c);
                break;
            default:
                m.msg("unknown type.");
                break;
        }
        scenenode = c.readObj(Uruobjectref.class);
        
    }
    public void compile(Bytedeque data)
    {
        //parent.compile(data);
        data.writeInt(newunknown);
        data.writeInt(zbias);
        data.writeInt(blendflags);
        data.writeInt(matcount);
        data.writeArray2(materials);
        data.writeInt(subsetcount);
        data.writeArray2(subsets);
        data.writeInt(unused);
        data.writeInt(listcount);
        data.writeInts(unused2);
        //data.writeBytes(unused3);
        data.writeArray2(unused3);
        if(subsetcount>0)
        {
            //data.writeArray(xboundingBoxes);
            xLocalBounds.compile(data);
            xWorldBounds.compile(data);
            xMaxWorldBounds.compile(data);
        }
        lightinfos.compile(data);
        data.writeInt(matrixsetcount);
        data.writeArray2(localToWorlds);
        data.writeArray2(worldToLocals);
        data.writeArray2(localToBones);
        data.writeArray2(boneToLocals);
        data.writeInt(subsetgroupcount);
        data.writeArray2(subsetgroups);
        data.writeInt(meshcount);
        data.writeArray2(meshes);
        //TODO: remove next line, it's a hack.
        //embeddedtype = Typeid.nil;
        embeddedtype.compile(data);
        
        switch(embeddedtype)
        {
            case nil:
                break;
            case plSpaceTree:
                xspacetree.compile(data);
                break;
            default:
                m.err("unknown type.");
                break;
        }
        scenenode.compile(data);
    }
    
    static public class SpanSubset extends uruobj
    {
        int visible;
        int materialindex;
        public Transmatrix localToWorld; //was transforms1
        public Transmatrix worldToLocal; //was transforms2
        int lightingflags;
        public BoundingBox localBounds; //was uegclassesca1
        public BoundingBox worldBounds; //was uegclassesca2
        public int blendflag;
        public int blendindex;
        short u1;
        short u2;
        short u3;
        Flt u4;
        Flt u5;
        Flt xu6;
        public int meshindex;
        int unused1;
        int unused2;
        int vertexstart1;
        int vertexstart2;
        int vertexcount;
        int surfaceindex;
        int indexstart;
        int indexcount;
        
        public SpanSubset(context c) throws readexception
        {
            Bytestream data = c.in;
            visible = data.readInt(); e.ensureflags(visible,1,0); //is sometimes 0 in hex isle.
            materialindex = data.readInt();
            if(materialindex==0 || materialindex==3)
            {
                if(c.curRootObject.objectname.toString().toLowerCase().startsWith("minkata_minkexteriorday_4000000c_2blendspans"))
                {
                    int breakdummy= 0;
                }
            }
            localToWorld = c.readObj(Transmatrix.class);
            worldToLocal = c.readObj(Transmatrix.class);
            lightingflags = data.readInt(); //props
            localBounds = c.readObj(BoundingBox.class);
            worldBounds = c.readObj(BoundingBox.class);
            blendflag = data.readInt();
            blendindex = data.readInt();
            u1 = data.readShort();
            u2 = data.readShort();
            u3 = data.readShort();
            u4 = c.readObj(Flt.class);
            u5 = c.readObj(Flt.class); //so far so good
            if((lightingflags & 0x10)!=0)
            {
                xu6 = c.readObj(Flt.class);
            }
            meshindex = data.readInt(); //groupIdx
            unused1 = data.readInt(); //vBufferIdx
            unused2 = data.readInt(); //cellIdx
            vertexstart1 = data.readInt(); //cellOffset
            vertexstart2 = data.readInt(); //vStartIdx
            vertexcount = data.readInt(); //vLength
            if(c.readversion==7)
            {
                //hex isle doesn't have the next two fields.
            }
            else
            {
                surfaceindex = data.readInt(); //bufferIdx
                indexstart = data.readInt(); //startIdx
            }
            indexcount = data.readInt(); //iLength
            if((lightingflags&0x4)!=0)
            {
                int dummy=0;
            }
        }
        
        public void compile(Bytedeque data)
        {
            data.writeInt(visible);
            data.writeInt(materialindex);
            localToWorld.compile(data);
            worldToLocal.compile(data);
            data.writeInt(lightingflags);
            localBounds.compile(data);
            worldBounds.compile(data);
            data.writeInt(blendflag);
            data.writeInt(blendindex);
            data.writeShort(u1);
            data.writeShort(u2);
            data.writeShort(u3);
            u4.compile(data);
            u5.compile(data);
            if((lightingflags & 0x10)!=0)
            {
                xu6.compile(data);
            }
            data.writeInt(meshindex);
            data.writeInt(unused1);
            data.writeInt(unused2);
            data.writeInt(vertexstart1);
            data.writeInt(vertexstart2);
            data.writeInt(vertexcount);
            data.writeInt(surfaceindex);
            data.writeInt(indexstart);
            data.writeInt(indexcount);
            
        }
    }
    


    /*static public class LightInfo extends uruobj
    {
        int lightcount;
        Uruobjectref[] xlights;
        
        public LightInfo(Bytestream data)
        {
            lightcount = data.readInt();
            if(lightcount>0)
            {
                xlights = data.readVector(Uruobjectref.class,lightcount);
            }
        }
    }*/
    
    static public class SubsetGroup extends uruobj
    {
        int u1;
        int subsetcount;
        int[] subsetindex;
        
        public SubsetGroup(context c)
        {
            u1 = c.in.readInt();
            subsetcount = c.in.readInt();
            subsetindex = c.in.readInts(subsetcount);
        }
        
        public void compile(Bytedeque data)
        {
            data.writeInt(u1);
            data.writeInt(subsetcount);
            data.writeInts(subsetindex);
        }
    }
    static public class x0240plSpaceTree extends uruobj
    {
        ////Objheader xheader;
        public short childnodes;
        public int leafnodes;
        public int allnodes;
        public Nodes[] nodes2;


        public x0240plSpaceTree(context c) throws readexception
        {
            Bytestream data = c.in;
            ////if(hasHeader) xheader = new Objheader(data);
            childnodes = data.readShort();
            leafnodes = data.readInt();
            allnodes = data.readInt();
            nodes2 = new Nodes[allnodes];
            for(int i=0;i<allnodes;i++)
            {
                nodes2[i] = new Nodes(c);
            }
            //nodes2 = data.readVector(Nodes.class, allnodes);
        }
        public void compile(Bytedeque data)
        {
            data.writeShort(childnodes);
            data.writeInt(leafnodes);
            data.writeInt(allnodes);
            for(int i=0;i<allnodes;i++)
            {
                nodes2[i].compile(data);
            }
        }

        public class Nodes
        {
            public BoundingBox boundingbox;
            short type;
            short parent;
            short left;
            short right;

            public Nodes(context c) throws readexception
            {
                boundingbox = c.readObj(BoundingBox.class);
                type = c.in.readShort(); //is this a typeid?
                parent = c.in.readShort(); //is this a typeid?
                left = c.in.readShort(); //typeid?
                right = c.in.readShort(); //typeid?
            }
            
            public void compile(Bytedeque data)
            {
                boundingbox.compile(data);
                data.writeShort(type);
                data.writeShort(parent);
                data.writeShort(left);
                data.writeShort(right);
            }
        }
    }
    
    
    static public class Mesh extends uruobj
    {
        //byte vertexformat;
        byte fformat;
        int size; //length to end of mesh.
        //byte[] restofmesh; //ignore it for now.
        int vertexstoragecount;
        SubMesh[] submeshes;
        //left off here.
        int surfacecount;
        //Uruvector<Short>[] surfaces;
        Shortvector[] surfaces;
        //int indexstoragecount;
        //IndexStorage[] indexstorages;
        int u1;
        int u2;
        int u3;
        int lastindex;
        
        public Mesh(context c)
        {
            Bytestream data = c.in;
            if(c.readversion==7)
            {
                //sub_5059A0
                int mesh1 = c.readInt(); //76
                byte mesh2 = c.readByte(); //0 //this+4+6
                byte mesh3 = c.readByte(); e.ensure(mesh3==1);//1 //v3+11, used in an if statement.
                byte mesh4 = c.readByte(); //0
                byte mesh5 = c.readByte(); //0
                int mesh6 = c.readInt(); //864 //v4+4
                byte mesh7 = c.readByte(); //0
                byte hi6a = (byte)(mesh6&0xFF);
                byte hi6b = (byte)((mesh6>>>8)&0xFF);
                int v7 = b.ByteToInt32(((mesh3&0x2)!=0)?hi6b:hi6a);
                //hex isle doesn't look like it has the size field.  It's ignored by pots, so we'll just stick 0 in it.
                size = 0;
                vertexstoragecount = data.readInt();
                
                int count = HexislePlDrawableSpans.getvertexcount(mesh1, mesh2);
                
                SubMeshAlt[] submeshalt = new SubMeshAlt[vertexstoragecount];
                for(int i=0;i<vertexstoragecount;i++)
                {
                    submeshalt[i] = new SubMeshAlt(c,fformat,v7,mesh6,mesh2,mesh1,count);
                    //submeshalt[i] = new SubMeshAlt(c,hi1,);
                }
                //TODO rest of this, but it seems to work perfectly up to this point.
                surfacecount = data.readInt();
                surfaces = new Shortvector[surfacecount];
                for(int i=0;i<surfacecount;i++)
                {
                    //surfaces[i] = new Shortvector(data);
                    int surf1 = c.readInt(); //3 times the number of vertices?
                    byte surf2 = c.readByte();
                    byte[] surf3 = c.readBytes(surf1);
                    int dummy=0;
                }
                int dummy=0;
            }
            else
            {
                fformat = data.readByte();
                size = data.readInt();
                
                //restofmesh = data.readBytes(size); //should this be size*4 ?
                vertexstoragecount = data.readInt();
                submeshes = new SubMesh[vertexstoragecount];
                for(int i=0;i<vertexstoragecount;i++)
                {
                    submeshes[i] = new SubMesh(c,fformat);
                }
                //TODO rest of this, but it seems to work perfectly up to this point.
                surfacecount = data.readInt();
                surfaces = new Shortvector[surfacecount];
                for(int i=0;i<surfacecount;i++)
                {
                    surfaces[i] = new Shortvector(data);
                }
                u1 = data.readInt();
                u2 = data.readInt();
                u3 = data.readInt();
                lastindex = data.readInt();
            }
        }
        public static class SubMeshAlt
        {
            public SubMeshAlt(context c, byte fformat, int v7, int v17, byte mesh2, int mesh1, int count)
            {
                int submesh1 = c.readInt(); //768, size in bytes?
                byte submesh2 = c.readByte(); //0
                m.warn("TODO: make this check if the short is in range.");
                
                int v16 = submesh1 / v7; //e.ensure(hi1 % v7 == 0);
                int shouldbe0a = submesh1 % v7;
                //int count = v16;
                int finalcount = submesh1 / count;
                int shouldbe0b = submesh1 % count;
                
                //hi1 is the size in bytes?
                //v16 is the count of GetVertexDataSize calls.
                //v7 is the stride?
                
                //c.readBytes(submesh1);
                //if(true)return;
                
                context c2 = c.Fork();
                //int dataSize = SubMesh.GetVertexDataSize(count, fformat, c2);
                //int dataSize = HexislePlDrawableSpans.GetVertexDataSize(count, fformat, c2, v17, hi2orig, hi1orig);
                m.warn("Using count, but this doesn't seem to be correct.");
                int dataSize = HexislePlDrawableSpans.GetVertexDataSize(count, fformat, c2, v17, mesh2, mesh1);
                //int dataSize = HexislePlDrawableSpans.GetVertexDataSize();
                byte[] rawdata = c.in.readBytes(dataSize);
                int rawdataversion = c.readversion;
            }
        }
        public void compile(Bytedeque data)
        {
            data.writeByte(fformat);
            data.writeInt(size); //this may be different, but apparently it's ignored anyway.
            data.writeInt(vertexstoragecount);
            for(int i=0;i<vertexstoragecount;i++)
            {
                submeshes[i].compile(data,fformat);
            }
            data.writeInt(surfacecount);
            for(int i=0;i<surfacecount;i++)
            {
                surfaces[i].compile(data);
            }
            data.writeInt(u1);
            data.writeInt(u2);
            data.writeInt(u3);
            data.writeInt(lastindex);
            
        }
        
        //this is essentially pyprp's plVertexCoder.
        static public class SubMesh
        {
            short count;

            //SubMeshVertex[] vertices;
            byte[] rawdata;
            
            int rawdataversion; //not actual data, just useful for compiling.
            
            public SubMesh(context c, byte fformat)
            {
                if((fformat&0x80)!=0)
                {
                    if(c.readversion==7)
                    {
                        int hi1 = c.readInt();
                        byte hi2 = c.readByte();
                        m.warn("TODO: make this check if the short is in range.");
                        count = (short)hi1;
                    }
                    else
                    {
                        count = c.in.readShort(); //number of vertexes.
                    }
                    
                    //vertices = new SubMeshVertex[count];
                    //for(int i=0;i<count;i++)
                    //{
                    //}
                    context c2 = c.Fork();
                    //c2.readversion = 3;
                    //context c2 = new context(6,3,false,c.in.CreateFork(),null);
                    int dataSize = GetVertexDataSize(count, fformat, c2);
                    rawdata = c.in.readBytes(dataSize);
                    rawdataversion = c.readversion;
                }
                else
                {
                    m.err("not supported.");
                }
            }
            
            public void compile(Bytedeque data, byte fformat)
            {
                if((fformat&0x80)!=0)
                {
                    data.writeShort(count);
                    //int dataSize = GetVertexDataSize(count, fformat, data.CreateFork());
                    //rawdata = data.readBytes(dataSize);
                    //Bytestream input = new Bytestream(rawdata);
                    //context c = new context(6,3,true,new Bytestream(rawdata),data,false,null);
                    context c = context.createFromBytestream(new Bytestream(rawdata));
                    c.compile = true;
                    c.out = data;
                    c.readversion = rawdataversion;
                    GetVertexDataSize(count,fformat,c);
                }
                else
                {
                    m.err("not supported.");
                }
                
            }
            
            //parse and get size. Used for both creation and compilation.
            static public int GetVertexDataSize(int count, byte fformat2, context c)
            {

                class rundata
                {
                    Flt basee;
                    byte basec;
                    int count;
                    boolean rle;
                    byte b1;
                    
                    public rundata()
                    {
                        basee = null;
                        count = 0;
                        basec = 0;
                        rle = false;
                    }
                    
                    //returns the compressed value, in some circumstances.
                    public short pollAsElement(int A, int B, int C, context c)
                    {
                        if(count==0)
                        {
                            basee = new Flt(c);
                            if(c.compile) basee.compile(c.out);

                            if(c.readversion==6)
                            {
                                b1 = c.in.readByte();
                                if(c.compile && c.writeversion==6) c.out.writeByte(b1);
                            }
                            else if(c.readversion==3||c.readversion==4||c.readversion==7)
                            {
                                //do nothing
                            }
                            
                            short out7 = c.in.readShort();
                            if(c.compile) c.out.writeShort(out7);
                            count = b.Int16ToInt32(out7);
                        }
                        if(count!=0)
                        {
                            if(c.readversion==6)
                            {
                                if(b1==0)
                                {
                                    count--;
                                    //return data.readShort();
                                    short out9 = c.in.readShort();
                                    if(c.compile) c.out.writeShort(out9);
                                    //return;
                                    return out9;
                                }
                                else if(b1==1)
                                {
                                    count--;
                                    if(c.compile && c.writeversion==3)
                                    {
                                        c.out.writeShort((short)0);//Is this right? Is this the default? //(32768);
                                    }
                                    return (short)0;
                                }
                                else
                                {
                                    m.msg("unknown byte.");
                                }
                            }
                            else if(c.readversion==3||c.readversion==4||c.readversion==7)
                            {
                                count--;
                                short out9 = c.in.readShort();
                                if(c.compile) c.out.writeShort(out9);
                            }
                        }
                        return 0;
                    }
                    public void pollAsColour(int A, int B, int C, context c)
                    {
                        if(count==0)
                        {
                            short out5 = c.in.readShort();
                            if(c.compile) c.out.writeShort(out5);
                            count = b.Int16ToInt32(out5);
                            if((count & 0x8000)!=0)
                            {
                                //m.msg("haven't tested this.");
                                rle = true;
                                basec = c.in.readByte();
                                if(c.compile) c.out.writeByte(basec);
                                count = count & 0x7FFF;
                            }
                            else
                            {
                                rle = false;
                            }
                        }
                        if(count!=0)
                        {
                            count--;
                            if(rle)
                            {
                                //return base;
                                return;
                            }
                            else
                            {
                                //return data.readByte();
                                byte out6 = c.in.readByte();
                                if(c.compile) c.out.writeByte(out6);
                                return;
                            }
                        }
                        m.err("We shouldn't be able to reach here.");
                    }
                }
                
                int start = c.in.getAbsoluteOffset();
                int fformat = b.ByteToInt32(fformat2);
                int A = (fformat & 0x40) >>> 6;
                int B = (fformat & 0x30) >>> 4;
                int C = (fformat & 0x0F) >>> 0;

                
                rundata x = new rundata();
                rundata y = new rundata();
                rundata z = new rundata();
                
                rundata[] ws = new rundata[3];
                for(int i=0;i<3;i++) ws[i] = new rundata();

                rundata blue = new rundata();
                rundata green = new rundata();
                rundata red = new rundata();
                rundata alpha = new rundata();
                
                rundata[][] uvs = new rundata[15][];
                for(int i=0;i<15;i++)
                {
                    uvs[i] = new rundata[3];
                    for(int j=0;j<3;j++)
                    {
                        uvs[i][j] = new rundata();
                    }
                }
                
                for(int i=0;i<count;i++)
                {
                    int possofar = c.in.getAbsoluteOffset() - start;
                    
                    //get vertex.
                    short xval = x.pollAsElement(A, B, C, c);
                    short yval = y.pollAsElement(A, B, C, c);
                    short zval = z.pollAsElement(A, B, C, c);
                    
                    //compute actuall, uncompressed vertex:
                    if (c.outputVertices)
                    {
                        float output;
                        output = x.basee.toJavaFloat();
                        output = output + (float)(xval)/(float)(1024.0);
                        c.vertices.add(output);
                        output = x.basee.toJavaFloat();
                        output = output + (float)(yval)/(float)(1024.0);
                        c.vertices.add(output);
                        output = x.basee.toJavaFloat();
                        output = output + (float)(zval)/(float)(1024.0);
                        c.vertices.add(output);
                    }
                    
                    //get weights
                    for(int j=0;j<B;j++)
                    {
                        ws[j].pollAsElement(A, B, C, c);
                    }
                    
                    //if A is set to true, get bones
                    if ((B!=0) && (A!=0))
                    {
                        int out1 = c.in.readInt(); //bones
                        if(c.compile) c.out.writeInt(out1);
                    }
                    
                    //normals: these are just a byte now.
                    //data.readShort(); //nx
                    //data.readShort(); //ny
                    //data.readShort(); //nz
                    if(c.readversion==6)
                    {
                        byte out2 = c.in.readByte(); //nx
                        byte out3 = c.in.readByte(); //ny
                        byte out4 = c.in.readByte(); //nz
                        if(c.compile)
                        {
                            c.out.writeShort((short)(b.ByteToInt32(out2)*257)); //the *257 expands it to 2 bytes. i.e. 0->0, 255->65535.
                            c.out.writeShort((short)(b.ByteToInt32(out3)*257));
                            c.out.writeShort((short)(b.ByteToInt32(out4)*257));
                        }
                    }
                    else if(c.readversion==3||c.readversion==4||c.readversion==7)
                    {
                        short out2 = c.in.readShort();
                        short out3 = c.in.readShort();
                        short out4 = c.in.readShort();
                        if(c.compile)
                        {
                            c.out.writeShort(out2);
                            c.out.writeShort(out3);
                            c.out.writeShort(out4);
                        }
                    }
                    
                    //colours:
                    blue.pollAsColour(A, B, C, c);
                    green.pollAsColour(A, B, C, c);
                    red.pollAsColour(A, B, C, c);
                    alpha.pollAsColour(A, B, C, c);
                    
                    //uv texture coordinates
                    for(int j=0;j<C;j++)
                    {
                        for(int k=0;k<3;k++)
                        {
                            uvs[j][k].pollAsElement(A, B, C, c);
                        }
                    }
                    
                }

                int stop = c.in.getAbsoluteOffset();
                return stop-start; //return the size we processed.
            }
            
        }
    }
    
    
}
