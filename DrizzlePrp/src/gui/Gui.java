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

package gui;
//import shared.State.CheckboxState;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import javax.swing.event.ListDataListener;
import javax.swing.text.Element;
import javax.swing.text.View;
import shared.FileUtils;
import javax.swing.JFileChooser;
import java.io.File;
import uru.UruCrypt;
import uru.UruFileTypes;
import shared.CryptHashes;
//import uru.Moul;
import uru.moulprp.prputils;
import uru.Bytestream;
import shared.m;
import relationvis.CrossRefPanel;
import shared.b;
import uru.moulprp._staticsettings;
import uru.context;
import uru.moulprp.Typeid;
import shared.Bytes;
import java.util.Vector;
import shared.GuiUtils;

/**
 *
 * @author  user
 */
public class Gui extends javax.swing.JFrame {

    JFileChooser fc = null;
    //CrossRefPanel vis = null;
    
    //String moul;
    //String pots;
    //String out;
    public String settingsfile;
    Settings settings = new Settings();
    //boolean dosavesettings = false;
    
    deepview.deepview deep;
    public final shared.State.TabsState tabs;
    
    /** Creates new form Gui */
    public Gui()
    {
        translation.translation.enable("/gui/");
        settingsfile = FileUtils.GetPresentWorkingDirectory()+"drizzlesettings.canbedeleted.dat";
        initComponents();
        int maxwidth = 1024; int maxheight = 740; //don't change these.
        //int width = 1016; int height = 732; //border of 4
        int width = 1016; int height = 726;
        //this.setMinimumSize(new java.awt.Dimension(width, height));
        this.setBounds((maxwidth-width)/2, (maxheight-height)/2, width, height);
        this.setTitle("Drizzle "+Integer.toString(Version.version));

        translation.translation.registerGUIForm(this);
        
        gui.UamGui.guiform = this;
        gui.UamGui.agelist = this.jList1;
        gui.UamGui.verlist = this.jList2;
        gui.UamGui.mirlist = this.jList3;
        gui.UamGui.deletebutton = this.jButton129;
        gui.UamGui.downloadbutton = this.jButton127;
        gui.UamGui.AgeLabel = this.ageInfo3;
        gui.UamGui.startup = this.uamStartupButtongroup;
        gui.UamGui.init();
        tabs = this.tabsState3;

        //set icon:
        java.awt.Image img = shared.GetResource.getResourceAsImage("/gui/Pterosaur2b4-16.png");
        this.setIconImage(img);
        
        GuiUtils.SetKeymaps();
        
        deep = new deepview.deepview(jDesktopPane1);


        listState4.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {
                "Dereno_District_DrnoPod.prp",
                "Minkata_District_minkExteriorDay.prp",
                "EderTsogal_District_tsoGarden.prp",
                "GUI_District_AdvancedGameSettingsDialog.prp",
                "Cleft_District_YeeshaVisionBlocked.prp",
                "Personal_District_psnlMYSTII.prp",
                "AhnySphere01_District_Sphere01.prp",
                "Neighborhood02_District_krelKirel.prp",
            };
            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int index) {
                return strings[index];
            }
        });
        listState2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {
                "Descent.(others)","Descent.age","Descent.fni","Descent.sum","Descent_dsntBahro_Idle02.prp","Descent_dsntBahro_Idle03.prp","Descent_dsntBahro_Idle04.prp","Descent_dsntBahro_Idle05.prp","Descent_dsntBahro_Idle06.prp","Descent_dsntBahro_Idle07.prp","Descent_dsntBahro_Idle08.prp","Descent_dsntBahro_Idle09.prp","Descent_dsntBahro_Shot02.prp","Descent_dsntBahro_Shot03.prp","Descent_dsntBahro_Shot04.prp","Descent_dsntBahro_Shot05.prp","Descent_dsntBahro_Shot06.prp","Descent_dsntBahro_Shot07.prp","Descent_dsntBahro_Shot08.prp","Descent_dsntBahro_Shot09.prp","Descent_dsntBahro_Tunnel01.prp","Descent_dsntBahro_Tunnel01Idle.prp","Descent_dsntBats.prp","Descent_dsntEsherIdleTopOfShaft.prp","Descent_dsntEsher_BottomOfShaft.prp","Descent_dsntEsher_FirstHub.prp","Descent_dsntEsher_Intro.prp","Descent_dsntEsher_TopOfShaft.prp","Descent_dsntGreatShaftBalcony.prp","Descent_dsntGreatShaftLowerRm.prp","Descent_dsntLowerBats.prp","Descent_dsntMapGUI.prp","Descent_dsntPostBats.prp","Descent_dsntPostShaftNodeAndTunnels.prp","Descent_dsntShaftGeneratorRoom.prp","Descent_dsntShaftTunnelSystem.prp","Descent_dsntTianaCave.prp","Descent_dsntTianaCaveNode2.prp","Descent_dsntTianaCaveTunnel1.prp","Descent_dsntTianaCaveTunnel3.prp","Descent_dsntUpperBats.prp","Descent_dsntUpperShaft.prp","Descent_dsntVolcano.prp","Descent_Textures.prp","Descent_dusttest.prp",
                /*"Direbo.sdl",*/"Direbo.(others)","Direbo.age","Direbo.fni","Direbo.sum","Direbo_DragonFly.prp","Direbo_drboEsherIdleDirebo.prp","Direbo_drboEsher_DireboLaki.prp","Direbo_drboEsher_DireboSrln.prp","Direbo_drboEsher_DireboTdlm.prp","Direbo_drboEsher_DireboThgr.prp","Direbo_drboUrwinShape.prp","Direbo_RestAge.prp","Direbo_Textures.prp","Direbo_UrwinIdle.prp","Direbo_UrwinWalk.prp",
                "Kveer.(others)","Kveer.age","Kveer.fni","Kveer.sum","Kveer_bkMystBookLocked.prp","Kveer_GreatRm.prp","Kveer_KveerBats.prp","Kveer_kverAtrus.prp","Kveer_kverAtrus_1.prp","Kveer_kverAtrus_Idle.prp","Kveer_kverBahroWingsGUI.prp","Kveer_kverBahro_1.prp","Kveer_kverBahro_2.prp","Kveer_kverBahro_Ballroom01.prp","Kveer_kverBahro_Ballroom02.prp","Kveer_kverBahro_Ballroom03.prp","Kveer_kverBahro_Exit01.prp","Kveer_kverBahro_Exit02.prp","Kveer_kverBahro_Idle05.prp","Kveer_kverBahro_Idle06.prp","Kveer_kverBahro_Idle07.prp","Kveer_kverBahro_Idle08.prp","Kveer_kverBahro_Idle09.prp","Kveer_kverBahro_Shot03.prp","Kveer_kverBahro_Shot04.prp","Kveer_kverBahro_Shot05.prp","Kveer_kverBahro_Shot06.prp","Kveer_kverBahro_Shot07.prp","Kveer_kverBahro_Shot08.prp","Kveer_kverBahro_Shot09.prp","Kveer_kverConc3Music.prp","Kveer_kverEsher_1.prp","Kveer_kverReleeshan.prp","Kveer_kverYeesha_1.prp","Kveer_kverYeesha_Conc01.prp","Kveer_kverYeesha_Conc02.prp","Kveer_kverYeesha_Conc03.prp","Kveer_kverYeesha_ConcIntro.prp","Kveer_kverYeesha_ConcIntro2.prp","Kveer_kverYeesha_IdleForIntro.prp","Kveer_kverYeesha_Intro.prp","Kveer_Prison.prp","Kveer_Textures.prp","Kveer_dusttest.prp",
                "Laki.(others)","Laki.age","Laki.fni","Laki.sum","Laki_Exterior.prp","Laki_LakiArenaVillaInt.prp","Laki_LakiCreatures.prp","Laki_lakiEsher-Arena.prp","Laki_lakiEsher-FighterBeach.prp","Laki_lakiEsher-Keep.prp","Laki_lakiEsher-Villa.prp","Laki_lakiEsherIdleKeep.prp","Laki_lakiEsherIdleVilla.prp","Laki_LakiMaze.prp","Laki_lakiMazeClue.prp","Laki_LakiTrees01.prp","Laki_PirBirdActor.prp","Laki_PirBirdChomp.prp","Laki_PirBirdIdle.prp","Laki_PirBirdSwallow.prp","Laki_PirBirdVocalize.prp","Laki_PirBirdWalk.prp","Laki_Textures.prp","Laki_dusttest.prp",
                "Myst.(others)","Myst.age","Myst.fni","Myst.sum","Myst_Island.prp","Myst_mystEsher-Conc01.prp","Myst_mystEsher-Conc02.prp","Myst_Textures.prp",
                "Siralehn.(others)","Siralehn.age","Siralehn.fni","Siralehn.sum","Siralehn_Birds.prp","Siralehn_Drawing01.prp","Siralehn_Drawing02.prp","Siralehn_Drawing03.prp","Siralehn_Drawing04.prp","Siralehn_Drawing05.prp","Siralehn_Drawing06.prp","Siralehn_Drawing07.prp","Siralehn_Drawing08.prp","Siralehn_Exterior.prp","Siralehn_rock.prp","Siralehn_srlnEsherIdleBeach.prp","Siralehn_srlnEsherIdleLab.prp","Siralehn_srlnEsher_NolobenBeach.prp","Siralehn_srlnEsher_NolobenKeep.prp","Siralehn_srlnEsher_NolobenLab.prp","Siralehn_srlnKeepInter.prp","Siralehn_Textures.prp","Siralehn_tunnels.prp","Siralehn_dusttest.prp",
                "Tahgira.(others)","Tahgira.age","Tahgira.fni","Tahgira.sum","Tahgira_Exterior.prp","Tahgira_IceCave.prp","Tahgira_Textures.prp","Tahgira_thgrEsherIdleIntro.prp","Tahgira_thgrEsherIdleTake.prp","Tahgira_thgrEsher_TahgiraGrave.prp","Tahgira_thgrEsher_TahgiraIntro.prp","Tahgira_thgrEsher_TahgiraTake.prp","Tahgira_thgrEsher_TahgiraThermals.prp","Tahgira_thgrEsher_TahgiraVillage.prp","Tahgira_dusttest.prp",
                "Todelmer.(others)","Todelmer.age","Todelmer.fni","Todelmer.sum","Todelmer_Exterior.prp","Todelmer_InteriorPillar1.prp","Todelmer_InteriorPillar3.prp","Todelmer_MiniScope.prp","Todelmer_Pod.prp","Todelmer_Sky.prp","Todelmer_tdlmEsherIdleP3.prp","Todelmer_tdlmEsherIdleRing.prp","Todelmer_tdlmEsher_TodelmerP1.prp","Todelmer_tdlmEsher_TodelmerP3.prp","Todelmer_tdlmEsher_TodelmerRing.prp","Todelmer_Textures.prp","Todelmer_dusttest.prp",
                "restStop1.bik","restStop2.bik","restStop3.bik","restStop4.bik",
            };
            public int getSize() {
                return strings.length;
            }
            public Object getElementAt(int index) {
                return strings[index];
            }
        });
        listState3.setModel(new javax.swing.AbstractListModel() {
            Vector<String> strings = automation.fileLists.moulSupportedList();
            public int getSize() {
                //return strings.length;
                return strings.size();
            }
            public Object getElementAt(int index) {
                //return strings[index];
                return strings.get(index);
            }
        });
        
        //fc = new JFileChooser();
        m.setJTextArea(this.jTextArea1); //make sure the messages will come through.
        //vis = new visualisation(this.jPanel5.getGraphics());
        m.redirectStdErr();
        m.redirectStdOut();

        //check memory...
        if(Main.maxmemory<256000000) m.warn("It appears that you unpacked Drizzle",Integer.toString(gui.Version.version),".jar and ran DrizzlePrp.jar.  You should run Drizzle",Integer.toString(gui.Version.version),".jar directly.  Alternatively, you could run Drizzle with a larger maximum heap space. E.g.: java -Xmx800m -jar DrizzlePrp.jar");
        if(Main.javaversion2<1.6) m.warn("Your version of java seems to be older than 1.6; some things might not work.");
        if(Main.os.toLowerCase().startsWith("windows") && Main.osversion2>5.1) m.warn("You appear to be running Windows Vista or Windows Seven.  Uru has a bug that will require a workaround, see http://alcugs.almlys.org/Drizzle for details.");
        //try{
        //java.net.URL url = this.getClass().getResource("Image2.png");
        //javax.swing.ImageIcon image = new javax.swing.ImageIcon(url,"");
        //java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(url);
        //java.awt.Image img = shared.GetResource.getResourceAsImage(this, "Image2.png");
        //this.jPanel32.getGraphics().drawImage(img, 0, 0, rootPane);
        //this.setIconImage(image.getImage());
        //}catch(Exception e){}
        
        //jTextArea5.repaint();
        //jTextArea5.scrollRectToVisible(new java.awt.Rectangle(0,0,1,1));

        
        /*jComboBox1.setEditable(true);
        this.jComboBox1.removeAllItems();
        //pots = "D:/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/Uru - Ages Beyond Myst/dat/";
        //moul = "D:/a/winedrive/drive_c/MystOnline/Program Files/Myst Online/dat/";
        out = "C:/Documents and Settings/user/Desktop/output/doesnotexist/";
        //pots = "/shared/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/Uru - Ages Beyond Myst/dat/";
        //moul = "/shared/a/winedrive/drive_c/MystOnline/Program Files/Myst Online/dat/";
        //out = "/shared/a/leftoff/output/";
        pots = "D:/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/Uru - Ages Beyond Myst/dat/doesnotexist/";
        moul = "D:/a/winedrive/drive_c/MystOnline/Program Files/Myst Online/dat/doesnotexist/";
        //out = "D:/a/leftoff/output/";*/
        //uru.moulprp._staticsettings.outputdir = out;

        /*this.jComboBox1.addItem("");
        this.jComboBox1.addItem("C:/Documents and Settings/user/Desktop/output/dat/MarshScene_District_Exterior.prp");
        this.jComboBox1.addItem("GuildPub-Cartographers_District_Pub.prp");
        this.jComboBox1.addItem("GuildPub-Cartographers_District_Textures.prp");
        this.jComboBox1.addItem("GuildPub-Cartographers_District_BuiltIn.prp");
        this.jComboBox1.addItem("GuildPub-Greeters_District_Pub.prp");
        this.jComboBox1.addItem("GuildPub-Greeters_District_Textures.prp");
        this.jComboBox1.addItem("GuildPub-Greeters_District_BuiltIn.prp");
        this.jComboBox1.addItem("GuildPub-Maintainers_District_Pub.prp");
        this.jComboBox1.addItem("GuildPub-Maintainers_District_Textures.prp");
        this.jComboBox1.addItem("GuildPub-Maintainers_District_BuiltIn.prp");
        this.jComboBox1.addItem("GuildPub-Messengers_District_Pub.prp");
        this.jComboBox1.addItem("GuildPub-Messengers_District_Textures.prp");
        this.jComboBox1.addItem("GuildPub-Messengers_District_BuiltIn.prp");
        this.jComboBox1.addItem("GuildPub-Writers_District_Pub.prp");
        this.jComboBox1.addItem("GuildPub-Writers_District_Textures.prp");
        this.jComboBox1.addItem("GuildPub-Writers_District_BuiltIn.prp");
        this.jComboBox1.addItem("Jalak_District_jlakArena.prp");
        this.jComboBox1.addItem("Jalak_District_Textures.prp");
        this.jComboBox1.addItem("Jalak_District_BuiltIn.prp");
        this.jComboBox1.addItem("Minkata_District_minkExteriorDay.prp");
        this.jComboBox1.addItem("Minkata_District_minkNightLinkSounds.prp");
        this.jComboBox1.addItem("Minkata_District_minkExteriorNight.prp");
        this.jComboBox1.addItem("Minkata_District_minkExcludeRegions.prp");
        this.jComboBox1.addItem("Minkata_District_minkDistCraterPhysicals.prp");
        this.jComboBox1.addItem("Minkata_District_minkDayLinkSounds.prp");
        this.jComboBox1.addItem("Minkata_District_minkCameras.prp");
        this.jComboBox1.addItem("Minkata_District_Textures.prp");
        this.jComboBox1.addItem("Minkata_District_BuiltIn.prp");
        this.jComboBox1.addItem("Tetsonot_District_tetsoPod.prp");
        this.jComboBox1.addItem("Tetsonot_District_Textures.prp");
        this.jComboBox1.addItem("Tetsonot_District_BuiltIn.prp");
        this.jComboBox1.addItem("Negilahn_District_Jungle.prp");
        this.jComboBox1.addItem("Negilahn_District_MuseumPod.prp");
        this.jComboBox1.addItem("Negilahn_District_Textures.prp");
        this.jComboBox1.addItem("Negilahn_District_BuiltIn.prp");
        this.jComboBox1.addItem("Dereno_District_DrnoExterior.prp");
        this.jComboBox1.addItem("Dereno_District_DrnoPod.prp");
        this.jComboBox1.addItem("Dereno_District_Textures.prp");
        this.jComboBox1.addItem("Dereno_District_BuiltIn.prp");
        this.jComboBox1.addItem("Payiferen_District_Pod.prp");
        this.jComboBox1.addItem("Payiferen_District_Textures.prp");
        this.jComboBox1.addItem("Payiferen_District_BuiltIn.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_MINKcave.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_POTScave.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_PODcave.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_BlueSpiralCave.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_TheSpecialPage.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_Textures.prp");
        this.jComboBox1.addItem("LiveBahroCaves_District_BuiltIn.prp");
        this.jComboBox1.addItem("EderTsogal_District_tsoGarden.prp");
        this.jComboBox1.addItem("EderTsogal_District_Textures.prp");
        this.jComboBox1.addItem("EderTsogal_District_BuiltIn.prp");
        this.jComboBox1.addItem("EderDelin_District_garden.prp");
        this.jComboBox1.addItem("EderDelin_District_BuiltIn.prp");
        this.jComboBox1.addItem("EderDelin_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Garden_District_kemoGarden.prp");
        this.jComboBox1.addItem(pots+"Garden_District_kemoGarden.prp");
        this.jComboBox1.addItem(moul+"Personal_District_psnlMYSTII.prp");
        this.jComboBox1.addItem(pots+"Personal_District_psnlMYSTII.prp");
        this.jComboBox1.addItem(moul+"spyroom_District_spyroom.prp");
        this.jComboBox1.addItem(pots+"spyroom_District_spyroom.prp");
        this.jComboBox1.addItem(moul+"EderTsogal_District_tsoGarden.prp");
        this.jComboBox1.addItem(moul+"EderTsogal_District_Textures.prp");
        this.jComboBox1.addItem(moul+"EderTsogal_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"Negilahn_District_Jungle.prp");
        this.jComboBox1.addItem(moul+"Negilahn_District_MuseumPod.prp");
        this.jComboBox1.addItem(moul+"Negilahn_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Negilahn_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"Dereno_District_DrnoExterior.prp");
        this.jComboBox1.addItem(moul+"Dereno_District_DrnoPod.prp");
        this.jComboBox1.addItem(moul+"Dereno_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Dereno_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"Payiferen_District_Pod.prp");
        this.jComboBox1.addItem(moul+"Payiferen_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Payiferen_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"EderTsogal_District_tsoGarden.prp");
        this.jComboBox1.addItem(out+"EderTsogal_District_Textures.prp");
        this.jComboBox1.addItem(out+"EderTsogal_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"Negilahn_District_Jungle.prp");
        this.jComboBox1.addItem(out+"Negilahn_District_MuseumPod.prp");
        this.jComboBox1.addItem(out+"Negilahn_District_Textures.prp");
        this.jComboBox1.addItem(out+"Negilahn_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"Dereno_District_DrnoExterior.prp");
        this.jComboBox1.addItem(out+"Dereno_District_DrnoPod.prp");
        this.jComboBox1.addItem(out+"Dereno_District_Textures.prp");
        this.jComboBox1.addItem(out+"Dereno_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"Payiferen_District_Pod.prp");
        this.jComboBox1.addItem(out+"Payiferen_District_Textures.prp");
        this.jComboBox1.addItem(out+"Payiferen_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"Jalak_District_jlakArena.prp");
        this.jComboBox1.addItem(moul+"Jalak_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Jalak_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkExteriorDay.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkNightLinkSounds.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkExteriorNight.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkExcludeRegions.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkDistCraterPhysicals.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkDayLinkSounds.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_minkCameras.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Minkata_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"Tetsonot_District_tetsoPod.prp");
        this.jComboBox1.addItem(moul+"Tetsonot_District_Textures.prp");
        this.jComboBox1.addItem(moul+"Tetsonot_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"Tetsonot_District_tetsoPod.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_MINKcave.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_PODcave.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_POTScave.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_BlueSpiralCave.prp");
        this.jComboBox1.addItem(out+"LiveBahroCaves_District_BlueSpiralCave.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_TheSpecialPage.prp");
        this.jComboBox1.addItem(out+"LiveBahroCaves_District_TheSpecialPage.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_Textures.prp");
        this.jComboBox1.addItem(out+"LiveBahroCaves_District_Textures.prp");
        this.jComboBox1.addItem(moul+"LiveBahroCaves_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"LiveBahroCaves_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"GuildPub-Cartographers_District_Pub.prp");
        this.jComboBox1.addItem(out+"GuildPub-Cartographers_District_Pub.prp");
        this.jComboBox1.addItem(moul+"GuildPub-Cartographers_District_Textures.prp");
        this.jComboBox1.addItem(out+"GuildPub-Cartographers_District_Textures.prp");
        this.jComboBox1.addItem(moul+"GuildPub-Cartographers_District_BuiltIn.prp");
        this.jComboBox1.addItem(out+"GuildPub-Cartographers_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"EderDelin_District_garden.prp");
        this.jComboBox1.addItem(moul+"EderDelin_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"EderDelin_District_Textures.prp");
        this.jComboBox1.addItem(pots+"spyroom_District_BuiltIn.prp");
        this.jComboBox1.addItem(pots+"spyroom_District_spyroom.prp");
        this.jComboBox1.addItem(pots+"spyroom_District_Textures.prp");
        this.jComboBox1.addItem(moul+"spyroom_District_BuiltIn.prp");
        this.jComboBox1.addItem(moul+"spyroom_District_spyroom.prp");
        this.jComboBox1.addItem(moul+"spyroom_District_Textures.prp");
        this.jComboBox1.addItem(pots);
        this.jComboBox1.addItem(moul);*/

        //if(!uru.moulprp.Typeid.validateTriplets()) m.err("Triplets not valid!");
        
        //this.loadsettings();
        //dosavesettings = true;
        shared.State.AllStates.loadandpush(settingsfile);

        //language stuff:
        pushlanguage(true);
        //String helpstr = shared.GetResource.getResourceAsString("/gui/help.txt");
        //jTextArea5.setText(helpstr);
        translation.translation.registerResourceString("/gui/help.txt", jTextArea5);

        SetBackgroundColour(true);

    }
    void pushlanguage(boolean silent)
    {
        int langnum = shared.State.AllStates.getStateAsInt("language");
        switch(langnum)
        {
            case 0:
                translation.translation.setLanguage("en");
                break;
            case 1:
                translation.translation.setLanguage("de");
                break;
            case 2:
                translation.translation.setLanguage("fr");
                break;
            case 3:
                translation.translation.setLanguage("nl");
                break;
            default:
                m.err("Unhandled language in pushlanguge()");
                break;
        }
        if(!silent) m.msg("Setting language to ",translation.translation.getCurLanguage());
    }
    public static class Settings implements java.io.Serializable
    {
        String curfile;
        int curfolder;
        
    }
    /*public void savesettings()
    {
        if(!dosavesettings) return;
        
        try
        {
            settings.curfile = (String)this.jComboBox1.getSelectedItem();
            //settings.curfolder = this.jRadioButton1.isSelected()
            if(this.jRadioButton1.isSelected()) settings.curfolder = 1;
            if(this.jRadioButton2.isSelected()) settings.curfolder = 2;
            if(this.jRadioButton3.isSelected()) settings.curfolder = 3;
            if(this.jRadioButton4.isSelected()) settings.curfolder = 4;
            
            java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(new java.io.FileOutputStream(this.out+"settings.dat"));
            out.writeObject(settings);
            out.close();
        }
        catch(Exception e)
        {
        }
    }*/
    /*public void loadsettings()
    {
        try
        {
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(new java.io.FileInputStream(this.out+"settings.dat"));
            settings = (Settings)in.readObject();
            in.close();
            this.jComboBox1.setSelectedItem(settings.curfile);
            if(settings.curfolder==1) this.jRadioButton1.setSelected(true);
            if(settings.curfolder==2) this.jRadioButton2.setSelected(true);
            if(settings.curfolder==3) this.jRadioButton3.setSelected(true);
            if(settings.curfolder==4) this.jRadioButton4.setSelected(true);
        }
        catch(Exception e)
        {
        }
    }*/
    /*private String getSelectedFilename()
    {
        String name = (String)this.jComboBox1.getSelectedItem();
        //String command = this.filedirButtonGroup.getSelection().getActionCommand();
        String result = null;
        if(this.jRadioButton1.isSelected()) result = this.moul+name;
        if(this.jRadioButton2.isSelected()) result = this.pots+name;
        if(this.jRadioButton3.isSelected()) result = this.out+name;
        if(this.jRadioButton4.isSelected()) result = name;
        if(result==null) m.err("Uruutils: problem in getSelectedFilename.");
        return result;
    }*/
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filedirButtonGroup = new javax.swing.ButtonGroup();
        uamStartupButtongroup = new shared.State.ButtongroupState();
        languageButtonGroup = new shared.State.ButtongroupState();
        jPanel28 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jPanel26 = new javax.swing.JPanel();
        jButton107 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        checkboxState24 = new shared.State.CheckboxState();
        tabsState3 = new shared.State.TabsState();
        UAMTab = new javax.swing.JPanel();
        jButton124 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jScrollPane12 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jButton127 = new javax.swing.JButton();
        jButton129 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        textfieldState38 = new shared.State.TextfieldState();
        jButton125 = new javax.swing.JButton();
        textfieldState39 = new shared.State.TextfieldState();
        jLabel37 = new javax.swing.JLabel();
        jButton136 = new javax.swing.JButton();
        jButton135 = new javax.swing.JButton();
        jPanel43 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        imagePanel3 = new shared.ImagePanel();
        imagePanel4 = new shared.ImagePanel();
        imagePanel5 = new shared.ImagePanel();
        imagePanel1 = new shared.ImagePanel();
        jPanel44 = new javax.swing.JPanel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jLabel50 = new javax.swing.JLabel();
        ageInfo3 = new javax.swing.JLabel();
        SimpTab = new javax.swing.JPanel();
        tabsState1 = new shared.State.TabsState();
        jPanel35 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        textfieldState33 = new shared.State.TextfieldState();
        textfieldState34 = new shared.State.TextfieldState();
        jButton114 = new javax.swing.JButton();
        jButton115 = new javax.swing.JButton();
        jButton116 = new javax.swing.JButton();
        jButton141 = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        textfieldState22 = new shared.State.TextfieldState();
        jLabel20 = new javax.swing.JLabel();
        textfieldState23 = new shared.State.TextfieldState();
        jButton96 = new javax.swing.JButton();
        jButton97 = new javax.swing.JButton();
        jButton98 = new javax.swing.JButton();
        jButton140 = new javax.swing.JButton();
        jPanel40 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        textfieldState40 = new shared.State.TextfieldState();
        textfieldState41 = new shared.State.TextfieldState();
        jLabel42 = new javax.swing.JLabel();
        jButton130 = new javax.swing.JButton();
        jButton131 = new javax.swing.JButton();
        jButton132 = new javax.swing.JButton();
        jButton142 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        MemTab = new javax.swing.JPanel();
        textfieldState36 = new shared.State.TextfieldState();
        jButton121 = new javax.swing.JButton();
        jButton122 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        textfieldState37 = new shared.State.TextfieldState();
        jButton123 = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        GHelpTab = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jButton42 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jButton35 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea3 = new shared.State.TextareaStateless();
        jButton43 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        textareaState1 = new shared.State.TextareaState();
        jLabel45 = new javax.swing.JLabel();
        ProxyTab = new javax.swing.JPanel();
        jButton70 = new javax.swing.JButton();
        textfieldState9 = new shared.State.TextfieldState();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textfieldState10 = new shared.State.TextfieldState();
        jButton71 = new javax.swing.JButton();
        checkboxState10 = new shared.State.CheckboxState();
        jLabel17 = new javax.swing.JLabel();
        jButton77 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        MiscPanel = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jButton126 = new javax.swing.JButton();
        jButton137 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        checkboxState25 = new shared.State.CheckboxState();
        jButton139 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton134 = new javax.swing.JButton();
        textfieldState42 = new shared.State.TextfieldState();
        jButton133 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jPanel46 = new javax.swing.JPanel();
        textfieldState45 = new shared.State.TextfieldState();
        jButton145 = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        textfieldState46 = new shared.State.TextfieldState();
        textfieldState47 = new shared.State.TextfieldState();
        jButton146 = new javax.swing.JButton();
        jButton147 = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        sliderState1 = new shared.State.SliderState();
        AdvTabState = new shared.State.TabsState();
        jPanel48 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        textfieldState12 = new shared.State.TextfieldState();
        jButton74 = new javax.swing.JButton();
        jButton75 = new javax.swing.JButton();
        jButton76 = new javax.swing.JButton();
        jButton87 = new javax.swing.JButton();
        jButton72 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        textfieldState11 = new shared.State.TextfieldState();
        jButton73 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea6 = new shared.State.TextareaStateless();
        jPanel4 = new javax.swing.JPanel();
        jButton23 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        crossRefPanel1 = new relationvis.CrossRefPanel();
        textfieldState50 = new shared.State.TextfieldState();
        jButton19 = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        textfieldState51 = new shared.State.TextfieldState();
        jPanel1 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton44 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        textfieldState48 = new shared.State.TextfieldState();
        textfieldState49 = new shared.State.TextfieldState();
        jPanel49 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jButton143 = new javax.swing.JButton();
        textfieldState44 = new shared.State.TextfieldState();
        textfieldState43 = new shared.State.TextfieldState();
        jPanel36 = new javax.swing.JPanel();
        jButton105 = new javax.swing.JButton();
        textfieldState26 = new shared.State.TextfieldState();
        textfieldState27 = new shared.State.TextfieldState();
        textfieldState28 = new shared.State.TextfieldState();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        textfieldState52 = new shared.State.TextfieldState();
        textfieldState53 = new shared.State.TextfieldState();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jButton37 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jButton38 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        textfieldState15 = new shared.State.TextfieldState();
        textfieldState16 = new shared.State.TextfieldState();
        jButton88 = new javax.swing.JButton();
        jButton89 = new javax.swing.JButton();
        jButton128 = new javax.swing.JButton();
        jButton138 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jButton45 = new javax.swing.JButton();
        textfieldState21 = new shared.State.TextfieldState();
        jButton95 = new javax.swing.JButton();
        jButton101 = new javax.swing.JButton();
        textfieldState24 = new shared.State.TextfieldState();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        textfieldState25 = new shared.State.TextfieldState();
        jButton102 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jButton103 = new javax.swing.JButton();
        jButton104 = new javax.swing.JButton();
        jButton106 = new javax.swing.JButton();
        jButton108 = new javax.swing.JButton();
        jButton109 = new javax.swing.JButton();
        jButton110 = new javax.swing.JButton();
        textfieldState29 = new shared.State.TextfieldState();
        textfieldState30 = new shared.State.TextfieldState();
        jPanel12 = new javax.swing.JPanel();
        jButton50 = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton39 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jButton119 = new javax.swing.JButton();
        textfieldState35 = new shared.State.TextfieldState();
        jButton120 = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        textfieldState13 = new shared.State.TextfieldState();
        jButton80 = new javax.swing.JButton();
        jButton81 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jButton83 = new javax.swing.JButton();
        jButton84 = new javax.swing.JButton();
        textfieldState14 = new shared.State.TextfieldState();
        jButton85 = new javax.swing.JButton();
        jButton117 = new javax.swing.JButton();
        jButton118 = new javax.swing.JButton();
        tabsState2 = new shared.State.TabsState();
        jPanel15 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        listState2 = new shared.State.ListState();
        textfieldState2 = new shared.State.TextfieldState();
        textfieldState3 = new shared.State.TextfieldState();
        jLabel10 = new javax.swing.JLabel();
        jButton78 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton53 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        textfieldState4 = new shared.State.TextfieldState();
        textfieldState5 = new shared.State.TextfieldState();
        jPanel16 = new javax.swing.JPanel();
        jButton79 = new javax.swing.JButton();
        checkboxState2 = new shared.State.CheckboxState();
        checkboxState14 = new shared.State.CheckboxState();
        checkboxState15 = new shared.State.CheckboxState();
        checkboxState16 = new shared.State.CheckboxState();
        jPanel17 = new javax.swing.JPanel();
        textfieldState7 = new shared.State.TextfieldState();
        jLabel11 = new javax.swing.JLabel();
        jButton62 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        listState4 = new shared.State.ListState();
        jButton69 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        textfieldState6 = new shared.State.TextfieldState();
        textfieldState8 = new shared.State.TextfieldState();
        jButton64 = new javax.swing.JButton();
        jButton65 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jButton67 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jButton68 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        listState3 = new shared.State.ListState();
        jButton66 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        textfieldState1 = new shared.State.TextfieldState();
        jButton60 = new javax.swing.JButton();
        jButton86 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        checkboxState1 = new shared.State.CheckboxState();
        checkboxState3 = new shared.State.CheckboxState();
        checkboxState4 = new shared.State.CheckboxState();
        checkboxState5 = new shared.State.CheckboxState();
        checkboxState6 = new shared.State.CheckboxState();
        checkboxState7 = new shared.State.CheckboxState();
        checkboxState12 = new shared.State.CheckboxState();
        checkboxState13 = new shared.State.CheckboxState();
        checkboxState21 = new shared.State.CheckboxState();
        jPanel20 = new javax.swing.JPanel();
        checkboxState8 = new shared.State.CheckboxState();
        checkboxState9 = new shared.State.CheckboxState();
        checkboxState11 = new shared.State.CheckboxState();
        checkboxState17 = new shared.State.CheckboxState();
        checkboxState18 = new shared.State.CheckboxState();
        checkboxState19 = new shared.State.CheckboxState();
        checkboxState20 = new shared.State.CheckboxState();
        checkboxState22 = new shared.State.CheckboxState();
        checkboxState23 = new shared.State.CheckboxState();
        jPanel23 = new javax.swing.JPanel();
        jButton90 = new javax.swing.JButton();
        textfieldState18 = new shared.State.TextfieldState();
        textfieldState19 = new shared.State.TextfieldState();
        jButton91 = new javax.swing.JButton();
        jButton92 = new javax.swing.JButton();
        jButton93 = new javax.swing.JButton();
        textfieldState20 = new shared.State.TextfieldState();
        jButton94 = new javax.swing.JButton();
        jButton99 = new javax.swing.JButton();
        jButton100 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        textfieldState17 = new shared.State.TextfieldState();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jButton41 = new javax.swing.JButton();
        textfieldState54 = new shared.State.TextfieldState();
        jButton144 = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        textfieldState31 = new shared.State.TextfieldState();
        textfieldState32 = new shared.State.TextfieldState();
        jButton111 = new javax.swing.JButton();
        jButton112 = new javax.swing.JButton();
        jButton113 = new javax.swing.JButton();
        HelpTab = new javax.swing.JPanel();
        imagePanel2 = new shared.ImagePanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea5 = new shared.State.TextareaStateless();
        jPanel27 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea1 = new shared.State.TextareaStateless();

        uamStartupButtongroup.setName("uamStartup");

        languageButtonGroup.setName("language");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Drizzle");
        setBounds(new java.awt.Rectangle(2, 2, 995, 713));
        setMinimumSize(null);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder("Language"));
        jPanel28.setLayout(null);

        languageButtonGroup.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("English");
        jRadioButton1.setMargin(new java.awt.Insets(0, 2, 0, 2));
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel28.add(jRadioButton1);
        jRadioButton1.setBounds(10, 30, 80, 20);

        languageButtonGroup.add(jRadioButton2);
        jRadioButton2.setText("Deutsch");
        jRadioButton2.setMargin(new java.awt.Insets(0, 2, 0, 2));
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel28.add(jRadioButton2);
        jRadioButton2.setBounds(10, 50, 80, 20);

        languageButtonGroup.add(jRadioButton3);
        jRadioButton3.setText("Français");
        jRadioButton3.setMargin(new java.awt.Insets(0, 2, 0, 2));
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel28.add(jRadioButton3);
        jRadioButton3.setBounds(10, 70, 80, 20);

        languageButtonGroup.add(jRadioButton4);
        jRadioButton4.setText("Nederlands");
        jRadioButton4.setMargin(new java.awt.Insets(0, 2, 0, 2));
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel28.add(jRadioButton4);
        jRadioButton4.setBounds(10, 90, 94, 20);

        getContentPane().add(jPanel28);
        jPanel28.setBounds(890, 510, 110, 180);

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));
        jPanel26.setLayout(null);

        jButton107.setText("Save settings now");
        jButton107.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton107ActionPerformed(evt);
            }
        });
        jPanel26.add(jButton107);
        jButton107.setBounds(10, 50, 230, 36);

        jButton61.setText("Delete settings and exit");
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });
        jPanel26.add(jButton61);
        jButton61.setBounds(10, 20, 230, 36);

        checkboxState24.setSelected(true);
        checkboxState24.setText("Save settings on exit.");
        checkboxState24.setName("saveOnExit"); // NOI18N
        jPanel26.add(checkboxState24);
        checkboxState24.setBounds(20, 80, 210, 50);

        getContentPane().add(jPanel26);
        jPanel26.setBounds(630, 510, 250, 180);

        tabsState3.setName("mainTabs"); // NOI18N

        UAMTab.setLayout(null);

        jButton124.setText("Get Latest List");
        jButton124.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton124ActionPerformed(evt);
            }
        });
        UAMTab.add(jButton124);
        jButton124.setBounds(80, 150, 190, 36);

        jLabel36.setFont(jLabel36.getFont());
        jLabel36.setForeground(new java.awt.Color(0, 102, 102));
        jLabel36.setText("( Help can be found under the \"Help\" tab, or detailed help can be found at http://alcugs.almlys.org/Drizzle  )");
        UAMTab.add(jLabel36);
        jLabel36.setBounds(20, 10, 830, 16);

        jScrollPane6.setViewportView(jList1);

        UAMTab.add(jScrollPane6);
        jScrollPane6.setBounds(30, 190, 240, 210);

        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane7.setViewportView(jList2);

        UAMTab.add(jScrollPane7);
        jScrollPane7.setBounds(280, 210, 210, 110);

        jList3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane12.setViewportView(jList3);

        UAMTab.add(jScrollPane12);
        jScrollPane12.setBounds(280, 350, 210, 50);

        jLabel38.setText("Ages:");
        UAMTab.add(jLabel38);
        jLabel38.setBounds(30, 160, 70, 16);

        jLabel39.setText("Versions:");
        UAMTab.add(jLabel39);
        jLabel39.setBounds(280, 190, 160, 16);

        jButton127.setText("Download");
        jButton127.setEnabled(false);
        jButton127.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton127ActionPerformed(evt);
            }
        });
        UAMTab.add(jButton127);
        jButton127.setBounds(490, 260, 100, 36);

        jButton129.setText("Delete");
        jButton129.setEnabled(false);
        jButton129.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton129ActionPerformed(evt);
            }
        });
        UAMTab.add(jButton129);
        jButton129.setBounds(490, 290, 100, 36);

        jLabel40.setText("Mirrors:");
        UAMTab.add(jLabel40);
        jLabel40.setBounds(280, 330, 120, 16);

        jPanel42.setBorder(javax.swing.BorderFactory.createTitledBorder("UAM Settings"));
        jPanel42.setLayout(null);

        jLabel35.setText("Server:");
        jPanel42.add(jLabel35);
        jLabel35.setBounds(10, 30, 70, 16);

        textfieldState38.setText("http://www.the-ancient-city.de/uru-ages/");
        textfieldState38.setEnabled(false);
        textfieldState38.setName("uamServer"); // NOI18N
        textfieldState38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textfieldState38ActionPerformed(evt);
            }
        });
        jPanel42.add(textfieldState38);
        textfieldState38.setBounds(120, 30, 270, 20);

        jButton125.setText("Select...");
        jButton125.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton125ActionPerformed(evt);
            }
        });
        jPanel42.add(jButton125);
        jButton125.setBounds(450, 50, 110, 36);

        textfieldState39.setText("C:\\Program Files\\Ubi Soft\\Cyan Worlds\\");
            textfieldState39.setName("uamRoot"); // NOI18N
            jPanel42.add(textfieldState39);
            textfieldState39.setBounds(120, 60, 320, 20);

            jLabel37.setText("Pots folder:");
            jPanel42.add(jLabel37);
            jLabel37.setBounds(10, 60, 100, 16);

            jButton136.setText("Change server...");
            jButton136.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton136ActionPerformed(evt);
                }
            });
            jPanel42.add(jButton136);
            jButton136.setBounds(400, 20, 160, 36);

            UAMTab.add(jPanel42);
            jPanel42.setBounds(10, 40, 570, 100);

            jButton135.setText("Launch Uru");
            jButton135.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton135ActionPerformed(evt);
                }
            });
            UAMTab.add(jButton135);
            jButton135.setBounds(830, 320, 140, 36);

            jPanel43.setBorder(javax.swing.BorderFactory.createTitledBorder("Legend"));
            jPanel43.setLayout(null);

            jLabel46.setForeground(new java.awt.Color(0, 119, 0));
            jLabel46.setText("You have the latest version.");
            jPanel43.add(jLabel46);
            jLabel46.setBounds(30, 20, 340, 16);

            jLabel47.setForeground(new java.awt.Color(119, 119, 0));
            jLabel47.setText("You have a version, but not the latest.");
            jPanel43.add(jLabel47);
            jLabel47.setBounds(30, 40, 330, 16);

            jLabel48.setForeground(new java.awt.Color(119, 0, 0));
            jLabel48.setText("You don't have this Age installed.");
            jPanel43.add(jLabel48);
            jLabel48.setBounds(30, 60, 270, 16);

            jLabel49.setText("You have this Age, but its version is unknown.");
            jPanel43.add(jLabel49);
            jLabel49.setBounds(30, 80, 340, 16);

            imagePanel3.setImageFile("/gui/dashred.png");
            imagePanel3.setResize(true);

            javax.swing.GroupLayout imagePanel3Layout = new javax.swing.GroupLayout(imagePanel3);
            imagePanel3.setLayout(imagePanel3Layout);
            imagePanel3Layout.setHorizontalGroup(
                imagePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 16, Short.MAX_VALUE)
            );
            imagePanel3Layout.setVerticalGroup(
                imagePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 16, Short.MAX_VALUE)
            );

            jPanel43.add(imagePanel3);
            imagePanel3.setBounds(10, 60, 16, 16);

            imagePanel4.setImageFile("/gui/check.png");
            imagePanel4.setResize(true);

            javax.swing.GroupLayout imagePanel4Layout = new javax.swing.GroupLayout(imagePanel4);
            imagePanel4.setLayout(imagePanel4Layout);
            imagePanel4Layout.setHorizontalGroup(
                imagePanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 16, Short.MAX_VALUE)
            );
            imagePanel4Layout.setVerticalGroup(
                imagePanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 16, Short.MAX_VALUE)
            );

            jPanel43.add(imagePanel4);
            imagePanel4.setBounds(10, 20, 16, 16);

            imagePanel5.setImageFile("/gui/up.png");
            imagePanel5.setResize(true);

            javax.swing.GroupLayout imagePanel5Layout = new javax.swing.GroupLayout(imagePanel5);
            imagePanel5.setLayout(imagePanel5Layout);
            imagePanel5Layout.setHorizontalGroup(
                imagePanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 16, Short.MAX_VALUE)
            );
            imagePanel5Layout.setVerticalGroup(
                imagePanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 16, Short.MAX_VALUE)
            );

            jPanel43.add(imagePanel5);
            imagePanel5.setBounds(10, 40, 16, 16);

            imagePanel1.setImageFile("/gui/unknown.png");
            imagePanel1.setResize(true);

            javax.swing.GroupLayout imagePanel1Layout = new javax.swing.GroupLayout(imagePanel1);
            imagePanel1.setLayout(imagePanel1Layout);
            imagePanel1Layout.setHorizontalGroup(
                imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 16, Short.MAX_VALUE)
            );
            imagePanel1Layout.setVerticalGroup(
                imagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 16, Short.MAX_VALUE)
            );

            jPanel43.add(imagePanel1);
            imagePanel1.setBounds(10, 80, 16, 16);

            UAMTab.add(jPanel43);
            jPanel43.setBounds(590, 150, 380, 110);

            jPanel44.setBorder(javax.swing.BorderFactory.createTitledBorder("Automatically download list?"));
            jPanel44.setLayout(null);

            uamStartupButtongroup.add(jRadioButton5);
            jRadioButton5.setSelected(true);
            jRadioButton5.setText("Do not load Age list at startup.");
            jPanel44.add(jRadioButton5);
            jRadioButton5.setBounds(10, 30, 360, 20);

            uamStartupButtongroup.add(jRadioButton6);
            jRadioButton6.setText("Load last saved Age list at startup.");
            jPanel44.add(jRadioButton6);
            jRadioButton6.setBounds(10, 50, 360, 20);

            uamStartupButtongroup.add(jRadioButton7);
            jRadioButton7.setText("Download most recent Age list at startup.");
            jPanel44.add(jRadioButton7);
            jRadioButton7.setBounds(10, 70, 360, 20);

            UAMTab.add(jPanel44);
            jPanel44.setBounds(590, 40, 380, 100);

            jLabel50.setText("Info:");
            UAMTab.add(jLabel50);
            jLabel50.setBounds(30, 410, 40, 16);

            ageInfo3.setText("(Select an Age, or click \"Get Latest List\" to get the latest list of Ages.)");
            ageInfo3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            ageInfo3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
            UAMTab.add(ageInfo3);
            ageInfo3.setBounds(60, 410, 910, 40);

            tabsState3.addTab("UruAgeManager(UAM)", UAMTab);

            SimpTab.setLayout(null);

            tabsState1.setName("simplicitySubTabs"); // NOI18N

            jLabel31.setText("Moul folder:");

            jLabel32.setText("Pots folder:");

            textfieldState33.setName("moulSimplicityIn"); // NOI18N

            textfieldState34.setName("moulSimplicityOut"); // NOI18N

            jButton114.setText("Start...");
            jButton114.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton114ActionPerformed(evt);
                }
            });

            jButton115.setText("select...");
            jButton115.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton115ActionPerformed(evt);
                }
            });

            jButton116.setText("select...");
            jButton116.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton116ActionPerformed(evt);
                }
            });

            jButton141.setText("SoundDecompress (When conversion is done, it will ask you to run this.)");
            jButton141.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton141ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
            jPanel35.setLayout(jPanel35Layout);
            jPanel35Layout.setHorizontalGroup(
                jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton114)
                        .addGroup(jPanel35Layout.createSequentialGroup()
                            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel31)
                                .addComponent(jLabel32))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textfieldState34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textfieldState33, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton115)
                                .addComponent(jButton116))))
                    .addContainerGap(488, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                    .addContainerGap(323, Short.MAX_VALUE)
                    .addComponent(jButton141, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(27, 27, 27))
            );
            jPanel35Layout.setVerticalGroup(
                jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31)
                        .addComponent(textfieldState33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton115))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(textfieldState34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton116))
                    .addGap(18, 18, 18)
                    .addComponent(jButton114)
                    .addGap(141, 141, 141)
                    .addComponent(jButton141)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            tabsState1.addTab("Moul", jPanel35);

            jPanel34.setLayout(null);

            jLabel19.setText("Myst 5 Folder:");
            jPanel34.add(jLabel19);
            jLabel19.setBounds(10, 30, 120, 16);

            textfieldState22.setName("simpleMystvIn"); // NOI18N
            jPanel34.add(textfieldState22);
            textfieldState22.setBounds(140, 30, 340, 20);

            jLabel20.setText("Pots Folder:");
            jPanel34.add(jLabel20);
            jLabel20.setBounds(10, 70, 120, 16);

            textfieldState23.setName("simpleMystvOut"); // NOI18N
            jPanel34.add(textfieldState23);
            textfieldState23.setBounds(140, 70, 340, 20);

            jButton96.setText("Start...");
            jButton96.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton96ActionPerformed(evt);
                }
            });
            jPanel34.add(jButton96);
            jButton96.setBounds(30, 110, 90, 36);

            jButton97.setText("select...");
            jButton97.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton97ActionPerformed(evt);
                }
            });
            jPanel34.add(jButton97);
            jButton97.setBounds(480, 20, 120, 36);

            jButton98.setText("select...");
            jButton98.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton98ActionPerformed(evt);
                }
            });
            jPanel34.add(jButton98);
            jButton98.setBounds(480, 60, 120, 36);

            jButton140.setText("SoundDecompress (When conversion is done, it will ask you to run this.)");
            jButton140.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton140ActionPerformed(evt);
                }
            });
            jPanel34.add(jButton140);
            jButton140.setBounds(340, 300, 600, 36);

            tabsState1.addTab("MystV", jPanel34);

            jLabel41.setText("Crowthistle folder:");

            textfieldState40.setName("crowSimpleIn"); // NOI18N

            textfieldState41.setName("crowSimpleOut"); // NOI18N

            jLabel42.setText("Pots folder:");

            jButton130.setText("Start...");
            jButton130.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton130ActionPerformed(evt);
                }
            });

            jButton131.setText("select...");
            jButton131.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton131ActionPerformed(evt);
                }
            });

            jButton132.setText("select...");
            jButton132.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton132ActionPerformed(evt);
                }
            });

            jButton142.setText("SoundDecompress (When conversion is done, it will ask you to run this.)");
            jButton142.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton142ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
            jPanel40.setLayout(jPanel40Layout);
            jPanel40Layout.setHorizontalGroup(
                jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel40Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton130)
                        .addGroup(jPanel40Layout.createSequentialGroup()
                            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel41)
                                .addComponent(jLabel42))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(textfieldState40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textfieldState41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton132)
                                .addComponent(jButton131))))
                    .addContainerGap(377, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                    .addContainerGap(324, Short.MAX_VALUE)
                    .addComponent(jButton142, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            jPanel40Layout.setVerticalGroup(
                jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel40Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel41)
                        .addComponent(textfieldState40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton131))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel42)
                        .addComponent(textfieldState41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton132))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton130)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                    .addComponent(jButton142)
                    .addContainerGap())
            );

            tabsState1.addTab("Crowthistle", jPanel40);

            jPanel5.setLayout(null);

            jLabel58.setForeground(new java.awt.Color(0, 102, 102));
            jLabel58.setText("<html>Fill out the info in the MystV, Moul, and Crowthistle tabs, then click these buttons to put their music in your Relto's musicbox.</html>");
            jLabel58.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            jPanel5.add(jLabel58);
            jLabel58.setBounds(20, 20, 770, 40);

            jButton30.setText("MystV music...");
            jButton30.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton30ActionPerformed(evt);
                }
            });
            jPanel5.add(jButton30);
            jButton30.setBounds(20, 60, 170, 36);

            jButton31.setText("Moul music...");
            jButton31.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton31ActionPerformed(evt);
                }
            });
            jPanel5.add(jButton31);
            jButton31.setBounds(20, 100, 170, 36);

            jButton34.setText("Crowthistle music...");
            jButton34.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton34ActionPerformed(evt);
                }
            });
            jPanel5.add(jButton34);
            jButton34.setBounds(20, 140, 170, 36);

            jButton36.setText("Pots music...");
            jButton36.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton36ActionPerformed(evt);
                }
            });
            jPanel5.add(jButton36);
            jButton36.setBounds(20, 180, 170, 36);

            jLabel59.setForeground(new java.awt.Color(0, 102, 102));
            jLabel59.setText("(Make sure you fill out the 'Pots Folder' on the Moul tab for this one.)");
            jPanel5.add(jLabel59);
            jLabel59.setBounds(190, 190, 750, 16);

            tabsState1.addTab("Music", jPanel5);

            SimpTab.add(tabsState1);
            tabsState1.setBounds(10, 72, 970, 380);

            jLabel63.setFont(jLabel63.getFont());
            jLabel63.setForeground(new java.awt.Color(0, 102, 102));
            jLabel63.setText("<html>This area is for converting MystV/Moul/Crowthistle files for play in Uru.  See the Help tab or http://alcugs.almlys.org/Drizzle</html>");
            jLabel63.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            SimpTab.add(jLabel63);
            jLabel63.setBounds(20, 20, 920, 40);

            tabsState3.addTab("Conversion(Simplicity)", SimpTab);

            textfieldState36.setName("vaultInFolder"); // NOI18N

            jButton121.setText("Select...");
            jButton121.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton121ActionPerformed(evt);
                }
            });

            jButton122.setText("Start...");
            jButton122.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton122ActionPerformed(evt);
                }
            });

            jLabel33.setText("infolder:");

            jLabel34.setText("outfolder:");

            textfieldState37.setName("vaultOutFolder"); // NOI18N
            textfieldState37.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    textfieldState37ActionPerformed(evt);
                }
            });

            jButton123.setText("Select...");
            jButton123.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton123ActionPerformed(evt);
                }
            });

            jLabel30.setFont(jLabel30.getFont());
            jLabel30.setForeground(new java.awt.Color(0, 102, 102));
            jLabel30.setText("Memories: this area is for extracting images, notes, etc. from Uru (either offline or online)");

            jLabel43.setForeground(new java.awt.Color(0, 102, 102));
            jLabel43.setText("Instructions can be found on the Help tab or at  http://alcugs.almlys.org/Drizzle");

            javax.swing.GroupLayout MemTabLayout = new javax.swing.GroupLayout(MemTab);
            MemTab.setLayout(MemTabLayout);
            MemTabLayout.setHorizontalGroup(
                MemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MemTabLayout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addGroup(MemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel43)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(MemTabLayout.createSequentialGroup()
                            .addGroup(MemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel34)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(6, 6, 6)
                            .addGroup(MemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textfieldState36, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                                .addComponent(textfieldState37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton122))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(MemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton123)
                                .addComponent(jButton121))))
                    .addContainerGap(183, Short.MAX_VALUE))
            );
            MemTabLayout.setVerticalGroup(
                MemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MemTabLayout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(jLabel30)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel43)
                    .addGap(94, 94, 94)
                    .addGroup(MemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33)
                        .addComponent(textfieldState36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton121))
                    .addGap(18, 18, 18)
                    .addGroup(MemTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(textfieldState37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton123))
                    .addGap(18, 18, 18)
                    .addComponent(jButton122)
                    .addContainerGap(145, Short.MAX_VALUE))
            );

            tabsState3.addTab("Memories", MemTab);

            jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Pods"));

            jTextField5.setText("Feb 15, 1982 8:00 AM CST");

            jButton42.setText("Predict Pod events");
            jButton42.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton42ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
            jPanel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton42)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(38, Short.MAX_VALUE))
            );
            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton42)
                    .addContainerGap(53, Short.MAX_VALUE))
            );

            jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Minkata"));

            jButton35.setText("sum...");
            jButton35.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton35ActionPerformed(evt);
                }
            });

            jTextField2.setEditable(false);

            jLabel4.setForeground(new java.awt.Color(0, 102, 102));
            jLabel4.setText("Enter a list of angles(20=full circle) and distances.  They will be summed as vectors.");

            jTextArea3.setColumns(20);
            jTextArea3.setRows(5);
            jTextArea3.setText("//angle1,dist1\n//angle2,dist2\n\n//e.g.:\n//8.5,360\n//1,556\n//2,442\n//15,420\n\n//15,1386\n//11,274\n//11,164\n//17,269\n//11.25,237\n//10,807\n//16.5,91\n//15,1049\n//1,50\n//15,41\n//9,50\n//7,524\n//0,1154\n//2,629\n\n//0.25,780\n//1,2052\n//7,825\n//10,866\n//2,105\n//10,2212\n//11.25,1245\n//15,748\n//13,429\n//16.25,315\n//10.5,264\n//15,461\n//15,652\n\n//2.75,109\n//0,1259\n//14,210\n//9,196\n//15,415\n//17,160\n//15,360\n//13,251\n//17.25,424\n\n//5,835\n//5,342\n//10,369\n//5,538\n//2.25,205\n//5,205\n//7.5,511\n");
            jScrollPane11.setViewportView(jTextArea3);

            javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
            jPanel11.setLayout(jPanel11Layout);
            jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jButton35)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton35)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jButton43.setText("PtGetDniTime");
            jButton43.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton43ActionPerformed(evt);
                }
            });

            textareaState1.setColumns(20);
            textareaState1.setRows(5);
            textareaState1.setName("notes"); // NOI18N
            jScrollPane14.setViewportView(textareaState1);

            jLabel45.setText("Notes:");

            javax.swing.GroupLayout GHelpTabLayout = new javax.swing.GroupLayout(GHelpTab);
            GHelpTab.setLayout(GHelpTabLayout);
            GHelpTabLayout.setHorizontalGroup(
                GHelpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(GHelpTabLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(GHelpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(GHelpTabLayout.createSequentialGroup()
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(142, 142, 142)
                            .addComponent(jButton43)))
                    .addGroup(GHelpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(GHelpTabLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(jLabel45))
                        .addGroup(GHelpTabLayout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(168, Short.MAX_VALUE))
            );
            GHelpTabLayout.setVerticalGroup(
                GHelpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(GHelpTabLayout.createSequentialGroup()
                    .addGroup(GHelpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, GHelpTabLayout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(jLabel45)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane14))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, GHelpTabLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(GHelpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(GHelpTabLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(GHelpTabLayout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addComponent(jButton43)))
                    .addContainerGap(92, Short.MAX_VALUE))
            );

            tabsState3.addTab("GameHelp", GHelpTab);

            ProxyTab.setLayout(null);

            jButton70.setText("Start Server");
            jButton70.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton70ActionPerformed(evt);
                }
            });
            ProxyTab.add(jButton70);
            jButton70.setBounds(140, 240, 170, 36);

            textfieldState9.setText("C:\\Documents and Settings\\user\\Desktop\\web");
            textfieldState9.setName("proxyFolder"); // NOI18N
            ProxyTab.add(textfieldState9);
            textfieldState9.setBounds(150, 90, 370, 20);

            jLabel15.setText("base folder:");
            ProxyTab.add(jLabel15);
            jLabel15.setBounds(10, 90, 120, 16);

            jLabel16.setText("port:");
            ProxyTab.add(jLabel16);
            jLabel16.setBounds(10, 120, 100, 16);

            textfieldState10.setText("8012");
            textfieldState10.setName("proxyPort"); // NOI18N
            ProxyTab.add(textfieldState10);
            textfieldState10.setBounds(150, 120, 100, 20);

            jButton71.setText("Stop Server");
            jButton71.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton71ActionPerformed(evt);
                }
            });
            ProxyTab.add(jButton71);
            jButton71.setBounds(310, 240, 170, 36);

            checkboxState10.setText("Indicate in the log when files are read?");
            checkboxState10.setName("proxyLogReads"); // NOI18N
            ProxyTab.add(checkboxState10);
            checkboxState10.setBounds(250, 170, 710, 28);

            jLabel17.setForeground(new java.awt.Color(0, 102, 102));
            jLabel17.setText("You have to stop and start the server for settings changes to take effect.");
            ProxyTab.add(jLabel17);
            jLabel17.setBounds(150, 280, 720, 16);

            jButton77.setText("select...");
            jButton77.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton77ActionPerformed(evt);
                }
            });
            ProxyTab.add(jButton77);
            jButton77.setBounds(520, 80, 120, 36);

            jLabel52.setFont(jLabel52.getFont());
            jLabel52.setForeground(new java.awt.Color(0, 102, 102));
            jLabel52.setText("This proxy server is for viewing old archived web sites.  Details on the help tab or  http://alcugs.almlys.org/Drizzle");
            ProxyTab.add(jLabel52);
            jLabel52.setBounds(20, 10, 940, 40);

            tabsState3.addTab("Proxy", ProxyTab);

            MiscPanel.setLayout(null);

            jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder("UAM Advanced"));
            jPanel41.setLayout(null);

            jButton126.setText("List Available Ages...");
            jButton126.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton126ActionPerformed(evt);
                }
            });
            jPanel41.add(jButton126);
            jButton126.setBounds(10, 180, 240, 36);

            jButton137.setText("Get Offline list.");
            jButton137.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton137ActionPerformed(evt);
                }
            });
            jPanel41.add(jButton137);
            jButton137.setBounds(10, 210, 240, 36);

            jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Sanity check"));
            jPanel6.setLayout(null);

            checkboxState25.setSelected(true);
            checkboxState25.setText("ignore known overrides");
            checkboxState25.setName("uamig"); // NOI18N
            jPanel6.add(checkboxState25);
            checkboxState25.setBounds(20, 50, 240, 28);

            jButton139.setText("Run Tests...");
            jButton139.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton139ActionPerformed(evt);
                }
            });
            jPanel6.add(jButton139);
            jButton139.setBounds(10, 20, 130, 36);

            jPanel41.add(jPanel6);
            jPanel6.setBounds(130, 260, 300, 90);

            jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("uam.status.xml"));
            jPanel7.setLayout(null);

            jButton134.setText("Select...");
            jButton134.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton134ActionPerformed(evt);
                }
            });
            jPanel7.add(jButton134);
            jButton134.setBounds(300, 10, 110, 36);

            textfieldState42.setText("textfieldState42");
            textfieldState42.setName("genFolder"); // NOI18N
            jPanel7.add(textfieldState42);
            textfieldState42.setBounds(130, 20, 170, 20);

            jButton133.setText("Generate uam.status.txt");
            jButton133.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton133ActionPerformed(evt);
                }
            });
            jPanel7.add(jButton133);
            jButton133.setBounds(10, 40, 180, 36);

            jLabel44.setText("archive folder:");
            jPanel7.add(jLabel44);
            jLabel44.setBounds(10, 20, 120, 16);

            jPanel41.add(jPanel7);
            jPanel7.setBounds(10, 60, 420, 80);

            jButton15.setText("Name a random Age!");
            jButton15.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton15ActionPerformed(evt);
                }
            });
            jPanel41.add(jButton15);
            jButton15.setBounds(10, 150, 240, 36);

            jPanel46.setBorder(javax.swing.BorderFactory.createTitledBorder("Age reports"));
            jPanel46.setLayout(null);

            textfieldState45.setText("EderRiltahInaltahv");
            textfieldState45.setName("ageReportPath"); // NOI18N
            jPanel46.add(textfieldState45);
            textfieldState45.setBounds(140, 20, 150, 20);

            jButton145.setText("Report");
            jButton145.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton145ActionPerformed(evt);
                }
            });
            jPanel46.add(jButton145);
            jButton145.setBounds(210, 40, 80, 36);

            jLabel56.setText("Agename:");
            jPanel46.add(jLabel56);
            jLabel56.setBounds(20, 20, 110, 16);

            jPanel41.add(jPanel46);
            jPanel46.setBounds(130, 350, 300, 80);

            jLabel62.setFont(jLabel62.getFont());
            jLabel62.setForeground(new java.awt.Color(0, 102, 102));
            jLabel62.setText("Everything here uses the Pots folder from the UAM tab.");
            jPanel41.add(jLabel62);
            jLabel62.setBounds(20, 30, 410, 16);

            MiscPanel.add(jPanel41);
            jPanel41.setBounds(400, 10, 440, 440);

            jPanel47.setBorder(javax.swing.BorderFactory.createTitledBorder("Wiki spider"));

            textfieldState46.setText("start url");
            textfieldState46.setName("wspurl"); // NOI18N

            textfieldState47.setText("c:\\place\\to\\save");
            textfieldState47.setName("wspout"); // NOI18N
            textfieldState47.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    textfieldState47ActionPerformed(evt);
                }
            });

            jButton146.setText("start");
            jButton146.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton146ActionPerformed(evt);
                }
            });

            jButton147.setText("select...");
            jButton147.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton147ActionPerformed(evt);
                }
            });

            jLabel54.setText("Url:");

            jLabel55.setText("outfolder:");

            javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
            jPanel47.setLayout(jPanel47Layout);
            jPanel47Layout.setHorizontalGroup(
                jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel47Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel55)
                        .addComponent(jLabel54))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton146)
                        .addGroup(jPanel47Layout.createSequentialGroup()
                            .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(textfieldState46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textfieldState47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton147)))
                    .addGap(39, 39, 39))
            );
            jPanel47Layout.setVerticalGroup(
                jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel47Layout.createSequentialGroup()
                    .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textfieldState46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel54))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textfieldState47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel55)
                        .addComponent(jButton147))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton146)
                    .addContainerGap(8, Short.MAX_VALUE))
            );

            MiscPanel.add(jPanel47);
            jPanel47.setBounds(10, 10, 370, 140);

            jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("How rainy of a day is it?"));

            sliderState1.setMinimum(20);
            sliderState1.setValue(76);
            sliderState1.setName("raininess"); // NOI18N
            sliderState1.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    sliderState1StateChanged(evt);
                }
            });

            javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
            jPanel25.setLayout(jPanel25Layout);
            jPanel25Layout.setHorizontalGroup(
                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(sliderState1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE))
            );
            jPanel25Layout.setVerticalGroup(
                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(sliderState1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(40, Short.MAX_VALUE))
            );

            MiscPanel.add(jPanel25);
            jPanel25.setBounds(20, 240, 330, 110);

            tabsState3.addTab("Misc", MiscPanel);

            AdvTabState.setName("subadv"); // NOI18N

            jPanel48.setLayout(null);

            jPanel31.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            jPanel31.setLayout(null);

            textfieldState12.setText("textfieldState12");
            textfieldState12.setName("logFile"); // NOI18N
            jPanel31.add(textfieldState12);
            textfieldState12.setBounds(10, 20, 87, 20);

            jButton74.setText("jButton74");
            jPanel31.add(jButton74);
            jButton74.setBounds(100, 20, 76, 36);

            jButton75.setText("jButton75");
            jPanel31.add(jButton75);
            jButton75.setBounds(10, 56, 80, 40);

            jButton76.setText("jButton76");
            jPanel31.add(jButton76);
            jButton76.setBounds(100, 60, 76, 36);

            jPanel48.add(jPanel31);
            jPanel31.setBounds(20, 10, 200, 100);

            jButton87.setText("Copy all text to clipboard");
            jButton87.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton87ActionPerformed(evt);
                }
            });
            jPanel48.add(jButton87);
            jButton87.setBounds(190, 60, 160, 36);

            jButton72.setText("copy from main output");
            jButton72.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton72ActionPerformed(evt);
                }
            });
            jPanel48.add(jButton72);
            jButton72.setBounds(230, 10, 150, 36);

            jPanel30.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            jPanel30.setLayout(null);

            jLabel18.setText("Prefix:");
            jPanel30.add(jLabel18);
            jLabel18.setBounds(10, 10, 33, 16);

            textfieldState11.setText("textfieldState11");
            textfieldState11.setName("logPrefix"); // NOI18N
            jPanel30.add(textfieldState11);
            textfieldState11.setBounds(70, 10, 160, 20);

            jButton73.setText("remove all that don't start with prefix");
            jPanel30.add(jButton73);
            jButton73.setBounds(10, 40, 220, 36);

            jPanel48.add(jPanel30);
            jPanel30.setBounds(310, 20, 240, 80);

            jTextArea6.setColumns(20);
            jTextArea6.setRows(5);
            jScrollPane1.setViewportView(jTextArea6);

            jPanel48.add(jScrollPane1);
            jScrollPane1.setBounds(30, 130, 770, 250);

            AdvTabState.addTab("List Analysis", jPanel48);

            jPanel4.setLayout(null);

            jButton23.setText("delete marked entities");
            jButton23.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton23ActionPerformed(evt);
                }
            });
            jPanel4.add(jButton23);
            jButton23.setBounds(650, 220, 170, 36);

            jButton22.setText("clear all marks");
            jButton22.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton22ActionPerformed(evt);
                }
            });
            jPanel4.add(jButton22);
            jButton22.setBounds(700, 100, 120, 36);

            jButton29.setText("mark links");
            jButton29.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton29ActionPerformed(evt);
                }
            });
            jPanel4.add(jButton29);
            jButton29.setBounds(710, 130, 110, 36);

            jButton24.setText("mark links from selected");
            jButton24.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton24ActionPerformed(evt);
                }
            });
            jPanel4.add(jButton24);
            jButton24.setBounds(670, 160, 150, 36);

            jButton25.setText("mark all that start with STRING");
            jButton25.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton25ActionPerformed(evt);
                }
            });
            jPanel4.add(jButton25);
            jButton25.setBounds(630, 310, 191, 36);

            jButton28.setText("load");
            jButton28.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton28ActionPerformed(evt);
                }
            });
            jPanel4.add(jButton28);
            jButton28.setBounds(760, 40, 80, 36);

            jButton27.setText("mark links to selected");
            jButton27.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton27ActionPerformed(evt);
                }
            });
            jPanel4.add(jButton27);
            jButton27.setBounds(650, 190, 170, 36);

            jButton26.setText("... end with STRING");
            jButton26.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton26ActionPerformed(evt);
                }
            });
            jPanel4.add(jButton26);
            jButton26.setBounds(660, 340, 160, 36);

            crossRefPanel1.setBackground(new java.awt.Color(153, 255, 153));
            crossRefPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

            javax.swing.GroupLayout crossRefPanel1Layout = new javax.swing.GroupLayout(crossRefPanel1);
            crossRefPanel1.setLayout(crossRefPanel1Layout);
            crossRefPanel1Layout.setHorizontalGroup(
                crossRefPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 558, Short.MAX_VALUE)
            );
            crossRefPanel1Layout.setVerticalGroup(
                crossRefPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 398, Short.MAX_VALUE)
            );

            jPanel4.add(crossRefPanel1);
            crossRefPanel1.setBounds(10, 10, 560, 400);

            textfieldState50.setText("textfieldState50");
            textfieldState50.setName("creffile"); // NOI18N
            jPanel4.add(textfieldState50);
            textfieldState50.setBounds(620, 20, 130, 20);

            jButton19.setText("select...");
            jButton19.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton19ActionPerformed(evt);
                }
            });
            jPanel4.add(jButton19);
            jButton19.setBounds(746, 10, 110, 36);

            jLabel57.setText("file:");
            jPanel4.add(jLabel57);
            jLabel57.setBounds(580, 20, 40, 16);

            textfieldState51.setText("textfieldState51");
            textfieldState51.setName("crstr"); // NOI18N
            jPanel4.add(textfieldState51);
            textfieldState51.setBounds(620, 280, 87, 20);

            AdvTabState.addTab("CrossRefTool", jPanel4);

            jButton13.setText("Make prp report");
            jButton13.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton13ActionPerformed(evt);
                }
            });

            jButton14.setText("Dump some objects");
            jButton14.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton14ActionPerformed(evt);
                }
            });

            jButton17.setText("Recompile prp");
            jButton17.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton17ActionPerformed(evt);
                }
            });

            jButton21.setText("Find Objects of a certain type");
            jButton21.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton21ActionPerformed(evt);
                }
            });

            jButton32.setText("convert from xml escaped text (TEXT:)");
            jButton32.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton32ActionPerformed(evt);
                }
            });

            jButton33.setText("convert to escaped double quotes (TEXT:)");
            jButton33.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton33ActionPerformed(evt);
                }
            });

            jLabel3.setText("Use these to convert text from .loc files to an escaped string that can be used by Python:");

            jButton44.setText("Dump All Objects");
            jButton44.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton44ActionPerformed(evt);
                }
            });

            jButton48.setText("create .sum file");
            jButton48.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton48ActionPerformed(evt);
                }
            });

            textfieldState48.setText("textfieldState48");
            textfieldState48.setName("oldfile"); // NOI18N

            textfieldState49.setText("textfieldState49");
            textfieldState49.setName("oldout"); // NOI18N

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(174, 174, 174)
                                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton48))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton44))
                                        .addComponent(jButton21)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(241, 241, 241)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(6, 6, 6)
                                                    .addComponent(textfieldState49, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(textfieldState48, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(101, 101, 101)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton32)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton33))
                                .addComponent(jLabel3))))
                    .addContainerGap(395, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton13)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton14)
                        .addComponent(jButton44))
                    .addGap(22, 22, 22)
                    .addComponent(textfieldState48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton17)
                        .addComponent(textfieldState49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton21))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addComponent(jButton48)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton32)
                        .addComponent(jButton33))
                    .addGap(42, 42, 42))
            );

            AdvTabState.addTab("Old", jPanel1);

            jPanel49.setLayout(null);

            jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder("Chat"));

            jButton143.setText("jButton143");
            jButton143.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton143ActionPerformed(evt);
                }
            });

            textfieldState44.setText("textfieldState44");
            textfieldState44.setName("chatmsg"); // NOI18N

            javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
            jPanel45.setLayout(jPanel45Layout);
            jPanel45Layout.setHorizontalGroup(
                jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel45Layout.createSequentialGroup()
                    .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel45Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(textfieldState44, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton143))
                    .addContainerGap(52, Short.MAX_VALUE))
            );
            jPanel45Layout.setVerticalGroup(
                jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textfieldState44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton143)
                    .addGap(34, 34, 34))
            );

            jPanel49.add(jPanel45);
            jPanel45.setBounds(40, 30, 288, 130);

            textfieldState43.setText("defaulttest");
            textfieldState43.setName("wha"); // NOI18N
            jPanel49.add(textfieldState43);
            textfieldState43.setBounds(90, 200, 61, 20);

            jPanel36.setBorder(javax.swing.BorderFactory.createEtchedBorder());
            jPanel36.setLayout(null);

            jButton105.setText("Check(url,timer,search)");
            jButton105.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton105ActionPerformed(evt);
                }
            });
            jPanel36.add(jButton105);
            jButton105.setBounds(10, 210, 180, 36);

            textfieldState26.setText("http://example.com/page.html");
            textfieldState26.setName("address"); // NOI18N
            jPanel36.add(textfieldState26);
            textfieldState26.setBounds(90, 10, 210, 20);

            textfieldState27.setText("60");
            textfieldState27.setName("updateTime"); // NOI18N
            jPanel36.add(textfieldState27);
            textfieldState27.setBounds(90, 40, 70, 20);

            textfieldState28.setText("c:\\path\\to\\outfolder");
            textfieldState28.setName("searchnet"); // NOI18N
            jPanel36.add(textfieldState28);
            textfieldState28.setBounds(90, 70, 210, 20);

            jLabel1.setText("url:");
            jPanel36.add(jLabel1);
            jLabel1.setBounds(10, 10, 50, 16);

            jLabel2.setText("timer(s)");
            jPanel36.add(jLabel2);
            jLabel2.setBounds(10, 40, 70, 16);

            jButton16.setText("Start saving(url,timer,outfolder)");
            jButton16.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton16ActionPerformed(evt);
                }
            });
            jPanel36.add(jButton16);
            jButton16.setBounds(10, 180, 210, 36);

            jButton18.setText("Cancel All");
            jButton18.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton18ActionPerformed(evt);
                }
            });
            jPanel36.add(jButton18);
            jButton18.setBounds(260, 150, 80, 36);

            jLabel51.setText("outfolder");
            jPanel36.add(jLabel51);
            jLabel51.setBounds(10, 70, 70, 16);

            textfieldState52.setText("textfieldState52");
            textfieldState52.setName("tmsrch"); // NOI18N
            jPanel36.add(textfieldState52);
            textfieldState52.setBounds(90, 100, 210, 20);

            textfieldState53.setText("Mar 14 2009, 2:05:00 PM");
            textfieldState53.setName("tmtime"); // NOI18N
            jPanel36.add(textfieldState53);
            textfieldState53.setBounds(90, 130, 210, 20);

            jLabel60.setText("search");
            jPanel36.add(jLabel60);
            jLabel60.setBounds(10, 100, 38, 16);

            jLabel61.setText("date & time:");
            jPanel36.add(jLabel61);
            jLabel61.setBounds(10, 130, 80, 16);

            jButton37.setText("Save at time(url, outfolder, date&time)");
            jButton37.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton37ActionPerformed(evt);
                }
            });
            jPanel36.add(jButton37);
            jButton37.setBounds(10, 240, 250, 36);

            jPanel49.add(jPanel36);
            jPanel36.setBounds(350, 10, 350, 300);

            jButton1.setText("Run Code Validation");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
            jPanel49.add(jButton1);
            jButton1.setBounds(160, 170, 170, 36);

            jButton6.setText("test!!!");
            jButton6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton6ActionPerformed(evt);
                }
            });
            jPanel49.add(jButton6);
            jButton6.setBounds(223, 210, 80, 36);

            jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Language"));
            jPanel13.setLayout(null);

            jButton38.setText("list all strings");
            jButton38.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton38ActionPerformed(evt);
                }
            });
            jPanel13.add(jButton38);
            jButton38.setBounds(0, 20, 180, 36);

            jButton40.setText("test current language");
            jButton40.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton40ActionPerformed(evt);
                }
            });
            jPanel13.add(jButton40);
            jButton40.setBounds(0, 50, 180, 36);

            jButton46.setText("Save Missing Translations");
            jButton46.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton46ActionPerformed(evt);
                }
            });
            jPanel13.add(jButton46);
            jButton46.setBounds(10, 120, 240, 36);

            jPanel49.add(jPanel13);
            jPanel13.setBounds(30, 240, 260, 170);

            AdvTabState.addTab("Misc", jPanel49);

            jButton5.setText("Decrypt notthedroids");
            jButton5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton5ActionPerformed(evt);
                }
            });

            jButton4.setText("Decrypt whatdoyousee");
            jButton4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton4ActionPerformed(evt);
                }
            });

            jButton11.setText("Decrypt elf");
            jButton11.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton11ActionPerformed(evt);
                }
            });

            jButton8.setText("Decrypt eoa");
            jButton8.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton8ActionPerformed(evt);
                }
            });

            jButton7.setText("Encrypt notthedroids");
            jButton7.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton7ActionPerformed(evt);
                }
            });

            jButton10.setText("Encrypt whatdoyousee");
            jButton10.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton10ActionPerformed(evt);
                }
            });

            jButton9.setText("Encrypt eoa");
            jButton9.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton9ActionPerformed(evt);
                }
            });

            jButton12.setText("Encrypt elf");
            jButton12.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton12ActionPerformed(evt);
                }
            });

            jButton47.setText("Calculate md5");
            jButton47.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton47ActionPerformed(evt);
                }
            });

            textfieldState15.setText("D:\\a\\leftoff\\decrypted\\sdl\\personal.sdl");
            textfieldState15.setName("encryptionIn"); // NOI18N

            textfieldState16.setText("C:\\Documents and Settings\\user\\Desktop\\output");
            textfieldState16.setName("encryptionOut"); // NOI18N

            jButton88.setText("select...");
            jButton88.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton88ActionPerformed(evt);
                }
            });

            jButton89.setText("select...");
            jButton89.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton89ActionPerformed(evt);
                }
            });

            jButton128.setText("Calculate Whirlpool");
            jButton128.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton128ActionPerformed(evt);
                }
            });

            jButton138.setText("Caclulate Sha1");
            jButton138.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton138ActionPerformed(evt);
                }
            });

            jButton3.setText("Detect Type");
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jButton5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(102, 102, 102)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton4)
                                        .addComponent(jButton8)
                                        .addComponent(jButton11)
                                        .addComponent(jButton47)
                                        .addComponent(jButton138))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jButton128))
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textfieldState16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textfieldState15, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
                            .addGap(6, 6, 6)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton88)
                                .addComponent(jButton89))))
                    .addContainerGap(458, Short.MAX_VALUE))
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textfieldState15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton88))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textfieldState16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton89))
                    .addGap(29, 29, 29)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5)
                        .addComponent(jButton7)
                        .addComponent(jButton3))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4)
                        .addComponent(jButton10))
                    .addGap(4, 4, 4)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton8)
                        .addComponent(jButton9))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11)
                        .addComponent(jButton12))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton47)
                        .addComponent(jButton128))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton138)
                    .addContainerGap(50, Short.MAX_VALUE))
            );

            AdvTabState.addTab("Encryption", jPanel3);

            jPanel10.setLayout(null);

            jButton45.setText("Depack");
            jButton45.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton45ActionPerformed(evt);
                }
            });
            jPanel10.add(jButton45);
            jButton45.setBounds(50, 120, 130, 36);

            textfieldState21.setText("textfieldState21");
            textfieldState21.setName("realmystIn"); // NOI18N
            jPanel10.add(textfieldState21);
            textfieldState21.setBounds(130, 30, 370, 20);

            jButton95.setText("select");
            jButton95.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton95ActionPerformed(evt);
                }
            });
            jPanel10.add(jButton95);
            jButton95.setBounds(500, 20, 110, 36);

            jButton101.setText("select");
            jButton101.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton101ActionPerformed(evt);
                }
            });
            jPanel10.add(jButton101);
            jButton101.setBounds(500, 70, 110, 36);

            textfieldState24.setText("textfieldState24");
            textfieldState24.setName("realmystOut"); // NOI18N
            jPanel10.add(textfieldState24);
            textfieldState24.setBounds(130, 80, 370, 20);

            jLabel25.setText("in file:");
            jPanel10.add(jLabel25);
            jLabel25.setBounds(10, 30, 100, 16);

            jLabel26.setText("out folder:");
            jPanel10.add(jLabel26);
            jLabel26.setBounds(10, 80, 110, 16);

            textfieldState25.setText("textfieldState25");
            textfieldState25.setName("realmystObjFile"); // NOI18N
            jPanel10.add(textfieldState25);
            textfieldState25.setBounds(90, 210, 340, 20);

            jButton102.setText("Attempt to read.");
            jButton102.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton102ActionPerformed(evt);
                }
            });
            jPanel10.add(jButton102);
            jButton102.setBounds(90, 250, 160, 36);

            jLabel27.setText("File:");
            jPanel10.add(jLabel27);
            jLabel27.setBounds(30, 210, 48, 16);

            jButton103.setText("select");
            jButton103.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton103ActionPerformed(evt);
                }
            });
            jPanel10.add(jButton103);
            jButton103.setBounds(440, 200, 110, 36);

            jButton104.setText("sdb test");
            jButton104.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton104ActionPerformed(evt);
                }
            });
            jPanel10.add(jButton104);
            jButton104.setBounds(210, 120, 100, 36);

            jButton106.setText("mdb test");
            jButton106.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton106ActionPerformed(evt);
                }
            });
            jPanel10.add(jButton106);
            jButton106.setBounds(302, 120, 100, 36);

            jButton108.setText("hsm test");
            jButton108.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton108ActionPerformed(evt);
                }
            });
            jPanel10.add(jButton108);
            jButton108.setBounds(400, 120, 120, 36);

            jButton109.setText("string search");
            jButton109.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton109ActionPerformed(evt);
                }
            });
            jPanel10.add(jButton109);
            jButton109.setBounds(646, 60, 140, 36);

            jButton110.setText("full test");
            jButton110.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton110ActionPerformed(evt);
                }
            });
            jPanel10.add(jButton110);
            jButton110.setBounds(380, 160, 130, 36);

            textfieldState29.setText("textfieldState29");
            textfieldState29.setName("searchString"); // NOI18N
            jPanel10.add(textfieldState29);
            textfieldState29.setBounds(650, 40, 130, 20);

            textfieldState30.setText("textfieldState30");
            textfieldState30.setName("searchPath"); // NOI18N
            jPanel10.add(textfieldState30);
            textfieldState30.setBounds(650, 10, 130, 20);

            AdvTabState.addTab("realMyst", jPanel10);

            jButton50.setText("jButton50");
            jButton50.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton50ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
            jPanel12.setLayout(jPanel12Layout);
            jPanel12Layout.setHorizontalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(79, 79, 79)
                    .addComponent(jButton50)
                    .addContainerGap(827, Short.MAX_VALUE))
            );
            jPanel12Layout.setVerticalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(jButton50)
                    .addContainerGap(348, Short.MAX_VALUE))
            );

            AdvTabState.addTab("Riven", jPanel12);

            jPanel24.setLayout(null);

            jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Quaternion Compression Test"));

            jTextField3.setText("776421322");

            jTextField4.setText("883481695");

            jButton39.setText("jButton39");
            jButton39.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton39ActionPerformed(evt);
                }
            });

            jLabel5.setText("jLabel5");

            jTextArea4.setColumns(20);
            jTextArea4.setRows(5);
            jTextArea4.setText("-1258393726   1764680261\n\n\n");
            jScrollPane4.setViewportView(jTextArea4);

            javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
            jPanel8.setLayout(jPanel8Layout);
            jPanel8Layout.setHorizontalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTextField4)
                        .addComponent(jLabel5)
                        .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(39, 39, 39))
            );
            jPanel8Layout.setVerticalGroup(
                jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton39)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel5)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            jPanel24.add(jPanel8);
            jPanel8.setBounds(260, 130, 320, 162);

            jButton119.setText("Read file.");
            jButton119.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton119ActionPerformed(evt);
                }
            });
            jPanel24.add(jButton119);
            jButton119.setBounds(10, 210, 160, 36);

            textfieldState35.setText("textfieldState35");
            textfieldState35.setName("vaultInFile"); // NOI18N
            jPanel24.add(textfieldState35);
            textfieldState35.setBounds(20, 290, 87, 20);

            jButton120.setText("Select...");
            jButton120.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton120ActionPerformed(evt);
                }
            });
            jPanel24.add(jButton120);
            jButton120.setBounds(10, 250, 160, 36);

            AdvTabState.addTab("Legacy", jPanel24);

            jPanel33.setLayout(null);

            textfieldState13.setText("C:\\Documents and Settings\\user\\Desktop\\output\\ahny\\Ahnonay_District_BuiltIn.prp");
            textfieldState13.setName("deepViewFilename"); // NOI18N
            jPanel33.add(textfieldState13);
            textfieldState13.setBounds(10, 10, 455, 20);

            jButton80.setText("Select...");
            jButton80.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton80ActionPerformed(evt);
                }
            });
            jPanel33.add(jButton80);
            jButton80.setBounds(470, 0, 120, 36);

            jButton81.setText("Read");
            jButton81.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton81ActionPerformed(evt);
                }
            });
            jPanel33.add(jButton81);
            jButton81.setBounds(580, 0, 110, 36);

            jScrollPane13.setViewportView(jDesktopPane1);

            jPanel33.add(jScrollPane13);
            jScrollPane13.setBounds(10, 70, 760, 340);

            jButton83.setText("Clear");
            jButton83.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton83ActionPerformed(evt);
                }
            });
            jPanel33.add(jButton83);
            jButton83.setBounds(680, 0, 120, 36);

            jButton84.setText("Save changes.");
            jButton84.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton84ActionPerformed(evt);
                }
            });
            jPanel33.add(jButton84);
            jButton84.setBounds(460, 30, 150, 36);

            textfieldState14.setText("C:\\Documents and Settings\\user\\Desktop\\output\\");
                textfieldState14.setName("deepViewOutput"); // NOI18N
                jPanel33.add(textfieldState14);
                textfieldState14.setBounds(10, 40, 272, 20);

                jButton85.setText("Select...");
                jButton85.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton85ActionPerformed(evt);
                    }
                });
                jPanel33.add(jButton85);
                jButton85.setBounds(280, 30, 140, 36);

                jButton117.setText("Strings");
                jButton117.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton117ActionPerformed(evt);
                    }
                });
                jPanel33.add(jButton117);
                jButton117.setBounds(780, 120, 63, 36);

                jButton118.setText("Refs");
                jButton118.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton118ActionPerformed(evt);
                    }
                });
                jPanel33.add(jButton118);
                jButton118.setBounds(790, 160, 50, 36);

                AdvTabState.addTab("DeepView", jPanel33);

                tabsState2.setName("tabs1"); // NOI18N

                jLabel8.setText("MystV folder:");

                jLabel9.setText("Output folder:");

                jButton55.setText("select...");
                jButton55.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton55ActionPerformed(evt);
                    }
                });

                jButton56.setText("select...");
                jButton56.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton56ActionPerformed(evt);
                    }
                });

                jButton57.setText("Start...");
                jButton57.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton57ActionPerformed(evt);
                    }
                });

                jButton58.setText("Select All");
                jButton58.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton58ActionPerformed(evt);
                    }
                });

                jButton59.setText("Select None");
                jButton59.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton59ActionPerformed(evt);
                    }
                });

                listState2.setName("myst5list"); // NOI18N
                jScrollPane5.setViewportView(listState2);

                textfieldState2.setText("C:\\Program Files\\Ubisoft\\Cyan Worlds\\Myst V End Of Ages");
                textfieldState2.setName("myst5intext"); // NOI18N

                textfieldState3.setText("C:\\Documents and Settings\\user\\Desktop\\output");
                textfieldState3.setName("myst5outtext"); // NOI18N

                jLabel10.setText("Files to process:");

                jButton78.setText("read all...");
                jButton78.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton78ActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                jPanel15.setLayout(jPanel15Layout);
                jPanel15Layout.setHorizontalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textfieldState2, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                            .addComponent(textfieldState3, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton58)
                                    .addComponent(jButton59))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton57)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jButton55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton78))
                            .addComponent(jButton56))
                        .addGap(312, 312, 312))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel10)
                        .addContainerGap(825, Short.MAX_VALUE))
                );
                jPanel15Layout.setVerticalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(textfieldState2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton55)
                            .addComponent(jButton78))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                        .addComponent(jButton57)
                                        .addGap(76, 76, 76))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addComponent(jButton58)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton59)))
                                        .addGap(17, 17, 17))))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(textfieldState3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton56))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)))
                        .addGap(98, 98, 98))
                );

                tabsState2.addTab("MystV", jPanel15);

                jLabel6.setText("Crowthistle folder:");

                jLabel7.setText("Output folder:");

                jButton53.setText("start...");
                jButton53.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton53ActionPerformed(evt);
                    }
                });

                jButton51.setText("select...");
                jButton51.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton51ActionPerformed(evt);
                    }
                });

                jButton52.setText("select...");
                jButton52.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton52ActionPerformed(evt);
                    }
                });

                textfieldState4.setText("C:\\Program Files\\Crowthistle");
                textfieldState4.setName("crowintext"); // NOI18N

                textfieldState5.setText("C:\\Documents and Settings\\user\\Desktop\\output");
                textfieldState5.setName("crowouttext"); // NOI18N

                javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                jPanel14.setLayout(jPanel14Layout);
                jPanel14Layout.setHorizontalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textfieldState5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textfieldState4, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton51)
                                    .addComponent(jButton52)))
                            .addComponent(jButton53))
                        .addContainerGap(379, Short.MAX_VALUE))
                );
                jPanel14Layout.setVerticalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jButton51)
                            .addComponent(textfieldState4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jButton52)
                            .addComponent(textfieldState5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton53)
                        .addContainerGap(268, Short.MAX_VALUE))
                );

                tabsState2.addTab("Crowthistle", jPanel14);

                jPanel16.setLayout(null);

                jButton79.setText("Read All Prps from All Games...");
                jButton79.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton79ActionPerformed(evt);
                    }
                });
                jPanel16.add(jButton79);
                jButton79.setBounds(50, 190, 230, 36);

                checkboxState2.setSelected(true);
                checkboxState2.setText("Read from Pots");
                checkboxState2.setName("readAllFromPots"); // NOI18N
                jPanel16.add(checkboxState2);
                checkboxState2.setBounds(60, 20, 160, 28);

                checkboxState14.setSelected(true);
                checkboxState14.setText("Read from Crowthistle");
                checkboxState14.setName("readAllFromCrowthistle"); // NOI18N
                jPanel16.add(checkboxState14);
                checkboxState14.setBounds(60, 50, 180, 28);

                checkboxState15.setSelected(true);
                checkboxState15.setText("Read from MystV");
                checkboxState15.setName("readAllFromMystv"); // NOI18N
                jPanel16.add(checkboxState15);
                checkboxState15.setBounds(60, 80, 140, 28);

                checkboxState16.setSelected(true);
                checkboxState16.setText("Read from Moul");
                checkboxState16.setName("readAllFromMoul"); // NOI18N
                jPanel16.add(checkboxState16);
                checkboxState16.setBounds(60, 110, 180, 28);

                tabsState2.addTab("tab3", jPanel16);

                jPanel17.setLayout(null);

                textfieldState7.setText("C:\\Documents and Settings\\user\\Desktop\\output\\pots\\");
                    textfieldState7.setName("potstext1"); // NOI18N
                    jPanel17.add(textfieldState7);
                    textfieldState7.setBounds(150, 40, 430, 20);

                    jLabel11.setText("Pots folder:");
                    jPanel17.add(jLabel11);
                    jLabel11.setBounds(10, 40, 140, 16);

                    jButton62.setText("Read all prp files...");
                    jButton62.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton62ActionPerformed(evt);
                        }
                    });
                    jPanel17.add(jButton62);
                    jButton62.setBounds(90, 70, 160, 36);

                    jButton63.setText("select...");
                    jButton63.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton63ActionPerformed(evt);
                        }
                    });
                    jPanel17.add(jButton63);
                    jButton63.setBounds(670, 30, 110, 36);

                    jScrollPane9.setName("potsFiles"); // NOI18N

                    listState4.setModel(new javax.swing.AbstractListModel() {
                        String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
                        public int getSize() { return strings.length; }
                        public Object getElementAt(int i) { return strings[i]; }
                    });
                    listState4.setName("potsFiles"); // NOI18N
                    jScrollPane9.setViewportView(listState4);

                    jPanel17.add(jScrollPane9);
                    jScrollPane9.setBounds(140, 130, 130, 146);

                    jButton69.setText("Read selected files.");
                    jButton69.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton69ActionPerformed(evt);
                        }
                    });
                    jPanel17.add(jButton69);
                    jButton69.setBounds(290, 180, 140, 36);

                    tabsState2.addTab("Pots", jPanel17);

                    jPanel19.setLayout(null);

                    textfieldState6.setText("D:\\a\\winedrive\\drive_c\\MystOnline\\Program Files\\Myst Online");
                    textfieldState6.setName("moulintext"); // NOI18N
                    jPanel19.add(textfieldState6);
                    textfieldState6.setBounds(130, 10, 510, 20);

                    textfieldState8.setText("C:\\Documents and Settings\\user\\Desktop\\output");
                    textfieldState8.setName("moulouttext"); // NOI18N
                    jPanel19.add(textfieldState8);
                    textfieldState8.setBounds(130, 60, 510, 20);

                    jButton64.setText("Select...");
                    jButton64.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton64ActionPerformed(evt);
                        }
                    });
                    jPanel19.add(jButton64);
                    jButton64.setBounds(660, 20, 110, 36);

                    jButton65.setText("Select....");
                    jButton65.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton65ActionPerformed(evt);
                        }
                    });
                    jPanel19.add(jButton65);
                    jButton65.setBounds(660, 60, 110, 36);

                    jLabel12.setText("Moul folder:");
                    jPanel19.add(jLabel12);
                    jLabel12.setBounds(10, 10, 110, 16);

                    jLabel13.setText("Output folder:");
                    jPanel19.add(jLabel13);
                    jLabel13.setBounds(10, 60, 110, 16);

                    jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("Process a bunch of files..."));

                    jButton67.setText("Select all");
                    jButton67.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton67ActionPerformed(evt);
                        }
                    });

                    jLabel14.setText("Files to process:");

                    jButton68.setText("Select none");
                    jButton68.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton68ActionPerformed(evt);
                        }
                    });

                    listState3.setName("moulfiles"); // NOI18N
                    jScrollPane8.setViewportView(listState3);

                    jButton66.setText("Start...");
                    jButton66.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton66ActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
                    jPanel21.setLayout(jPanel21Layout);
                    jPanel21Layout.setHorizontalGroup(
                        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton67, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton68, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel21Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton66)
                            .addContainerGap())
                    );
                    jPanel21Layout.setVerticalGroup(
                        jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel21Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton67)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton68))
                                .addGroup(jPanel21Layout.createSequentialGroup()
                                    .addGap(47, 47, 47)
                                    .addComponent(jButton66))
                                .addGroup(jPanel21Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(14, Short.MAX_VALUE))
                    );

                    jPanel19.add(jPanel21);
                    jPanel21.setBounds(10, 90, 570, 170);

                    jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder("Process a single file..."));

                    textfieldState1.setText("Kveer_District_BuiltIn.prp");
                    textfieldState1.setName("moulParticularFile"); // NOI18N

                    jButton60.setText("select...");
                    jButton60.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton60ActionPerformed(evt);
                        }
                    });

                    jButton86.setText("start...");
                    jButton86.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton86ActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
                    jPanel22.setLayout(jPanel22Layout);
                    jPanel22Layout.setHorizontalGroup(
                        jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(textfieldState1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton60)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton86)
                            .addContainerGap(87, Short.MAX_VALUE))
                    );
                    jPanel22Layout.setVerticalGroup(
                        jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textfieldState1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton60)
                                .addComponent(jButton86))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                    jPanel19.add(jPanel22);
                    jPanel22.setBounds(10, 270, 490, 60);

                    tabsState2.addTab("Moul", jPanel19);

                    jPanel18.setLayout(null);

                    checkboxState1.setText("Report the ogg files seen.");
                    checkboxState1.setName("reportOggFiles"); // NOI18N
                    jPanel18.add(checkboxState1);
                    checkboxState1.setBounds(130, 50, 380, 28);

                    checkboxState3.setText("Report the avi files seen.");
                    checkboxState3.setName("reportAviFiles"); // NOI18N
                    jPanel18.add(checkboxState3);
                    checkboxState3.setBounds(130, 90, 430, 28);

                    checkboxState4.setText("Report PlEAXSourceSettings info.");
                    checkboxState4.setName("reportEaxSourceSettings"); // NOI18N
                    jPanel18.add(checkboxState4);
                    checkboxState4.setBounds(130, 130, 450, 28);

                    checkboxState5.setText("Report Physics settings.");
                    checkboxState5.setName("reportPhysics"); // NOI18N
                    jPanel18.add(checkboxState5);
                    checkboxState5.setBounds(130, 170, 410, 28);

                    checkboxState6.setText("Report suffixes written(including forced changes) and changed Age names.");
                    checkboxState6.setName("reportSuffixes"); // NOI18N
                    jPanel18.add(checkboxState6);
                    checkboxState6.setBounds(130, 210, 490, 28);

                    checkboxState7.setText("Report prp files processed.");
                    checkboxState7.setName("reportPrp"); // NOI18N
                    jPanel18.add(checkboxState7);
                    checkboxState7.setBounds(130, 20, 290, 28);

                    checkboxState12.setText("Report PythonFileMod info.");
                    checkboxState12.setName("reportPythonFileMod"); // NOI18N
                    jPanel18.add(checkboxState12);
                    checkboxState12.setBounds(130, 250, 190, 28);

                    checkboxState13.setText("Report Decryption info.");
                    checkboxState13.setName("reportDecryption"); // NOI18N
                    jPanel18.add(checkboxState13);
                    checkboxState13.setBounds(130, 280, 170, 28);

                    checkboxState21.setText("Report all object name & types.");
                    checkboxState21.setName("reportObjects"); // NOI18N
                    jPanel18.add(checkboxState21);
                    checkboxState21.setBounds(130, 310, 220, 28);

                    tabsState2.addTab("Reports", jPanel18);

                    checkboxState8.setSelected(true);
                    checkboxState8.setText("Remove PlDynamicCamMap references from Materials(removes frostiness, makes it transparent instead)");
                    checkboxState8.setName("removeDynamicCamMap"); // NOI18N

                    checkboxState9.setText("Make all PlLayers wireframe!");
                    checkboxState9.setName("makePlLayersWireframe"); // NOI18N

                    checkboxState11.setSelected(true);
                    checkboxState11.setText("When renaming Ages, changes the VeryVerySpecialPythonFileMod too.");
                    checkboxState11.setName("changeVerySpecialPython"); // NOI18N

                    checkboxState17.setText("Translate avatar away from smartseeks. (Warning: looks kind-of bad)");
                    checkboxState17.setName("translateSmartseeks"); // NOI18N

                    checkboxState18.setSelected(true);
                    checkboxState18.setText("Remove plLogicModifiers that reference plLadderModifiers.");
                    checkboxState18.setName("removeLadders"); // NOI18N

                    checkboxState19.setText("Skip physics with unhandled flags.");
                    checkboxState19.setName("skipPhysics"); // NOI18N

                    checkboxState20.setSelected(true);
                    checkboxState20.setText("Do automated work on Myst5(PythonFileMods, etc)");
                    checkboxState20.setName("automateMystV"); // NOI18N

                    checkboxState22.setText("Use xyzw for PlPXPhysical orientation.");
                    checkboxState22.setName("plpxphysicalQuatChange"); // NOI18N

                    checkboxState23.setText("Include new content (currently does nothing).");
                    checkboxState23.setName("includeAuthoredMaterial"); // NOI18N

                    javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
                    jPanel20.setLayout(jPanel20Layout);
                    jPanel20Layout.setHorizontalGroup(
                        jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addGap(114, 114, 114)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkboxState22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkboxState20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkboxState19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkboxState18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkboxState17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkboxState9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkboxState8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkboxState11, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkboxState23, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(261, Short.MAX_VALUE))
                    );
                    jPanel20Layout.setVerticalGroup(
                        jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addComponent(checkboxState8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkboxState9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkboxState11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkboxState17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkboxState18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkboxState19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkboxState20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkboxState22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkboxState23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(40, Short.MAX_VALUE))
                    );

                    tabsState2.addTab("Settings", jPanel20);

                    jButton90.setText("Create BuiltIn District prp (Agename, Output)");
                    jButton90.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton90ActionPerformed(evt);
                        }
                    });

                    textfieldState18.setText("g:\\prps\\pots\\dat\\Nexus_District_nxusBookMachine.prp");
                    textfieldState18.setName("translateInput"); // NOI18N

                    textfieldState19.setText("c:\\documents and settings\\user\\desktop\\output\\");
                        textfieldState19.setName("translateOutput"); // NOI18N

                        jButton91.setText("Translate (File, Output, Translation)");
                        jButton91.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton91ActionPerformed(evt);
                            }
                        });

                        jButton92.setText("select");
                        jButton92.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton92ActionPerformed(evt);
                            }
                        });

                        jButton93.setText("select");
                        jButton93.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton93ActionPerformed(evt);
                            }
                        });

                        textfieldState20.setText("0,0,-100");
                        textfieldState20.setName("translateVector"); // NOI18N

                        jButton94.setText("List Spawnpoint Sceneobjects (File)");
                        jButton94.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton94ActionPerformed(evt);
                            }
                        });

                        jButton99.setText("Sound test");
                        jButton99.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton99ActionPerformed(evt);
                            }
                        });

                        jButton100.setText("Add DynamicTextMap (File, Output)");
                        jButton100.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton100ActionPerformed(evt);
                            }
                        });

                        jLabel21.setText("File:");

                        jLabel22.setText("Output:");

                        textfieldState17.setText("Direbo");
                        textfieldState17.setName("agename"); // NOI18N

                        jLabel23.setText("Agename:");

                        jLabel24.setText("Translation:");

                        jButton41.setText("AutoMod");
                        jButton41.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton41ActionPerformed(evt);
                            }
                        });

                        textfieldState54.setText("g:\\prps\\pots\\");
                            textfieldState54.setName("spmodpots"); // NOI18N

                            jButton144.setText("select");
                            jButton144.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    jButton144ActionPerformed(evt);
                                }
                            });

                            jLabel53.setText("Pots:");

                            javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                            jPanel23.setLayout(jPanel23Layout);
                            jPanel23Layout.setHorizontalGroup(
                                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel23Layout.createSequentialGroup()
                                                .addGap(67, 67, 67)
                                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jButton91)
                                                    .addComponent(jButton90)
                                                    .addGroup(jPanel23Layout.createSequentialGroup()
                                                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jButton94)
                                                            .addComponent(jButton100))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                                                        .addComponent(jButton99))))
                                            .addGroup(jPanel23Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel23Layout.createSequentialGroup()
                                                            .addComponent(jLabel21)
                                                            .addGap(32, 32, 32))
                                                        .addGroup(jPanel23Layout.createSequentialGroup()
                                                            .addComponent(jLabel23)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                                    .addComponent(jLabel53))
                                                .addGap(12, 12, 12)
                                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(textfieldState17, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(textfieldState18, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                                                    .addComponent(textfieldState54, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jButton92)
                                                    .addComponent(jButton144))))
                                        .addGroup(jPanel23Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel24)
                                                .addComponent(jLabel22))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel23Layout.createSequentialGroup()
                                                    .addComponent(textfieldState19, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButton93))
                                                .addComponent(textfieldState20, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(312, 312, 312))
                            );
                            jPanel23Layout.setVerticalGroup(
                                jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel23Layout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textfieldState17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel23))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel21)
                                        .addComponent(textfieldState18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton92))
                                    .addGap(14, 14, 14)
                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textfieldState54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton144)
                                        .addComponent(jLabel53))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel23Layout.createSequentialGroup()
                                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(textfieldState19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel22)
                                                .addComponent(jButton93))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel24)
                                                .addComponent(textfieldState20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButton90)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton94)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton100)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton91))
                                        .addGroup(jPanel23Layout.createSequentialGroup()
                                            .addGap(41, 41, 41)
                                            .addComponent(jButton41)
                                            .addGap(39, 39, 39)
                                            .addComponent(jButton99)))
                                    .addContainerGap(21, Short.MAX_VALUE))
                            );

                            tabsState2.addTab("SpecialMods", jPanel23);

                            jLabel28.setText("HexIsle folder:");

                            jLabel29.setText("Output folder:");

                            textfieldState31.setText("textfieldState31");
                            textfieldState31.setName("hexisleInfolder"); // NOI18N

                            textfieldState32.setText("textfieldState32");
                            textfieldState32.setName("hexisleOutfolder"); // NOI18N

                            jButton111.setText("Start...");
                            jButton111.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    jButton111ActionPerformed(evt);
                                }
                            });

                            jButton112.setText("select...");
                            jButton112.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    jButton112ActionPerformed(evt);
                                }
                            });

                            jButton113.setText("select...");
                            jButton113.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    jButton113ActionPerformed(evt);
                                }
                            });

                            javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
                            jPanel37.setLayout(jPanel37Layout);
                            jPanel37Layout.setHorizontalGroup(
                                jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel37Layout.createSequentialGroup()
                                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel37Layout.createSequentialGroup()
                                            .addGap(34, 34, 34)
                                            .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel28)
                                                .addComponent(jLabel29))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(textfieldState32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(textfieldState31, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton113)
                                                .addComponent(jButton112)))
                                        .addGroup(jPanel37Layout.createSequentialGroup()
                                            .addGap(75, 75, 75)
                                            .addComponent(jButton111)))
                                    .addContainerGap(505, Short.MAX_VALUE))
                            );
                            jPanel37Layout.setVerticalGroup(
                                jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel37Layout.createSequentialGroup()
                                    .addGap(47, 47, 47)
                                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel28)
                                        .addComponent(textfieldState31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton112))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel29)
                                        .addComponent(textfieldState32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton113))
                                    .addGap(32, 32, 32)
                                    .addComponent(jButton111)
                                    .addContainerGap(189, Short.MAX_VALUE))
                            );

                            tabsState2.addTab("HexIsle", jPanel37);

                            AdvTabState.addTab("Manual Conversion", tabsState2);

                            tabsState3.addTab("Advanced", AdvTabState);

                            HelpTab.setLayout(null);

                            imagePanel2.setImageFile("/gui/Pterosaur2b4.png");

                            javax.swing.GroupLayout imagePanel2Layout = new javax.swing.GroupLayout(imagePanel2);
                            imagePanel2.setLayout(imagePanel2Layout);
                            imagePanel2Layout.setHorizontalGroup(
                                imagePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 135, Short.MAX_VALUE)
                            );
                            imagePanel2Layout.setVerticalGroup(
                                imagePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGap(0, 124, Short.MAX_VALUE)
                            );

                            HelpTab.add(imagePanel2);
                            imagePanel2.setBounds(10, 40, 135, 124);

                            jTextArea5.setColumns(20);
                            jTextArea5.setRows(5);
                            jScrollPane2.setViewportView(jTextArea5);

                            HelpTab.add(jScrollPane2);
                            jScrollPane2.setBounds(150, 40, 810, 370);

                            tabsState3.addTab("Help", HelpTab);

                            getContentPane().add(tabsState3);
                            tabsState3.setBounds(10, 10, 990, 490);

                            jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder("Log"));
                            jPanel27.setLayout(null);

                            jButton2.setText("Clear log.");
                            jButton2.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    jButton2ActionPerformed(evt);
                                }
                            });
                            jPanel27.add(jButton2);
                            jButton2.setBounds(10, 20, 140, 36);

                            jButton20.setText("Remove dupes.");
                            jButton20.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    jButton20ActionPerformed(evt);
                                }
                            });
                            jPanel27.add(jButton20);
                            jButton20.setBounds(10, 50, 140, 36);

                            jButton54.setText("Alphabetise.");
                            jButton54.addActionListener(new java.awt.event.ActionListener() {
                                public void actionPerformed(java.awt.event.ActionEvent evt) {
                                    jButton54ActionPerformed(evt);
                                }
                            });
                            jPanel27.add(jButton54);
                            jButton54.setBounds(10, 80, 140, 36);

                            jTextArea1.setColumns(20);
                            jTextArea1.setRows(5);
                            jScrollPane10.setViewportView(jTextArea1);

                            jPanel27.add(jScrollPane10);
                            jScrollPane10.setBounds(160, 27, 430, 130);

                            getContentPane().add(jPanel27);
                            jPanel27.setBounds(10, 510, 610, 180);

                            pack();
                        }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.jTextArea1.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        UruFileTypes type = UruCrypt.DetectType(this.textfieldState15.getText());
        m.msg("Filetype is:",type.toString());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        File file = new File(textfieldState15.getText());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] decodedcontents = uru.UruCrypt.DecryptWhatdoyousee(filecontents);
        //Main.message(new String(decodedcontents));
        FileUtils.WriteFile(textfieldState16.getText()+"/"+file.getName(), decodedcontents);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        File file = new File(textfieldState15.getText());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] decodedcontents = uru.UruCrypt.DecryptNotthedroids(filecontents);
        //Main.message(new String(decodedcontents));
        FileUtils.WriteFile(textfieldState16.getText()+"/"+file.getName(), decodedcontents);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        uam.GuiModal uamform = new uam.GuiModal(null, false, null);
        Object filechooser = shared.GuiUtils.getJFileChooser();
        translation.translation.printStringsForAllGuiForms();
        uamform.toString();
        filechooser.toString();
        //shared.GuiUtils.printUIManagerResources();
        //shared.GuiUtils.setBackgroundColour(java.awt.Color.cyan);
        //shared.GuiUtils.updateGui(this);
        //shared.zip.getAllEntries("C:\\Documents and Settings\\user\\Local Settings\\Temp\\Drizzle6683135168305972449.tmp");
        /*String path = "G:\\prps\\moul\\dat";
        //String strtofind = "femaleamazed";
        String strtofind = "blowkiss";
        for(java.io.File f: new java.io.File(path).listFiles())
        {
            if(f.isFile())
            {
                //m.msg(f.getName());
                byte[] data = shared.FileUtils.ReadFile(f);
                for(int i=0;i<data.length;i++)
                {
                    //data[i] = b.not(data[i]);
                }
                String data2 = b.BytesToString(data);
                String data3 = data2.toLowerCase();
                int ind = data3.indexOf(strtofind);
                if(ind!=-1)
                {
                    m.msg("found str at pos: "+Integer.toString(ind));
                    m.msg("in file: "+f.getName());
                }
            }
        }
        if(true)return;
    
        //tests
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        context c = context.createFromBytestream(new Bytestream(filecontents));
        //try{
        uru.moulprp.prpprocess.ProcessAllObjectsOfType(c, Typeid.plLayerAnimation);
        //}catch(Exception e){}
        
        
        //byte[] content = {'a','b','c'};
        //FileUtils.WriteFile("c:/hi.txt", content);
        
        //byte[] bytes = {(byte)0x00,(byte)0x01};
        //short s = UruUtils.BytesToInt16(bytes,0);
        //Main.message("short:"+Short.toString(s));
        
        //byte[] bytes = {(byte)0x06,(byte)0x00,(byte)0x29,(byte)0x10,(byte)0x01,(byte)0x11,(byte)0x0C,(byte)0x0A};
        //byte[] result = UruUtils.DecryptEoastring(bytes);
        //Main.message("eoastring:"+new String(result));
        
        //byte[] bytes = {(byte)'D',(byte)'i',(byte)'r',(byte)'e',(byte)'b',(byte)'o'};
        //byte[] result = UruUtils.EncryptEoastring(bytes);
        //Main.message("eoastring:"+new String(result));//GEN-LAST:event_jButton6ActionPerformed

        //byte[] bytes = {(byte)0x09,(byte)0xF0,(byte)0xBD,(byte)0x9E,(byte)0x97,(byte)0x8D,(byte)0x90,(byte)0xBC,(byte)0x9E,(byte)0x89,(byte)0x9A};
        //byte[] result = UruUtils.DecryptUrustring(bytes);
        //Main.message("urustring:"+new String(result));
        
        //byte[] bytes = {(byte)'B',(byte)'a',(byte)'h',(byte)'r',(byte)'o',(byte)'C',(byte)'a',(byte)'v',(byte)'e'};
        //UruUtils.EncryptUrustring(bytes);
        
        //byte[] bytes = {'T','h','e',' ','q','u','i','c','k',' ','b','r','o','w','n',' ','f','o','x',' ','j','u','m','p','s',' ','o','v','e','r',' ','t','h','e',' ','l','a','z','y',' ','d','o','g'};
        //byte[] result = CryptHashes.GetMd5(bytes);
        //Main.message("done");
        
        //byte[] filecontents = FileUtils.ReadFile((String)this.jComboBox1.getSelectedItem());
        //Moul.createPrp(filecontents);
        
        
        //uru.Bytedeque d = new uru.Bytedeque();
        //byte[] bytes1 = {'a','b'};
        //byte[] bytes2 = {'c'};
        //byte[] bytes3 = {'d','e','f'};
        //byte[] bytes4 = {'g'};
        //d.appendBytes(bytes1);
        //d.prependBytes(bytes2);
        //d.appendBytes(bytes4);
        //d.prependBytes(bytes3);
        //byte[] result = d.getAllBytes();
        
        //try{
        //jxl.write.WritableWorkbook wb = jxl.Workbook.createWorkbook(new java.io.File("c:/hi.xls"));
        //jxl.write.WritableSheet sheet = wb.createSheet("Sheet", 0);
        ////sheet.addCell(new jxl.write.Label(1,1,"hi"));
        //sheet.addCell(new jxl.write.Number(1,1,7));
        //wb.write();
        //wb.close();
        //}catch(Exception e){}
        
        //byte[] bytes = new byte[256];
        //for(int i=0;i<256;i++)
        //{
        //    bytes[i] = (byte)i;
        //}
        //byte[] encoded = uru.UruCrypt.EncryptUruMessage(bytes);
        //byte[] decoded = uru.UruCrypt.DecryptUruMessage(encoded);
        
        //byte[] bytes = FileUtils.ReadFile("c:/documents and settings/user/desktop/4.dat");
        //byte[] dec = uru.UruCrypt.DecryptUruMessage(bytes,7);
        //FileUtils.WriteFile("c:/documents and settings/user/desktop/5.dat", dec);
        */
        /*java.util.zip.Inflater inflater = new java.util.zip.Inflater();
        inflater.setInput(bytes);
        byte[] output = new byte[10000];
        try{
        inflater.inflate(output);
        inflater.end();
        //java.util.zip.inf
        FileUtils.WriteFile("c:/documents and settings/user/desktop/5.dat", output);
        }
        catch(Exception e)
        {
            int i=0;
        }*/
        
        /*
        com.jcraft.jzlib.ZStream d_stream = new com.jcraft.jzlib.ZStream();
        d_stream.next_in = bytes;
        d_stream.next_in_index = 0;
        byte[] uncompr = new byte[100000];
        d_stream.next_out=uncompr;
        d_stream.next_out_index=0;

        int err=d_stream.inflateInit();
        //CHECK_ERR(d_stream, err, "inflateInit");

        while(d_stream.total_out<200 &&
          d_stream.total_in<200) {
          d_stream.avail_in=d_stream.avail_out=1; 
          err=d_stream.inflate(com.jcraft.jzlib.JZlib.Z_NO_FLUSH);
          if(err==com.jcraft.jzlib.JZlib.Z_STREAM_END) break;
          //CHECK_ERR(d_stream, err, "inflate");
        }

        err=d_stream.inflateEnd();
        //CHECK_ERR(d_stream, err, "inflateEnd");
        */
        
        //uru.moulprp.Uruvector<String> a23 = new uru.moulprp.Uruvector<String>();

        
    }                                        

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        File file = new File(textfieldState15.getText());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] encodedcontents = uru.UruCrypt.EncryptNotthedroids(filecontents);
        FileUtils.WriteFile(textfieldState16.getText()+"/"+file.getName(), encodedcontents);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        File file = new File(textfieldState15.getText());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] decodedcontents = uru.UruCrypt.DecryptEoa(filecontents);
        //Main.message(new String(decodedcontents));
        FileUtils.WriteFile(textfieldState16.getText()+"/"+file.getName(), decodedcontents);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        File file = new File(textfieldState15.getText());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] encodedcontents = uru.UruCrypt.EncryptEoa(filecontents);
        FileUtils.WriteFile(textfieldState16.getText()+"/"+file.getName(), encodedcontents);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        File file = new File(textfieldState15.getText());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] encodedcontents = uru.UruCrypt.EncryptWhatdoyousee(filecontents);
        FileUtils.WriteFile(textfieldState16.getText()+"/"+file.getName(), encodedcontents);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        File file = new File(textfieldState15.getText());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] encodedcontents = uru.UruCrypt.DecryptElf(filecontents);
        FileUtils.WriteFile(textfieldState16.getText()+"/"+file.getName(), encodedcontents);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        File file = new File(textfieldState15.getText());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        byte[] encodedcontents = uru.UruCrypt.EncryptElf(filecontents);
        FileUtils.WriteFile(textfieldState16.getText()+"/"+file.getName(), encodedcontents);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        File file = new File(this.textfieldState48.getText());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        String report = prputils.MakeObjectIndexReport(filecontents);
        FileUtils.WriteFile(this.textfieldState49.getText()+"/"+file.getName(), report.getBytes());
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.textfieldState48.getText());
        //prputils.DumpObjects(filecontents, Typeid.plClusterGroup);
        m.err("Ask Dustin to fix this!"); //should just need the output folder passed into dumpobjects.
    }//GEN-LAST:event_jButton14ActionPerformed
        /*{
            savesettings();
        }
    }     */

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        m.err("This method of recompiling Moul is deprecated, and has been commented out.");
        //byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        //prputils.Compiler.RecompilePrp(filecontents, new automation.mystAutomation.moulDecider());
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        //remove duplicate lines, even if they're not adjacent.
        
        String alltext = this.jTextArea1.getText();
        String[] lines = alltext.split("\n");
        int linecount = lines.length;
        StringBuilder newtext = new StringBuilder();

        for(int i=0;i<linecount;i++)
        {
            String curline = lines[i];
            if(curline != null) //if the current line isn't empty.
            {
                newtext.append(curline);
                newtext.append("\n");

                for(int j=i+1;j<linecount;j++) //search for duplicates
                {
                    if(curline.equals(lines[j]))
                    {
                        lines[j] = null; //remove the duplicate.
                    }
                }
            }
        }
        
        this.jTextArea1.setText(newtext.toString());
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        //uru.moulprp.prputils.findAllObjectsOfType("D:/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/Uru - Ages Beyond Myst/dat/", uru.moulprp.Typeid.plBoundInterface);
        uru.moulprp.prputils.findAllObjectsOfType("D:/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/Uru - Ages Beyond Myst/dat/", uru.moulprp.Typeid.plClusterGroup);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        this.crossRefPanel1.clearAllMarks();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        this.crossRefPanel1.deleteMarkedEntities();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        this.crossRefPanel1.markLinks(false,true);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        this.crossRefPanel1.markEntitiesThatStartWith(this.textfieldState51.getText());
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        this.crossRefPanel1.markEntitiesThatEndWith(this.textfieldState51.getText());
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        this.crossRefPanel1.markLinks(true,false);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        this.crossRefPanel1.initAsCrossLinkReport(this.textfieldState50.getText());
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        this.crossRefPanel1.markLinks(true,true);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        //String in = this.jTextArea2.getText();
        String in = "";
        m.err("Nag Dustin to fix this!");
        char[] inchars = in.toCharArray();
        StringBuilder result = new StringBuilder();
        for(int i=0;i < inchars.length;i++)
        {
            char curchar = inchars[i];
            if(curchar=='&')
            {
                StringBuilder esc = new StringBuilder();
                while(true)
                {
                    i++; //go to next char
                    curchar = inchars[i];
                    if(curchar==';')
                    {
                        break;
                    }
                    else
                    {
                        esc.append(curchar);
                    }
                }
                String esc2 = esc.toString();
                if (esc2.equals("lt")) result.append("<");
                else if (esc2.equals("gt")) result.append(">");
                else if (esc2.equals("amp")) result.append("&");
                else m.err("unhandled escape char.");
            }
            else
            {
                result.append(curchar);
            }
        }
        String out = result.toString();
        //this.jTextArea2.setText(out);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        //String in = this.jTextArea2.getText();
        String in = "";
        m.err("Nag Dustin to fix this!");
        char[] inchars = in.toCharArray();
        StringBuilder result = new StringBuilder();
        for(int i=0;i < inchars.length;i++)
        {
            char curchar = inchars[i];
            if(curchar=='"') result.append("\\\"");
            else if (curchar=='\n') result.append("\\n");
            else if (curchar=='\r') result.append("\\r");
            else result.append(curchar);
        }
        String out = result.toString();
        //this.jTextArea2.setText(out);
    }//GEN-LAST:event_jButton33ActionPerformed
        /*File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        //try{
            String report = uru.moulprp.prpreports.MakeFullReport(filecontents);
            FileUtils.WriteFile(_staticsettings.outputdir+"fullreport.txt", report.getBytes());
        //}catch(Exception e){}*/
    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        /*String text = this.jTextArea3.getText();
        double unitspercircle = 20;
        String[] nums = text.split(",");
        double x = 0;
        double y = 0;
        for(int i=0;i<nums.length;i+=2)
        {
            double curangle = new Double(nums[i]);
            double curdist = new Double(nums[i+1]);
            curangle = curangle*(2*Math.PI)/unitspercircle;
            double curx = curdist*Math.cos(curangle);
            double cury = curdist*Math.sin(curangle);
            x += curx;
            y += cury;
        }
        double dist = java.lang.Math.sqrt(x*x+y*y);
        //double angle = Math.atan(y/x);
        double angle = Math.atan2(y, x);
        angle = angle*unitspercircle/(2*Math.PI);
        String result = "angle=" + Double.toString(angle)+  " dist=" + Double.toString(dist);
        this.jTextField2.setText(result);*/

        String text = this.jTextArea3.getText();
        double unitspercircle = 20;
        String[] lines = text.split("\n");
        double x = 0;
        double y = 0;
        for(String line: lines)
        {
            if(line.startsWith("//")) continue;
            if(line.equals("")) continue;
            String[] nums = line.split(",");
            double curangle;
            double curdist;
            try{
                curangle = new Double(nums[0]);
                curdist = new Double(nums[1]);
            }catch(Exception e){
                m.err("Invalid line: ",line);
                return;
            }
            curangle = curangle*(2*Math.PI)/unitspercircle;
            double curx = curdist*Math.cos(curangle);
            double cury = curdist*Math.sin(curangle);
            x += curx;
            y += cury;
        }
        double dist = java.lang.Math.sqrt(x*x+y*y);
        double angle = Math.atan2(y, x);
        angle = angle*unitspercircle/(2*Math.PI);
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.####");
        String result = "angle=" + df.format(angle)+  " dist=" + df.format(dist);
        this.jTextField2.setText(result);
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        /*int data = Integer.parseInt(this.jTextField3.getText());
        int bits1 = (data >>> 0) & 0x03FF; //get 10 bits;
        int bits2 = (data >>> 10) & 0x03FF; //get next 10 bits;
        int bits3 = (data >>> 20) & 0x03FF; //get next 10 bits;
        int missingval = (data >>> 30) & 0x03; //get last 2 bits;
        double decoded1 = (bits1/1023.0-0.5)*Math.sqrt(2);
        double decoded2 = (bits2/1023.0-0.5)*Math.sqrt(2);
        double decoded3 = (bits3/1023.0-0.5)*Math.sqrt(2);
        float result1 = (float)decoded1;
        float result2 = (float)decoded2;
        float result3 = (float)decoded3;*/
        int data1 = Integer.parseInt(this.jTextField3.getText());
        int data2 = Integer.parseInt(this.jTextField4.getText());
        uru.moulprp.PrpController.decompressQuaternion(data1,data2);
        
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        
        //PtGetServerTime() = UTC (seconds)
        //PtGetDniTime() = UTC - 7*60*60 (New Mexico timezone). No DST.
        //PtGetAgeTime()+agefile.StartDateTime=PtGetServerTime()
        //agefile.DayLength = length of age's day in hours. I think internally this is changed to seconds and rounded off, as this more closely matches the numbers in-game.
        //PtGetAgeTimeOfDayPercent() = PtGetAgeTime() % (DayLength*60*60)
        class agepair
        {
            String name;
            long startdatetime;
            public agepair(String name2, long startdatetime2)
            {
                name = name2;
                startdatetime = startdatetime2;
            }
        }
        agepair[] pairs = new agepair[]{
            new agepair("Negilahn",946713600L*1000),
            new agepair("Dereno",946718065L*1000),
            new agepair("Payiferen",946720085L*1000),
            new agepair("Tetsonot",946730009L*1000),
        };
        for(int i=0;i<pairs.length;i++)
        {
        //try
        //{
            //double daylengthinhours = 15.718056; //not used.
            //long agefileStartDatetime = 946713600L*1000; //negilahn
            //long agefileStartDatetime = 946718065L*1000; //dereno
            //long agefileStartDatetime = 946720085L*1000; //payiferen
            //long agefileStartDatetime = 946730009L*1000; //tetsonot
            long agefileStartDatetime = pairs[i].startdatetime;
            long msecondsPerDay = 56585*1000;
            long timeForSymbol = msecondsPerDay*226/2000;
            
            //String datetimestring = this.jTextField5.getText();
            //long servertime = new java.text.SimpleDateFormat("MMM dd, yyyy KK:mm aa zzz").parse(datetimestring).getTime();
            
            long servertime = uru.Time.PtGetServerTime();
            long dnitime = uru.Time.PtGetDniTime();
            uru.Time.podtime pt = uru.Time.GetPodAgeTimeInfo(msecondsPerDay, agefileStartDatetime, servertime, timeForSymbol);
            long agetime = pt.msAgeTime;
            long day = pt.numDay;
            long timeofday = pt.msTimeOfDay;
            long curdaystart = pt.msCurDayStart;
            long nextdaystart = pt.msNextDayStart;
            
            long curdaysymbol = pt.msCurDaySymbol;
            long nextdaysymbol = pt.msNextDaySymbol;
            
            if(i==0)
            {
                m.msg("servertime=",Long.toString(servertime));
                m.msg("dnitime=",Long.toString(dnitime));
                m.msg("agetime=",Long.toString(agetime));
                m.msg("agetime%=",Double.toString(pt.percentTimeOfDay));
                m.msg("Time of cur day: ",new java.util.Date(curdaystart).toString());
                m.msg("Time of next day: ",new java.util.Date(nextdaystart).toString());
            }
            m.msg("Time of today's ",pairs[i].name," symbol: ",new java.util.Date(curdaysymbol).toString());
            m.msg("Time of tomorrow's ",pairs[i].name," symbol: ",new java.util.Date(nextdaysymbol).toString());
        //}
        //catch(Exception e)
        //{
        //    m.err("Incorrect format; use \"Feb 15, 1982 8:00 AM\" for example.");
        //}
        }
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        /*long time = new java.util.Date().getTime();
        int tzoffset = java.util.Calendar.getInstance().get(java.util.Calendar.ZONE_OFFSET);
        int dstoffset = java.util.Calendar.getInstance().get(java.util.Calendar.DST_OFFSET);
        //long localtime = time + tzoffset + dstoffset;
        long localtime = time/10000000L + 1240428288L;
        long low = time & 0xFFFFFFFFL;
        long high = (time >>> 32) & 0xFFFFFFFFL;
        long low2 = time/10000000L + 1240428288L;
        long high2 = (time % 10000000L) / 10;
        localtime = time - 7*60*60*1000; //adjust time zone.
        m.msg("Current GMT time is: ",Long.toString(time));
        m.msg("Current local time is: ",Long.toString(localtime));
        m.msg("Time of gmttime: ",(new java.util.Date(time)).toString());
        m.msg("Time of localtime: ",(new java.util.Date(localtime)).toString());*/
        //m.msg("test: ",(new java.util.Date(1240428288)).toString());
        long servertime = uru.Time.PtGetServerTime();
        long dnitime = uru.Time.ServerTimeToDniTime(servertime);
        String servertimestr = uru.Time.TimeToString(servertime);
        String dnitimestr = uru.Time.TimeToString(dnitime);
        m.msg("PtGetServerTime = ",Long.toString(servertime)," (",servertimestr,")");
        m.msg("PtGetDniTime = ",Long.toString(dnitime)," (",dnitimestr,")");
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        //byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        //prputils.DumpObjects(filecontents, null);
        m.err("Ask Dustin to fix this!");
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        //byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        //byte[] filecontents = FileUtils.ReadFile(this.textfieldState21.getText());
        //realmyst.rmcontext c = new realmyst.rmcontext(new Bytestream(filecontents));
        //realmyst.dirtfile df = new realmyst.dirtfile(c);
        shared.IBytestream bs = shared.SerialBytestream.createFromFilename(this.textfieldState21.getText());
        realmyst.dirtfile df = new realmyst.dirtfile(bs);
        df.saveAllFiles(this.textfieldState24.getText());
        int dummy=0;
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(textfieldState15.getText());
        byte[] md5 = shared.CryptHashes.GetMd5(filecontents);
        String md5str = b.BytesToHexString(md5);
        m.msg("md5: ",md5str);
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        /*String age = this.jTextField1.getText();
        //String infolder = this.pots+"\\dat\\";
        String infolder = "/shared/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/dat";
        String outfolder = this.out;
        Bytes b = uru.moulprp.sumfile.createSumfile(infolder, age);
        FileUtils.WriteFile(outfolder+"/sum.sum", b);*/
        m.err("Ask Dustin to fix this!");
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        try{
            new riven.mhk("/shared/DontBackup/a_Data.MHK");
        }catch(Exception e){}
        //org.kc7bfi.jflac.FLACEncoder a = new org.kc7bfi.jflac.FLACEncoder();
        //org.kc7bfi.jflac.FLACDecoder b;
        //a.
        
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        GuiUtils.getUserSelectedFolder(this.textfieldState4);
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        GuiUtils.getUserSelectedFolder(this.textfieldState5);
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        //do crowthistle conversion.
        automation.mystAutomation.convertCrowthistleToPots(textfieldState4.getText(), textfieldState5.getText());
    }//GEN-LAST:event_jButton53ActionPerformed

