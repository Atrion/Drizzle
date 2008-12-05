package shared.State;

import javax.swing.event.ListSelectionEvent;
import java.util.Vector;



public class ListState extends javax.swing.JList implements IState
{
    private int[] _default;
    
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
        _default = (int[])this.getValue();
        //is this correct?
        this.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                change();
            }
        });
        common.addSpecialMenu(this);
    }

    public void putValue(Object obj)
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
    public Vector<String> getSelectedAsStrings()
    {
        Object[] objs = (Object[])this.getSelectedValues();
        Vector<String> list = new Vector<String>();
        for(Object obj: objs) list.add((String)obj);
        return list;
    }
    //public void setDefault(Object obj)
    //{
    //    this._default = (int[])obj;
    //}
    public Object getDefault()
    {
        if(_default==null) return getValue();
        else return this._default;
    }

}
