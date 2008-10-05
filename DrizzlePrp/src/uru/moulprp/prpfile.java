/*
    Drizzle - A general Myst tool.
    Copyright (C) 2008  Dustin Bernard.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/ 

package uru.moulprp;

//import java.util.Vector;
import java.lang.reflect.Field;
import shared.m;
import uru.context;
import shared.Bytes;
import uru.moulprp.Typeid;
import uru.moulprp.prputils.Compiler.Decider;
import java.util.Vector;
/**
 *
 * @author user
 */
public class prpfile
{
    public PrpHeader header;
    //Vector<PrpRootObject> objects;
    public PrpRootObject[] objects;
    public PrpObjectIndex objectindex;
    
    public String filename;
    
    public prpfile(){}
    
    public void orderObjects()
    {
        java.util.Arrays.sort(objects);
        //java.util.Collections.sort(objects);
    }
    
    public static prpfile createFromContext(context c, Typeid[] typesToRead)
    {
        prpfile result = prputils.ProcessAllMoul(c, false, typesToRead);
        return result;
    }
    public static prpfile createFromObjectsAndInfo(Vector<PrpRootObject> objs, String agename, String pagename, Pageid pageid, Pagetype pagetype)
    {
        PrpRootObject[] objs2 = uru.generics.convertVectorToArray(objs, PrpRootObject.class);
        return createFromObjectsAndInfo(objs2,agename,pagename,pageid,pagetype);
    }
    
    public static prpfile createFromObjectsAndInfo(PrpRootObject[] objs, String agename, String pagename, Pageid pageid, Pagetype pagetype)
    {
        prpfile result = new prpfile();
        result.objects = objs;
        result.header = PrpHeader.createFromInfo(agename, pageid, pagetype, pagename);
        
        //these don't seem to be needed for compilation.  We may need to regenerate the ObjectIndex if we want to merge objects in and parse them.
        //result.objectindex = PrpObjectIndex.
        //result.filename
        
        return result;
    }
    public Bytes saveAsBytes()
    {
        //use the decider that always returns true by default.
        class compileDecider implements uru.moulprp.prputils.Compiler.Decider{
            public boolean isObjectToBeIncluded(Uruobjectdesc desc){
                return true;
            }
        }
        return saveAsBytes(new compileDecider());
    }
    public Bytes saveAsBytes(Decider decider)
    {
        orderObjects();
        Bytes result = prputils.Compiler.RecompilePrp(this, decider);
        return result;
    }
    public PrpRootObject findObject(String name, Typeid type)
    {
        for(PrpRootObject obj: objects)
        {
            if(obj.header.desc.objectname.toString().equals(name) && obj.header.desc.objecttype.equals(type))
            {
                return obj;
            }
        }
        return null;
    }
    public PrpRootObject findObjectWithRef(Uruobjectref ref)
    {
        if(!ref.hasref())
        {
            m.warn("Tried to remove object, but the ref given has no desc.");
            return null;
        }
        PrpRootObject result = prputils.findObjectWithDesc(this, ref.xdesc);
        return result;
    }
    public void tagRootObjectAsDeleted(PrpRootObject obj)
    {
        if(obj==null) return;
        /*PrpRootObject[] newobjects = new PrpRootObject[objects.length-1];
        int pos = 0;
        for(int i=0;i<objects.length;i++)
        {
            PrpRootObject curobj = objects[i];
            if(obj!=curobj)
            {
                newobjects[pos] = curobj;
                pos++;
            }
            else
            {
                int dummy=0;
            }
        }
        objects = newobjects;*/
        for(PrpRootObject curobj: objects)
        {
            if(obj==curobj)
            {
                curobj.tagDeleted = true;
            }
        }
    }
    public void tagRootObjectAsDeleted(Uruobjectref ref)
    {
        PrpRootObject obj = findObjectWithRef(ref);
        tagRootObjectAsDeleted(obj);
    }
    
    public PrpRootObject[] FindAllObjectsOfType(Typeid type)
    {
        return prputils.FindAllObjectsOfType(this, type);
    }
    
}
