/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deepview;

import java.util.Vector;
import uru.moulprp.Uruobjectdesc;

public class refLinks
{
    Vector<refLink> reflinks=new Vector<refLink>();
    boolean acceptNewEntries=false;
    
    public static class refLink
    {
        public Uruobjectdesc from;
        public Uruobjectdesc to;
        
        public refLink(Uruobjectdesc from, Uruobjectdesc to)
        {
            this.from = from;
            this.to = to;
        }
    }
    
    public void add(Uruobjectdesc from, Uruobjectdesc to)
    {
        if(!acceptNewEntries) return;
        if(from==null || to==null) return;
        if(from.equals(to)) return; //skip self-references.
        reflinks.add(new refLink(from,to));
    }
}
