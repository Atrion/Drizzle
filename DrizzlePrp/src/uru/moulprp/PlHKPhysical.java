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
import uru.e;
import shared.m;
import uru.b;
import shared.FileUtils;
//import java.util.Vector;


//Not working fully!
//About the <=256 branches: 256 should be the right number, but in any event, I tested to find that x is such that: 254<=x<262.
public class PlHKPhysical extends uruobj
{
    int _version; //do not compile!
    
    //plHKPhysical
    HKPhysical havok;
    
    //plPXPhysical
    PXPhysical physx;
    
    //plODEPHysical
    ODEPhysical ode;
    
    public static potsflags convertMoulFlagsToPotsFlags(moulflags moul, String objname)
    {
        potsflags pots = new potsflags();
        byte u14 = moul.u14;
        int u15 = moul.u15;
        short LOSDB = moul.LOSDB;
        int group0 = moul.group0;
        
        /*if( u14==0x4 && u15==0x0 && LOSDB==0x0 && group0==0x200 )
        {
            //kickable.
            //works! See soccer ball in Minkata.
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x100;
            pots.zzzflagsdetect = 0x0;
            pots.zzzflagsrespond = 0x3800000;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = 0x0;
            pots.zzzgroup0 = 0x200;
        }*/
        if( u14==0x4 )
        {
            //kickable 2. Jalak and Minkata are the only Ages with kickables (Minkata has the soccer ball).
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x100;
            pots.zzzflagsdetect = 0x0;
            pots.zzzflagsrespond = 0x3800000;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = 0x0;
            pots.zzzgroup0 = group0;
        }
        else if(( u14==0x0 && u15==0x0 && LOSDB==0x45 && group0==0x0 )
                ||( u14==0x0 && u15==0x0 && LOSDB==0x40 && group0==0x0 )
                ||( u14==0x0 && u15==0x0 && LOSDB==0x44 && group0==0x0 ))
        {
            //ordinary collision surface.
            //works! See ground in Minkata.
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x200;
            pots.zzzflagsdetect = 0x0;
            pots.zzzflagsrespond = 0x0;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = LOSDB; //bugfix.
            pots.zzzgroup0 = 0x0;
        }
        else if(( u14==0x5 && u15==0x8 && LOSDB==0x0 && group0==0x0 )
                ||( u14==0x5 && u15==0x8 && LOSDB==0x0 && group0==0x4 ))
            //ladders
        //else if( u14==0x5 )
        {
            //detector: sitting, etc.
            //footprint sounds. needs a responderModifier too, I think.
            //works when mass is set.
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x400;
            pots.zzzflagsdetect = 0x8000000;
            pots.zzzflagsrespond = 0x0;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = 0x0;
            pots.zzzgroup0 = 0x4;
            
            pots.givemass = true;
        }
        else if( u14==0x6 && u15==0x0 && LOSDB==0x2 && group0==0x0 )
        {
            //books?
            //mass not needed coming from moul
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x400;
            pots.zzzflagsdetect = 0x0;
            pots.zzzflagsrespond = 0x20000;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = 0x2;
            pots.zzzgroup0 = 0x4;
            
            //pots.givemass = true; //delete this.
        }
        else if( u14==0x0 && u15==0x0 && LOSDB==0x45 && group0==0x120 )
        {
            //jalak columns
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x200;
            pots.zzzflagsdetect = 0x0;
            pots.zzzflagsrespond = 0x0;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = 0x45;
            pots.zzzgroup0 = 0x104;
        }
        else if(( u14==0x0 && u15==0x0 && LOSDB==0x2 && group0==0x0 )
                ||( u14==0x0 && u15==0x0 && LOSDB==0x2 && group0==0x20 ))
        {
            //clickable, works!
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x400;
            pots.zzzflagsdetect = 0x0;
            pots.zzzflagsrespond = 0x20000;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = 0x2;
            pots.zzzgroup0 = 0x4;

            //pots.givemass = true; //delete this.
        }
        else if( u14==0x0 && u15==0x0 && LOSDB==0x0 && group0==0x0 ) //ladders bounds
        {
            //mass does not always need to be set, coming from moul.
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x200;
            pots.zzzflagsdetect = 0x0;
            pots.zzzflagsrespond = 0x20000;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = 0x0;
            pots.zzzgroup0 = 0x4;

            pots.givemass = true; //delete this.
        }
        //new ones
        else if( u14==0x6 && u15==0x0 && LOSDB==0x4 && group0==0x0 )
        {
            //cameras?
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x0;
            pots.zzzflagsdetect = 0x0;
            pots.zzzflagsrespond = 0x0;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = 0x4;
            pots.zzzgroup0 = 0x0;

        }
        else if( u14==0x6 && u15==0x0 && LOSDB==0x2 && group0==0x20 )
        {
            //e.g. clkBSDoor in delin
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x400;
            pots.zzzflagsdetect = 0x0;
            pots.zzzflagsrespond = 0x0;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = 0x2;
            pots.zzzgroup0 = 0x4;

        }
        else if( u14==0x5 && u15==0x8 && LOSDB==0x0 && group0==0x120 )
        {
            //e.g. PanicRegion in delin.
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x400;
            pots.zzzflagsdetect = 0x8000000;
            pots.zzzflagsrespond = 0x0;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = 0x0;
            pots.zzzgroup0 = 0x104;

        }
        else if( u14==0x0 && u15==0x0 && LOSDB==0x5 && group0==0x124 )
        {
            //e.g. PanicRegion in delin.
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x400;
            pots.zzzflagsdetect = 0x1020000;
            pots.zzzflagsrespond = 0x0;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = 0x5;
            pots.zzzgroup0 = 0x124; //just a guess.

        }
        else if( u14==0x5 && u15==0x18 && LOSDB==0x0 && group0==0x0 )
        {
            //e.g. some stuff in Jalak
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x400;
            pots.zzzflagsdetect = 0x1020000;
            pots.zzzflagsrespond = 0x0;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = 0x0;
            pots.zzzgroup0 = 0x4;
            pots.givemass = true; //otherwise wall detectors don't work.
        }
        else if( u14==0x5 && u15==0x8 && LOSDB==0x0 && group0==0x20 )
        {
            //e.g. some stuff in Jalak
            pots.zzzu1 = 0x0;
            pots.zzzcoltype = 0x400;
            pots.zzzflagsdetect = 0x8000000;
            pots.zzzflagsrespond = 0x0;
            pots.zzzu2 = 0x0;
            pots.zzzu3 = 0x0;
            pots.zzzLOSDB = 0x0;
            pots.zzzgroup0 = 0x4;

        }
        else
        {
            if(shared.State.AllStates.getStateAsBoolean("reportPhysics"))
            {
                m.warn("plHKPhysical: Unhandled flag combination.: u14=0x"+Integer.toHexString(u14)+";u15=0x"+Integer.toHexString(u15)+";losdb=0x"+Integer.toHexString(LOSDB)+";group0=0x"+Integer.toHexString(group0)+";name="+objname);
            }
            return null;
        }
        return pots;
    }
    
