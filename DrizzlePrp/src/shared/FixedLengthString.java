/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

/**
 *
 * @author user
 */
public class FixedLengthString
{
    byte[] str;
    
    public FixedLengthString(IBytestream c, int length)
    {
        str = c.readBytes(length);
    }
    public void compile(IBytedeque c)
    {
        c.writeBytes(str);
    }
    public String toString()
    {
        return b.BytesToString(str);
    }
}
