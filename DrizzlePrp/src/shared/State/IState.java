/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared.State;

/**
 *
 * @author user
 */
public interface IState
{
    void setDefault(Object obj);
    Object getDefault();
    void setValue(Object obj);
    Object getValue();
    String getStateName();
    void initialise();
}
