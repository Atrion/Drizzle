/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import shared.*;
import realmyst.*;
import export3ds.*;
import java.util.Vector;
import java.io.File;

public class realmyst
{
    public static Vector<Mdb> filterMdbsByRoom(Vector<Mdb> mdbs, String[] rooms)
    {
        Vector<Mdb> result = new Vector();
        for(Mdb mdb: mdbs)
        {
            String objname = mdb.name.toString();
            //String oname = mdb.name.toString().toLowerCase();
            int ind = objname.indexOf("..");
            if(ind==-1)
            {
                m.msg("objectname has no .. : "+objname);
                break;
            }
            
            String curroom = objname.substring(0, ind);
            m.msg("objectname: "+curroom);
            for(String room: rooms)
            {
                if(room.equals(curroom))
                {
                    result.add(mdb);
                }
            }
            //if(mdb.name.toString().toLowerCase().startsWith("myst.."))
            //{
            //    mdbs.add(mdb);
            //}
        }
        return result;
    }
    public static Vector<Hsm> realAllHsms(String folder)
    {
        File f = new File(folder+"/scn/maps");
        //realmyst.rmcontext.get().curnum=0;
        Vector<Hsm> hsms = new Vector<Hsm>();
        int count = 0;
        for(File child: f.listFiles())
        {
            if(child.getName().toLowerCase().endsWith(".hsm"))
            {
                //realmyst.rmcontext.get().curnum++;
                count++;
                //if(count>400) break;
                try
                {
                    int fs = (int)child.length();
                    shared.IBytestream bs = shared.SerialBytestream.createFromFile(child);
                    Hsm hsm = new Hsm(bs,child.getName());
                    int offset = bs.getAbsoluteOffset();
                    int bytesleft = bs.getBytesRemaining();

                    //if (mdb.filesizeMinusHeader!=fs-offset)
                    //{
                    //    int dummy=0;
                    //}
                    if(bytesleft!=0)
                    {
                        int dummy=0;
                    }

                    //String oname = hsm.name.toString().toLowerCase();
                    //int ind = oname.indexOf("..");
                    //if(ind==-1) m.msg("objectname has no ..");
                    //else m.msg("objectname: "+oname.substring(0, ind));
                    //if(mdb.name.toString().toLowerCase().startsWith("myst.."))
                    //{
                    //    mdbs.add(mdb);
                    //}

                    hsms.add(hsm);

                    int dummy=0;
                }
                catch(shared.ignore e)
                {
                    m.warn("Error so skipping file.");
                }
            }
        }
        return hsms;
    }
    public static Vector<Mdb> readAllMdbs(String folder)
    {
        File f = new File(folder+"/mdb");
        rmcontext.get().curnum=0;
        Vector<Mdb> mdbs = new Vector<Mdb>();
        int count = 0;
        for(File child: f.listFiles())
        {
            if(child.getName().toLowerCase().endsWith(".vdb"))
            {
                rmcontext.get().curnum++;
                count++;
                //if(count>400) break;
                try
                {
                    int fs = (int)child.length();
                    shared.IBytestream bs = shared.SerialBytestream.createFromFile(child);
                    Mdb mdb = new Mdb(bs,"102445243.vdb");
                    int offset = bs.getAbsoluteOffset();
                    int bytesleft = bs.getBytesRemaining();

                    //if (mdb.filesizeMinusHeader!=fs-offset)
                    //{
                    //    int dummy=0;
                    //}
                    if(bytesleft!=0)
                    {
                        int dummy=0;
                    }

                    //String oname = mdb.name.toString().toLowerCase();
                    //int ind = oname.indexOf("..");
                    //if(ind==-1) m.msg("objectname has no ..");
                    //else m.msg("objectname: "+oname.substring(0, ind));
                    //if(mdb.name.toString().toLowerCase().startsWith("myst.."))
                    //{
                    mdbs.add(mdb);
                    //}

                    int dummy=0;
                }
                catch(shared.ignore e)
                {
                    m.warn("Error so skipping file.");
                }
            }
        }
        return mdbs;
    }
    public static Vector<Sdb> readAllSdbs(String folder)
    {
        File f = new File(folder+"/sdb");
        Vector<Sdb> sdbs = new Vector();
        for(File child: f.listFiles())
        {
            if(child.getName().toLowerCase().endsWith(".vdb"))
            {
                try
                {
                    int fs = (int)child.length();
                    shared.IBytestream bs = shared.SerialBytestream.createFromFile(child);
                    Sdb sdb = new Sdb(bs);
                    int offset = bs.getAbsoluteOffset();
                    int bytesleft = bs.getBytesRemaining();

                    if (sdb.filesizeMinusHeader!=fs-offset)
                    {
                        int dummy=0;
                    }
                    if(bytesleft!=0)
                    {
                        int dummy=0;
                    }
                    int dummy=0;

                    sdbs.add(sdb);
                }
                catch(shared.ignore e)
                {
                    m.warn("Error so skipping file.");
                }
            }
        }
        return sdbs;
    }

