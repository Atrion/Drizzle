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
import shared.FileUtils;
import java.io.File;
import uru.Bytestream;
/**
 *
 * @author user
 */
public class prpfile
{
    public PrpHeader header;
    public Vector<PrpRootObject> extraobjects;
    public PrpRootObject[] objects;
    public PrpObjectIndex objectindex;
    
    public String filename;
    
    public prpfile()
    {
        extraobjects = new Vector<PrpRootObject>();
    }
    public void addObject(PrpRootObject obj)
    {
        extraobjects.add(obj);
        this.mergeExtras();
    }
    /*public PrpRootObject readSingleObject(String name, Typeid type, String filename)
    {
        for(PrpObjectIndex.ObjectindexObjecttype oiot: objectindex.types)
        {
            if(oiot.type==type)
            {
                for(PrpObjectIndex.ObjectindexObjecttypeObjectdesc oiotod: oiot.descs)
                {
                    if(oiotod.desc.objectname.toString().equals(name))
                    {
                        context c = context.createFromBytestream(Bytestream.)
                        oiotod.offset;
                    }
                }
            }
        }
    }*/
    public boolean contains(Uruobjectdesc desc)
    {
        PrpRootObject result = this.findObjectWithDesc(desc);
        return (result!=null);
    }
    public boolean contains(String name, Typeid type)
    {
        Uruobjectdesc desc = findDescInIndex(name,type);
        return (desc!=null);
    }
    public Uruobjectdesc findDescInIndex(String name, Typeid type)
    {
        for(PrpObjectIndex.ObjectindexObjecttype oiot: objectindex.types)
        {
            if(oiot.type==type)
            {
                for(PrpObjectIndex.ObjectindexObjecttypeObjectdesc oiotod: oiot.descs)
                {
                    m.msg(oiotod.desc.toString());
                    if(oiotod.desc.objectname.toString().equals(name))
                    {
                        return oiotod.desc;
                    }
                }
            }
        }
        return null;
    }
    public static prpfile readHeaderAndIndexFromFile(String filename)
    {
        prpfile result = new prpfile();
        //read file
        byte[] filedata = shared.FileUtils.ReadFile(filename);
        File f = new File(filename);
        context c = context.createFromBytestream(new Bytestream(filedata));
        c.curFile = filename;
        result.header = new PrpHeader(c);
        //result.objectindex = new PrpObjectIndex(c.Fork(new Bytestream(c.in,result.header.offsetToObjectIndex)));
        result.objectindex = new PrpObjectIndex(c.Fork(c.in.Fork(result.header.offsetToObjectIndex)));
        result.filename = filename;
        
        return result;
    }
    public void removeObject(Typeid type, String name)
    {
        for(PrpRootObject obj: objects)
        {
            if(obj.header.desc.objecttype==type && obj.header.desc.objectname.toString().equals(name))
            {
                obj.tagDeleted = true;
                return;
            }
        }
        m.msg("Could not find object to remove.");
    }
    public void orderObjects()
    {
        java.util.Arrays.sort(objects);
        //java.util.Collections.sort(objects);
    }
    public void mergeExtras()
    {
        int newsize = objects.length + extraobjects.size();
        PrpRootObject[] newobjects = new PrpRootObject[newsize];
        
        for(int i=0;i<objects.length;i++)
        {
            newobjects[i] = objects[i];
        }
        for(int i=0;i<extraobjects.size();i++)
        {
            newobjects[objects.length+i] = extraobjects.get(i);
        }
        
        extraobjects.clear();
        objects = newobjects;
    }
    /*public PrpRootObject[] getObjects()
    {
        int size = objects.size();
        PrpRootObject[] result = new PrpRootObject[size];
        for(int i=0;i<size;i++)
        {
            result[i] = objects.get(i);
        }
        return result;
    }*/
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
        result.extraobjects = new Vector<PrpRootObject>();
        /*result.objects = new Vector<PrpRootObject>();
        for(PrpRootObject obj: objs)
        {
            result.objects.add(obj);
        }*/
        result.header = PrpHeader.createFromInfo(agename, pageid, pagetype, pagename);
        
