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
import relationvis.visualisation;
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
    visualisation vis = null;
    
    String moul;
    String pots;
    String out;
    String settingsfile;
    Settings settings = new Settings();
    boolean dosavesettings = false;
    
    deepview.deepview deep;
    
    /** Creates new form Gui */
    public Gui()
    {
        initComponents();
        
        deep = new deepview.deepview(jDesktopPane1);
        gui.UamGui.gui = this;
        gui.UamGui.agelist = this.jList1;
        gui.UamGui.verlist = this.jList2;
        gui.UamGui.mirlist = this.jList3;
        gui.UamGui.init();
        
        GuiUtils.SetKeymaps();
        
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
        if(Runtime.getRuntime().maxMemory()<256000000) m.warn("You should run Drizzle with a larger maximum heap space. E.g.: java -Xmx800m -jar DrizzlePrp.jar");
        
        //try{
        //java.net.URL url = this.getClass().getResource("Image2.png");
        //javax.swing.ImageIcon image = new javax.swing.ImageIcon(url,"");
        //java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(url);
        //java.awt.Image img = shared.GetResource.getResourceAsImage(this, "Image2.png");
        //this.jPanel32.getGraphics().drawImage(img, 0, 0, rootPane);
        //this.setIconImage(image.getImage());
        //}catch(Exception e){}
        
        String helpstr = shared.GetResource.getResourceAsString("/gui/help.txt");
        jTextArea5.setText(helpstr);
        //jTextArea5.repaint();
        //jTextArea5.scrollRectToVisible(new java.awt.Rectangle(0,0,1,1));

        
        jComboBox1.setEditable(true);
        this.jComboBox1.removeAllItems();
        //pots = "D:/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/Uru - Ages Beyond Myst/dat/";
        //moul = "D:/a/winedrive/drive_c/MystOnline/Program Files/Myst Online/dat/";
        out = "C:/Documents and Settings/user/Desktop/output/doesnotexist/";
        //pots = "/shared/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/Uru - Ages Beyond Myst/dat/";
        //moul = "/shared/a/winedrive/drive_c/MystOnline/Program Files/Myst Online/dat/";
        //out = "/shared/a/leftoff/output/";
        pots = "D:/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/Uru - Ages Beyond Myst/dat/doesnotexist/";
        moul = "D:/a/winedrive/drive_c/MystOnline/Program Files/Myst Online/dat/doesnotexist/";
        //out = "D:/a/leftoff/output/";
        settingsfile = FileUtils.GetPresentWorkingDirectory()+"drizzlesettings.canbedeleted.dat";
        uru.moulprp._staticsettings.outputdir = out;

        this.jComboBox1.addItem("");
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
        this.jComboBox1.addItem(moul);
        if(!uru.moulprp.Typeid.validateTriplets()) m.err("Triplets not valid!");
        
        this.loadsettings();
        dosavesettings = true;
    }
    public static class Settings implements java.io.Serializable
    {
        String curfile;
        int curfolder;
        
    }
    public void savesettings()
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
    }
    public void loadsettings()
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
    }
    private String getSelectedFilename()
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
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filedirButtonGroup = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jButton61 = new javax.swing.JButton();
        jButton107 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        tabsState3 = new shared.State.TabsState();
        jPanel32 = new javax.swing.JPanel();
        tabsState1 = new shared.State.TabsState();
        jPanel34 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        textfieldState22 = new shared.State.TextfieldState();
        jLabel20 = new javax.swing.JLabel();
        textfieldState23 = new shared.State.TextfieldState();
        jButton96 = new javax.swing.JButton();
        jButton97 = new javax.swing.JButton();
        jButton98 = new javax.swing.JButton();
        checkboxState23 = new shared.State.CheckboxState();
        jPanel35 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        textfieldState33 = new shared.State.TextfieldState();
        textfieldState34 = new shared.State.TextfieldState();
        jButton114 = new javax.swing.JButton();
        jButton115 = new javax.swing.JButton();
        jButton116 = new javax.swing.JButton();
        jPanel40 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        textfieldState40 = new shared.State.TextfieldState();
        textfieldState41 = new shared.State.TextfieldState();
        jLabel42 = new javax.swing.JLabel();
        jButton130 = new javax.swing.JButton();
        jButton131 = new javax.swing.JButton();
        jButton132 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jButton124 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        textfieldState38 = new shared.State.TextfieldState();
        textfieldState39 = new shared.State.TextfieldState();
        jButton125 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jButton126 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jScrollPane12 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jButton127 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jButton129 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
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
        jLabel30 = new javax.swing.JLabel();
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
        jPanel37 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        textfieldState31 = new shared.State.TextfieldState();
        textfieldState32 = new shared.State.TextfieldState();
        jButton111 = new javax.swing.JButton();
        jButton112 = new javax.swing.JButton();
        jButton113 = new javax.swing.JButton();
        jPanel38 = new javax.swing.JPanel();
        textfieldState35 = new shared.State.TextfieldState();
        jButton119 = new javax.swing.JButton();
        jButton120 = new javax.swing.JButton();
        textfieldState36 = new shared.State.TextfieldState();
        jButton121 = new javax.swing.JButton();
        jButton122 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        textfieldState37 = new shared.State.TextfieldState();
        jButton123 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton15 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton34 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
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
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton23 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton39 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jButton49 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jButton42 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jButton35 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButton43 = new javax.swing.JButton();
        jPanel36 = new javax.swing.JPanel();
        jButton105 = new javax.swing.JButton();
        textfieldState26 = new shared.State.TextfieldState();
        textfieldState27 = new shared.State.TextfieldState();
        textfieldState28 = new shared.State.TextfieldState();
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
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jPanel28 = new javax.swing.JPanel();
        jButton70 = new javax.swing.JButton();
        textfieldState9 = new shared.State.TextfieldState();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textfieldState10 = new shared.State.TextfieldState();
        jButton71 = new javax.swing.JButton();
        checkboxState10 = new shared.State.CheckboxState();
        jLabel17 = new javax.swing.JLabel();
        jButton77 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jButton72 = new javax.swing.JButton();
        jPanel30 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        textfieldState11 = new shared.State.TextfieldState();
        jButton73 = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        textfieldState12 = new shared.State.TextfieldState();
        jButton74 = new javax.swing.JButton();
        jButton75 = new javax.swing.JButton();
        jButton76 = new javax.swing.JButton();
        jButton87 = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        textfieldState13 = new shared.State.TextfieldState();
        jButton80 = new javax.swing.JButton();
        jButton81 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jButton82 = new javax.swing.JButton();
        jButton83 = new javax.swing.JButton();
        jButton84 = new javax.swing.JButton();
        textfieldState14 = new shared.State.TextfieldState();
        jButton85 = new javax.swing.JButton();
        jButton117 = new javax.swing.JButton();
        jButton118 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        imagePanel2 = new shared.ImagePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Drizzle");
        setBounds(new java.awt.Rectangle(0, 0, 600, 600));
        setMinimumSize(new java.awt.Dimension(760, 550));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel26.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton61.setText("Delete settings and exit");
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });

        jButton107.setText("Save settings");
        jButton107.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton107ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton107))
                    .addComponent(jButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton107)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jPanel27.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setText("Clear log.");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton20.setText("remove dupes.");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton54.setText("Alphabetise.");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton54)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabsState3.setName("mainTabs"); // NOI18N

        tabsState1.setName("simplicitySubTabs"); // NOI18N

        jPanel34.setLayout(null);

        jLabel19.setText("Myst 5 Folder:");
        jPanel34.add(jLabel19);
        jLabel19.setBounds(40, 30, 75, 16);

        textfieldState22.setText("Click the select button to pick a folder.");
        textfieldState22.setName("simpleMystvIn"); // NOI18N
        jPanel34.add(textfieldState22);
        textfieldState22.setBounds(130, 30, 340, 20);

        jLabel20.setText("Pots Folder:");
        jPanel34.add(jLabel20);
        jLabel20.setBounds(40, 70, 66, 16);

        textfieldState23.setText("Click the select button to pick a folder.");
        textfieldState23.setName("simpleMystvOut"); // NOI18N
        jPanel34.add(textfieldState23);
        textfieldState23.setBounds(130, 70, 340, 20);

        jButton96.setText("Start...");
        jButton96.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton96ActionPerformed(evt);
            }
        });
        jPanel34.add(jButton96);
        jButton96.setBounds(30, 110, 58, 36);

        jButton97.setText("select...");
        jButton97.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton97ActionPerformed(evt);
            }
        });
        jPanel34.add(jButton97);
        jButton97.setBounds(480, 20, 80, 36);

        jButton98.setText("select...");
        jButton98.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton98ActionPerformed(evt);
            }
        });
        jPanel34.add(jButton98);
        jButton98.setBounds(480, 60, 80, 36);

        checkboxState23.setText("Include new content (i.e. footstep sounds).");
        checkboxState23.setName("includeAuthoredMaterial"); // NOI18N
        jPanel34.add(checkboxState23);
        checkboxState23.setBounds(110, 170, 280, 28);

        tabsState1.addTab("MystV", jPanel34);

        jLabel31.setText("Moul folder:");

        jLabel32.setText("Pots folder:");

        textfieldState33.setText("Click the select button to pick a folder.");
        textfieldState33.setName("moulSimplicityIn"); // NOI18N

        textfieldState34.setText("Click the select button to pick a folder.");
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

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textfieldState34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textfieldState33, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton115)
                            .addComponent(jButton116)))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jButton114)))
                .addContainerGap(302, Short.MAX_VALUE))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(28, 28, 28)
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
                .addContainerGap(168, Short.MAX_VALUE))
        );

        tabsState1.addTab("Moul", jPanel35);

        jLabel41.setText("Crowthistle folder:");

        textfieldState40.setText("Click the select button to pick a folder.");
        textfieldState40.setName("crowSimpleIn"); // NOI18N

        textfieldState41.setText("Click the select button to pick a folder.");
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

        jLabel43.setText("To include new content (i.e. footsteps), check the box in Simplicity->MystV.");

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textfieldState41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textfieldState40, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43)))
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jButton130)))
                .addGap(47, 47, 47)
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton132)
                    .addComponent(jButton131))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(47, 47, 47)
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
                .addGap(69, 69, 69)
                .addComponent(jLabel43)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        tabsState1.addTab("Crowthistle", jPanel40);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(tabsState1, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(tabsState1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tabsState3.addTab("Simplicity", jPanel32);

        jPanel39.setLayout(null);

        jButton124.setText("Get Latest List");
        jButton124.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton124ActionPerformed(evt);
            }
        });
        jPanel39.add(jButton124);
        jButton124.setBounds(300, 110, 120, 36);

        jLabel35.setText("Server:");
        jPanel39.add(jLabel35);
        jLabel35.setBounds(20, 120, 38, 16);

        textfieldState38.setText("http://ddb174.bplaced.net/uam/ages/");
        textfieldState38.setName("uamServer"); // NOI18N
        jPanel39.add(textfieldState38);
        textfieldState38.setBounds(80, 120, 200, 20);

        textfieldState39.setText("C:\\Program Files\\");
            textfieldState39.setName("uamRoot"); // NOI18N
            jPanel39.add(textfieldState39);
            textfieldState39.setBounds(90, 180, 250, 20);

            jButton125.setText("Select...");
            jButton125.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton125ActionPerformed(evt);
                }
            });
            jPanel39.add(jButton125);
            jButton125.setBounds(350, 180, 67, 36);

            jLabel36.setText("This is not intended for use yet; there may still be bugs!");
            jPanel39.add(jLabel36);
            jLabel36.setBounds(50, 40, 320, 16);

            jLabel37.setText("Pots folder:");
            jPanel39.add(jLabel37);
            jLabel37.setBounds(10, 180, 70, 16);

            jButton126.setText("List Available Ages...");
            jButton126.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton126ActionPerformed(evt);
                }
            });
            jPanel39.add(jButton126);
            jButton126.setBounds(530, 50, 150, 36);

            jScrollPane6.setViewportView(jList1);

            jPanel39.add(jScrollPane6);
            jScrollPane6.setBounds(40, 240, 80, 130);

            jScrollPane7.setViewportView(jList2);

            jPanel39.add(jScrollPane7);
            jScrollPane7.setBounds(150, 240, 80, 130);

            jScrollPane12.setViewportView(jList3);

            jPanel39.add(jScrollPane12);
            jScrollPane12.setBounds(260, 240, 70, 130);

            jButton127.setText("Download");
            jButton127.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton127ActionPerformed(evt);
                }
            });
            jPanel39.add(jButton127);
            jButton127.setBounds(370, 260, 80, 36);

            jLabel38.setText("Ages:");
            jPanel39.add(jLabel38);
            jLabel38.setBounds(50, 220, 31, 16);

            jLabel39.setText("Versions:");
            jPanel39.add(jLabel39);
            jLabel39.setBounds(150, 220, 60, 16);

            jLabel40.setText("Mirrors:");
            jPanel39.add(jLabel40);
            jLabel40.setBounds(260, 220, 41, 16);

            jButton129.setText("Delete");
            jButton129.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton129ActionPerformed(evt);
                }
            });
            jPanel39.add(jButton129);
            jButton129.setBounds(370, 300, 60, 36);

            tabsState3.addTab("UAM", jPanel39);

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
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textfieldState3, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                                .addGroup(jPanel15Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton58)
                                        .addComponent(jButton59))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton57)
                            .addGap(74, 74, 74))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(textfieldState2, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jButton55)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton78))
                        .addComponent(jButton56))
                    .addGap(24, 24, 24))
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(jLabel10)
                    .addContainerGap(673, Short.MAX_VALUE))
            );
            jPanel15Layout.setVerticalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jButton55)
                        .addComponent(textfieldState2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton78))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jButton56)
                            .addGap(38, 38, 38)
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
                                .addComponent(textfieldState3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(jLabel10)))
                    .addGap(92, 92, 92))
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
                    .addContainerGap(227, Short.MAX_VALUE))
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
                    .addContainerGap(242, Short.MAX_VALUE))
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
            jButton79.setBounds(50, 190, 200, 36);

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
                textfieldState7.setBounds(110, 40, 430, 20);

                jLabel11.setText("Pots folder:");
                jPanel17.add(jLabel11);
                jLabel11.setBounds(30, 40, 70, 16);

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
                jButton63.setBounds(550, 40, 81, 36);

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
                jButton64.setBounds(660, 20, 80, 36);

                jButton65.setText("Select....");
                jButton65.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButton65ActionPerformed(evt);
                    }
                });
                jPanel19.add(jButton65);
                jButton65.setBounds(660, 60, 80, 36);

                jLabel12.setText("Moul folder:");
                jPanel19.add(jLabel12);
                jLabel12.setBounds(40, 10, 70, 16);

                jLabel13.setText("Output folder:");
                jPanel19.add(jLabel13);
                jLabel13.setBounds(30, 50, 80, 16);

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
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton68, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
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

                checkboxState6.setSelected(true);
                checkboxState6.setText("Report suffixes written(including forced changes) and changed Age names.");
                checkboxState6.setName("reportSuffixes"); // NOI18N
                jPanel18.add(checkboxState6);
                checkboxState6.setBounds(130, 210, 490, 28);

                checkboxState7.setSelected(true);
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

                jLabel30.setText("Include new content (this checkbox is on the Simplicity->MystV tab)");

                javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
                jPanel20.setLayout(jPanel20Layout);
                jPanel20Layout.setHorizontalGroup(
                    jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel30))
                            .addComponent(checkboxState22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkboxState20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkboxState19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkboxState18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkboxState17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkboxState9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkboxState8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkboxState11, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(109, Short.MAX_VALUE))
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
                        .addComponent(jLabel30)
                        .addContainerGap(26, Short.MAX_VALUE))
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

                    javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
                    jPanel23.setLayout(jPanel23Layout);
                    jPanel23Layout.setHorizontalGroup(
                        jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
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
                                        .addGroup(jPanel23Layout.createSequentialGroup()
                                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel23Layout.createSequentialGroup()
                                                    .addComponent(jLabel21)
                                                    .addGap(32, 32, 32))
                                                .addGroup(jPanel23Layout.createSequentialGroup()
                                                    .addComponent(jLabel23)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(textfieldState17, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(textfieldState18, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)))
                                        .addGroup(jPanel23Layout.createSequentialGroup()
                                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel22)
                                                .addComponent(jLabel24))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(textfieldState20, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(textfieldState19, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton92)
                                        .addComponent(jButton93))))
                            .addGap(305, 305, 305))
                    );
                    jPanel23Layout.setVerticalGroup(
                        jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel23Layout.createSequentialGroup()
                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel23Layout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textfieldState17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel23))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel21)
                                        .addComponent(textfieldState18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel23Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(jButton92)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel23Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel22)
                                        .addComponent(textfieldState19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel23Layout.createSequentialGroup()
                                    .addGap(4, 4, 4)
                                    .addComponent(jButton93)))
                            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel23Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(textfieldState20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel24))
                                    .addGap(34, 34, 34)
                                    .addComponent(jButton90)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton94)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton100)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton91))
                                .addGroup(jPanel23Layout.createSequentialGroup()
                                    .addGap(116, 116, 116)
                                    .addComponent(jButton99)))
                            .addContainerGap(47, Short.MAX_VALUE))
                    );

                    tabsState2.addTab("tab8", jPanel23);

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
                            .addContainerGap(353, Short.MAX_VALUE))
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
                            .addContainerGap(163, Short.MAX_VALUE))
                    );

                    tabsState2.addTab("HexIsle", jPanel37);

                    textfieldState35.setText("textfieldState35");
                    textfieldState35.setName("vaultInFile"); // NOI18N

                    jButton119.setText("Read file.");
                    jButton119.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton119ActionPerformed(evt);
                        }
                    });

                    jButton120.setText("Select...");
                    jButton120.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton120ActionPerformed(evt);
                        }
                    });

                    textfieldState36.setText("textfieldState36");
                    textfieldState36.setName("vaultInFolder"); // NOI18N

                    jButton121.setText("Select...");
                    jButton121.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton121ActionPerformed(evt);
                        }
                    });

                    jButton122.setText("Save images");
                    jButton122.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton122ActionPerformed(evt);
                        }
                    });

                    jLabel33.setText("infolder:");

                    jLabel34.setText("outfolder:");

                    textfieldState37.setText("textfieldState37");
                    textfieldState37.setName("vaultOutFolder"); // NOI18N

                    jButton123.setText("Select...");
                    jButton123.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton123ActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
                    jPanel38.setLayout(jPanel38Layout);
                    jPanel38Layout.setHorizontalGroup(
                        jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                            .addGap(69, 69, 69)
                            .addComponent(textfieldState35, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton120)
                            .addGap(33, 33, 33))
                        .addGroup(jPanel38Layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(jButton119)
                            .addContainerGap(697, Short.MAX_VALUE))
                        .addGroup(jPanel38Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel38Layout.createSequentialGroup()
                                    .addComponent(jLabel33)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel38Layout.createSequentialGroup()
                                            .addComponent(textfieldState36, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButton121))
                                        .addComponent(jButton122))
                                    .addContainerGap(78, Short.MAX_VALUE))
                                .addGroup(jPanel38Layout.createSequentialGroup()
                                    .addComponent(jLabel34)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textfieldState37, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                                    .addComponent(jButton123)
                                    .addGap(53, 53, 53))))
                    );
                    jPanel38Layout.setVerticalGroup(
                        jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel38Layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton120)
                                .addComponent(textfieldState35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton119)
                            .addGap(68, 68, 68)
                            .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(textfieldState36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33)
                                .addComponent(jButton121))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel34)
                                .addComponent(textfieldState37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton123))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton122)
                            .addGap(62, 62, 62))
                    );

                    tabsState2.addTab("Vault", jPanel38);

                    javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                    jPanel13.setLayout(jPanel13Layout);
                    jPanel13Layout.setHorizontalGroup(
                        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(tabsState2, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(17, Short.MAX_VALUE))
                    );
                    jPanel13Layout.setVerticalGroup(
                        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(tabsState2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                            .addContainerGap())
                    );

                    tabsState3.addTab("Automation", jPanel13);

                    jButton15.setText("Process All (moul)");
                    jButton15.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton15ActionPerformed(evt);
                        }
                    });

                    jButton19.setText("Process All (pots)");
                    jButton19.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton19ActionPerformed(evt);
                        }
                    });

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

                    jButton16.setText("Report cross-references");
                    jButton16.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton16ActionPerformed(evt);
                        }
                    });

                    jButton18.setText("Deep Reflection Report");
                    jButton18.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton18ActionPerformed(evt);
                        }
                    });

                    jButton17.setText("Recompile prp");
                    jButton17.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton17ActionPerformed(evt);
                        }
                    });

                    jButton3.setText("Detect Type");
                    jButton3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton3ActionPerformed(evt);
                        }
                    });

                    jButton6.setText("test!!!");
                    jButton6.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton6ActionPerformed(evt);
                        }
                    });

                    jButton21.setText("Find Objects of a certain type");
                    jButton21.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton21ActionPerformed(evt);
                        }
                    });

                    jButton31.setText("find drawinterfaces that use a LayerAnimation");
                    jButton31.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton31ActionPerformed(evt);
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

                    jButton34.setText("Make sound report");
                    jButton34.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton34ActionPerformed(evt);
                        }
                    });

                    jButton36.setText("Process all files as pots");
                    jButton36.setEnabled(false);
                    jButton36.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton36ActionPerformed(evt);
                        }
                    });

                    jButton37.setText("Process all files");
                    jButton37.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton37ActionPerformed(evt);
                        }
                    });

                    jButton38.setText("Python report");

                    jButton40.setText("jButton40");
                    jButton40.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton40ActionPerformed(evt);
                        }
                    });

                    jButton41.setText("jButton41");
                    jButton41.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton41ActionPerformed(evt);
                        }
                    });

                    jButton44.setText("Dump All Objects");
                    jButton44.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton44ActionPerformed(evt);
                        }
                    });

                    jButton46.setText("read .sum file");
                    jButton46.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton46ActionPerformed(evt);
                        }
                    });

                    jButton48.setText("create .sum file");
                    jButton48.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton48ActionPerformed(evt);
                        }
                    });

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
                                            .addComponent(jButton15)
                                            .addGap(49, 49, 49)
                                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jButton46)
                                                .addComponent(jButton48))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jButton44))
                                                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton21)
                                                .addComponent(jButton31)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jButton34)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButton38)))))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(110, 110, 110)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jButton36)
                                                .addComponent(jButton37)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(66, 66, 66)
                                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(83, 83, 83)
                                                    .addComponent(jButton6)))
                                            .addGap(33, 33, 33)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton41)
                                                .addComponent(jButton40)))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(101, 101, 101)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton33)
                                        .addComponent(jButton32)
                                        .addComponent(jLabel3))))
                            .addContainerGap(119, Short.MAX_VALUE))
                    );
                    jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton15)
                                        .addComponent(jButton13))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton19)
                                        .addComponent(jButton14)
                                        .addComponent(jButton44))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton18)
                                        .addComponent(jButton17)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton3)
                                        .addComponent(jButton40))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton6)
                                        .addComponent(jButton41))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton21)
                                        .addComponent(jButton37))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton31)
                                        .addComponent(jButton36))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton34)
                                        .addComponent(jButton38)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(jButton46)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton48)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton32)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton33)
                            .addGap(119, 119, 119))
                    );

                    tabsState3.addTab("tab1", jPanel1);

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

                    jButton88.setText("select");
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
                                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jButton4)
                                                .addComponent(jButton8)
                                                .addComponent(jButton11)
                                                .addComponent(jButton47))
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
                            .addContainerGap(429, Short.MAX_VALUE))
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
                                .addComponent(jButton7))
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
                            .addContainerGap(78, Short.MAX_VALUE))
                    );

                    tabsState3.addTab("Encryption", jPanel3);

                    jPanel4.setLayout(null);

                    jPanel5.setBackground(new java.awt.Color(153, 255, 153));
                    jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                    jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            jPanel5MouseClicked(evt);
                        }
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            jPanel5MousePressed(evt);
                        }
                        public void mouseReleased(java.awt.event.MouseEvent evt) {
                            jPanel5MouseReleased(evt);
                        }
                    });
                    jPanel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                        public void mouseMoved(java.awt.event.MouseEvent evt) {
                            jPanel5MouseMoved(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                    jPanel5.setLayout(jPanel5Layout);
                    jPanel5Layout.setHorizontalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 658, Short.MAX_VALUE)
                    );
                    jPanel5Layout.setVerticalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 318, Short.MAX_VALUE)
                    );

                    jPanel4.add(jPanel5);
                    jPanel5.setBounds(0, 0, 660, 320);

                    jButton23.setText("delete marked entities");
                    jButton23.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton23ActionPerformed(evt);
                        }
                    });
                    jPanel4.add(jButton23);
                    jButton23.setBounds(691, 280, 170, 36);

                    jButton22.setText("clear all marks");
                    jButton22.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton22ActionPerformed(evt);
                        }
                    });
                    jPanel4.add(jButton22);
                    jButton22.setBounds(741, 40, 120, 36);

                    jButton29.setText("mark links");
                    jButton29.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton29ActionPerformed(evt);
                        }
                    });
                    jPanel4.add(jButton29);
                    jButton29.setBounds(751, 70, 110, 36);

                    jButton24.setText("mark links from selected");
                    jButton24.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton24ActionPerformed(evt);
                        }
                    });
                    jPanel4.add(jButton24);
                    jButton24.setBounds(715, 100, 150, 36);

                    jButton25.setText("mark all that start with STRING");
                    jButton25.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton25ActionPerformed(evt);
                        }
                    });
                    jPanel4.add(jButton25);
                    jButton25.setBounds(680, 160, 191, 36);

                    jButton28.setText("load");
                    jButton28.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton28ActionPerformed(evt);
                        }
                    });
                    jPanel4.add(jButton28);
                    jButton28.setBounds(785, 10, 80, 36);

                    jButton27.setText("mark links to selected");
                    jButton27.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton27ActionPerformed(evt);
                        }
                    });
                    jPanel4.add(jButton27);
                    jButton27.setBounds(689, 130, 170, 36);

                    jButton26.setText("... end with STRING");
                    jButton26.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton26ActionPerformed(evt);
                        }
                    });
                    jPanel4.add(jButton26);
                    jButton26.setBounds(700, 190, 160, 36);

                    jButton30.setText("repaint");
                    jButton30.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton30ActionPerformed(evt);
                        }
                    });
                    jPanel4.add(jButton30);
                    jButton30.setBounds(680, 10, 81, 36);

                    tabsState3.addTab("CrossRefTool", jPanel4);

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

                    jButton49.setText(".age test");
                    jButton49.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton49ActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                    jPanel6.setLayout(jPanel6Layout);
                    jPanel6Layout.setHorizontalGroup(
                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(96, 96, 96)
                            .addComponent(jButton49)
                            .addContainerGap(335, Short.MAX_VALUE))
                    );
                    jPanel6Layout.setVerticalGroup(
                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGap(49, 49, 49)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGap(103, 103, 103)
                                    .addComponent(jButton49)))
                            .addContainerGap(201, Short.MAX_VALUE))
                    );

                    tabsState3.addTab("tab4", jPanel6);

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

                    jLabel4.setText("Enter a list of angles(20=full circle) and distances.  They will be summed as vectors.");

                    jTextArea3.setColumns(20);
                    jTextArea3.setRows(5);
                    jTextArea3.setText("angle1,dist1,angle2,dist2,...\ne.g.:\n8.5,360,\n1,556,\n2,442,\n15,420\n\n15,1386,\n11,274,\n11,164,\n17,269,\n11.25,237,\n10,807,\n16.5,91,\n15,1049,\n1,50,\n15,41,\n9,50,\n7,524,\n0,1154,\n2,629\n\n0.25,780,\n1,2052,\n7,825,\n10,866,\n2,105,\n10,2212,\n11.25,1245,\n15,748,\n13,429,\n16.25,315,\n10.5,264,\n15,461,\n15,652\n\n2.75,109,\n0,1259,\n14,210,\n9,196,\n15,415,\n17,160,\n15,360,\n13,251,\n17.25,424\n\n5,835,\n5,342,\n10,369,\n5,538,\n2.25,205,\n5,205,\n7.5,511");
                    jScrollPane3.setViewportView(jTextArea3);

                    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                    jPanel11.setLayout(jPanel11Layout);
                    jPanel11Layout.setHorizontalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton35)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    jPanel11Layout.setVerticalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

                    jPanel36.setBorder(javax.swing.BorderFactory.createEtchedBorder());

                    jButton105.setText("jButton105");
                    jButton105.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton105ActionPerformed(evt);
                        }
                    });

                    textfieldState26.setText("textfieldState26");
                    textfieldState26.setName("address"); // NOI18N

                    textfieldState27.setText("30");
                    textfieldState27.setName("updateTime"); // NOI18N

                    textfieldState28.setText("textfieldState28");
                    textfieldState28.setName("searchnet"); // NOI18N

                    javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
                    jPanel36.setLayout(jPanel36Layout);
                    jPanel36Layout.setHorizontalGroup(
                        jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel36Layout.createSequentialGroup()
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel36Layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(jButton105))
                                .addGroup(jPanel36Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textfieldState28, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textfieldState27, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textfieldState26, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(129, Short.MAX_VALUE))
                    );
                    jPanel36Layout.setVerticalGroup(
                        jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addComponent(textfieldState26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(textfieldState27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(textfieldState28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                            .addComponent(jButton105)
                            .addGap(57, 57, 57))
                    );

                    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                    jPanel7.setLayout(jPanel7Layout);
                    jPanel7Layout.setHorizontalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(142, 142, 142)
                                    .addComponent(jButton43)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(55, Short.MAX_VALUE))
                    );
                    jPanel7Layout.setVerticalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addGap(132, 132, 132)
                                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addGap(47, 47, 47)
                                            .addComponent(jButton43)))))
                            .addContainerGap(47, Short.MAX_VALUE))
                    );

                    tabsState3.addTab("GameHelp", jPanel7);

                    jPanel10.setLayout(null);

                    jButton45.setText("Depack");
                    jButton45.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton45ActionPerformed(evt);
                        }
                    });
                    jPanel10.add(jButton45);
                    jButton45.setBounds(50, 120, 66, 36);

                    textfieldState21.setText("textfieldState21");
                    textfieldState21.setName("realmystIn"); // NOI18N
                    jPanel10.add(textfieldState21);
                    textfieldState21.setBounds(80, 30, 410, 20);

                    jButton95.setText("select");
                    jButton95.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton95ActionPerformed(evt);
                        }
                    });
                    jPanel10.add(jButton95);
                    jButton95.setBounds(500, 20, 57, 36);

                    jButton101.setText("select");
                    jButton101.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton101ActionPerformed(evt);
                        }
                    });
                    jPanel10.add(jButton101);
                    jButton101.setBounds(510, 70, 57, 36);

                    textfieldState24.setText("textfieldState24");
                    textfieldState24.setName("realmystOut"); // NOI18N
                    jPanel10.add(textfieldState24);
                    textfieldState24.setBounds(80, 80, 410, 20);

                    jLabel25.setText("in file:");
                    jPanel10.add(jLabel25);
                    jLabel25.setBounds(10, 30, 32, 16);

                    jLabel26.setText("out folder:");
                    jPanel10.add(jLabel26);
                    jLabel26.setBounds(10, 80, 60, 16);

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
                    jButton103.setBounds(440, 200, 57, 36);

                    jButton104.setText("sdb test");
                    jButton104.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton104ActionPerformed(evt);
                        }
                    });
                    jPanel10.add(jButton104);
                    jButton104.setBounds(224, 120, 80, 36);

                    jButton106.setText("mdb test");
                    jButton106.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton106ActionPerformed(evt);
                        }
                    });
                    jPanel10.add(jButton106);
                    jButton106.setBounds(330, 120, 72, 36);

                    jButton108.setText("hsm test");
                    jButton108.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton108ActionPerformed(evt);
                        }
                    });
                    jPanel10.add(jButton108);
                    jButton108.setBounds(410, 120, 72, 36);

                    jButton109.setText("string search");
                    jButton109.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton109ActionPerformed(evt);
                        }
                    });
                    jPanel10.add(jButton109);
                    jButton109.setBounds(680, 60, 96, 36);

                    jButton110.setText("full test");
                    jButton110.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton110ActionPerformed(evt);
                        }
                    });
                    jPanel10.add(jButton110);
                    jButton110.setBounds(380, 160, 63, 36);

                    textfieldState29.setText("textfieldState29");
                    textfieldState29.setName("searchString"); // NOI18N
                    jPanel10.add(textfieldState29);
                    textfieldState29.setBounds(650, 40, 130, 20);

                    textfieldState30.setText("textfieldState30");
                    textfieldState30.setName("searchPath"); // NOI18N
                    jPanel10.add(textfieldState30);
                    textfieldState30.setBounds(650, 10, 87, 20);

                    tabsState3.addTab("realMyst", jPanel10);

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
                            .addContainerGap(698, Short.MAX_VALUE))
                    );
                    jPanel12Layout.setVerticalGroup(
                        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(jButton50)
                            .addContainerGap(334, Short.MAX_VALUE))
                    );

                    tabsState3.addTab("Riven", jPanel12);

                    jPanel24.setLayout(null);

                    jButton1.setText("Select...");
                    jButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton1ActionPerformed(evt);
                        }
                    });
                    jPanel24.add(jButton1);
                    jButton1.setBounds(10, 10, 90, 36);

                    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
                    jComboBox1.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                            jComboBox1ItemStateChanged(evt);
                        }
                    });
                    jComboBox1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jComboBox1ActionPerformed(evt);
                        }
                    });
                    jPanel24.add(jComboBox1);
                    jComboBox1.setBounds(110, 10, 610, 22);

                    jLabel1.setText("STRING:");
                    jPanel24.add(jLabel1);
                    jLabel1.setBounds(10, 50, 70, 16);
                    jPanel24.add(jTextField1);
                    jTextField1.setBounds(80, 50, 168, 20);

                    jLabel2.setText("TEXT:");
                    jPanel24.add(jLabel2);
                    jLabel2.setBounds(280, 50, 50, 16);

                    jTextArea2.setColumns(20);
                    jTextArea2.setRows(5);
                    jScrollPane2.setViewportView(jTextArea2);

                    jPanel24.add(jScrollPane2);
                    jScrollPane2.setBounds(350, 50, 310, 40);

                    filedirButtonGroup.add(jRadioButton1);
                    jRadioButton1.setText("moul");
                    jRadioButton1.addChangeListener(new javax.swing.event.ChangeListener() {
                        public void stateChanged(javax.swing.event.ChangeEvent evt) {
                            jRadioButton1StateChanged(evt);
                        }
                    });
                    jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jRadioButton1ActionPerformed(evt);
                        }
                    });
                    jPanel24.add(jRadioButton1);
                    jRadioButton1.setBounds(150, 120, 90, 10);

                    filedirButtonGroup.add(jRadioButton2);
                    jRadioButton2.setText("pots");
                    jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jRadioButton2ActionPerformed(evt);
                        }
                    });
                    jPanel24.add(jRadioButton2);
                    jRadioButton2.setBounds(150, 130, 110, 10);

                    filedirButtonGroup.add(jRadioButton3);
                    jRadioButton3.setText("output");
                    jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jRadioButton3ActionPerformed(evt);
                        }
                    });
                    jPanel24.add(jRadioButton3);
                    jRadioButton3.setBounds(150, 140, 100, 10);

                    filedirButtonGroup.add(jRadioButton4);
                    jRadioButton4.setSelected(true);
                    jRadioButton4.setText("absolute");
                    jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jRadioButton4ActionPerformed(evt);
                        }
                    });
                    jPanel24.add(jRadioButton4);
                    jRadioButton4.setBounds(150, 150, 110, 10);

                    tabsState3.addTab("Legacy", jPanel24);

                    jPanel28.setLayout(null);

                    jButton70.setText("Start Server");
                    jButton70.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton70ActionPerformed(evt);
                        }
                    });
                    jPanel28.add(jButton70);
                    jButton70.setBounds(140, 240, 87, 36);

                    textfieldState9.setText("C:\\Documents and Settings\\user\\Desktop\\web");
                    textfieldState9.setName("proxyFolder"); // NOI18N
                    jPanel28.add(textfieldState9);
                    textfieldState9.setBounds(110, 90, 300, 20);

                    jLabel15.setText("base folder:");
                    jPanel28.add(jLabel15);
                    jLabel15.setBounds(20, 90, 80, 16);

                    jLabel16.setText("port:");
                    jPanel28.add(jLabel16);
                    jLabel16.setBounds(60, 120, 50, 16);

                    textfieldState10.setText("8012");
                    textfieldState10.setName("proxyPort"); // NOI18N
                    jPanel28.add(textfieldState10);
                    textfieldState10.setBounds(110, 120, 100, 20);

                    jButton71.setText("Stop Server");
                    jButton71.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton71ActionPerformed(evt);
                        }
                    });
                    jPanel28.add(jButton71);
                    jButton71.setBounds(260, 240, 120, 36);

                    checkboxState10.setText("Log files read.");
                    checkboxState10.setName("proxyLogReads"); // NOI18N
                    jPanel28.add(checkboxState10);
                    checkboxState10.setBounds(250, 170, 130, 28);

                    jLabel17.setText("You have to stop and start the server for settings changes to take effect.");
                    jPanel28.add(jLabel17);
                    jLabel17.setBounds(40, 40, 420, 16);

                    jButton77.setText("select...");
                    jButton77.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton77ActionPerformed(evt);
                        }
                    });
                    jPanel28.add(jButton77);
                    jButton77.setBounds(410, 80, 66, 36);

                    tabsState3.addTab("Proxy", jPanel28);

                    jPanel29.setLayout(null);

                    jTextArea6.setColumns(20);
                    jTextArea6.setRows(5);
                    jScrollPane11.setViewportView(jTextArea6);

                    jPanel29.add(jScrollPane11);
                    jScrollPane11.setBounds(10, 10, 820, 230);

                    jButton72.setText("copy from main output");
                    jButton72.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton72ActionPerformed(evt);
                        }
                    });
                    jPanel29.add(jButton72);
                    jButton72.setBounds(20, 260, 150, 36);

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

                    jPanel29.add(jPanel30);
                    jPanel30.setBounds(20, 290, 240, 80);

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

                    jPanel29.add(jPanel31);
                    jPanel31.setBounds(330, 250, 200, 100);

                    jButton87.setText("Copy all text to clipboard");
                    jButton87.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton87ActionPerformed(evt);
                        }
                    });
                    jPanel29.add(jButton87);
                    jButton87.setBounds(280, 350, 160, 36);

                    tabsState3.addTab("List Analysis", jPanel29);

                    textfieldState13.setText("C:\\Documents and Settings\\user\\Desktop\\output\\ahny\\Ahnonay_District_BuiltIn.prp");
                    textfieldState13.setName("deepViewFilename"); // NOI18N

                    jButton80.setText("Select...");
                    jButton80.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton80ActionPerformed(evt);
                        }
                    });

                    jButton81.setText("Read");
                    jButton81.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton81ActionPerformed(evt);
                        }
                    });

                    jScrollPane13.setViewportView(jDesktopPane1);

                    jButton82.setText("All Types");
                    jButton82.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton82ActionPerformed(evt);
                        }
                    });

                    jButton83.setText("Clear");
                    jButton83.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton83ActionPerformed(evt);
                        }
                    });

                    jButton84.setText("Save changes.");
                    jButton84.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton84ActionPerformed(evt);
                        }
                    });

                    textfieldState14.setText("C:\\Documents and Settings\\user\\Desktop\\output\\");
                        textfieldState14.setName("deepViewOutput"); // NOI18N

                        jButton85.setText("Select...");
                        jButton85.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton85ActionPerformed(evt);
                            }
                        });

                        jButton117.setText("Strings");
                        jButton117.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton117ActionPerformed(evt);
                            }
                        });

                        jButton118.setText("Refs");
                        jButton118.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton118ActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
                        jPanel33.setLayout(jPanel33Layout);
                        jPanel33Layout.setHorizontalGroup(
                            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel33Layout.createSequentialGroup()
                                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel33Layout.createSequentialGroup()
                                                .addComponent(textfieldState13, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(jPanel33Layout.createSequentialGroup()
                                                .addComponent(textfieldState14, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton85)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton84)
                                                .addGap(53, 53, 53)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton80)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton81)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton82)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton83)
                                        .addGap(39, 39, 39))
                                    .addGroup(jPanel33Layout.createSequentialGroup()
                                        .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton118)
                                            .addComponent(jButton117))
                                        .addContainerGap())))
                        );
                        jPanel33Layout.setVerticalGroup(
                            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel33Layout.createSequentialGroup()
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel33Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(textfieldState13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton80)
                                            .addComponent(jButton81)
                                            .addComponent(jButton82)
                                            .addComponent(jButton83))
                                        .addGap(3, 3, 3)
                                        .addComponent(textfieldState14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel33Layout.createSequentialGroup()
                                        .addContainerGap(39, Short.MAX_VALUE)
                                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButton84)
                                            .addComponent(jButton85))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel33Layout.createSequentialGroup()
                                        .addComponent(jButton117)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton118))
                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
                                .addContainerGap())
                        );

                        tabsState3.addTab("DeepView", jPanel33);

                        jTextArea5.setColumns(20);
                        jTextArea5.setEditable(false);
                        jTextArea5.setRows(5);
                        jScrollPane10.setViewportView(jTextArea5);

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

                        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
                        jPanel25.setLayout(jPanel25Layout);
                        jPanel25Layout.setHorizontalGroup(
                            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(imagePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(40, Short.MAX_VALUE))
                        );
                        jPanel25Layout.setVerticalGroup(
                            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(imagePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(27, Short.MAX_VALUE))
                        );

                        tabsState3.addTab("Help", jPanel25);

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tabsState3, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(27, Short.MAX_VALUE))
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(450, 450, 450)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(tabsState3, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(25, Short.MAX_VALUE))
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        m.msg("hi");
        if(fc==null) fc = new JFileChooser();
        if(this.jRadioButton1.isSelected()) fc.setCurrentDirectory(new File(this.moul));
        if(this.jRadioButton2.isSelected()) fc.setCurrentDirectory(new File(this.pots));
        if(this.jRadioButton3.isSelected()) fc.setCurrentDirectory(new File(this.out));
        int wasFileChosen = fc.showOpenDialog(null);
        //Main.message(returnVal);
        if(wasFileChosen==0)
        {
            //fc.getSelectedFiles(); //for multi-select.
            String file = fc.getSelectedFile().getAbsolutePath();
            if(this.jRadioButton1.isSelected()) file = fc.getSelectedFile().getName();
            if(this.jRadioButton2.isSelected()) file = fc.getSelectedFile().getName();
            if(this.jRadioButton3.isSelected()) file = fc.getSelectedFile().getName();
            jComboBox1.setSelectedItem(file);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.jTextArea1.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        UruFileTypes type = UruCrypt.DetectType(this.getSelectedFilename());
        m.msg("Filetype is:"+type.toString());
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
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        String report = prputils.MakeObjectIndexReport(filecontents);
        FileUtils.WriteFile(_staticsettings.outputdir+file.getName(), report.getBytes());
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        prputils.DumpObjects(filecontents, Typeid.plClusterGroup);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        m.err("This method of converting Moul is deprecated, and probably doesn't work correctly.");
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        prputils.ProcessAllMoul(filecontents, automation.mystAutomation.moulReadable);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if(dosavesettings)//GEN-LAST:event_jComboBox1ActionPerformed
        {
            savesettings();
        }
    }                                          

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        prputils.ReportCrossLinks(filecontents);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        m.err("This method of recompiling Moul is deprecated, and probably doesn't work right.");
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        prputils.Compiler.RecompilePrp(filecontents, new automation.mystAutomation.moulDecider());
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        prputils.ReportDeep(filecontents);
        
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        context c = context.createFromBytestream(new Bytestream(filecontents));
        //c.readversion = 3; //read as pots
        uru.moulprp.prputils.ProcessPotsPrp(c);
    }//GEN-LAST:event_jButton19ActionPerformed

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

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MousePressed
        vis.handleClick(0, evt.getButton(), evt.getX(), evt.getY()); //0 means down
    }//GEN-LAST:event_jPanel5MousePressed

    private void jPanel5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseReleased
        vis.handleClick(1, evt.getButton(), evt.getX(), evt.getY()); //1 means up
    }//GEN-LAST:event_jPanel5MouseReleased

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        vis.clearAllMarks();
        vis.drawAll();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        vis.deleteMarkedEntities();
        vis.drawAll();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        vis.markLinks(false,true);
        vis.drawAll();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        vis.markEntitiesThatStartWith(this.jTextField1.getText());
        vis.drawAll();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        vis.markEntitiesThatEndWith(this.jTextField1.getText());
        vis.drawAll();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        vis.markLinks(true,false);
        vis.drawAll();
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        boolean registerScannedLinks = true; //whether to bother dealing with refs only found by scanning.
        
        //boolean includeSceneNodes = false;
        
        //vis = new visualisation();
        //java.awt.Dimension dim = new java.awt.Dimension(300,200);
        //this.jPanel5.setMinimumSize(dim);
        java.awt.Dimension dim = this.jPanel5.getSize();
        vis = new visualisation(this.jPanel5.getGraphics(), dim.width, dim.height);
        vis.leftborder = 100;
        vis.boxsize = 6;
        
        
        //read from input file.
        byte[] filedata = FileUtils.ReadFile(this.out+"crosslinkreport.csv");
        byte[][] lines = b.splitBytes(filedata, (byte)'\n');
        int linecount = lines.length;
        int startline = 1; //skip the first line.
        
        //register all entities
        for(int i=startline;i<linecount;i++) {
            byte[] curline = lines[i];
            byte[][] fields = b.splitBytes(curline, (byte)';');
            //int fieldcount = fields.length;
            if (fields.length > 1) {
                //String scantype = b.BytesToString(fields[0]);
                byte[] fromnamebytes = b.appendBytes(fields[1],b.StringToBytes("**"),fields[2]); //append the objectname and objecttype.
                String fromname = b.BytesToString(fromnamebytes);
                vis.addEntity(fromname);
                
                if(registerScannedLinks) {
                    byte[] tonamebytes = b.appendBytes(fields[4],b.StringToBytes("**"),fields[5]); //append the objectname and objecttype.
                    String toname = b.BytesToString(tonamebytes);
                    vis.addEntity(toname);
                }
            }
        }
        
        //register all relations.
        for(int i=startline;i<linecount;i++) {
            byte[] curline = lines[i];
            byte[][] fields = b.splitBytes(curline, (byte)';');
            //int fieldcount = fields.length;
            if(fields.length > 1) {
                //String scantype = b.BytesToString(fields[0]);
                byte[] fromnamebytes = b.appendBytes(fields[1],b.StringToBytes("**"),fields[2]); //append the objectname and objecttype.
                String fromname = b.BytesToString(fromnamebytes);
                byte[] tonamebytes = b.appendBytes(fields[4],b.StringToBytes("**"),fields[5]); //append the objectname and objecttype.
                String toname = b.BytesToString(tonamebytes);
                
                vis.addRelation(fromname,toname);
            }
        }
        
        vis.assignRandomPositions();
        
        vis.drawAll();
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        vis.markLinks(true,true);
        vis.drawAll();
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        vis.repaint();
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jPanel5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseMoved
        //m.msg("moved");
        vis.handleClick(0, 0, evt.getX(), evt.getY()); //movement
    }//GEN-LAST:event_jPanel5MouseMoved

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        byte[] filecontents = FileUtils.ReadFile((String)this.jComboBox1.getSelectedItem());
        uru.moulprp.prputils.FindDrawInterfacesThatUseLayerAnimations(filecontents);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        String in = this.jTextArea2.getText();
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
        this.jTextArea2.setText(out);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        String in = this.jTextArea2.getText();
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
        this.jTextArea2.setText(out);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        File file = new File(this.getSelectedFilename());
        byte[] filecontents = FileUtils.ReadFile(file.getAbsoluteFile());
        //try{
            String report = uru.moulprp.prpreports.MakeFullReport(filecontents);
            FileUtils.WriteFile(_staticsettings.outputdir+"fullreport.txt", report.getBytes());
        //}catch(Exception e){}

    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        String text = this.jTextArea3.getText();
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
        this.jTextField2.setText(result);
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        String dir = this.getSelectedFilename();
        //prputils.ProcessAllFiles(dir);//, 6);
        automation.mystAutomation.readAllPotsPrps(dir);
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        String dir = this.getSelectedFilename();
        //prputils.ProcessAllFiles(dir);//, 3);
        automation.mystAutomation.readAllPotsPrps(dir);
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        savesettings();
    }//GEN-LAST:event_jRadioButton4ActionPerformed

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

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        /*if(dosavesettings)
        {
            savesettings();
        }*/
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        loadsettings();
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        //savesettings();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jRadioButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButton1StateChanged
        //savesettings();
    }//GEN-LAST:event_jRadioButton1StateChanged

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        savesettings();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        savesettings();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        savesettings();
    }//GEN-LAST:event_jRadioButton3ActionPerformed

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
                m.msg("servertime="+Long.toString(servertime));
                m.msg("dnitime="+Long.toString(dnitime));
                m.msg("agetime="+Long.toString(agetime));
                m.msg("agetime%="+Double.toString(pt.percentTimeOfDay));
                m.msg("Time of cur day: "+new java.util.Date(curdaystart).toString());
                m.msg("Time of next day: "+new java.util.Date(nextdaystart).toString());
            }
            m.msg("Time of today's "+pairs[i].name+" symbol: "+new java.util.Date(curdaysymbol).toString());
            m.msg("Time of tomorrow's "+pairs[i].name+" symbol: "+new java.util.Date(nextdaysymbol).toString());
        //}
        //catch(Exception e)
        //{
        //    m.err("Incorrect format; use \"Feb 15, 1982 8:00 AM\" for example.");
        //}
        }
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        long time = new java.util.Date().getTime();
        m.msg("Current GMT time is: "+Long.toString(time));
        int tzoffset = java.util.Calendar.getInstance().get(java.util.Calendar.ZONE_OFFSET);
        int dstoffset = java.util.Calendar.getInstance().get(java.util.Calendar.DST_OFFSET);
        //long localtime = time + tzoffset + dstoffset;
        long localtime = time/10000000L + 1240428288L;
        long low = time & 0xFFFFFFFFL;
        long high = (time >>> 32) & 0xFFFFFFFFL;
        long low2 = time/10000000L + 1240428288L;
        long high2 = (time % 10000000L) / 10;
        localtime = time - 7*60*60*1000; //adjust time zone.
        m.msg("Current local time is: "+Long.toString(localtime));
            m.msg("Time of gmttime: "+new java.util.Date(time));
            m.msg("Time of localtime: "+new java.util.Date(localtime));
        m.msg("test: "+new java.util.Date(1240428288));
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        prputils.DumpObjects(filecontents, null);
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

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        try
        {
            uru.moulprp.sumfile sfile = new uru.moulprp.sumfile(filecontents,true);
        }
        catch(Exception e)
        {
            m.err("Exception while processing sumfile.");
        }
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        byte[] filecontents = FileUtils.ReadFile(textfieldState15.getText());
        byte[] md5 = shared.CryptHashes.GetMd5(filecontents);
        String md5str = b.BytesToHexString(md5);
        m.msg("md5: "+md5str);
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        String age = this.jTextField1.getText();
        //String infolder = this.pots+"\\dat\\";
        String infolder = "/shared/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/dat";
        String outfolder = this.out;
        Bytes b = uru.moulprp.sumfile.createSumfile(infolder, age);
        FileUtils.WriteFile(outfolder+"/sum.sum", b);
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        //uru.moulprp.textfile tf = uru.moulprp.textfile.loadFromFile("/shared/DontBackup/deletable/deletable/Program Files/Ubi Soft/Cyan Worlds/dat/EderDelin.age", true);
        byte[] filecontents = FileUtils.ReadFile(this.getSelectedFilename());
        uru.moulprp.textfile tf = uru.moulprp.textfile.createFromBytes(new Bytes(filecontents));
        tf.dump();
        tf.setVariable("SequencePrefix", "2pi");
        tf.dump();
        //tf.setVariable(moul, moul);
    }//GEN-LAST:event_jButton49ActionPerformed

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
    shared.State.AllStates.loadandpush(settingsfile);
    
    jTextArea5.scrollRectToVisible(new java.awt.Rectangle(0,0,1,1));
    
}//GEN-LAST:event_formWindowOpened

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    shared.State.AllStates.pullandsave(settingsfile);
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
}//GEN-LAST:event_jButton81ActionPerformed