private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        //alphabetise...
        String alltext = this.jTextArea1.getText();
        String[] lines = alltext.split("\n");
        java.util.List<String> list = java.util.Arrays.asList(lines);
        java.util.Collections.sort(list);
        
        int linecount = list.size();
        StringBuilder newtext = new StringBuilder();
        for(int i=0;i<linecount;i++)
        {
            newtext.append(list.get(i));
            newtext.append("\n");
        }
        this.jTextArea1.setText(newtext.toString());

}//GEN-LAST:event_jButton54ActionPerformed

private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
    //shared.State.AllStates.test();
    listState2.selectAll();
}//GEN-LAST:event_jButton58ActionPerformed

private void jButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton59ActionPerformed
    //shared.State.AllStates.test2();
    listState2.selectNone();
    //listState2.setSelectionInterval(0, 0);    listState2.getSelectionModel().clearSelection();

}//GEN-LAST:event_jButton59ActionPerformed

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    //shared.State.AllStates.loadandpush(settingsfile);
    

    //set language if called for...
    /*int startuptype = shared.State.AllStates.getStateAsInt("language");
    String code = "en";
    switch(startuptype)
    {
        case 0:
            //english
            code = "en";
            break;
        case 1:
            //german
            code = "de";
            break;
        case 2:
            //french
            code = "fr";
            break;
        default:
            m.err("Unhandled language type.");
            break;
    }
    translation.translation.setLanguage(code);*/
    //pushlanguage();

    //scroll the help window down.
    //jTextArea5.scrollRectToVisible(new java.awt.Rectangle(0,0,1,1));
    
}//GEN-LAST:event_formWindowOpened

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    if(shared.State.AllStates.getStateAsBoolean("saveOnExit"))
    {
        shared.State.AllStates.pullandsave(settingsfile);
    }
}//GEN-LAST:event_formWindowClosing
int[] oldlist={};
boolean skip=false;
private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        GuiUtils.getUserSelectedFolder(this.textfieldState2);
}//GEN-LAST:event_jButton55ActionPerformed