        //these don't seem to be needed for compilation.  We may need to regenerate the ObjectIndex if we want to merge objects in and parse them.
        //result.objectindex = PrpObjectIndex.
        //result.filename
        
        return result;
    }
    public void addScenenode()
    {
        uru.moulprp.x0000Scenenode sn = uru.moulprp.x0000Scenenode.createDefault();

        String nodename = this.header.agename.toString() + "_" + this.header.pagename.toString();
        Uruobjectdesc sndesc = Uruobjectdesc.createDefaultWithTypeNamePagePagetype(Typeid.plSceneNode, nodename, this.header.pageid, this.header.pagetype);
        PrpRootObject snro = PrpRootObject.createFromDescAndObject(sndesc, sn);
        this.addObject(snro);
    }
    public static prpfile create(String agename, String pagename, Pageid pageid, Pagetype pagetype)
    {
        //create the prpfile
        PrpRootObject[] objects = new PrpRootObject[]{ };
        prpfile prp = prpfile.createFromObjectsAndInfo(objects, agename, pagename, pageid, pagetype);
        return prp;
    }
    public static prpfile createFromFile(String filename, boolean readRaw)
    {
        File f = new File(filename);
        return createFromFile(f,readRaw);
    }
    public static prpfile createFromFile(File f, boolean readRaw)
    {
        //read file
        byte[] filedata = shared.FileUtils.ReadFile(f);
        context c = context.createFromBytestream(new Bytestream(filedata));
        c.curFile = f.getAbsolutePath();
        prpfile prp = uru.moulprp.prpprocess.ProcessAllObjects(c,readRaw); //read raw
        prp.filename = f.getAbsolutePath();
        
        return prp;
    }
    public void saveAsFile(String filename)
    {
        this.mergeExtras();
        shared.IBytedeque result = this.saveAsBytes();
        //FileUtils.WriteFile(filename, result);
        result.writeAllBytesToFile(filename);
        
    }
    public shared.IBytedeque saveAsBytes()
    {
        //use the decider that always returns true by default.
        /*class compileDecider implements uru.moulprp.prputils.Compiler.Decider{
            public boolean isObjectToBeIncluded(Uruobjectdesc desc){
                return true;
            }
        }*/
        return saveAsBytes(uru.moulprp.prputils.Compiler.getDefaultDecider());
    }
    public shared.IBytedeque saveAsBytes(Decider decider)
    {
        mergeExtras();
        orderObjects();
        shared.IBytedeque result = prputils.Compiler.RecompilePrp(this, decider);
        return result;
    }
    public PrpRootObject findObject(String name, Typeid type)
    {
        for(PrpRootObject obj: objects)
        {
            /*if(obj==null||obj.header==null||obj.header.desc==null||obj.header.objecttype==null||obj.header.desc.objectname==null||obj.header.desc.objecttype==null)
            {
                int dummy=0;
            }*/
            if(obj!=null &&obj.header.desc.objectname.toString().equals(name) && obj.header.desc.objecttype.equals(type))
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
        PrpRootObject result = findObjectWithDesc(ref.xdesc);
        return result;
    }
    public PrpRootObject findObjectWithDesc(Uruobjectdesc desc)
    {
        int numobjects = this.objects.length;
        for(int i=0;i<numobjects;i++)
        {
            PrpRootObject curobj = this.objects[i];
            Uruobjectdesc curdesc = curobj.header.desc;
            /*if(curdesc==null || curdesc.objectname==null || curdesc.objectname.toString()==null||desc==null||desc.objectname==null||desc.objectname.toString()==null)
            {
                int dummy=0;
            }*/
            if(curdesc.objectname.toString().equals(desc.objectname.toString())
                    &&(curdesc.objecttype==desc.objecttype)
                    &&(curdesc.pageid.equals(desc.pageid)))
            {
                return curobj;
            }
        }
        return null;
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
