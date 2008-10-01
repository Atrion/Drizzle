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
import shared.m;
import shared.readexception;
import uru.Bytedeque;
import uru.b;
import uru.e;

public abstract class PrpMessage extends PrpTaggedObject
{

    public PrpMessage(context c) throws readexception
    {
        super(c);
    }
    
    //Template:
    /*public static class PlTemplate extends uruobj
    {
        PlMessage parent;
        
        public PlTemplate(context c) throws readexception
        {
            parent = new PlMessage(c);
            
        }
        
        public void compile(Bytedeque c)
        {
            m.warn("compile not implemented.");
            m.warn("compile: not tested with pots.");
        }
    }*/
    
    public static class PlSimSuppressMsg extends uruobj
    {
        PlMessage parent;
        byte b1;
        
        public PlSimSuppressMsg(context c) throws readexception
        {
            parent = new PlMessage(c);
            b1 = c.readByte();
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeByte(b1);
        }
    }
    
    public static class PlDampMsg extends uruobj
    {
        PlMessage parent;
        int u1;
        
        public PlDampMsg(context c) throws readexception
        {
            parent = new PlMessage(c);
            u1 = c.readInt();
        }
        
        public void compile(Bytedeque c)
        {
            m.warn("compile not implemented.");
            m.warn("compile: not tested with pots.");
        }
    }
    
    public static class PlRideAnimatedPhysMsg extends uruobj
    {
        PlMessage parent;
        byte u1;
        Uruobjectref u2;
        
        public PlRideAnimatedPhysMsg(context c) throws readexception
        {
            e.ensure(c.readversion==6);
            
            parent = new PlMessage(c);
            u1 = c.readByte();
            u2 = new Uruobjectref(c);
        }
    }
    
    public static class PlCameraMsg extends uruobj
    {
        PlMessage parent;
        HsBitVector cmd;
        Double64 transtime;
        byte activated;
        Uruobjectref newcam;
        Uruobjectref triggerer;
        PlCameraConfig cameraConfig;
        
        public PlCameraMsg(context c) throws readexception
        {
            parent = new PlMessage(c);
            cmd = new HsBitVector(c);
            transtime = new Double64(c);
            activated = c.readByte();
            newcam = new Uruobjectref(c);
            triggerer = new Uruobjectref(c);
            cameraConfig = new PlCameraConfig(c);
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            cmd.compile(c);
            transtime.compile(c);
            c.writeByte(activated);
            newcam.compile(c);
            triggerer.compile(c);
            cameraConfig.compile(c);
        }
        
        public static class PlCameraConfig extends uruobj
        {
            Flt accel;
            Flt decel;
            Flt vel;
            Flt FPaccel;
            Flt FPdecel;
            Flt FPvel;
            Flt FOVw;
            Flt FOVh;
            Vertex offset;
            byte worldspace;
            
            public PlCameraConfig(context c) throws readexception
            {
                accel = new Flt(c);
                decel = new Flt(c);
                vel = new Flt(c);
                FPaccel = new Flt(c);
                FPdecel = new Flt(c);
                FPvel = new Flt(c);
                FOVw = new Flt(c);
                FOVh = new Flt(c);
                offset = new Vertex(c);
                worldspace = c.readByte();
            }
            public void compile(Bytedeque c)
            {
                accel.compile(c);
                decel.compile(c);
                vel.compile(c);
                FPaccel.compile(c);
                FPdecel.compile(c);
                FPvel.compile(c);
                FOVw.compile(c);
                FOVh.compile(c);
                offset.compile(c);
                c.writeByte(worldspace);
            }
            
        }
    }
    
    //I reverse-engineered this myself, via decompilation.
    public static class PlEventCallbackMsg extends uruobj
    {
        PlMessage parent;
        Flt u1;
        short u2;
        short u3;
        short u4;
        short u5;
        
        public PlEventCallbackMsg(context c) throws readexception
        {
            parent = new PlMessage(c);
            u1 = new Flt(c);
            u2 = c.readShort();
            u3 = c.readShort();
            u4 = c.readShort();
            u5 = c.readShort();
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            u1.compile(c);
            c.writeShort(u2);
            c.writeShort(u3);
            c.writeShort(u4);
            c.writeShort(u5);
        }
    }

    //I reverse-engineered this myself, via decompilation.
    public static class PlTimerCallbackMsg extends uruobj
    {
        PlMessage parent;
        int u1;
        Flt u2;
        
