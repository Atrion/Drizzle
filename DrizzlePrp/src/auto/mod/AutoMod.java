/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package auto.mod;

import java.io.File;
import java.util.Vector;
import java.util.HashMap;
import shared.m;
import uru.moulprp.prpfile;
import uru.moulprp.PrpRootObject;
import uru.moulprp.Typeid;
import uru.moulprp.Uruobjectref;
import uru.moulprp.Urustring;
import uru.moulprp.x00A2Pythonfilemod.Pythonlisting;
import uru.moulprp.Bstr;
import uru.moulprp.x0001Sceneobject;
import uru.moulprp.Pageid;
import uru.moulprp.Pagetype;
import uru.moulprp.Uruobjectdesc;
import uru.moulprp.Transmatrix;
import prpdistiller.distiller;
import shared.Flt;
import shared.Vertex;


public class AutoMod
{
    public static void SimplicityAutoMod(String infolder, String outfolder, String filename)
    {
        AutoMod(infolder, outfolder, filename, null, true);
    }
    public static void AutoMod(String infolder, String outfolder, String filename, String cleanpotsfolder, boolean useProfiles)
    {
        String infile = infolder+"/dat/"+filename;
        File infile2 = new File(infile);
        //String filename = infile2.getName();
        AutoModInfo args = new AutoModInfo();
        args.filename = filename;
        args.infolder = infolder;
        args.cleanpotsfolder = cleanpotsfolder;
        args.useProfiles = useProfiles;
        args.outfolder = outfolder;

        //this one is still good, just disabling temporarily
        if(filename.equals("Personal_District_psnlMYSTII.prp"))
        {
            prpfile prp = prpfile.createFromFile(infile, true);
            AutoMod_Relto.ModRelto_AddBookCovers(prp);
            AutoMod_Relto.ModRelto_FixPineTrees(prp);
            prp.saveAsFile(outfolder+"/dat/"+filename);
        }
        if(filename.equals("city_District_kdshgalleryDustAdditions.prp"))
        {
            AutoMod_Moul_Others.DustKadishGalleryAdditions(args);
        }
        if(filename.equals("city_District_guildhallDustAdditions.prp"))
        {
            AutoMod_Moul_Others.DustGuildhall(args);
        }
        if(filename.equals("Teledahn_District_tldnDustAdditions.prp"))
        {
            //DustAdditionsTeledahn(args);
            AutoMod_Moul_Sparklies.DustAdditionsGenericCalendarStar(args,"Teledahn","tldn",11,80,"tldnSlaveCave",9);
        }
        if(filename.equals("Personal02_District_philDustAdditions.prp"))
        {
            AutoMod_Moul_Sparklies.DustAdditionsPersonal02(args);
        }
        if(filename.equals("GreatZero_District_grtzDustAdditions.prp"))
        {
            //this.DustAdditionsGenericCalendarStar(args, filename, filename, sequencePrefix, additionsPagenum, filename, calendarStarNum);
            AutoMod_Moul_Sparklies.DustAdditionsGenericCalendarStar(args,"GreatZero","grtz",39,80,"GreatZeroInterior",11);
        }
        if(filename.equals("Myst_District_mystDustAdditions.prp"))
        {
            AutoMod_Moul_Sparklies.DustAdditionsGenericCalendarStar(args,"Myst","myst",28,80,"Fireplace",12);
        }
        if(filename.equals("Garrison_District_grsnDustAdditions.prp"))
        {
            AutoMod_Moul_Sparklies.DustAdditionsGenericCalendarStar(args,"Garrison","grsn",2,120,"grsnTrainingCenterHalls",1);
        }
        if(filename.equals("Garrison_District_grsnDustAdditions2.prp"))
        {
            AutoMod_Moul_Sparklies.DustAdditionsGenericCalendarStar(args,"Garrison","grsn",2,121,"grsnPrison",4,"2","grsnPrison");
        }
        if(filename.equals("Kadish_District_kdshDustAdditions.prp"))
        {
            AutoMod_Moul_Sparklies.DustAdditionsGenericCalendarStar(args,"Kadish","kdsh",22,80,"kdshPillars",2);
        }
        if(filename.equals("Gira_District_giraDustAdditions.prp"))
        {
            AutoMod_Moul_Sparklies.DustAdditionsGenericCalendarStar(args,"Gira","gira",24,80,"giraCanyon",3);
        }
        if(filename.equals("Descent_District_dsntDustAdditions.prp"))
        {
            //AutoMod_Moul_Sparklies.DustAdditionsGenericCalendarStar(args,"Descent","dsnt",21,80,"TreasureBookShaft",5,"","dsntTreasureBookShaft");
            AutoMod_Moul_Others.DustDescentAdditions(args);
        }
        if(filename.equals("Cleft_District_clftDustAdditions2.prp"))
        {
            AutoMod_Moul_Sparklies.DustCleftAdditions2(args);
        }
        if(filename.equals("Cleft_District_clftDustAdditions.prp"))
        {
            AutoMod_Moul_Sparklies.DustCleftAdditions(args);
        }
        if(filename.equals("Personal_District_psnlDustAdditions.prp"))
        {
            AutoMod_Moul_Sparklies.DustReltoAdditions(args);
        }
        if(filename.equals("AhnySphere02_District_ahny2DustAdditions.prp"))
        {
            AutoMod_Moul_Sparklies.DustAhnySphere02Additions(args);
        }
        if(filename.equals("Ercana_District_ercaDustAdditions.prp"))
        {
            AutoMod_Moul_Sparklies.DustErcanaAdditions(args);
        }

        if(filename.equals("Nexus_District_nxusBookMachine.prp"))
        {
            prpfile prp = prpfile.createFromFile(infile, true);
            AutoMod_NexusImages.DustinModNexusBookMachine(prp);
            prp.saveAsFile(outfolder+"/dat/"+filename);
        }
        if(filename.equals("GUI_District_KIBlackBar.prp"))
        {
            prpfile prp = prpfile.createFromFile(infile, true);
            AutoMod_NexusImages.DustinModKIBlackBar(prp);
            prp.saveAsFile(outfolder+"/dat/"+filename);
        }
        if(filename.equals("AhnySphere01_District_Sphere01.prp"))
        {
            prpfile prp = prpfile.createFromFile(infile, true);
            AutoMod_TranslateAge.DustinModAhnySphere01(prp);
            prp.saveAsFile(outfolder+"/dat/"+filename);
        }
        if(filename.equals("AhnySphere01_District_MaintRoom01.prp"))
        {
            prpfile prp = prpfile.createFromFile(infile, true);
            AutoMod_TranslateAge.DustinModAhnyMaint01(prp);
            prp.saveAsFile(outfolder+"/dat/"+filename);
        }
        if(filename.equals("AhnySphere01_District_Sphere01OutBuildingInterior.prp"))
        {
            prpfile prp = prpfile.createFromFile(infile, true);
            AutoMod_TranslateAge.DustinModAhnyOutBuilding(prp);
            prp.saveAsFile(outfolder+"/dat/"+filename);
        }
        if(filename.equals("Neighborhood_District_nb01Ayhoheek5Man1Dead.prp"))
        {
            AutoMod_Moul_Others.DustHeek1(args);
        }
        if(filename.equals("Neighborhood_District_nb01Ayhoheek5Man1State.prp"))
        {
            AutoMod_Moul_Others.DustHeek2(args);
        }


        if(!useProfiles) m.status("Done AutoMod.");
        
    }
    public static final String[] SimplicityAutoModMoulFiles = new String[]{
            "Teledahn_District_tldnDustAdditions.prp",
            "Personal02_District_philDustAdditions.prp",
            "GreatZero_District_grtzDustAdditions.prp",
            "Myst_District_mystDustAdditions.prp",
            "Garrison_District_grsnDustAdditions.prp",
            "Garrison_District_grsnDustAdditions2.prp",
            "Kadish_District_kdshDustAdditions.prp",
            "Gira_District_giraDustAdditions.prp",
            "Descent_District_dsntDustAdditions.prp",
            "Cleft_District_clftDustAdditions2.prp",
            "Cleft_District_clftDustAdditions.prp",
            "AhnySphere02_District_ahny2DustAdditions.prp",
            "Ercana_District_ercaDustAdditions.prp",
            "Personal_District_psnlDustAdditions.prp",
            //Drizzle22:
            "city_District_guildhallDustAdditions.prp",
            "Neighborhood_District_nb01Ayhoheek5Man1Dead.prp",
            "Neighborhood_District_nb01Ayhoheek5Man1State.prp",

        };
    public static void CreateAllProfiles(String infolder, String outfolder, String cleanpotsfolder)
    {
        for(String file: SimplicityAutoModMoulFiles)
        {
            AutoMod(infolder, outfolder, file, cleanpotsfolder, false);
        }
    }
    public static class AutoModInfo
    {
        String infolder;
        String outfolder;
        String filename;
        String cleanpotsfolder;
        boolean useProfiles;
    }


}
