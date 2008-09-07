package shared.State;

import javax.swing.event.ListSelectionEvent;



public class ListState extends javax.swing.JList implements IState
{
    public ListState()
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
        this.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                change();
            }
        });
    }

    public void setValue(Object obj)
    {
        this.setSelectedIndices((int[])obj);
    }

    public Object getValue()
    {
        return this.getSelectedIndices();
    }
    
    public String getStateName()
    {
        return this.getName();
    }
    
    public void selectAll()
    {
        int size =this.getModel().getSize();
        this.setSelectionInterval(0, size-1);        
    }
    public void selectNone()
    {
        this.getSelectionModel().clearSelection();
    }

}
