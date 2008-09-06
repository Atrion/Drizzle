package shared.State;

import javax.swing.event.DocumentEvent;



public class TextfieldState extends javax.swing.JTextField implements IState
{
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

}