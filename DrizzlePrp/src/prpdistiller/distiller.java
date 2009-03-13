/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prpdistiller;

import uru.moulprp.*;
import java.util.Vector;
import java.util.HashMap;
import java.util.ArrayDeque;
import shared.m;

public class distiller
{
    public static int distillEverything(prpfile dest, Vector<prpfile> prpfiles, HashMap<Uruobjectdesc, Uruobjectdesc> refReassigns)
    {
        int numlevels = 0;
        while(true)
        {
            int numSaw = distillEverythingOneLayerDeep(dest,prpfiles,refReassigns);
            if(numSaw==0)
            {
                return numlevels;
            }
            else
            {
                numlevels++;
            }
        }
    }

    public static int distillEverythingOneLayerDeep(prpfile dest, Vector<prpfile> prpfiles, HashMap<Uruobjectdesc, Uruobjectdesc> refReassigns)
    {
        Pageid destid = dest.header.pageid;

        int numSaw = 0;

        //ArrayDeque<PrpRootObject> newobjs = new ArrayDeque();
        //HashMap<Uruobjectdesc, Uruobjectdesc> refReassigns = new HashMap();
        for(Uruobjectdesc desc: shared.FindAllDescendants.FindAllDescendantsByClass(Uruobjectdesc.class, dest))
        {
            if(!desc.pageid.equals(destid))
            {
                //copy this object.
                numSaw++;

                if(desc.objecttype==Typeid.plSceneNode)
                {
                    //point it to the local scenenode instead. (assume it has 1 and only 1.)
                    Uruobjectdesc sndesc = dest.FindAllObjectsOfType(Typeid.plSceneNode)[0].header.desc;
                    sndesc.copyInto(desc);
                }
                else
                {
                    //find it
                    boolean found = false;
                    for(prpfile prp: prpfiles)
                    {
                        PrpRootObject obj = prp.findObject(desc.objectname.toString(), desc.objecttype);
                        if(obj!=null)
                        {
                            //found it
                            distiller.copyObjectAndModifyRef(obj, dest, refReassigns);
                            if(desc.objecttype==Typeid.plSceneObject)
                            {
                                //add to scenenode.
                                uru.moulprp.x0000Scenenode sn = dest.FindAllObjectsOfType(Typeid.plSceneNode)[0].castTo();
                                sn.addToObjectrefs1(Uruobjectref.createFromUruobjectdesc(desc));
                            }
                            found = true;
                            break;
                        }
                    }
                    if(!found) m.warn("Unable to find obj.");
                }
            }
        }

        updateAllReferences(dest, refReassigns);

        return numSaw;
    }

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
    
    public static void updateAllReferences(prpfile prpToUpdate, HashMap<Uruobjectdesc, Uruobjectdesc> refReassigns)
    {
        Vector<Uruobjectdesc> descs = shared.FindAllDescendants.FindAllDescendantsByClass(Uruobjectdesc.class, prpToUpdate);
        for(Uruobjectdesc desc: descs)
        {
            Uruobjectdesc newdesc = refReassigns.get(desc);
            if(newdesc==null)
            {
                //not reassigned so ignore.
            }
            else
            {
                newdesc.copyInto(desc);
            }
        }
    }
    
}