private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        GuiUtils.getUserSelectedFolder(this.textfieldState3);
}//GEN-LAST:event_jButton56ActionPerformed

private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
    shared.State.AllStates.resetSettings(this.settingsfile);
    //shared.m.msg("You'll have to restart to program for the defaults to be restored.");
    System.exit(0);
}//GEN-LAST:event_jButton61ActionPerformed

private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
        //do myst5 conversion.
        automation.mystAutomation.convertMyst5ToPots(textfieldState2.getText(), textfieldState3.getText(), listState2.getSelectedAsStrings(), false);
}//GEN-LAST:event_jButton57ActionPerformed

private void jButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton63ActionPerformed
            GuiUtils.getUserSelectedFolder(this.textfieldState7);
}//GEN-LAST:event_jButton63ActionPerformed

private void jButton62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton62ActionPerformed
    automation.mystAutomation.readAllPotsPrps(this.textfieldState7.getText());
}//GEN-LAST:event_jButton62ActionPerformed

private void jButton67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton67ActionPerformed
    listState3.selectAll();
}//GEN-LAST:event_jButton67ActionPerformed

private void jButton68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton68ActionPerformed
    listState3.selectNone();
}//GEN-LAST:event_jButton68ActionPerformed

private void jButton64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton64ActionPerformed
    GuiUtils.getUserSelectedFolder(textfieldState6);
}//GEN-LAST:event_jButton64ActionPerformed

