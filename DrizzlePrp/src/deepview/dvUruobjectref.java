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

public class dvUruobjectref extends dvPanel
{
    Uruobjectref ref;
    String name;
    
    public dvUruobjectref(Uruobjectref ref, String name)
    {
        this.ref = ref;
        this.name = name;
        reload();
    }
    private void reload()
    {
        this.removeAll();
        this.add(dvWidgets.jlabel("UruObjectRef name:"+name+" ref:"+ref.toString()));
        JButton button = dvWidgets.jbutton("testbutton");
        button.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                handleclick();
            }
        });
        this.add(button);
        this.revalidate();
    }
    
    void handleclick()
    {
        m.msg(name);
        JList list = new JList();
        list.setListData(deepview.allrefs);
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                handleselection(e.getSource());
            }
        });
        this.add(list);
        this.revalidate();
    }
    
    void handleselection(Object source)
    {
        Object ref1 = ((JList)source).getSelectedValue();
        Uruobjectref ref2 = (Uruobjectref)ref1;
        this.ref.shallowCopyFrom(ref2);
        m.warn("Shallow copy performed, be careful of deeper changes.");
        this.reload();
    }
}
