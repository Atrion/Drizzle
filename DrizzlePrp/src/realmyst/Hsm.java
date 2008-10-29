/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package realmyst;

import shared.*;

public class Hsm
{
    int u1;
    int flagsMaybe; //flags
    int widthMaybe; //width or maybe height
    int heightMaybe; //height or maybe width
    byte b5;
    int u6;
    int numLevels; //num levels?
    int u8;
    int bytesPerBlock; //bytes per 4x4 DXT block
    int dxtType; //DXT type 1=DXT1, 5=DXT5
    
    uru.moulprp.Image.Dxt dxt;
    
    public Hsm(IBytestream c)
    {
        u1 = c.readInt();
        flagsMaybe = c.readInt();
        widthMaybe = c.readInt();
        heightMaybe = c.readInt();
        b5 = c.readByte();
        u6 = c.readInt();
        numLevels = c.readInt();
        u8 = c.readInt();
        bytesPerBlock = c.readInt();
        dxtType = c.readInt();
        if(dxtType==1)
        {
            try{
                dxt = new uru.moulprp.Image.Dxt(c, numLevels, widthMaybe, heightMaybe, (byte)bytesPerBlock);
            }catch(Exception e){
                e.printStackTrace();
                throw new uncaughtexception("Error in Dxt parsing.");
            }
        }
        else if(dxtType==5)
        {
            try{
                dxt = new uru.moulprp.Image.Dxt(c, numLevels, widthMaybe, heightMaybe, (byte)bytesPerBlock);
            }catch(Exception e){
                e.printStackTrace();
                throw new uncaughtexception("Error in Dxt parsing.");
            }
        }
        else
        {
            m.err("Unhandled dxtType.");
        }
        int dummy = 0;
    }
}