private void jButton65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton65ActionPerformed
    GuiUtils.getUserSelectedFolder(textfieldState8);
}//GEN-LAST:event_jButton65ActionPerformed

private void jButton66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton66ActionPerformed
    automation.mystAutomation.convertMoulToPots(textfieldState6.getText(), textfieldState8.getText(), listState3.getSelectedAsStrings(),false);
}//GEN-LAST:event_jButton66ActionPerformed

private void jButton69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton69ActionPerformed
    automation.mystAutomation.readPotsPrps(textfieldState7.getText(), listState4.getSelectedAsStrings());
}//GEN-LAST:event_jButton69ActionPerformed

private void jButton70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton70ActionPerformed
    mystProxy.mystProxy.startServer(textfieldState9.getText(),textfieldState10.getText());
}//GEN-LAST:event_jButton70ActionPerformed

private void jButton71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton71ActionPerformed
    mystProxy.mystProxy.stopServer();
}//GEN-LAST:event_jButton71ActionPerformed

private void jButton72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton72ActionPerformed
    jTextArea6.setText(jTextArea1.getText());
}//GEN-LAST:event_jButton72ActionPerformed

private void jButton77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton77ActionPerformed
    GuiUtils.getUserSelectedFolder(textfieldState9);
}//GEN-LAST:event_jButton77ActionPerformed