    public static class potsflags
    {
        //int zzzformat = b.ByteToInt32(format);
        short zzzu1 = 0x0;
        short zzzcoltype = 0x200;
        int zzzflagsdetect = 0x0;
        int zzzflagsrespond = 0x0;
        byte zzzu2 = 0x0;
        byte zzzu3 = 0x0;
        int zzzLOSDB = 0x0;
        //HsBitVector zzzgroup = new HsBitVector(0x0);
        int zzzgroup0 = 0x0;
        
        //extras
        boolean givemass = false;
        
        public potsflags()
        {
        }
    }
    
    public static class moulflags
    {
        byte u14;
        int u15;
        short LOSDB;
        int group0;
        
        public moulflags()
        {
        }
    }
    
    
    public PlHKPhysical(context c) throws readexception
    {
        String filenameStart = "";
        String filenameEnd = "";
        String objt = "ropeladder";
        if(c.curFile.toLowerCase().startsWith(filenameStart.toLowerCase()))
        {
            if(c.curFile.toLowerCase().endsWith(filenameEnd.toLowerCase()))
            {
                if(c.curRootObject.objectname.toString().toLowerCase().startsWith(objt.toLowerCase()))
                {
                    int dummy=0;
                }
            }
        }
        
        if(c.readversion==6)
        {
            _version = 6;
            physx = new PXPhysical(c);
        }
        else if(c.readversion==4)
        {
            _version = 4;
            ode = new ODEPhysical(c);
        }
        else if(c.readversion==3)
        {
            _version = 3;
            havok = new HKPhysical(c);
        }
        
    }
    public void compile(Bytedeque c)
    {
        if(_version==6)
        {
            physx.compileSpecial(c);
        }
        else if(_version==4)
        {
            ode.compileSpecial(c);
        }
        else if(_version==3)
        {
            havok.compile(c);
        }
        else
        {
            m.err("plhkphysical: unknown version in compile.");
        }
    }
    
