/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deepview;

import uru.moulprp.Uruobjectref;
import uru.moulprp.Uruobjectdesc;
import uru.moulprp.Typeid;

public class dvAllObjectsOfType extends dvPanel
{
    deepview parent;
    Typeid type;
    
    public dvAllObjectsOfType(deepview parent, Typeid type)
    {
        this.parent = parent;
        this.type = type;
        
        reload();
    }
    
    public void reload()
    {
        this.removeAll();
        this.add(dvWidgets.jlabel("All Objects:"));
        for(Uruobjectref ref: parent.allrefs)
        {
            if(ref.hasref() && ref.xdesc.objecttype==type)
            {
                this.add(new dvUruobjectref(ref,"object",parent,false));
            }
        }
        
    }
    
}
