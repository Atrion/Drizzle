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

import uru.Bytestream;
import uru.Bytedeque;
import uru.context; import shared.readexception;
import shared.m;

public class PlWin32Sound extends uruobj
{
    PlSound parent;
    byte channel; //0 means left channel, 1 means right channel.
    
    public PlWin32Sound(context c) throws readexception
    {
        parent = new PlSound(c);
        channel = c.readByte();
    }
    
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        c.writeByte(channel);
    }
    
    public static class PlSound extends uruobj
    {
        PlSynchedObject parent;
        byte playing;
        Double64 time;
        int maxfalloff;
        int minfalloff;
        Flt curvolume;
        Flt desiredvolume;
        int outervol;
        int innercone;
        int outercone;
        Flt fadedvolume;
        int properties;
        byte type;
        byte priority;
        plFadeParams fadeInParams;
        plFadeParams fadeOutParams;
        Uruobjectref softRegion;
        Uruobjectref dataBuffer; //plSoundBuffer
        plEAXSourceSettings EAXSettings;
        Uruobjectref softOcclusionRegion;

        public PlSound(context c) throws readexception
        {
            parent = new PlSynchedObject(c);
            playing = c.readByte();
            time = new Double64(c);
            maxfalloff = c.readInt();
            minfalloff = c.readInt();
            curvolume = new Flt(c);
            desiredvolume = new Flt(c);
            outervol = c.readInt();
            innercone = c.readInt();
            outercone = c.readInt();
            fadedvolume = new Flt(c);
            properties = c.readInt();
            type = c.readByte();
            priority = c.readByte();
            fadeInParams = new plFadeParams(c);
            fadeOutParams = new plFadeParams(c);
            softRegion = new Uruobjectref(c);
            dataBuffer = new Uruobjectref(c);
            EAXSettings = new plEAXSourceSettings(c);
            softOcclusionRegion = new Uruobjectref(c);
        }

        public void compile(Bytedeque c)
        {
            parent.compile(c);
            c.writeByte(playing);
            time.compile(c);
            c.writeInt(maxfalloff);
            c.writeInt(minfalloff);
            curvolume.compile(c);
            desiredvolume.compile(c);
            c.writeInt(outervol);
            c.writeInt(innercone);
            c.writeInt(outercone);
            fadedvolume.compile(c);
            c.writeInt(properties);
            c.writeByte(type);
            c.writeByte(priority);
            fadeInParams.compile(c);
            fadeOutParams.compile(c);
            softRegion.compile(c);
            dataBuffer.compile(c);
            EAXSettings.compile(c);
            softOcclusionRegion.compile(c);

        }

        /*public String toString()
        {
        }*/
    }
    
    public static class plFadeParams extends uruobj
    {
        Flt lengthInSecs;
        Flt volStart;
        Flt volEnd;
        byte type;
        Flt curTime;
        int stopWhenDone;
        int fadeSoftVol;
        
        public plFadeParams(context c)
        {
            lengthInSecs = new Flt(c);
            volStart = new Flt(c);
            volEnd = new Flt(c);
            type = c.readByte();
            curTime = new Flt(c);
            stopWhenDone = c.readInt();
            fadeSoftVol = c.readInt();
        }
        
        public void compile(Bytedeque c)
        {
            lengthInSecs.compile(c);
            volStart.compile(c);
            volEnd.compile(c);
            c.writeByte(type);
            curTime.compile(c);
            c.writeInt(stopWhenDone);
            c.writeInt(fadeSoftVol);
        }
    }
    
    public static class plEAXSourceSettings extends uruobj
    {
        
        byte enabled;
        
        //these are only present if enabled is set.
        short room;
        short roomHF;
        byte roomAuto;
        byte roomHFAuto;
        short outsideVolHF;
        Flt airAbsorptionFactor;
        Flt roomRolloffFactor;
        Flt dopplerFactor;
        Flt rolloffFactor;
        plEAXSourceSoftSettings softStarts;
        plEAXSourceSoftSettings softEnds;
        Flt occlusionSoftValue;
        
        public plEAXSourceSettings(context c)
        {
            enabled = c.readByte();
            if(enabled!=0)
            {
                room = c.readShort();
                roomHF = c.readShort();
                roomAuto = c.readByte();
                roomHFAuto = c.readByte();
                outsideVolHF = c.readShort();
                airAbsorptionFactor = new Flt(c);
                roomRolloffFactor = new Flt(c);
                dopplerFactor = new Flt(c);
                rolloffFactor = new Flt(c);
                softStarts = new plEAXSourceSoftSettings(c);
                softEnds = new plEAXSourceSoftSettings(c);
                occlusionSoftValue = new Flt(c);
            }
        }
        
        public void compile(Bytedeque c)
        {
            c.writeByte(enabled);
            if(enabled!=0)
            {
                c.writeShort(room);
                c.writeShort(roomHF);
                c.writeByte(roomAuto);
                c.writeByte(roomHFAuto);
                c.writeShort(outsideVolHF);
                airAbsorptionFactor.compile(c);
                roomRolloffFactor.compile(c);
                dopplerFactor.compile(c);
                rolloffFactor.compile(c);
                softStarts.compile(c);
                softEnds.compile(c);
                occlusionSoftValue.compile(c);
            }
        }
    }
    
    public static class plEAXSourceSoftSettings extends uruobj
    {
        short occlusion;
        Flt occlusionLFRatio;
        Flt occlusionRoomRatio;
        Flt occlusionDirectRatio;
        
        public plEAXSourceSoftSettings(context c)
        {
            occlusion = c.readShort();
            occlusionLFRatio = new Flt(c);
            occlusionRoomRatio = new Flt(c);
            occlusionDirectRatio = new Flt(c);
        }
        
        public void compile(Bytedeque c)
        {
            c.writeShort(occlusion);
            occlusionLFRatio.compile(c);
            occlusionRoomRatio.compile(c);
            occlusionDirectRatio.compile(c);
        }
    }
}

    