    public static class PXPhysical extends uruobj
    {
        PlSynchedObject parent;
        Flt mass;
        Flt RC;
        Flt EL;
        byte format;
        byte u14; //6,4,2,1 //5 I think I missed.
        int u15; //8,18,10
        short LOSDB;
        Uruobjectref sceneobject;
        Uruobjectref scenenode;
        Uruobjectref subworld;
        Uruobjectref soundgroup;
        Vertex position;
        Quat orientation;
        HsBitVector group; //4,20,200,24,120,100,124,300,700  //count is always 1 in moul
        PXSphereBounds xu24a;
        PXBoxBounds xu24b;
        PXConeBounds xu24c;
        PXMeshBounds xu24d;

        public PXPhysical(context c) throws readexception
        {
            if(c.curRootObject.objectname.toString().toLowerCase().startsWith("box01"))
            {
                int dummy=0;
            }
            //not fully working.
            //int dummy;
            parent = new PlSynchedObject(c);
            mass = new Flt(c); //0
            //if(mass.toJavaFloat()!=(float)0)
            //    dummy=0;
            RC = new Flt(c); //0.5 //RC?
            EL = new Flt(c); //0
            //if(EL.toJavaFloat()!=(float)0)
            //    dummy=0;
            format = c.readByte(); //4 //format?
            u14 = c.readByte(); //0
            /*if(u14!=0 && u14!=5)
            {
                m.msg("u14="+Integer.toHexString(u14));
                dummy=0;
            }*/
            u15 = c.readInt(); //0
            /*if(u15!=0)
            {
                m.msg("u15="+Integer.toHexString(u15));
                dummy=0;
            }*/
            LOSDB = c.readShort(); //68 //LOSDB?
            sceneobject = new Uruobjectref(c); //sceneobject?
            scenenode = new Uruobjectref(c); //scenenode?
            subworld = new Uruobjectref(c); //subworld??
            //if(subworld.hasRef!=0)
            //    dummy=0;
            soundgroup = new Uruobjectref(c); //soundgroup??
            //if(soundgroup.hasRef!=0)
            //    dummy=0;
            position = new Vertex(c); //position?
            orientation = new Quat(c); //orientation?
            group = new HsBitVector(c); //{0} //like u4?
            /*if(group.count!=1)
                dummy=0;
            if(group.values[0]!=0)
            {
                m.msg("group="+Integer.toHexString(group.values[0]));
                dummy=0;
            }*/
            if(format==2) //like SphereBounds?
            {
                xu24a = new PXSphereBounds(c);
            }
            else if(format==1) //in Kaufmann's book, this could be used for a Bounding Box(cubic).
            {
                xu24b = new PXBoxBounds(c);
            }
            else if(format==3)
            {
                //calls PhysXCore.dll and gives it an object to read from the stream with.
                xu24c = new PXConeBounds(c);
            }
            else
            {
                //calls PhysXCore.dll and gives it an object to read from the stream with.
                //I pretty sure it's a cooked PhysX mesh.
                //here's a partial attempt: (along with some example values)
                xu24d = new PXMeshBounds(c);
            }
            
            if(shared.State.AllStates.getStateAsBoolean("reportPhysics"))
            {
                //String text = "file="+c.curFile+"   objname="+c.curRootObject.objectname.toString()+"   format="+Integer.toHexString(format)+"   u14="+Integer.toHexString(u14)+"   u15="+Integer.toHexString(u15)+"   losdb="+Integer.toHexString(LOSDB)+"   group="+Integer.toHexString(group.values[0]);
                String text = "Physics:"+Integer.toHexString(format)+","+Integer.toHexString(u14)+","+Integer.toHexString(u15)+","+Integer.toHexString(LOSDB)+","+Integer.toHexString(group.values[0])+","+c.curFile+","+c.curRootObject.objectname.toString();
                //FileUtils.AppendText(_staticsettings.outputdir+"pxphysical.txt", text+"\n");
                m.msg(text);
            }

            //This block checks to see if we handle this combination of flags.
            moulflags moul = new moulflags();
            moul.u14 = u14;
            moul.u15 = u15;
            moul.LOSDB = LOSDB;
            moul.group0 = group.values[0];
            potsflags pots = PlHKPhysical.convertMoulFlagsToPotsFlags(moul,c.curRootObject.toString());
            if(pots==null)
            {
                throw new shared.readwarningexception("plHKPhysical: Can read okay, but failing in order to ignore.");
            }
        }

