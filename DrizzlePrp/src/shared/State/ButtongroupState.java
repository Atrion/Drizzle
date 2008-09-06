package shared.State;

import java.util.Enumeration;
import javax.swing.AbstractButton;

public class ButtongroupState extends javax.swing.ButtonGroup implements IState
{
    private String _name;
    
    public ButtongroupState()
    {
        super();
        
        AllStates.register(this);
        
    }
    
    private void change()
    {
        AllStates.update(this);
    }
    public void initialise()
    {
        for(AbstractButton curbutton: this.buttons)
        {
            curbutton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    change();
                }
            });
        }
    }
    public void setValue(Object obj)
    {
        Enumeration<AbstractButton> buttons = this.getElements();
        int i = 0;
        while(buttons.hasMoreElements())
        {
            AbstractButton button = buttons.nextElement();
            if(i==(Integer)obj)
            {
                button.setSelected(true);
                return;
            }
            i++;
        }
    }

    public Object getValue()
    {
        Enumeration<AbstractButton> buttons = this.getElements();
        int i = 0;
        while(buttons.hasMoreElements())
        {
            if(buttons.nextElement().isSelected()) return i;
            i++;
        }
        return -1;
    }
    
    public String getStateName()
    {
        //initialised = true;
        return this.getName();
    }
    
    public String getName()
    {
        return _name;
    }
    public void setName(String newname)
    {
        _name = newname;
    }

}
