/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package uru.moulprp;

/**
 *
 * @author user
 */
public class NameAndType
{
    public String name;
    public Typeid type;

    public NameAndType(String name, Typeid type)
    {
        this.name = name;
        this.type = type;
    }

    public static NameAndType createWithNameType(String name, Typeid type)
    {
        return new NameAndType(name,type);
    }
}
