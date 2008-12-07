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
import shared.e;
import shared.m;
import shared.b;
import shared.readexception;
//import java.util.Vector;
import shared.*;


public class HexislePlDrawableSpans extends uruobj
{

    private static int getatpos(int flags, int pos)
    {
        int resutl = ((flags >>> 2*(15-pos))&0x3) + 1;
        return resutl;
    }
    public static int getvertexcount(int flags, int hi2orig)
    {
        
        //sub_5049c0
        
        int a3 = 0; //actually set in object, either 0 or 1. hi2 is the var, I think
        
        int a = 0;
        a += getatpos(flags,7);
        a += getatpos(flags,6);
        a += getatpos(flags,5);
        a += getatpos(flags,4);
        a += getatpos(flags,3);
        a += getatpos(flags,2);
        a += getatpos(flags,1);
        a += getatpos(flags,0);
        if(((flags>>>7)&1)!=0) a+= 4;
        if(((flags>>>6)&1)!=0) a+= 4;
        if(((flags>>>5)&1)!=0) a+= 4*a3+8;
        if(((flags>>>4)&1)!=0) a+= 4*a3+8;
        if(((flags>>>3)&1)!=0) a+= 4*a3+8;
        if(((flags>>>2)&1)!=0) a+= 12;
        if(((flags>>>1)&1)!=0) a+= 4;
        if(hi2orig!=0 )
        {
            a += 4;
            /*if(a3!=0)
            {
                a += 4*hi2orig;
            }
            else
            {
                a += 4;
            }*/
        }
        return a;
    }
    