private void jButton82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton82ActionPerformed
    deep.openAllTypes();
}//GEN-LAST:event_jButton82ActionPerformed

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
    
    m.msg("Subcount3s handled: "+Integer.toString(realmyst.Count3Undone.subcount3.numhandled));
    m.msg("Subcount3s ignored: "+Integer.toString(realmyst.Count3Undone.subcount3.numignored));
    m.msg("Count3s handled: "+Integer.toString(realmyst.Count3Undone.numhandled));
    m.msg("Count3s ignored: "+Integer.toString(realmyst.Count3Undone.numignored));
    
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
    String search = this.textfieldState28.getText();
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
                m.msg("String found in file:"+filename+"  at pos 0x"+Integer.toHexString(pos));
                int dummy=0;
            }
        }
        else
        {
            boolean allfound = filesearcher.search.searchForStrings(f, searchstrs);
            if(allfound)
            {
                m.msg("Strings all found in file:"+f.getName());
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
        m.msg("Whirlpool: "+hashstr);
}//GEN-LAST:event_jButton128ActionPerformed

private void jButton129ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton129ActionPerformed
    gui.UamGui.PerformDeletion();
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
    private shared.State.CheckboxState checkboxState3;
    private shared.State.CheckboxState checkboxState4;
    private shared.State.CheckboxState checkboxState5;
    private shared.State.CheckboxState checkboxState6;
    private shared.State.CheckboxState checkboxState7;
    private shared.State.CheckboxState checkboxState8;
    private shared.State.CheckboxState checkboxState9;
    private javax.swing.ButtonGroup filedirButtonGroup;
    private shared.ImagePanel imagePanel2;
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
    private javax.swing.JButton jButton14;
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
    private javax.swing.JButton jButton49;
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
    private javax.swing.JButton jButton82;
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
    private javax.swing.JComboBox jComboBox1;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private shared.State.ListState listState2;
    private shared.State.ListState listState3;
    private shared.State.ListState listState4;
    private shared.State.TabsState tabsState1;
    private shared.State.TabsState tabsState2;
    private shared.State.TabsState tabsState3;
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
    private shared.State.TextfieldState textfieldState5;
    private shared.State.TextfieldState textfieldState6;
    private shared.State.TextfieldState textfieldState7;
    private shared.State.TextfieldState textfieldState8;
    private shared.State.TextfieldState textfieldState9;
    // End of variables declaration//GEN-END:variables
    
}
