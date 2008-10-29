/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package realmyst;

import shared.*;

public enum Typeid
{
    
    unknown,
    sdbstart,
    xbe1f5733,
    x5c3e0f00,
    ;
    public static pair[] pairs = {
        p(0x9469DF4E, sdbstart),
        p(0x33571FBE, xbe1f5733),
        p(0x000f3e5c, x5c3e0f00),
    };
    
    
    public static Typeid read(IBytestream c)
    {
        int data = c.readInt();
        Typeid result = getType(data);
        return result;
    }
    public static boolean has(int i)
    {
        for(pair p: pairs)
        {
            if(p.i==i) return true;
        }
        return false;
    }
    public static pair p(int i, Typeid type)
    {
        return new pair(i,type);
    }
    public static class pair
    {
        int i;
        Typeid type;
        
        public pair(int i, Typeid type)
        {
            this.i = i;
            this.type = type;
        }
    }
    
    public int getInt()
    {
        for(pair p: pairs)
        {
            if(p.type==this)
            {
                return p.i;
            }
        }
        return -1;
    }
    public static int getInt(Typeid type)
    {
        return type.getInt();
    }
    public static Typeid getType(int i)
    {
        for(pair p: pairs)
        {
            if(p.i==i)
            {
                return p.type;
            }
        }
        m.warn("Unhandled type in Typeid.");
        return unknown;
    }
}