private void jButton78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton78ActionPerformed
    automation.mystAutomation.readAllPotsPrps(this.textfieldState2.getText());
}//GEN-LAST:event_jButton78ActionPerformed

private void jButton79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton79ActionPerformed
    automation.mystAutomation.readAllPrpsFromAllGames(textfieldState7.getText(),textfieldState4.getText(),textfieldState2.getText(),textfieldState6.getText());
}//GEN-LAST:event_jButton79ActionPerformed

private void jButton80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton80ActionPerformed
    GuiUtils.getUserSelectedFile(textfieldState13);
}//GEN-LAST:event_jButton80ActionPerformed

private void jButton81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton81ActionPerformed
    deep.read(textfieldState13.getText());//, /*jPanel34*/jDesktopPane1);
    deep.openAllTypes();
}//GEN-LAST:event_jButton81ActionPerformed

private void jButton83ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton83ActionPerformed
    deep.clearAll();
}//GEN-LAST:event_jButton83ActionPerformed

private void jButton85ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton85ActionPerformed
    GuiUtils.getUserSelectedFolder(textfieldState14);
}//GEN-LAST:event_jButton85ActionPerformed

private void jButton84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton84ActionPerformed
    deep.saveChanges(textfieldState14.getText());
}//GEN-LAST:event_jButton84ActionPerformed

