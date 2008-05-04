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

import shared.readexception;
import uru.context; import shared.readexception;
import uru.Bytestream;
import uru.Bytedeque;
import uru.e;
import shared.m;
import uru.b;
//import java.util.Vector;

//untested and needs PrpMessages to be at least partially complete.
public class PlResponderModifier extends uruobj
{
    //Objheader xheader;
    
    PlSingleModifier parent;
    byte count;
    PlResponderState[] messages;
    byte state;
    byte enabled;
    byte flags;
    
    public PlResponderModifier(context c) throws readexception
    {
        ////if(hasHeader) xheader = new Objheader(c);
        
        parent = new PlSingleModifier(c);//,false);
        count = c.readByte();
        messages = c.readVector(PlResponderState.class, count);
        state = c.readByte();
        enabled = c.readByte();
        flags = c.readByte();
    }
    public void compile(Bytedeque c)
    {
        parent.compile(c);
        c.writeByte(count);
        c.writeVector(messages);
        c.writeByte(state);
        c.writeByte(enabled);
        c.writeByte(flags);
    }
    
    public static class PlResponderState extends uruobj
    {
        byte numCallbacks;
        byte switchToState;
        byte count;
        PlResponderCmd[] commands;
        byte count2;
        byte[][] u1;
        
        public PlResponderState(context c) throws readexception
        {
            numCallbacks = c.readByte();
            switchToState = c.readByte();
            count = c.readByte();
            commands = new PlResponderCmd[count];
            for(int i=0;i<count;i++)
            {
                commands[i] = new PlResponderCmd(c);
            }
            //if(true) return;
            count2 = c.readByte();
            u1 = new byte[count2][];
            for(int i=0;i<count2;i++)
            {
                u1[i] = new byte[2];
                u1[i][0] = c.readByte();
                u1[i][1] = c.readByte();
            }
        }
        
        public void compile(Bytedeque c)
        {
            c.writeByte(numCallbacks);
            c.writeByte(switchToState);
            c.writeByte(count);
            for(int i=0;i<count;i++)
            {
                commands[i].compile(c);
            }
            c.writeByte(count2);
            for(int i=0;i<count2;i++)
            {
                c.writeByte(u1[i][0]);
                c.writeByte(u1[i][1]);
            }
            
        }
    }
    
    public static class PlResponderCmd extends uruobj
    {
        PrpMessage message;
        byte waitOn;
        
        public PlResponderCmd(context c) throws readexception
        {
            message = new PrpMessage(c);
            waitOn = c.readByte();
        }
        
        public void compile(Bytedeque c)
        {
            message.compile(c);
            c.writeByte(waitOn);
        }
    }
}
