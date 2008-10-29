/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package realmyst;

import shared.*;
import shared.e;

public class Shp
{
    ReverseInt ver1;
    ReverseInt ver2;
    ReverseInt ver3;
    
    //these aren't used.
    int u1;
    int u2;
    int u3;
    int u4;
    
    //sub_4d1130
    int v3;
    int v35;

    public Shp(IBytestream c)
    {
        ver1 = new ReverseInt(c);
        ver2 = new ReverseInt(c);
        ver3 = new ReverseInt(c);
        e.ensure(ver1.convertToInt()==123456789);
        e.ensure(ver2.convertToInt()==4);
        e.ensure(ver3.convertToInt()==0);
        
        u1 = c.readInt();
        u2 = c.readInt();
        u3 = c.readInt();
        u4 = c.readInt();
        
        v3 = c.readInt();
        v35 = c.readInt();
        for(int i=0;i<v3;i++)
        {
            //does something???
        }
        int counter = v3>0?v3:v35; //this seems to be what the decomilation shows.
        for(int i=0;i<counter;i++)
        {
            
        }
    }
}