        public PlTimerCallbackMsg(context c) throws readexception
        {
            parent = new PlMessage(c);
            u1 = c.readInt();
            u2 = new Flt(c);
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeInt(u1);
            u2.compile(c);
        }
    }
    
    //I reverse-engineered this myself, via decompilation.
    public static class PlAnimCmdMsg extends uruobj
    {
        PlMessageWithCallbacks parent;
        HsBitVector u1;
        Flt[] u2;
        Urustring u3;
        Urustring u4;
        
        public PlAnimCmdMsg(context c) throws readexception
        {
            parent = new PlMessageWithCallbacks(c);
            u1 = new HsBitVector(c);
            if(c.readversion==3||c.readversion==6)
            {
                //this is correct.
                u2 = c.readVector(Flt.class, 7);
            }
            else if(c.readversion==4)
            {
                u2 = new Flt[7];
                if(u1.count>0)
                {
                    //m.warn("PlAnimCmdMsg: untested case.");
                    int flags = u1.values[0];
                    //fill the array with 0, since that is what it normally(always?) is anyway.
                    for(int i=0;i<7;i++)
                    {
                        u2[i] = new Flt(0);
                    }
                    /*if((flags&0x10)!=0) u2[0] = new Flt(c);
                    if((flags&0x20)!=0) u2[1] = new Flt(c);
                    if((flags&0x80000000)!=0) u2[2] = new Flt(c);
                    if((flags&0x40)!=0) u2[3] = new Flt(c);
                    //the following 3 lines use BYTE1(), so i'm not sure which byte it uses.
                    if((flags&0x01000000)!=0) u2[4] = new Flt(c);
                    if((flags&0x01000000)!=0) u2[5] = new Flt(c);
                    //if((flags&0x02000000)!=0 || (flags&0x3252534F)!=0 || (flags&0x01000000)!=0) u2[6] = new Flt(c); //0x3252534F="OSR2"
                    if((flags&0x02000000)!=0 || (flags&0x3252534F)!=0 || (flags&0x01000000)!=0) u2[6] = new Flt(c); //0x3252534F="OSR2"*/
                }
            }
            u3 = new Urustring(c);
            u4 = new Urustring(c);
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            u1.compile(c);
            c.writeVector(u2);
            u3.compile(c);
            u4.compile(c);
        }
    }

    //I reverse-engineered this myself, via decompilation and a lot of pain!
    public static class PlLinkToAgeMsg extends uruobj
    {
        PlMessage parent;
        byte u1;
        PlAgeLinkStruct ageLinkStruct;
        Urustring ustr;
        
        HsBitVector xbv;
        Wpstr xs1;
        Wpstr xs2;
        HsBitVector xbv2;
        Wpstr xs3;
        Wpstr xs4;
        Wpstr xs5;
        HsBitVector xbv3;
        Wpstr xs6;
        int xi1;
        int xi2;
        int xi3;
        int xi4;
        byte xb;
        
        public PlLinkToAgeMsg(context c) throws readexception
        {
            parent = new PlMessage(c);
            if(c.readversion==6||c.readversion==3)
            {
                u1 = c.readByte();
                ageLinkStruct = new PlAgeLinkStruct(c);
                //c.readBytes(31);
                ustr = new Urustring(c);
            }
            else if(c.readversion==4)
            {
                xbv = new HsBitVector(c);
                int flag = xbv.get(0);
                if((flag&1)!=0) xs1 = new Wpstr(c);
                if((flag&2)!=0) xs2 = new Wpstr(c);
                if((flag&4)!=0)
                {
                    xbv2 = new HsBitVector(c);
                    int flag2 = xbv2.get(0);
                    if((flag2&1)!=0) xs3 = new Wpstr(c);
                    if((flag2&2)!=0) xs4 = new Wpstr(c);
                    if((flag2&4)!=0) xs5 = new Wpstr(c);
                
                }
                xbv3 = new HsBitVector(c);
                int flag3 = xbv3.get(0);
                if((flag3&1)!=0) xs6 = new Wpstr(c);
                if((flag3&2)!=0) xi1 = c.readInt();
                if((flag3&4)!=0) xi1 = c.readInt();
                if((flag3&8)!=0) xi1 = c.readInt();
                if((flag3&16)!=0) xi1 = c.readInt();
                xb = c.readByte();
                throw new shared.readwarningexception("PlLinkToAgeMsg: can read okay but failing in order to ignore.");
            }
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeByte(u1);
            ageLinkStruct.compile(c);
            ustr.compile(c);
        }
        