        public void compileSpecial(Bytedeque c)
        {
            //This block converts the flags from moul to pots.
            moulflags moul = new moulflags();
            moul.u14 = u14;
            moul.u15 = u15;
            moul.LOSDB = LOSDB;
            moul.group0 = group.values[0];
            potsflags pots = PlHKPhysical.convertMoulFlagsToPotsFlags(moul,"");
            if(pots==null)
            {
                m.err("plHKPhysical: Unexpected combination.");
            }
            //int zzzformat = b.ByteToInt32(format);
            short zzzu1 = pots.zzzu1;
            short zzzcoltype = pots.zzzcoltype;
            int zzzflagsdetect = pots.zzzflagsdetect;
            int zzzflagsrespond = pots.zzzflagsrespond;
            byte zzzu2 = pots.zzzu2;
            byte zzzu3 = pots.zzzu3;
            int zzzLOSDB = pots.zzzLOSDB;
            HsBitVector zzzgroup = new HsBitVector(pots.zzzgroup0);
            
            //extras
            if(pots.givemass) mass = Flt.one();

            
            //compile as if it were an HKPhysical.
            parent.compile(c);
            position.compile(c);
            orientation.compile(c);
            //m.msg("compiling quat differently.");
            //orientation.x.compile(c);
            //orientation.y.compile(c);
            //orientation.z.compile(c);
            //orientation.w.compile(c);
            mass.compile(c);
            RC.compile(c);
            EL.compile(c);
            
            
            //c.writeInt(zzzformat);
            c.writeInt(b.ByteToInt32(format));
            c.writeShort(zzzu1); // u1; only non-zero on one-object in Cleft.

            //coltype: not at all right!
            c.writeShort(zzzcoltype);
            
            //flagsdetect:
            c.writeInt(zzzflagsdetect);
            /*switch(u15) 
            {
                case 0x00:
                    c.writeInt(0x00);
                    break;
                case 0x08:
                    //c.writeInt(0x020000); //also occurs, but much rarer.
                    c.writeInt(0x08000000);
                    break;
                case 0x10:
                    c.writeInt(0x800000);
                    break;
                case 0x18:
                    c.writeInt(0x01020000);
                    break;
                default:
                    m.err("plhkphysical: Unhandled write case.");
                    break;
            }*/
            
            //flagsrespond: this is way off!
            c.writeInt(zzzflagsrespond);
            /*switch(u14)
            {
                case 0x00:
                    switch(LOSDB)
                    {
                        case 0x01:
                        case 0x05:
                            c.writeInt(0x01020000);
                            break;
                        case 0x02:
                            c.writeInt(0x00020000);
                            break;
                        case 0x04:
                            c.writeInt(0x00000000);
                            break;
                        default:
                            m.msg("Unhandled.");
                            break;
                    }
                    break;
                case 0x02:
                    c.writeInt(0x01000000);
                    break;
                case 0x04:
                    c.writeInt(0x03800000);
                    //c.writeInt(0x03800002);
                    //c.writeInt(0x03800004);
                    break;
                case 0x05:
                    c.writeInt(0x00000000);
                    break;
            }*/
            
            c.writeByte(zzzu2); //u2; only non-zero on great-stairs.
            c.writeByte(zzzu3); //u3; only non-zero on great-stairs.
            
            switch(format)
            {
                case 0x01: //box
                    xu24b.compileSpecial(c);
                    break;
                case 0x02: //sphere
                    xu24a.compileSpecial(c);
                    break;
                case 0x03: //hull
                    xu24c.compileSpecial(c);
                    break;
                case 0x04: //proxy
                case 0x05: //explicit
                    xu24d.compileSpecial(c);
                    break;
                default:
                    m.err("Unknown hkphysical flag");
                    break;
            }

            sceneobject.compile(c);
            zzzgroup.compile(c);
            scenenode.compile(c);
            c.writeInt(zzzLOSDB);
            subworld.compile(c);
            soundgroup.compile(c);
        }
        
