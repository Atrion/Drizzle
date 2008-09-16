/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deepview;

import javax.swing.JPanel;
import shared.FileUtils;
import uru.context;
import uru.Bytestream;
import uru.moulprp.PrpHeader;
import uru.moulprp.PrpObjectIndex;
import java.util.Vector;
import uru.moulprp.PrpRootObject;
import uru.moulprp.prpprocess;
import shared.m;
import uru.moulprp.prpfile;
import uru.moulprp.Typeid;
import uru.moulprp.uruobj;
import uru.moulprp.Uruobjectref;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

import java.lang.reflect.Field;

public class deepview
{
    public static void read(String filename, JPanel panel)
    {
        byte[] filedata = FileUtils.ReadFile(filename);
                
        //process all the objects as raw data.
        context c = context.createFromBytestream(new Bytestream(filedata));
        c.curFile = filename;
        prpfile prp = uru.moulprp.prpprocess.ProcessAllObjects(c,true);
        
        try
        {
            prp.objects[0].parseRawDataNow(); //actually parse the object.
            reflect(prp.objects[0],panel);
        }
        catch(Exception e)
        {
            m.err("Error during reflection.");
            e.printStackTrace();
        }
        
    }
    
    public static void reflect(PrpRootObject obj, JPanel panel) throws Exception
    {
        uruobj uo = obj.getObject();
        //panel.setLayout(new FlowLayout());
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        
        panel.add(new JLabel("Name:"+obj.header.desc.objectname.toString()));
        panel.add(new JLabel("Type:"+obj.header.desc.objecttype.toString()));

        reflect2(panel,uo,uo.getClass(),"putnamehere");
        
        //panel.setVisible(false);
        //panel.setVisible(true);
        //panel.invalidate();
        //panel.validate();
        panel.revalidate();
        //panel.repaint();
        //uo.getClass();
    }
    
    public static void reflect2(JPanel panel, Object obj, Class objclass, String name) throws Exception
    {
        if(objclass==null)
        {
            m.msg("null class encountered.");
            return;
        }
        if(obj==null)
        {
            m.msg("null object encountered.");
            return;
        }
        
        m.msg("doing "+obj.toString() + " of class "+objclass.getName());
        
        //Class objclass = obj.getClass();

        //if simple, just output
        if(objclass.isPrimitive()||objclass==String.class||objclass.isEnum())
        {
            panel.add(new JLabel("name:"+name+" type:"+objclass.getName()+" value:"+obj.toString()));
        }
        //if array, do each element
        else if(objclass.isArray())
        {
            m.msg("array unhandled.");
        }
        //otherwise, it's a class?
        else
        {
            if(objclass==Class.class) return;
            
            //depthString(result,depth);
            label(panel,"type:"+objclass.getName()+" class members:");

            //if inherited, do parent
            if(objclass.getSuperclass()!=Object.class) //get ancestor's info
            {
                //we don't actually use inheritance this way.
                //deepReflectionReport(obj,objclass.getSuperclass(),result, depth+0);
                m.msg("unhandled inheritance");
            }

            Field[] fields = objclass.getDeclaredFields(); //get all fields
            java.lang.reflect.AccessibleObject.setAccessible(fields, true);
            for(int i=0;i<fields.length;i++)
            {
                Class curclass = fields[i].getType();
                Object curfield = fields[i].get(obj);
                //deepReflectionReportText(curfield,curclass,result,depth+1);
                String curname = fields[i].getName();
                reflect2(panel, curfield, curclass, curname);
            }
        }
    }
    
    public static void label(JPanel panel, String text)
    {
        panel.add(new JLabel(text));
    }
}
