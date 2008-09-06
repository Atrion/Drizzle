package shared.State;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JCheckBox;

/**
 *
 * @author user
 */
public class CheckboxState extends javax.swing.JCheckBox implements IState
{
    public CheckboxState()
    {
        super();
        
        //this.getParent().getClass().
        //String name = this.getClass().getName();

        //AllStates.register(this.getActionCommand(), this);
        //AllStates.register(this.getName(), this);
        AllStates.register(this);
        
        //this.add
    }
    
    private void change()
    {
        AllStates.update(this);
    }
    
    public void initialise()
    {
        this.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                change();
            }
        });
    }
    
    public void setValue(Object obj)
    {
        //Object obj = AllStates.getState(this.getActionCommand());
        //if(obj!=null) this.setSelected((Boolean)obj);
        this.setSelected((Boolean)obj);
    }
    public Object getValue()
    {
        return this.isSelected();
    }
    
    public String getStateName()
    {
        return this.getName();
    }
    
}