        public void compile(Bytedeque c)
        {
            m.err("hkphysical: compile not implemented.");
        }
        
    }
    
    public static class HKPhysical extends uruobj
    {
        PlSynchedObject parent;
        Vertex position;
        Quat orientation;
        Flt mass; //mass
        Flt RC; //friction coefficient
        Flt EL; //elasticity
        int format;
        short u1; //2,
        short coltype; //200,400,100,2
        int flagsdetect; //8000000,1020000,20000,800000,1000000
        int flagsrespond; //1020000,20000,1800002,3800000,3820000,3800002,3840000,3860000,2000000,3840002,1000000,3800004
        byte u2; //1
        byte u3; //1
        HKBoxBounds xboxbounds;
        HKSphereBounds xspherebounds;
        HKHullBounds xhullbounds;
        HKProxyBounds xproxybounds;
        HKExplicitBounds xexplicitbounds;
        Uruobjectref sceneobject;
        HsBitVector group; //0,4,200,104,100,10,80,24,a4,300,124,184,2000,700  //count always equals 1.
        Uruobjectref scenenode;
        int LOSDB; //45,2,44,5,4,80,40,1,20,41,
        Uruobjectref subworld;
        Uruobjectref soundgroup;
        
        private HKPhysical(){}
        public static HKPhysical createBlank()
        {
            return new HKPhysical();
        }
        
