/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auto.inplace;

import uru.moulprp.Typeid;
import uru.moulprp.prpfile;
import java.util.Vector;
import java.util.HashMap;
import uru.moulprp.*;
import shared.m;

public class Inplace
{
    public static void InplaceMod(InplaceFile potsfolder, String relpath)
    {
        InplaceMod(potsfolder, InplaceModInfo.get(relpath));
    }
    public static void InplaceMod(InplaceFile potsfolder, InplaceModInfo info)
    {
        InplaceFile f = potsfolder.File(info.relpath);
        byte[] data = f.ReadAsBytes();
        prpfile prp = prpfile.createFromBytes(data, true);

        for(String modname: info.modnames)
        {
            if(modname.equals("RemoveFence"))
            {
                //Dustin's removal of colliders for the Cleft fence so that avatars can jump over it (and eventually jump into the volcano.)
                Inplace_Cleft.RemoveFence(info,prp);
            }
            else if(modname.equals("FixKadishDoors"))
            {
                //Fixes the Kadish Gallery doors in the city, so that they can be opened!
                Inplace_city.FixKadishDoors(info,prp);
            }
            else if(modname.equals("RemoveRelevanceRegions"))
            {
                //A'moaca and Ashtar's fix for Relevance regions in the city that otherwise makes avatars invisible or some such thing.
                PrpRootObject[] relregs = prp.FindAllObjectsOfType(Typeid.plRelevanceRegion);
                for(PrpRootObject relreg: relregs)
                {
                    uru.moulprp.PlRelevanceRegion rr = relreg.castTo();
                    Uruobjectref r1 = rr.ref;
                    Uruobjectref r2 = rr.parent.ref;
                    prp.markObjectDeleted(relreg.getref(),false);
                    prp.markObjectDeleted(r1,false);
                    prp.markObjectDeleted(r2,false);
                    int i=0;
                }
            }
            else if(modname.equals("CityBalconyMarkerFix"))
            {
                //A'moaca and Ashtar's fix for the player not being able to interact with the marker on Alcugs because they link in too close.
                Inplace_city.CityBalconyMarkerFix(info, prp);
            }
            else if(modname.equals("CityMuseumDoorFix"))
            {
                //A'moaca and Ashtar's fix for the Museum doors which wouldn't open otherwise online.  (Neither Alcugs nor UU nor MOUL.)
                Inplace_city.CityMuseumDoorFix(info, prp);
            }
            else if(modname.equals("MakeTeledahnIntoKirelBook"))
            {
                //turns the Teledahn linking book by the center spire into a KirelMOUL linking book.
                Inplace_city.MakeTeledahnInfoKirelBook(info, prp);
            }
            else
            {
                m.err("Unable to find modname: "+modname);
            }
        }
        f.SaveFile(prp.saveAsBytes());
        UpdateSumfile(potsfolder, info);
    }

    public static class InplaceModInfo
    {
        public String relpath;
        public String[] modnames;
        public String age;

        private InplaceModInfo(){}
        private static HashMap<String,InplaceModInfo> mods;
        private static HashMap<String,InplaceModInfo> getmods()
        {
            if(mods==null)
            {
                mods = new HashMap();
                addinfo("Cleft","/dat/Cleft_District_Desert.prp", "RemoveFence");
                addinfo("Cleft","/dat/Cleft_District_tmnaDesert.prp", "RemoveFence");
                addinfo("city", "/dat/city_District_palace.prp", "RemoveRelevanceRegions", "CityBalconyMarkerFix", "FixKadishDoors");
                addinfo("city", "/dat/city_District_courtyard.prp", "RemoveRelevanceRegions", "CityMuseumDoorFix", "FixKadishDoors", "MakeTeledahnIntoKirelBook");
                addinfo("city", "/dat/city_District_canyon.prp", "RemoveRelevanceRegions", "FixKadishDoors");
                addinfo("city", "/dat/city_District_cavetjunction.prp", "RemoveRelevanceRegions");
                addinfo("city", "/dat/city_District_ferry.prp", "RemoveRelevanceRegions"); //ferry has RemoveRelevanceRegions and the soccer ball added
                addinfo("city", "/dat/city_District_greatstair.prp", "RemoveRelevanceRegions");
                addinfo("city", "/dat/city_District_KadishGallery.prp", "RemoveRelevanceRegions", "FixKadishDoors"); //also add door fix?
                addinfo("city", "/dat/city_District_KahloPub.prp", "RemoveRelevanceRegions");
                addinfo("city", "/dat/city_District_library.prp", "RemoveRelevanceRegions", "FixKadishDoors");
                addinfo("city", "/dat/city_District_harbor.prp", "FixKadishDoors");
            }
            return mods;
        }
        private static void addinfo(String age, String relpath, String... modnames)
        {
            InplaceModInfo r = new InplaceModInfo();
            r.age = age;
            r.relpath = relpath;
            r.modnames = modnames;
            mods.put(relpath, r);
        }
        public static InplaceModInfo get(String relpath)
        {
            InplaceModInfo r = getmods().get(relpath);
            return r;
        }
    }

    public static void UpdateSumfile(InplaceFile potsfolder, InplaceModInfo info)
    {
        InplaceFile sumfile = potsfolder.File("/dat/"+info.age+".sum");
        if(sumfile.exists())
        {
            byte[] sumdata = uru.moulprp.sumfile.createEmptySumfile().getByteArray();
            sumfile.SaveFile(sumdata);
        }
    }

}
