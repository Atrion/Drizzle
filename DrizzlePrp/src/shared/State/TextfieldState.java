package shared.State;

import javax.swing.event.DocumentEvent;



public class TextfieldState extends javax.swing.JTextField implements IState
{
    private String _default = "";
    
    public TextfieldState()
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
        _default = (String)this.getValue();
        //is this correct?
        /*this.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                change();
            }
        });*/
        this.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                //shared.m.msg("insert");
                change();
            }
            public void removeUpdate(DocumentEvent e) {
                //shared.m.msg("remove");
                change();
            }
            public void changedUpdate(DocumentEvent e) {
                //shared.m.msg("changed");
                change();
            }
        });
    }
 
    public void setValue(Object obj)
    {
        this.setText((String)obj);
    }

    public Object getValue()
    {
        return this.getText();
    }
    
    public String getStateName()
    {
        return this.getName();
    }
    public void setDefault(Object obj)
    {
        this._default = (String)obj;
    }
    public Object getDefault()
    {
        if(_default==null) return getValue();
        else return this._default;
    }

}
