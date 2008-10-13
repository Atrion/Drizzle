/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deepview;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import javax.swing.event.ListSelectionEvent;
import uru.moulprp.Uruobjectref;
import javax.swing.JButton;
import shared.m;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JComboBox;
import uru.moulprp.PrpRootObject;
import uru.moulprp.prpfile;
import uru.moulprp.Urustring;
import uru.moulprp.*;

public class dvTransmatrix extends dvPanel
{
    Transmatrix mat;
    String name;
    deepview parent;
    //boolean changeable;
    prpfile prp;
    PrpRootObject rootobj;
    
    public dvTransmatrix(Transmatrix mat, String name, deepview parent)
    {
        this.mat = mat;
        this.name = name;
        this.parent = parent;
        //this.changeable = changeable;
        
        prp = parent.curprp;
        rootobj = parent.curobj;
        reload();
    }
    private void reload()
    {
        this.removeAll();
        this.add(dvWidgets.jlabel("Transmatrix name:"+name+" value:"+mat.toString()));
        
        this.revalidate();
    }
    
}