private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
    //GuiUtils.getUserSelectedFileWithNoPath(textfieldState1);
    GuiUtils.getUserSelectedFile(textfieldState1);
}//GEN-LAST:event_jButton60ActionPerformed

private void jButton86ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton86ActionPerformed
    Vector<String> v = new Vector<String>();
    File f = new File(textfieldState1.getText());
<<<<<<< .mine
    if (!f.getParentFile().getName().equals("dat")) {
        m.err("The file you specify must be in a folder called \"dat\"");
        return;
    }
=======
    if (!f.getParentFile().getName().equals("dat")) {
           m.err("The file you specify must be in a folder called \"dat\"");
           return;
    }
>>>>>>> .r2329
    String filename = f.getName();
    String infolder = f.getParentFile().getParent();
    v.add(filename);
    automation.mystAutomation.convertMoulToPots(infolder/*textfieldState6.getText()*/,textfieldState8.getText(), v,false);
}//GEN-LAST:event_jButton86ActionPerformed

private void jButton87ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton87ActionPerformed
    String s2 = jTextArea6.getText();
    s2 = s2.replace("\0", "");
    java.awt.datatransfer.StringSelection s = new java.awt.datatransfer.StringSelection(s2);
    java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
}//GEN-LAST:event_jButton87ActionPerformed

