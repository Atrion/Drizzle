/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

import uru.moulprp.*;
import shared.m;

public class listSpawnpoints
{
    public static void listSpawnpoints(String filename)
    {
        prpfile prp = prpfile.createFromFile(filename, true);
        
        //PrpRootObject[] spawnmods = prp.FindAllObjectsOfType(Typeid.plSpawnModifier);
        PrpRootObject[] sceneobjects = prp.FindAllObjectsOfType(Typeid.plSceneObject);
        
        for(PrpRootObject obj2: sceneobjects)
        {
            x0001Sceneobject so = obj2.castTo();
            for(Uruobjectref ref: so.objectrefs2)
            {
                if(ref.hasref() && ref.xdesc.objecttype==Typeid.plSpawnModifier)
                {
                    m.msg("SpawnPoint: ",obj2.toString()," uses plSpawnModifier: ",ref.toString());
                }
            }
        }
    }
}
