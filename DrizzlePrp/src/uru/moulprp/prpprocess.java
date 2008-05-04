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

import uru.context;
import shared.readexception;
import uru.Bytestream;
import shared.m;
import java.util.Vector;

public class prpprocess
{
    public static prpfile ProcessAllObjects(context c)
    {
        PrpHeader header = new PrpHeader(c);
        PrpObjectIndex objectindex = new PrpObjectIndex(c.Fork(new Bytestream(c.in,header.offsetToObjectIndex)));
        Vector<PrpRootObject> rootobjects = new Vector<PrpRootObject>();

        int numobjecttypes = objectindex.indexCount;
        for(int i_type=0;i_type<numobjecttypes;i_type++)
        {
            int numObjects = objectindex.types[i_type].objectcount;
            for(int j_obj=0;j_obj<numObjects;j_obj++)
            {
                PrpRootObject ro = ProcessObjectindexObjecttypeObjectdesc( c, objectindex.types[i_type].descs[j_obj] );
                rootobjects.add(ro);
            }
        }
        
        m.msg("Done Processing All Objects.");
        prpfile result = new prpfile();
        result.header = header;
        result.objectindex = objectindex;
        result.objects = uru.generics.convertVectorToArray(rootobjects,PrpRootObject.class);
        return result;
    }
    
    public static void ProcessAllObjectsOfType(context c, Typeid typeid)
    {
        PrpHeader header = new PrpHeader(c);
        PrpObjectIndex objectindex = new PrpObjectIndex(c.Fork(new Bytestream(c.in,header.offsetToObjectIndex)));

        int numobjecttypes = objectindex.indexCount;
        for(int i_type=0;i_type<numobjecttypes;i_type++)
        {
            if(objectindex.types[i_type].type==typeid)
            {
                int numObjects = objectindex.types[i_type].objectcount;
                for(int j_obj=0;j_obj<numObjects;j_obj++)
                {
                    ProcessObjectindexObjecttypeObjectdesc( c, objectindex.types[i_type].descs[j_obj] );
                }
            }
        }
        
        m.msg("Done Processing All Object of Type: "+typeid.toString());
    }
    
    public static PrpRootObject ProcessObjectindexObjecttypeObjectdesc(context c, PrpObjectIndex.ObjectindexObjecttypeObjectdesc d)
    {
        int offset = d.offset;
        int size = d.size;
        Typeid type = d.desc.objecttype;
        
        context stream = c.Fork(new Bytestream(c.in,offset));
        stream.curRootObject = d.desc;
        stream.curRootObjectOffset = offset;
        stream.curRootObjectSize = size;
        stream.curRootObjectEnd = offset+size;
        
        PrpRootObject result = ProcessRootObject(stream,type);
        if(result!=null)
        {
            int shortby = offset+size-stream.in.getAbsoluteOffset();
            if(shortby!=0)
            {
                if(d.desc.objecttype!=Typeid.plHKPhysical)
                    m.msg("Prp: Object was not the expected size. It was off by:"+Integer.toString(shortby));
            }
        }
        
        return result;
    }
    private static PrpRootObject ProcessRootObject(context c, Typeid type)
    {
        PrpRootObject object = null;
        try
        {
            object = new PrpRootObject(c);
        }catch(readexception e){}
        
        if(object==null)
        {
            m.warn("Object was skipped.");
        }
        else
        {
            
        }
        return object;
    }
    
}
