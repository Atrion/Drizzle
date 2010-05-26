/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

/**
 *
 * @author user
 */
public class uncaughtnestedexception extends java.lang.RuntimeException
{
    public Exception e;
    public String msg;

    public uncaughtnestedexception(Exception e)
    {
        this.e = e;
        m.err(e.getMessage());
    }

    public void printStackTrace()
    {
        e.printStackTrace();
        super.printStackTrace();
    }

    public uncaughtnestedexception(Exception e, String msg)
    {
        this.e = e;
        m.err(e.getMessage());
        this.msg = msg;
        m.err(msg);
    }
    
}