        public HKPhysical(context c) throws readexception
        {
            parent = new PlSynchedObject(c);
            position = new Vertex(c);
            orientation = new Quat(c);
            mass = new Flt(c);
            RC = new Flt(c);
            EL = new Flt(c);
            format = c.readInt();
            u1 = c.readShort();
            //if(u1!=0) m.msg("u1="+Integer.toHexString(u1));
            coltype = c.readShort();
            //if(coltype!=0) m.msg("coltype="+Integer.toHexString(coltype));
            flagsdetect = c.readInt();
            //if(flagsdetect!=0) m.msg("flagsdetect="+Integer.toHexString(flagsdetect));
            flagsrespond = c.readInt();
            //if(flagsrespond!=0) m.msg("flagsrespond="+Integer.toHexString(flagsrespond));
            u2 = c.readByte();
            //if(u2!=0) m.msg("u2="+Integer.toHexString(u2));
            u3 = c.readByte();
            //if(u3!=0) m.msg("u3="+Integer.toHexString(u3));
            switch(format)
            {
                case 0x01: //box
                    xboxbounds = new HKBoxBounds(c);
                    break;
                case 0x02: //sphere
                    xspherebounds = new HKSphereBounds(c);
                    break;
                case 0x03: //hull
                    xhullbounds = new HKHullBounds(c);
                    break;
                case 0x04: //proxy
                    xproxybounds = new HKProxyBounds(c);
                    break;
                case 0x05: //explicit
                    xexplicitbounds = new HKExplicitBounds(c);
                    break;
                default:
                    m.err("Unknown hkphysical flag");
                    break;
            }

            sceneobject = new Uruobjectref(c);
            group = new HsBitVector(c);
            /*if(group.count==1)
            {
                m.msg("group="+Integer.toHexString(group.values[0]));
            }
            else
            {
                int dummy=0;
            }*/
            scenenode = new Uruobjectref(c);
            LOSDB = c.readInt();
            //if(LOSDB!=0) m.msg("LOSDB="+Integer.toHexString(LOSDB));
            subworld = new Uruobjectref(c);
            soundgroup = new Uruobjectref(c);

            if(shared.State.AllStates.getStateAsBoolean("reportPhysics"))
            {
                //String text = "file="+c.curFile+"   objname="+c.curRootObject.objectname.toString()+"   format="+Integer.toHexString(format)+"   u1="+Integer.toHexString(u1)+"   coltype="+Integer.toHexString(coltype)+"   flagsdetect="+Integer.toHexString(flagsdetect)+"   flagsrespond="+Integer.toHexString(flagsrespond)+"   u2="+Integer.toHexString(u2)+"   u3="+Integer.toHexString(u3)+"   losdb="+Integer.toHexString(LOSDB)+"   group="+Integer.toHexString(group.values[0]);
                String text = "Physics:"+c.curFile+","+c.curRootObject.objectname.toString()+","+Integer.toHexString(format)+","+Integer.toHexString(u1)+","+Integer.toHexString(coltype)+","+Integer.toHexString(flagsdetect)+","+Integer.toHexString(flagsrespond)+","+Integer.toHexString(u2)+","+Integer.toHexString(u3)+","+Integer.toHexString(LOSDB)+","+Integer.toHexString(group.values[0]);
                //FileUtils.AppendText(_staticsettings.outputdir+"hkphysical.txt", text+"\n");
                m.msg(text);
            }
            
            
        }
        
        public void compile(Bytedeque c)
        {
            m.err("hkphysical: compile not implemented.");
        }
        
    }
    
    public static class HKBoxBounds extends uruobj
    {
        HKProxyBounds parent;
        
        public HKBoxBounds(context c) throws readexception
        {
            parent = new HKProxyBounds(c);
        }
    }
    public static class HKSphereBounds extends uruobj
    {
        //HKBounds parent;
        Vertex offset;
        Flt radius;
        
        public HKSphereBounds(context c) throws readexception
        {
            //parent = new HKBounds(c);
            offset = new Vertex(c);
            radius = new Flt(c);
        }
    }
    public static class HKHullBounds extends uruobj
    {
        //HKBounds parent;
        int vertexcount;
        Vertex[] vertices;
        
        public HKHullBounds(context c) throws readexception
        {
            //parent = new HKBounds(c);
            vertexcount = c.readInt();
            vertices = c.readVector(Vertex.class, vertexcount);
        }
    }
    public static class HKProxyBounds extends uruobj
    {
        HKHullBounds parent;
        int facecount;
        ShortTriplet[] faces;
        
        public HKProxyBounds(context c) throws readexception
        {
            parent = new HKHullBounds(c);
            facecount = c.readInt();
            faces = c.readVector(ShortTriplet.class, facecount);
        }
    }
    public static class HKExplicitBounds extends uruobj
    {
        HKProxyBounds parent;
        
        public HKExplicitBounds(context c) throws readexception
        {
            parent = new HKProxyBounds(c);
        }
    }
    
    public static class PXSphereBounds
    {
        Flt radius;
        Vertex offset;
        
        public PXSphereBounds(context c) throws readexception
        {
                radius = new Flt(c);
                offset = new Vertex(c);
        }
        
        public void compileSpecial(Bytedeque c)
        {
            offset.compile(c);
            radius.compile(c);
        }
    }
    
    public static class PXBoxBounds
    {
        Vertex cornervector; //one of these is the center and the other is the half-size.
        Vertex center; //one of these is the center and the other is the half-size.
        
