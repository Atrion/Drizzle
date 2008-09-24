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

/**
 *
 * @author user
 */
public class x00A2Pythonfilemod extends uruobj
{
    //Objheader xheader;
    PlMultiModifier parent;
    public Urustring pyfile;
    int refcount;
    Uruobjectref[] pythonrefs;
    int listcount;
    Pythonlisting[] listings;
    
    public x00A2Pythonfilemod(context c) throws readexception//,boolean hasHeader)
    {
        Bytestream data = c.in;
        //if(hasHeader) xheader = new Objheader(c);
        parent = new PlMultiModifier(c);//,false);
        pyfile = new Urustring(c);
        if(shared.State.AllStates.getStateAsBoolean("reportPythonFileMod")) m.msg("PythonFileMod: file="+pyfile.toString());
        refcount = data.readInt();
        pythonrefs = new Uruobjectref[refcount];
        for(int i=0;i<refcount;i++)
        {
            pythonrefs[i] = new Uruobjectref(c);
        }
        listcount = data.readInt();
        listings = new Pythonlisting[listcount];
        for(int i=0;i<listcount;i++)
        {
            listings[i] = new Pythonlisting(c,pyfile);
        }
    }
    public void compile(Bytedeque deque)
    {
        parent.compile(deque);
        pyfile.compile(deque);
        deque.writeInt(refcount);
        for(int i=0;i<refcount;i++)
        {
            pythonrefs[i].compile(deque);
        }
        deque.writeInt(listcount);
        for(int i=0;i<listcount;i++)
        {
            listings[i].compile(deque);
        }
    }
    
    static public class Pythonlisting extends uruobj
    {
        int index;
        int type;
        int xInteger;
        int xFloat; //this is really a floating point number, but why bother to convert it?
        int xBoolean;
        Bstr xString;
        Uruobjectref xRef;
        
        public Pythonlisting(context c, Urustring pyfile) throws readexception
        {
            Bytestream data = c.in;

            index = data.readInt();
            type = data.readInt();
            if(shared.State.AllStates.getStateAsBoolean("reportPythonFileMod")) m.msg("PythonType: type="+Integer.toString(type)+" index="+Integer.toString(index)+" pyfile="+pyfile.toString());
            switch(type)
            {
                case 1:
                    xInteger = data.readInt();
                    if(shared.State.AllStates.getStateAsBoolean("reportPythonFileMod")) m.msg("PythonFileMod:   int="+Integer.toString(xInteger));
                    break;
                case 2:
                    xFloat = data.readInt();
                    if(shared.State.AllStates.getStateAsBoolean("reportPythonFileMod")) m.msg("PythonFileMod:   float="+Integer.toString(xFloat));
                    break;
                case 3:
                    xBoolean = data.readInt();
                    if(shared.State.AllStates.getStateAsBoolean("reportPythonFileMod")) m.msg("PythonFileMod:   bool="+Integer.toString(xBoolean));
                    break;
                case 4:
                case 13:
                    xString = new Bstr(c);
                    if(shared.State.AllStates.getStateAsBoolean("reportPythonFileMod")) m.msg("PythonFileMod:   string="+xString.toString());
                    break;
                case 20:
                case 23:
                    if(c.readversion==4)
                    {
                        xString = new Bstr(c);
                        if(shared.State.AllStates.getStateAsBoolean("reportPythonFileMod")) m.msg("PythonFileMod:   string="+xString.toString());
                        //m.warn("PythonFileMod: usinng a case that differs between versions.");
                        throw new shared.readwarningexception("PythonFileMod: can read okay, but throwing error to ignore.");
                    }
                    else if(c.readversion==6||c.readversion==3)
                    {
                        e.ensure(type!=23); //type 23 shouldn't occur, according to my stats.
                        xRef = new Uruobjectref(c);
                        if(shared.State.AllStates.getStateAsBoolean("reportPythonFileMod")) m.msg("PythonFileMod:   ref="+xRef.toString());
                    }
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 21:
                    xRef = new Uruobjectref(c);
                    if(shared.State.AllStates.getStateAsBoolean("reportPythonFileMod")) m.msg("PythonFileMod:   ref="+xRef.toString());
                    break;
                default:
                    m.msg("unknown pythonfilemod type."); //we *may* encounter 22 in moul; if so, it has no match in pots.
                    break;
            }
        }
        public void compile(Bytedeque deque)
        {
            deque.writeInt(index);
            deque.writeInt(type);
            switch(type)
            {
                case 1:
                    deque.writeInt(xInteger);
                    break;
                case 2:
                    deque.writeInt(xFloat);
                    break;
                case 3:
                    deque.writeInt(xBoolean);
                    break;
                case 4:
                case 13:
                    xString.compile(deque);
                    break;
                case 20:
                case 23:
                    if(xString!=null)
                    {
                        xString.compile(deque);
                        m.warn("PythonFileMod: usinng a case that differs between versions.");
                    }
                    else
                    {
                        e.ensure(xRef!=null); //if it's not a string it should be a ref.
                        xRef.compile(deque);
                    }
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                //case 20:
                case 21:
                    xRef.compile(deque);
                    break;
                default:
                    m.msg("unknown pythonfilemod type.");
                    break;
            }
        }
    }
}
