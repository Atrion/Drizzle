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

package realmyst;

import shared.*;

public class Count10
{
    //int tag;
    public Typeid type;
    int size;
    int sub1;
    //Bstr xsubs;
    Flagsen xsubs;
    int sub2;
    //Flagsen[] subs2;
    TaggedObj[] subs2;
    
    public Count10(IBytestream c)
    {
        //sub_5027f0???
        
        //tag = c.readInt(); e.ensure(Typeid.has(tag));
        type = Typeid.read(c); e.ensure(type==Typeid.count10);
        size = c.readInt(); //size of this object (including type).
        
        int u1 = c.readInt(); e.ensure(u1,1);
        Bstr s1 = new Bstr(c);
        byte b1 = c.readByte(); e.ensure((int)b1,16);
        int u2 = c.readInt(); e.ensure(u2,0);
        int u3 = c.readInt(); e.ensure(u3,0);
        int u4 = c.readInt(); e.ensure(u4,4);
        int u5 = c.readInt(); e.ensure(u5,0,256,8,1);
        Bstr s2 = new Bstr(c);
        
        int count1 = c.readInt();
        c.readArray(occref.class, count1);

        int count2 = c.readInt();
        c.readArray(occref.class, count2);
        
        int count3Maybe = c.readInt(); e.ensure(count3Maybe==0);
        
        int count4 = c.readInt();
        c.readArray(stringAndByte.class, count4);
        
        /*sub1 = c.readInt();
        if(sub1!=0)
        {
            //45a950 block
            //xsubs = new Bstr(c);
            //byte type = c.readByte();
            xsubs = new Flagsen(c);
            int dummy=0;
        }
        sub2 = c.readInt();
        subs2 = new TaggedObj[sub2];
        for(int j=0;j<sub2;j++)
        {
            //45a950 block
            //m.err("unhandled");
            //Bstr subs2 = new Bstr(c);
            subs2[j] = new TaggedObj(c);
        }*/
        
    }
    public static class stringAndByte
    {
        public stringAndByte(IBytestream c)
        {
            //sub_45A950, I think
            
            Bstr str = new Bstr(c);
            byte b = c.readByte(); //this is used in the switch.
            int dummy=0;
        }
    }
    public static class occref
    {
        public static class suboccref
        {
            public suboccref(IBytestream c)
            {
                //sub_5070E0??? (called only by sub_508420)
                
                int u6 = c.readInt(); e.ensure(u6,0);
                int u7 = c.readInt(); e.ensure(u7,1);
                int u8 = c.readInt(); e.ensure(u8,0,37842956); //only saw 27842956 once
                int u9 = c.readInt(); e.ensure(u9,1,2,3,4,5,6);
                m.msg("u9="+Integer.toString(u9));
                if(u9==5)
                {
                    //this is presumably a vertex or quaternion.
                    int u10 = c.readInt();
                    Flt x = new Flt(c);
                    Flt y = new Flt(c);
                    Flt z = new Flt(c);
                    int uc1 = c.readInt(); //seems to be 0, so probably a Flt, making this a quaternion.
                    if(uc1!=0)
                    {
                        int dummy=0;
                    }
                    int dummy=0;
                }
                else if(u9==6)
                {
                    int u10 = c.readInt();
                    int count = c.readInt();
                    Bstr[] sb1 = c.readArray(Bstr.class, count);
                    int dummy=0;
                }
                else if(u9==4)
                {
                    //vertex?
                    int u10 = c.readInt();
                    //int ua1 = c.readInt();
                    //int ua2 = c.readInt();
                    //int ua3 = c.readInt();
                    Flt ua1 = new Flt(c);
                    Flt ua2 = new Flt(c);
                    Flt ua3 = new Flt(c);
                    //if(!ua1.approxequals(0))
                    //{
                    //    int dummy=0;
                    //}
                }
                else if(u9==3)
                {
                    int u10 = c.readInt(); //e.ensure(u10,1,0,16);
                    Bstr s1 = new Bstr(c);
                    int dummy=0;
                }
                else if(u9==1)
                {
                    int u10 = c.readInt();
                    int u11 = c.readInt();
                }
                else if(u9==2)
                {
                    int u10 = c.readInt();
                    Flt f1 = new Flt(c);
                }
            
            }
        }
        public occref(IBytestream c)
        {
            //sub_508420?
            
            Typeid type = Typeid.read(c); //e.ensure(type==Typeid.occref);
            int u1 = c.readInt(); e.ensure(u1,0);
            int u2 = c.readInt(); e.ensure(u2,1);
            int u3 = c.readInt(); //?
            int u4 = c.readInt(); e.ensure(u4,0,1,4,6,7,5,37837212);
            int count = c.readInt(); //e.ensure(u5,2);
            suboccref[] subs = c.readArray(suboccref.class, count);
            //Object[] subs = new Object[count];
            //for(int i=0;i<count;i++)
            //{
            //    subs[i] = TaggedObj.readwithtype(type, c);
            //}
            
            /*int u6 = c.readInt(); e.ensure(u6,0);
            int u7 = c.readInt(); e.ensure(u7,1);
            int u8 = c.readInt(); e.ensure(u8,0);
            int u9 = c.readInt(); e.ensure(u9,3);
            int u10 = c.readInt(); e.ensure(u10,1);
            Bstr s1 = new Bstr(c);
            int u11 = c.readInt();
            int u12 = c.readInt();
            int u13 = c.readInt();
            int u14 = c.readInt();
            int u15 = c.readInt();
            int u16 = c.readInt();*/
            int dummy=0;
        }
    }
}