        public PXBoxBounds(context c) throws readexception
        {
                cornervector = new Vertex(c);
                center = new Vertex(c);
        }
        
        //This is the exact same ordering used by pots, and the chiralities of all the faces point outwards according to the right-hand rule.
        public void compileSpecial(Bytedeque c)
        {
            int vertexcount = 8;
            int facecount = 12;
            Vertex[] vertices = new Vertex[]{
                new Vertex(center.x.sub(cornervector.x),center.y.sub(cornervector.y),center.z.sub(cornervector.z)),
                new Vertex(center.x.add(cornervector.x),center.y.sub(cornervector.y),center.z.sub(cornervector.z)),
                new Vertex(center.x.sub(cornervector.x),center.y.add(cornervector.y),center.z.sub(cornervector.z)),
                new Vertex(center.x.add(cornervector.x),center.y.add(cornervector.y),center.z.sub(cornervector.z)),
                new Vertex(center.x.sub(cornervector.x),center.y.sub(cornervector.y),center.z.add(cornervector.z)),
                new Vertex(center.x.add(cornervector.x),center.y.sub(cornervector.y),center.z.add(cornervector.z)),
                new Vertex(center.x.sub(cornervector.x),center.y.add(cornervector.y),center.z.add(cornervector.z)),
                new Vertex(center.x.add(cornervector.x),center.y.add(cornervector.y),center.z.add(cornervector.z)),
            };
            short[] faces = new short[]{
                0,2,1,
                1,2,3,
                0,1,4,
                1,5,4,
                0,4,2,
                2,4,6,
                1,3,7,
                7,5,1,
                3,2,7,
                2,6,7,
                4,7,6,
                4,5,7,
            };
            //reverse-chirality:
            /*short[] faces = new short[]{
                0,1,2,
                1,3,2,
                0,4,1,
                1,4,5,
                0,2,4,
                2,6,4,
                1,7,3,
                7,1,5,
                3,7,2,
                2,7,6,
                4,6,7,
                4,7,5,
            };*/
            
            e.ensure(vertices.length==vertexcount);
            e.ensure(faces.length==facecount*3);
            
            c.writeInt(vertexcount);
            c.writeVector(vertices);
            c.writeInt(facecount);
            c.writeShorts(faces);
        }
    }
    
    public static class PXConeBounds
    {
        byte[] yheader1;
        int y1;
        int y2;
        byte[] yheader2;
        int y3;
        byte[] yheader3;
        int y4;
        int vertexcount;
        int surfacecount;
        int y7;
        int y8;
        int y9;
        int y10;
        Vertex[] vertices;
        int y11;
        byte[] xy12a;
        short[] xy12b;
        
        public PXConeBounds(context c) throws readexception
        {
            yheader1 = c.readBytes(8); //header "NXS\0x01CVXM
            if(!b.BytesToString(yheader1).equals("NXS\u0001CVXM"))
            {
                int dummy=0;
            }
            y1 = c.readInt(); //
            y2 = c.readInt(); //
            yheader2 = c.readBytes(8); //header "ICE\0x01CLHL
            if(!b.BytesToString(yheader2).equals("ICE\u0001CLHL"))
            {
                int dummy=0;
            }
            y3 = c.readInt(); //
            yheader3 = c.readBytes(8); //header "ICE\0x01CVHL
            if(!b.BytesToString(yheader3).equals("ICE\u0001CVHL"))
            {
                int dummy=0;
            }
            y4 = c.readInt(); //
            vertexcount = c.readInt(); //vertexcount?
            surfacecount = c.readInt(); //surfacecount?
            y7 = c.readInt(); //
            y8 = c.readInt(); //
            y9 = c.readInt(); //
            y10 = c.readInt(); //

            //read vertices
            vertices = c.readVector(Vertex.class, vertexcount); //vertices

            y11 = c.readInt(); //highest index(=y5-1)
            /*if(y11!=vertexcount-1)
            {
                int dummy=0;
            }*/
            //read surfaces
            /*if(vertexcount>254 && vertexcount <262)
            {
                int dummy=0;
            }*/
            if(vertexcount<=256) //this may need tweeking.
            {
                xy12a = c.readBytes(3*surfacecount); //surfaces
            }
            else
            {
                xy12b = c.readShorts(3*surfacecount); //surfaces
            }

            //String text = "   y1="+Integer.toHexString(y1)+"   y2="+Integer.toHexString(y2)+"   y3="+Integer.toString(y3)+"   y4="+Integer.toHexString(y4)+"   vertexcount="+Integer.toHexString(vertexcount)+"   surfacecount="+Integer.toHexString(surfacecount)+"   y7="+Integer.toHexString(y7)+"   y8="+Integer.toHexString(y8)+"   y9="+Integer.toHexString(y9)+"   y10="+Integer.toHexString(y10);
            //FileUtils.AppendText(_staticsettings.outputdir+"pxphysical.txt", text+"\n");
        }
        