        public static class PlAgeLinkStruct extends uruobj
        {
            short flags;
            PlAgeInfoStruct xageinfo;
            byte xu1;
            Wpstr xu2;
            Wpstr xu3;
            Wpstr xu4;
            HsBitVector xu5;
            Wpstr xu5a;
            Wpstr xu5b;
            Wpstr xu5c;
            byte xu6;
            Wpstr xu7;
            
            public PlAgeLinkStruct(context c) throws readexception
            {
                flags = c.readShort();
                int test = b.Int16ToInt32(flags);
                if((test&0x01)!=0)
                {
                    xageinfo = new PlAgeInfoStruct(c);
                }
                if((test&0x02)!=0)
                {
                    xu1 = c.readByte();
                }
                if((test&0x04)!=0)
                {
                    xu2 = new Wpstr(c);
                    test = test & 0xFFFB | 0x20; //removes this flag and adds a later one. Why?
                }
                if((test&0x08)!=0)
                {
                    xu3 = new Wpstr(c);
                    xu4 = new Wpstr(c);
                    test = test & 0xFFF7 | 0x20; //removes this flag and adds a later one. Why?
                }
                else
                {
                    if((test&0x20)!=0)
                    {
                        //function sub_6f16c0 here is a little weird.
                        //I had to figure this out on my own.
                        xu5 = new HsBitVector(c); //decompiled version has a bunch of junk here.
                        int test2 = xu5.get(0);
                        if((test2&0x01)!=0)
                        {
                            xu5a = new Wpstr(c);
                        }
                        if((test2&0x02)!=0)
                        {
                            xu5b = new Wpstr(c);
                        }
                        if((test2&0x04)!=0)
                        {
                            xu5c = new Wpstr(c);
                        }
                    }
                }
                if((test&0x10)!=0)
                {
                    xu6 = c.readByte();
                }
                if((test&0x40)!=0)
                {
                    xu7 = new Wpstr(c);
                }
            }
            
            public void compile(Bytedeque c)
            {
                c.writeShort(flags);
                int test = b.Int16ToInt32(flags);
                if((test&0x01)!=0)
                {
                    xageinfo.compile(c);
                }
                if((test&0x02)!=0)
                {
                    c.writeByte(xu1);
                }
                if((test&0x04)!=0)
                {
                    xu2.compile(c);
                    test = test & 0xFFFB | 0x20; //removes this flag and adds a later one. Why?
                }
                if((test&0x08)!=0)
                {
                    xu3.compile(c);
                    xu4.compile(c);
                    test = test & 0xFFF7 | 0x20; //removes this flag and adds a later one. Why?
                }
                else
                {
                    if((test&0x20)!=0)
                    {
                        //function sub_6f16c0 here is a little weird.
                        //I had to figure this out on my own.
                        xu5.compile(c); //decompiled version has a bunch of junk here.
                        int test2 = xu5.get(0);
                        if((test2&0x01)!=0)
                        {
                            xu5a.compile(c);
                        }
                        if((test2&0x02)!=0)
                        {
                            xu5b.compile(c);
                        }
                        if((test2&0x04)!=0)
                        {
                            xu5c.compile(c);
                        }
                    }
                }
                if((test&0x10)!=0)
                {
                    c.writeByte(xu6);
                }
                if((test&0x40)!=0)
                {
                    xu7.compile(c);
                }
            }
        }
        
        public static class PlAgeInfoStruct extends uruobj
        {
            byte flags;
            Wpstr xu1;
            Wpstr xu2;
            byte[] xu3;
            Wpstr xu4;
            int xu5;
            Wpstr xu6;
            int xu7;
            
            public PlAgeInfoStruct(context c)
            {
                flags = c.readByte();
                int test = b.ByteToInt32(flags);
                if((test&0x01)!=0)
                {
                    xu1 = new Wpstr(c);
                }
                if((test&0x02)!=0)
                {
                    xu2 = new Wpstr(c);
                }
                if((test&0x04)!=0)
                {
                    xu3 = c.readBytes(16);
                }
                if((test&0x08)!=0)
                {
                    xu4 = new Wpstr(c);
                }
                if((test&0x10)!=0)
                {
                    xu5 = c.readInt();
                }
                if((test&0x20)!=0)
                {
                    xu6 = new Wpstr(c);
                }
                if((test&0x40)!=0)
                {
                    xu7 = c.readInt();
                }
            }
            
