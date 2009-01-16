/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prpdistiller;

import uru.moulprp.*;
import java.util.Vector;
import java.util.HashMap;

public class distiller
{
    public static HashMap<Uruobjectdesc, Uruobjectdesc> distillTextures(String texturePrp, String destinationPrp, String[] affectedPrps)
    {
        prpfile destprp = prpfile.createFromFile(destinationPrp, true);
        prpfile textprp = prpfile.createFromFile(texturePrp, true);
        HashMap<Uruobjectdesc, Uruobjectdesc> refReassigns = new HashMap();
        distillTextures(destprp, textprp, new String[]{}, refReassigns);
        return refReassigns;
    }
    //moves the textures used by destinationPrp, into destinationPrp, and updates references in the affectedPrps.
    public static void distillTextures(prpfile destprp, prpfile textprp, String[] affectedPrps, HashMap<Uruobjectdesc, Uruobjectdesc> refReassigns)
    {
        
        
        PrpRootObject[] layers = destprp.FindAllObjectsOfType(Typeid.plLayer);
        for(PrpRootObject layer2: layers)
        {
            x0006Layer layer = layer2.castTo();
            PrpRootObject texture2 = textprp.findObjectWithRef(layer.texture);
            uru.moulprp.x0004MipMap texture = texture2.castTo();
            distiller.copyObjectAndModifyRef(texture2, destprp, refReassigns);
        }
        
        distiller.updateTextureRefs(destprp,refReassigns);
        for(String s: affectedPrps)
        {
            distiller.updateTextureRefs(s, refReassigns);
        }
        
    }
    
    private static void updateTextureRefs(String prpname, HashMap<Uruobjectdesc, Uruobjectdesc> refReassigns)
    {
        prpfile destprp = prpfile.createFromFile(prpname, true);
        updateTextureRefs(destprp,refReassigns);
    }
    public static void updateTextureRefs(prpfile destprp, HashMap<Uruobjectdesc, Uruobjectdesc> refReassigns)
    {
        for(PrpRootObject layer2: destprp.FindAllObjectsOfType(Typeid.plLayer))
        {
            x0006Layer layer = layer2.castTo();
            if(layer.texture.hasref())
            {
                int u1 = layer.texture.xdesc.hashCode();
                int u2 = refReassigns.keySet().toArray()[0].hashCode();
                Uruobjectdesc newdesc = refReassigns.get(layer.texture.xdesc);
                if(newdesc!=null)
                {
                    newdesc.copyInto(layer.texture.xdesc);
                }
            }
        }
                
    }
    public static void copyObjectAndModifyRef(PrpRootObject object, prpfile dest, HashMap<Uruobjectdesc, Uruobjectdesc> refReassigns)
    {
        //check if we have already reassigned this object.
        Uruobjectdesc newpos = refReassigns.get(object.header.desc);
        if(newpos==null)
        {
            //copy the object and add the ref to our list.
            Uruobjectdesc olddesc = object.header.desc.deepClone();
            Uruobjectdesc newdesc = olddesc.deepClone();
            newdesc.pageid = dest.header.pageid; //change pageid.
            newdesc.pagetype = dest.header.pagetype;
            PrpRootObject newobj = PrpRootObject.createFromDescAndObject(newdesc, object.getObject());
            dest.addObject(newobj);
            refReassigns.put(olddesc, newdesc);
        }
        else
        {
            //do nothing, we've already got this object.
        }
    }
    
    public static void updateAllReferences(prpfile prpToUpdate, Uruobjectdesc olddesc, Uruobjectdesc newdesc)
    {
        Vector<Uruobjectdesc> descs = shared.FindAllDescendants.FindAllDescendantsByClass(Uruobjectdesc.class, prpToUpdate);
        for(Uruobjectdesc desc: descs)
        {
            if(desc.equals(olddesc))
            {
                newdesc.copyInto(desc);
            }
        }
    }
    
}