    public static String[] findRoomInfo(Vector<Sdb> sdbs, String agecode)
    {
        //Valid agecodes are: Channel, Dni, Mech, Rime, Sel, Stone, Myst
        String soughtObject;
        if(agecode.equals("Myst"))
            soughtObject = "HoldingPen_Channel";
        else if(agecode.equals("Channel")||agecode.equals("Channel")||agecode.equals("Channel")||agecode.equals("Channel")||agecode.equals("Channel")||agecode.equals("Channel")||agecode.equals("Channel"))
            soughtObject = "HoldingPen_"+agecode+"ToMyst";
        else throw new uncaughtexception("realMyst Agename wasn't known, it was probably a typo.:"+agecode);
        
        for(Sdb sdb: sdbs)
        {
            String objname = sdb.name.toString();
            if(objname.startsWith("HoldingPen_"))
            {
                String room = sdb.strs[0].toString();
                if(room.equals("global"))
                {
                    if(objname.equals(soughtObject))
                    {
                        Vector<String> list2500=new Vector();
                        Vector<String> list2501=new Vector();
                        for(Count10.occref occ: sdb.count10s[0].occrefs1)
                        {
                            if(occ.u3==2500) list2500.add(occ.subs[0].xstr.toString());
                            if(occ.u3==2501) list2501.add(occ.subs[0].xstr.toString());
                        }
                        //String list1 = sdb.count10s[0].occrefs1[2].subs[0].xstr.toString();
                        //String list2 = sdb.count10s[0].occrefs1[3].subs[0].xstr.toString();
                        
                        String[] result = list2501.get(0).split(",");
                        return result;
                    }                    
                    
                    int dummy=0;
                }
                int dummy=0;
            }
            int dummy=0;
        }
        return null;
    }
    
    public static void saveDdsFiles(Vector<Hsm> hsms, String outfolder)
    {
        for(Hsm hsm: hsms)
        {
            int compressionType = hsm.getCompressionType();
            if(compressionType==0)
            {
                IBytedeque out = new Bytedeque2();
                images.dds.createFromUncompressed(out, hsm.xagrb, hsm.widthMaybe, hsm.heightMaybe);
                byte[] outdata = out.getAllBytes();
                FileUtils.WriteFile(outfolder+"/"+hsm.name+".dds", outdata);
            }
            else if(compressionType==1 || compressionType==5)
            {
                IBytedeque out = new Bytedeque2();
                images.dds.createFromDxt(out, hsm.dxt);
                byte[] outdata = out.getAllBytes();
                FileUtils.WriteFile(outfolder+"/"+hsm.name+".dds", outdata);
            }
        }
    }
    public static void save3dsFile(Vector<Mdb> mdbs)
    {
        Primary main = Primary.createNull();
        main.meshdata.mat = Material.create("defaultmat");
        
        //add texture filename...
        main.meshdata.mat.texturemap = TextureMap.create("active.hsm.dds");
        
        for(Mdb mdb: mdbs)
        {
            try
            {
                NamedObj obj = createNamedObj(mdb);
                main.meshdata.objs.add(obj);
            }
            catch(ignore e)
            {
                
            }
        }

        IBytedeque out = new Bytedeque2();
        main.compile(out);
        byte[] filedata = out.getAllBytes();
        FileUtils.WriteFile("c:/test.3ds", filedata);
    }
    
