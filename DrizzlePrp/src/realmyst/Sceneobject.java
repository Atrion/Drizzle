/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package realmyst;

import shared.*;

public class Sceneobject
{
    short s1;
    short s2;
    int u1;
    
    int xa;
    int xb;
    int xc;
    int xd;
    
    public Sceneobject(IBytestream c)
    {
        s1 = c.readShort();
        s2 = c.readShort();
        u1 = c.readInt();
        
        if((u1 & 0x1)!=0)
        {
            xa = c.readInt();
        }
        if((u1 & 0x2)!=0)
        {
            xb = c.readInt();
        }
        if((u1 & 0x4)!=0)
        {
            xc = c.readInt();
        }
        if((u1 & 0x8)!=0)
        {
            xd = c.readInt();
        }
        if((u1 & 0x200)!=0)
        {
            int dummy=0;
        }
        if((u1 & 0x1000)!=0)
        {
            //offset 56 is set
            int dummy=0;
        }
        if((u1 & 0x400)!=0)
        {
            //offset 60 is set
            int count = c.readInt();
        }
        if((u1 & 0x10)!=0)
        {
            int dummy=0;
            
        }
        if((u1 & 0x20)!=0)
        {
            //open behaviorState
            int dummy=0;
        }
        if((u1 & 0x100)!=0)
        {
            
            int dummy=0;
        }
    }
}
