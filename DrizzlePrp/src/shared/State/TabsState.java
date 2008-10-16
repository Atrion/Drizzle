package shared.State;

import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.event.ChangeEvent;

public class TabsState extends javax.swing.JTabbedPane implements IState
{
    private Integer _default;
    
    public TabsState()
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
        this.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                change();
            }
        });
    }
    public void setValue(Object obj)
    {
        this.setSelectedIndex((Integer)obj);
    }

    public Object getValue()
    {
        return this.getSelectedIndex();
    }
    
    public String getStateName()
    {
        //initialised = true;
        return this.getName();
    }
    
    public void setDefault(Object obj)
    {
        this._default = (Integer)obj;
    }
    public Object getDefault()
    {
        if(_default==null) return getValue();
        else return this._default;
    }

}
