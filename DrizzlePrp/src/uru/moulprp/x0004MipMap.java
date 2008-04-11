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

import uru.context; import uru.readexception;
import uru.Bytestream;
import uru.Bytedeque;
import uru.b;
import uru.e;
import uru.m;
import java.util.Vector;
import uru.generics;

/*BASE
DWORD TexWidth
DWORD TexHeight
DWORD Stride // always TexWidth * 4
DWORD MemorySize 
if HsmType = 0x02
  BYTE Unknown?
  BYTE Type // 0 1 2 or 3
  // if Type====3 then there are 8 Dwords then EOF (I only found 1)
  if (Type====1)
    DWORD[4] Unknown?
  DWORD JpgSize1
  BYTE[JpgSize1] JpegFile1
  if Type====2
    do
      DWORD[2] Extra
    while Extra[0] != 0
  elseif Type====0
    DWORD JpgSize2
    BYTE[JpgSize2] JpegFile2 // blue channel is the alpha channel
if HsmType = 0x00
  BYTE MipmapLevels
  for L = 1 to MipmapLevels
    DWORD[TexWidth >> (L - 1) x TexHeight >> (L - 1)] AGRB
if HsmType = 0x01
  BYTE MipmapLevels
  for L = 1 to MipmapLevels
    BYTE[(TexWidth >> (L + 1)) * (TexHeight >> (L + 1)) * texel_size] Texel*/
public class x0004MipMap extends uruobj
{
    //Objheader xheader;
    x0003Bitmap parent;
    int texwidth;
    int texheight;
    int stride;
    int memorysize;
    //byte xu1;
    byte xtype;
    int[] xu2;
    int xjpgsize1;
    byte[] xjpegfile1;
    //int[] xextra;
    //Vector<Integer> xextra1 = new Vector<Integer>();
    //Vector<Integer> xextra2 = new Vector<Integer>();
    Integer[] xextra1;
    Integer[] xextra2;
    int xjpgsize2;
    byte[] xjpegfile2;
    byte xmipmaplevels;
    int[][] xagrb;
    byte[][] xtexel;
    