    //parse and get size. Used for both creation and compilation.
    static boolean flag(int flags, int pos)
    {
        return ((flags & (0x1 << pos))!=0);
    }
    static public int GetVertexDataSize(int count, byte fformat2, context c, int a5, byte mesh2, int mesh1)
    {

        class rundata
        {
            Flt basee;
            byte basec;
            int count;
            boolean rle;
            byte b1;

            public rundata()
            {
                basee = null;
                count = 0;
                basec = 0;
                rle = false;
            }

            //returns the compressed value, in some circumstances.
            public short pollAsElement(int A, int B, int C, context c)
            {
                if(count==0)
                {
                    basee = new Flt(c);
                    if(c.compile) basee.compile(c.out);

                    if(c.readversion==6)
                    {
                        b1 = c.in.readByte();
                        if(c.compile && c.writeversion==6) c.out.writeByte(b1);
                    }
                    else if(c.readversion==3||c.readversion==4||c.readversion==7)
                    {
                        //do nothing
                    }

                    short out7 = c.in.readShort();
                    if(c.compile) c.out.writeShort(out7);
                    count = b.Int16ToInt32(out7);
                }
                if(count!=0)
                {
                    if(c.readversion==6)
                    {
                        if(b1==0)
                        {
                            count--;
                            //return data.readShort();
                            short out9 = c.in.readShort();
                            if(c.compile) c.out.writeShort(out9);
                            //return;
                            return out9;
                        }
                        else if(b1==1)
                        {
                            count--;
                            if(c.compile && c.writeversion==3)
                            {
                                c.out.writeShort((short)0);//Is this right? Is this the default? //(32768);
                            }
                            return (short)0;
                        }
                        else
                        {
                            m.msg("unknown byte.");
                        }
                    }
                    else if(c.readversion==3||c.readversion==4||c.readversion==7)
                    {
                        count--;
                        short out9 = c.in.readShort();
                        if(c.compile) c.out.writeShort(out9);
                    }
                }
                return 0;
            }
            public void pollAsColour(int A, int B, int C, context c)
            {
                //sub_50e270
                if(count==0)
                {
                    short out5 = c.in.readShort();
                    if(c.compile) c.out.writeShort(out5);
                    count = b.Int16ToInt32(out5);
                    if((count & 0x8000)!=0)
                    {
                        //m.msg("haven't tested this.");
                        rle = true;
                        basec = c.in.readByte();
                        if(c.compile) c.out.writeByte(basec);
                        count = count & 0x7FFF;
                    }
                    else
                    {
                        rle = false;
                    }
                }
                if(count!=0)
                {
                    count--;
                    if(rle)
                    {
                        //return base;
                        return;
                    }
                    else
                    {
                        //return data.readByte();
                        byte out6 = c.in.readByte();
                        if(c.compile) c.out.writeByte(out6);
                        return;
                    }
                }
                m.err("We shouldn't be able to reach here.");
            }
        }

        //e.ensure(hi1orig==76);
        
        byte v5 = (byte)((a5>>>8)&0xFF);
        if((a5&0x2000000)!=0)
        {
            v5 = (byte)(a5&0xFF);
        }
        int v7 = b.ByteToInt32(mesh2);
        int flags = mesh1;
        //int a4 = mesh1;
        //if(v7)
        
        int start = c.in.getAbsoluteOffset();
        int fformat = b.ByteToInt32(fformat2);
        fformat = flags;
        int A = (fformat & 0x40) >>> 6;
        int B = (fformat & 0x30) >>> 4;
        int C = (fformat & 0x0F) >>> 0;


        rundata x = new rundata();
        rundata y = new rundata();
        rundata z = new rundata();

        rundata[] ws = new rundata[3];
        for(int i=0;i<3;i++) ws[i] = new rundata();

        rundata blue = new rundata();
        rundata green = new rundata();
        rundata red = new rundata();
        rundata alpha = new rundata();

        rundata[][] uvs = new rundata[15][];
        for(int i=0;i<15;i++)
        {
            uvs[i] = new rundata[3];
            for(int j=0;j<3;j++)
            {
                uvs[i][j] = new rundata();
            }
        }

        for(int i=0;i<count;i++)
        {
            int possofar = c.in.getAbsoluteOffset() - start;

            
            if(v7!=0)
            {
                //not tested.
                byte[] b1 = c.readBytes(v7);
                if(flag(flags,1))
                {
                    byte[] b2 = c.readBytes(v7);
                    int dummy=0;
                }
                int unhandled = 0;
            }
            if(flag(flags,2))
            {
                short xval = x.pollAsElement(A, B, C, c);
                short yval = y.pollAsElement(A, B, C, c);
                short zval = z.pollAsElement(A, B, C, c);
                //not implemented yet.
                int dummy=0;
            }
            if(flag(flags,3))
            {
                //these are decompressed to floats.
                byte b1 = c.readByte();
                byte b2 = c.readByte();
                byte b3 = c.readByte();
                int dummy=0;
                //not implemented yet.
            }
            if(flag(flags,4))
            {
                int dummy=0;
                //not implemented yet.
            }
            if(flag(flags,5))
            {
                int dummy=0;
                //not implemented yet.
            }
            if(flag(flags,6))
            {
                blue.pollAsColour(A, B, C, c);
                green.pollAsColour(A, B, C, c);
                red.pollAsColour(A, B, C, c);
                alpha.pollAsColour(A, B, C, c);
                int dummy=0;
                //not implemented yet.
            }
            if(flag(flags,7))
            {
                int dummy=0;
                //not implemented yet.
            }
            
            int result2 = sub5042d0(flags);
            
            for(int j=0;j<result2;j++)
            {
                int wha = sub504300(flags, j);
                for(int k=0;k<wha;k++)
                {
                    short s1 = c.readShort(); //encoded float
                    int dummy=0;
                }
                if(wha==1 || wha==3)
                {
                    //decompress byte to float?
                    int dummy2=0;
                }
                //unhandled
                int dummy=0;
            }
            
            if(true)continue;
            
            
            //get vertex.
            short xval = x.pollAsElement(A, B, C, c);
            short yval = y.pollAsElement(A, B, C, c);
            short zval = z.pollAsElement(A, B, C, c);

            //compute actuall, uncompressed vertex:
            if (c.outputVertices)
            {
                float output;
                output = x.basee.toJavaFloat();
                output = output + (float)(xval)/(float)(1024.0);
                c.vertices.add(output);
                output = x.basee.toJavaFloat();
                output = output + (float)(yval)/(float)(1024.0);
                c.vertices.add(output);
                output = x.basee.toJavaFloat();
                output = output + (float)(zval)/(float)(1024.0);
                c.vertices.add(output);
            }

            //get weights
            for(int j=0;j<B;j++)
            {
                ws[j].pollAsElement(A, B, C, c);
            }

            //if A is set to true, get bones
            if ((B!=0) && (A!=0))
            {
                int out1 = c.in.readInt(); //bones
                if(c.compile) c.out.writeInt(out1);
            }

            //normals: these are just a byte now.
            //data.readShort(); //nx
            //data.readShort(); //ny
            //data.readShort(); //nz
            if(c.readversion==6||c.readversion==7)
            {
                byte out2 = c.in.readByte(); //nx
                byte out3 = c.in.readByte(); //ny
                byte out4 = c.in.readByte(); //nz
                if(c.compile)
                {
                    c.out.writeShort((short)(b.ByteToInt32(out2)*257)); //the *257 expands it to 2 bytes. i.e. 0->0, 255->65535.
                    c.out.writeShort((short)(b.ByteToInt32(out3)*257));
                    c.out.writeShort((short)(b.ByteToInt32(out4)*257));
                }
            }
            else if(c.readversion==3||c.readversion==4)
            {
                short out2 = c.in.readShort();
                short out3 = c.in.readShort();
                short out4 = c.in.readShort();
                if(c.compile)
                {
                    c.out.writeShort(out2);
                    c.out.writeShort(out3);
                    c.out.writeShort(out4);
                }
            }

            //colours:
            blue.pollAsColour(A, B, C, c);
            green.pollAsColour(A, B, C, c);
            red.pollAsColour(A, B, C, c);
            alpha.pollAsColour(A, B, C, c);

            if(true)continue;
            //this is slightly wrong I think...
            int result = sub5042d0(flags);
            for(int j=0;j<result;j++)
            {
                //reads some number of shorts
                m.err("Unhandled.");
            }
            if(true)continue;
            
            //uv texture coordinates
            for(int j=0;j<C;j++)
            {
                for(int k=0;k<3;k++)
                {
                    uvs[j][k].pollAsElement(A, B, C, c);
                }
            }

        }

        int stop = c.in.getAbsoluteOffset();
        return stop-start; //return the size we processed.
    }
    public static int sub504300(int val, int val2)
    {
        int shift = val2+8;
        int othershift = 15-val2;
        if((val&(1<<shift))!=0)
        {
            return ((val >> 2 * (othershift)) & 3) + 1;
        }
        else
        {
            return 0;
        }
    }
    public static int sub5042d0(int val)
    {
        //actually does a table lookup, but the table just has 8 ints and they run from 0x8 to 0xF.
        if((val&(1<<8))!=0) return 0;
        if((val&(1<<9))!=0) return 1;
        if((val&(1<<10))!=0) return 2;
        if((val&(1<<11))!=0) return 3;
        if((val&(1<<12))!=0) return 4;
        if((val&(1<<13))!=0) return 5;
        if((val&(1<<14))!=0) return 6;
        if((val&(1<<15))!=0) return 7;
        return 8;
    }
}