            public void compile(Bytedeque c)
            {
                c.writeByte(flags);
                int test = b.ByteToInt32(flags);
                if((test&0x01)!=0)
                {
                    xu1.compile(c);
                }
                if((test&0x02)!=0)
                {
                    xu2.compile(c);
                }
                if((test&0x04)!=0)
                {
                    c.writeBytes(xu3);
                }
                if((test&0x08)!=0)
                {
                    xu4.compile(c);
                }
                if((test&0x10)!=0)
                {
                    c.writeInt(xu5);
                }
                if((test&0x20)!=0)
                {
                    xu6.compile(c);
                }
                if((test&0x40)!=0)
                {
                    c.writeInt(xu7);
                }
            }
        }
    }

    public static class PlOneShotMsg extends uruobj
    {
        PlMessage parent;
        int count;
        PlOneShotCallback[] callbacks;
        
        public PlOneShotMsg(context c) throws readexception
        {
            parent = new PlMessage(c);
            count = c.readInt();
            callbacks = c.readVector(PlOneShotCallback.class, count);
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeInt(count);
            c.writeVector(callbacks);
        }
        
        public static class PlOneShotCallback extends uruobj
        {
            Urustring marker;
            Uruobjectref receiver;
            short user;
            
            public PlOneShotCallback(context c) throws readexception
            {
                marker = new Urustring(c);
                receiver = new Uruobjectref(c);
                user = c.readShort();
            }
            
            public void compile(Bytedeque c)
            {
                marker.compile(c);
                receiver.compile(c);
                c.writeShort(user);
            }
        }
    }
    public static class PlNotifyMsg extends uruobj
    {
        public PlMessage parent;
        int type;
        Flt state;
        int id;
        int count;
        proEventData[] events;
        
        public PlNotifyMsg(context c) throws readexception
        {
            parent = new PlMessage(c);
            type = c.readInt();
            state = new Flt(c);
            if(c.readversion==3||c.readversion==6)
            {
                id = c.readInt();
            }
            else if(c.readversion==4)
            {
                byte idb = c.readByte();
                id = b.ByteToInt32(idb); //is this correct?
            }
            count = c.readInt();
            events = new proEventData[count];
            for(int i=0;i<count;i++)
            {
                events[i] = new proEventData(c);
            }
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeInt(type);
            state.compile(c);
            c.writeInt(id);
            c.writeInt(count);
            for(int i=0;i<count;i++)
            {
                events[i].compile(c);
            }
        }
        
        public static class proEventData extends uruobj
        {
            int type;
            uruobj event;
            
            public proEventData(context c) throws readexception
            {
                type = c.readInt();
                switch(type)
                {
                    case 1:
                        event = new Collision(c);
                        break;
                    case 8:
                        event = new Callback(c);
                        break;
                    default:
                        throw new readexception("proeventdata: unhandled type:"+Integer.toString(type));
                        //break;
                }
            }
            
            public void compile(Bytedeque c)
            {
                c.writeInt(type);
                event.compile(c);
            }
        }
        public static class Callback extends uruobj
        {
            int eventTypeCb;
            
            public Callback(context c)
            {
                eventTypeCb = c.readInt();
            }
            
            public void compile(Bytedeque c)
            {
                c.writeInt(eventTypeCb);
            }
        }
        public static class Collision extends uruobj
        {
            byte enter;
            Uruobjectref hitter;
            Uruobjectref hittee;
            
            public Collision(context c) throws readexception
            {
                enter = c.readByte();
                hitter = new Uruobjectref(c);
                hittee = new Uruobjectref(c);
            }
            
            public void compile(Bytedeque c)
            {
                c.writeByte(enter);
                hitter.compile(c);
                hittee.compile(c);
            }
        }
    }
    
    public static class PlEnableMsg extends uruobj
    {
        PlMessage parent;
        HsBitVector cmd;
        HsBitVector types;
        
