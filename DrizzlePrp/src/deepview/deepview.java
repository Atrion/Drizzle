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
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import java.lang.reflect.Field;
import java.util.Vector;

public class deepview
{
    public static Vector<Uruobjectref> allrefs;
    public static refLinks allreflinks = new refLinks();
    public static prpfile prp;
    
    public static void read(String filename, JPanel panel)
    {
                
        //process all the objects as raw data.
        //allreflinks = new refLinks();
        allreflinks.reflinks.clear();
        byte[] filedata = FileUtils.ReadFile(filename);
        context c = context.createFromBytestream(new Bytestream(filedata));
        c.curFile = filename;
        allreflinks.acceptNewEntries = true;
        uru.moulprp.prpprocess.ProcessAllObjects(c.Fork(), false); //parse all to find all Uruobjectdesc links.
        allreflinks.acceptNewEntries = false;
        prp = uru.moulprp.prpprocess.ProcessAllObjects(c,true);
        
        //get all Uruobjectrefs.
        allrefs = new Vector<Uruobjectref>();
        for(PrpRootObject curRootObject: prp.objects)
        {
            allrefs.add(Uruobjectref.createFromUruobjectdesc(curRootObject.header.desc));
        }
        
        for(PrpRootObject curRootObject: prp.objects)
        {
            try
            {
                curRootObject.parseRawDataNow(); //actually parse the object.
                reflect(curRootObject,panel);
            }
            catch(Exception e)
            {
                m.err("Error during reflection.");
                e.printStackTrace();
            }
        }
        
    }
    
    public static void reflect(PrpRootObject obj, JPanel panel) throws Exception
    {
        uruobj uo = obj.getObject();
        //panel.setLayout(new FlowLayout());
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JPanel panel2 = new dvPanel();
        panel.add(panel2);
        
        //panel2.add(new JLabel("Name:"+obj.header.desc.objectname.toString()));
        //panel2.add(new JLabel("Type:"+obj.header.desc.objecttype.toString()));

        //reflect2(panel2,uo,uo.getClass(),"putnamehere");
        reflect2(panel2,obj,obj.getClass(),"putnamehere");
        
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
        
        if(objclass==Uruobjectref.class)
        {
            panel.add(new dvUruobjectref((Uruobjectref)obj,name));
            return;
        }
        
        if(objclass==PrpRootObject.class)
        {
            //PrpRootObject pro = (PrpRootObject)obj;
            //panel.add(dvWidgets.jlabel("PrpRootObject Name:"+pro.header.desc.objectname.toString()+"Type:"+pro.header.desc.objecttype.toString()));
            panel.add(new dvPrpRootObject((PrpRootObject)obj));

        }

        //if simple, just output
        if(objclass.isPrimitive()||objclass==String.class||objclass.isEnum()||objclass==Byte.class||objclass==Integer.class||objclass==Short.class||objclass==Boolean.class||objclass==Long.class||objclass==Float.class||objclass==Double.class||objclass==Character.class)
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
            
            //JPanel subpanel = dvWidgets.jpanel();
            JPanel subpanel = new dvPanel();
            panel.add(subpanel);

            //depthString(result,depth);
            subpanel.add(dvWidgets.jlabel("name:"+name+" type:"+objclass.getName()+" class members:"));

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
                Class curclass;
                Object curfield = fields[i].get(obj);
                if(curfield!=null)
                {
                    curclass = curfield.getClass(); //bugfix: get the actual class, not some ancestor or interface.
                }
                else
                {
                    curclass = fields[i].getType();
                }
                //deepReflectionReportText(curfield,curclass,result,depth+1);
                String curname = fields[i].getName();
                reflect2(subpanel, curfield, curclass, curname);
            }
        }
    }
    
}
