package shared.State;



public class ComboboxState extends javax.swing.JComboBox implements IState
{
    private Object _default;
    
    public ComboboxState()
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
        _default = this.getSelectedItem();
        //is this correct?
        this.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                change();
            }
        });
    }
    
    public void setValue(Object obj)
    {
        this.setSelectedItem(obj);
    }

    public Object getValue()
    {
        return this.getSelectedItem();
    }
    
    public String getStateName()
    {
        return this.getName();
    }
    public void setDefault(Object obj)
    {
        this._default = (Object)obj;
    }
    public Object getDefault()
    {
        if(_default==null) return getValue();
        else return this._default;
    }

}
