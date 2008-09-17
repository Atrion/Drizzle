/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deepview;

import uru.moulprp.PrpRootObject;
        
public class dvPrpRootObject extends dvPanel
{
    PrpRootObject prprootobject;
    
    public dvPrpRootObject(PrpRootObject prprootobject)
    {
        this.prprootobject = prprootobject;
        reload();
    }
    
    private void reload()
    {
        this.removeAll();
        this.add(dvWidgets.jlabel("PrpRootObject "+prprootobject.header.toString()));
        for(refLinks.refLink rl: deepview.allreflinks.reflinks)
        {
            if(prprootobject.header.desc.equals(rl.to))
            {
                this.add(dvWidgets.jlabel("found a ref:"+rl.from.toString()));
            }
        }
        this.revalidate();
    }
    
}