    public x0004MipMap(context c)//,boolean hasHeader)
    {
        Bytestream data = c.in;
        //if(hasHeader) xheader = new Objheader(c);
        parent = new x0003Bitmap(c);//,false);
        texwidth = data.readInt();
        texheight = data.readInt();
        stride = data.readInt(); e.ensure(stride==texwidth*4);
        memorysize = data.readInt(); //the size of the rest of the object.
        xmipmaplevels = data.readByte();
        switch(parent.type)
        {
            case 0x00:
                //xmipmaplevels = data.readByte();
                xagrb = new int[xmipmaplevels][];
                //this may be wrong, see how its done in case 0x01.
                for(int i=0;i<xmipmaplevels;i++)
                {
                    int levelsize = (texwidth >>> i)*(texheight >>> i);
                    xagrb[i] = data.readInts(levelsize);
                }
                break;
            case 0x01:
                //xmipmaplevels = data.readByte();
                xtexel = new byte[xmipmaplevels][];
                for(int i=0;i<xmipmaplevels;i++)
                {
                    //see ptMipMap in pyprp
                    e.ensure(texwidth!=0);
                    e.ensure(texheight!=0);
                    int levelsize;
                    int levelwidth = texwidth >>> i;
                    int levelheight = texheight >>> i;
                    if(levelwidth<3 || levelheight<3)
                    {
                        levelsize = levelwidth * levelheight * 4; //not enough for a texel, so just use raw data.
                    }
                    else
                    {
                        levelsize = levelwidth * levelheight * b.ByteToInt32(parent.xtexel_size) / 16; //16 pixels per texel.
                    }
                    xtexel[i] = data.readBytes(levelsize);
                }
                break;
            case 0x02:
                xtype = data.readByte();
                
                if((xtype&0x01)==0)
                {
                    xjpgsize1 = data.readInt();
                    xjpegfile1 = data.readBytes(xjpgsize1);
                }
                else
                {
                    int i1;
                    int i2;
                    Vector<Integer> xextra1temp = new Vector<Integer>();
                    do
                    {
                        i1 = data.readInt();
                        i2 = data.readInt();
                        xextra1temp.add(i1);
                        xextra1temp.add(i2);
                    }while(i1!=0);
                    xextra1 = generics.convertVectorToArray(xextra1temp, Integer.class);
                }
                if((xtype&0x02)==0)
                {
                    xjpgsize2 = data.readInt();
                    xjpegfile2 = data.readBytes(xjpgsize2); //blue channel is the alpha channel.
                }
                else
                {
                    int i1;
                    int i2;
                    Vector<Integer> xextra2temp = new Vector<Integer>();
                    do
                    {
                        i1 = data.readInt();
                        i2 = data.readInt();
                        xextra2temp.add(i1);
                        xextra2temp.add(i2);
                    }while(i1!=0);
                    xextra2 = generics.convertVectorToArray(xextra2temp, Integer.class);
                }
                /*//xu1 = data.readByte();
                switch(xtype)
                {
                    case 0:
                        xjpgsize1 = data.readInt();
                        xjpegfile1 = data.readBytes(xjpgsize1);

                        xjpgsize2 = data.readInt();
                        xjpegfile2 = data.readBytes(xjpgsize2); //blue channel is the alpha channel.
                        break;
                    case 1:
                        xu2 = data.readInts(4);
                        
                        xjpgsize1 = data.readInt();
                        xjpegfile1 = data.readBytes(xjpgsize1);
                        break;
                    case 2:
                        xjpgsize1 = data.readInt();
                        xjpegfile1 = data.readBytes(xjpgsize1);

                        int i1;
                        int i2;
                        do
                        {
                            i1 = data.readInt();
                            i2 = data.readInt();
                            xextra.add(i1);
                            xextra.add(i2);
                        }while(i1!=0);
                        break;
                    default:
                         //it could be a 3 too, but that needs further analysis.
                        m.msg("unsupported type in mipmap.");
                        break;
                }*/
                break;
            default:
                m.msg("x0004mipmap: mystery!");
                break;
        }
    }
    public void compile(Bytedeque deque)
    {
        //m.err("fixme");
        parent.compile(deque);
        deque.writeInt(texwidth);
        deque.writeInt(texheight);
        deque.writeInt(stride);
        deque.writeInt(memorysize);
        
        deque.writeByte(xmipmaplevels);
        switch(parent.type)
        {
            case 0x00:
                //deque.writeByte(xmipmaplevels);
                for(int i=0;i<xmipmaplevels;i++)
                {
                    int levelsize = (texwidth >>> i)*(texheight >>> i);
                    deque.writeInts(xagrb[i]);
                }
                break;
            case 0x01:
                //deque.writeByte(xmipmaplevels);
                for(int i=0;i<xmipmaplevels;i++)
                {
                    int levelsize;
                    int levelwidth = texwidth >>> i;
                    int levelheight = texheight >>> i;
                    if(levelwidth<3 || levelheight<3)
                    {
                        levelsize = levelwidth * levelheight * 4; //not enough for a texel, so just use raw data.
                    }
                    else
                    {
                        levelsize = levelwidth * levelheight * b.ByteToInt32(parent.xtexel_size) / 16; //16 pixels per texel.
                    }
                    deque.writeBytes(xtexel[i]);
                }
                break;
            case 0x02:
                //deque.writeByte(xu1);
                deque.writeByte(xtype);
                
                if((xtype&0x01)==0)
                {
                    deque.writeInt(xjpgsize1);
                    deque.writeBytes(xjpegfile1);
                }
                else
                {
                    for(int i=0;i<xextra1.length;i++)
                    {
                        deque.writeInt(xextra1[i]);
                    }
                }
                if((xtype&0x02)==0)
                {
                    deque.writeInt(xjpgsize2);
                    deque.writeBytes(xjpegfile2); //blue channel.
                }
                else
                {
                    for(int i=0;i<xextra2.length;i++)
                    {
                        deque.writeInt(xextra2[i]);
                    }
                }
                /*switch(xtype)
                {
                    case 0:
                        deque.writeInt(xjpgsize1);
                        deque.writeBytes(xjpegfile1);
                        deque.writeInt(xjpgsize2);
                        deque.writeBytes(xjpegfile2);
                        break;
                    case 1:
                        deque.writeInts(xu2);
                        deque.writeInt(xjpgsize1);
                        deque.writeBytes(xjpegfile1);
                        break;
                    case 2:
                        deque.writeInt(xjpgsize1);
                        deque.writeBytes(xjpegfile1);
                        int i1;
                        int i2;
                        //do
                        //{
                            //i1 = data.readInt();
                            //i2 = data.readInt();
                            //xextra.add(i1);
                            //xextra.add(i2);
                        //}while(i1!=0);
                        for(int i=0;i<xextra1.size();i++)
                        {
                            deque.writeInt(xextra1.get(i));
                        }
                        break;
                    default:
                         //it could be a 3 too, but that needs further analysis.
                        m.msg("unsupported type in mipmap.");
                        break;
                }*/
                break;
            default:
                m.msg("x0004mipmap: mystery!");
                break;
        }
    }
}
