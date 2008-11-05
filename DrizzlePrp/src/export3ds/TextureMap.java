/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package export3ds;

import shared.*;

public class TextureMap extends tdsobj
{
    
    public TextureFilename texturefilename;
    
    private TextureMap(){}
    
    public static TextureMap create(String filename)
    {
        TextureMap result = new TextureMap();
        result.texturefilename = TextureFilename.create(filename);
        return result;
    }
    
    public Typeid type(){return Typeid.texturemap;}

    public void innercompile(IBytedeque c)
    {
        texturefilename.compile(c);
    }
    
}
