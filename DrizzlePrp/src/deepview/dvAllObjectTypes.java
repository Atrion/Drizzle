/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deepview;

import uru.moulprp.Uruobjectref;
import uru.moulprp.Uruobjectdesc;
import uru.moulprp.Typeid;

public class dvAllObjectTypes extends dvPanel
{
    deepview parent;
    
    public dvAllObjectTypes(deepview parent)
    {
        this.parent = parent;
        
        reload();
    }
    
    public void reload()
    {
        this.removeAll();
        this.add(dvWidgets.jlabel("All Object Types:"));
        for(Typeid typeid: parent.alltypes)
        {
            this.add(new dvTypeid(typeid,parent));
        }
        
    }
    
}
