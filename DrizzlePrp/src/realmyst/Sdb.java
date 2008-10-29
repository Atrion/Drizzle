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
            
            //hsObjectGroup
            int tag2 = c.readInt(); //should actually be a reverse int.
            if(tag2!=0x9469DF4E)
            {
                throw new uncaughtexception("Unhandled tag.");
            }
            ReverseInt two = new ReverseInt(c); e.ensure(two.convertToInt()==2);
            int six = c.readInt();  e.ensure(six==6);
            int u8 = c.readInt(); //v6
            if(u8!=0)
            {
                throw new uncaughtexception("Unhandled.");
            }
            int count = c.readInt(); //v65
            Bstr[] morestrs = c.readArray(Bstr.class, count);
            int count2 = c.readInt(); //v69
            if(count2!=0)
            {
                throw new uncaughtexception("Unhandled.");
            }
            int count3 = c.readInt(); //v72
            if(count3!=0)
            {
                throw new uncaughtexception("Unhandled.");
            }
            else
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
                                            if(true) throw new uncaughtexception("unhandled.");
                                            U1[] u1s = c.readArray(U1.class, count10);
                                        }
                                        else
                                        {
                                            int count11 = c.readInt(); //result
                                            if(count11!=0)
                                            {
                                                throw new uncaughtexception("unhandled.");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                }
            }
            
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
