/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package realmyst;

/**
 *
 * @author user
 */
public enum Typeid
{
    
    unknown,
    sdbstart,
    u1,
    ;
    public static pair[] pairs = {
        p(0x9469DF4E, sdbstart),
        p(0x33571FBE, u1),
    };
    
    
    
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
        return unknown;
    }
}