private void jButton88ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton88ActionPerformed
    GuiUtils.getUserSelectedFile(textfieldState15);
}//GEN-LAST:event_jButton88ActionPerformed

private void jButton89ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton89ActionPerformed
    GuiUtils.getUserSelectedFolder(textfieldState16);
}//GEN-LAST:event_jButton89ActionPerformed

private void jButton90ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton90ActionPerformed
    automation.hackFactory.createBuiltInPrpFile(textfieldState17.getText(),textfieldState19.getText());
}//GEN-LAST:event_jButton90ActionPerformed

private void jButton92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton92ActionPerformed
    GuiUtils.getUserSelectedFile(textfieldState18);
}//GEN-LAST:event_jButton92ActionPerformed

private void jButton93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton93ActionPerformed
    GuiUtils.getUserSelectedFolder(textfieldState19);
}//GEN-LAST:event_jButton93ActionPerformed

private void jButton91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton91ActionPerformed
    String in = textfieldState18.getText();
    String outfolder = textfieldState19.getText();
    String vecstr = textfieldState20.getText();
    String[] vecstrs = vecstr.split(",");
    float x = Float.parseFloat(vecstrs[0]);
    float y = Float.parseFloat(vecstrs[1]);
    float z = Float.parseFloat(vecstrs[2]);
    
    automation.inplaceModifications.atranslateAllObjects(in,outfolder,x,y,z);
}//GEN-LAST:event_jButton91ActionPerformed

private void jButton94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton94ActionPerformed
    automation.listSpawnpoints.listSpawnpoints(this.textfieldState18.getText());
}//GEN-LAST:event_jButton94ActionPerformed

private void jButton97ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton97ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState22);
}//GEN-LAST:event_jButton97ActionPerformed

private void jButton98ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton98ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState23);
}//GEN-LAST:event_jButton98ActionPerformed

private void jButton96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton96ActionPerformed
    automation.myst5Fixes.convertABunchOfMyst5Stuff(this.textfieldState22.getText(), this.textfieldState23.getText());
}//GEN-LAST:event_jButton96ActionPerformed

private void jButton99ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton99ActionPerformed
    File f = new File("g:/prps/pots/sfx/");
    Vector<String> pots = new Vector<String>();
    for(File f2: f.listFiles())
    {
        String name = f2.getName();
        if(f2.isFile() && name.toLowerCase().endsWith(".ogg"))
        {
            pots.add(name);
        }
    }
    
    File f3 = new File("g:/prps/mouloffline/sfx/");
    Vector<String> moul = new Vector<String>();
    for(File f2: f3.listFiles())
    {
        String name = f2.getName();
        if(f2.isFile() && name.toLowerCase().endsWith(".ogg"))
        {
            moul.add(name);
        }
    }
    File f4 = new File("g:/prps/mystv/sfx/");
    Vector<String> mystv = new Vector<String>();
    for(File f2: f4.listFiles())
    {
        String name = f2.getName();
        if(f2.isFile() && name.toLowerCase().endsWith(".ogg"))
        {
            mystv.add(name);
        }
    }
    
    
    //find the sfx that are in mystv, but not pots
    Vector<String> mystvNotPots = new Vector<String>();
    for(String f1: mystv)
    {
        boolean cond = true;
        for(String f2: pots)
        {
            if(f1.toLowerCase().equals(f2.toLowerCase()))
            {
                cond = false;
                break;
            }
        }
        if(cond) mystvNotPots.add(f1);
    }
    
    //find the sfx that are in mystv, but not pots nor moul
    Vector<String> mystvNotPotsNorMoul = new Vector<String>();
    for(String f1: mystv)
    {
        boolean cond = true;
        for(String f2: pots)
        {
            if(f1.toLowerCase().equals(f2.toLowerCase()))
            {
                cond = false;
                break;
            }
        }
        if(cond)
        {
            for(String f2: moul)
            {
                if(f1.toLowerCase().equals(f2.toLowerCase()))
                {
                    cond = false;
                    break;
                }
            }
        }
        if(cond) mystvNotPotsNorMoul.add(f1);
    }
    
    StringBuilder sb = new StringBuilder();
    for(String s: mystvNotPotsNorMoul)
    {
        sb.append("\""+s+"\",");
    }
    String sb2 = sb.toString();
    shared.clipboard.SetString(sb2);
    
    int dummy=0;
}//GEN-LAST:event_jButton99ActionPerformed

private void jButton100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton100ActionPerformed
    String in = textfieldState18.getText();
    String outfolder = textfieldState19.getText();
    
    automation.inplaceModifications.addDynamicTextMapAndMiscToFile(in, outfolder);
}//GEN-LAST:event_jButton100ActionPerformed

private void jButton95ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton95ActionPerformed
    GuiUtils.getUserSelectedFile(this.textfieldState21);
}//GEN-LAST:event_jButton95ActionPerformed

private void jButton101ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton101ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState24);
}//GEN-LAST:event_jButton101ActionPerformed

private void jButton103ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton103ActionPerformed
    GuiUtils.getUserSelectedFile(this.textfieldState25);
}//GEN-LAST:event_jButton103ActionPerformed

private void jButton102ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton102ActionPerformed
    String filename = this.textfieldState25.getText();
    m.state.curstate.writeToFile = true;
    File f = new File(filename);
    shared.IBytestream bs = shared.SerialBytestream.createFromFilename(filename);
    realmyst.Hsm hsm;
    realmyst.Idx idx;
    realmyst.Mdb mdb;
    realmyst.Sdb sdb;
    realmyst.Shp shp;
    if(filename.toLowerCase().endsWith(".hsm"))
    {
        hsm = new realmyst.Hsm(bs,f.getName());
    }
    else if(filename.toLowerCase().endsWith(".idx"))
    {
        idx = new realmyst.Idx(bs);
    }
    else if(filename.toLowerCase().endsWith(".vdb"))
    {
        if(f.getParentFile().getName().toLowerCase().equals("sdb"))
        {
            sdb = new realmyst.Sdb(bs);
            for(int i=0;i<sdb.count2s.length;i++)
            {
                realmyst.Count2 c2 = sdb.count2s[i];
                String curtex = c2.sb2.str.toString();
                if(curtex.equals("GBdock.hsm"))
                {
                    int dummy=0;
                }
            }
        }
        else if(f.getParentFile().getName().toLowerCase().equals("mdb"))
            mdb = new realmyst.Mdb(bs,"none");
    }
    else if(filename.toLowerCase().endsWith(".shp"))
    {
        shp = new realmyst.Shp(bs);
    }
    else if(filename.toLowerCase().endsWith(".wav"))
    {
        m.msg("It's just a normal wav file.");
    }
    else if(filename.toLowerCase().endsWith(".dat"))
    {
        m.msg("It's just a text file.");
    }
    else if(filename.toLowerCase().endsWith(".beh"))
    {
        m.err("Haven't implemented .beh parsing.");
    }
    //realmyst.Idx idx = new realmyst.Idx(bs);
    //realmyst.SceneObject so = new realmyst.SceneObject(bs);
    //realmyst.Sdb mdb = new realmyst.Sdb(bs);
    if(bs.getBytesRemaining()!=0) m.warn("Didn't read all data.");
}//GEN-LAST:event_jButton102ActionPerformed

private void jButton104ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton104ActionPerformed
    String outfol = this.textfieldState24.getText();
    m.state.push();
    m.state.curstate.writeToFile = true;
    
    Vector<realmyst.Sdb> sdbs = automation.realmyst.readAllSdbs(outfol);
    String[] rooms = automation.realmyst.findRoomInfo(sdbs,"Myst");
    
    m.state.pop();
    
    m.msg("Subcount3s handled: ",Integer.toString(realmyst.Count3Undone.subcount3.numhandled));
    m.msg("Subcount3s ignored: ",Integer.toString(realmyst.Count3Undone.subcount3.numignored));
    m.msg("Count3s handled: ",Integer.toString(realmyst.Count3Undone.numhandled));
    m.msg("Count3s ignored: ",Integer.toString(realmyst.Count3Undone.numignored));
    
    /*File f2 = new File(outfol+"/mdb");
    for(File child: f2.listFiles())
    {
        if(child.getName().toLowerCase().endsWith(".vdb"))
        {
            int fs = (int)child.length();
            shared.IBytestream bs = shared.SerialBytestream.createFromFile(child);
            realmyst.Mdb mdb = new realmyst.Mdb(bs);
            int offset = bs.getAbsoluteOffset();
            int bytesleft = bs.getBytesRemaining();
            
            if (mdb.u2!=fs-offset)
            {
                int dummy=0;
            }
            if(bytesleft!=0)
            {
                int dummy=0;
            }
            int dummy=0;
        }
    }*/
    
}//GEN-LAST:event_jButton104ActionPerformed

private void jButton105ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton105ActionPerformed
    GuiUtils.showTrayIcon("/gui/Pterosaur2b4-16.png");
    String address = this.textfieldState26.getText();
    String timer = this.textfieldState27.getText();
    double timer2 = Double.parseDouble(timer);
    String search = this.textfieldState52.getText();
    checker.nettimer.timer(address, timer2, search);
}//GEN-LAST:event_jButton105ActionPerformed

private void jButton106ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton106ActionPerformed
    String outfol = this.textfieldState24.getText();
    m.state.push();
    m.state.curstate.writeToFile = true;
    
    Vector<realmyst.Mdb> mdbs = automation.realmyst.readAllMdbs(outfol);
    automation.realmyst.save3dsFile(mdbs);

    m.state.pop();
}//GEN-LAST:event_jButton106ActionPerformed

private void jButton107ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton107ActionPerformed
    shared.State.AllStates.pullandsave(settingsfile);
}//GEN-LAST:event_jButton107ActionPerformed

private void jButton108ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton108ActionPerformed
    String outfol = this.textfieldState24.getText();
    //m.state.curstate.writeToFile = true;
    
    Vector<realmyst.Hsm> hsms = automation.realmyst.readAllHsms(outfol);
    //automation.realmyst.save3dsFile(mdbs);
    automation.realmyst.saveDdsFiles(hsms,"c:/hsmout");

}//GEN-LAST:event_jButton108ActionPerformed

private void jButton109ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton109ActionPerformed
    //String folder = "G:/prps/realmysttest2/sdb";
    String sep = ";";
    String folder = this.textfieldState30.getText();
    String searchstr = this.textfieldState29.getText();
    String[] searchstrs = {
        //6910138.vdb
        //"channelwood",
        //"ch_bookroom",
        //"channel_temple",
        //"windmill",
        //"ch_sirrus01",
        //"ch_waterhut01",
        //"ch_cylinder_f",
        //"ch_achenar01",
        
        //85660192.vdb
        //"me_achenar02",
        //"me_achenar01",
        //"me_pass02",
        //"mech_indoor",
        //"me_bookroom",
        //"mech_outdoor",
        //"me_conpane",
        
        //"se_stair04",
        //"selenitic",
        //"se_stair03",
        //"se_stair02",
        
        //"cabin",
        //"treegate",
        //"rocket",
        //"fireplace",
        
        ////"stoneship01",
        ////"sn_island_laboratory",
        //"snowage",
        //"aurora",
        
        //"sship_aku_room",
        //"ss_akk04",
        //"ss_deep_sea",
        //"compus_room",
        //"sship_tunnel",
        //"lighthouse",
        
        "myst..base_mountain"
    };
    searchstrs = searchstr.split(sep);
    //String[] searchstrs2 = new String[]{this.textfieldState29.getText()};
    Vector<File> files = filesearcher.search.getallfiles(folder, false);
    for(File f: files)
    {
        //boolean allfound = filesearcher.search.searchForStrings(f, searchstrs);
        //if(allfound)
        if(searchstrs.length<2)
        {
            int pos = filesearcher.search.searchForStringPos(f, searchstr);
            if(pos!=-1)
            {
                String filename = f.getName();
                m.msg("String found in file:",filename,"  at pos 0x",Integer.toHexString(pos));
                int dummy=0;
            }
        }
        else
        {
            boolean allfound = filesearcher.search.searchForStrings(f, searchstrs);
            if(allfound)
            {
                m.msg("Strings all found in file:",f.getName());
            }
        }
    }
}//GEN-LAST:event_jButton109ActionPerformed