    public static NamedObj createNamedObj(Mdb mdb)
    {
        /*Vertex[] verts = new Vertex[bunch.length];
        for(int i=0;i<bunch.length;i++)
        {
            Flt x = bunch[i].f1;
            Flt y = bunch[i].f2;
            Flt z = bunch[i].f3;
            verts[i] = Vertex.createFromFlts(x, y, z);
        }*/
        
        String ignorereason = "";
        if(mdb.fs==null) ignorereason += "Skipping NamedObj because it has no fs. ";
        if(mdb.bunch==null) ignorereason += "Skipping NamedObj because it has no bunch. ";
        if(mdb.whas==null) ignorereason += "Skipping NamedObj because it has no whas. ";
        if(mdb.trips==null) ignorereason += "NamedObj has no trips.";
        if(!ignorereason.equals("")) throw new ignore(ignorereason);
        
        Vertex[] verts = new Vertex[mdb.fs.length];
        for(int i=0;i<mdb.fs.length;i++)
        {
            int to = mdb.fs[i].v2;
            int from = mdb.fs[i].v3;
            Flt x= mdb.bunch[to].f1;
            Flt y= mdb.bunch[to].f2;
            Flt z= mdb.bunch[to].f3;
            verts[i] = Vertex.createFromFlts(x, y, z);
        }

        ShortTriplet[] faces = new ShortTriplet[mdb.whas.length];
        for(int i=0;i<mdb.whas.length;i++)
        {
            int v1 = mdb.whas[i].u6;
            int v2 = mdb.whas[i].u7;
            int v3 = mdb.whas[i].u8;
            faces[i] = ShortTriplet.createFromShorts((short)v1, (short)v2, (short)v3);
        }

        String objname = mdb.name.toString();
        
        FltPair[] uvcoords = new FltPair[mdb.trips.length];
        for(int i=0;i<mdb.trips.length;i++)
        {
            Flt u = mdb.trips[i].x;
            Flt v = mdb.trips[i].y;
            uvcoords[i] = FltPair.createFromFlts(u, v);
        }

        //if(main==null)
        //{
        //    main = Primary.createNull();
        //    main.meshdata.mat = Material.create("defaultmat");
        //}
        NamedObj newobj = NamedObj.createNull(objname);
        newobj.namedTriangleObject = NamedTriangleObject.createNull();
        newobj.namedTriangleObject.points = PointArray.create(verts);
        newobj.namedTriangleObject.faces = FaceArray.create(faces, "defaultmat");
        //if(uvcoords.length!=0) newobj.namedTriangleObject.uvcoords = UvVerts.create(uvcoords);
        //main.meshdata.objs.add(newobj);
        
        for(ShortTriplet face: faces)
        {
            if(face.p >= verts.length || face.q>= verts.length || face.r>=verts.length)
            {
                int dummy=0;
            }
        }
        if(uvcoords.length!=0)
        {
            if(uvcoords.length!=verts.length)
            {
                int dummy=0;
            }
            else
            {
                if(uvcoords.length!=0) newobj.namedTriangleObject.uvcoords = UvVerts.create(uvcoords);
                
            }
        }

        //IBytedeque out = new Bytedeque2();
        //main.compile(out);
        //byte[] filedata = out.getAllBytes();
        //FileUtils.WriteFile("c:/test.3ds", filedata);
        
        return newobj;
    }
}
