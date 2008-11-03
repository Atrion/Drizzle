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

public class Count9
{
    
    
    public Count9(IBytestream c)
    {
        Typeid type = Typeid.read(c); e.ensure(type==Typeid.count9);
        int size = c.readInt(); //size of this object.
        
        int u1 = c.readInt(); e.ensure(u1,1);
        Bstr s1 = new Bstr(c);
        byte b1 = c.readByte(); e.ensure((int)b1,15);
        int u2 = c.readInt();
        int u3 = c.readInt();
        int u4 = c.readInt();
        int u5 = c.readInt();
        int u6 = c.readInt();
        int u7 = c.readInt();
        int u8 = c.readInt();
        int u9 = c.readInt();
        //Bstr s2 = new Bstr(c);
        Bstr[] s2s = c.readArray(Bstr.class, u9);
        int u10 = c.readInt(); e.ensure(u10>=0);//e.ensure(u10,0,1,2,3); //0
        if(u10!=1)
        {
            int dummy=0;
        }
        //else if(u10==1 || u10==3 || u10==2)
        //{
            //int u11 = c.readInt(); e.ensure(u11,1001); //3
            //Typeid reftype = Typeid.read(c);
            TaggedObj[] refs = c.readArray(TaggedObj.class, u10);
            //if(reftype==Typeid.ref)
            //{
            //    if(u10!=1)
            //    {
            //        int dummy=0;
            //    }
                /*count9ref ref = new count9ref(c);
                int u12 = c.readInt(); e.ensure(u12,1); //if this is not 1, the array part might break because it might be wrong.
                //Bstr s3 = new Bstr(c);
                Bstr[] s3s = c.readArray(Bstr.class, u12);*/
            //    Ref1[] ref1s = c.readArray(Ref1.class, u10);
            //    int dummy=0;
            //}
            //else if(reftype==Typeid.ref2)
            //{
            //    if(u10!=1)
            //    {
            //        int dummy=0;
            //    }
            //    Ref2[] ref2s = c.readArray(Ref2.class, u10);
            //}
            //else
            //{
            //    m.err("Unhandled reftype in Count9.");
            //}
        //}
        int strcount = c.readInt();
        Bstr[] strs = c.readArray(Bstr.class, strcount);
       
        int dummy=0;
    }
    public static class Subref2
    {
        public Subref2(IBytestream c)
        {
            //int count = c.readInt();
            //Bstr[] strs = c.readArray(Bstr.class, 1);
            int dummy=0;
        }
    }
    public static class Ref1
    {
        public Ref1(IBytestream c)
        {
            Count10.occref ref = new Count10.occref(c);
            int u12 = c.readInt(); //e.ensure(u12,1); //if this is not 1, the array part might break because it might be wrong.
            //Bstr s3 = new Bstr(c);
            Bstr[] s3s = c.readArray(Bstr.class, u12);
            int dummy=0;
            
        }
    }
    public static class Ref2
    {
        public Ref2(IBytestream c)
        {
            Typeid targetTypeMaybe = Typeid.read(c);
            int u1 = c.readInt(); e.ensure(u1,0);
            int u2 = c.readInt(); e.ensure(u2,1);
            int u3 = c.readInt(); e.ensure(u3,8);
            int u4 = c.readInt(); e.ensure(u4,2);
            int u5 = c.readInt(); //e.ensure(u5,676); //676,762,1064,etc
            int u6 = c.readInt(); e.ensure(u6,0);
            Bstr s1 = new Bstr(c);
            int u7 = c.readInt(); e.ensure(u7,3);
        
            //int strcount = c.readInt();
            //Bstr[] strs = c.readArray(Bstr.class, strcount);
        }
    }
}