        public PlEnableMsg(context c) throws readexception
        {
            parent = new PlMessage(c);
            cmd = new HsBitVector(c);
            types = new HsBitVector(c);
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            cmd.compile(c);
            types.compile(c);
        }
    }
    public static class PlActivatorMsg extends uruobj
    {
        PlMessage parent;
        int u1;
        Vertex u2;
        
        public PlActivatorMsg(context c) throws readexception
        {
            m.warn("untested");
            parent = new PlMessage(c);
            u1 = c.readInt();
            u2 = new Vertex(c);
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeInt(u1);
            u2.compile(c);
        }
    }
    public static class PlArmatureEffectStateMsg extends uruobj
    {
        PlMessage parent;
        byte surface;
        byte addsurface;
        public PlArmatureEffectStateMsg(context c) throws readexception
        {
            parent = new PlMessage(c);
            surface = c.readByte();
            addsurface = c.readByte();
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeByte(surface);
            c.writeByte(addsurface);
        }
    }
    public static class PlExcludeRegionMsg extends uruobj
    {
        PlMessage parent;
        
        byte u1;
        int u2;
        //Pageid pageid;
        
        public PlExcludeRegionMsg(context c) throws readexception
        {
            parent = new PlMessage(c);
            u1 = c.readByte();
            if(c.readversion==3||c.readversion==6)
            {
                u2 = c.readInt();//always 0 in pots
                //m.msg("PlExcludeRegionMsg:u2="+Integer.toString(u2));
            }
            else if(c.readversion==4)
            {
                u2 = 0; //since it's always 0 in pots anyway.
            }
            //pageid = new Pageid(c);
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeByte(u1);
            c.writeInt(u2);
        }
    }
    public static class PlMessageWithCallbacks extends uruobj
    {
        PlMessage parent;
        int count;
        PrpTaggedObject[] callbacks;
        
        public PlMessageWithCallbacks(context c) throws readexception
        {
            parent = new PlMessage(c);
            count = c.readInt();
            callbacks = c.readVector(PrpTaggedObject.class, count); //this may be wrong. the messages may be stripped of the header in plMessage
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeInt(count);
            c.writeVector(callbacks);
        }
    }
    public static class PlSoundMsg extends uruobj
    {
        PlMessageWithCallbacks parent;
        HsBitVector cmd;
        Double64 begin;
        Double64 end;
        byte loop;
        byte playing;
        Flt speed;
        Double64 time;
        int index;
        int repeats;
        int namestr;
        Flt volume;
        byte fadetype;
        
        public PlSoundMsg(context c) throws readexception
        {
            parent = new PlMessageWithCallbacks(c);
            cmd = new HsBitVector(c);
            begin = new Double64(c);
            end = new Double64(c);
            loop = c.readByte();
            playing = c.readByte();
            speed = new Flt(c);
            time = new Double64(c);
            index = c.readInt();
            repeats = c.readInt();
            namestr = c.readInt();
            volume = new Flt(c);
            fadetype = c.readByte();
        }
        
        public void compile(Bytedeque c)
        {
            parent.compile(c);
            cmd.compile(c);
            begin.compile(c);
            end.compile(c);
            c.writeByte(loop);
            c.writeByte(playing);
            speed.compile(c);
            time.compile(c);
            c.writeInt(index);
            c.writeInt(repeats);
            c.writeInt(namestr);
            volume.compile(c);
            c.writeByte(fadetype);
        }
    }
    
    public static class PlMessage extends uruobj
    {
        public Uruobjectref parentobj;
        public int refcount;
        public Uruobjectref[] refs;
        int u1;
        int u2;
        int flags;
        
        int xu1;
        
        public PlMessage(context c) throws readexception
        {
            parentobj = new Uruobjectref(c);
            refcount = c.readInt();
            refs = c.readVector(Uruobjectref.class, refcount);
            if(c.readversion==3||c.readversion==6)
            {
                u1 = c.readInt();
                u2 = c.readInt();
                flags = c.readInt();
            }
            else if(c.readversion==4)
            {
                //if(refcount!=0)
                //{
                    xu1 = c.readInt();
                //}
                //the other flags will all default to 0.
            }
        }
        
        public void compile(Bytedeque c)
        {
            parentobj.compile(c);
            c.writeInt(refcount);
            c.writeVector(refs);
            c.writeInt(u1);
            c.writeInt(u2);
            c.writeInt(flags);
        }
    }

    //public String toString()
    //{
    //    return "PrpMessageType = "+messageType.toString();
    //}
}