        public void compileSpecial(Bytedeque c)
        {
            c.writeInt(vertexcount);
            e.ensure(vertices.length==vertexcount);
            c.writeVector(vertices);
        }
    }
    
    public static class PXMeshBounds
    {
        byte[] header;
        int u28;
        int u29;
        Flt u30;
        int u31;
        int u32;
        int vertexcount;
        int surfacecount;
        Vertex[] vertices;
        byte[] xu36a;
        short[] xu36b;
        int u37;
        byte[] xu38a;
        short[] xu38b;
        
        public PXMeshBounds(context c) throws readexception
        {
            header = c.readBytes(8); //header "NXS\0x01MESH
            if(!b.BytesToString(header).equals("NXS\u0001MESH"))
            {
                int dummy=0;
            }
            u28 = c.readInt(); //0,0
            u29 = c.readInt(); //18,10
            u30 = new Flt(c); //0.001,0.001
            u31 = c.readInt(); //255,255
            u32 = c.readInt(); //0,0
            vertexcount = c.readInt(); //526,24 //vertexcount?
            surfacecount = c.readInt(); //1044,44 //surfacecount?

            //read vertices
            vertices = c.readVector(Vertex.class, vertexcount); //vertices

            //read surfaces
            /*if(vertexcount>254 && vertexcount <262)
            {
                int dummy=0;
            }*/
            if(vertexcount<=256) //this may need tweeking.
            {
                xu36a = c.readBytes(3*surfacecount); //surfaces
            }
            else
            {
                xu36b = c.readShorts(3*surfacecount); //surfaces
            }

            u37 = c.readInt(); //u34-1=hightest index
            /*if(u37!=surfacecount-1)
            {
                int dummy=0;
            }*/
            
            //read permutation of surface indexes.
            if(surfacecount<=256)
            {
                xu38a = c.readBytes(surfacecount);
            }
            else
            {
                xu38b = c.readShorts(surfacecount);
            }

            //String text = "   u28="+Integer.toHexString(u28)+"   u29="+Integer.toHexString(u29)+"   u30="+u30.toString()+"   u31="+Integer.toHexString(u31)+"   u32="+Integer.toHexString(u32)+"   vertexcount="+Integer.toHexString(vertexcount)+"   surfacecount="+Integer.toHexString(surfacecount);
            //FileUtils.AppendText(_staticsettings.outputdir+"pxphysical.txt", text+"\n");
            
        }

        public void compileSpecial(Bytedeque c)
        {
            c.writeInt(vertexcount);
            e.ensure(vertices.length==vertexcount);
            c.writeVector(vertices);
            
            c.writeInt(surfacecount);
            if(vertexcount<=256) //this may need tweeking.
            {
                e.ensure(xu36a.length==surfacecount*3);
                for(int i=0;i<surfacecount*3;i++)
                {
                    short s1 = b.ByteToInt16(xu36a[i]);
                    c.writeShort(s1);
                }
                //c.writeBytes(xu36a);
            }
            else
            {
                e.ensure(xu36b.length==surfacecount*3);
                c.writeShorts(xu36b);
            }
        }
    }
    
}
