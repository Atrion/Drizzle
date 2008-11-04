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
    public static void save3dsFile(Vector<Mdb> mdbs)
    {
        Primary main = Primary.createNull();
        main.meshdata.mat = Material.create("defaultmat");
        
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
        
        if(mdb.fs==null) throw new ignore("Skipping NamedObj because it has no fs.");
        if(mdb.bunch==null) throw new ignore("Skipping NamedObj because it has no bunch.");
        if(mdb.whas==null) throw new ignore("Skipping NamedObj because it has no whas.");
        
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

        //if(main==null)
        //{
        //    main = Primary.createNull();
        //    main.meshdata.mat = Material.create("defaultmat");
        //}
        NamedObj newobj = NamedObj.createNull(objname);
        newobj.namedTriangleObject = NamedTriangleObject.createNull();
        newobj.namedTriangleObject.points = PointArray.create(verts);
        newobj.namedTriangleObject.faces = FaceArray.create(faces, "defaultmat");
        //main.meshdata.objs.add(newobj);

        //IBytedeque out = new Bytedeque2();
        //main.compile(out);
        //byte[] filedata = out.getAllBytes();
        //FileUtils.WriteFile("c:/test.3ds", filedata);
        
        return newobj;
    }
}
