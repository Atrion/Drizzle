/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package automation;

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
            ModReltoBooksDynamicTextMap(prp);
            prp.saveAsFile(outfolder+"/"+filename);
        }
        if(filename.equals("Teledahn_District_tldnDustAdditions.prp"))
        {
            //DustAdditionsTeledahn(args);
            DustAdditionsGenericCalendarStar(args,"Teledahn","tldn",11,80,"tldnSlaveCave",9);
        }
        if(filename.equals("Personal02_District_philDustAdditions.prp"))
        {
            DustAdditionsPersonal02(args);
        }
        if(filename.equals("GreatZero_District_grtzDustAdditions.prp"))
        {
            //this.DustAdditionsGenericCalendarStar(args, filename, filename, sequencePrefix, additionsPagenum, filename, calendarStarNum);
            DustAdditionsGenericCalendarStar(args,"GreatZero","grtz",39,80,"GreatZeroInterior",11);
        }
        if(filename.equals("Myst_District_mystDustAdditions.prp"))
        {
            DustAdditionsGenericCalendarStar(args,"Myst","myst",28,80,"Fireplace",12);
        }
        if(filename.equals("Garrison_District_grsnDustAdditions.prp"))
        {
            DustAdditionsGenericCalendarStar(args,"Garrison","grsn",2,120,"grsnTrainingCenterHalls",1);
        }
        if(filename.equals("Garrison_District_grsnDustAdditions2.prp"))
        {
            DustAdditionsGenericCalendarStar(args,"Garrison","grsn",2,121,"grsnPrison",4,"2","grsnPrison");
        }
        if(filename.equals("Kadish_District_kdshDustAdditions.prp"))
        {
            DustAdditionsGenericCalendarStar(args,"Kadish","kdsh",22,80,"kdshPillars",2);
        }
        if(filename.equals("Gira_District_giraDustAdditions.prp"))
        {
            DustAdditionsGenericCalendarStar(args,"Gira","gira",24,80,"giraCanyon",3);
        }
        if(filename.equals("Descent_District_dsntDustAdditions.prp"))
        {
            DustAdditionsGenericCalendarStar(args,"Descent","dsnt",21,80,"TreasureBookShaft",5,"","dsntTreasureBookShaft");
        }
        if(filename.equals("Cleft_District_clftDustAdditions2.prp"))
        {
            prpdistiller.distiller.distillInfo info = new prpdistiller.distiller.distillInfo();

            prpfile dest = prpfile.create("Cleft", "clftDustAdditions2", Pageid.createFromPrefixPagenum(7, 121), Pagetype.createWithType(0));
            dest.addScenenode();

            Vector<prpfile> sources = new Vector();
            prpfile prp; prpfile prp2;
            sources.add(prpfile.createFromFile(infolder+"/dat/Cleft_District_Textures.prp", true));
            //sources.add(prp = prpfile.createFromFile(infolder+"/dat/Cleft_District_Desert.prp", true));
            sources.add(prp2 = prpfile.createFromFile(infolder+"/dat/Cleft_District_BookRoom.prp", true));

            if(!useProfiles)
            {
                Vector<prpfile> altdests = new Vector();
                altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/Cleft_District_Textures.prp", true));
                altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/Cleft_District_BookRoom.prp", true));
                info.altdests = altdests;

                Vector<Uruobjectdesc> list = new Vector();
                for(String sobj: new String[]{"RgnYeeshaPage01","YeeshaPage01","YeeshaPage01Decal",})list.add(prp2.findObject(sobj,Typeid.plSceneObject).header.desc);

                info.list = list;

                info.createObjectList = true; info.outputFileForObjectList = outfolder+"/dat/"+filename+".profile";
            }
            else
            {
                info.usePreexistingObjectList = true; info.objectListResourceName = "/files/profiles/"+filename+".profile";
            }

            info.dest = dest;
            info.sourceprpfiles = sources;
            info.trimDrawableSpans = true;
            info.runtests = false;
            info.forcedDuplicateInclusions = new prpdistiller.distiller.includeDuplicateDecider() {

                public boolean include(Uruobjectdesc desc) {
                    String name = desc.objectname.toString();
                    Typeid type = desc.objecttype;
                    //if(type==Typeid.plPythonFileMod) return true;
                    if(type==Typeid.plViewFaceModifier) return true; //they all have similar names and are small.
                    if(type==Typeid.hsGMaterial || type==Typeid.plLayer || type==Typeid.plLayerAnimation) return true;
                    return false;
                }
            };
            prpdistiller.distiller.distillList(info);

            //set this page to only be visible when the relto book isn't.
            for(String sobj: new String[]{"RgnYeeshaPage01","YeeshaPage01","YeeshaPage01Decal",})
            {
                x0001Sceneobject so = dest.findObject(sobj,Typeid.plSceneObject).castTo();
                //AddXAgeSDLBoolShowHideToObject(dest, so, "clftYeeshaBookVis", true);
                //AddXAgeSDLBoolShowHideToObject(dest, so, "clftZandiVis", true);
                AddXDustChronicleShowHideToObject(dest, so, "CleftSolved","yes", false);
            }


            //save the dest file.
            dest.saveAsFile(outfolder+"/dat/"+filename);

        }
        if(filename.equals("Cleft_District_clftDustAdditions.prp"))
        {
            prpdistiller.distiller.distillInfo info = new prpdistiller.distiller.distillInfo();

            prpfile dest = prpfile.create("Cleft", "clftDustAdditions", Pageid.createFromPrefixPagenum(7, 120), Pagetype.createWithType(0));
            dest.addScenenode();

            Vector<prpfile> sources = new Vector();
            prpfile prp; prpfile prp2;
            sources.add(prpfile.createFromFile(infolder+"/dat/Cleft_District_Textures.prp", true));
            sources.add(prp = prpfile.createFromFile(infolder+"/dat/Cleft_District_Desert.prp", true));
            //sources.add(prp2 = prpfile.createFromFile(infolder+"/dat/Cleft_District_BookRoom.prp", true));

            if(!useProfiles)
            {
                Vector<prpfile> altdests = new Vector();
                altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/Cleft_District_Textures.prp", true));
                altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/Cleft_District_Desert.prp", true));
                //altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/Cleft_District_BookRoom.prp", true));
                info.altdests = altdests;

                Vector<Uruobjectdesc> list = new Vector();
                for(String sobj: new String[]{"campfire","campfire01","CampfireDecal","CampfireRock54",})list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                for(String sobj: new String[]{"RgnYeeshaPage20","RgnYeeshaPageBirds","YeeshaPage20","YeeshaPageBirds","YeeshaPageBirdsDecal","YeeshaPageIcon20"})list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                //for(String sobj: new String[]{"ZandiChair29","ZandiChair36","ZandiChair42",})list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                for(String sobj: prp.findAllSceneobjectsThatStartWith("ZandiChair"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                for(String sobj: new String[]{"Box01","Object01","Torus01",})list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc); //these are colliders around the fireplace (at least some of them, but probably all of them.)
                //for(String sobj: new String[]{"RgnYeeshaPage01","YeeshaPage01","YeeshaPage01Decal",})list.add(prp2.findObject(sobj,Typeid.plSceneObject).header.desc);

                info.list = list;

                info.createObjectList = true; info.outputFileForObjectList = outfolder+"/dat/"+filename+".profile";
            }
            else
            {
                info.usePreexistingObjectList = true; info.objectListResourceName = "/files/profiles/"+filename+".profile";
            }

            info.dest = dest;
            info.sourceprpfiles = sources;
            info.trimDrawableSpans = true;
            info.runtests = false;
            info.forcedDuplicateInclusions = new prpdistiller.distiller.includeDuplicateDecider() {

                public boolean include(Uruobjectdesc desc) {
                    String name = desc.objectname.toString();
                    Typeid type = desc.objecttype;
                    //if(type==Typeid.plPythonFileMod) return true;
                    if(type==Typeid.plViewFaceModifier) return true; //they all have similar names and are small.
                    if(type==Typeid.hsGMaterial || type==Typeid.plLayer || type==Typeid.plLayerAnimation) return true;
                    return false;
                }
            };
            prpdistiller.distiller.distillList(info);

            //switch calendar yeesha page number from 20 to 26:
            try{
            uru.moulprp.x00A2Pythonfilemod pfm = dest.findObject("cPythYeeshaPage20_0", Typeid.plPythonFileMod).castTo();
            for(int i=0;i<pfm.listcount;i++)
            {
                if(i%2==1)
                {
                    if(pfm.listings.get(i).xInteger!=20)
                    {
                        m.err("Unexpected.");
                    }
                    pfm.listings.get(i).xInteger = 26; //switch calendar page from 20 to 26.
                }
            }
            uru.moulprp.x00A2Pythonfilemod pfm2 = dest.findObject("cPythYeeshaPage20_1", Typeid.plPythonFileMod).castTo();
            for(int i=0;i<pfm2.listcount;i++)
            {
                if(i%2==1)
                {
                    if(pfm2.listings.get(i).xInteger!=20)
                    {
                        m.err("Unexpected.");
                    }
                    pfm2.listings.get(i).xInteger = 26; //switch calendar page from 20 to 26.
                }
            }
            }catch(Exception e){m.err("exception");}


            //save the dest file.
            dest.saveAsFile(outfolder+"/dat/"+filename);

        }

        if(filename.equals("Personal_District_psnlDustAdditions.prp"))
        {
            prpdistiller.distiller.distillInfo info = new prpdistiller.distiller.distillInfo();

            prpfile dest = prpfile.create("Personal", "psnlDustAdditions", Pageid.createFromPrefixPagenum(13, 80), Pagetype.createWithType(0));
            dest.addScenenode();

            Vector<prpfile> sources = new Vector();
            prpfile prp;
            sources.add(prpfile.createFromFile(infolder+"/dat/Personal_District_Textures.prp", true));
            //sources.add(prpfile.createFromFile(infolder+"/dat/Personal_District_BuiltIn.prp", true));
            sources.add(prp = prpfile.createFromFile(infolder+"/dat/Personal_District_psnlMYSTII.prp", true));
            //sources.add(prpfile.createFromFile("C:\\Documents and Settings\\user\\Desktop\\output/dat/PersonalMOUL_District_Textures.prp", true));
            //sources.add(prpfile.createFromFile("C:\\Documents and Settings\\user\\Desktop\\output/dat/PersonalMOUL_District_BuiltIn.prp", true));
            //sources.add(prp = prpfile.createFromFile("C:\\Documents and Settings\\user\\Desktop\\output/dat/PersonalMOUL_District_psnlMYSTII.prp", true));

            if(!useProfiles)
            {
                Vector<prpfile> altdests = new Vector();
                altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/Personal_District_Textures.prp", true));
                //altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/Personal_District_BuiltIn.prp", true));
                altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/Personal_District_psnlMYSTII.prp", true));
                info.altdests = altdests;

                Vector<Uruobjectdesc> list = new Vector();

                //storm:
                for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythYeeshaPage24 - Storm_6")) list.add(prp.findObject(sobj, Typeid.plSceneObject).header.desc);

                //bridge/calendar:  The 3 Buttes with a 4 are the big calendar island; the 3 with the 6 are the little intermediate island which is also in pots.
                for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythYeeshaPage20- Calendar_1"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                for(String sobj: new String[]{"Butte004Top01","Butte04btm01","Butte04topDecal01","Butte006Top01","Butte06btm","Butte06topDecal"})list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);

                //bench:
                for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythYeeshaPage15 - Bench_0"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythYeeshaPage19 - Birds_1"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythYeeshaPage21 - MapleTrees_11"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythYeeshaPage21 - PineTrees_0"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythYeeshaPage22 - Grass_0"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythYeeshaPage23 - ErcanaPlants_7"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);

                //cleft pole
                for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythYeeshaPage25 - CleftPole_0"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                list.add(prp.findObject("AgeSDLHook", Typeid.plSceneObject).header.desc); //collider around wedges.

                //wedges
                for(String sobj: prp.findAllSceneobjectsThatStartWith("Wedge"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                list.add(prp.findObject("Cylinder02", Typeid.plSceneObject).header.desc); //collider around wedges.

                info.list = list;

                info.createObjectList = true; info.outputFileForObjectList = outfolder+"/dat/"+filename+".profile";
            }
            else
            {
                info.usePreexistingObjectList = true; info.objectListResourceName = "/files/profiles/"+filename+".profile";
            }

            info.dest = dest;
            info.sourceprpfiles = sources;
            info.trimDrawableSpans = true;
            info.runtests = false;
            info.forcedDuplicateInclusions = new prpdistiller.distiller.includeDuplicateDecider() {

                public boolean include(Uruobjectdesc desc) {
                    String name = desc.objectname.toString();
                    Typeid type = desc.objecttype;
                    /*if(desc.objectname.toString().startsWith("Map #7370"))
                    {
                        return true;
                    }*/
                    //if(true) return true;
                    //if(type==Typeid.plPythonFileMod) return true;
                    if(type==Typeid.plViewFaceModifier) return true; //they all have similar names and are small.
                    if(type==Typeid.hsGMaterial || type==Typeid.plLayer || type==Typeid.plLayerAnimation) return true;
                    if(name.startsWith("Butte04")||name.startsWith("Butte004")||name.startsWith("Butte06")||name.startsWith("Butte006")) return true;
                    if(name.equals("AgeSDLHook")) return true;
                    //if(name.startsWith("Butte")) return true;
                    return false;
                }
            };
            prpdistiller.distiller.distillList(info);

            //this changes the draw order so that the fog doesn't override these, but it may mess up other things.  In practice, I can't see a problem on Relto, so we don't have to distill to another drawablespans or use a different prp file to keep them separate.
            for(String sobj: new String[]{"Butte04btm01","Butte06btm",})
            {
                try{
                uru.moulprp.x0016DrawInterface di = dest.findObject(sobj, Typeid.plDrawInterface).castTo();
                for(uru.moulprp.x0016DrawInterface.SubsetGroupRef sgr: di.subsetgroups)
                {
                    if(sgr.subsetgroupindex!=-1)
                    {
                        uru.moulprp.PlDrawableSpans spans = dest.findObject(sgr.span.xdesc.objectname.toString(), sgr.span.xdesc.objecttype).castTo();
                        spans.criteria = 0x20000007; //the fog is 20000008
                    }
                }
                }catch(Exception e){m.err("exception");}
            }

            //hack to make flickery objects visible by adding a pythonfilemod and a python file to force them as shown.
            //fixed by fixing plSpaceTrees instead.
            /*for(String sobj: new String[]{"Butte004Top01","Butte04btm01","Butte04topDecal01","Butte006Top01","Butte06btm","Butte06topDecal","WedgeRingsBase"})
            {
                try{
                x0001Sceneobject so = prp.findObject(sobj,Typeid.plSceneObject).castTo();
                AddXDustShowToObject(dest, so);
                }catch(Exception e){m.err("exception");}
            }*/

            //change cleft pole python page:
            try{
            uru.moulprp.x00A2Pythonfilemod pfm = dest.findObject("cPythBahroPoles", Typeid.plPythonFileMod).castTo();
            pfm.pyfile = Urustring.createFromString("psnlBahroPolesMOUL");
            /*Vector<uru.moulprp.x00A2Pythonfilemod.Pythonlisting> listings = new Vector();
            for(int i=0;i<pfm.listcount;i++)
            {
                uru.moulprp.x00A2Pythonfilemod.Pythonlisting listing = pfm.listings.get(i);
                if(listing.index >= 48)
                {
                    listings.add(listing);
                }
            }
            pfm.clearListings();
            for(uru.moulprp.x00A2Pythonfilemod.Pythonlisting listing: listings)
            {
                //pfm.addListing(listing);
            }*/
            }catch(Exception e){m.err("exception");}

            /*try{
            uru.moulprp.x00A2Pythonfilemod pfm = dest.findObject("cPythClftLinkBookGUI", Typeid.plPythonFileMod).castTo();
            for(int i=0;i<pfm.listcount;i++)
            {
                if(pfm.listings.get(i).index==4)
                {
                    pfm.listings.get(i).xString = Bstr.createFromString("Cleft");
                }
            }
            }catch(Exception e){m.err("exception");}*/

            //switch calendar yeesha page number from 20 to 26:
            try{
            uru.moulprp.x00A2Pythonfilemod pfm = dest.findObject("cPythYeeshaPage20- Calendar_1", Typeid.plPythonFileMod).castTo();
            for(int i=0;i<pfm.listcount;i++)
            {
                if(i%2==0)
                {
                    if(pfm.listings.get(i).xInteger!=20)
                    {
                        m.err("Unexpected.");
                    }
                    pfm.listings.get(i).xInteger = 26; //switch calendar page from 20 to 26.
                }
            }
            }catch(Exception e){m.err("exception");}
            //switch bench yeesha page number from 15 to 18:
            try{
            uru.moulprp.x00A2Pythonfilemod pfm2 = dest.findObject("cPythYeeshaPage15 - Bench_0", Typeid.plPythonFileMod).castTo();
            for(int i=0;i<pfm2.listcount;i++)
            {
                if(i%2==0)
                {
                    if(pfm2.listings.get(i).xInteger!=15)
                    {
                        m.err("Unexpected.");
                    }
                    pfm2.listings.get(i).xInteger = 18; //switch calendar page from 20 to 26.
                }
            }
            }catch(Exception e){m.err("exception");}
            //switch activation states for cleft pole from "0,2,4" to "1,3"
            try{
            uru.moulprp.x00A2Pythonfilemod pfm3 = dest.findObject("cPythYeeshaPage25 - CleftPole_0", Typeid.plPythonFileMod).castTo();
            for(int i=0;i<pfm3.listcount;i++)
            {
                if(i%2==1)
                {
                    if(!pfm3.listings.get(i).xString.toString().equals("0,2,4"))
                    {
                        m.err("Unexpected.");
                    }
                    pfm3.listings.get(i).xString = Bstr.createFromString("1,3");
                }
            }
            }catch(Exception e){m.err("exception");}

            //take away the mass from the CalendarStoneProxyXX
            /*try{
                for(int i=1;i<=12;i++)
                {
                    String prox = "CalendarStoneProxy"+(i<10?"0":"")+Integer.toString(i);
                    uru.moulprp.PlHKPhysical phys = dest.findObject(prox, Typeid.plHKPhysical).castTo();
                    phys.convertPXtoHK();
                    phys.havok.mass = Flt.zero();
                }
            }catch(Exception e){m.err("exception");}*/

            //save the dest file.
            dest.saveAsFile(outfolder+"/dat/"+filename);

        }
        if(filename.equals("AhnySphere02_District_ahny2DustAdditions.prp"))
        {
            prpdistiller.distiller.distillInfo info = new prpdistiller.distiller.distillInfo();

            prpfile dest = prpfile.create("AhnySphere02", "ahny2DustAdditions", Pageid.createFromPrefixPagenum(26, 80), Pagetype.createWithType(0));
            dest.addScenenode();

            Vector<prpfile> sources = new Vector();
            prpfile prp;
            sources.add(prpfile.createFromFile(infolder+"/dat/Ahnonay_District_Textures.prp", true));
            sources.add(prpfile.createFromFile(infolder+"/dat/Ahnonay_District_BuiltIn.prp", true));
            sources.add(prp = prpfile.createFromFile(infolder+"/dat/Ahnonay_District_MaintRoom02.prp", true));

            if(!useProfiles)
            {
                Vector<prpfile> altdests = new Vector();
                altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/AhnySphere02_District_Textures.prp", true));
                altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/AhnySphere02_District_BuiltIn.prp", true));
                altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/AhnySphere02_District_MaintRoom02.prp", true));
                altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/AhnySphere02_District_Sphere02.prp", true));
                info.altdests = altdests;

                Vector<Uruobjectdesc> list = new Vector();
                list.add(prp.findObject("YeeshaPage24", Typeid.plSceneObject).header.desc);
                list.add(prp.findObject("YeeshaPage24Decal", Typeid.plSceneObject).header.desc);
                list.add(prp.findObject("RgnYeeshaPageStorm", Typeid.plSceneObject).header.desc);
                //list.add(prp.findObject("Dummy01Relocator", Typeid.plSceneObject).header.desc); //do we need this one?
                info.list = list;

                info.createObjectList = true; info.outputFileForObjectList = outfolder+"/dat/"+filename+".profile";
            }
            else
            {
                info.usePreexistingObjectList = true; info.objectListResourceName = "/files/profiles/"+filename+".profile";
            }

            info.dest = dest;
            info.sourceprpfiles = sources;
            info.trimDrawableSpans = true;
            info.runtests = false;
            info.forcedDuplicateInclusions = new distiller.includeDuplicateDecider() {
                public boolean include(Uruobjectdesc desc)
                {
                    Typeid tid = desc.objecttype;
                    String name = desc.objectname.toString();
                    //include these two because they used the same name but changed the page texture for moul.
                    if(tid==Typeid.hsGMaterial && name.equals("Material #10969")) return true;
                    if(tid==Typeid.plLayer && name.equals("Map #7392")) return true;
                    return false;
                }
            };
            prpdistiller.distiller.distillList(info);




            //translate the page since the entire sphere was shifted over for moul to accomodate all the sphere's in one age. Also, translate 3.5 units to the right so it is not in the same place as the other.
            double yoff = 3.5; //move the page a little to the right.
            double x = 43.0636-32.8057; double y = 548.7101-665.3181-yoff; double z = 2.5576-0.3330;
            uru.moulprp.x0015CoordinateInterface ci = dest.findObject("YeeshaPage24", Typeid.plCoordinateInterface).castTo();
            ci.translate(x,y,z, true);
            ci = dest.findObject("YeeshaPage24Decal", Typeid.plCoordinateInterface).castTo();
            ci.translate(x,y,z, true);
            ci = dest.findObject("VisRegionMaintRm02", Typeid.plCoordinateInterface).castTo();
            ci.translate(x,y,z, true);
            //uru.moulprp.x0016DrawInterface di = dest.findObject("YeeshaPage24", Typeid.plDrawInterface).castTo();
            //di.visregioncount = 0; di.visibleregion = new Uruobjectref[]{};
            //di = dest.findObject("YeeshaPage24Decal", Typeid.plDrawInterface).castTo();
            //di.visregioncount = 0; di.visibleregion = new Uruobjectref[]{};

            //ci = dest.findObject("RgnYeeshaPageStorm", Typeid.plCoordinateInterface).castTo();
            //ci.translate(x, y, z, true);
            uru.moulprp.PlHKPhysical phys = dest.findObject("RgnYeeshaPageStorm", Typeid.plHKPhysical).castTo();
            phys.convertPXtoHK();
            phys.havok.position = Vertex.zero();
            phys.havok.transformVertices(Transmatrix.createFromVector2(x,y,z));

            uru.moulprp.PlHKPhysical phys2 = dest.findObject("YeeshaPage24", Typeid.plHKPhysical).castTo();
            phys2.convertPXtoHK();
            phys2.havok.position = Vertex.zero();
            phys2.havok.transformVertices(Transmatrix.createFromVector2(x,y,z));
            

            //save the dest file.
            dest.saveAsFile(outfolder+"/dat/"+filename);

        }
        if(filename.equals("Ercana_District_ercaDustAdditions.prp"))
        {
            prpdistiller.distiller.distillInfo info = new prpdistiller.distiller.distillInfo();

            prpfile dest = prpfile.create("Ercana", "ercaDustAdditions", Pageid.createFromPrefixPagenum(15, 80), Pagetype.createWithType(0));
            dest.addScenenode();

            Vector<prpfile> sources = new Vector();
            prpfile prp;
            sources.add(prp = prpfile.createFromFile(infolder+"/dat/Ercana_District_Canyon.prp", true));
            sources.add(prpfile.createFromFile(infolder+"/dat/Ercana_District_Textures.prp", true));

            if(!useProfiles)
            {
                Vector<prpfile> altdests = new Vector();
                altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/Ercana_District_Canyon.prp", true));
                altdests.add(prpfile.createFromFile(cleanpotsfolder+"/dat/Ercana_District_Textures.prp", true));
                info.altdests = altdests;

                Vector<Uruobjectdesc> list = new Vector();
                list.add(prp.findObject("YeeshaPageErcaPlants", Typeid.plSceneObject).header.desc);
                list.add(prp.findObject("YeeshaPageErcaPlantsDecal", Typeid.plSceneObject).header.desc);
                list.add(prp.findObject("RgnYeeshaPageErcaPlants", Typeid.plSceneObject).header.desc);


                //list.add(prp.findObject("CalStar07Dtct", Typeid.plSceneObject).header.desc);
                //list.add(prp.findObject("CalendarGlare07", Typeid.plSceneObject).header.desc);
                //list.add(prp.findObject("CalendarStarDecal", Typeid.plSceneObject).header.desc);

                for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythPOTSIcon"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                //list.add(prp.findObject("POTSiconRgn", Typeid.plInterfaceInfoModifier).header.desc);
                //list.add(prp.findObject("cRgnSnsIconLinker_Enter", Typeid.plObjectInVolumeDetector).header.desc);
                list.add(prp.findObject("POTSiconRgn", Typeid.plSceneObject).header.desc);

                for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythCalStar07Get"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythCalStar07Vis_1"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
                for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythCalStarSNDCtrl"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);

                info.list = list;

                info.createObjectList = true; info.outputFileForObjectList = outfolder+"/dat/"+filename+".profile";
            }
            else
            {
                info.usePreexistingObjectList = true; info.objectListResourceName = "/files/profiles/"+filename+".profile";
            }

            info.dest = dest;
            info.sourceprpfiles = sources;
            info.trimDrawableSpans = true;
            info.runtests = false;
            info.forcedDuplicateInclusions = new distiller.includeDuplicateDecider() {
                public boolean include(Uruobjectdesc desc)
                {
                    Typeid tid = desc.objecttype;
                    String name = desc.objectname.toString();
                    if(tid==Typeid.hsGMaterial || tid==Typeid.plLayer || tid==Typeid.plLayerAnimation) return true;
                    if(tid==Typeid.plViewFaceModifier) return true;
                    return false;
                }
            };
            prpdistiller.distiller.distillList(info);

            //save the dest file.
            dest.saveAsFile(outfolder+"/dat/"+filename);
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
        };
    public static void CreateAllProfiles(String infolder, String outfolder, String cleanpotsfolder)
    {
        for(String file: SimplicityAutoModMoulFiles)
        {
            AutoMod(infolder, outfolder, file, cleanpotsfolder, false);
        }
    }
    public static void DustAdditionsGenericCalendarStar(AutoModInfo c, String agename, String fourLetterAgename, int sequencePrefix, int additionsPagenum, String sourcePagename, int calendarStarNum)
    {
        DustAdditionsGenericCalendarStar(c,agename,fourLetterAgename,sequencePrefix,additionsPagenum,sourcePagename,calendarStarNum,"",sourcePagename);
    }
    public static void DustAdditionsGenericCalendarStar(AutoModInfo c, String agename, String fourLetterAgename, int sequencePrefix, int additionsPagenum, String sourcePagename, int calendarStarNum, String outpagesuffix, String altDestPagename)
    {
        prpdistiller.distiller.distillInfo info = new prpdistiller.distiller.distillInfo();

        prpfile dest = prpfile.create(agename, fourLetterAgename+"DustAdditions"+outpagesuffix, Pageid.createFromPrefixPagenum(sequencePrefix, additionsPagenum), Pagetype.createWithType(0));
        dest.addScenenode();

        Vector<prpfile> sources = new Vector();
        prpfile prp;
        sources.add(prp = prpfile.createFromFile(c.infolder+"/dat/"+agename+"_District_"+sourcePagename+".prp", true));
        sources.add(prpfile.createFromFile(c.infolder+"/dat/"+agename+"_District_Textures.prp", true));

        if(!c.useProfiles)
        {
            Vector<prpfile> altdests = new Vector();
            altdests.add(prpfile.createFromFile(c.cleanpotsfolder+"/dat/"+agename+"_District_"+altDestPagename+".prp", true));
            altdests.add(prpfile.createFromFile(c.cleanpotsfolder+"/dat/"+agename+"_District_Textures.prp", true));
            info.altdests = altdests;

            Vector<Uruobjectdesc> list = new Vector();
            String starnum = ((calendarStarNum<10)?"0":"")+Integer.toString(calendarStarNum);
            for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythCalStar"+starnum+"Get"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
            for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythCalStar"+starnum+"Vis_1"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
            for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythCalStar"+starnum+"Vis_3"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
            for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythCalStarSNDCtrl"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
            for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythSparky-SNDCtrl"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
            if(agename.equals("Myst")) list.add(prp.findObject("floor_closed_door", Typeid.plCoordinateInterface).header.desc);

            info.list = list;
            info.createObjectList = true; info.outputFileForObjectList = c.outfolder+"/dat/"+c.filename+".profile";
        }
        else
        {
            info.usePreexistingObjectList = true; info.objectListResourceName = "/files/profiles/"+c.filename+".profile";
        }

        info.dest = dest;
        info.sourceprpfiles = sources;
        info.trimDrawableSpans = true;
        info.runtests = false;
        info.forcedDuplicateInclusions = new distiller.includeDuplicateDecider() {
            public boolean include(Uruobjectdesc desc)
            {
                Typeid tid = desc.objecttype;
                String name = desc.objectname.toString();
                if(tid==Typeid.hsGMaterial || tid==Typeid.plLayer || tid==Typeid.plLayerAnimation) return true;
                if(tid==Typeid.plViewFaceModifier) return true;
                return false;
            }
        };
        prpdistiller.distiller.distillList(info);

        if(agename.equals("Gira"))
        {
            //this changes the draw order so that the fog doesn't override these, but it may mess up other things.  In practice, I can't see a problem on Relto, so we don't have to distill to another drawablespans or use a different prp file to keep them separate.
            for(String sobj: new String[]{"CalendarStarDecal",})
            {
                try{
                uru.moulprp.x0016DrawInterface di = dest.findObject(sobj, Typeid.plDrawInterface).castTo();
                for(uru.moulprp.x0016DrawInterface.SubsetGroupRef sgr: di.subsetgroups)
                {
                    if(sgr.subsetgroupindex!=-1)
                    {
                        uru.moulprp.PlDrawableSpans spans = dest.findObject(sgr.span.xdesc.objectname.toString(), sgr.span.xdesc.objecttype).castTo();
                        //spans.criteria = 0x10000007; //the fog is 20000008
                        spans.criteria = 0x20000003; //0x20000007 was too high.
                    }
                }
                }catch(Exception e){m.err("exception");}
            }
        }
        if(agename.equals("Kadish"))
        {
            //CalendarStarDecal has messed up coordinateinterface, as if it should have a parent but doesn't.  Perhaps it is on another page, tied to pillar03, so that it moves up with pillar03.
            //let's translate it by the height pillar03 moves up (48 feet)
            uru.moulprp.x0015CoordinateInterface ci = dest.findObject("CalendarStarDecal", Typeid.plCoordinateInterface).castTo();
            ci.translate(0, 0, (-89.4645)-(-137.4645), false);
            ci.localToParent = ci.localToWorld;
            ci.parentToLocal = ci.worldToLocal;
        }

        //save the dest file.
        dest.saveAsFile(c.outfolder+"/dat/"+c.filename);
    }
    public static void DustAdditionsPersonal02(AutoModInfo c)
    {
        prpdistiller.distiller.distillInfo info = new prpdistiller.distiller.distillInfo();

        prpfile dest = prpfile.create("Personal02", "philDustAdditions", Pageid.createFromPrefixPagenum(12, 80), Pagetype.createWithType(0));
        dest.addScenenode();

        Vector<prpfile> sources = new Vector();
        prpfile prp;
        sources.add(prp = prpfile.createFromFile(c.infolder+"/dat/philRelto_District_PhilsRelto.prp", true));
        sources.add(prpfile.createFromFile(c.infolder+"/dat/philRelto_District_Textures.prp", true));

        if(!c.useProfiles)
        {
            Vector<prpfile> altdests = new Vector();
            altdests.add(prpfile.createFromFile(c.cleanpotsfolder+"/dat/Personal02_District_philRelto.prp", true));
            altdests.add(prpfile.createFromFile(c.cleanpotsfolder+"/dat/Personal02_District_Textures.prp", true));
            info.altdests = altdests;

            Vector<Uruobjectdesc> list = new Vector();
            for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythCalStar10Get"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
            for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythCalStar10Vis_1"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);
            for(String sobj: prp.findAllSceneobjectsThatReferencePythonfilemod("cPythCalStarSNDCtrl"))list.add(prp.findObject(sobj,Typeid.plSceneObject).header.desc);

            info.list = list;
            info.createObjectList = true; info.outputFileForObjectList = c.outfolder+"/dat/"+c.filename+".profile";
        }
        else
        {
            info.usePreexistingObjectList = true; info.objectListResourceName = "/files/profiles/"+c.filename+".profile";
        }

        info.dest = dest;
        info.sourceprpfiles = sources;
        info.trimDrawableSpans = true;
        info.runtests = false;
        prpdistiller.distiller.distillList(info);

        //save the dest file.
        dest.saveAsFile(c.outfolder+"/dat/"+c.filename);
    }

    public static class AutoModInfo
    {
        String infolder;
        String outfolder;
        String filename;
        String cleanpotsfolder;
        boolean useProfiles;
    }
    public static void AddXDustChronicleShowHideToObject(prpfile prp, x0001Sceneobject sceneobject, String sdlvar, String valToMatch, boolean reverse)
    {
        //create pfm
        //add the pfm to the prp if it doesn't have it already:
        Uruobjectref pfmref;
        String pfmname = "PythXDustChronicleShowHide"+sdlvar+(reverse?"Rev":"");
        PrpRootObject pfmobj = prp.findObject(pfmname, Typeid.plPythonFileMod);
        if(pfmobj==null)
        {
            uru.moulprp.x00A2Pythonfilemod pfm = uru.moulprp.x00A2Pythonfilemod.createDefault();
            pfm.pyfile = Urustring.createFromString("xDustChronicleShowHide");
            pfm.addListing(uru.moulprp.x00A2Pythonfilemod.Pythonlisting.createWithString(1, Bstr.createFromString(sdlvar)));
            pfm.addListing(uru.moulprp.x00A2Pythonfilemod.Pythonlisting.createWithString(2, Bstr.createFromString(valToMatch)));
            pfm.addListing(uru.moulprp.x00A2Pythonfilemod.Pythonlisting.createWithBoolean(3, !reverse));
            //no pythonlistings here...
            pfmref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plPythonFileMod, pfmname, prp);
            pfmobj = PrpRootObject.createFromDescAndObject(pfmref.xdesc, pfm);
            prp.addObject(pfmobj);
        }
        else
        {
            pfmref = pfmobj.header.desc.toRef();
        }
        sceneobject.addToObjectrefs2(pfmref);

    }
    public static void AddXAgeSDLBoolShowHideToObject(prpfile prp, x0001Sceneobject sceneobject, String sdlvar, boolean reverse)
    {
        //create pfm
        //add the pfm to the prp if it doesn't have it already:
        Uruobjectref pfmref;
        String pfmname = "PythXAgeSDLBoolShowHide"+sdlvar+(reverse?"Rev":"");
        PrpRootObject pfmobj = prp.findObject(pfmname, Typeid.plPythonFileMod);
        if(pfmobj==null)
        {
            uru.moulprp.x00A2Pythonfilemod pfm = uru.moulprp.x00A2Pythonfilemod.createDefault();
            pfm.pyfile = Urustring.createFromString("xAgeSDLBoolShowHide");
            pfm.addListing(uru.moulprp.x00A2Pythonfilemod.Pythonlisting.createWithString(1, Bstr.createFromString(sdlvar)));
            pfm.addListing(uru.moulprp.x00A2Pythonfilemod.Pythonlisting.createWithBoolean(2, !reverse));
            //no pythonlistings here...
            pfmref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plPythonFileMod, pfmname, prp);
            pfmobj = PrpRootObject.createFromDescAndObject(pfmref.xdesc, pfm);
            prp.addObject(pfmobj);
        }
        else
        {
            pfmref = pfmobj.header.desc.toRef();
        }
        sceneobject.addToObjectrefs2(pfmref);

    }
    public static void AddXDustShowToObject(prpfile prp, x0001Sceneobject sceneobject)
    {
        //create pfm
        //add the pfm to the prp if it doesn't have it already:
        Uruobjectref pfmref;
        PrpRootObject pfmobj = prp.findObject("PythXDustShow", Typeid.plPythonFileMod);
        if(pfmobj==null)
        {
            uru.moulprp.x00A2Pythonfilemod pfm = uru.moulprp.x00A2Pythonfilemod.createDefault();
            pfm.pyfile = Urustring.createFromString("xDustShow");
            //no pythonlistings here...
            pfmref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plPythonFileMod, "PythXDustShow", prp);
            pfmobj = PrpRootObject.createFromDescAndObject(pfmref.xdesc, pfm);
            prp.addObject(pfmobj);
        }
        else
        {
            pfmref = pfmobj.header.desc.toRef();
        }
        sceneobject.addToObjectrefs2(pfmref);

    }
    public static void ModReltoBooksDynamicTextMap(prpfile prp)
    {
        for(int i=15;i<=36;i++)
        {
            String book = Integer.toString(i);
            if(i<10) book = "0"+book;

            PrpRootObject book01 = prp.findObject("ShelfA_book"+book, Typeid.plDrawInterface);
            uru.moulprp.x0016DrawInterface di = book01.castTo();
            for(uru.moulprp.x0016DrawInterface.SubsetGroupRef sgr: di.subsetgroups)
            {
                PrpRootObject dsroot = prp.findObjectWithRef(sgr.span);
                dsroot.markAsChanged();
                uru.moulprp.PlDrawableSpans ds = dsroot.castTo();
                uru.moulprp.PlDrawableSpans.PlDISpanIndex sg = ds.DIIndices[sgr.subsetgroupindex];
                for(int subsetind: sg.indices)
                {
                    uru.moulprp.PlDrawableSpans.PlIcicle ss = ds.icicles[subsetind];
                    Uruobjectref matrefb = ds.materials.get(ss.parent.parent.materialindex);
                    uru.moulprp.x0007Material mat = prp.findObjectWithRef(matrefb).castTo();
                    uru.moulprp.x0006Layer layer = prp.findObjectWithRef(mat.layerrefs.get(0)).castTo();
                    //matclone.layerrefs.get(0)

                    //found it, now add a material and change ss.materialindex to point to it.
                    int width=1024; int height=1024;
                    PrpRootObject map = automation.hackFactory.createAndAddDynamicTextMap(prp, "book"+book+"DynTexture", width, height);
                    map.markAsChanged();
                    uru.moulprp.x0007Material matclone = mat.deepClone();
                    uru.moulprp.x0006Layer layerclone = layer.deepClone();
                    layerclone.texture = map.getref();
                    Uruobjectref layerref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plLayer, "book"+book+"Layer", prp);
                    matclone.layerrefs.get(0).shallowCopyFrom(layerref);
                    Uruobjectref matref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.hsGMaterial, "book"+book+"Mat", prp);


                    ss.parent.parent.materialindex = ds.addMaterial(matref);

                    prp.extraobjects.add(PrpRootObject.createFromDescAndObject(matref.xdesc, matclone));
                    prp.extraobjects.add(PrpRootObject.createFromDescAndObject(layerref.xdesc, layerclone));

                    //create pythonfilemod to get a python hook to these objects.
                    CreatePythonfilemodReference(prp,"dustReltoDynCovers",map.getref(),8,"dustBook"+book);

                }
            }
        }

    }

    public static void CreatePythonfilemodReference(prpfile prp, String pyfilenameWithoutExtension, Uruobjectref objref, int type, String arbitraryPfmName)
    {
        //create pfm
        uru.moulprp.x00A2Pythonfilemod pfm = uru.moulprp.x00A2Pythonfilemod.createDefault();
        pfm.pyfile = Urustring.createFromString(pyfilenameWithoutExtension);
        pfm.addListing(Pythonlisting.createWithString(4, 1, Bstr.createFromString(objref.xdesc.objectname.toString())));
        pfm.addListing(Pythonlisting.createWithRef(type, 2, objref));
        //pfm.addListing(Pythonlisting.createWithString(4, 3, Bstr.createFromString(objref.xdesc.objecttype.toString())));
        Uruobjectref pfmref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plPythonFileMod, arbitraryPfmName, prp);
        PrpRootObject pfmroot = PrpRootObject.createFromDescAndObject(pfmref.xdesc, pfm);

        //find scenenode.
        PrpRootObject sn = null;
        for(PrpRootObject obj: prp.objects)
            if(obj.header.objecttype==Typeid.plSceneNode)
                sn = obj;

        //create sceneobject
        x0001Sceneobject so = x0001Sceneobject.createDefaultWithScenenode(sn.header.desc.toRef());
        so.addToObjectrefs2(pfmref);
        Uruobjectref soref = Uruobjectref.createDefaultWithTypeNamePrp(Typeid.plSceneObject, arbitraryPfmName+"_so", prp);
        PrpRootObject soroot = PrpRootObject.createFromDescAndObject(soref.xdesc, so);

        //add scenenode and sceneobject
        sn.markAsChanged();
        soroot.markAsChanged();
        pfmroot.markAsChanged();
        prp.extraobjects.add(soroot);
        prp.extraobjects.add(pfmroot);

    }

}
