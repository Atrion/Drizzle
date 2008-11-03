/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package realmyst;

import shared.*;
import shared.e;
import export3ds.*;

public class Mdb
{
    public int tag;
    public int filesize;
    public int u2;
    public Bstr name;
    //public int u3;
    public Bstr u3;
    public int strCount; //number of Bstrs that follow
    public Bstr[] strs;
    
    static Primary main;
    
    public Mdb(IBytestream c)
    {
        //tag = c.readInt();
        Typeid type = Typeid.read(c);
        if(type!=Typeid.mdb)
        {
            throw new uncaughtexception("Mdb didn't have the expected magic number.");
        }
        //mdb
        filesize = c.readInt(); //filesize (including header)
        u2 = c.readInt(); e.ensure(u2,1);//=1?
        name = new Bstr(c);
        String trap = 
                //"aurora..sn_TOWER_observe_roof01"
                "aurora..switchtube"
                ;
        if(name.toString().toLowerCase().startsWith(trap.toLowerCase()))
        {
            int dummy=0;
        }

        byte b3 = c.readByte(); e.ensure((int)b3,3); //always 3
        int u4 = c.readInt(); e.ensure(u4,0); //always 0
        int u5 = c.readInt(); e.ensure(u5,3); //always 3
        int u6 = c.readInt(); e.ensure(u6,0,1); //0 or 1
        int u7 = c.readInt(); e.ensure(u7,257,1); //1,11,2305,257,265,267,283,321,331,9 //0x1,B,901,101,109,10B,11B,141,14B,9 //100101011011 are the bits used.
        m.msg("u7="+Integer.toString(u7));
        int u8 = c.readInt(); e.ensure(u8,257,1); //0,1,11,257,265,267,283,321,331,9 //0x0,1,B,101,109,10B,11B,141,14B,9
        m.msg("u8="+Integer.toString(u8));
        int u9 = c.readInt(); e.ensure(u9,1); //1,2305 //0x1,901
        m.msg("u9="+Integer.toString(u9));
        int u10 = c.readInt(); e.ensure(u10,0); //0 or 1
        m.msg("u10="+Integer.toString(u10));
        m.msg(name.toString());
        //m.err("Unhandled tag: 0x"+Integer.toHexString(tag));
        
        Sixlet start = new Sixlet(c); //opposite corners of bounding box?
        
        int s1 = c.readInt(); e.ensure(s1,3,1,2);//3 (1 seemed the same, 2 ignores quats: this is probably flags.)
        
        int s2 = c.readInt(); //4
        Sixlet bunch[] = c.readArray(Sixlet.class, s2); //vertex and vertex normal (vertex normal is average of face normals of adjacent faces.)
        Quat[] quats = null; //I think this is actually a RGBA colour.
        if(s1!=2)
        {
            quats = c.readArray(Quat.class, s2);
        }
        
        int u21 = c.readInt(); //e.ensure(u21,0);
        Vertex[] trips = c.readArray(Vertex.class, u21); //uvw maybe?
        
        if(u6==1) //u7 and u8 are also 1 in this case.
        {
            //int xu1 = c.readInt(); e.ensure(xu1,0);
            int xu2 = c.readInt(); e.ensure(xu2,0);
            int xu3 = c.readInt(); e.ensure(xu3,3);
            int xu4 = c.readInt(); e.ensure(xu4,0);
            int xu5 = c.readInt(); e.ensure(xu5,18433);
            int xu6 = c.readInt(); e.ensure(xu6,1);
            int xu7 = c.readInt(); e.ensure(xu7,0);
            Sixlet xu8 = new Sixlet(c);
            int xu9 = c.readInt(); e.ensure(xu9,0);
            int dummy=0;
            
            repeat r1 = new repeat(c);
            int xub3 = c.readInt(); e.ensure(xub3,0);
            int xub4 = c.readInt(); e.ensure(xub4,0);
            int xub5 = c.readInt(); e.ensure(xub5,18433);
            int xub6 = c.readInt(); e.ensure(xub6,1);
            int xub7 = c.readInt(); e.ensure(xub7,0);
            Sixlet xub8 = new Sixlet(c);
            int xub9 = c.readInt(); e.ensure(xub9,0);
            
            repeat r2 = new repeat(c);
            int xuc3 = c.readInt(); e.ensure(xuc3,0);
            int xuc4 = c.readInt(); e.ensure(xuc4,0);
            int xuc5 = c.readInt(); e.ensure(xuc5,18433);
            int xuc6 = c.readInt(); e.ensure(xuc6,1);
            int xuc7 = c.readInt(); e.ensure(xuc7,0);
            Sixlet xuc8 = new Sixlet(c);
            int xuc9 = c.readInt(); e.ensure(xuc9,0);
            
            repeat r3 = new repeat(c);
            
            int x10 = c.readInt(); e.ensure(x10,0);
            int x11 = c.readInt(); e.ensure(x11,5);
            huh[] huhs = c.readArray(huh.class, x11);
            
            int[] extraints = c.readInts(6);
            
            return;
        }
        
        int u22 = c.readInt(); e.ensure(u22,128);
        
        int u23 = c.readInt(); //e.ensure(u23,4);
        Face[] fs = c.readArray(Face.class, u23); //this maps from the longer lists to the shorter lists; or maybe it gives multiple items from the longer lists given an item from the shorter lists         //or should this be u23 instead of s2?
        
        int u24 = c.readInt(); //e.ensure(u24,2);
        wha[] whas = c.readArray(wha.class, u24);

        
        
        if(true)
        {
            /*Vertex[] verts = new Vertex[bunch.length];
            for(int i=0;i<bunch.length;i++)
            {
                Flt x = bunch[i].f1;
                Flt y = bunch[i].f2;
                Flt z = bunch[i].f3;
                verts[i] = Vertex.createFromFlts(x, y, z);
            }*/
            Vertex[] verts = new Vertex[fs.length];
            for(int i=0;i<fs.length;i++)
            {
                int to = fs[i].v2;
                int from = fs[i].v3;
                Flt x= bunch[to].f1;
                Flt y= bunch[to].f2;
                Flt z= bunch[to].f3;
                verts[i] = Vertex.createFromFlts(x, y, z);
            }
            
            ShortTriplet[] faces = new ShortTriplet[whas.length];
            for(int i=0;i<whas.length;i++)
            {
                int v1 = whas[i].u6;
                int v2 = whas[i].u7;
                int v3 = whas[i].u8;
                faces[i] = ShortTriplet.createFromShorts((short)v1, (short)v2, (short)v3);
            }
            
            String objname = this.name.toString();
            
            if(main==null)
            {
                main = Primary.createNull();
                main.meshdata.mat = Material.create("defaultmat");
            }
            NamedObj newobj = NamedObj.createNull(objname);
            newobj.namedTriangleObject = NamedTriangleObject.createNull();
            newobj.namedTriangleObject.points = PointArray.create(verts);
            newobj.namedTriangleObject.faces = FaceArray.create(faces, "defaultmat");
            main.meshdata.objs.add(newobj);
            
            IBytedeque out = new Bytedeque2();
            main.compile(out);
            byte[] filedata = out.getAllBytes();
            FileUtils.WriteFile("c:/test.3ds", filedata);
        }
        if(true)return;
        
        
        
        
        int u25 = c.readInt(); //???
        
        Face[] fs2 = c.readArray(Face.class, u24);
        
        //int u26 = c.readInt(); //6  6  6  4  3
        //int u27 = c.readInt(); //0  0  0  0  0
        //int u28 = c.readInt(); //6  6  6  2  16
        //int u29 = c.readInt(); //22 18 18 6  28
        //int u30 = c.readInt(); //30 24 24 10 2
        //int u31 = c.readInt(); //49 43 43 3
        //int u32 = c.readInt(); //59 53 53
        //int u33 = c.readInt(); //5  5  5
        int u26 = c.readInt();
        int[] extra = c.readInts(u26-1); //n-1 extra ints.
        int u32 = c.readInt();
        int u33 = c.readInt();
        int[] intsIndex = c.readInts(u33);//u32+u33);//u21);
        if(u33!=1) //if there's only one index, I think it ignores these, since they must all be that value.
        {
            int[] ints = c.readInts(u32);
        }
        else
        {
            int dummy=0;
        }
        
        int dummy=0;
    }
    public static class huh
    {
        public huh(IBytestream c)
        {
            short u1 = c.readShort();
            short u2 = c.readShort();
            int u3 = c.readInt();
            short u4 = c.readShort();
            int u5 = c.readInt();
            Flt f6 = new Flt(c);
            int u7 = c.readInt();
            Flt f8 = new Flt(c);
            int u9 = c.readInt();
            int u10 = c.readInt();
            Sixlet s11 = new Sixlet(c);
        }
    }
    public static class repeat
    {
        public repeat(IBytestream c)
        {
            int u22 = c.readInt(); e.ensure(u22,128);

            int u23 = c.readInt(); //e.ensure(u23,4);
            Face[] fs = c.readArray(Face.class, u23); //or should this be u23 instead of s2?

            int u24 = c.readInt(); //e.ensure(u24,2);
            wha[] whas = c.readArray(wha.class, u24);
        }
    }
    public static class wha
    {
        int u1; //tag?
        Flt f2; //normal x
        Flt f3; //normal y
        //int u4;
        Flt f4; //normal z
        Flt f5; //dot product of normal and vertex.
        int u6; //face vert1
        int u7; //face vert2
        int u8; //face vert3
        Flt f9;  //vertex x
        Flt f10; //vertex y
        Flt f11; //vertex z
        Flt f12;
        
        public wha(IBytestream c)
        {
            u1 = c.readInt();
            f2 = new Flt(c);
            f3 = new Flt(c);
            //u4 = c.readInt();
            f4 = new Flt(c);
            f5 = new Flt(c);
            u6 = c.readInt();
            u7 = c.readInt();
            u8 = c.readInt();
            f9 = new Flt(c);
            f10 = new Flt(c);
            f11 = new Flt(c);
            f12 = new Flt(c);
        }
    }
    public static class Sixlet
    {
        Flt f1;
        Flt f2;
        Flt f3;
        Flt f4;
        Flt f5;
        Flt f6;
        
        public Sixlet(IBytestream c)
        {
            f1 = new Flt(c);
            f2 = new Flt(c);
            f3 = new Flt(c);
            f4 = new Flt(c);
            f5 = new Flt(c);
            f6 = new Flt(c);
        }
        
        public String toString()
        {
            String s = "  ";
            String result = "("+f1.toString()+s+f2.toString()+s+f3.toString()+s+f4.toString()+s+f5.toString()+s+f6.toString()+s+")";
            return result;
        }
    }
}
