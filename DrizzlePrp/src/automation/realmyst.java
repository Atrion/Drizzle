/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import shared.*;
import realmyst.*;
import export3ds.*;
import java.util.Vector;

public class realmyst
{
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
