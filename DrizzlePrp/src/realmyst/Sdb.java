/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package realmyst;

import shared.*;
import shared.e;

public class Sdb
{
    public int tag;
    public int u1;
    public int filesizeMinusHeader;
    public Bstr name;
    //public int u3;
    public Bstr u3;
    public int strCount; //number of Bstrs that follow
    public Bstr[] strs;
    
    public Sdb(IBytestream c)
    {
        String filename=
                //"113499986.vdb"
                //"14341445.vdb"
                "45043.vdb"
                ;
        if(c.sourceName.toLowerCase().endsWith(filename.toLowerCase()))
        {
            int dummy=0;
        }
        
        boolean ignore = true;
        
        tag = c.readInt();
        if(tag==0x02000000)
        {
            //sdb header
            u1 = c.readInt(); //filesize (including header) Sometimes not, though.
            filesizeMinusHeader = c.readInt(); //filesize minus header
            name = new Bstr(c);
            m.msg(name.toString()+" u1="+Integer.toString(u1)+" u2="+Integer.toString(filesizeMinusHeader));
            //u3 = c.readInt();
            u3 = new Bstr(c);
            strCount = c.readInt();
            //s2 = new Bstr(c);
            if(strCount!=1)
            {
                int dummy=0;
            }
            strs = c.readArray(Bstr.class, strCount);
            
            if(c.getBytesRemaining()<40)
            {
                int dummy=0;
            }
            
            //if(true)return;
            
            //hsObjectGroup (sub_46c140)
            int tag2 = c.readInt(); //should actually be a reverse int.
            if(tag2!=0x9469DF4E)
            {
                ignore = false;
                throw new ignore("Unhandled tag.");
            }
            ReverseInt two = new ReverseInt(c); e.ensure(two.convertToInt()==2);
            int six = c.readInt();  e.ensure(six==6);
            int u8 = c.readInt(); //v6
            if(u8!=0)
            {
                ignore = false;
                throw new ignore("Unhandled.");
            }
            int count = c.readInt(); //v65
            Bstr[] morestrs = c.readArray(Bstr.class, count);
            int count2 = c.readInt(); //v69
            if(count2!=0)
            {
                ignore = false;
                throw new ignore("Unhandled.");
            }
            int count3 = c.readInt(); //v72
            if(count3!=0)
            {
                ignore = false;
                throw new ignore("Unhandled.");
            }

            //new try:
            int hasThing = c.readInt();
            if(hasThing!=0)
            {
                ignore = false;
                throw new ignore("Unhandled.");
            }
            int count4 = c.readInt(); //v78
            if(count4!=0)
            {
                ignore = false;
                throw new ignore("Unhandled.");
            }
            int u10 = c.readInt(); //stored at offset 148
            int u11 = c.readInt(); //v15
            if(u11!=0)
            {
                ignore = false;
                throw new ignore("Unhandled.");
            }
            int count5 = c.readInt(); //v84
            if(count5!=0)
            {
                ignore = false;
                throw new ignore("Unhandled.");
            }
            int count6 = c.readInt(); //v86
            if(count6!=0)
            {
                ignore = false;
                throw new ignore("Unhandled.");
            }
            int count7 = c.readInt(); //v91
            if(count7!=0)
            {
                ignore = false;
                throw new ignore("Unhandled.");
            }
            int count8 = c.readInt(); //v96
            if(count8!=0)
            {
                ignore = false;
                throw new ignore("unhandled.");
            }
            int count9 = c.readInt(); //v101
            if(count9!=0)
            {
                ignore = false;
                if(ignore) throw new ignore("unhandled.");
                Count9[] c9s = c.readArray(Count9.class, count9);
            }
            int count10 = c.readInt(); //v107
            if(count10!=0)
            {
                //m.err("unhandled.");
                //ignore = false;
                if(ignore) throw new ignore("unhandled.");
                Count10[] u1s = c.readArray(Count10.class, count10);
            }
            int count11 = c.readInt(); //result
            if(count11!=0)
            {
                if(ignore) throw new ignore("unhandled.");
                m.msg("count11:"+c.sourceName);
                //throw new uncaughtexception("unhandled.");
                Count11[] c11s = c.readArray(Count11.class, count11);
            }
            
            //old try:
            /*else
            {
                int hasThing = c.readInt();
                if(hasThing!=0)
                {
                    throw new uncaughtexception("Unhandled.");
                }
                int count4 = c.readInt(); //v78
                if(count4!=0)
                {
                    throw new uncaughtexception("Unhandled.");
                }
                else
                {
                    int u10 = c.readInt(); //stored at offset 148
                    int u11 = c.readInt(); //v15
                    if(u11!=0)
                    {
                        throw new uncaughtexception("Unhandled.");
                    }
                    int count5 = c.readInt(); //v84
                    if(count5!=0)
                    {
                        throw new uncaughtexception("Unhandled.");
                    }
                    else
                    {
                        int count6 = c.readInt(); //v86
                        if(count6!=0)
                        {
                            throw new uncaughtexception("Unhandled.");
                        }
                        else
                        {
                            int count7 = c.readInt(); //v91
                            if(count7!=0)
                            {
                                throw new uncaughtexception("Unhandled.");
                            }
                            else
                            {
                                int count8 = c.readInt(); //v96
                                if(count8!=0)
                                {
                                    throw new uncaughtexception("unhandled.");
                                }
                                else
                                {
                                    int count9 = c.readInt(); //v101
                                    if(count9!=0)
                                    {
                                        throw new uncaughtexception("unhandled.");
                                    }
                                    else
                                    {
                                        int count10 = c.readInt(); //v107
                                        if(count10!=0)
                                        {
                                            //m.err("unhandled.");
                                            //if(true) throw new uncaughtexception("unhandled.");
                                            Count10[] u1s = c.readArray(Count10.class, count10);
                                        }
                                        else
                                        {
                                            int count11 = c.readInt(); //result
                                            if(count11!=0)
                                            {
                                                m.msg("count11:"+c.sourceName);
                                                //throw new uncaughtexception("unhandled.");
                                                Count11[] c11s = c.readArray(Count11.class, count11);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                }
            }*/
            
            //Sceneobject so = new Sceneobject(c);
            
            int dummy=0;
        }
        else if(tag==0x529A6D54)
        {
            //mdb
            u1 = c.readInt(); //filesize (including header)
            filesizeMinusHeader = c.readInt(); //=1?
            if(filesizeMinusHeader!=1)
            {
                int dummy=0;
            }
            name = new Bstr(c);
            
            int u3 = c.readInt();
            byte b4 = c.readByte();
            int u5 = c.readInt();
            int u6 = c.readInt();
            int u7 = c.readInt();
            int u8 = c.readInt();
            int u9 = c.readInt();
            int u10 = c.readInt();
            m.msg(name.toString());
        }
        else
        {
            m.err("Unhandled tag: 0x"+Integer.toHexString(tag));
        }
        int dummy=0;
    }
}
