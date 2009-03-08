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
        _default = (Integer)this.getValue();
        this.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                change();
            }
        });
        //common.addSpecialMenu(this); //doesn't really matter for Tabs.
    }
    public void putValue(Object obj)
    {
        int i = (Integer)obj;
        if(i>=this.getTabCount())
        {
            this.setSelectedIndex((Integer)getDefault());
        }
        else
        {
            this.setSelectedIndex((Integer)obj);
        }
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
    
    //public void setDefault(Object obj)
    //{
    //    this._default = (Integer)obj;
    //}
    public Object getDefault()
    {
        if(_default==null) return getValue();
        else return this._default;
    }

}
