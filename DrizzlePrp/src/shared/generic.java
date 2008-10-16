/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shared;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Vector;

public class generic
{
    public static <T> T createObjectWithDefaultConstructor(Class<T> c)
    {
        try
        {
            Constructor constr = c.getConstructor();
            Object result = constr.newInstance();
            T result2 = c.cast(result);
            return result2;
        }
        catch(Exception e)
        {
            throw new shared.uncaughtexception("gereric: Unable to create an instance of this class.");
        }
    }
    public static <T> T createShallowClone(T obj)
    {
        try
        {
            Method m = obj.getClass().getMethod("clone");
            Object clone = m.invoke(obj);
            T result = (T)clone;
            return result;
        }
        catch(Exception e)
        {
            throw new shared.uncaughtexception("gereric: Unable to create a shallow clone of this object.");
        }
    }
    public static <T> T createSerializedClone(T obj)
    {
        try
        {
            //write
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream out2 = new ObjectOutputStream(out);
            out2.writeObject(obj);
            byte[] bytes = out.toByteArray();
            
            //read
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ObjectInputStream in2 = new ObjectInputStream(in);
            T result = (T)in2.readObject();
            
            return result;
            
        }
        catch(Exception e)
        {
            throw new shared.uncaughtexception("gereric: Unable to create a serialized clone of this object.");
        }
    }
    public static <T> T[] convertVectorToArray(Vector<T> v, Class c)
    {
        T[] result = (T[])makeArray(c,v.size());
        for(int i=0;i<result.length;i++)
        {
            result[i] = v.get(i);
        }
        return result;
    }
    public static <T> Vector<T> convertArrayToVector(T[] arry)
    {
        //m.msg("Using untested convertArrayToVector method.");
        Vector<T> result = new Vector<T>();
        for(int i=0;i<arry.length;i++)
        {
            result.add(arry[i]);
        }
        return result;
    }
    public static <T> T[] makeArray(Class objclass, int length)
    {
        T[] result = (T[])java.lang.reflect.Array.newInstance(objclass, length);
        return result;
    }
    
}
