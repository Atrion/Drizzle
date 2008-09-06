package shared.State;



public class ComboboxState extends javax.swing.JComboBox implements IState
{
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

}
