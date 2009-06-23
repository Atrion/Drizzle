/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared.State;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

public class common
{
    public static void addSpecialMenu(IState state, JComponent component)
    {
        final IState state2 = state;
        //JComponent component = (JComponent)state;
        component.addMouseListener(new javax.swing.event.MouseInputListener() {
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3)
                {
                    javax.swing.JPopupMenu popup = new javax.swing.JPopupMenu();
                    javax.swing.JComponent parent = (javax.swing.JComponent)e.getSource();
                    javax.swing.JMenuItem mi = new javax.swing.JMenuItem("set to default");
                    mi.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            Object o = state2.getDefault();
                            state2.putStateValue(o);
                        }
                    });
                    java.awt.Point p = e.getPoint();
                    popup.add(mi);
                    popup.show(parent, p.x , p.y);
                }
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseDragged(MouseEvent e) {}
            public void mouseMoved(MouseEvent e) {}
        });
    }
    
    public static void addSpecialMenu(IState state)
    {
        //final IState state2 = state;
        JComponent component = (JComponent)state;
        addSpecialMenu(state, component);
    }
}