private void jButton110ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton110ActionPerformed

    String outfol = this.textfieldState24.getText();
    
    Vector<realmyst.Sdb> sdbs = automation.realmyst.readAllSdbs(outfol);
    
    Vector<realmyst.Mdb> mdbs = automation.realmyst.readAllMdbs(outfol);
    //String[] mystrooms = automation.realmyst.findRoomInfo(sdbs,"Myst");
    //Vector<realmyst.Mdb> mystmdbs = automation.realmyst.filterMdbsByRoom(mdbs, mystrooms);
    //automation.realmyst.save3dsFile(mystmdbs);

    automation.realmyst.testrun2(sdbs,mdbs);
    
}//GEN-LAST:event_jButton110ActionPerformed

private void jButton112ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton112ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState31);
}//GEN-LAST:event_jButton112ActionPerformed

private void jButton113ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton113ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState32);
}//GEN-LAST:event_jButton113ActionPerformed

private void jButton111ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton111ActionPerformed
    String in = this.textfieldState31.getText();
    String out = this.textfieldState32.getText();
    automation.hexisle.convert(in,out);
}//GEN-LAST:event_jButton111ActionPerformed

private void jButton115ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton115ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState33);
}//GEN-LAST:event_jButton115ActionPerformed

private void jButton116ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton116ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState34);
}//GEN-LAST:event_jButton116ActionPerformed

private void jButton114ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton114ActionPerformed
    automation.moul.convertMoul(this.textfieldState33.getText(), this.textfieldState34.getText());
}//GEN-LAST:event_jButton114ActionPerformed

private void jButton118ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton118ActionPerformed
    deep.reportRefs();
}//GEN-LAST:event_jButton118ActionPerformed

private void jButton117ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton117ActionPerformed
    deep.reportStrings();
}//GEN-LAST:event_jButton117ActionPerformed

private void jButton120ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton120ActionPerformed
    GuiUtils.getUserSelectedFile(this.textfieldState35);
}//GEN-LAST:event_jButton120ActionPerformed

private void jButton119ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton119ActionPerformed
    //uru.vault.vfile vf = uru.vault.vfile.createFromFilename(this.textfieldState35.getText());
    uru.vault.vaultdatfile vdf = uru.vault.vaultdatfile.createFromFilename(this.textfieldState35.getText());
}//GEN-LAST:event_jButton119ActionPerformed

private void jButton121ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton121ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState36);
}//GEN-LAST:event_jButton121ActionPerformed

private void jButton122ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton122ActionPerformed
    automation.vaultAutomation.saveImages(this.textfieldState36.getText(),this.textfieldState37.getText());
}//GEN-LAST:event_jButton122ActionPerformed

private void jButton123ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton123ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState37);
}//GEN-LAST:event_jButton123ActionPerformed

private void jButton124ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton124ActionPerformed
    gui.UamGui.GetAgeListGui(this.textfieldState38.getText(),this.textfieldState39.getText());
}//GEN-LAST:event_jButton124ActionPerformed

private void jButton125ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton125ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState39);
}//GEN-LAST:event_jButton125ActionPerformed

private void jButton126ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton126ActionPerformed
    uam.Uam.listAvailableAges();
}//GEN-LAST:event_jButton126ActionPerformed

private void jButton127ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton127ActionPerformed
    gui.UamGui.PerformDownload();
}//GEN-LAST:event_jButton127ActionPerformed

private void jButton128ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton128ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(textfieldState15.getText());
        byte[] hash = shared.CryptHashes.GetWhirlpool(filecontents);
        String hashstr = b.BytesToHexString(hash);
        m.msg("Whirlpool: ",hashstr);
}//GEN-LAST:event_jButton128ActionPerformed

private void jButton129ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton129ActionPerformed
    gui.UamGui.PerformDeletionAction();
}//GEN-LAST:event_jButton129ActionPerformed

private void jButton130ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton130ActionPerformed
    automation.crowthistle.convertCrowthistle(this.textfieldState40.getText(),this.textfieldState41.getText());
}//GEN-LAST:event_jButton130ActionPerformed

private void jButton131ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton131ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState40);
}//GEN-LAST:event_jButton131ActionPerformed

private void jButton132ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton132ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState41);
}//GEN-LAST:event_jButton132ActionPerformed

private void jButton134ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton134ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState42);
}//GEN-LAST:event_jButton134ActionPerformed

private void jButton133ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton133ActionPerformed
    //uam.UamConfigGenerator.generateStatusFile(this.textfieldState42.getText());
    uam.UamConfigNew.generateStatusFile(this.textfieldState42.getText());
}//GEN-LAST:event_jButton133ActionPerformed

private void textfieldState38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldState38ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_textfieldState38ActionPerformed

private void jButton135ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton135ActionPerformed
    uam.Uam.launchUru();
}//GEN-LAST:event_jButton135ActionPerformed

private void jButton136ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton136ActionPerformed
    GuiUtils.getStringFromUser(this.textfieldState38,"Enter the server you want to use.","Enter a server.");
}//GEN-LAST:event_jButton136ActionPerformed

private void jButton137ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton137ActionPerformed
    gui.UamGui.GetAgeListGuiOffline(this.textfieldState39.getText());
}//GEN-LAST:event_jButton137ActionPerformed

private void jButton138ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton138ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(textfieldState15.getText());
        java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(filecontents);
        byte[] hash = shared.CryptHashes.GetHash(in, shared.CryptHashes.Hashtype.sha1);
        String hashstr = b.BytesToHexString(hash);
        m.msg("Sha1: ",hashstr);
}//GEN-LAST:event_jButton138ActionPerformed

private void jButton139ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton139ActionPerformed
    uam.Uam.RunTests(this.textfieldState39.getText());
}//GEN-LAST:event_jButton139ActionPerformed

private void jButton140ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton140ActionPerformed
    shared.Exec.LaunchProgram(this.textfieldState23.getText()+"/"+"SoundDecompress.exe", "SoundDecompress");
}//GEN-LAST:event_jButton140ActionPerformed

private void jButton141ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton141ActionPerformed
    shared.Exec.LaunchProgram(this.textfieldState34.getText()+"/"+"SoundDecompress.exe", "SoundDecompress");
}//GEN-LAST:event_jButton141ActionPerformed

private void jButton142ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton142ActionPerformed
    shared.Exec.LaunchProgram(this.textfieldState41.getText()+"/"+"SoundDecompress.exe", "SoundDecompress");
}//GEN-LAST:event_jButton142ActionPerformed

private void jButton143ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton143ActionPerformed
    String msg = this.textfieldState44.getText();
    alcugsinterface.parser.testmsg(msg);
}//GEN-LAST:event_jButton143ActionPerformed

private void jButton145ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton145ActionPerformed
    automation.pots.CreateAgeReport(this.textfieldState39.getText(), this.textfieldState45.getText());
}//GEN-LAST:event_jButton145ActionPerformed

private void textfieldState47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldState47ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_textfieldState47ActionPerformed

private void jButton146ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton146ActionPerformed
    wikispider.wikispider.start(this.textfieldState46.getText(),this.textfieldState47.getText());
}//GEN-LAST:event_jButton146ActionPerformed

private void jButton147ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton147ActionPerformed
    GuiUtils.getUserSelectedFolder(this.textfieldState47);
}//GEN-LAST:event_jButton147ActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if(!uru.moulprp.Typeid.validateTriplets()) m.err("Triplets not valid!");
}//GEN-LAST:event_jButton1ActionPerformed

private void textfieldState37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textfieldState37ActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_textfieldState37ActionPerformed

private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
    String age = uam.Uam.GetRandomAge();
    m.msg(age);
}//GEN-LAST:event_jButton15ActionPerformed

private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
    //GuiUtils.showTrayIcon("/gui/Pterosaur2b4-16.png");
    String address = this.textfieldState26.getText();
    String timer = this.textfieldState27.getText();
    double timer2 = Double.parseDouble(timer);
    String outfolder = this.textfieldState28.getText();
    checker.nettimer.SavePageAtTime(address,timer2,outfolder,"",".html");
}//GEN-LAST:event_jButton16ActionPerformed

private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
    checker.nettimer.CancelTimers();
}//GEN-LAST:event_jButton18ActionPerformed

private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
    GuiUtils.getUserSelectedFile(this.textfieldState50);
}//GEN-LAST:event_jButton19ActionPerformed

private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
    automation.myst5Fixes.CopyMusic(this.textfieldState22.getText(),this.textfieldState23.getText());
}//GEN-LAST:event_jButton30ActionPerformed

private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
    automation.moul.CopyMusic(this.textfieldState33.getText(),this.textfieldState34.getText());
}//GEN-LAST:event_jButton31ActionPerformed

private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
    automation.crowthistle.CopyMusic(this.textfieldState40.getText(),this.textfieldState41.getText());
}//GEN-LAST:event_jButton34ActionPerformed

private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
    automation.pots.CopyMusic(this.textfieldState34.getText(),this.textfieldState34.getText());
}//GEN-LAST:event_jButton36ActionPerformed

private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
    //GuiUtils.showTrayIcon("/gui/Pterosaur2b4-16.png");
    String address = this.textfieldState26.getText();
    String outfolder = this.textfieldState28.getText();
    String datetime = this.textfieldState53.getText();
    checker.nettimer.SavePageAtSpecificTime(address,outfolder,datetime,"",".html");
}//GEN-LAST:event_jButton37ActionPerformed

private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
    translation.translation.saveCurrentStrings();
}//GEN-LAST:event_jButton38ActionPerformed

private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
    translation.translation.testCurrentLanguage();
}//GEN-LAST:event_jButton40ActionPerformed

private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
    //translation.translation.setLanguage("en");
    pushlanguage(false);
}//GEN-LAST:event_jRadioButton1ActionPerformed

private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
    //translation.translation.setLanguage("de");
    pushlanguage(false);
}//GEN-LAST:event_jRadioButton2ActionPerformed

private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
    //translation.translation.setLanguage("fr");
    pushlanguage(false);
}//GEN-LAST:event_jRadioButton3ActionPerformed

private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
    translation.translation.saveMissingTranslations();
}//GEN-LAST:event_jButton46ActionPerformed
public void SetBackgroundColour(boolean immediate)
{
    int newrain = this.sliderState1.getValue();
    double newrainpercentage = ((double)newrain)/100.0;
    shared.ColorUtils.RGB24 rgb24 = new shared.ColorUtils.RGB24(174, 178, 195);
    shared.ColorUtils.RGB rgb = rgb24.convertToRGB();

    boolean usehsv = true;
    java.awt.Color newcolour;
    if(usehsv)
    {
        shared.ColorUtils.HSV hsv = rgb.convertToHSV();
        hsv.v = newrainpercentage;
        newcolour = hsv.convertToRGB().convertToRGB24().convertToJavaColor();
    }
    else
    {
        shared.ColorUtils.HSL hsl = rgb.convertToHSL();
        hsl.l = newrainpercentage;
        newcolour = hsl.convertToRGB().convertToRGB24().convertToJavaColor();
    }

    //final Gui gui23 = this;
    //m.msg("setcolor");
    shared.GuiUtils.setBackgroundColour(newcolour);
    //m.msg(newcolour.toString());
    shared.GuiUtils.updateGui(this,immediate);
}
private void sliderState1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderState1StateChanged
    //change the raininess! //174,178,195
    //;
    //m.msg("trying to change colour."+Boolean.toString(this.sliderState1.getValueIsAdjusting()));
    if(!this.sliderState1.getValueIsAdjusting())
    {
        SetBackgroundColour(false);
    }
}//GEN-LAST:event_sliderState1StateChanged

private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
    //automation.AutoMod.AutoMod(textfieldState18.getText(), textfieldState19.getText(), textfieldState54.getText());
    File f = new File(textfieldState18.getText());
    automation.AutoMod.AutoMod(f.getParentFile().getParent(), textfieldState19.getText(), f.getName(), textfieldState54.getText(), false);
}//GEN-LAST:event_jButton41ActionPerformed

private void jButton144ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton144ActionPerformed
    GuiUtils.getUserSelectedFolder(textfieldState54);
}//GEN-LAST:event_jButton144ActionPerformed

private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
    pushlanguage(false);
}//GEN-LAST:event_jRadioButton4ActionPerformed
    
/*class c2 extends javax.swing.DefaultListSelectionModel
{
    this.
}*/

    /*private String getUserSelectedFolder()
    {
        if(fc==null) fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int wasFileChosen = fc.showOpenDialog(null);
        if(wasFileChosen==0)
        {
            String file = fc.getSelectedFile().getAbsolutePath();
            return file;
        }
        return "";
    }*/
    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }*/
    
    /*public void message(String msg)
    {
        String finalmsg = msg + "\n";
        this.jTextArea1.append(finalmsg);
    }*/
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private shared.State.TabsState AdvTabState;
    private javax.swing.JPanel GHelpTab;
    private javax.swing.JPanel HelpTab;
    private javax.swing.JPanel MemTab;
    private javax.swing.JPanel MiscPanel;
    private javax.swing.JPanel ProxyTab;
    private javax.swing.JPanel SimpTab;
    private javax.swing.JPanel UAMTab;
    private javax.swing.JLabel ageInfo3;
    private shared.State.CheckboxState checkboxState1;
    private shared.State.CheckboxState checkboxState10;
    private shared.State.CheckboxState checkboxState11;
    private shared.State.CheckboxState checkboxState12;
    private shared.State.CheckboxState checkboxState13;
    private shared.State.CheckboxState checkboxState14;
    private shared.State.CheckboxState checkboxState15;
    private shared.State.CheckboxState checkboxState16;
    private shared.State.CheckboxState checkboxState17;
    private shared.State.CheckboxState checkboxState18;
    private shared.State.CheckboxState checkboxState19;
    private shared.State.CheckboxState checkboxState2;
    private shared.State.CheckboxState checkboxState20;
    private shared.State.CheckboxState checkboxState21;
    private shared.State.CheckboxState checkboxState22;
    private shared.State.CheckboxState checkboxState23;
    private shared.State.CheckboxState checkboxState24;
    private shared.State.CheckboxState checkboxState25;
    private shared.State.CheckboxState checkboxState3;
    private shared.State.CheckboxState checkboxState4;
    private shared.State.CheckboxState checkboxState5;
    private shared.State.CheckboxState checkboxState6;
    private shared.State.CheckboxState checkboxState7;
    private shared.State.CheckboxState checkboxState8;
    private shared.State.CheckboxState checkboxState9;
    private relationvis.CrossRefPanel crossRefPanel1;
    private javax.swing.ButtonGroup filedirButtonGroup;
    private shared.ImagePanel imagePanel1;
    private shared.ImagePanel imagePanel2;
    private shared.ImagePanel imagePanel3;
    private shared.ImagePanel imagePanel4;
    private shared.ImagePanel imagePanel5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton100;
    private javax.swing.JButton jButton101;
    private javax.swing.JButton jButton102;
    private javax.swing.JButton jButton103;
    private javax.swing.JButton jButton104;
    private javax.swing.JButton jButton105;
    private javax.swing.JButton jButton106;
    private javax.swing.JButton jButton107;
    private javax.swing.JButton jButton108;
    private javax.swing.JButton jButton109;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton110;
    private javax.swing.JButton jButton111;
    private javax.swing.JButton jButton112;
    private javax.swing.JButton jButton113;
    private javax.swing.JButton jButton114;
    private javax.swing.JButton jButton115;
    private javax.swing.JButton jButton116;
    private javax.swing.JButton jButton117;
    private javax.swing.JButton jButton118;
    private javax.swing.JButton jButton119;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton120;
    private javax.swing.JButton jButton121;
    private javax.swing.JButton jButton122;
    private javax.swing.JButton jButton123;
    private javax.swing.JButton jButton124;
    private javax.swing.JButton jButton125;
    private javax.swing.JButton jButton126;
    private javax.swing.JButton jButton127;
    private javax.swing.JButton jButton128;
    private javax.swing.JButton jButton129;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton130;
    private javax.swing.JButton jButton131;
    private javax.swing.JButton jButton132;
    private javax.swing.JButton jButton133;
    private javax.swing.JButton jButton134;
    private javax.swing.JButton jButton135;
    private javax.swing.JButton jButton136;
    private javax.swing.JButton jButton137;
    private javax.swing.JButton jButton138;
    private javax.swing.JButton jButton139;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton140;
    private javax.swing.JButton jButton141;
    private javax.swing.JButton jButton142;
    private javax.swing.JButton jButton143;
    private javax.swing.JButton jButton144;
    private javax.swing.JButton jButton145;
    private javax.swing.JButton jButton146;
    private javax.swing.JButton jButton147;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JButton jButton74;
    private javax.swing.JButton jButton75;
    private javax.swing.JButton jButton76;
    private javax.swing.JButton jButton77;
    private javax.swing.JButton jButton78;
    private javax.swing.JButton jButton79;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton80;
    private javax.swing.JButton jButton81;
    private javax.swing.JButton jButton83;
    private javax.swing.JButton jButton84;
    private javax.swing.JButton jButton85;
    private javax.swing.JButton jButton86;
    private javax.swing.JButton jButton87;
    private javax.swing.JButton jButton88;
    private javax.swing.JButton jButton89;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton90;
    private javax.swing.JButton jButton91;
    private javax.swing.JButton jButton92;
    private javax.swing.JButton jButton93;
    private javax.swing.JButton jButton94;
    private javax.swing.JButton jButton95;
    private javax.swing.JButton jButton96;
    private javax.swing.JButton jButton97;
    private javax.swing.JButton jButton98;
    private javax.swing.JButton jButton99;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private shared.State.TextareaStateless jTextArea1;
    private shared.State.TextareaStateless jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private shared.State.TextareaStateless jTextArea5;
    private shared.State.TextareaStateless jTextArea6;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private shared.State.ButtongroupState languageButtonGroup;
    private shared.State.ListState listState2;
    private shared.State.ListState listState3;
    private shared.State.ListState listState4;
    private shared.State.SliderState sliderState1;
    private shared.State.TabsState tabsState1;
    private shared.State.TabsState tabsState2;
    private shared.State.TabsState tabsState3;
    private shared.State.TextareaState textareaState1;
    private shared.State.TextfieldState textfieldState1;
    private shared.State.TextfieldState textfieldState10;
    private shared.State.TextfieldState textfieldState11;
    private shared.State.TextfieldState textfieldState12;
    private shared.State.TextfieldState textfieldState13;
    private shared.State.TextfieldState textfieldState14;
    private shared.State.TextfieldState textfieldState15;
    private shared.State.TextfieldState textfieldState16;
    private shared.State.TextfieldState textfieldState17;
    private shared.State.TextfieldState textfieldState18;
    private shared.State.TextfieldState textfieldState19;
    private shared.State.TextfieldState textfieldState2;
    private shared.State.TextfieldState textfieldState20;
    private shared.State.TextfieldState textfieldState21;
    private shared.State.TextfieldState textfieldState22;
    private shared.State.TextfieldState textfieldState23;
    private shared.State.TextfieldState textfieldState24;
    private shared.State.TextfieldState textfieldState25;
    private shared.State.TextfieldState textfieldState26;
    private shared.State.TextfieldState textfieldState27;
    private shared.State.TextfieldState textfieldState28;
    private shared.State.TextfieldState textfieldState29;
    private shared.State.TextfieldState textfieldState3;
    private shared.State.TextfieldState textfieldState30;
    private shared.State.TextfieldState textfieldState31;
    private shared.State.TextfieldState textfieldState32;
    private shared.State.TextfieldState textfieldState33;
    private shared.State.TextfieldState textfieldState34;
    private shared.State.TextfieldState textfieldState35;
    private shared.State.TextfieldState textfieldState36;
    private shared.State.TextfieldState textfieldState37;
    private shared.State.TextfieldState textfieldState38;
    private shared.State.TextfieldState textfieldState39;
    private shared.State.TextfieldState textfieldState4;
    private shared.State.TextfieldState textfieldState40;
    private shared.State.TextfieldState textfieldState41;
    private shared.State.TextfieldState textfieldState42;
    private shared.State.TextfieldState textfieldState43;
    private shared.State.TextfieldState textfieldState44;
    private shared.State.TextfieldState textfieldState45;
    private shared.State.TextfieldState textfieldState46;
    private shared.State.TextfieldState textfieldState47;
    private shared.State.TextfieldState textfieldState48;
    private shared.State.TextfieldState textfieldState49;
    private shared.State.TextfieldState textfieldState5;
    private shared.State.TextfieldState textfieldState50;
    private shared.State.TextfieldState textfieldState51;
    private shared.State.TextfieldState textfieldState52;
    private shared.State.TextfieldState textfieldState53;
    private shared.State.TextfieldState textfieldState54;
    private shared.State.TextfieldState textfieldState6;
    private shared.State.TextfieldState textfieldState7;
    private shared.State.TextfieldState textfieldState8;
    private shared.State.TextfieldState textfieldState9;
    private shared.State.ButtongroupState uamStartupButtongroup;
    // End of variables declaration//GEN-END:variables
    
}
